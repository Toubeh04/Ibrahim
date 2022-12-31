package com.progressoft.quartz.intern.utility;

import com.progressoft.quartz.intern.exception.ReceiptException;
import com.progressoft.quartz.intern.manager.Meal;
import com.progressoft.quartz.intern.manager.Receipt;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;

public class ReceiptServices implements ReceiptCalculator {
    ArrayList<Meal> arrayList = new ArrayList<>();

    @Override
    public Receipt calculateReceipt(ArrayList<Meal> meals, double tax, int numberOfCustomers) throws ReceiptException, FileNotFoundException {
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
        receipt.setMeals(meals);

        for (int i = 0; i < meals.size(); i++) {
            num += meals.get(i).getQuantity() * meals.get(i).getPrice();
        }
        receipt.setTotal(num + tax);
        receipt.setTax(tax);

        return receipt;
    }

}





