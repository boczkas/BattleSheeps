var myBoard = document.getElementById("myBoard");
var opponentBoard = document.getElementById("opponentBoard");
var timerIntervalID;
var myRound = false;
var stompClient = null;

window.onload = function load() {
    setDivsForBoard(myBoard, false, true);
    setDivsForBoard(opponentBoard, true, false);
    getUserName();
    connect();
};

window.onunload = function close() {
    disconnect();
};

function setCellValue(cell, j, i) {
    cell.setAttribute("value", j + "," + i);
}

function setDivsForBoard(board, clickable, myBoard) {

    for (var i = 0; i < 10; i++) {
        var row = document.createElement('DIV');
        row.setAttribute("class", "row");
        board.appendChild(row);

        for (var j = 0; j < 10; j++) {
            var cell = document.createElement('DIV');
            cell.setAttribute("class", "cell col-xs-1 col-xs-push-1");

            var attribute = j.toString() + "," + i.toString();

            setCellAttribute(cell, attribute, myBoard);

            setCellValue(cell, j, i);

            if (clickable) {
                cell.onclick = clickMe;
            }

            cell.innerText = "_";
            row.appendChild(cell);
        }
    }
}

function setCellAttribute(cell, attribute, myBoard) {
    if (myBoard) {
        cell.setAttribute("id", "cell" + attribute);
    }
    else {
        cell.setAttribute("id", "opp_cell" + attribute);
    }
}

var timerDiv = document.getElementById("timer");

function clickMe() {

    if (myRound) {
        var coordinates = this.getAttribute("value");

        $.ajax({
            type: "POST",
            url: "playing",
            data: {guiCoordinates: coordinates}
        });
        switchRound();
    }
}

function setShotResults(container) {
    console.log(container);
    var json = JSON.parse(container);
    for (var key in json) {
        console.log(key);
        var elementById = document.getElementById(key);
        elementById.innerText = json[key];
    }
}

function startTimer(duration, display) {
    var timerVal = duration, minutes, seconds;
    timerIntervalID = setInterval(function () {
        minutes = parseInt(timerVal / 60, 10);
        seconds = parseInt(timerVal % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds;



        if (--timerVal < 0) {
            switchBoolean();
            switchRound();
            timerVal = duration;
        }
    }, 1000);
}

function stopTimer() {
    clearInterval(timerIntervalID);
}

function switchBoolean() {
    myRound = myRound !== true;
}

function switchRound() {
    if(myRound === true) {
        myRound = false;
        console.log("my round");
    } else {
        myRound = true;
        console.log("not my round");
    }
}


var userName;

function connect() {
    var socket = new SockJS('/timer-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect('guest', 'guest', function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/synchro/timer/' + userName, function (synchronize) {
            console.log(synchronize);
            myRound = true;
        });

        stompClient.subscribe('/synchro/boards/' + userName, function (board) {
            setShotResults(board.body);
        });
    });
}

function getUserName() {
    $.ajax({
        type: 'GET',
        url: 'getname',
        success: function (data) {
           userName = data;
        }
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}
