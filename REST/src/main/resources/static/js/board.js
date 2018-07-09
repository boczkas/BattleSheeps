window.onload = function load() {

    var myBoard = document.getElementById("myBoard");
    var opponentBoard = document.getElementById("opponentBoard");
    setDivsForBoard(myBoard, false, true);
    setDivsForBoard(opponentBoard, true, false);
};

function setDivsForBoard(board, clickable, myBoard) {

    for (var i = 0; i < 10; i++) {
        var row = document.createElement('DIV');
        row.setAttribute("class", "row");
        board.appendChild(row);

        for (var j = 0; j < 10; j++) {
            var cell = document.createElement('DIV');
            cell.setAttribute("class", "cell col-xs-1 col-xs-push-1");
            if (myBoard) {
                cell.setAttribute("id", "cell" + j + i);
            }
            else {
                cell.setAttribute("id", "opp_cell" + j + i);
            }
            cell.setAttribute("value", j + "," + i);
            if (clickable) {
                cell.onclick = clickMe;
            }
            cell.innerText = "_";
            row.appendChild(cell);
        }
    }
}

function clickMe() {
    var coords = this.getAttribute("value");

    $.ajax({
        type: "POST",
        url: "playing",
        data: {coordinates: coords}
    });
}


function refresh() {
    refreshBoard("getmyboard");
    refreshBoard("getopponentboard");
}

function refreshBoard(url) {
    $.ajax({
        type: 'POST',
        url: url,
        success: function (data) {
            var ships = JSON.parse(data);


            for(var key in ships) {

                var elementById = document.getElementById(key);
                elementById.innerText = ships[key];
            }

        }
    });
}

function refreshElement(entry) {
    if(entry.localeCompare("") !== 0) {

        for(var key in entry) {

            var elementById = document.getElementById(key);
            elementById.innerText = entry[key];
        }
    }

}
