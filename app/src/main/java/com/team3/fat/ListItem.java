package com.team3.fat;

public class ListItem {
    private String date;
    private float weight;

    public float getWeight() {
        return weight;
    }

    public String getWeight_String(){
        return Float.toString(weight);
    }
    public void setWeight(float MyWeight) {
        this.weight = MyWeight;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String Date) {
        this.date = Date;
    }

}