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

    for (let i = 1; i < 9; i++) {
        figuresOnBoard[i] = []
        for (let j = 1; j < 9; j++) {
            figuresOnBoard[i][j] = document.getElementById("A" + i.toString() + j.toString()).innerHTML;
        }
    }

    boardId = bId;
    boardIdAdditional = bIdA;

    // console.log("Testuje: " + boardId);
    // console.log("testuje 2: " + boardIdAdditional);
    // document.getElementById(boardId).innerHTML = boardId;
    // document.getElementById(boardIdAdditional).innerHTML = boardIdAdditional;
}

var figureNameOld = ""; //nazwa bierki po 1 kliknieciu
var coordinateOld = "";
var figureNameNew = "";
var coordinateNew = "";
var boardName = "";
var moveType = "";
var gameResult = "";
var color;
var colorSecond;
// roszada
var castling = [true, true, true];
var castlingTwo = [true, true, true];

//bicie w przelocie
var enPassantCord = "";
var enPassantCordSecond = "";

var figuresOnBoard = [];

//promocja pionka - nazwa
var promoFigure = "";
var promoFigureSecond = "";

// czas
var internalFirst = null;
var internalSecond = null;
var internalThird = null
var internalFourth = null;
var interval = 1000;

var oldInsideStyle;//
var oldInside;


function runMe(position) {

    if (gameType === "4") {
        var outside = document.getElementById(boardId);
        var inside = document.getElementById(position);

        //tu jest sprawdzane czy kliknieto na odpowiednia szachownice
        //sprawdzane czy kliknieto odpowiedni kolor przypisany do gracza

        if (gameResult !== "CHECKMATE") {
            if (figureNameOld === "" 
            && outside.contains(inside) 
            && checkColor(inside.innerHTML.toString()) === color
            && color === nextMoveColor) {// && color === nextMoveColor//po kliknieciu 1
                figureNameOld = document.getElementById(position).innerHTML;//pobiera nazwe figury
                coordinateOld = position.substring(1);//usuwa 1 litere A lub B i pobiera koordynaty
                boardName = position[0];
                moveType = "BASIC";

                //zmiana backgroundu po kliknieciu
                oldInsideStyle = inside.style.backgroundColor;
                oldInside = inside;
                inside.style.background = "#FF8C00";

            } else if (figureNameOld !== "" 
            && outside.contains(inside)
            && color === nextMoveColor) {//po kliknieciu 2 && color === nextMoveColor
                figureNameNew = document.getElementById(position).innerHTML;//pobiera nazwe figury
                coordinateNew = position.substring(1); //usuwa 1 litere A lub B i pobiera koordynaty
                makeAMove(boardId, promoFigure);

                boardName = "";

                //odmiana backgroundu
                oldInside.style.background = oldInsideStyle;
            }
        } else {
            console.log("koniec gry");
        }
    }

    if (gameType === "2") {
        var outside = document.getElementById(boardId);
        var outsideTwo = document.getElementById(boardIdSecond);

        var inside = document.getElementById(position);

        //tu jest sprawdzane czy kliknieto na odpowiednia szachownice
        //sprawdzane czy kliknieto odpowiedni kolor przypisany do gracza

        if (gameResult !== "CHECKMATE") {
            //do 1 planszy
            if (figureNameOld === "" &&
              outside.contains(inside) &&
              checkColor(inside.innerHTML.toString()) === color 
              && color === nextMoveColor) {//po kliknieciu 1 && color === nextMoveColor
                figureNameOld = document.getElementById(position).innerHTML;//pobiera nazwe figury
                coordinateOld = position.substring(1);//usuwa 1 litere A lub B i pobiera koordynaty
                boardName = position[0];
                moveType = "BASIC";

                //zmiana backgroundu po kliknieciu
                oldInsideStyle = inside.style.backgroundColor;
                oldInside = inside;
                inside.style.background = "#FF8C00";
            } else if (figureNameOld !== "" 
            && outside.contains(inside) 
            && boardName === "A" 
            && coordinateOld !== position.substring(1)
            && color === nextMoveColor) {//po kliknieciu 2 && color === nextMoveColor
                //nie mozna kliknac 2 raz na to samo pole todo
                figureNameNew = document.getElementById(position).innerHTML;//pobiera nazwe figury
                coordinateNew = position.substring(1); //usuwa 1 litere A lub B i pobiera koordynaty

                //odmiana backgroundu
                oldInside.style.background = oldInsideStyle;

                makeAMove(boardId, promoFigure);

                // boardName = "";
                // oldInside = "";
                // oldInsideStyle = "";
                console.log("tu");
            }

            //do 2 planszy
            if (figureNameOld === "" 
            && outsideTwo.contains(inside) 
            && checkColor(inside.innerHTML.toString()) === colorSecond
            && colorSecond === nextMoveColorSecond) {//&& colorSecond === nextMoveColorSecond
                figureNameOld = document.getElementById(position).innerHTML;//pobiera nazwe figury
                coordinateOld = position.substring(1);//usuwa 1 litere A lub B i pobiera koordynaty
                boardName = position[0];
                moveType = "BASIC";

                //zmiana backgroundu po kliknieciu
                oldInsideStyle = inside.style.backgroundColor;
                oldInside = inside;
                inside.style.background = "#FF8C00";
            } else if (figureNameOld !== "" 
            && outsideTwo.contains(inside) 
            && boardName === "B"
            && coordinateOld !== position.substring(1)
            && colorSecond === nextMoveColorSecond) {//&& colorSecond === nextMoveColorSecond
                figureNameNew = document.getElementById(position).innerHTML;//pobiera nazwe figury
                coordinateNew = position.substring(1); //usuwa 1 litere A lub B i pobiera koordynaty

                //odmiana backgroundu
                oldInside.style.background = oldInsideStyle;

                makeAMove(boardIdSecond, promoFigureSecond);

                boardName = "";
                oldInside = "";
                oldInsideStyle = "";

                console.log("tu");
            }
        } else {
            console.log("koniec gry");
        }
    }
}

function runMeTwo(position) {
    if (gameType === "4") {
        var outside = document.getElementById(boardId);
        var inside = document.getElementById(position);

        if(color===nextMoveColor){
        if (gameResult !== "CHECKMATE") {
            if (figureNameOld === "" && outside.contains(inside) 
            && checkColor(inside.innerHTML.toString()) === color) {//po kliknieciu 1
                figureNameOld = document.getElementById(position).innerHTML;//pobiera nazwe figury
                coordinateOld = position.substring(2);//usuwa 1 litere A lub B i pobiera koordynaty
                boardName = position[0];
                moveType = "RESERVE";

                //zmiana backgroundu po kliknieciu
                oldInsideStyle = inside.style.backgroundColor;
                oldInside = inside;
                inside.style.background = "#FF8C00";
            }
        } else {
            console.log("koniec gry");
        }
        }
        else {
            console.log("to nie twoja kolej na ruch");
         }
    }

    if (gameType === "2") {
        

        var outside = document.getElementById(boardId);
        var outsideTwo = document.getElementById(boardIdSecond);

        var inside = document.getElementById(position);


        if (gameResult !== "CHECKMATE") {
            if (figureNameOld === "" 
            && outside.contains(inside) 
            && checkColor(inside.innerHTML.toString()) === color
            && color === nextMoveColor) {//po kliknieciu 1
                figureNameOld = document.getElementById(position).innerHTML;//pobiera nazwe figury
                coordinateOld = position.substring(2);//usuwa 1 litere A lub B i pobiera koordynaty
                boardName = position[0];
                moveType = "RESERVE";
                console.log("dolozenie");

                 //zmiana backgroundu po kliknieciu
                 oldInsideStyle = inside.style.backgroundColor;
                 oldInside = inside;
                 inside.style.background = "#FF8C00";
            }
        } else {
            console.log("koniec gry");
        }

        if (gameResult !== "CHECKMATE") {
            if (figureNameOld === "" 
            && outsideTwo.contains(inside) 
            && checkColor(inside.innerHTML.toString()) === colorSecond
            && colorSecond === nextMoveColorSecond) {//po kliknieciu 1
                figureNameOld = document.getElementById(position).innerHTML;//pobiera nazwe figury
                coordinateOld = position.substring(2);//usuwa 1 litere A lub B i pobiera koordynaty
                boardName = position[0];
                moveType = "RESERVE";
                console.log("dolozenie");

                 //zmiana backgroundu po kliknieciu
                 oldInsideStyle = inside.style.backgroundColor;
                 oldInside = inside;
                 inside.style.background = "#FF8C00";
            }
        } else {
            console.log("koniec gry");
        }

    }
}

function checkColor(name) {
    if (name === "♜" || name === "♞" || name === "♝" ||
        name === "♛" || name === "♚" || name === "♟") {
        return "BLACK";
    } else if (name === "♖" || name === "♘" || name === "♗" ||
        name === "♕" || name === "♔" || name === "♙") {
        return "WHITE";
    }

}

function makeAMove(bId, pf) {
    // var playerId = document.getElementById("playerId").innerHTML;

    var figuresOnBoard = [];
    for (let i = 1; i < 9; i++) {
        figuresOnBoard[i] = []
        for (let j = 1; j < 9; j++) {
            figuresOnBoard[i][j] = document.getElementById(boardName + i.toString() + j.toString()).innerHTML;
        }
    }

    if (gameType === "4") {
        $.ajax({
            url: url + "/game/gameplay",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "type": "gameplay",
                "gameId": gameId,
                "boardId": bId,
                "playerId": playerId,
                "coordinateOld": coordinateOld,
                "figureNameOld": figureNameOld,
                "coordinateNew": coordinateNew,
                "figureNameNew": figureNameNew,
                "figuresOnBoard": figuresOnBoard,
                "boardName": boardName,
                "nextMoveColor": nextMoveColor,//color
                "moveType": moveType,
                "team": team,
                "castling": castling,
                "castlingMove": false,
                "enPassantCord": enPassantCord,
                "enPassantMove": false,
                "promoFigure": pf,
                "gameResult": gameResult,
                "algebraicNotationFirst": algebraicNotationFirst,
                "algebraicNotationSecond": algebraicNotationSecond
            }),
            success: function (data) {
                //            gameOn = false;
                // displayResponse(data);
                // playerIdMove = data.playerIdMove;

                    boardName = "";
                    oldInside = "";
                    oldInsideStyle = "";

                    figureNameOld = "";
                    positionOld = "";
                    figureNameNew = "";
                    positionNew = "";
                    moveType = "";

                    if(data.boardId == boardId) {
                        console.log("dla 1 planszy " + castling);
                        castling = data.castling;
                    }
                    
                    if(data.boardId == boardIdSecond) {
                        console.log("castling two: " +castlingTwo);
                        castlingTwo = data.castling;
                    }
            },
            error: function (error) {
                console.log(error);
            }
        })
    }

    if (gameType === "2") {

        if(bId == boardId){
            $.ajax({
                url: url + "/game/gameplay",
                type: 'POST',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify({
                    "type": "gameplay",
                    "gameId": gameId,
                    "boardId": bId,
                    "playerId": playerId,
                    "coordinateOld": coordinateOld,
                    "figureNameOld": figureNameOld,
                    "coordinateNew": coordinateNew,
                    "figureNameNew": figureNameNew,
                    "figuresOnBoard": figuresOnBoard,
                    "boardName": boardName,
                    "nextMoveColor": nextMoveColor,
                    "moveType": moveType,
                    "team": team,
                    "castling": castling,
                    "castlingMove": false,
                    "enPassantCord": enPassantCord,
                    "enPassantMove": false,
                    "promoFigure": pf,
                    "gameResult": gameResult,
                    "algebraicNotationFirst": algebraicNotationFirst,
                    "algebraicNotationSecond": algebraicNotationSecond
                }),
                success: function (data) {
                    boardName = "";
                    oldInside = "";
                    oldInsideStyle = "";

                    figureNameOld = "";
                    positionOld = "";
                    figureNameNew = "";
                    positionNew = "";
                    moveType = "";

                    if(data.boardId == boardId) {
                        console.log("dla 1 planszy " + castling);
                        castling = data.castling;
                    }
                    
                    if(data.boardId == boardIdSecond) {
                        console.log("castling two: " +castlingTwo);
                        castlingTwo = data.castling;
                    }
                },
                error: function (error) {
                    console.log(error);
                }
            })
        }

        if(bId == boardIdSecond) {
            $.ajax({
                url: url + "/game/gameplay",
                type: 'POST',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify({
                    "type": "gameplay",
                    "gameId": gameId,
                    "boardId": bId,
                    "playerId": playerId,
                    "coordinateOld": coordinateOld,
                    "figureNameOld": figureNameOld,
                    "coordinateNew": coordinateNew,
                    "figureNameNew": figureNameNew,
                    "figuresOnBoard": figuresOnBoard,
                    "boardName": boardName,
                    "nextMoveColor": nextMoveColorSecond,
                    "moveType": moveType,
                    "team": team,
                    "castling": castlingTwo,
                    "castlingMove": false,
                    "enPassantCord": enPassantCordSecond,
                    "enPassantMove": false,
                    "promoFigure": pf,
                    "gameResult": gameResult,
                    "algebraicNotationFirst": algebraicNotationSecond,
                    "algebraicNotationSecond": algebraicNotationFirst
                }),
                success: function (data) {
                    boardName = "";
                    oldInside = "";
                    oldInsideStyle = "";

                    figureNameOld = "";
                    positionOld = "";
                    figureNameNew = "";
                    positionNew = "";
                    moveType = "";

                    if(data.boardId == boardId) {
                        console.log("dla 1 planszy " + castling);
                        castling = data.castling;
                    }
                    
                    if(data.boardId == boardIdSecond) {
                        console.log("castling two: " +castlingTwo);
                        castlingTwo = data.castling;
                    }
                },
                error: function (error) {
                    console.log(error);
                }
            })
        }
    }
}

function displayResponseBasic(data) {

    //sprawdzanie czy ruch jest bijacy i odpowiednie ustawienie zbitego pionki na dodatkowym polu

    if (data.boardId == boardId && data.moveStatus === "OK") {
        var help = "A";
        var helpTwo = "B";
        var field = "";
        var figure = document.getElementById(help + data.coordinateNew).innerHTML;
        var figureOld = document.getElementById(help + data.coordinateOld).innerHTML;
        var figureTwo = "";

        // console.log("kord " + data.enPassantCord);
        //dla bialego bicie w przelocie koordynaty do przeniesienia pionka na 2 szachownice
        if (data.enPassantMove == true && checkColor(data.figureNameNew) === "BLACK") {//kolor przeciwny
            console.log(data.coordinateNew[0] + " " + (parseInt(data.coordinateNew[1]) + 1))
            figureTwo = document.getElementById(help +
                data.coordinateNew[0] +
                (parseInt(data.coordinateNew[1]) + 1)).innerHTML;
        }

        if (data.enPassantMove == true && checkColor(data.figureNameNew) === "WHITE") {
            console.log(data.coordinateNew[0] + " " + (parseInt(data.coordinateNew[1]) - 1))
            figureTwo = document.getElementById(help +
                data.coordinateNew[0] +
                (parseInt(data.coordinateNew[1]) - 1)).innerHTML;
        }

        // console.log(document.getElementById(help + data.coordinateNew).innerHTML);

        if (figure !== "" && data.castlingMove == false) {
            console.log("jest bicie");
            // console.log(helpTwo);
            // console.log(checkColor(figure).charAt(0));
            // console.log(document.getElementById(helpTwo + checkColor(figure).charAt(0) + "0" + "0").innerHTML==="");

            for (var i = 15; i >= 0; i--) {

                if (i > 9) {
                    if (document.getElementById(helpTwo + checkColor(figure).charAt(0) + i.toString()).innerHTML === "") {
                        field = i.toString();
                    }
                }

                if (i < 10) {
                    if (document.getElementById(helpTwo + checkColor(figure).charAt(0) + "0" + i.toString()).innerHTML === "") {
                        field = "0" + i.toString();
                    }
                }
            }

            // console.log(helpTwo + checkColor(figure).charAt(0) + field);
            document.getElementById(helpTwo + checkColor(figure).charAt(0) + field).innerHTML = figure;
        }

        if (data.enPassantMove == true && data.castlingMove == false && figureTwo !== "") {
            console.log("bicie w przelocie");

            for (var i = 15; i >= 0; i--) {

                if (i > 9) {
                    if (document.getElementById(helpTwo + checkColor(figureTwo).charAt(0) + i.toString()).innerHTML === "") {
                        field = i.toString();
                    }
                }

                if (i < 10) {
                    if (document.getElementById(helpTwo + checkColor(figureTwo).charAt(0) + "0" + i.toString()).innerHTML === "") {
                        field = "0" + i.toString();
                    }
                }
            }

            // console.log(helpTwo + checkColor(figure).charAt(0) + field);
            document.getElementById(helpTwo + checkColor(figureTwo).charAt(0) + field).innerHTML = figureTwo;
        }
    }

    if (data.boardId == boardIdAdditional && data.moveStatus === "OK") {
        var help = "B";
        var helpTwo = "A";
        var figure = document.getElementById(help + data.coordinateNew).innerHTML;
        var figureTwo;

        // console.log(document.getElementById(help + data.coordinateNew).innerHTML);
        // console.log("kord " + data.enPassantCord);
        if (data.enPassantMove == true && checkColor(data.figureNameNew) === "BLACK") {//kolor przeciwny
            console.log(data.coordinateNew[0] + " " + (parseInt(data.coordinateNew[1]) + 1))
            figureTwo = document.getElementById(help +
                data.coordinateNew[0] +
                (parseInt(data.coordinateNew[1]) + 1)).innerHTML;
        }

        if (data.enPassantMove == true && checkColor(data.figureNameNew) === "WHITE") {
            console.log(data.coordinateNew[0] + " " + (parseInt(data.coordinateNew[1]) - 1))
            figureTwo = document.getElementById(help +
                data.coordinateNew[0] +
                (parseInt(data.coordinateNew[1]) - 1)).innerHTML;
        }

        if (figure !== "" && data.castlingMove == false) {
            //console.log("jest bicie");
            // console.log(helpTwo);
            // console.log(checkColor(figure).charAt(0));
            // console.log(document.getElementById(helpTwo + checkColor(figure).charAt(0) + "0" + "0").innerHTML==="");

            for (var i = 15; i >= 0; i--) {

                if (i > 9) {
                    if (document.getElementById(helpTwo + checkColor(figure).charAt(0) + i.toString()).innerHTML === "") {
                        field = i.toString();
                    }
                }

                if (i < 10) {
                    if (document.getElementById(helpTwo + checkColor(figure).charAt(0) + "0" + i.toString()).innerHTML === "") {
                        field = "0" + i.toString();
                    }
                }
            }

            // console.log(helpTwo + checkColor(figure).charAt(0) + field);
            document.getElementById(helpTwo + checkColor(figure).charAt(0) + field).innerHTML = figure;
        }

        if (data.enPassantMove == true && data.castlingMove == false && figureTwo !== "") {
            console.log("bicie w przelocie");

            for (var i = 15; i >= 0; i--) {

                if (i > 9) {
                    if (document.getElementById(helpTwo + checkColor(figureTwo).charAt(0) + i.toString()).innerHTML === "") {
                        field = i.toString();
                    }
                }

                if (i < 10) {
                    if (document.getElementById(helpTwo + checkColor(figureTwo).charAt(0) + "0" + i.toString()).innerHTML === "") {
                        field = "0" + i.toString();
                    }
                }
            }

            // console.log(helpTwo + checkColor(figure).charAt(0) + field);
            document.getElementById(helpTwo + checkColor(figureTwo).charAt(0) + field).innerHTML = figureTwo;
        }

    }


    // ustawianie szachownicy
    if (data.boardId == boardId && data.moveStatus === "OK") {
        //console.log("update pierwszej szachownicy");
        var help = "A";
        document.getElementById(help + data.coordinateNew).innerHTML = data.figureNameNew;
        document.getElementById(help + data.coordinateOld).innerHTML = data.figureNameOld;

        // console.log(help + data.coordinateNew);
        // console.log(data.boardName + data.coordinateOld);

        //prawo castling biale
        if (data.castlingMove == true && checkColor(data.figureNameNew) === "WHITE") {
            if (data.coordinateOld === "61" && data.coordinateNew === "71") {
                document.getElementById(help + "51").innerHTML = "";
                document.getElementById(help + "81").innerHTML = "";
            }
        }

        //lewo castling biale
        if (data.castlingMove == true && checkColor(data.figureNameNew) === "WHITE") {
            if (data.coordinateOld === "41" && data.coordinateNew === "31") {
                document.getElementById(help + "51").innerHTML = "";
                document.getElementById(help + "11").innerHTML = "";
            }
        }

        //prawo castling czarne
        if (data.castlingMove == true && checkColor(data.figureNameNew) === "BLACK") {
            if (data.coordinateOld === "68" && data.coordinateNew === "78") {
                document.getElementById(help + "58").innerHTML = "";
                document.getElementById(help + "88").innerHTML = "";
            }
        }

        //lewo castling czarne
        if (data.castlingMove == true && checkColor(data.figureNameNew) === "BLACK") {
            if (data.coordinateOld === "48" && data.coordinateNew === "38") {
                document.getElementById(help + "58").innerHTML = "";
                document.getElementById(help + "18").innerHTML = "";
            }
        }

        if (data.enPassantMove == true && checkColor(data.figureNameNew) === "WHITE") {
            document.getElementById(help + (parseInt(data.coordinateNew) - 1)).innerHTML = "";
        }

        if (data.enPassantMove == true && checkColor(data.figureNameNew) === "BLACK") {
            document.getElementById(help + (parseInt(data.coordinateNew) + 1)).innerHTML = "";
        }

        //ustawianie czasu

        setTimeForBoardA(data);

        // clearInterval(internalFirst);
    }

    if (data.boardId == boardIdAdditional && data.moveStatus === "OK") {
        //console.log("update drugiej szachownicy");
        var help = "B";
        document.getElementById(help + data.coordinateNew).innerHTML = data.figureNameNew;
        document.getElementById(help + data.coordinateOld).innerHTML = data.figureNameOld;

        // console.log(help + data.coordinateNew);
        // console.log(data.boardName + data.coordinateOld);

        //prawo castling biale
        if (data.castlingMove == true && checkColor(data.figureNameNew) === "WHITE") {
            if (data.coordinateOld === "61" && data.coordinateNew === "71") {
                document.getElementById(help + "51").innerHTML = "";
                document.getElementById(help + "81").innerHTML = "";
            }
        }

        //lewo castling biale
        if (data.castlingMove == true && checkColor(data.figureNameNew) === "WHITE") {
            if (data.coordinateOld === "41" && data.coordinateNew === "31") {
                document.getElementById(help + "51").innerHTML = "";
                document.getElementById(help + "11").innerHTML = "";
            }
        }

        //prawo castling czarne
        if (data.castlingMove == true && checkColor(data.figureNameNew) === "BLACK") {
            if (data.coordinateOld === "68" && data.coordinateNew === "78") {
                document.getElementById(help + "58").innerHTML = "";
                document.getElementById(help + "88").innerHTML = "";
            }
        }

        //lewo castling czarne
        if (data.castlingMove == true && checkColor(data.figureNameNew) === "BLACK") {
            if (data.coordinateOld === "48" && data.coordinateNew === "38") {
                document.getElementById(help + "58").innerHTML = "";
                document.getElementById(help + "18").innerHTML = "";
            }
        }

        if (data.enPassantMove == true && checkColor(data.figureNameNew) === "WHITE") {
            document.getElementById(help + (parseInt(data.coordinateNew) - 1)).innerHTML = "";
        }

        if (data.enPassantMove == true && checkColor(data.figureNameNew) === "BLACK") {
            console.log(help + (data.coordinateNew + 1));
            document.getElementById(help + (parseInt(data.coordinateNew) + 1)).innerHTML = "";
        }

        //ustawianie czasu

        setTimeForBoardB(data);

        // clearInterval(internalFirst);

    }


    // figureNameOld = "";
    // positionOld = "";
    // figureNameNew = "";
    // positionNew = "";
    // boardName = "";
    // moveType = "";
}

function displayResponseReserve(data) {


    // ustawianie szachownicy
    if (data.boardId == boardId && data.moveStatus === "OK") {
        var help = "A";
        document.getElementById(help + data.coordinateNew).innerHTML = data.figureNameNew;
        document.getElementById(help + checkColor(data.figureNameNew)[0] + data.coordinateOld).innerHTML = data.figureNameOld;

        setTimeForBoardA(data);
        // console.log(help + data.coordinateNew);
        // console.log(help + checkColor(data.figureNameNew)[0] + data.coordinateOld);
    }

    if (data.boardId == boardIdAdditional && data.moveStatus === "OK") {
        var help = "B";
        document.getElementById(help + data.coordinateNew).innerHTML = data.figureNameNew;
        document.getElementById(help + checkColor(data.figureNameNew)[0] + data.coordinateOld).innerHTML = data.figureNameOld;

        setTimeForBoardB(data);
        // console.log(help + data.coordinateNew);
        // console.log(help + checkColor(data.figureNameNew)[0] + data.coordinateOld);
    }


    // figureNameOld = "";
    // positionOld = "";
    // figureNameNew = "";
    // positionNew = "";
    // boardName = "";
    // moveType = "";
}

function getPromoFigures(name) {

    document.getElementById("promoQueen").style.background = "#A0522D";
    document.getElementById("promoBishop").style.background = "#eee";
    document.getElementById("promoKnight").style.background = "#A0522D";
    document.getElementById("promoRook").style.background = "#eee";

    promoFigure = name.innerHTML;
    name.style.backgroundColor = "#FF8C00";
}

function getPromoFiguresSecond(name) {

    document.getElementById("promoQueenSecond").style.background = "#eee";
    document.getElementById("promoBishopSecond").style.background = "#A0522D";
    document.getElementById("promoKnightSecond").style.background = "#eee";
    document.getElementById("promoRookSecond").style.background = "#A0522D";

    promoFigureSecond = name.innerHTML;
    name.style.backgroundColor = "#FF8C00";
}