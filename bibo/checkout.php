<?php
	require "ini.php";
	//////////////////////////

	$custID = 0; //Default value

	$sql = "SELECT * FROM orders ORDER BY orderID DESC LIMIT 1";
	$result = mysqli_query($conn,$sql);

	if (mysqli_num_rows($result) > 0){
		$row = mysqli_fetch_assoc($result);
		$custID = $row['customerID'];
		$status = "ok";
	}else{
		$status = "error";
	}

	$total = 0.0; //Default Value
	$bookArrTmp = array();
	$qtyArrTmp = array();
	$priceArrTmp = array();
	$subTotalArrTmp = array();

	////////////////////////////////////////////////////
	$sql = "SELECT * FROM orders ORDER BY orderID DESC";
	$result = $conn->query($sql);
	if ($result->num_rows > 0) {
	    // output data of each row
	    $i = 0;
    	while($row = $result->fetch_assoc()) {
    		if ($row["customerID"] == $custID){
    			// echo "orderID: " . $row["orderID"]. " - CustomerID: " . $row["customerID"]. " " . $row["bookName"]. "<br>";
    			$bookArrTmp[$i] = $row['bookName'];
    			$qtyArrTmp[$i] = $row['qty'];
    			$priceArrTmp[$i] = $row['price'];
    			$subTotalArrTmp[$i] = $row['subTotal'];
    			$total += $row['subTotal'];
    			$i++; //Increasing
    		}else{
    			$i = 0; //Reset
    			break;
    		}
    	}
	   
	} else {
	    echo "0 results";
	}


	$b = array_reverse($bookArrTmp);
	$s = $b[0];
	echo $s;

	//Reverse value of array
	// $bookName = array_reverse($bookArrTmp);
	// $qty = array_reverse($qtyArrTmp);
	// $price = array_reverse($priceArrTmp);
	// $subTotal = array_reverse($subTotalArrTmp);
	// echo $total;
	// echo "<br>";
	// echo "********************";
	// echo "<br>";
	// print_r($bookName);
	// echo "<br>";
	// print_r($qty);
	// echo "<br>";
	// print_r($price);
	// echo "<br>";
	// print_r($subTotal);
?>