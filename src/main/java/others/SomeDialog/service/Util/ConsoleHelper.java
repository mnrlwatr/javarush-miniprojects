
package others.SomeDialog.service.Util;



public final class ConsoleHelper {

    private ConsoleHelper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static void writeMessage(String message){
        System.out.println(message);
    }

}
