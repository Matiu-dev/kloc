const url = 'http://192.168.1.245:8080';// 'https://klocuwb.herokuapp.com'
let login;
let gameId;
let playerId;
let boardId;
let boardIdAdditional;
let stompClient;
let playerIdMove;
let nextMoveColor;
let team;
let chatId;
let submit;
let gameTime;
let additionalTime;

function connectToSocket(gameId) {

    console.log("connecting to the game");
    let socket = new SockJS(url + "/gameplay");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        // console.log("connected to the frame: " + frame);
        // console.log("board id to: " + boardId);
        console.log("kolor to: " + color);
        console.log("chat id to: " + chatId);


        stompClient.subscribe("/topic/game-progress/" + gameId, function (response) {
            let data = JSON.parse(response.body);
            console.log(data);
            // console.log(data.nextMoveColor)

            if(data.type==="gameplay"){
                //ustawienie nastepnego ruchu dla odpowiedniej planszy
                if(data.boardId == boardId) {
                    nextMoveColor = data.nextMoveColor;
                    enPassantCord = data.enPassantCord;
                }

                console.log(enPassantCord);

                //do roszady
                if(data.playerId==playerId) {
                    castling=data.castling;
                }

                if(data.moveType==="BASIC"){
                    displayResponseBasic(data);
                }

                if(data.moveType==="RESERVE") {
                    displayResponseReserve(data);
                }

                // console.log(castling[0] + " " + castling[1] + " " + castling[2]);

                gameResult = data.gameResult;
            }

            if(data.type==="message") {
                displayResponseMessage(data);
            }
        })
    })
}

function create_game() {
    login = document.getElementById("login").innerHTML;//sprawdzic czy z sesji da sie to wyciagnac
    let id = document.getElementById("playerId").innerHTML;//i to

    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        $.ajax({
            url: url + "/game/create",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                        "id": id,
                         "playerName": login
            }),
            success: function (data) {
                gameId = data.gameId;
                boardId = data.boardId;
                color = data.color;
                boardIdAdditional = data.boardIdAdditional;
                playerIdMove = data.playerIdMove;
                playerId=data.playerId;
                team = data.team;
                chatId = data.chatId;
                //ustawia id dla szachownic
                setBoardsId(boardId, boardIdAdditional);
                
                //ustawia czas
                setTime();
                

                //ustawia figury do awansu
                setPromoFigures(color);

                nextMoveColor="WHITE";
                reset(boardId, boardIdAdditional);
                connectToSocket(gameId);
                alert("Your created a game. Game id is: " + gameId +
                " and your board id is: " + boardId);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}

window.onload = function() {

    if(submit === "create"){
        create_game();
    }

    if(submit === "join") {
        connect_to_specific_game();
    }
}

function connect_to_specific_game() {
    let login = document.getElementById("login").innerHTML;
    let id = document.getElementById("playerId").innerHTML;


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
                        "playerId": id
                    }),
                    success: function (data) {
                        gameId = data.gameId;
                        boardId = data.boardId;
                        color = data.color;
                        boardIdAdditional = data.boardIdAdditional;
                        playerIdMove = data.playerIdMove;
                        playerId=data.playerId;
                        team = data.team;
                        //ustawia id dla szachownic
                        setBoardsId(boardId, boardIdAdditional);
                        // setColorOnBoard(color);

                        //ustawia figury do awansu
                        setPromoFigures(color);

                        nextMoveColor="WHITE";
                        reset(boardId, boardIdAdditional);
                        connectToSocket(gameId);
                        alert("You connected to a game. Game id is: " + gameId +
                                        " and your board id is: " + boardId);
                        console.log(boardId);
                    },
                    error: function (error) {
                        console.log(error);
                    }
        })
     }


}

function setColorOnBoard(color) {
    document.getElementById("colorOnBoard").innerHTML =  color;
}

function setBoardsId(myBoard, boardAdditional) {
    document.getElementById("board1").setAttribute('id',myBoard);
    document.getElementById("board2").setAttribute('id', boardAdditional);
    // document.getElementById("boardName1").innerHTML = myBoard;
    // document.getElementById("boardName2").innerHTML = boardAdditional;

}

function setTime() {
    document.getElementById("gameTimeOne").innerHTML += gameTime;
    document.getElementById("gameTimeTwo").innerHTML += gameTime;
    document.getElementById("gameTimeThree").innerHTML += gameTime;
    document.getElementById("gameTimeFour").innerHTML += gameTime;
}

function setPromoFigures(color) {
    if(color==="WHITE") {
        document.getElementById("promoQueen").innerHTML = "♕";
        document.getElementById("promoBishop").innerHTML = "♗";
        document.getElementById("promoKnight").innerHTML = "♘";
        document.getElementById("promoRook").innerHTML = "♖";
    }

    if(color==="BLACK") {
        document.getElementById("promoQueen").innerHTML = "♛";
        document.getElementById("promoBishop").innerHTML = "♝";
        document.getElementById("promoKnight").innerHTML = "♞";
        document.getElementById("promoRook").innerHTML = "♜";
    }
}