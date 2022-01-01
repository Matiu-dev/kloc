function reset(bId) {
    document.getElementById("A8").innerHTML = "&#x265C";
    document.getElementById("B8").innerHTML = "&#x265E";
    document.getElementById("C8").innerHTML = "&#x265D";
    document.getElementById("D8").innerHTML = "&#x265B";
    document.getElementById("E8").innerHTML = "&#x265A";
    document.getElementById("F8").innerHTML = "&#x265D";
    document.getElementById("G8").innerHTML = "&#x265E";
    document.getElementById("H8").innerHTML = "&#x265C";

    document.getElementById("A7").innerHTML = "&#x265F";
    document.getElementById("B7").innerHTML = "&#x265F";
    document.getElementById("C7").innerHTML = "&#x265F";
    document.getElementById("D7").innerHTML = "&#x265F";
    document.getElementById("E7").innerHTML = "&#x265F";
    document.getElementById("F7").innerHTML = "&#x265F";
    document.getElementById("G7").innerHTML = "&#x265F";
    document.getElementById("H7").innerHTML = "&#x265F";

    document.getElementById("A2").innerHTML = "&#x2659";
    document.getElementById("B2").innerHTML = "&#x2659";
    document.getElementById("C2").innerHTML = "&#x2659";
    document.getElementById("D2").innerHTML = "&#x2659";
    document.getElementById("E2").innerHTML = "&#x2659";
    document.getElementById("F2").innerHTML = "&#x2659";
    document.getElementById("G2").innerHTML = "&#x2659";
    document.getElementById("H2").innerHTML = "&#x2659";

    document.getElementById("A1").innerHTML = "&#x2656";
    document.getElementById("B1").innerHTML = "&#x2658";
    document.getElementById("C1").innerHTML = "&#x2657";
    document.getElementById("D1").innerHTML = "&#x2655";
    document.getElementById("E1").innerHTML = "&#x2654";
    document.getElementById("F1").innerHTML = "&#x2657";
    document.getElementById("G1").innerHTML = "&#x2658";
    document.getElementById("H1").innerHTML = "&#x2656";

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
            "figureNameNew": figureNameNew
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


    if(data.coordinateNew !=="") {
        document.getElementById(data.coordinateNew).innerHTML = data.figureNameOld;
        document.getElementById(data.coordinateOld).innerHTML = data.figureNameNew;
    }

    figureNameOld="";
    positionOld="";
    figureNameNew="";
    positionNew = "";
}