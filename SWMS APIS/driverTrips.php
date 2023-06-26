<?php
$serverName = "localhost";
$userName = "root";
$password = "abd123456";
$db_name = "smartwaste";
$DId =$_POST["DId"];//driver id
$conn = new mysqli($serverName, $userName, $password, $db_name); //connection object
if ($conn->connect_error) {
    //error in connection
    //die
} else {
    //connection successfully
    $sqlStatement = "SELECT trid, trstartTime, trendTime, trdistance, trdate, f_tid FROM `trip` WHERE f_did=$DId";
    $result = $conn->query($sqlStatement);
    if ($result->num_rows > 0) {
        //there is data
        while ($row = $result->fetch_assoc()) {
            $trip["id"] = $row["trid"];
            $trip["startTime"] = $row["trstartTime"];
            $trip["endTime"] = $row["trendTime"];
            $trip["distance"] = $row["trdistance"];
            $trip["date"] = $row["trdate"];
            $trip["truckid"] = $row["f_tid"];
            $trip["tripqueryresult"] = true;
            $trips[] = $trip;
        }
        echo json_encode($trips);
    } else {
        $trip["id"] = 0;
        $trip["startTime"] =date('Y-m-d H:i:s');
        $trip["endtime"] = date(' H:i:s');
        $trip["distance"] =0;
        $trip["date"] =date('Y-m-d');
        $trip["truckid"] = 0;
        $trip["tripqueryresult"] = false;
        $trips[] = $trip;
        echo json_encode($trips);
    }
}
$conn->close();
?>
