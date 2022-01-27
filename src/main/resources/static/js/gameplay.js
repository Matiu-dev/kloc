function reset(bId) {
    document.getElementById("A18").innerHTML = "&#x265C";
    document.getElementById("A28").innerHTML = "&#x265E";
    document.getElementById("A38").innerHTML = "&#x265D";
    document.getElementById("A48").innerHTML = "&#x265B";
    document.getElementById("A58").innerHTML = "&#x265A";
    document.getElementById("A68").innerHTML = "&#x265D";
    document.getElementById("A78").innerHTML = "&#x265E";
    document.getElementById("A88").innerHTML = "&#x265C";

    document.getElementById("A17").innerHTML = "&#x265F";
    document.getElementById("A27").innerHTML = "&#x265F";
    document.getElementById("A37").innerHTML = "&#x265F";
    document.getElementById("A47").innerHTML = "&#x265F";
    document.getElementById("A57").innerHTML = "&#x265F";
    document.getElementById("A67").innerHTML = "&#x265F";
    document.getElementById("A77").innerHTML = "&#x265F";
    document.getElementById("A87").innerHTML = "&#x265F";

    document.getElementById("A12").innerHTML = "&#x2659";
    document.getElementById("A22").innerHTML = "&#x2659";
    document.getElementById("A32").innerHTML = "&#x2659";
    document.getElementById("A42").innerHTML = "&#x2659";
    document.getElementById("A52").innerHTML = "&#x2659";
    document.getElementById("A62").innerHTML = "&#x2659";
    document.getElementById("A72").innerHTML = "&#x2659";
    document.getElementById("A82").innerHTML = "&#x2659";

    document.getElementById("A11").innerHTML = "&#x2656";
    document.getElementById("A21").innerHTML = "&#x2658";
    document.getElementById("A31").innerHTML = "&#x2657";
    document.getElementById("A41").innerHTML = "&#x2655";
    document.getElementById("A51").innerHTML = "&#x2654";
    document.getElementById("A61").innerHTML = "&#x2657";
    document.getElementById("A71").innerHTML = "&#x2658";
    document.getElementById("A81").innerHTML = "&#x2656";

    for(let i = 1; i < 9; i++) {
            figuresOnBoard[i]=[]
            for(let j = 1; j < 9; j++) {
                figuresOnBoard[i][j]=document.getElementById("A"+i.toString()+j.toString()).innerHTML;
            }
    }

    boardId = bId;
}

var figureNameOld="";
var coordinateOld="";
var figureNameNew="";
var coordinateNew = "";
var figuresOnBoard=[];
var boardName = "";

function runMe(position){
    if(figureNameOld=== "" ){//po kliknieciu 1
        figureNameOld=document.getElementById(position).innerHTML;
        coordinateOld = position.substring(1);
        boardName = position[0];
    }else if(figureNameOld!=="" && boardName === position[0]) {//po kliknieciu 2
        figureNameNew=document.getElementById(position).innerHTML;
        coordinateNew = position.substring(1);
        makeAMove();
     }
}

function makeAMove() {
    let id = document.getElementById("playerId").innerHTML;

    var figuresOnBoard = [];
    for(let i = 1; i < 9; i++) {
        figuresOnBoard[i]=[]
        for(let j = 1; j < 9; j++) {
            figuresOnBoard[i][j]=document.getElementById("A" + i.toString()+j.toString()).innerHTML;
        }
    }

    $.ajax({
        url: url + "/game/gameplay",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "boardId": boardId,
            "playerId": id,
            "coordinateOld": coordinateOld,
            "figureNameOld": figureNameOld,
            "coordinateNew": coordinateNew,
            "figureNameNew": figureNameNew,
            "figuresOnBoard": figuresOnBoard,
            "boardName": boardName
        }),
        success: function (data) {
//            gameOn = false;
            displayResponse(data);
        },
        error: function (error) {
            console.log(error);
        }
    })
}

function displayResponse(data) {

    if(data.coordinateNew !=="" && data.moveStatus === "OK") {
        document.getElementById(data.boardName +data.coordinateNew).innerHTML = data.figureNameNew;
        document.getElementById(data.boardName + data.coordinateOld).innerHTML = data.figureNameOld;
    }

    figureNameOld="";
    positionOld="";
    figureNameNew="";
    positionNew = "";
    boardName = "";
}