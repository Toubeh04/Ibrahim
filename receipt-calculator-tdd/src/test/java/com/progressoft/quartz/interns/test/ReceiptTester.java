package com.progressoft.quartz.interns.test;

import com.progressoft.quartz.intern.exception.MealException;
import com.progressoft.quartz.intern.exception.ReceiptException;
import com.progressoft.quartz.intern.manager.Meal;
import com.progressoft.quartz.intern.manager.MealManager;
import com.progressoft.quartz.intern.manager.Receipt;
import com.progressoft.quartz.intern.utility.MealServices;
import com.progressoft.quartz.intern.utility.ReceiptCalculator;
import com.progressoft.quartz.intern.utility.ReceiptServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTester {

    private ReceiptCalculator receiptCalculator;
    private MealManager mealManager;

    // TODO: Editing the Test cases automatically renders the task as an invalid task (Rule 2 inside README.md)
    //  therefore, i will check your clean code and give you some tips, as a new developer keep in mind that when you are given a task and the rules are broken they will not
    //  take you into consideration, However, do not take this as a negative comment as it is just a guide for you for future references :)
    // Done
    @BeforeEach
    void init(){
        mealManager = new MealServices(); // Initialize meals manager service here
        receiptCalculator = new ReceiptServices(); // Initialize receipt calculator service here
        if(mealManager == null){
            fail("Please initialize mealManager");
        }
        if(receiptCalculator == null){
            fail("Please initialize receiptCalculator");
        }

    }

    @Test
    public void givenNullMeal_whenCalculateReceipt_thenThrowReceiptException(){
        ReceiptException exception = assertThrows(ReceiptException.class, () -> receiptCalculator.calculateReceipt(null, 0, 0));
        assertEquals("Meals must not be null", exception.getMessage());
    }

    @Test
    public void givenEmptyMeal_whenCalculateReceipt_thenThrowReceiptException(){
        ReceiptException exception = assertThrows(ReceiptException.class, () -> receiptCalculator.calculateReceipt(new ArrayList<>(), 0, 0));
        assertEquals("Meals must not be empty", exception.getMessage());
    }


    // TODO: you are not supposed to add a new "throws MealException"
    //  hint: you are extending Exception on the MealException class, try extending RuntimeException
    //  RuntimeExceptions does not force the methods to throw a specific exception
    //  hint 2: for the other exceptions like IOExceptions, you can surround it with try catch in the method that throws an IOException, so that it is not forced to throw an IOException
    // Done
    @Test
    public void givenZeroNumberOfCustomers_whenCalculateReceipt_thenThrowReceiptException() {
        ArrayList<Meal> mealsById = getMeals();
        ReceiptException exception = assertThrows(ReceiptException.class, () -> receiptCalculator.calculateReceipt(mealsById, 0, 0));
        assertEquals("Number of Customers must be greater than 0", exception.getMessage());
    }

    // TODO: same TODO as line 55
    // Done
    @Test
    public void givenValidArgumentsWithTwoCustomers_whenCalculateReceipt_thenGetReceipt() {
        ArrayList<Meal> mealsById = getMeals();
        //  TODO, why was the test changed from (mealsById, 2, 2), (mealsById, 1, 2)
        //  if it was for testing purposes and you forgot to change it, then do not worry about it.
        //  if it was to make the test case work then go to line 26
        //  Done
        Receipt receipt = receiptCalculator.calculateReceipt(mealsById, 2, 2);
        assertEquals(3, receipt.getMeals().size());
        assertEquals(13.5, receipt.getTotal());
        assertEquals(1, receipt.getTax());
    }


    // TODO: same TODO as line 55
    // Done
    @Test
    public void givenValidArgumentsWithOneCustomers_whenCalculateReceipt_thenGetReceipt(){
        ArrayList<Meal> mealsById = getMeals();
        Receipt receipt = receiptCalculator.calculateReceipt(mealsById, 2, 1);
        assertEquals(3, receipt.getMeals().size());
        assertEquals(14.5, receipt.getTotal());
        assertEquals(2, receipt.getTax());
    }

    // TODO: same TODO as line 55
    // Done
    private ArrayList<Meal> getMeals(){
        mealManager.loadMealsFromFile(getFilePath("sample/meals.csv"));
        HashMap<String, Integer> mealsWithQuantity = new HashMap<>();
        mealsWithQuantity.put("2", 1);
        mealsWithQuantity.put("6", 2);
        mealsWithQuantity.put("3", 1);
        ArrayList<Meal> mealsById = mealManager.getMealsById(mealsWithQuantity, (HashMap<String, Meal>) mealManager.getMeals());
        return mealsById;
    }

    private String getFilePath(String resourcePath){
        URL resource = this.getClass().getClassLoader().getResource(resourcePath);
        return resource.getPath();
    }

}
