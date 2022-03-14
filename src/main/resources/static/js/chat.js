function sendMessage() {
    var message  = document.getElementById("input-message").value;
    var login = document.getElementById("login").innerHTML;
    console.log(message);
    console.log("chat id is " + chatId);
    // stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));

    $.ajax({
        url: url + "/game/gameplay",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "type": "message",
            "gameId": gameId,
            "login": login,
            "message": message
        }),
        success: function (data) {

        },
        error: function (error) {
            console.log(error);
        }
    })
}

function displayResponseMessage(data) {

    document.getElementById("addMessage").innerHTML +=
     "<ul class='list-group list-group-flush' ><li class='list-group-item'><strong>"+ data.login+ ": " +"</strong><span>" + data.message +"</span></li></ul>";
    console.log("Message");
}