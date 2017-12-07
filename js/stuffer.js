/**
 * Created by Samuel on 07/12/2017.
 */

getMeteo('savoie');

function push(val){
    $(".container").append("<div>BOB</div>");
}


function getMeteo(city){
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
                    $(".weather").html("<div>"+data.query.results.channel.location.city+"<br/>" + weatherToday.date + "<br/>Min: " + Math.floor((weatherToday.low - 32) * 5 / 9) + "°C   Max: " + Math.floor((weatherToday.high - 32) * 5 / 9) + "°C<br/>" + weatherToday.text + "</div>").css("color", "red");
                },
            });
        })
    }


}

function showPosition(position) {
    x.innerHTML = "Latitude: " + position.coords.latitude +
        "<br>Longitude: " + position.coords.longitude;
}