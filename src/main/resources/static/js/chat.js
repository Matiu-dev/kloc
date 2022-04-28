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
            "message": message,
            "team": team
        }),
        success: function (data) {
            document.getElementById("input-message").value = "";
        },
        error: function (error) {
            console.log(error);
        }
    })
}

function displayResponseMessage(data) {

    if(data.messageCommand === "TEAM" && data.team == team) {
        document.getElementById("addMessage").innerHTML +=
        "<ul class='list-group list-group-flush' ><li class='list-group-item'><strong>"+ data.login+ ": " +"</strong><span>" + data.message +"</span></li></ul>";
    }

    if(data.messageCommand === "WHISPER" && data.whisperLogin == login) {
        document.getElementById("addMessage").innerHTML +=
        "<ul class='list-group list-group-flush' ><li class='list-group-item'><strong>"+ data.login+ " (PW): " +"</strong><span>" + data.message +"</span></li></ul>";

    }

    if(data.messageCommand === "WHISPER" && data.login == login) {
        document.getElementById("addMessage").innerHTML +=
        "<ul class='list-group list-group-flush' ><li class='list-group-item'><strong>"+ data.login+ " (PW): " +"</strong><span>" + data.message +"</span></li></ul>";

    }

    if(data.messageCommand === "ALL") {
        document.getElementById("addMessage").innerHTML +=
        "<ul class='list-group list-group-flush' ><li class='list-group-item'><strong>"+ data.login+ ": " +"</strong><span>" + data.message +"</span></li></ul>";
    }
}

function writeFigureName(name) {
    console.log(name.innerHTML);

    document.getElementById("input-message").value += name.innerHTML;
}