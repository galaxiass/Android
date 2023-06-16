<?php
    $data = array();
    $patient = $_GET["patient"];

    $host="localhost";
    $uname="root";
    $pass="";
    $dbname="physiodate";

    $dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
    mysqli_select_db($dbh, $dbname);

    $sql = "DELETE FROM appointment WHERE patient_amka= $_GET[patient_amka];"
    $result = mysqli_query($dbh, $sql);
    header("Content-Type: application/json");
    echo json_encode($data);
    mysqli_close($dbh);
?> 