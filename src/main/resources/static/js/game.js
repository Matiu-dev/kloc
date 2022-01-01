const url = 'http://localhost:8080';
let boardId;
let stompClient;

function connectToSocket(boardId) {

    console.log("connecting to the game");
    let socket = new SockJS(url + "/gameplay");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to the frame: " + frame);
        console.log(boardId);
        stompClient.subscribe("/topic/game-progress/" + boardId, function (response) {
            let data = JSON.parse(response.body);
            console.log(data);
            displayResponse(data);
        })
    })
}

function create_game() {
    let login = document.getElementById("login").innerHTML;
    let id = document.getElementById("playerId").innerHTML;

    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        $.ajax({
            url: url + "/game/create",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "test": "board1",
                    "players": [
                        {
                            "id": id,
                            "playerName": login
                        }
                    ]
            }),
            success: function (data) {
                boardId = data.id;
//                playerType = 'X';
                reset(boardId);
//                gameOn = true;
                connectToSocket(boardId);
                alert("Your created a game. Game id is: " + data.id);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
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
                        "id": boardId,
                        "test": "board1",
                        "players": [
                            {
                                "id": id,
                                "playerName": login
                            }
                        ]
                    }),
                    success: function (data) {
                        boardId = data.id;
//                        playerType = 'O';
                        reset(boardId);
                        connectToSocket(boardId);
                        alert("Congrats you're playing with: " + data.id);
                        console.log(boardId);
                    },
                    error: function (error) {
                        console.log(error);
                    }
        })
     }


}
