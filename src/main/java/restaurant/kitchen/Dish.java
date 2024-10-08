package restaurant.kitchen;

import java.util.Arrays;


public enum Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    private int duration;


    Dish(int duration) {
        this.duration = duration;
    }

    public static String allDishesToString(){
        return Arrays.toString(values()).substring(1, Arrays.toString(values()).length() - 1);
    }

    public int getDuration() {
        return duration;
    }
}
