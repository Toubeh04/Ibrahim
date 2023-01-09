package com.progressoft.quartz.intern.manager;

import lombok.Getter;
import lombok.Setter;

// TODO note: Knowing how to use @Setter and @Getter is great since lot of people does not know this exists
// Thanks
@Setter
@Getter
public class Meal {
    private String name;
    private double price;
    private String id;
    private String description;
    private int quantity;
}
