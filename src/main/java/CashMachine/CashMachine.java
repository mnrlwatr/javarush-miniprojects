package CashMachine;

import CashMachine.command.CommandExecutor;
import CashMachine.exception.InterruptOperationException;

import java.util.Locale;

public class CashMachine {

    public static final String RESOURCE_PATH = "main.java."+CashMachine.class.getPackage().getName() + ".resources.";

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            Operation operation = Operation.LOGIN;
            CommandExecutor.execute(operation);
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);
        } catch (InterruptOperationException ignored) {
            ConsoleHelper.printExitMessage();
        }
    }
}
