var ships = [];
var ship = [];
var shipLength = 0;

window.onload = function load() {
    var board = document.getElementById("board");
    setDivsForBoard(board);
};

function setDivsForBoard(board) {
    for (var i = 0; i < 10; i++) {
        var row = document.createElement('DIV');
        row.setAttribute("class", "row");
        board.appendChild(row);

        for (var j = 0; j < 10; j++) {
            var cell = document.createElement('DIV');
            cell.setAttribute("class", "cell col-xs-1 col-xs-push-1");

            var attribute = j.toString() + "," + i.toString();
            cell.setAttribute("id", "cell" + attribute);
            cell.setAttribute("value", j + "," + i);


            cell.innerText = "_";
            row.appendChild(cell);
        }
    }
}

function refreshBoard() {
    clearBoard();
    ships.forEach(
      function (value) {
          value.forEach(function (cellName) {
              document.getElementById("cell" + cellName).style.backgroundColor = "blue";
          })
      }
    );
}

function playerOnClick() {
    this.style.backgroundColor = "yellow";
    ship[ship.length] = this.getAttribute("value");

    markAvailablePositionsForShip();


    shipLength--;
    if (shipLength === 0) {
        ships[ships.length] = ship;
        ship = [];
        refreshBoard();
    }
}

function markAvailablePositionsForShip() {
    clearBoard();
    var currentShipLength = ship.length;
    if (currentShipLength === 1) {
        markTwoSites(ship[0]);
    } else {
        markOneSite();
    }

}

function markTwoSites(position) {
    var coordinates = position.split(",");
    markAsPossiblePlacePosition("cell" + (parseInt(coordinates[0])-1) + "," + coordinates[1]);
    markAsPossiblePlacePosition("cell" + (parseInt(coordinates[0])+1) + "," + coordinates[1]);
    markAsPossiblePlacePosition("cell" + coordinates[0] + "," + (parseInt(coordinates[1])-1));
    markAsPossiblePlacePosition("cell" + coordinates[0] + "," + (parseInt(coordinates[1])+1));
}

function markOneSite() {
    var x = [];
    var y = [];

    ship.forEach(function(c) {
        var coordinates = c.split(",");
        x[x.length] = coordinates[0];
        y[y.length] = coordinates[1];
    });

    var first;
    var last;

    if (x[0] === x[1]) {
        first = x[0] + "," + (Math.min.apply(null, y) -1);
        last = x[0] + "," + (Math.max.apply(null, y) + 1);
    } else if (y[0] === y[1]) {
        first = (Math.min.apply(null, x) -1)+ "," + y[0];
        last = (Math.max.apply(null, x) +1) + "," + y[0];
    }

    markAsPossiblePlacePosition("cell" + first);
    markAsPossiblePlacePosition("cell" + last);
}

function markAsPossiblePlacePosition(position) {
    console.log(position);
    var cell = document.getElementById(position);
    cell.style.backgroundColor = "green";
    cell.onclick = playerOnClick;
}

function clearBoard() {
    var board = document.getElementById("board");
    board.childNodes.forEach(
        function(row) {
            row.childNodes.forEach(
                function(cell) {
                    cell.style.backgroundColor = "white";
                    cell.onclick = null;
                })
        });
}

function markAvailableFields() {
    var board = document.getElementById("board");
    board.childNodes.forEach(
        function(row) {
            row.childNodes.forEach(
                function(cell) {
                    cell.style.backgroundColor = "green";
                    cell.onclick = playerOnClick;
                })
        });
}

function selectShip(length) {
    shipLength = length;
    ship = [];
    markAvailableFields();
}

function resetAll() {
    clearBoard();
    ships = [];
    ship = [];
    shipLength = 0;
}

function resetCurrent() {
    refreshBoard();
    ship = [];
    shipLength = 0;
}

function generateRandom() {

}

function startGame() {

}