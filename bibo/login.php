<?php
	require "ini.php";

	$username = $_GET["username"];
	$user_password = $_GET["password"];

	$sql = "select * from employees where username = '$username' and password = '$user_password'";

	$result = mysqli_query($conn,$sql);

	if (!mysqli_num_rows($result) > 0){
		$status = "failed";
		echo json_encode(array("response"=>$status));
	}else{
		$row = mysqli_fetch_assoc($result);
		$uname = $row['username'];
		$status = 'ok';
		echo json_encode(array("response"=>$status,"username"=>$uname));
	}

	mysqli_close($conn);
?>