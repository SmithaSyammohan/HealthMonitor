package com.example.healthmonitor;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Scientist  implements Serializable {

    private String mId,Food,Measure,Calories,Grams,Protein,Fat,Carbs,Fib,key,Category;

    public Scientist() {
        //empty constructor needed
    }
    public Scientist(String Food,String Measure,String Calories,String Grams, String Protein, String Carbs, String Fat, String Fib, String Category ) {
        if (Food.trim().equals("")) {
            Food = "Food NoName";
        }

        this.Food = Food;
        this.Measure = Measure;
        this.Calories = Calories;
        this.Grams = Grams;
        this.Protein = Protein;
        this.Fat = Fat;
        this.Carbs = Carbs;
        this.Fib = Fib;
        this.Category = Category;
    }


    public String getId() {
        return mId;
    }
    public void setId(String id) {
        mId = id;
    }
    public String getFood() {
        return Food;
    }
    public void setFood(String Food) {
        this.Food = Food;
    }
    public String getMeasure() {
        return Measure;
    }
    public void setMeasure(String Measure) {
        this.Measure = Measure;
    }
    public String getCalories() {
        return Calories;
    }
    public void setCalories(String Calories) {
        this.Calories = Calories;
    }

    public String getGrams() {
        return Grams;
    }

    public void setGrams(String Grams) {
        this.Grams = Grams;
    }

    public String getProtein() {
        return Protein;
    }

    public void setProtein(String Protein) {
        this.Protein = Protein;
    }

    public String getFat() {
        return Fat;
    }

    public void setFat(String Fat) {
        this.Fat = Fat;
    }

    public String getCarbs() {
        return Carbs;
    }

    public void setCarbs(String Carbs) {
        this.Carbs = Carbs;
    }

    public String getFib() {
        return Fib;
    }

    public void setFib(String Fib) {
        this.Fib = Fib;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }


    @Override
    public String toString() {
        return getFood();
    }
    @Exclude
    public String getKey() {
        return key;
    }
    @Exclude
    public void setKey(String key) {
        this.key = key;
    }
}
//end
