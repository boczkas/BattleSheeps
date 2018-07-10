var myBoard = document.getElementById("myBoard");
var opponentBoard = document.getElementById("opponentBoard");

window.onload = function load() {
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
            var ships = data;

            if (Object.keys(data).length !== 0) {

                if (url === "getmyboard")
                    clearBoard(myBoard);

                for (var key in ships) {
                    if (key !== 0) {
                        var elementById = document.getElementById(key);
                        if (elementById !== null)
                            elementById.innerText = ships[key];
                    }
                }

            }
        }
    });
}

function clearBoard() {
    var cells = myBoard.getElementsByClassName("cell");

    for(var i = 0; i < cells.length; i++) {

            cells[i].innerText = "_";

    }
}
