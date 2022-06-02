package com.example.mma;

//Cesar Paredes - start/////////////////////////////////////////////////////////

//im create and user class here so we can pass the object of this class to the Firebase
//the user object will pass all its attributes to the firebase database

public class User {

    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public String email;
    public String membership;
    public String schedule;


    //a default constructor will allow me to create an object without initializing anything
    public User(){

    }

    //this constructor will initialize all of our variables
    public User(String username, String password, String firstName, String lastName, String email, String membership, String schedule){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.membership = membership;
        this.schedule = schedule;
    }
}
