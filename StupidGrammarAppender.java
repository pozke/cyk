import java.util.Enumeration;

public class StupidGrammarAppender implements Enumeration<String> {

    private String enumerationString;

    public StupidGrammarAppender(){
        this.enumerationString = "";
    }

    @Override
    public boolean hasMoreElements() {
        return false;
    }

    @Override
    public String nextElement() {
        for(int i = 0; i < 100; i++){
            enumerationString = enumerationString.concat("a");
        }
        return enumerationString;
    }
}
