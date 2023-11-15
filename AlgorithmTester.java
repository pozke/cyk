import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class AlgorithmTester {
    public static void main(String[] args){

        ArrayList<String> dyckProductionRules = readGrammarFile(args, 0);
        ArrayList<String> stupidProductionRules = readGrammarFile(args, 1);
        ArrayList<String> stringsStartingInA = readGrammarFile(args, 2);
        ArrayList<String> stringsEndingInA = readGrammarFile(args, 3);
        ArrayList<String> aAnywhere = readGrammarFile(args, 4);
        ArrayList<String> equalAAndB = readGrammarFile(args, 5);
        ArrayList<String> linearGrammarRules = readGrammarFile(args, 6);
        ArrayList<String> linearGrammarRulesCopy = new ArrayList<>(linearGrammarRules);
        LinearConvertToCNF linearCFG = new LinearConvertToCNF(linearGrammarRulesCopy);

        ArrayList<ArrayList<String>> grammars = new ArrayList<>();
        grammars.add(dyckProductionRules);
        grammars.add(stupidProductionRules);
        grammars.add(stringsStartingInA);
        grammars.add(stringsEndingInA);
        grammars.add(aAnywhere);
        grammars.add(equalAAndB);
        grammars.add(linearCFG.getCnfProductionRules());
        grammars.add(linearGrammarRules);

        TestRunner testRunner = new TestRunner(grammars);
    }

    private static ArrayList<String> readGrammarFile(String[] args, int programArgumentIndex){
        ArrayList<String> grammarProdRules = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(new File(args[programArgumentIndex]));
            while(scanner.hasNextLine()){
                String fileRow = scanner.nextLine();
                grammarProdRules.add(fileRow);
                System.out.println(fileRow);
            }
            System.out.println();
        }
        catch(java.io.FileNotFoundException e){
            System.out.println("grammar file not found: " +e.getMessage());
        }
        return grammarProdRules;
    }
}

