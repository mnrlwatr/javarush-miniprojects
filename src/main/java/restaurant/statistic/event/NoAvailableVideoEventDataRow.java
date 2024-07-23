package restaurant.statistic.event;

import java.util.Date;


public class NoAvailableVideoEventDataRow implements EventDataRow{
    /**
     * время приготовления заказа в секундах.
     */
    private int totalDuration;

    private Date currentDate;

    /**
     * @param totalDuration время приготовления заказа в секундах
     */
    public NoAvailableVideoEventDataRow(int totalDuration) {
        this.totalDuration = totalDuration;
        this.currentDate = new Date();
    }

    @Override
    public EventType getType() {
        return EventType.NO_AVAILABLE_VIDEO;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return totalDuration;
    }
}
