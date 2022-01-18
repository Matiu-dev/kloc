function reset(bId) {
    document.getElementById("18").innerHTML = "&#x265C";
    document.getElementById("28").innerHTML = "&#x265E";
    document.getElementById("38").innerHTML = "&#x265D";
    document.getElementById("48").innerHTML = "&#x265B";
    document.getElementById("58").innerHTML = "&#x265A";
    document.getElementById("68").innerHTML = "&#x265D";
    document.getElementById("78").innerHTML = "&#x265E";
    document.getElementById("88").innerHTML = "&#x265C";

    document.getElementById("17").innerHTML = "&#x265F";
    document.getElementById("27").innerHTML = "&#x265F";
    document.getElementById("37").innerHTML = "&#x265F";
    document.getElementById("47").innerHTML = "&#x265F";
    document.getElementById("57").innerHTML = "&#x265F";
    document.getElementById("67").innerHTML = "&#x265F";
    document.getElementById("77").innerHTML = "&#x265F";
    document.getElementById("87").innerHTML = "&#x265F";

    document.getElementById("12").innerHTML = "&#x2659";
    document.getElementById("22").innerHTML = "&#x2659";
    document.getElementById("32").innerHTML = "&#x2659";
    document.getElementById("42").innerHTML = "&#x2659";
    document.getElementById("52").innerHTML = "&#x2659";
    document.getElementById("62").innerHTML = "&#x2659";
    document.getElementById("72").innerHTML = "&#x2659";
    document.getElementById("82").innerHTML = "&#x2659";

    document.getElementById("11").innerHTML = "&#x2656";
    document.getElementById("21").innerHTML = "&#x2658";
    document.getElementById("31").innerHTML = "&#x2657";
    document.getElementById("41").innerHTML = "&#x2655";
    document.getElementById("51").innerHTML = "&#x2654";
    document.getElementById("61").innerHTML = "&#x2657";
    document.getElementById("71").innerHTML = "&#x2658";
    document.getElementById("81").innerHTML = "&#x2656";

    for(let i = 1; i < 9; i++) {
            figuresOnBoard[i]=[]
            for(let j = 1; j < 9; j++) {
                figuresOnBoard[i][j]=document.getElementById(i.toString()+j.toString()).innerHTML;
            }
        }

    boardId = bId;
}

var figureNameOld="";
var coordinateOld="";
var figureNameNew="";
var coordinateNew = "";

function runMe(position){
    if(figureNameOld=== "" ){//po kliknieciu 1
        figureNameOld=document.getElementById(position).innerHTML;
        coordinateOld = position;
    }else if(figureNameOld!=="") {//po kliknieciu 2
        figureNameNew=document.getElementById(position).innerHTML;
        coordinateNew = position;
        makeAMove();
     }
}

function makeAMove() {
    let id = document.getElementById("playerId").innerHTML;

    var figuresOnBoard = [];
    for(let i = 1; i < 9; i++) {
        figuresOnBoard[i]=[]
        for(let j = 1; j < 9; j++) {
            figuresOnBoard[i][j]=document.getElementById(i.toString()+j.toString()).innerHTML;
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
            "figuresOnBoard": figuresOnBoard
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
//        console.log("old " + data.figureNameOld);
//        console.log("new " + data.figureNameNew);

        document.getElementById(data.coordinateNew).innerHTML = data.figureNameNew;
        document.getElementById(data.coordinateOld).innerHTML = data.figureNameOld;
    }

    figureNameOld="";
    positionOld="";
    figureNameNew="";
    positionNew = "";
}