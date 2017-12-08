<?php
    if(isset($_POST["Submit"]) && $_POST["Submit"] == "register"){
        $userMail = $_POST["userMail"];
        $userFName = $_POST["userFName"];
        $userLName = $_POST["userLName"];
        $userPassword =  $_POST["userPassword"];
        if($userMail == "" || $userFName == "" || $userLName == "" || $userPassword == ""){
            echo "<script>alert('les informations ne sont pas completes'); history.go(-1);</script>";
        }else{
          try{
          $c = new PDO("mysql:host=pjkw.myd.infomaniak.com;dbname=pjkw_gpsam;port=3306","pjkw_gpsam","ereborteam");
          $c->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        }catch(PDOException $e){
          echo "Connection failed: " . $e->getMessage();
        }
                $sql = 'SELECT userMail
                FROM user
                WHERE userMail = :userMail';

                $statement = $c->prepare($sql,array(PDO::ATTR_CURSOR => PDO::CURSOR_FWDONLY));
                $statement->execute(array(':userMail' => $_POST['userMail']));
                $rows = $statement->fetchAll();
                if($row){
                    echo "<script>alert('ce E-mail est déjà registré'); history.go(-1);</script>";
                } else {
                    $statement = $c -> prepare('INSERT INTO user (userMail,userFName,userLName,userPassword)
                    values(:userMail,
                           :userFName,
                           :userLName,
                           :userPassword)');
                $statement ->execute(array(':userMail' => $_POST['userMail'],
                                          ':userFName' => $_POST['userFName'],
                                          ':userLName' => $_POST['userLName'],
                                          ':userPassword' => $_POST['userPassword'],));

                //$rows = $statement->fetchAll();

                    if($statement){
                        echo "<script>alert('registre success'); history.go(-1);</script>";
                    }else{
                        echo "<script>alert('le system est occupé maintenant'); history.go(-1);</script>";
                    }
                }
        }
    }else{
        echo "<script>alert('echoue de registre'); history.go(-1);</script>";
    }
?>
