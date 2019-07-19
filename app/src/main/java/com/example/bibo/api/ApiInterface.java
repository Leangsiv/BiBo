package com.example.bibo.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("register.php")
        //User class
    Call<User> performRegistration(@Query("fname") String fname, @Query("lname") String lname, @Query("uname") String username,@Query("user_password") String userPassword,@Query("email") String email, @Query("phoneNum") String phoneNum);

    @GET("login.php")
    Call<User> performUserLogin(@Query("username") String username,@Query("password") String userPassword);

    @GET("export.php")
    Call<User> performExport(@Query("custName") String customerName,@Query("custNumber") String ph,@Query("bNameOrID") String bookNameOrID,@Query("qty") int qtyExport);

    @GET("addMore.php")
    Call<User> performAdding(@Query("bNameOrID") String bookNameOrID, @Query("qty") int qtyExport);

    @GET("import.php")
    Call<User> performImportation(@Query("publisherName") String publisherName,@Query("title") String title, @Query("genre") String genre, @Query("publication_year") String publication_year, @Query("quantity") int quantity, @Query("price") float price);

    @GET("check.php")
    Call<User> performChecking(@Query("bID") String bID);
}
