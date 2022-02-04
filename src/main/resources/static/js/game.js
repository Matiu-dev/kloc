const url = 'http://localhost:8080';
let gameId;
let playerId;
let boardId;
let boardIdAdditional;
let color;
let stompClient;
let playerIdMove;
let nextMoveColor;


function connectToSocket(gameId) {

    console.log("connecting to the game");
    let socket = new SockJS(url + "/gameplay");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to the frame: " + frame);
        console.log("board id to: " + boardId);
        console.log("kolor to: " + color);

        stompClient.subscribe("/topic/game-progress/" + gameId, function (response) {
            let data = JSON.parse(response.body);
            console.log(data);
            console.log(data.nextMoveColor)

            nextMoveColor = data.nextMoveColor;
            displayResponse(data);
        })
    })
}

function create_game() {
    let login = document.getElementById("login").innerHTML;//sprawdzic czy z sesji da sie to wyciagnac
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
                //ustawia id dla szachownic
                setBoardsId(boardId, boardIdAdditional);

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

function setBoardsId(myBoard, boardAdditional) {
    document.getElementById("board1").setAttribute('id',myBoard);
    document.getElementById("board2").setAttribute('id', boardAdditional);
}


function connect_to_specific_game() {
    let login = document.getElementById("login").innerHTML;
    let id = document.getElementById("playerId").innerHTML;


     if (login == null || login === '') {
            alert("Please enter login");
     } else {
        let boardId = document.getElementById("boardId").value;
//        console.log(boardId);
        if (boardId == null || boardId === '') {
                    alert("Please enter board id");
        }
        $.ajax({
                    url: url + "/game/connect",
                    type: 'POST',
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify({
                        "gameId": boardId,
                        "playerId": id
                    }),
                    success: function (data) {
                        gameId = data.gameId;
                        boardId = data.boardId;
                        color = data.color;
                        boardIdAdditional = data.boardIdAdditional;
                        playerIdMove = data.playerIdMove;
                        playerId=data.playerId;
                        //ustawia id dla szachownic
                        setBoardsId(boardId, boardIdAdditional);

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
