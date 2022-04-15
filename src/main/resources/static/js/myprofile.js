const url = 'http://192.168.1.245:8080';// 'https://klocuwb.herokuapp.com''http://192.168.1.245:8080';

function updatePlayer() {
    var login = document.getElementById("login").innerHTML;
    var newlogin = document.getElementById("newlogin").value;
    var password = document.getElementById("password").innerHTML;
    var newpassword = document.getElementById("newpassword").value;

    $.ajax({
        url: url + "/updatePlayer",
        type: 'PUT',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "login": login,
            "newlogin": newlogin,
            "password": password,
            "newpassword": newpassword
        }),
        success: function (data) {
           

        },
        error: function (error) {
            console.log(error);
        }
    })
    console.log(login + " " + newlogin);
}

function deletePlayer() {

    var login = document.getElementById("login").innerHTML;

    $.ajax({
        url: url + "/deletePlayer",
        type: 'DELETE',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "login": login
        }),
        success: function (data) {
           

        },
        error: function (error) {
            console.log(error);
        }
    })
}