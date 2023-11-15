import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LinearGrammar {
    private ArrayList<String> productionRules;
    private HashMap<Character, Integer> integerCoversionHashMap;
    private Character startRule;
    private ArrayList<Character> uniqueNonTerminalStartSymbols;
    private ArrayList<ArrayList<LinearNonTermRule>> nonTerm2dArrayList;
    private ArrayList<LinearNonTermRule> linearGrammarObjects;
    private Character[][] terminals2dArray;
    private ArrayList<ArrayList<Character>> terminals2dArrayList;
    private LinearNonTermRule[][] nonTerm2dArray;

    private char[] terminals;

    public LinearGrammar(ArrayList<String> productionRules){
        this.productionRules = productionRules;
        this.uniqueNonTerminalStartSymbols = new ArrayList<>();
        System.out.println(productionRules.size());

        this.integerCoversionHashMap = new HashMap<>();
        fillHashMap();
        this.terminals = new char[uniqueNonTerminalStartSymbols.size()];
        constructTerminalArrayList();
        System.out.println("terminals");
        for(int i = 0; i < terminals2dArrayList.size(); i++){
            for(int j = 0; j < terminals2dArrayList.get(i).size(); j++){
                System.out.println("(" + i + ", " + j + ") " + terminals2dArrayList.get(i).get(j));
            }
        }

        this.linearGrammarObjects = createLinearGrammarObjects();

        for(int i = 0; i < linearGrammarObjects.size(); i++){
            System.out.println(linearGrammarObjects.get(i).nonTerminalRHS);
        }
        System.out.println(linearGrammarObjects);


        System.out.println(integerCoversionHashMap);
        System.out.println(uniqueNonTerminalStartSymbols);

        nonTerm2dArrayList = new ArrayList<>();
        construct2dArrayList();

        System.out.println("ArrayList:");
        for(int i = 0; i < nonTerm2dArrayList.size(); i++){
            System.out.println(nonTerm2dArrayList.get(i));
        }

        convertArrayListToArray();
        System.out.println("NonTermArray:");
        for(int i = 0; i < nonTerm2dArray.length; i++){
            System.out.println(Arrays.toString(nonTerm2dArray[i]));
        }

        System.out.println("term2dArray:");
        for(int i = 0; i <terminals2dArray.length; i++){
            System.out.println(Arrays.toString(terminals2dArray[i]));
        }

        System.out.println("termArray");
        for(int i = 0; i < terminals.length; i++){
            System.out.println(terminals[i]);
        }
    }

    private void fillHashMap(){
        int nonTermAsIntIndex = 0;

        for(int i = 0; i < productionRules.size(); i++){
            Character firstCharInRule = productionRules.get(i).charAt(0);

            if(i == 0){
                startRule = firstCharInRule;
            }

            if(!uniqueNonTerminalStartSymbols.contains(productionRules.get(i).charAt(0))){
                uniqueNonTerminalStartSymbols.add(productionRules.get(i).charAt(0));
            }

            if(!integerCoversionHashMap.containsKey(firstCharInRule)){
                integerCoversionHashMap.put(firstCharInRule, nonTermAsIntIndex);
                System.out.println(integerCoversionHashMap);
                nonTermAsIntIndex++;
            }
        }
    }

    private ArrayList<LinearNonTermRule> createLinearGrammarObjects(){
        ArrayList<LinearNonTermRule> linearGrammarObjects = new ArrayList<>();
        for(int i = 0; i < productionRules.size(); i++){
            String rule = productionRules.get(i);
            if(rule.length() == 4){
                int startSymbolConversion = integerCoversionHashMap.get(rule.charAt(0));
                if(Character.isLowerCase(rule.charAt(2))){
                    int rhsNonTermConversion = integerCoversionHashMap.get(rule.charAt(3));
                    LinearNonTermRule linearNonTermRHS = new LinearNonTermRule(startSymbolConversion, rhsNonTermConversion, rule.charAt(2), true);
                    linearGrammarObjects.add(linearNonTermRHS);
                }
                else{
                    int rhsNonTermConversion = integerCoversionHashMap.get(rule.charAt(2));
                    LinearNonTermRule linearNonTermRHS = new LinearNonTermRule(startSymbolConversion, rhsNonTermConversion, rule.charAt(3), false);
                    linearGrammarObjects.add(linearNonTermRHS);
                }


            }
        }
        return linearGrammarObjects;
    }

    private void constructTerminalArrayList(){

        for(int i = 0; i < uniqueNonTerminalStartSymbols.size(); i++){
            ArrayList<Character> terms = new ArrayList<>();
            terminals2dArrayList.add(terms);
        }

        for(int i = 0; i < productionRules.size(); i++){
            String rule = productionRules.get(i);
            int conversion = integerCoversionHashMap.get(rule.charAt(0));
            if(rule.length() == 3){
                terminals[conversion] = rule.charAt(2);
                terminals2dArrayList.get(conversion).add(rule.charAt(2));
            }
            else{
                terminals[conversion] = ' ';
                if(Character.isLowerCase(rule.charAt(3))){
                    terminals2dArrayList.get(conversion).add(rule.charAt(3));
                }
                else if(Character.isLowerCase(rule.charAt(2))){
                    terminals2dArrayList.get(conversion).add(rule.charAt(2));
                }
            }
        }
    }

    private void construct2dArrayList(){
        for(int i = 0; i < uniqueNonTerminalStartSymbols.size(); i++){
            ArrayList<LinearNonTermRule> objectArrayList = new ArrayList<>();
            nonTerm2dArrayList.add(objectArrayList);
        }

        for(int i = 0; i < linearGrammarObjects.size(); i++){

            LinearNonTermRule linearNonTermRule = linearGrammarObjects.get(i);
            nonTerm2dArrayList.get(linearNonTermRule.nonTerminalLHS).add(linearNonTermRule);
        }
    }

    private void convertArrayListToArray(){
        nonTerm2dArray = new LinearNonTermRule[nonTerm2dArrayList.size()][];
        terminals2dArray = new Character[terminals2dArrayList.size()][];

        for(int i = 0; i < nonTerm2dArrayList.size(); i++){
            nonTerm2dArray[i] = nonTerm2dArrayList.get(i).toArray(new LinearNonTermRule[nonTerm2dArrayList.get(i).size()]);
        }

        for(int i = 0; i < terminals2dArrayList.size(); i++){
            terminals2dArray[i] = terminals2dArrayList.get(i).toArray(new Character[terminals2dArrayList.get(i).size()]);
        }
    }

    public HashMap<Character, Integer> getIntegerConversionHashMap() {
        return integerCoversionHashMap;
    }

    //placeholders
    public ArrayList<Character> getUniqueNonTerminalStartSymbols() {
        return uniqueNonTerminalStartSymbols;
    }

    public ArrayList<ArrayList<LinearNonTermRule>> getNonTerms2dArrayList() {
        return nonTerm2dArrayList;
    }

    public LinearNonTermRule[][] getNonTerm2dArray() {
        return nonTerm2dArray;
    }

    public char getTerminal(int index){
        return this.terminals[index];
    }

    public Character[][] getTerminals2dArray(){
        return terminals2dArray;
    }

    public Character[] getTerminalArray(int index){
        return terminals2dArray[index];
    }

    public ArrayList<LinearNonTermRule> getLinearGrammarObjects(){
        return linearGrammarObjects;
    }

    public Character getStartRule(){
        return this.startRule;
    }
}
