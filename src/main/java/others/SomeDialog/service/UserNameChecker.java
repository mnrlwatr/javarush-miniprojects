package others.SomeDialog.service;

import java.util.HashSet;
import java.util.Set;

public final class UserNameChecker {

    private static final Set<String> users = new HashSet<>();

    private UserNameChecker() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static boolean addNewUser(String name){
        return users.add(name);
    }




}
