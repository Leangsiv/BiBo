<?php
	require "ini.php";

	//Declare Datatype
	$bNameOrID = $_GET['bNameOrID'];
	$qty = $_GET['qty'];
	$cNumber = ""; //Default Value
	$cName = ""; //Default Value
	$custID = 0; //Default Value
	$price = 0.1; //Default Value
	$subTotal = 0; //Default Value
	$custID = 0; //Default Value

	$sql = "SELECT * FROM orders ORDER BY orderID DESC LIMIT 1";
	$result = mysqli_query($conn,$sql);

	if (mysqli_num_rows($result) > 0){
		$row = mysqli_fetch_assoc($result);
		$cName = $row['customerName'];
		$custID = $row['customerID'];
		$status = "ok";
	}else{
		$status = "error";
	}
	// echo "Show response find Customer Name and ID : ";
	// echo json_encode(array("response"=>$status));
	// echo "<br>";

	//////GET from export.php
	/////////////// Insert into CUSTOMERS ///////////////
	$sqlCust = "select * from customers where customerID = '$custID'";
	$resultCust = mysqli_query($conn,$sqlCust);
	
	if (mysqli_num_rows($resultCust) > 0){
		$row = mysqli_fetch_assoc($resultCust);
		//Get the value from customer qty
		$qtyBookForCust = $row['amount_of_book'];
		$cNumber = $row['phoneNumber'];

		//Update qty
		$sumQtyForCust = $qtyBookForCust + $qty;
		$sqlCust = "UPDATE customers SET amount_of_book = '$sumQtyForCust' WHERE amount_of_book = '$qtyBookForCust' and phoneNumber = '$cNumber'";

		//Put it inorder
		if (mysqli_query($conn,$sqlCust)){	
			$sqlCust = "select * from customers where customerID = 1";
			$resultCust = mysqli_query($conn,$sqlCust);
			$row = mysqli_fetch_assoc($resultCust);
			$customerID = $row['customerID'];
			$sqlCust = "UPDATE customers SET customerID = '$customerID' ORDER BY customerID DESC";
			$status = "ok";//Show status	
		}else{
			$status = "error";
		}
		///////////////////////////////////////////////
	}else{
		$sqlCust = "insert into customers(CustName,phoneNumber,amount_of_book) values('$cName','$cNumber','$qty')";

		if (mysqli_query($conn,$sqlCust)){
			$sqlCust = "select * from customers where phoneNumber = '$cNumber'";
			$resultCust = mysqli_query($conn,$sqlCust);
			$row = mysqli_fetch_assoc($resultCust);
			$custID = $row['customerID']; //get customer ID
			$status = "ok";
		}else{
			$status = "error";
		}
	}
	// echo "Show response in CUSTOMERS table : ";
	// echo json_encode(array("response"=>$status));

	/////////////// Insert into ORDERS ///////////////

	//Declare Current Date
	date_default_timezone_set('Asia/Bangkok');
	$date = date('m/d/Y h:i:s', time());

	//Default info of BOOKS
	$bID = '0';
	$bName = "0";
	//Get some info from BOOKS
	$sqlBook = "SELECT * FROM books WHERE bookID = '$bNameOrID' OR title = '$bNameOrID'";
	$resultBook = mysqli_query($conn,$sqlBook);
	if (mysqli_num_rows($resultBook) > 0){
		$row = mysqli_fetch_assoc($resultBook);

		$bID = $row['bookID'];
		$bName = $row['title'];
		$price = $row['price'];

		//Update Quantity of book
		$qtyBook = $row['quantity'];
		$sumQty = $qtyBook - $qty;

		$sqlBook = "UPDATE books SET quantity = '$sumQty' WHERE quantity = '$qtyBook' AND bookID = '$bID'";
		if (mysqli_query($conn,$sqlBook)){
			//echo "Update qty book success";
		}else{
			//echo "Update failed";
		}
	}

	//Calculate SubTotal
	$subTotal = $qty * $price;

	$sqlOrder = "INSERT INTO orders(orderDate,customerID,customerName,bookID,bookName,qty,price,subTotal) VALUES('$date','$custID','$cName','$bID','$bName','$qty','$price','$subTotal');";
	if (mysqli_query($conn,$sqlOrder)){
		$status = "ok";
	}else{
		$status = "error";
	}
	//echo "Show response in ORDERS table : ";
	echo json_encode(array("response"=>$status));



?>