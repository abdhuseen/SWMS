<?php
$serverName="localhost";
$userName="root";
$password="abd123456";
$db_name="smartwaste";


$did=$_POST["dId"];

$dname=$_POST["dname"];

$dssn=$_POST["dssn"];

$dBdate=$_POST["dBdate"];

$dSalary=$_POST["dSalary"];

$dCity=$_POST["dCity"];

$dPhone=$_POST["dPhone"];

$dPassword=$_POST["dPassword"];


$conn=new mysqli($serverName,$userName,$password,$db_name);//connection object
if($conn->connect_error){
     //error in connection
    //die
}else{
    //connection sucessfully
    $sqlStatement="update driver set dname='$dname',dssn='$dssn',ddateofbirth='$dBdate',
    daddress='$dCity',dphone='$dPhone' where did =$did";
    $result=$conn->query($sqlStatement);
    if($result===true){
        //there is data
            $driver["id"]=$did;
            $driver["name"]="$dname";
            $driver["ssn"]="$dssn";
            $driver["birthDate"]="$dBdate";
            $driver["salary"]=$dSalary;
            $driver["address"]="$dCity";
            $driver["phone"]="$dPhone";
            $driver["password"]="$dPassword";
            $driver["driverqueryresult"]=true;
            $drivers[]=$driver;
        echo json_encode($drivers);
    }else{
            
            $driver["id"]=0;
            $driver["name"]="empty";
            $driver["ssn"]="empty";
            $driver["birthDate"]="empty";
            $driver["salary"]=0;
            $driver["address"]="empty";
            $driver["phone"]="empty";
            $driver["password"]="empty";
            $driver["driverqueryresult"]=false;
            $drivers[]=$driver;
        echo json_encode($drivers);
    }
}
$conn->close();
?>