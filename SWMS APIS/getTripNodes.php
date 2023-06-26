<?php
$serverName = "localhost";
$userName = "root";
$password = "abd123456";
$db_name = "smartwaste";
$trId =$_POST["TrId"];//driver id
$conn = new mysqli($serverName, $userName, $password, $db_name); //connection object
if ($conn->connect_error) {
    //error in connection
    //die
} else {
    //connection successfully
    $sqlStatement = "SELECT nid,nlatitude,nlongitude,nstreet FROM node,trip ,`node_trip` WHERE node_trip.f_trid=$trId and node.nid=node_trip.f_nid and trip.trid=node_trip.f_trid";
    $result = $conn->query($sqlStatement);
    if ($result->num_rows > 0) {
        //there is data
        while ($row = $result->fetch_assoc()) {
            $node["id"] = $row["nid"];
            $node["latitude"] = $row["nlatitude"];
            $node["longitude"] = $row["nlongitude"];
            $node["street"] = $row["nstreet"];
            $nodes[] = $node;
        }
        echo json_encode($nodes);
    } else {
        $node["id"] =0;
            $node["latitude"] =0;
            $node["longitude"] =0;
            $node["street"] ="empty";
            $nodes[] = $node;
        echo json_encode($nodes);
    }
}
$conn->close();
?>
