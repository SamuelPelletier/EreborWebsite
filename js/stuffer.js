/**
 * Created by Samuel on 07/12/2017.
 */

getMeteo('chambery');

function push(val){
    $(".container").append("<div>BOB</div>");
}


function getMeteo(city){
    $.ajax({
        url : 'https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22'+city+'%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys',
        type : 'GET',
        dataType : 'json',
        success : function(data){
            weatherToday = data.query.results.channel.item.forecast[0];
            console.log(weatherToday)
            $(".weather").html("<div>"+weatherToday.date+"<br/>"+Math.floor((weatherToday.low-32)*5/9)+"   "+Math.floor((weatherToday.high-32)*5/9)+"    "+weatherToday.text+"</div>").css("color","red");
        },

        error : function(resultat, statut, erreur){
        }

    });


}