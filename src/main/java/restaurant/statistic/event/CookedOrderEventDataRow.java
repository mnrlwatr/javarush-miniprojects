package restaurant.statistic.event;

import restaurant.kitchen.Dish;

import java.util.Date;
import java.util.List;


public class CookedOrderEventDataRow implements EventDataRow{

    private String tabletName;
    private String cookName;
    private int cookingTimeSeconds;
    private List<Dish> cookingDishs;

    private Date currentDate;

    /**
     * @param tabletName         имя планшета
     * @param cookName           имя повара
     * @param cookingTimeSeconds время приготовления заказа в секундах
     * @param cookingDishs       список блюд для приготовления
     */
    public CookedOrderEventDataRow(String tabletName, String cookName, int cookingTimeSeconds, List<Dish> cookingDishs) {
        this.tabletName = tabletName;
        this.cookName = cookName;
        this.cookingTimeSeconds = cookingTimeSeconds;
        this.cookingDishs = cookingDishs;

        this.currentDate = new Date();
    }

    @Override
    public EventType getType() {
        return EventType.COOKED_ORDER;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return cookingTimeSeconds;
    }

    public String getCookName() {
        return cookName;
    }
}
