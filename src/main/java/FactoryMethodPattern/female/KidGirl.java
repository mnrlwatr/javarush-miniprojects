package FactoryMethodPattern.female;

import FactoryMethodPattern.Human;

public class KidGirl implements Human {
    public static final int MAX_AGE = 12;

    @Override
    public String toString() {
        return "KidGirl{}";
    }
}
