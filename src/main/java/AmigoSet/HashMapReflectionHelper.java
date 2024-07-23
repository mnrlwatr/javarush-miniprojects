package AmigoSet;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class HashMapReflectionHelper {

   public static <T> T callHiddenMethod (HashMap map,String methodName){
       try {
           Method method = map.getClass().getDeclaredMethod(methodName);
           method.setAccessible(true);
           return (T) method.invoke(map);
       } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
           throw new RuntimeException(e);
       }
   }
}
