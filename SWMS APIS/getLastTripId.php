<?php
$serverName = "localhost";
$userName = "root";
$password = "abd123456";
$db_name = "smartwaste";

$conn = new mysqli($serverName, $userName, $password, $db_name); //connection object
if ($conn->connect_error) {
    //error in connection
    //die
} else {
    //connection successfully
    $sqlStatement = "SELECT max(trid) as lastid FROM `trip`";
    $result = $conn->query($sqlStatement);
    if ($result->num_rows > 0) {
        //there is data
        while ($row = $result->fetch_assoc()) {
            $lastid["id"] = $row["lastid"];
            $lastid["queryresult"] = true;
            $lastids[] =$lastid;
        }
        echo json_encode($lastids);
    } else {
            $lastid["id"] =0;
            $lastid["queryresult"] =false;
            $lastids[] =$lastid;
            echo json_encode($lastids);
    }
}
$conn->close();
?>
