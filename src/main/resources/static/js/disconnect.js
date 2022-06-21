//do testu nie dziala to wszystko
// function disconnect() {
//     if (stompClient !== null) {
//         stompClient.disconnect(function() {
//             console.log("Client disconnected");
//         });
//         stompClient = null;
//     }
//  }

//  window.onclose = function () {
//     disconnect();
//     console.log("discoonect test");
// }

// function disconnect() {

//     var disconnectTeam="";

//     if(team === "A"){
//         disconnectTeam = "B";
//     } else if(team === "B") {
//         disconnectTeam = "A";
//     }

//     $.ajax({
//         url: url + "/game/gameplay",
//         type: 'POST',
//         dataType: "json",
//         contentType: "application/json",
//         data: JSON.stringify({
//             "type": "disconnect",
//             "gameId": gameId,
//             "team": disconnectTeam,
//             "gameResult": gameResult,
//             "playerId": playerId
//         }),
//         success: function (data) {
//             //            gameOn = false;
//             // displayResponse(data);
//             // playerIdMove = data.playerIdMove;
//         },
//         error: function (error) {
//             console.log(error);
//         }
//     })
// }