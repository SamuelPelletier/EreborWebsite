<?php
    error_reporting(E_ERROR | E_PARSE);
        $mail = $_POST["userMail"];
        $psw = $_POST["userPassword"];

        if($mail == "" || $psw == ""){
            echo "<script>alert('Insérez votre E-mail ou mot de passe svp'); history.go(-1);</script>";
        }else{
          try{
          $c = new PDO("mysql:host=pjkw.myd.infomaniak.com;dbname=pjkw_gpsam;port=3306","pjkw_gpsam","ereborteam");
          $c->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        }catch(PDOException $e){
          echo "Connection failed: " . $e->getMessage();
        }
          $c->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
          $sql = 'SELECT userMail,userPassword
                  FROM user
                  WHERE userMail = :userMail AND userPassword = :userPassword';
	         $statement = $c->prepare($sql,array(PDO::ATTR_CURSOR => PDO::CURSOR_FWDONLY));
	         $statement->execute(array(':userMail' => $_POST['userMail'], ':userPassword' => $_POST['userPassword']));
           $rows = $statement->fetchAll();

            if(count($rows) > 0){
              foreach ($rows as $key => $value) {
                foreach ($value as $k => $v) {
                  if(is_int($k)){
                    echo $v;
                    echo "</br>";
                  }
                }
              }
            }else{
                echo "<script>alert('le mél ou le mot de passe n\'est pas correct');history.go(-1);</script>";
            }
        }
