import java.util.Enumeration;

public class DyckLanguageAppend implements Enumeration<String> {

    private String growingString;
    private final StringBuilder stringBuilder;

    public DyckLanguageAppend(){
        this.growingString = "";
        this.stringBuilder = new StringBuilder(3000);
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

        growingString = stringBuilder.toString();
        return growingString;
    }
}
