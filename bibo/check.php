<?php
	require "ini.php";

	$bID = $_GET['bID'];
	$title = "";
	$genre = "";
	$pYear = "";
	$qty = "";
	$price = "";

	$sql = "SELECT * from books where BookID = '$bID'";
	$result = mysqli_query($conn,$sql);

	if (mysqli_num_rows($result) > 0){
		$row = mysqli_fetch_assoc($result);

		$title = $row['title'];
		$genre = $row['genre'];
		$pYear = $row['publication_year'];
		$qty = $row['quantity'];
		$price = $row['price'];

		$status = "ok";
	}else{
		$status = "error";
	}
	// echo $bID."<br>";
	// echo $title."<br>";
	// echo $genre."<br>";
	// echo $pYear."<br>";
	// echo $qty."<br>";
	// echo $price."<br>";

	echo json_encode(array("response"=>$status,
							"bID"=>$bID,
							"title"=>$title,
							"genre"=>$genre,
							"pYear"=>$pYear,
							"qty"=>$qty,
							"price"=>$price));
	//mysqli_close($conn);
?>