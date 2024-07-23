package FactoryMethodPattern.male;


import FactoryMethodPattern.Human;

public class KidBoy implements Human {
    public static final int MAX_AGE = 12;

    @Override
    public String toString() {
        return "KidBoy{}";
    }
}
