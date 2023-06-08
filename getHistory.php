<?php
    $data = array();
    $patient = $_GET["patient"];

    $host="localhost";
	$uname="root";
	$pass="";
	$dbname="physiodate";

    $dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
	mysqli_select_db($dbh, $dbname);

    $sql = "SELECT s.name AS service_name, s.price, a.date
	FROM appointment a
	JOIN patient p ON a.patient_amka = p.amka
	JOIN services s ON a.service_id = s.id
	WHERE p.name ='" . $patient . "'";
	$result = mysqli_query($dbh, $sql);
	if ($result->num_rows > 0) {
		$appointments = array();
		$i = 0;
		while ($row = $result->fetch_assoc()) {
			$appointments['appointmentId' . $i] = $row;
			$i++;
		}
		$jsonResult = json_encode($appointments);
		echo $jsonResult;
	}
	else{
		$jsonResult = json_encode("");
	}

	header("Content-Type: application/json");
	echo json_encode($jsonResult);
	mysqli_close($dbh);
?>    