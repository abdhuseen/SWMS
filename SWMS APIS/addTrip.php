<?php
$serverName = "localhost";
$userName = "root";
$password = "abd123456";
$db_name = "smartwaste";

$lastId=$_POST["lastId"];

$startTime=$_POST["startTime"];

$endTime=$_POST["endTime"];

$distance=$_POST["distance"];

$tripDate=$_POST["date"];

$truckId=$_POST["truckId"];

$driverId=$_POST["driverId"];

$conn = new mysqli($serverName, $userName, $password, $db_name); //connection object
if ($conn->connect_error) {
    //error in connection
    //die
} else {
    //connection successfully
   $sqlStatement="insert into trip (trstartTime,trendTime,trdistance,trdate,f_tid,f_did)
   values ('$startTime','$endTime',$distance,'$tripDate',$truckId,$driverId)";
    $result=$conn->query($sqlStatement);
    if($result===true){
      
            $trip["id"] =$lastId;
            $trip["startTime"] ="$startTime";
            $trip["endTime"] = "$endTime";
            $trip["distance"] = $distance;
            $trip["date"] = "$tripDate";
            $trip["truckid"] =$truckId;
            $trip["tripqueryresult"] = true;
            $trips[] = $trip;
        echo json_encode($trips);
    }else{
            
            $trip["id"] =0;
            $trip["startTime"] ="empty";
            $trip["endTime"] = "empty";
            $trip["distance"] = "empty";
            $trip["date"] = "empty";
            $trip["truckid"] =0;
            $trip["tripqueryresult"] =false;
            $trips[] = $trip;
        echo json_encode($trips);
    }
}
$conn->close();
?>
