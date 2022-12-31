package com.progressoft.quartz.intern.utility;

import com.progressoft.quartz.intern.exception.MealException;
import com.progressoft.quartz.intern.manager.Meal;
import com.progressoft.quartz.intern.manager.MealManager;

import java.io.*;
import java.util.*;

public class MealServices implements MealManager {
    final private String filePath="C:\\Users\\ibrah\\Downloads\\tests\\receipt-calculator-tdd\\src\\test\\resources\\sample\\testTosave";
    ArrayList<Meal> mealArrayList = new ArrayList<>();
    @Override

    public ArrayList<Meal> getMealsById(HashMap<String, Integer> mealsIdWithQuantity, HashMap<String, Meal> meals) throws MealException {
        ArrayList<Meal> mealList =new ArrayList<>();
        if (mealsIdWithQuantity == null ){
            throw new MealException("Meals Id With Quantity must not be null");
        }
        if (meals == null){
            throw new MealException("Meals must not be null");
        }
        for (Map.Entry<String,Integer> set: mealsIdWithQuantity.entrySet()) {
            Meal meal;
            String id = set.getKey();
            if (!meals.containsKey(id)) {
                throw new MealException("Meal with id " + id + " is not found");
            }
            meal = meals.get(id);
            meal.setQuantity(set.getValue());
            mealList.add(meal);
        }
        return mealList;
    }
    @Override
    public Object getMealsSize() {
        return mealArrayList.size();
    }

    @Override
    public boolean isMealRegistered(String id) {
       return false;
    }

    @Override
    public HashMap<String,Meal> getMeals() {
        HashMap<String,Meal> meals = new HashMap<>();
        for (Meal meal : mealArrayList) {
            meals.put(meal.getId(), meal);
        }
        return meals;
    }

    @Override
    public void saveMeal(Meal meal) throws MealException, IOException {
        if(meal == null){
            throw new MealException("Meal must not be null");
        }
        if (meal.getId() == null){
            throw new MealException("Meal ID must not be null");
        }
        ReadAndWriteToFile.writeMealsToFile(mealArrayList,meal,"C:\\Users\\ibrah\\Downloads\\tests\\receipt-calculator-tdd\\src\\test\\resources\\sample\\testesttestetest");
    }

    @Override
    public Meal getMeal(String id) throws MealException {
        for (int i =0;i< mealArrayList.size();i++){
            if (mealArrayList.get(i).getId().equals(id)){
                return mealArrayList.get(i);
            }
        }throw new MealException("Meal with ID "+ id+" is not registered");
    }

    @Override
    public void removeMeal(Meal meal) throws MealException, IOException {
        String filePathForThisFunction = "C:\\Users\\ibrah\\Downloads\\tests\\receipt-calculator-tdd\\src\\test\\resources\\sample\\testesttestetest";
        if(meal == null){
            throw new MealException("Meal must not be null");
        }
        if (meal.getId() == null){
            throw new MealException("Meal ID must not be null");
        }
        loadMealsFromFile(filePathForThisFunction);
        if (mealArrayList.size()==0)
            throw new MealException("Meal with ID "+ meal.getId() +" is not registered");

        for (int i =0;i< mealArrayList.size();i++){
           if (!mealArrayList.get(i).getId().equals(meal.getId())){
               throw new MealException("Meal with ID "+ meal.getId() +" is not registered");
           }
        }
        for(int i = 0;i < mealArrayList.size();i++) {
            if (meal.getId().equals(mealArrayList.get(i).getId())) {
                mealArrayList.remove(i);
            }
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(filePathForThisFunction));
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePathForThisFunction))){
        String line;
        while ((line = reader.readLine())!=null) {
            if (!line.startsWith(meal.getId())){
                writer.write(line);
                writer.newLine();
            }
        }
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void loadMealsFromFile(String filePath) throws MealException {
        if (Objects.isNull(filePath)){
            throw new MealException("Path must not be null");
        }
        mealArrayList =new ArrayList<>();
        ReadAndWriteToFile.readMealsFromFile(filePath,mealArrayList);
    }
}