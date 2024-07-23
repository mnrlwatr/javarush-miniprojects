
package others.SomeDialog.service.Util;



public final class ConsoleHelper {

    private ConsoleHelper() {
        throw new RuntimeException("Это утилитарный класс");
    }

    public static void writeMessage(String message){
        System.out.println(message);
    }

}
