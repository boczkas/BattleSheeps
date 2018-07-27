var myBoard = document.getElementById("myBoard");
var opponentBoard = document.getElementById("opponentBoard");
var timerIntervalID;
var myRound = true;
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
    var coordinates = this.getAttribute("value");

    $.ajax({
        type: "POST",
        url: "playing",
        data: {guiCoordinates: coordinates}
    });
    switchRound();
}

function refresh() {
    refreshBoard("getBoardView");
    refreshBoard("getShotBoardView");
}

function refreshBoard(url) {
    $.ajax({
        type: 'POST',
        url: url,
        success: function (data) {
            if (jQuery.isEmptyObject(data) !== true) {
                setShotResults(data);
            }
        }
    });
}

function setShotResults(container) {
    for (var key in container) {
        var elementById = document.getElementById(key);
        elementById.innerText = container[key];
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
            switchRound();
            timerVal = duration;
        }
    }, 1000);
}

function stopTimer() {
    clearInterval(timerIntervalID);
}

function switchRound() {

    if(myRound === true) {
        myRound = false;
        console.log("my round");
        stopTimer();
        //enable clicking
        startTimer(120, timerDiv);
    }
    else
        myRound = true;
        console.log("not my round");
        stopTimer();
        //disable clicking
        startTimer(120, timerDiv);
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
            stopTimer();
            startTimer(120, timerDiv);
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
