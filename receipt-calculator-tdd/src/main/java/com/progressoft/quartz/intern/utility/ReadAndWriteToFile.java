package com.progressoft.quartz.intern.utility;

import com.progressoft.quartz.intern.exception.MealException;
import com.progressoft.quartz.intern.manager.Meal;
import org.codehaus.plexus.util.StringUtils;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ReadAndWriteToFile {

    // TODO: it is great that you are creating multiple validations for id name description price
    //  but keep in mind that over validations are not always the correct business.
    //  usually for TDD tasks or Projects, you only write the code as simple as possible just to make the test work, any additional validations should not be added
    //  hint: in the test case, only the ID is unique

    // TODO Continued:  Why is the extra validations can be considered wrong?
    //  Lets say you have a meal
    //  id: 1, name: Mushroom Pizza, Description: Contains Mushroom and Chicken, Price:2
    //  and
    //  id: 2, name: Mushroom Burger, Description: Contains Mushroom and Chicken, Price:2
    //  that means the validations will not allow the meal to be registered because it contains the same description. which is wrong since the description just
    //  describes what is inside the meal in this situation, so validating it might cause issues. same goes for the price


    // TODO: it is better to validate the meal inside the saveMeal method instead of the writeMealsToFile
    //  keep the writeMealsToFile for writing only
    // Done
    public static void writeMealsToFile(ArrayList<Meal> mealArrayList, Meal meal, String filePath) throws IOException, MealException {
        Path filepath = Paths.get(filePath);
        Files.write(filepath, "id,name,description,price".getBytes());
        Files.write(filepath, (meal.getId()+" "+meal.getName()+" "+meal.getDescription()+" "+meal.getPrice()).getBytes());
        mealArrayList.add(meal);
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
                // TODO: Duplicate Code of line 98, try to use a method for duplicates
                // I deleted the code because it wasn't required in the test cases
                // Done


                meal.setName(values[1]);
                if (values[2].isEmpty()) {
                    throw new MealException("Meal description can not be empty");
                }
                // TODO: Duplicate Code of line 98, try to use a method for duplicates
                // I deleted the code because it wasn't required in the test cases
                // Done
              ;
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
