<?php

  if(!($_GET["lat"]==null || $_GET["lon"]==null || $_GET["radius"]==null || $_GET["param"]==null)) // 0-> sam / 1->info
  {
    $db = new PDO("mysql:host=pjkw.myd.infomaniak.com;dbname=pjkw_gpsam;port=3306","pjkw_gpsam","ereborteam");
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $param = $_GET["param"]=='1'? "Sam" : "InfoRoute";
    $complement = $_GET["param"]=='1'? "Depart" : "";

    $sql = 'SELECT * FROM '.$param.'
    WHERE ACOS(SIN(RADIANS( `latitude'.$complement.'` ))
    * SIN( RADIANS( :latitude ))
    + COS( RADIANS( `latitude'.$complement.'` ))
    * COS(RADIANS( :latitude ))
    * COS( RADIANS( `longitude'.$complement.'` )
    - RADIANS( :longitude )))
    * 6380 < '.$_GET["radius"];

     $statement = $db->prepare($sql,array(PDO::ATTR_CURSOR => PDO::CURSOR_FWDONLY));
     $statement->execute(array(':latitude' => $_GET["lat"], ':longitude' => $_GET["lon"]));
     $rows = $statement->fetchAll();
     echo json_encode($rows);
  }
  else
    echo "error";
    ?>
