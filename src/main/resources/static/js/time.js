var teamTime = "";

function setTimeForBoardA(data) {

    var help = "A";
    

    if (checkColor(data.figureNameNew) === "BLACK") {

        //dodaje czas po ruchu i go wyswietla
        AgameTimeFirst += parseInt(additionalTime);
        document.getElementById(help + "gameTimeFirst").innerHTML = "Czas: " +
            Math.floor(AgameTimeFirst / 60) +
            "m" +
            Math.floor(AgameTimeFirst % 3600 % 60) + "s";


        if (internalSecond != null) {
            clearInterval(internalSecond);
        }

        if (internalFirst != null) {
            clearInterval(internalFirst);
        }

        internalFirst = setInterval(function () {

            document.getElementById(help + "gameTimeSecond").innerHTML = "Czas: " +
                Math.floor(AgameTimeSecond / 60) +
                "m" +
                Math.floor(AgameTimeSecond % 3600 % 60) + "s";

            if (AgameTimeSecond < 0) {
                clearInterval(internalFirst);
                document.getElementById(help + "gameTimeSecond").innerHTML = "Przegrana";


                if(data.team === "A"){
                    teamTime = "A";
                } else if(data.team === "B") {
                    teamTime = "B";
                }

                timeLeft();
            }

            AgameTimeSecond -= 1;
        }, interval);
    }

    if (checkColor(data.figureNameNew) === "WHITE") {

        AgameTimeSecond += parseInt(additionalTime);
        document.getElementById(help + "gameTimeSecond").innerHTML = "Czas: " +
            Math.floor(AgameTimeSecond / 60) +
            "m" +
            Math.floor(AgameTimeSecond % 3600 % 60) + "s";


        if (internalFirst != null) {
            clearInterval(internalFirst);
        }

        if (internalSecond != null) {
            clearInterval(internalSecond);
        }

        internalSecond = setInterval(function () {

            document.getElementById(help + "gameTimeFirst").innerHTML = "Czas: " +
                Math.floor(AgameTimeFirst / 60) +
                "m" +
                Math.floor(AgameTimeFirst % 3600 % 60) + "s";

            if (AgameTimeFirst < 0) {
                clearInterval(internalSecond);
                document.getElementById(help + "gameTimeFirst").innerHTML = "Przegrana";

                if(data.team === "A"){
                    teamTime = "A";
                } else if(data.team === "B") {
                    teamTime = "B";
                }

                timeLeft();
            }

            AgameTimeFirst -= 1;
        }, interval);
    }
}

function setTimeForBoardB(data) {

    var help = "B";

    if (checkColor(data.figureNameNew) === "BLACK") {

        BgameTimeFirst += parseInt(additionalTime);
        document.getElementById(help + "gameTimeFirst").innerHTML = "Czas: " +
            Math.floor(BgameTimeFirst / 60) +
            "m" +
            Math.floor(BgameTimeFirst % 3600 % 60) + "s";

        if (internalFourth != null) {
            clearInterval(internalFourth);
        }

        if (internalThird != null) {
            clearInterval(internalThird);
        }

        internalThird = setInterval(function () {

            document.getElementById(help + "gameTimeSecond").innerHTML = "Czas: " +
                Math.floor(BgameTimeSecond / 60) +
                "m" +
                Math.floor(BgameTimeSecond % 3600 % 60) + "s";

            if (BgameTimeSecond < 0) {
                clearInterval(internalThird);
                document.getElementById(help + "gameTimeSecond").innerHTML = "Przegrana";

                if(data.team === "A"){
                    teamTime = "A";
                } else if(data.team === "B") {
                    teamTime = "B";
                }

                timeLeft();
            }

            BgameTimeSecond -= 1;
        }, interval);
    }

    if (checkColor(data.figureNameNew) === "WHITE") {

        BgameTimeSecond += parseInt(additionalTime);
        document.getElementById(help + "gameTimeSecond").innerHTML = "Czas: " +
            Math.floor(BgameTimeSecond / 60) +
            "m" +
            Math.floor(BgameTimeSecond % 3600 % 60) + "s";

        if (internalThird != null) {
            clearInterval(internalThird);
        }

        if (internalFourth != null) {
            clearInterval(internalFourth);
        }

        internalFourth = setInterval(function () {

            document.getElementById(help + "gameTimeFirst").innerHTML = "Czas: " +
                Math.floor(BgameTimeFirst / 60) +
                "m" +
                Math.floor(BgameTimeFirst % 3600 % 60) + "s";

            if (BgameTimeFirst < 0) {
                clearInterval(internalFourth);
                document.getElementById(help + "gameTimeFirst").innerHTML = "Przegrana";

                if(data.team === "A"){
                    teamTime = "A";
                } else if(data.team === "B") {
                    teamTime = "B";
                }

                timeLeft();
            }

            BgameTimeFirst -= 1;
        }, interval);
    }
}

function timeLeft() {
    $.ajax({
        url: url + "/game/gameplay",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "type": "time",
            "gameId": gameId,
            "team": teamTime,
            "gameResult": "CHECKMATE"
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