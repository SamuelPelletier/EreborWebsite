/**
 * Created by Samuel on 07/12/2017.
 */

var lat = 48.86;
var lon = 2.34;
getGeoloc()
var result = parse_query_string(document.URL.split("?").pop());
if (result.page != undefined) {
    push(result.page)
} else {
    push(1)
}


function push(val) {
    val = parseInt(val);
    switch (val) {
        case 0:
            $(".container").html("<p style='z-index: 2;position: relative;text-align: center;font-size: xx-large;font-family: unset;'>Traffic en temps réel</p><div id='map'></div>");
            initMap();
            break;
        case 1:
            $(".container").html("<p>Prévention</p><br/></p>");
            break;
        case 2:
            $(".container").html('<form action="./php/logincheck.php" method="post"> <table> <tr><td>E-mail : </td><td><input type="text" name="userMail" /></td></tr></br> <tr><td>Password : </td><td><input type="password" name="userPassword" /></td></tr></br> </table> <input type="submit" name="submit" value="login" /> <a href="#" onclick="push(4)">registre</a> </form>')
            break;
        case 3:
            $(".container").html('<h1>À propos</h1> <p>Application réalisée au campus TechnoLac du Bourget-du-Lac par l\'équipe "Erebor" dans le cadre de la Nuit de l\'Informatique 2017.</p> <h2>Membres</h2> <a href="mailto:samuel.pelletier@etu.univ-savoie.fr">Samuel Pelletier</a> <br /> <a href="mailto:loideh74@gmail.com">Loic Dehours</a> <br /> <a href="mailto:juju7397@gmail.com">Julien Aimonier-Davat</a> <br /> <a href="mailto:lei.zhang@etu.univ-savoie.fr">Lei Zhang</a> <br /> <a href="mailto:shuyuan.lyu@etu.univ-savoie.fr">Lyu Shuyuan</a> <br /> <a href="mailto:jia.qin@etu.univ-savoie.fr">Jia Qin</a> <br /> <a href="mailto:abdelghafour.driowya@etu.univ-smb.fr">Abdelghafour Driowya</a> <br /> <a href="mailto:juliettebois@icloud.com">Juliette Bois</a> <br /> <a href="mailto:cfriedrich1@gmail.com">Christian Friedrich</a> <br /> <a href="mailto:bastienmonin@gmail.com">Bastien Monin</a> <br /> <a href="mailto:teddy.ruppin@hotmail.fr">Teddy Ruppin</a> <br /> <a href="mailto:hugues-reynaud@orange.fr">Reynaud Hugues</a>')
            break;
        case 4:
            $(".container").html('<form action="./php/regcheck.php" method="post"> <table> <tr><td>userMail : </td><td><input type="text" name="userMail"/></td></tr><br/> <tr><td>First Name : </td><td><input type="text" name="userFName"/></td></tr><br/> <tr><td>Last Name : </td><td><input type="text" name="userLName"/></td></tr><br/> <tr><td>Password : </td><td><input type="password" name="userPassword"/></td></tr><br/> </table> <input type="Submit" name="Submit" value="register"/><a href="#" onclick="push(2)">se connecter</a> </form>')
            break;
    }
}


function getMeteo() {
    $.ajax({
        url: 'https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22(' + lat + '%2C' + lon + ')%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            weatherToday = data.query.results.channel.item.forecast[0];
            $(".weather").html("<p style='left:30%;text-transform: uppercase;position: relative;'>Météo</p><p>" + data.query.results.channel.location.city + "</p><p>" + weatherToday.date + "</p><p>Min: " + Math.floor((weatherToday.low - 32) * 5 / 9) + "°C   Max: " + Math.floor((weatherToday.high - 32) * 5 / 9) + "°C</p><p>" + weatherToday.text + "</p>").css("color", "white");
        },
    });


}

function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 13,
        center: {lat: lat, lng: lon}
    });

    var marker = new google.maps.Marker({
        position: {lat: lat, lng: lon},
        map: map,
        title: 'Vous étes ici !'
    });

    var trafficLayer = new google.maps.TrafficLayer();
    trafficLayer.setMap(map);
}

function linkTransport() {
    var tab = {
        "Chambery": "https://www.bus-stac.fr/Se-deplacer/Horaires-passage-temps-reel",
        "Paris": "https://www.ratp.fr/horaires",
        "Lille": "https://www.transpole.fr/fr/",
        "Caen": "http://www.twisto.fr/",
        "Rennes": "http://www.star.fr/accueil/",
        "Orleans": "http://www.reseau-tao.fr/",
        "Strasbourg": "https://www.cts-strasbourg.eu/fr/se-deplacer/fiches-horaires/",
        "Dijon": "https://www.divia.fr/page/recherche-d-itineraire",
        "Lyon": "http://www.tcl.fr/index.php",
        "Bordeaux": "https://www.infotbm.com/nextdeparture",
        "Toulouse": "https://www.tisseo.fr/",
        "Marseille": "http://www.rtm.fr/horaires-en-temps-reel-0",
        "Ajaccio": "https://www.capamove.corsica/",
        "Le Bourget-du-Lac": "https://www.bus-stac.fr/Se-deplacer/Horaires-passage-temps-reel"
    };

    $.ajax({
        url: 'https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22(' + lat + '%2C' + lon + ')%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            city = data.query.results.channel.location.city;
            if (tab[city]) {
                $(".sidebar-nav").append("<li><a class='linkTransport'>Transport en temps réel</a></li>")
                $(".linkTransport").attr("href", tab[city]);
            }
        },
    });


}

function getGeoloc() {
    navigator.geolocation.getCurrentPosition(function (data) {
        lat = data.coords.latitude;
        lon = data.coords.longitude;
        getMeteo();
        linkTransport();
    }, function () {
        $(".container").html("<p style='text-align: center;position: relative;top: 50%;font-size: -webkit-xxx-large; '>Il faut la géolocalisation pour utiliser ce site :(</p>")
    })
}

function parse_query_string(query) {
    var vars = query.split("&");
    var query_string = {};
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        // If first entry with this name
        if (typeof query_string[pair[0]] === "undefined") {
            query_string[pair[0]] = decodeURIComponent(pair[1]);
            // If second entry with this name
        } else if (typeof query_string[pair[0]] === "string") {
            var arr = [query_string[pair[0]], decodeURIComponent(pair[1])];
            query_string[pair[0]] = arr;
            // If third or later entry with this name
        } else {
            query_string[pair[0]].push(decodeURIComponent(pair[1]));
        }
    }
    return query_string;
}

function calendar() {
    var days = [
        [896, 378],
        [842, 174],
        [774, 444],
        [1033, 606],
        [924, 305],
        [797, 278],
        [877, 532],
        [955, 507],
        [978, 272],
        [1096, 623],
        [880, 242],
        [727, 486],
        [928, 139],
        [1036, 487],
        [936, 204],
        [816, 384],
        [800, 517],
        [974, 432],
        [847, 322],
        [882, 453],
        [869, 115],
        [982, 356],
        [1055, 550],
        [885, 39]
    ];
    var i = 1;
    days.forEach(function (item) {
        //$("body").append("<div id=" + i + " style='position:absolute;left:" + item[0] + "px;top:" + item[1] + "px;z-index:12;' onclick='displayPresent(" + i + ")'> </div>");
        i++
    });

}

function displayPresent(val) {


}