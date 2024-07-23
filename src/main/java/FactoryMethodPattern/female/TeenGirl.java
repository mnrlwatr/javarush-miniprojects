package FactoryMethodPattern.female;

import FactoryMethodPattern.Human;

public class TeenGirl implements Human {
    public static final int MAX_AGE = 19;

    @Override
    public String toString() {
        return "TeenGirl{}";
    }
}
