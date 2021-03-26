package com.team3.fat;

import java.util.ArrayList;
import java.util.List;

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
        ArrayList<ListItem> middle = new ArrayList<>();
        middle.add(item);
        middle.addAll(_user.list_of_input);
        _user.list_of_input=middle;
    }

    // Getter weight_type
    public static Weight_type get_weight_type() {
        return _user.weight_type;
    }
    // Setter weight_type
    public static void set_weight_type(Weight_type newWeight_type) {
        _user.weight_type = newWeight_type;
    }

    // Getter Goal
    public static float get_Goal() {
        return _user.weight_goal;
    }

    // Setter Goal
    public static void set_Goal(float Goal) {
        _user.weight_goal = Goal;
    }

    //Switch function for list items, this is to be run inside a for loop for each item
    //public void convertWeight(ListItem item){
       // switch(_user.weight_type){
         //   case "kilo":
           //     calcKiloToPound(item);
         //       break;
           // case "pound":
           //     calcPoundToKilo(item);
           //     break;
       // }
   // }

    //functions for switch function
   // public void calcPoundToKilo(ListItem item) {
     //   item.weight = (float) (item.weight * 0.45359237);
    //}

    //functions for switch function
    //public void calcKiloToPound(ListItem item) {
    //    item.weight = (item.weight * 2.2046226218);
    //}

    //Toggle user's weight type
    public static void toggleWeightType(Users user){
        ArrayList<ListItem> user_list = user.list_of_input;
        //For loop to parse through user's weight list
        for(int i = 0; i < user_list.size(); i += 1){
            ListItem item = (ListItem) user_list.get(i);
            //Check for pounds to kilos
            if(user.weight_type == Weight_type.pounds) {
                item.weight = Math.round(item.weight * 0.45359237);
            }else{
                //For kilos to pounds
                item.weight = Math.round(item.weight * 2.2046226218);
            }
        }
    }

}

