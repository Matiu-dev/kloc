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
var boardName = "";
var moveType = "";
var gameResult = "";
var color;
var castling = [true, true, true];
var figuresOnBoard=[];

function runMe(position){
    
    var outside  = document.getElementById(boardId);
    var inside = document.getElementById(position);

    //tu jest sprawdzane czy kliknieto na odpowiednia szachownice
    //sprawdzane czy kliknieto odpowiedni kolor przypisany do gracza

    // if(color===nextMoveColor){
    if(gameResult !== "CHECKMATE"){
        if(figureNameOld=== "" && outside.contains(inside) && checkColor(inside.innerHTML.toString())===color){//po kliknieciu 1
            figureNameOld=document.getElementById(position).innerHTML;//pobiera nazwe figury
            coordinateOld = position.substring(1);//usuwa 1 litere A lub B i pobiera koordynaty
            boardName = position[0];
            moveType = "BASIC";
        }else if(figureNameOld!=="" && outside.contains(inside)) {//po kliknieciu 2
            figureNameNew=document.getElementById(position).innerHTML;//pobiera nazwe figury
            coordinateNew = position.substring(1); //usuwa 1 litere A lub B i pobiera koordynaty
            makeAMove();
    
            boardName = "";
        }
    }else{
        console.log("koniec gry");
    }
//    }
//    else {
//        console.log("to nie twoja kolej na ruch");
//     }
}

function runMeTwo(position) {
    var outside  = document.getElementById(boardId);
    var inside = document.getElementById(position);

    // if(color===nextMoveColor){
    if(gameResult !== "CHECKMATE"){
        if(figureNameOld=== "" && outside.contains(inside) && checkColor(inside.innerHTML.toString())===color){//po kliknieciu 1
            figureNameOld=document.getElementById(position).innerHTML;//pobiera nazwe figury
            coordinateOld = position.substring(2);//usuwa 1 litere A lub B i pobiera koordynaty
            boardName = position[0];
            moveType = "RESERVE";
        }
    }else {
        console.log("koniec gry");
    }
//    }
//    else {
//        console.log("to nie twoja kolej na ruch");
//     }

    console.log(document.getElementById(position).innerHTML);
    console.log(position.substring(2));
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
            "nextMoveColor": color,
            "moveType": moveType,
            "team": team,
            "castling": castling,
            "castlingMove": false

        }),
        success: function (data) {
//            gameOn = false;
            // displayResponse(data);
            // playerIdMove = data.playerIdMove;
        },
        error: function (error) {
            console.log(error);
        }
    })
}

function displayResponseBasic(data) {

    //sprawdzanie czy ruch jest bijacy i odpowiednie ustawienie zbitego pionki na dodatkowym polu


   
    if(data.boardId == boardId && data.moveStatus === "OK") {
        var help = "A";
        var helpTwo = "B";
        var field = "";
        var figure = document.getElementById(help + data.coordinateNew).innerHTML;
        // console.log(document.getElementById(help + data.coordinateNew).innerHTML);

        if(figure !== "" && data.castlingMove == false){
            console.log("jest bicie");
            // console.log(helpTwo);
            // console.log(checkColor(figure).charAt(0));
            // console.log(document.getElementById(helpTwo + checkColor(figure).charAt(0) + "0" + "0").innerHTML==="");
            
            for(var i = 15; i >= 0; i--) {
               
                if(i > 9){
                    if(document.getElementById(helpTwo + checkColor(figure).charAt(0) + i.toString()).innerHTML===""){
                        field =i.toString();
                    }
                }

                if(i < 10){
                    if(document.getElementById(helpTwo + checkColor(figure).charAt(0) + "0" + i.toString()).innerHTML===""){
                        field = "0" + i.toString();
                    }
                }  
            }

            // console.log(helpTwo + checkColor(figure).charAt(0) + field);
            document.getElementById(helpTwo + checkColor(figure).charAt(0) + field).innerHTML = figure;
        }
        
        
    }

    if(data.boardId == boardIdAdditional && data.moveStatus === "OK") {
        var help = "B";
        var helpTwo = "A";
        var figure = document.getElementById(help + data.coordinateNew).innerHTML;
        // console.log(document.getElementById(help + data.coordinateNew).innerHTML);

        if(figure !== "" && data.castlingMove == false){
            console.log("jest bicie");
            // console.log(helpTwo);
            // console.log(checkColor(figure).charAt(0));
            // console.log(document.getElementById(helpTwo + checkColor(figure).charAt(0) + "0" + "0").innerHTML==="");
            
            for(var i = 15; i >= 0; i--) {
               
                if(i > 9){
                    if(document.getElementById(helpTwo + checkColor(figure).charAt(0) + i.toString()).innerHTML===""){
                        field =i.toString();
                    }
                }

                if(i < 10){
                    if(document.getElementById(helpTwo + checkColor(figure).charAt(0) + "0" + i.toString()).innerHTML===""){
                        field = "0" + i.toString();
                    }
                }  
            }

            // console.log(helpTwo + checkColor(figure).charAt(0) + field);
            document.getElementById(helpTwo + checkColor(figure).charAt(0) + field).innerHTML = figure;
        }
        
    }
    

    // ustawianie szachownicy
    if(data.boardId == boardId && data.moveStatus === "OK"){
        var help = "A";
        document.getElementById(help + data.coordinateNew).innerHTML = data.figureNameNew;
        document.getElementById(data.boardName + data.coordinateOld).innerHTML = data.figureNameOld;

        // console.log(help + data.coordinateNew);
        // console.log(data.boardName + data.coordinateOld);

        //prawo castling biale
        if(data.castlingMove == true && checkColor(data.figureNameNew) === "WHITE"){
            if(data.coordinateOld === "61" && data.coordinateNew === "71") {
                document.getElementById(help+"51").innerHTML = "";
                document.getElementById(help+"81").innerHTML = "";
            }
        }

        //lewo castling biale
        if(data.castlingMove == true && checkColor(data.figureNameNew) === "WHITE"){
            if(data.coordinateOld === "41" && data.coordinateNew === "31") {
                document.getElementById(help+"51").innerHTML = "";
                document.getElementById(help+"11").innerHTML = "";
            }
        }

        //prawo castling czarne
        if(data.castlingMove == true && checkColor(data.figureNameNew) === "BLACK"){
            if(data.coordinateOld === "68" && data.coordinateNew === "78") {
                document.getElementById(help+"58").innerHTML = "";
                document.getElementById(help+"88").innerHTML = "";
            }
        }

        //lewo castling czarne
        if(data.castlingMove == true && checkColor(data.figureNameNew) === "BLACK"){
            if(data.coordinateOld === "48" && data.coordinateNew === "38") {
                document.getElementById(help+"58").innerHTML = "";
                document.getElementById(help+"18").innerHTML = "";
            }
        }
    }

    if(data.boardId == boardIdAdditional && data.moveStatus === "OK") {
        var help = "B";
        document.getElementById(help + data.coordinateNew).innerHTML = data.figureNameNew;
        document.getElementById(help + data.coordinateOld).innerHTML = data.figureNameOld;

        // console.log(help + data.coordinateNew);
        // console.log(data.boardName + data.coordinateOld);

        //prawo castling biale
        if(data.castlingMove == true && checkColor(data.figureNameNew) === "WHITE"){
            if(data.coordinateOld === "61" && data.coordinateNew === "71") {
                document.getElementById(help+"51").innerHTML = "";
                document.getElementById(help+"81").innerHTML = "";
            }
        }

        //lewo castling biale
        if(data.castlingMove == true && checkColor(data.figureNameNew) === "WHITE"){
            if(data.coordinateOld === "41" && data.coordinateNew === "31") {
                document.getElementById(help+"51").innerHTML = "";
                document.getElementById(help+"11").innerHTML = "";
            }
        }

        //prawo castling czarne
        if(data.castlingMove == true && checkColor(data.figureNameNew) === "BLACK"){
            if(data.coordinateOld === "68" && data.coordinateNew === "78") {
                document.getElementById(help+"58").innerHTML = "";
                document.getElementById(help+"88").innerHTML = "";
            }
        }

        //lewo castling czarne
        if(data.castlingMove == true && checkColor(data.figureNameNew) === "BLACK"){
            if(data.coordinateOld === "48" && data.coordinateNew === "38") {
                document.getElementById(help+"58").innerHTML = "";
                document.getElementById(help+"18").innerHTML = "";
            }
        }
    }

    
    figureNameOld="";
    positionOld="";
    figureNameNew="";
    positionNew = "";
    boardName = "";
    moveType = "";
}

function displayResponseReserve(data) {


    // ustawianie szachownicy
    if(data.boardId == boardId && data.moveStatus === "OK"){
        var help = "A";
        document.getElementById(help + data.coordinateNew).innerHTML = data.figureNameNew;
        document.getElementById(help + checkColor(data.figureNameNew)[0] + data.coordinateOld).innerHTML = data.figureNameOld;

        // console.log(help + data.coordinateNew);
        // console.log(help + checkColor(data.figureNameNew)[0] + data.coordinateOld);
    }

    if(data.boardId == boardIdAdditional && data.moveStatus === "OK") {
        var help = "B";
        document.getElementById(help + data.coordinateNew).innerHTML = data.figureNameNew;
        document.getElementById(help + checkColor(data.figureNameNew)[0] + data.coordinateOld).innerHTML = data.figureNameOld;

        // console.log(help + data.coordinateNew);
        // console.log(help + checkColor(data.figureNameNew)[0] + data.coordinateOld);
    }


    figureNameOld="";
    positionOld="";
    figureNameNew="";
    positionNew = "";
    boardName = "";
    moveType = "";
}