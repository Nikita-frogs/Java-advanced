import java.util.ArrayList;
import java.util.List;

public class RawTypes_Generics {
    public static void main(String[] args) {
//        List list = new ArrayList<>();
//        list.add("Hello");
//        list.add(1500);
//
//        try{
//            for(Object o: list){
//                System.out.println((String)o);
//            }
//        }
//        catch(ClassCastException e){
//            System.out.println(e.getMessage());
//        }

        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("1500");

        try{
            for(Object o: list){
                System.out.println((String)o);
            }
        }
        catch(ClassCastException e){
            System.out.println(e.getMessage());
        }
    }
}
