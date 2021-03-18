package com.example.healthmonitor;

class User
{
    String email;
    String password;
    String food1;
    String food2;

    User()
    {
        email = "";
        password = "";
        food1 = "";
        food2 = "";
    }

    User(String email, String password, String food1, String food2)
    {
        this.email = email;
        this.password = password;
        this.food1 = food1;
        this.food2 = food2;
    }

}