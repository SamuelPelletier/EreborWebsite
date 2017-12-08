<?php

  if(!($_GET["lonStart"]==null || $_GET["latStart"]==null || $_GET["lonFinish"]==null || $_GET["latFinish"]==null|| $_GET["nbPlace"]==null|| $_GET["phoneNbr"]==null|| $_GET["horaireDepart"]==null))
  {
    $db = new PDO("mysql:host=pjkw.myd.infomaniak.com;dbname=pjkw_gpsam;port=3306","pjkw_gpsam","ereborteam");
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $statement = $db->prepare("INSERT INTO Sam (horaireDepart, longitudeDepart, latitudeDepart, longitudeArrivee, latitudeArrivee, nbPlace, phoneNumber) VALUES (:horaireDepart, :longitudeDepart, :latitudeDepart, :longitudeArrivee, :latitudeArrivee, :nbPlace, :phoneNumber)");
    $statement->execute(array(':horaireDepart' => $_GET["horaireDepart"], ':longitudeDepart' => $_GET["lonStart"], ':latitudeDepart' => $_GET["latStart"], ':longitudeArrivee' => $_GET["lonFinish"], ':latitudeArrivee' => $_GET["latFinish"], ':nbPlace' => $_GET["nbPlace"], ':phoneNumber' => $_GET["phoneNbr"]));
  }
  else {
      echo "error";
  }
