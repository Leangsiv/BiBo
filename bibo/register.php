<?php
	require "ini.php";
	$fname = $_GET['fname'];
	$lname = $_GET['lname'];
	$username = $_GET['uname'];
	$user_password = $_GET['user_password'];
	$email = $_GET['email'];
	$phoneNum = $_GET['phoneNum'];

	$sql = "select * from employees where username = '$username'";

	$result = mysqli_query($conn,$sql);

	if (mysqli_num_rows($result) > 0){
		$status = "exist";
	}else{
		$sql = "insert into employees(firstname,lastname,username,password,email,phoneNumber) values('$fname','$lname','$username','$user_password','$email','$phoneNum')";

		if (mysqli_query($conn,$sql)){
			$status = "ok";
		}else{
			$status = "error";
		}
	}

	echo json_encode(array("response"=>$status));

	//mysqli_close($conn);



?>