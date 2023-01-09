package com.progressoft.quartz.intern.utility;

import com.progressoft.quartz.intern.exception.MealException;
import com.progressoft.quartz.intern.manager.Meal;
import com.progressoft.quartz.intern.manager.MealManager;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.*;

public class MealServices implements MealManager {


    // TODO: remove unused filePath
    // Done
    // TODO: make mealArrayList as private
    // Done
    private ArrayList<Meal> mealArrayList = new ArrayList<>();
    @Override

    public ArrayList<Meal> getMealsById(HashMap<String, Integer> mealsIdWithQuantity, HashMap<String, Meal> meals){
        ArrayList<Meal> mealList =new ArrayList<>();
        if (mealsIdWithQuantity == null ){
            throw new MealException("Meals Id With Quantity must not be null");
        }
        if (meals == null){
            throw new MealException("Meals must not be null");
        }
        // TODO: an easier way, you can use forEach with the help of lambda, read about lambda :)
        //  example:
        //        mealsIdWithQuantity.forEach((key, value) -> {
        //            // key is the id (the `key` takes the left diamond value of HashMap<String, Integer>)
        //            // value is the quantity (the `value` takes the right diamond value of HashMap<String, Integer>)
        //            // note that the (key, value) can be renamed like forEach((id, quantity)->{})
        //            // business
        //        });
        // Done

        mealsIdWithQuantity.forEach((id, quantity) -> {
            Meal meal;
            if (!meals.containsKey(id)) {
                throw new MealException("Meal with id " + id + " is not found");
            }
            meal = meals.get(id);
            meal.setQuantity(quantity);
            mealList.add(meal);
        });


        return mealList;
    }
    @Override
    public Object getMealsSize() {
        return mealArrayList.size();
    }

    @Override
    public boolean isMealRegistered(String id) {
        for (int i =0;i< mealArrayList.size();i++){
            if (mealArrayList.get(i).getId().equals(id)){
                return true;
            }
        }
        throw new MealException("Meal with ID "+ id +" is not registered");
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
    public void saveMeal(Meal meal) {
        try {


            if (meal == null) {
                throw new MealException("Meal must not be null");
            }

            if (meal.getId() == null) {
                throw new MealException("Meal ID must not be null");
            }

            if (meal.getId().isEmpty()) {
                throw new MealException("Meal id can not be empty");
            }

            for (int i = 0; i < mealArrayList.size(); i++) {
                if (mealArrayList.get(i).getId().equals(meal.getId())) {
                    throw new MealException("Meal with id " + meal.getId() + " already registered");
                }
            }

            if (meal.getName().isEmpty()) {
                throw new MealException("Meal name can not be empty");
            }

            if (meal.getDescription().isEmpty()) {
                throw new MealException("Meal description can not be empty");
            }
            try {
                if (Double.isNaN(meal.getPrice())) {
                    throw new MealException("Meal price can not be empty");
                }

            } catch (NumberFormatException e) {
                throw new MealException("Invalid Price Format");
            }

            // TODO: you are hard coding the path, read about NIO (example classes: Path - Files - Paths)
            //  you can use NIO to read and write to files easier than the normal JAVA IO
            // Done
            Path filePath = Paths.get("src/test/resources/sample/testesttestetest");
            ReadAndWriteToFile.writeMealsToFile(mealArrayList, meal, String.valueOf(filePath));
        }
        catch (IOException e){
            throw new MealException("Error 404 xD");
        }
    }

    @Override
    public Meal getMeal(String id)  {
        try {


        for (int i =0;i< mealArrayList.size();i++){
            if (mealArrayList.get(i).getId().equals(id)){
                return mealArrayList.get(i);
            }
        }throw new MealException("Meal with ID "+ id+" is not registered");
        }
        catch (MealException e){
            throw new MealException("Meal with ID 1 is not registered");
        }
    }

    @Override
    public void removeMeal(Meal meal) {
        try {
            // TODO: you are hard coding the path, read about NIO (example classes: Path - Files - Paths)
            //  you can use NIO to read and write to files easier than the normal JAVA IO
            // Done
            Path filePath = Paths.get("src/test/resources/sample/testesttestetest");

            if (meal == null) {
                throw new MealException("Meal must not be null");
            }
            if (meal.getId() == null) {
                throw new MealException("Meal ID must not be null");
            }
            if (mealArrayList.size() == 0)
                throw new MealException("Meal with ID " + meal.getId() + " is not registered");

            // TODO: you are only checking the first value of the mealArrayList, so any other values added, it will always throw an exception
            //  which means you can not register more than 1 meal :'(
            //  why dont you move the validation to a function and reverse the validation?
            //  example:
            //  private void isMealRegistered(Meal meal) throws MealException {
            //        for (int i =0;i< mealArrayList.size();i++){
            //           if (mealArrayList.get(i).getId().equals(meal.getId())){
            //               return;
            //           }
            //        }
            //       throw new MealException("Meal with ID "+ meal.getId() +" is not registered");
            //    }
            //  this way it will loop over all IDs, once it finds the correct ID, it returns
            //  if the ID is not found in the whole arrayList, it will throw an exception
            // Done
            isMealRegistered(meal.getId());
            for (int i = 0; i < mealArrayList.size(); i++) {
                if (!mealArrayList.get(i).getId().equals(meal.getId())) {
                    throw new MealException("Meal with ID " + meal.getId() + " is not registered");
                }
            }
            // TODO: it is not a good idea to remove something from an arrayList you are currently looping
            //  after removing, use break to get out of the for loop, to not cause issues
            // Done
            for (int i = 0; i < mealArrayList.size(); i++) {
                if (meal.getId().equals(mealArrayList.get(i).getId())) {
                    mealArrayList.remove(i);
                    break;
                }
            }

            // TODO: read about NIO, can replace this code with 2 lines :)
            // Done
            // Sorry, I couldn't do that with only two lines :(
            try {
                List<String> lines = Files.readAllLines(filePath);
                List<String> modifiedLines = new ArrayList<>();
                for (String line : lines) {
                    if (!line.startsWith(meal.getId())) {
                        modifiedLines.add(line);
                    }
                }
                Files.write(filePath, modifiedLines);
            } catch (Exception e) {
                throw e;
            }
        }catch (IOException e){

        }
    }

    @Override
    public void loadMealsFromFile(String filePath) {
        if (Objects.isNull(filePath)){
            throw new MealException("Path must not be null");
        }
        mealArrayList =new ArrayList<>();
        ReadAndWriteToFile.readMealsFromFile(filePath,mealArrayList);
    }
}