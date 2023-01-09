package com.progressoft.quartz.intern.manager;


// TODO, remove unused imports
// Done
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
@Getter
@Setter
public class Receipt {
    private double Total;
    private double Tax;
    private ArrayList<Meal> meals;

}

