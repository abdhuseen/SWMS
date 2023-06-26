<?php

//In the calculateDistance method, you pass the latitude and longitude values of two points (e.g., your current location and the marker's location), and it will return the distance between them in kilometers.

//To find the nearest marker, you would loop through a list of markers, calculate the distance between each marker and the specific latitude and longitude, and keep track of the minimum distance and corresponding marker.



function calculateDistance($lat1, $lon1, $lat2, $lon2) {
    // Convert latitude and longitude to radians
    $lat1Rad = deg2rad($lat1);
    $lon1Rad = deg2rad($lon1);
    $lat2Rad = deg2rad($lat2);
    $lon2Rad = deg2rad($lon2);

    // Calculate the differences between the coordinates
    $latDiff = $lat2Rad - $lat1Rad;
    $lonDiff = $lon2Rad - $lon1Rad;

    // Earth's radius in kilometers
    $earthRadius = 6371;

    // Apply Haversine formula
    $a = sin($latDiff / 2) * sin($latDiff / 2) +
        cos($lat1Rad) * cos($lat2Rad) *
        sin($lonDiff / 2) * sin($lonDiff / 2);
    $c = 2 * atan2(sqrt($a), sqrt(1 - $a));
    $distance = $earthRadius * $c;

    return $distance;
}



//connection parameters
$serverName="localhost";
$userName="root";
$password="abd123456";
$db_name="smartwaste";

//----------------------------------------------

//parameters to get data from android


$TId=$_POST["TId"];//truck id

$driverLatitude=$_POST["latitude"];//current latitude 32.020255

$driverLongitude =$_POST["longitude"];//current longitude 35.838976

//----------------------------------------



//create connection


$conn=new mysqli($serverName,$userName,$password,$db_name);//connection object

// check connection state 



if($conn->connect_error)
{
     //error in connection
    //die
}



else
{
    
    
    //connection sucessfully
    
    //get all nodes 
    
    $sqlStatement="SELECT * FROM `node`";
    
    $result=$conn->query($sqlStatement);
    
   
    
    
    if($result->num_rows>0)
    {
       
        while($row=$result->fetch_assoc())
        {
          
            $node= array('id' =>$row["nid"] , 'latitude' =>$row["nlatitude"] , 'longitude' =>$row["nlongitude"],'street' =>$row["nstreet"]);
            $nodes[]=$node;
        }
        
    }
    
 
    
   
      //Find the nearest markers
    
    
    $minDistance = 2;//in killo
    
    
    
    foreach ($nodes as $marker)
    {
    $distance = calculateDistance(
        $driverLatitude, $driverLongitude, $marker['latitude'], $marker['longitude']
    );

    if ($distance <= $minDistance)
    {
        $nearestmarker= array('id' =>$marker["id"] ,'latitude' =>$marker['latitude'] , 'longitude' =>$marker['longitude'],'street' =>$marker["street"] );
        
        $nearestMarkers[]=$nearestmarker;
    }
    
}
    
   //echo json_encode($nearestMarkers); 
    
    
    //-----------------------------------------------------------------
    
      // get nearest filled nodes (summation of bins state at node/number of bins)>=1
    
    
    
    
    
    foreach ($nearestMarkers as $marker2)
    {
    
          
     // avg of bins in node
         
    $sqlStatement2="SELECT avg(bstate) as avg from bin,node WHERE node.nlatitude={$marker2['latitude'] }and node.nlongitude={$marker2['longitude']} and bin.f_nid=node.nid";


   $result2=$conn->query($sqlStatement2); 
   
          
    if($result2->num_rows>0){
        
        
         while($row=$result2->fetch_assoc())
        {
            $nodeAverage=$row["avg"];
             
        }
        
      
        
        
        if($nodeAverage>=0.1)
        {
            
            $nearest_filled_nodes[]=$marker2;
        }
        
        
    }
          

          
    
}
    
    
   // echo json_encode($nearest_filled_nodes);
    
    
    
    
    
    
    //--------------------------------------------------------
    
 //echo json_encode($nearest_filled_nodes);  

    
//get truck capacity
        
    $sqlStatement3="SELECT * FROM truck where tid=$TId";
    $result3=$conn->query($sqlStatement3);
    if($result3->num_rows>0)
    {
        while($row=$result3->fetch_assoc())
        {
            
            $truck_capacity=$row["tcapacity"];
        }
        
        //echo "$truck_capacity";
    } 
    
    
    
    //get rooting nodes trip path 
    
    $counter=0;
    
    foreach($nearest_filled_nodes as $filledNode){
        
    
    //number of filed bins in node    
        
    $sqlStatement4="SELECT COUNT(bid) as'numOfFilled' from bin,node WHERE bstate>=1 AND node.nlatitude={$filledNode['longitude']} and node.nlongitude={$filledNode['longitude']} and bin.f_nid=node.nid";
    $result4=$conn->query($sqlStatement4);
    if($result4->num_rows>0)
    {
        while($row=$result4->fetch_assoc())
        {
            
            $numberOfFilledBins=$row["numOfFilled"];
        }
        
        //compare numberOfFilledBins for the node 
        
        //if it less than or equal reminding space (truck_capacity-counter)
        
        //counter number of filled bins for previous rooting nodes
        
        //then the node accepted 
        
        if($numberOfFilledBins<=($truck_capacity-$counter)){
            
            $rootingNodes[]=$filledNode;
            $counter+=$numberOfFilledBins;
        }
        
        
        
        
       
    }
        
        
        
    }
    
    echo json_encode($rootingNodes); 
    
    
    
}
$conn->close();
?>

