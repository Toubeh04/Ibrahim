package com.progressoft.quartz.intern.utility;

import com.progressoft.quartz.intern.exception.MealException;
import com.progressoft.quartz.intern.manager.Meal;
import org.codehaus.plexus.util.StringUtils;

import java.io.*;
import java.util.ArrayList;

public class ReadAndWriteToFile {
    public static void writeMealsToFile(ArrayList<Meal> mealArrayList, Meal meal, String filePath) throws IOException, MealException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.write("id,name,description,price\n");
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
            for (int i = 0; i < mealArrayList.size(); i++) {
                if (mealArrayList.get(i).getId().equals(meal.getName())) {
                    throw new MealException("Meal with name " + meal.getName() + " is already registered");
                }
            }
            if (meal.getDescription().isEmpty()) {
                throw new MealException("Meal description can not be empty");
            }
            for (int i = 0; i < mealArrayList.size(); i++) {
                if (mealArrayList.get(i).getId().equals(meal.getDescription())) {
                    throw new MealException("Meal with description " + meal.getDescription() + " is already registered");
                }
            }

            try {
                if (Double.isNaN(meal.getPrice())) {
                    throw new MealException("Meal price can not be empty");
                }
                for (int i = 0; i < mealArrayList.size(); i++) {
                    if (mealArrayList.get(i).getId().equals(meal.getPrice())) {
                        throw new MealException("Meal with price " + meal.getPrice() + " is already registered");
                    }
                }
                writer.println(meal.getId() + "," + meal.getName() + "," + meal.getDescription() + "," + meal.getPrice());
                writer.close();
                mealArrayList.add(meal);
            } catch (NumberFormatException e) {
                throw new MealException("Invalid Price Format");
            }
        } catch (MealException e) {
            throw e;
        }

    }

    public static void readMealsFromFile(String filePath, ArrayList<Meal> mealArrayList) throws MealException {
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                Meal meal = new Meal();
                if (StringUtils.isEmpty(line)) {
                    continue;
                }
                String[] values = line.split(",");
                if (values.length != 4) {
                    throw new MealException("One of the lines parsed size is incorrect");
                }
                if (values[0].isEmpty()) {
                    throw new MealException("Meal id can not be empty");
                }
                for (int i = 0; i < mealArrayList.size(); i++) {
                    if (mealArrayList.get(i).getId().equals(values[0])) {
                        throw new MealException("Meal with id " + values[0] + " is already registered");
                    }
                }
                meal.setId(values[0]);
                if (values[1].isEmpty()) {
                    throw new MealException("Meal name can not be empty");
                }
                for (int i = 0; i < mealArrayList.size(); i++) {
                    if (mealArrayList.get(i).getId().equals(values[1])) {
                        throw new MealException("Meal with name " + values[1] + " is already registered");
                    }
                }
                meal.setName(values[1]);
                if (values[2].isEmpty()) {
                    throw new MealException("Meal description can not be empty");
                }
                for (int i = 0; i < mealArrayList.size(); i++) {
                    if (mealArrayList.get(i).getId().equals(values[2])) {
                        throw new MealException("Meal with description " + values[2] + " is already registered");
                    }
                }
                meal.setDescription(values[2]);
                try {
                    if (StringUtils.isEmpty(values[3])) {
                        throw new MealException("Meal price can not be empty");
                    }

                    meal.setPrice(Double.parseDouble(values[3]));
                } catch (NumberFormatException e) {
                    throw new MealException("Invalid Price Format");
                }
                mealArrayList.add(meal);
            }
        } catch (IOException e) {
            throw new MealException("File Path sent does not exists");
        }
    }

}
