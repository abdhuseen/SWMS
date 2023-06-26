<?php
$serverName="localhost";
$userName="root";
$password="abd123456";
$db_name="smartwaste";
$bid=$_POST["bId"];//bin id
$z=0;
$conn=new mysqli($serverName,$userName,$password,$db_name);//connection object
if($conn->connect_error){
     //error in connection
    //die
}else{
    //connection sucessfully
    $sqlStatement="update bin set bstate=$z where bid =$bid";
    $result=$conn->query($sqlStatement);
    if($result===true){
        //there is data
            $bin["id"]=$bid;
            $bin["state"]=0;
            $bins[]=$bin;
        echo json_encode($bins);
    }else{
            $bin["id"]=0;
            $bin["state"]=-1;
            $bins[]=$bin;
        echo json_encode($bins);
    }
}
$conn->close();
?>