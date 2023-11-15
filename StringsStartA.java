import java.util.ArrayList;
import java.util.Enumeration;

public class StringsStartA implements Enumeration<String> {

    private String enumerationString;

    public StringsStartA(){
        this.enumerationString = "a";
    }

    @Override
    public boolean hasMoreElements() {
        return false;
    }

    @Override
    public String nextElement() {
        for(int i = 0; i < 50; i++){
            enumerationString = enumerationString.concat("ba");
        }
        return enumerationString;
    }
}
