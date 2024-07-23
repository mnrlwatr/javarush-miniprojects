package LogParser;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("src/main/java/LogParser/logs"));
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
    }
}