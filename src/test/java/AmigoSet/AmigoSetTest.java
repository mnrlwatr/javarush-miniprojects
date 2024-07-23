package AmigoSet;

public class AmigoSetTest {
    public static void main(String[] args) {
        AmigoSet<String> amigoSet = new AmigoSet<String>();
        amigoSet.add("one");
        amigoSet.add("two");
        System.out.println(amigoSet.iterator().next());
    }
}
