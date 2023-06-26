<?php
$serverName="localhost";
$userName="root";
$password="abd123456";
$db_name="smartwaste";
$conn=new mysqli($serverName,$userName,$password,$db_name);//connection object
if($conn->connect_error){
     //error in connection
    //die
}else{
    //connection sucessfully
    $sqlStatement="select * from truck where tstate=1 and tisused=0";
    $result=$conn->query($sqlStatement);
    if($result->num_rows>0){
        //there is data
        while($row=$result->fetch_assoc()){
            $truck["tid"]=$row["tid"];
            $truck["tnumber"]=$row["tnumber"];
            $truck["tplate"]=$row["tplate"];
            $truck["tcapacity"]=$row["tcapacity"];
            $truck["tstate"]=$row["tstate"];
            $truck["tisused"]=$row["tisused"];
            $truck["queryresult"]=true;
            $trucks[]=$truck;
        }
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