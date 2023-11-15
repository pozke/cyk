import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class EqualAAndB implements Enumeration<String> {

    private String enumerationString;

    public EqualAAndB(){
        this.enumerationString = "";
    }

    @Override
    public boolean hasMoreElements() {
        return false;
    }

    @Override
    public String nextElement() {
        for(int i = 0; i < 50; i++){
            enumerationString.concat("ab");
        }
        return enumerationString;
    }
}
