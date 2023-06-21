<?php
    $data = array();

    $host="localhost";
    $uname="root";
    $pass="";
    $dbname="physiodate";

    $dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
    mysqli_select_db($dbh, $dbname);

    $sql = "SELECT clinic, GROUP_CONCAT(services) AS grouped_services FROM services GROUP BY
clinic";
    $result = mysqli_query($dbh, $sql);
	while ($row = mysqli_fetch_array($result)) {
		$data[$row['clinic']] = $row['grouped_services'];
 }

    header("Content-Type: application/json");
    echo json_encode($data);
    mysqli_close($dbh);
?>

