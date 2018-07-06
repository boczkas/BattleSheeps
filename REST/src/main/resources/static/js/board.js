window.onload = function load() {

    var myBoard = document.getElementById("myBoard");
    var opponentBoard = document.getElementById("opponentBoard");
    setDivsForBoard(myBoard, true);
    setDivsForBoard(opponentBoard, false);
};

function setDivsForBoard(board, clickable) {

    for(var i = 0; i < 10; i++) {
        var row = document.createElement('DIV');
        row.setAttribute("class", "row");
        board.appendChild(row);

        for(var j = 0; j < 10; j++) {
            var cell = document.createElement('DIV');
            cell.setAttribute("class", "cell col-xs-1 col-xs-push-1");
            cell.setAttribute("id", "cell" + i + j);
            cell.setAttribute("value", "[" + i + "," + j + "]");
            if(clickable) {
                cell.onclick = clickMe;
            }
            cell.innerText = "X";
            row.appendChild(cell);
        }
    }
}

function clickMe() {
    alert(this.getAttribute("value"));
}
