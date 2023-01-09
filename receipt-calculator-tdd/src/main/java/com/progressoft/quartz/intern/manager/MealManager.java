package com.progressoft.quartz.intern.manager;


import com.progressoft.quartz.intern.exception.MealException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface MealManager {

    // TODO: Editing the Interfaces automatically renders the task as an invalid task (Rule 1)
    //  therefore, i will check your clean code and give you some tips, as a new developer keep in mind that when you are given a task and the rules are broken they will not
    //  take you into consideration, However, do not take this as a negative comment as it is just a guide for you for future references :)
    // Done

    ArrayList<Meal> getMealsById(HashMap<String, Integer> mealsIdWithQuantity, HashMap<String, Meal> meals);
    Object getMealsSize();
    boolean isMealRegistered(String id);
    Object getMeals();
    void saveMeal(Meal meal);
    Meal getMeal(String id);
    void removeMeal(Meal meal);

    void loadMealsFromFile(String filePath);



}
