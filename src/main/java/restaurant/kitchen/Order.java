package restaurant.kitchen;

import restaurant.ConsoleHelper;
import restaurant.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }


    public int getTotalCookingTime(){
        int totalTime = 0;
        for (Dish dish : dishes) {
            totalTime += dish.getDuration();
        }

        return totalTime;
    }

    @Override
    public String toString() {
        String result = "";


        if (!isEmpty()){
            return "Your order: " + dishes.toString() +" of "+  tablet.toString();
        }

        return result;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    protected void initDishes() throws IOException {
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public Tablet getTablet() {
        return tablet;
    }
}
