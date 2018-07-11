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

            var attribute = (j.toString()).concat(i.toString());

            setAttribute(cell, attribute, myBoard);

            cell.setAttribute("value", j + "," + i);

            if (clickable) {
                cell.onclick = clickMe;
            }

            cell.innerText = "_";
            row.appendChild(cell);
        }
    }
}

function setAttribute(cell, attribute, myBoard) {
    if (myBoard) {
        cell.setAttribute("id", "cell" + attribute);
    }
    else {
        cell.setAttribute("id", "opp_cell" + attribute);
    }
}

function clickMe() {
    var coordinates = this.getAttribute("value");

    $.ajax({
        type: "POST",
        url: "playing",
        data: {guiCoordinates: coordinates}
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
            if (jQuery.isEmptyObject(data) !== true) {
                setElementValues(data);
            }
        }
    });
}

function setElementValues(container) {
    for (var key in container) {
        var elementById = document.getElementById(key);
        elementById.innerText = container[key];
    }
}
