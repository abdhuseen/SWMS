<?php
$serverName="localhost";
$userName="root";
$password="abd123456";
$db_name="smartwaste";
$tId=$_POST["tId"];//truckid
$tisused=$_POST["tisused"];
$conn=new mysqli($serverName,$userName,$password,$db_name);//connection object
if($conn->connect_error){
     //error in connection
    //die
}else{
    //connection sucessfully
    $sqlStatement="update truck set tisused=$tisused where tid =$tId";
    $result=$conn->query($sqlStatement);
    if($result===true){
        //there is data
            $truck["tid"]=$tId;
            $truck["tnumber"]=-1;
            $truck["tplate"]="";
            $truck["tcapacity"]=0;
            $truck["tstate"]=-1;
            $truck["tisused"]=$tisused;
            $truck["queryresult"]=true;
            $trucks[]=$truck;
        echo json_encode($trucks);
    }else{
            $truck["tid"]=0;
            $truck["tnumber"]=0;
            $truck["tplate"]="empty";
            $truck["tcapacity"]=0;
            $truck["tstate"]=-1;
            $truck["tisused"]=-1;
            $truck["queryresult"]=false;
            $trucks[]=$truck;
        echo json_encode($trucks);
    }
}
$conn->close();
?>