/**
 * Created by Samuel on 07/12/2017.
 */

getMeteo();
linkTransport();

function push(val) {
    $(".container").append("<div>BOB</div>");
}


function getMeteo() {
    var lon;
    var lat;
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (data) {
            lon = data.coords.latitude;
            lat = data.coords.longitude;
            $.ajax({
                url: 'https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22(' + lon + '%2C' + lat + ')%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys',
                type: 'GET',
                dataType: 'json',
                success: function (data) {
                    weatherToday = data.query.results.channel.item.forecast[0];
                    $(".weather").html("<div>" + data.query.results.channel.location.city + "<br/>" + weatherToday.date + "<br/>Min: " + Math.floor((weatherToday.low - 32) * 5 / 9) + "°C   Max: " + Math.floor((weatherToday.high - 32) * 5 / 9) + "°C<br/>" + weatherToday.text + "</div>").css("color", "red");
                },
            });
        })
    }


}

function secret(){
    
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
        "Le Bourget-du-Lac":"https://www.bus-stac.fr/Se-deplacer/Horaires-passage-temps-reel"
    };
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (data) {
            lon = data.coords.latitude;
            lat = data.coords.longitude;
            $.ajax({
                url: 'https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22(' + lon + '%2C' + lat + ')%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys',
                type: 'GET',
                dataType: 'json',
                success: function (data) {
                    city = data.query.results.channel.location.city;
                    if(tab[city]){
                        $(".sidebar-nav").append("<li><a class='linkTransport'>Transport en temps réel</a></li>")
                        $(".linkTransport").attr("href", tab[city]);
                    }
                },
            });
        })
    }
}