<?php
$serverName = "localhost";
$userName = "root";
$password = "abd123456";
$db_name = "smartwaste";
$latitude =$_POST["lat"];
$longitude =$_POST["long"];
$conn = new mysqli($serverName, $userName, $password, $db_name); //connection object
if ($conn->connect_error) {
    //error in connection
    //die
} else {
    //connection successfully
    $sqlStatement = "SELECT bin.bid,bin.bstate from bin,node WHERE node.nlatitude=$latitude and node.nlongitude=$longitude and bin.bstate>=1 AND bin.f_nid=node.nid";
    $result = $conn->query($sqlStatement);
    if ($result->num_rows > 0) {
        //there is data
        while ($row = $result->fetch_assoc()) {
            $bin["id"] = $row["bid"];
            $bin["state"] = $row["bstate"];
            $bins[] = $bin;
        }
        echo json_encode($bins);
    } else {
            $bin["id"] =0;
            $bin["id"] =0;
            $bin["state"] =-1;
            $bins[] = $bin;
        echo json_encode($bins);
    }
}
$conn->close();
?>
