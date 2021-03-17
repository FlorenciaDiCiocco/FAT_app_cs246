package com.team3.fat;

public class ListItem {
    private String date;
    private float weight;
    private String weight_type = "pound";


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

    public void convertWeight(){
        switch(weight_type){
            case "pound":
                calcKiloToPound();
                break;
            case "kilo":
                calcPoundToKilo();
                break;
        }
    }
    public void calcPoundToKilo() {
        this.weight = (float) (this.weight * 0.45359237);
    }

    public void calcKiloToPound() {
        this.weight = (float) (this.weight * 2.2046226218);
    }
}