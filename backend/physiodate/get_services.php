<?php
$host = "localhost";
$username = "root";
$password = "";
$dbname = "physiodate";
$table = "clinic";

// Create a new connection
$conn = mysqli_connect($host, $username, $password, $dbname);

// Check the connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

// Prepare the SQL statement for retrieving all clinics
$selectSql = "SELECT * FROM $table";

// Execute the select statement
$result = mysqli_query($conn, $selectSql);

// Check if there are any results
if (mysqli_num_rows($result) > 0) {
    // Fetch the rows and store them in an array
    $clinics = array();
    while ($row = mysqli_fetch_assoc($result)) {
        $clinics[] = $row;
    }

    // Generate a response
    $response = array(
        'clinics' => $clinics
    );
} else {
    // No clinics found
    $response = array(
        'status' => 'success',
        'message' => 'No clinics found',
        'clinics' => array()
    );
}

// Close the connection
mysqli_close($conn);

// Send the response as JSON
header('Content-Type: application/json');
echo json_encode($response);
?>
