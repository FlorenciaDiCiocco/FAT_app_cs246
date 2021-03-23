package com.team3.fat;

import java.util.ArrayList;

public class Globals {
    public static Users _user = new Users();

    // Getter userEmail
    public static String getuserEmail() {
        return _user.userEmail;
    }

    // Setter userEmail
    public static void setuserEmail(String newuserEmail) {
        _user.userEmail = newuserEmail;
    }

    // Getter userpassword
    public static String getpassword() {
        return _user.password;
    }

    // Setter userpassword
    public static void setuserpassword(String newpassword) {
        _user.password = newpassword;
    }

    // Getter list_of_input
    public static ArrayList get_weight_list(){
        return _user.list_of_input;
    }

    //setter a new input
    public static void set_weight (ListItem item){
        _user.list_of_input.add(item);
    }
}

