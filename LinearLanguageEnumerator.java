import java.util.Enumeration;

public class LinearLanguageEnumerator implements Enumeration<String> {
    private String growingString;
    private StringBuilder stringBuilder;

    public LinearLanguageEnumerator(){
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
            stringBuilder.insert(0, "a");

        }
        stringBuilder.append("b");
        for(int i = 0; i < 50; i++){
            stringBuilder.append("c");

        }
        growingString = stringBuilder.toString();
        return growingString;
    }
}
