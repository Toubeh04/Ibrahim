package com.progressoft.quartz.intern.utility;

import com.progressoft.quartz.intern.exception.MealException;
import com.progressoft.quartz.intern.exception.ReceiptException;
import com.progressoft.quartz.intern.manager.Meal;
import com.progressoft.quartz.intern.manager.Receipt;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface ReceiptCalculator {


    // TODO: Editing the Interfaces automatically renders the task as an invalid task (Rule 1)
    //  therefore, i will check your clean code and give you some tips, as a new developer keep in mind that when you are given a task and the rules are broken they will not
    //  take you into consideration, However, do not take this as a negative comment as it is just a guide for you for future references :)
    // Done
    Receipt calculateReceipt(ArrayList<Meal> meals, double tax, int numberOfCustomers);
}
