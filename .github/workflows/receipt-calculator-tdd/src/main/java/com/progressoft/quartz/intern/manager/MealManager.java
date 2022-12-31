package com.progressoft.quartz.intern.manager;


import com.progressoft.quartz.intern.exception.MealException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface MealManager {

    ArrayList<Meal> getMealsById(HashMap<String, Integer> mealsIdWithQuantity, HashMap<String, Meal> meals) throws MealException;
    Object getMealsSize();
    boolean isMealRegistered(String id);
    Object getMeals();
    void saveMeal(Meal meal) throws MealException, IOException;
    Meal getMeal(String id) throws MealException;
    void removeMeal(Meal meal) throws MealException, IOException;

    void loadMealsFromFile(String filePath) throws MealException;



}
