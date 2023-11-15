import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Enumeration;

public class StringsEndA implements Enumeration<String> {

    private String enumerationString;

    public StringsEndA(){
        this.enumerationString = "";
    }

    @Override
    public boolean hasMoreElements() {
        return false;
    }

    @Override
    public String nextElement() {
        for(int i = 0; i < 50; i++){
            enumerationString = enumerationString.concat("ab");
        }
        enumerationString = enumerationString.concat("a");
        return enumerationString;
    }
}
