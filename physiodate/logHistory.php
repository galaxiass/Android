<?php
 $data = array();
 $name = $_GET["name"];
 $amka = $_GET["amka"];
 $address = $_GET["address"];
 $password=$_GET["password"];
 
 $host="localhost";
 $uname="root";
 $pass="";
 $dbname="physiodate";
 
 $dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
 mysqli_select_db($dbh, $dbname);
 
 $sql = "INSERT into patient values('" . $name . "','" . $amka . "','" .
$address . "','" . $password . "')";
 echo $sql;
 mysqli_query($dbh, $sql);
 mysqli_close($dbh);
?>