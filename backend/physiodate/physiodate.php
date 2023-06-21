<?php
// Retrieve the parameters from the request
$serviceId = $_GET['serviceId'];
$serviceName = $_GET['serviceName'];
$serviceCost = $_GET['serviceCost'];
$serviceDescription = $_GET['serviceDescription'];

$host = "localhost";
$username = "root";
$password = "";
$dbname = "physiodate";
$table = "services";

// Create a new connection
$conn = mysqli_connect($host, $username, $password, $dbname);

// Check the connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

// Prepare the SQL statement
$sql = "INSERT INTO $table (id, name, price, description) VALUES ('$serviceId', '$serviceName', '$serviceCost', '$serviceDescription')";

if (mysqli_query($conn, $sql)) {
    // Data inserted successfully

    // Generate a response
    $response = array(
        'status' => 'success',
        'message' => 'Service added successfully'
    );
} else {
    // Failed to insert data

    // Generate a response
    $response = array(
        'status' => 'error',
        'message' => 'Failed to add service: ' . mysqli_error($conn)
    );
	 // Output the error message
    echo json_encode($response);
    exit; // Terminate the script execution
}

// Close the connection
mysqli_close($conn);

// Send the response as JSON
header('Content-Type: application/json');
echo json_encode($response);

?>
