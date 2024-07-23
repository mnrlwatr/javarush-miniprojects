package restaurant.kitchen;

import restaurant.ConsoleHelper;
import restaurant.statistic.StatisticManager;
import restaurant.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;


public class Cook extends Observable implements Runnable{
    private final String name;
    private LinkedBlockingQueue<Order> queue;
    private boolean busy;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


    public void startCookingOrder(Order order) throws InterruptedException {
        this.busy = true;
        ConsoleHelper.writeMessage(String.format("Start cooking - %s, cooking time %d min", order, order.getTotalCookingTime()));

        Thread.sleep(order.getTotalCookingTime() * 10);

        StatisticManager.getInstance().register(
                new CookedOrderEventDataRow(
                        order.getTablet().toString(),
                        this.toString(),
                        order.getTotalCookingTime() * 60,
                        order.getDishes()
                )
        );

        queue.put(order);
        this.busy = false;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                startCookingOrder(queue.take());
                Thread.sleep(10);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
