<?php
$serverName="localhost";
$userName="root";
$password="abd123456";
$db_name="smartwaste";
$DId=$_POST["DId"];//driver id
$Dpass=$_POST["Dpass"];//driver password
$conn=new mysqli($serverName,$userName,$password,$db_name);//connection object
if($conn->connect_error){
     //error in connection
    //die
}else{
    //connection sucessfully
    $sqlStatement="select * from driver where did =$DId and dpassword='$Dpass'";
    $result=$conn->query($sqlStatement);
    if($result->num_rows>0){
        //there is data
        while($row=$result->fetch_assoc()){
            $driver["id"]=$row["did"];
            $driver["name"]=$row["dname"];
            $driver["ssn"]=$row["dssn"];
            $driver["birthDate"]=$row["ddateofbirth"];
            $driver["salary"]=$row["dsalary"];
            $driver["address"]=$row["daddress"];
            $driver["phone"]=$row["dphone"];
            $driver["password"]=$row["dpassword"];
            $driver["driverqueryresult"]=true;
            $drivers[]=$driver;
        }
        echo json_encode($drivers);
    }else{
            $driver["id"]=0;
            $driver["name"]="empty";
            $driver["ssn"]="empty";
            $driver["birthDate"]="empty";
            $driver["salary"]="empty";
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