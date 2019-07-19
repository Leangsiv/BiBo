<?php
	require "ini.php";

	// $sqlCust = "select * from customers where CustName = 'siv'";

	// $result = mysqli_query($conn,$sqlCust);

	// $row = mysqli_fetch_assoc($result);
	// 	$qty = $row['amount_of_book'];
	// 	echo $qty;

	//Current Date
	// date_default_timezone_set('Asia/Bangkok');
	// $date = date('m/d/Y h:i:s', time());
	// //echo $date;

	// $title = "Python";
	// $genre = "language";
	// $publication_year = "08/23/2019";
	// $qty = 12;
	// $price = 25.5;

/*
	$sql = "INSERT INTO books(publisherID,title,genre,publication_year,quantity,price) VALUES(1,'$title','$genre','$publication_year','$qty','$price')";

	if (mysqli_query($conn,$sql)){
			$status = "ok";
		}else{
			$status = "error";
		}

	echo json_encode(array("response"=>$status));
*/

	$publisherName = "hone";
	$bID = 4;
	
	// $sqlPub = "INSERT INTO publishers(publisherName,bookID) VALUES('$publisherName','$bID')";

	$sqlB = "INSERT into books(publisherID,title, genre, publication_year, quantity, price) values ('$publisherID',$title','$genre','$publication_year','$quantity','$price')";

    //$result = mysqli_query($conn,$sql);
 		if (mysqli_query($conn,$sqlB)) {
 			$status = "ok";
 		}
 		else{
 			$status = "error";
 		}
    
    echo json_encode(array("response insert Book"=>$status));

		
	
	
	// $arr =array();
	// $arr[0] = "hello";
	// $arr[1] = "hi";
	// $newArr = array_reverse($arr);
	// print_r($newArr);

	// $sqlOrder = "INSERT INTO orders(orderDate,customerID,customerName,bookID,bookName,qty,price,subTotal) VALUES('$date','1','ngon','1','Java',2,23.2,46.4);";
	// if(mysqli_query($conn,$sqlOrder)){
	// 	echo "ok";
	// }else{
	// 	echo "error";
	// }

?>