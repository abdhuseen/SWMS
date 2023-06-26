<?php
$serverName = "localhost";
$userName = "root";
$password = "abd123456";
$db_name = "smartwaste";

$tripId=$_POST["trId"];

$nodeId=$_POST["nid"];

$conn = new mysqli($serverName, $userName, $password, $db_name); //connection object
if ($conn->connect_error) {
    //error in connection
    //die
} else {
    //connection successfully
   $sqlStatement="insert into node_trip (f_nid,f_trid)
   values ($nodeId,$tripId)";
    $result=$conn->query($sqlStatement);
    if($result===true){
      
            $path["trid"] =$tripId;
            $path["nid"] =$nodeId;
            $path["queryresult"] = true;
            $paths[] = $path;
        echo json_encode($paths);
    }else{
            
            $path["trid"] =0;
            $path["nid"] =0;
            $path["queryresult"] =false;
            $paths[] = $path;
        echo json_encode($paths);
    }
}
$conn->close();
?>
