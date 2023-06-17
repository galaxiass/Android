<?php
	$data = array();
	$clinic = $_GET["clinic"];
	$service = $_GET["service"];
	$date = $_GET["date"];
	$time = $_GET["time"];
	
	
	$host="localhost";
	$uname="root";
	$pass="";
	$dbname="physiodate";
	
	$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
	mysqli_select_db($dbh, $dbname);
	
	$sql = "INSERT into history values('" . $clinic . "','" . $service . "','" . $date . "','" . $time . "','" "')";
	echo $sql;
	mysqli_query($dbh, $sql);
	mysqli_close($dbh);
?>