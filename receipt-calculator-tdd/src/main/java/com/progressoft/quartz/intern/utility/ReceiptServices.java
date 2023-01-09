package com.progressoft.quartz.intern.utility;
import com.progressoft.quartz.intern.exception.ReceiptException;
import com.progressoft.quartz.intern.manager.Meal;
import com.progressoft.quartz.intern.manager.Receipt;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
public class ReceiptServices implements ReceiptCalculator {

    // TODO: remove unused arraylist
    //  if you need the arraylist, consider giving it private access modifier
    // Done

    @Override
    public Receipt calculateReceipt(ArrayList<Meal> meals, double tax, int numberOfCustomers) {
        if (Objects.isNull(meals)) {
            throw new ReceiptException("Meals must not be null");
        }
        if (meals.size() == 0) {
            throw new ReceiptException("Meals must not be empty");
        }
        if (numberOfCustomers == 0) {
            throw new ReceiptException("Number of Customers must be greater than 0");
        }
        double num = 0;
        Receipt receipt = new Receipt();
        tax = tax/numberOfCustomers;
        receipt.setMeals(meals);

        for (int i = 0; i < meals.size(); i++) {
            num += meals.get(i).getQuantity() * meals.get(i).getPrice();
        }
        receipt.setTotal(num + tax);
        receipt.setTax(tax);

        return receipt;
    }

}





