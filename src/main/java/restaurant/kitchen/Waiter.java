package restaurant.kitchen;

import restaurant.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;


public class Waiter extends Observable implements Observer {
    /**
     * @param o  объект, который отправил нам значение - Cook
     * @param arg само значение - Order
     */
    @Override
    public void update(Observable o, Object arg) {
        ConsoleHelper.writeMessage(String.format("%s was cooked by %s", arg, o));
    }
}
