const url = 'https://klocuwb.herokuapp.com';// 'https://klocuwb.herokuapp.com''http://192.168.1.245:8080';
//loginy
let login;
//let loginTwo;
//let loginThree;
//let loginFourth;

let gameId;
let playerId;
let boardId;//twoja 1 plansza
let boardIdSecond;//twoja 2 plansza - jesli 2 graczy to to jest twoja 2 plansza

let boardIdAdditional;//dodatkowa plansza do wyswietlania wynikow

let stompClient;
let playerIdMove;

let nextMoveColor;//kolejny ruch na 1 szachownicy
let nextMoveColorSecond;//kolejny ruch na 2 szachownicy todo

let team;//druzyna A lub B
let chatId;
let submit;
let gameTime;
let AgameTimeFirst;
let AgameTimeSecond;
let BgameTimeFirst;
let BgameTimeSecond;
let additionalTime;
let gameType;

function connectToSocket(gameId) {

    console.log("connecting to the game");
    // let socket = new SockJS(url + "/gameplay");
    let socket = new SockJS(url + "/gameplay", null, {
        sessionId: function() {
            return gameId + ":" + login + ":" + team;
        }
    });
    stompClient = Stomp.over(socket);

    stompClient.connect(login, "456", function (frame) {
        // console.log("connected to the frame: " + frame);
        // console.log("board id to: " + boardId);
        // console.log("kolor to: " + color);
        // console.log("chat id to: " + chatId);


        stompClient.subscribe("/topic/game-progress/" + gameId, function (response) {
            let data = JSON.parse(response.body);
            // console.log(data);
            // console.log(AgameTimeFirst + " " + AgameTimeSecond + " " + BgameTimeFirst + " " + BgameTimeSecond);
            // console.log(data.nextMoveColor)

            // console.log(data.type === "gameplay" && data.moveStatus === "OK");

            // if(data.type === "gameplay" && data.moveStatus === "BAD") {
            //     boardName = "";
            //     oldInside = "";
            //     oldInsideStyle = "";

            //     figureNameOld = "";
            //     positionOld = "";
            //     figureNameNew = "";
            //     positionNew = "";
            //     moveType = "";
            // }

            if (data.type === "gameplay" && data.moveStatus === "OK") {
                //ustawienie nastepnego ruchu dla odpowiedniej planszy

                // console.log(data.boardId == boardId);
                // console.log(data.boardId == boardIdSecond);
                // console.log(data.nextMoveColor);

                if (data.boardId == boardId) {
                    nextMoveColor = data.nextMoveColor;
                    enPassantCord = data.enPassantCord;
                }

                if(data.boardId == boardIdSecond) {
                    nextMoveColorSecond = data.nextMoveColor;
                    enPassantCordSecond = data.enPassantCord;
                }

                // console.log("nastepny ruch: " + nextMoveColor + " " + nextMoveColorSecond);

                // if(data.boardId == boardAdditional) {
                //     nextMoveColorSe
                // }

                //do roszady
                // if (data.playerId == playerId) {
                //     // console.log("roszada");
                //     if(data.boardId == boardId) {
                //         console.log("dla 1 planszy " + castling);
                //         castling = data.castling;
                //     }
                    
                //     if(data.boardId == boardIdSecond) {
                //         castlingTwo = data.castling;
                //         console.log("castling two: " +castlingTwo);
                //     }
                // }

                //ruch na planszy
                if (data.moveType === "BASIC") {
                    displayResponseBasic(data);
                }

                //ruch z rezerwy na plansze
                if (data.moveType === "RESERVE") {
                    displayResponseReserve(data);
                }

                // console.log(castling[0] + " " + castling[1] + " " + castling[2]);

                gameResult = data.gameResult;

                if (gameResult === "CHECKMATE") {
                    clearAllIntervals();
                    alert("Zakonczenie gry z powodu szach mat.")
                }
            }

            if (data.type === "message" && data.messageStatus === "OK") {
                displayResponseMessage(data);
            }

            if (data.type === "time") {
                gameResult = data.gameResult;

                if (gameResult === "CHECKMATE") {
                    clearAllIntervals();
                    alert("Zakonczenie gry z powodu skończenia się czasu.")
                }
            }

            if (data.type === "disconnect") {
                gameResult = data.gameResult;

                if (gameResult === "CHECKMATE") {
                    clearAllIntervals();
                    alert("Zakonczenie gry z powodu wyjścia gracza z gry.")
                }
            }

            if (data.type === "connect") {
                alert("Polaczenie gracza o nicku: " + data.login);
                setLogins(data);
            }


        })

        notificateOtherPlayers();
    })

}


function create_game() {
    login = document.getElementById("login").innerHTML;//sprawdzic czy z sesji da sie to wyciagnac
    // let id = document.getElementById("playerId").innerHTML;//i to

    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        $.ajax({
            url: url + "/game/create",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                // "playerId": id,
                "login": login,
                "gameTime": gameTime,
                "additionalTime": additionalTime,
                "gameType": gameType
            }),
            success: function (data) {
                gameId = data.gameId;
                boardId = data.boardId;
                color = data.color;
                boardIdAdditional = data.boardIdAdditional;
                playerIdMove = data.playerIdMove;
                playerId = data.playerId;
                team = data.team;
                chatId = data.chatId;
                //ustawia id dla szachownic
                setBoardsId(boardId, boardIdAdditional);

                //ustawia czas
                setTime();

                //ustawia figury do awansu
                setPromoFigures(color, gameType);

                nextMoveColor = "WHITE";
                reset(boardId, boardIdAdditional);
                connectToSocket(gameId);

                if (gameType === "2") {
                    boardIdSecond = data.boardIdAdditional;
                    colorSecond = data.colorSecond;
                    nextMoveColorSecond = "WHITE";

                    alert("Stworzenie gry pomyślne. Id gry to: " + gameId +
                        " twoja szachwnica ma numer: " + boardId +
                        " twoja druga szachownica ma numer: " + boardIdSecond);
                }

                if (gameType === "4") {
                    boardIdSecond = "";

                    alert("Stworzenie gry pomyślne. Id gry to: " + gameId +
                        " twoja szachwnica ma numer: " + boardId);
                }

            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}

window.onload = function () {

    if (submit === "create") {
        create_game();
    }

    if (submit === "join") {
        connect_to_specific_game();
    }
}

window.onclose = function () {
    disconnect();
}

function connect_to_specific_game() {
    login = document.getElementById("login").innerHTML;
    // let id = document.getElementById("playerId").innerHTML;


    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        // let boardId = document.getElementById("boardId").value;
        //        console.log(boardId);
        if (gameId == null || gameId === '') {
            alert("Please enter board id");
        }
        $.ajax({
            url: url + "/game/connect",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "gameId": gameId,
                "login": login
            }),
            success: function (data) {
                if(data.gameStatus === "OPEN"){

                gameId = data.gameId;
                boardId = data.boardId;
                color = data.color;
                boardIdAdditional = data.boardIdAdditional;
                playerIdMove = data.playerIdMove;
                playerId = data.playerId;
                team = data.team;
                gameTime = data.gameTime;
                gameType = data.gameType;

                additionalTime = data.additionalTime;
                //ustawia id dla szachownic
                setBoardsId(boardId, boardIdAdditional);

                //ustawia czas
                setTime();

                //ustawia figury do awansu
                setPromoFigures(color, gameType);

                nextMoveColor = "WHITE";
                reset(boardId, boardIdAdditional);
                connectToSocket(gameId);


                if (gameType === "2") {
                    boardIdSecond = data.boardIdAdditional;
                    colorSecond = data.colorSecond;
                    nextMoveColorSecond = "WHITE";

                    alert("Pomyślnie dołaczyłes do gry. Id gry to: " + gameId +
                        " twoja szachwnica ma numer: " + boardId +
                        " twoja druga szachownica ma numer: " + boardIdSecond);
                }

                if (gameType === "4") {
                    boardIdSecond = "";

                    alert("Pomyślnie dołaczyłes do gry. Id gry to: " + gameId +
                        " twoja szachwnica ma numer: " + boardId);
                }
            }
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}

function notificateOtherPlayers() {

    console.log("notificate");

    // let id = document.getElementById("playerId").innerHTML;//i to
    login = document.getElementById("login").innerHTML;

    $.ajax({
        url: url + "/game/gameplay",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "type": "connect",
            "gameId": gameId,
            "login": login,
            // "playerId": id,
            "boardId": boardId,
            "boardIdAdditional": boardIdAdditional
        }),
        success: function (data) {

        },
        error: function (error) {
            console.log(error);
        }
    })
}

function setColorOnBoard(color) {
    document.getElementById("colorOnBoard").innerHTML = color;
}

function setBoardsId(myBoard, boardAdditional) {
    document.getElementById("board1").setAttribute('id', myBoard);
    document.getElementById("board2").setAttribute('id', boardAdditional);
    // document.getElementById("boardName1").innerHTML = myBoard;
    // document.getElementById("boardName2").innerHTML = boardAdditional;

}

function setTime() {
    gameTime = gameTime * 60;
    document.getElementById("AgameTimeFirst").innerHTML += Math.floor(gameTime / 60) + ":" + Math.floor(gameTime % 3600 % 60);
    document.getElementById("AgameTimeSecond").innerHTML += Math.floor(gameTime / 60) + ":" + Math.floor(gameTime % 3600 % 60);
    document.getElementById("BgameTimeFirst").innerHTML += Math.floor(gameTime / 60) + ":" + Math.floor(gameTime % 3600 % 60);
    document.getElementById("BgameTimeSecond").innerHTML += Math.floor(gameTime / 60) + ":" + Math.floor(gameTime % 3600 % 60);

    AgameTimeFirst = gameTime;
    BgameTimeFirst = gameTime;
    AgameTimeSecond = gameTime;
    BgameTimeSecond = gameTime;
}

function setPromoFigures(color, gameType) {

    
    if (color === "WHITE") {
        document.getElementById("promoQueen").innerHTML = "♕";
        document.getElementById("promoBishop").innerHTML = "♗";
        document.getElementById("promoKnight").innerHTML = "♘";
        document.getElementById("promoRook").innerHTML = "♖";

        promoFigure = "♕";
        // document.getElementById("promoQueen").innerHTML.style.background = "#FF8C00";
    }

    if (color === "BLACK") {
        document.getElementById("promoQueen").innerHTML = "♛";
        document.getElementById("promoBishop").innerHTML = "♝";
        document.getElementById("promoKnight").innerHTML = "♞";
        document.getElementById("promoRook").innerHTML = "♜";
        promoFigure = "♛";
    }

    document.getElementById("promoQueen").style.background = "#FF8C00";

    if(gameType === "2") {
        if (color === "BLACK") {
            document.getElementById("promoQueenSecond").innerHTML = "♕";
            document.getElementById("promoBishopSecond").innerHTML = "♗";
            document.getElementById("promoKnightSecond").innerHTML = "♘";
            document.getElementById("promoRookSecond").innerHTML = "♖";
            promoFigureSecond = "♕";
        }
    
        if (color === "WHITE") {
            document.getElementById("promoQueenSecond").innerHTML = "♛";
            document.getElementById("promoBishopSecond").innerHTML = "♝";
            document.getElementById("promoKnightSecond").innerHTML = "♞";
            document.getElementById("promoRookSecond").innerHTML = "♜";
            promoFigureSecond = "♛";
        }

        document.getElementById("promoQueenSecond").style.background = "#FF8C00";
    }
}

function setLogins(data) {
    if (data.boardId == boardId) {
        var help = "A";
        var helpTwo = "B"

        if (document.getElementById(help + "playerFirst").innerHTML === "") {
            document.getElementById(help + "playerFirst").innerHTML = data.loginBoardFirstBlack;
        }

        if (document.getElementById(help + "playerSecond").innerHTML === "") {
            document.getElementById(help + "playerSecond").innerHTML = data.loginBoardFirstWhite;
        }

        if (document.getElementById(helpTwo + "playerFirst").innerHTML === "") {
            document.getElementById(helpTwo + "playerFirst").innerHTML = data.loginBoardSecondBlack;
        }

        if (document.getElementById(helpTwo + "playerSecond").innerHTML === "") {
            document.getElementById(helpTwo + "playerSecond").innerHTML = data.loginBoardSecondWhite;
        }
    }

    if (data.boardId == boardIdAdditional) {
        var help = "B";
        var helpTwo = "A";

        if (document.getElementById(help + "playerFirst").innerHTML === "") {
            document.getElementById(help + "playerFirst").innerHTML = data.loginBoardFirstBlack;
        }

        if (document.getElementById(help + "playerSecond").innerHTML === "") {
            document.getElementById(help + "playerSecond").innerHTML = data.loginBoardFirstWhite;
        }

        if (document.getElementById(helpTwo + "playerFirst").innerHTML === "") {
            document.getElementById(helpTwo + "playerFirst").innerHTML = data.loginBoardSecondBlack;
        }

        if (document.getElementById(helpTwo + "playerSecond").innerHTML === "") {
            document.getElementById(helpTwo + "playerSecond").innerHTML = data.loginBoardSecondWhite;
        }
    }
}