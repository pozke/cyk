import java.util.Enumeration;

public class DyckLanguageOpeningEnd implements Enumeration<String> {

    private String enumerationString;
    private final StringBuilder stringBuilder;

    public DyckLanguageOpeningEnd(){
        this.enumerationString = "";
        this.stringBuilder = new StringBuilder(3001);
    }

    @Override
    public boolean hasMoreElements() {
        return false;
    }

    @Override
    public String nextElement() {
        for(int i = 0; i < 50; i++){
            stringBuilder.insert(0,"a");
            stringBuilder.append("b");
        }
        enumerationString = stringBuilder.toString();
        enumerationString = enumerationString.concat("a");
        return enumerationString;
    }
}
