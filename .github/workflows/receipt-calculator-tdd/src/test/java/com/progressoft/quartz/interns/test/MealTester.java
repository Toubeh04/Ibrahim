package com.progressoft.quartz.interns.test;

import com.progressoft.quartz.intern.exception.MealException;
import com.progressoft.quartz.intern.manager.Meal;
import com.progressoft.quartz.intern.manager.MealManager;
import com.progressoft.quartz.intern.utility.MealServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

public class MealTester {


    private MealManager mealManager;

    @BeforeEach
    void init() throws IOException {
        mealManager = new MealServices(); // Initialize meals manager service here
        if(mealManager == null){
            fail("Please initialize mealManager");
        }
        try(BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ibrah\\Downloads\\tests\\receipt-calculator-tdd\\src\\test\\resources\\sample\\testesttestetest"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\ibrah\\Downloads\\tests\\receipt-calculator-tdd\\src\\test\\resources\\sample\\testesttestetest"))){
            String line;
            while ((line = reader.readLine())!=null) {
                    writer.write(line);
                    writer.newLine();
            }
        }catch (Exception e){
            throw e;
        }

    }


    @Test
    public void givenNullMeal_whenSaveMeal_thenThrowMealException(){
        MealException exception = assertThrows(MealException.class, () -> mealManager.saveMeal(null));
        assertEquals("Meal must not be null", exception.getMessage());
    }

    @Test
    public void givenNullMeal_whenRemoveMeal_thenThrowMealException(){
        MealException exception = assertThrows(MealException.class, () -> mealManager.removeMeal(null));
        assertEquals("Meal must not be null", exception.getMessage());
    }
    @Test
    public void givenNullPath_whenLoadMeal_thenThrowMealException(){
        MealException exception = assertThrows(MealException.class, () -> mealManager.loadMealsFromFile(null));
        assertEquals("Path must not be null", exception.getMessage());
    }
    @Test
    public void givenNullId_whenSaveMeal_thenThrowMealException(){
        MealException exception = assertThrows(MealException.class, () -> mealManager.saveMeal(new Meal()));
        assertEquals("Meal ID must not be null", exception.getMessage());
    }

    @Test
    public void givenNullId_whenRemoveMeal_thenThrowMealException(){
        MealException exception = assertThrows(MealException.class, () -> mealManager.removeMeal(new Meal()));
        assertEquals("Meal ID must not be null", exception.getMessage());
    }
    @Test
    public void givenNullMealsIdWithQuantity_whenGetMealsById_thenThrowMealException(){
        MealException exception = assertThrows(MealException.class, () -> mealManager.getMealsById(null, null));
        assertEquals("Meals Id With Quantity must not be null", exception.getMessage());
    }
    @Test
    public void givenNullMeals_whenGetMealsById_thenThrowMealException(){
        MealException exception = assertThrows(MealException.class, () -> mealManager.getMealsById(new HashMap<>(), null));
        assertEquals("Meals must not be null", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"55", "34", "52", "33","50","32"}) // six numbers
    public void givenMealsWithWrongId_whenGetMealsById_thenThrowMealException(String id) throws MealException {
        mealManager.loadMealsFromFile(getFilePath("sample/meals.csv"));
        HashMap<String, Integer> mealsIdWithQuantityMap = getMealsIdWithQuantityMap();
        mealsIdWithQuantityMap.put(id, 0);
        MealException exception = assertThrows(MealException.class, () -> mealManager.getMealsById(mealsIdWithQuantityMap, (HashMap<String, Meal>) mealManager.getMeals()));
        assertEquals("Meal with id " + id + " is not found", exception.getMessage());
    }


    @Test
    public void givenValidFilePath_whenLoadMealsFromFile_thenSaveMeals() throws MealException {
        // note that the meals.csv has an empty line on purpose, you are not allowed to remove the empty line
        mealManager.loadMealsFromFile(getFilePath("sample/meals.csv"));
        assertEquals(6, mealManager.getMealsSize());
    }

    @Test
    public void givenValidFilePathAndMissingId_whenLoadMealsFromFile_thenThrowMissingId(){
        MealException exception = assertThrows(MealException.class, () -> mealManager.loadMealsFromFile(getFilePath("sample/mealsWithMissingId.csv")));
        assertEquals("Meal id can not be empty", exception.getMessage());
    }

    @Test
    public void givenValidFilePathAndDuplicateId_whenLoadMealsFromFile_thenThrowMissingId(){
        MealException exception = assertThrows(MealException.class, () -> mealManager.loadMealsFromFile(getFilePath("sample/mealsWithDuplicateIds.csv")));
        assertEquals("Meal with id 1 is already registered", exception.getMessage());
    }

    @Test
    public void givenValidFilePathAndMissingName_whenLoadMealsFromFile_thenThrowMissingName(){
        MealException exception = assertThrows(MealException.class, () -> mealManager.loadMealsFromFile(getFilePath("sample/mealsWithMissingName.csv")));
        assertEquals("Meal name can not be empty", exception.getMessage());
    }

    @Test
    public void givenValidFilePathAndMissingDescription_whenLoadMealsFromFile_thenThrowMissingDescription(){
        MealException exception = assertThrows(MealException.class, () -> mealManager.loadMealsFromFile(getFilePath("sample/mealsWithMissingDescription.csv")));
        assertEquals("Meal description can not be empty", exception.getMessage());
    }
   @Test
    public void givenValidFilePathAndMissingPrice_whenLoadMealsFromFile_thenThrowMissingPrice(){
        MealException exception = assertThrows(MealException.class, () -> mealManager.loadMealsFromFile(getFilePath("sample/mealsWithMissingPrice.csv")));
        assertEquals("Meal price can not be empty", exception.getMessage());
    }
    @Test
    public void givenValidFilePathAndWrongLength_whenLoadMealsFromFile_thenThrowIncorrectLength(){
        MealException exception = assertThrows(MealException.class, () -> mealManager.loadMealsFromFile(getFilePath("sample/mealsWithLengthIncorrect.csv")));
        assertEquals("One of the lines parsed size is incorrect", exception.getMessage());
      }
    @Test
    public void givenValidFilePathAndWrongPriceFormat_whenLoadMealsFromFile_thenThrowIncorrectFormat(){
        MealException exception = assertThrows(MealException.class, () -> mealManager.loadMealsFromFile(getFilePath("sample/mealsWithIncorrectPriceFormat.csv")));
        assertEquals("Invalid Price Format", exception.getMessage());
      }
    @Test
    public void givenInvalid_whenLoadMealsFromFile_thenThrowInvalidPath(){
        MealException exception = assertThrows(MealException.class, () -> mealManager.loadMealsFromFile("test"));
        assertEquals("File Path sent does not exists", exception.getMessage());
    }
    @Test
    public void givenMealAlreadyRegisteredWithId1_whenSaveMeal_thenThrowMealException() throws MealException, IOException {
        mealManager.saveMeal(createPizzaMeal());
        MealException exception = assertThrows(MealException.class, () -> mealManager.saveMeal(createPizzaMeal()));
        assertEquals("Meal with id 1 already registered", exception.getMessage());
    }
    @Test
    public void givenMealAlreadyRegisteredWithId2_whenSaveMeal_thenThrowMealException() throws MealException, IOException {
        mealManager.saveMeal(createBurgerMeal());
        MealException exception = assertThrows(MealException.class, () -> mealManager.saveMeal(createBurgerMeal()));
        assertEquals("Meal with id 2 already registered", exception.getMessage());
    }
    @Test
    public void givenMeal_whenRemoveMeal_thenThrowMealException(){
        Meal meal = createPizzaMeal();
        Meal meal2 = createBurgerMeal();
        MealException exception = assertThrows(MealException.class, () -> mealManager.removeMeal(meal));
        MealException exception2 = assertThrows(MealException.class, () -> mealManager.removeMeal(meal2));
        assertEquals("Meal with ID 1 is not registered", exception.getMessage());
        assertEquals("Meal with ID 2 is not registered", exception2.getMessage());
    }
    @Test
    public void givenMeal_whenSaveMeal_thenRemoveMeal_thenRemoveMeal_thenThrowMealException() throws MealException, IOException {
        Meal meal = createPizzaMeal();
        mealManager.saveMeal(meal);
        mealManager.removeMeal(meal);
        MealException exception = assertThrows(MealException.class, () -> mealManager.removeMeal(meal));
        assertEquals("Meal with ID 1 is not registered", exception.getMessage());
    }
    @Test
    public void givenMeal_whenGetMeal_thenThrowMealException(){
        MealException exception = assertThrows(MealException.class, () -> mealManager.getMeal("1"));
        assertEquals("Meal with ID 1 is not registered", exception.getMessage());
    }
    @Test
    public void givenMeal_whenSaveMeal_thenGetMeal_thenSuccess() throws MealException, IOException {
        Meal meal = createPizzaMeal();
        mealManager.saveMeal(meal);
        Meal mealFound = mealManager.getMeal(meal.getId());
        assertEquals(mealFound.getId(), "1");
        assertEquals(mealFound.getName(), "Pizza");
        assertEquals(mealFound.getDescription(), "Zinger Pizza");
        assertEquals(mealFound.getPrice(), 3.5);
    }
    @Test
    public void givenMeal_whenSaveMeal_thenGetSize_thenSuccess() throws MealException, IOException {
        Meal meal = createPizzaMeal();
        mealManager.saveMeal(meal);
        assertEquals(1, mealManager.getMealsSize());
    }
    @Test
    public void givenMeal_whenRemoveMeal_thenGetSize_thenSuccessOnSizeZero() throws MealException, IOException {
        Meal meal = createPizzaMeal();
        mealManager.saveMeal(meal);
        mealManager.removeMeal(meal);
        assertEquals(0, mealManager.getMealsSize());
    }
    @Test
    public void getter_setter(){
        Meal meal = new Meal();
        meal.setId("1");
        meal.setName("Pizza");
        meal.setDescription("Zinger Pizza");
        meal.setPrice(3.5);
        meal.setQuantity(2);
        assertEquals(meal.getId(), "1");
        assertEquals(meal.getName(), "Pizza");
        assertEquals(meal.getDescription(), "Zinger Pizza");
        assertEquals(meal.getPrice(), 3.5);
        assertEquals(meal.getQuantity(), 2);
    }
    private Meal createPizzaMeal(){
        Meal meal = new Meal();
        meal.setId("1");
        meal.setName("Pizza");
        meal.setDescription("Zinger Pizza");
        meal.setPrice(3.5);
        return meal;
    }

    private Meal createBurgerMeal(){
        Meal meal = new Meal();
        meal.setId("2");
        meal.setName("Burger");
        meal.setDescription("Chicken Burger Medium");
        meal.setPrice(4);
        return meal;
    }


    private String getFilePath(String resourcePath){
        URL resource = this.getClass().getClassLoader().getResource(resourcePath);
        return resource.getPath();
    }

    private HashMap<String, Integer> getMealsIdWithQuantityMap() {
        HashMap<String, Integer> mealsWithQuantity = new HashMap<>();
        mealsWithQuantity.put("2", 1);
        mealsWithQuantity.put("6", 2);
        mealsWithQuantity.put("3", 1);
        return mealsWithQuantity;
    }

}
