function reset(bId, bIdA) {
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
    boardIdAdditional = bIdA

    // console.log("Testuje: " + boardId);
    // console.log("testuje 2: " + boardIdAdditional);
    // document.getElementById(boardId).innerHTML = boardId;
    // document.getElementById(boardIdAdditional).innerHTML = boardIdAdditional;
}

var figureNameOld="";
var coordinateOld="";
var figureNameNew="";
var coordinateNew = "";
var figuresOnBoard=[];
var boardName = "";

function runMe(position){
    
    //sprawdzic czy wszyscy gracze dolaczyli do gry


    // console.log(playerIdMove);

    //do sprawdzenia czy kliknietio w odpowiednia szachownice
    var outside  = document.getElementById(boardId);
    var inside = document.getElementById(position);

    //console.log(outside.contains(inside)); sprawdza czy jest kliknieta dobra plansza

    //do sprawdzenia czy kliknieto w odpowiedni kolor
    // checkColor(inside.innerHTML.toString())===color; - po 1 kliknieciu
    //checkColor(inside.innerHTML.toString())!==color; - po 2 kliknieciu

    //sprawdza czy jest teraz ruch gracza
    // console.log(playerIdMove);
    // console.log(playerId);
    // console.log(playerIdMove.toString()===playerId.toString());


    //sprawdzenie czy kolejny ruch jest bialy
    //if(playerIdMove.toString()===playerId.toString()){

    //tu jest sprawdzane czy kliknieto na odpowiednia szachownice
    //sprawdzane czy kliknieto odpowiedni kolor przypisany do gracza
    if(color===nextMoveColor){
        if(figureNameOld=== "" && outside.contains(inside) && checkColor(inside.innerHTML.toString())===color){//po kliknieciu 1
            figureNameOld=document.getElementById(position).innerHTML;//pobiera nazwe figury
            coordinateOld = position.substring(1);//usuwa 1 litere A lub B i pobiera koordynaty
            boardName = position[0];
        }else if(figureNameOld!=="" && outside.contains(inside)) {//po kliknieciu 2
            figureNameNew=document.getElementById(position).innerHTML;//pobiera nazwe figury
            coordinateNew = position.substring(1); //usuwa 1 litere A lub B i pobiera koordynaty
            makeAMove();
    
            boardName = "";
        }
   }else {
       console.log("to nie twoja kolej na ruch");
    }
}

function checkColor(name) {
    if(name === "♜" || name === "♞" || name === "♝" ||
    name === "♛" || name === "♚" || name === "♟" ){
        return "BLACK";
    }else if(name === "♖" || name === "♘" || name === "♗" ||
    name === "♕" || name === "♔" || name === "♙" ){
        return "WHITE";
    }

}

function makeAMove() {
    // var playerId = document.getElementById("playerId").innerHTML;

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
            "gameId": gameId,
            "boardId": boardId,
            "playerId": playerId,
            "coordinateOld": coordinateOld,
            "figureNameOld": figureNameOld,
            "coordinateNew": coordinateNew,
            "figureNameNew": figureNameNew,
            "figuresOnBoard": figuresOnBoard,
            "boardName": boardName,
            "nextMoveColor": color
        }),
        success: function (data) {
//            gameOn = false;
            displayResponse(data);
            // playerIdMove = data.playerIdMove;
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