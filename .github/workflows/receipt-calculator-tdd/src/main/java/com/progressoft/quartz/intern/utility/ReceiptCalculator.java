package com.progressoft.quartz.intern.utility;

import com.progressoft.quartz.intern.exception.MealException;
import com.progressoft.quartz.intern.exception.ReceiptException;
import com.progressoft.quartz.intern.manager.Meal;
import com.progressoft.quartz.intern.manager.Receipt;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface ReceiptCalculator {

    Receipt calculateReceipt(ArrayList<Meal> meals, double tax, int numberOfCustomers) throws ReceiptException, MealException, FileNotFoundException;
}
