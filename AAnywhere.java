import java.util.Enumeration;
import java.util.Random;

public class AAnywhere implements Enumeration<String> {

    private String enumeration;

    public AAnywhere(){
        enumeration = "";
    }

    @Override
    public boolean hasMoreElements() {
        return false;
    }

    @Override
    public String nextElement() {
        Random rn = new Random();
        for(int i = 0; i < 100; i++){
            enumeration = enumeration.concat("b");
        }

        for(int i = 0; i < enumeration.length(); i++){
            enumeration = enumeration.replaceFirst("a", "");
        }

        int index = rn.nextInt(enumeration.length());
        enumeration = enumeration.substring(0, index) + "a" + enumeration.substring(index);
        return enumeration;
    }
}
