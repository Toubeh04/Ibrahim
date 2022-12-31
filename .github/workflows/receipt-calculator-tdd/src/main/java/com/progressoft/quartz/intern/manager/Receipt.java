package com.progressoft.quartz.intern.manager;


import com.progressoft.quartz.intern.exception.MealException;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.plexus.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;

@Getter
@Setter
public class Receipt {
    private double Total;
    private double Tax;
    private ArrayList<Meal> meals;

}

