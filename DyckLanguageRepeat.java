import java.util.Enumeration;

public class DyckLanguageRepeat implements Enumeration<String> {

    private String repeatString;

    public DyckLanguageRepeat(){
        this.repeatString = "";
    }

    @Override
    public boolean hasMoreElements() {
        return false;
    }

    @Override
    public String nextElement() {
        for(int i = 0; i < 50; i++){
            repeatString = repeatString.concat("ab");
        }
        return repeatString;
    }
}
