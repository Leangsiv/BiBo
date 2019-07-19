<?php
    require "ini.php";

    $publisherName = $_GET["publisherName"];
    $title = $_GET["title"];
    $genre = $_GET["genre"];
    $publication_year = $_GET["publication_year"];
    $quantity = $_GET["quantity"];
    $price = $_GET["price"];

    $bID = 0; //Default value
    //Get some info from BOOKS
    $sqlBook = "SELECT * FROM books ORDER BY bookID DESC";
    $resultBook = mysqli_query($conn,$sqlBook);
    if (mysqli_num_rows($resultBook) > 0){
        $row = mysqli_fetch_assoc($resultBook);
        $bID = $row['bookID'];
        $status = "ok";
    }else{
        $status = "error";
    }
    $bID++;
    //echo json_encode(array("response of Book"=>$status));
    //echo $bID;

    //echo "Show bID = ".$bID;

    $sqlPub = "INSERT INTO publishers(publisherName,bookID) VALUES('$publisherName','$bID');";
    if (mysqli_query($conn,$sqlPub)){
        $status = "ok";
    }else{
        $status = "error";
    }
    //echo json_encode(array("response of publisher"=>$status));
    //Get ID from publisher
    $publisherID = 0;
    //Get some info from BOOKS
    $sqlBook = "SELECT * FROM publishers ORDER BY publisherID DESC";
    $resultBook = mysqli_query($conn,$sqlBook);
    if (mysqli_num_rows($resultBook) > 0){
        $row = mysqli_fetch_assoc($resultBook);
        $publisherID = $row['publisherID'];
        $status = "ok";
    }else{
        $status = "error";
    }
    //echo json_encode(array("response of getting pID"=>$status));
    //echo "Show publisherID = ".$publisherID;

    //Insert into Books
    $sqlB = "INSERT into books(publisherID,title, genre, publication_year, quantity, price) values ('$publisherID','$title','$genre','$publication_year','$quantity','$price')";

    //$result = mysqli_query($conn,$sql);
 		if (mysqli_query($conn,$sqlB)) {
 			$status = "ok";
 		}
 		else{
 			$status = "error";
 		}
    
    echo json_encode(array("response"=>$status));
?>
