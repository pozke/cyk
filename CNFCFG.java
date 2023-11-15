import java.util.*;

public class CNFCFG {

    private char startRule;
    private final ArrayList<String> productionRules;
    private final ArrayList<String> nonTerminalRules;
    private final ArrayList<Character> terminalRules;
    private final ArrayList<Character> nonTerminalStartSymbols;
    private int[][] nonTerms2dArray;
    private final ArrayList<ArrayList<int[]>> nonTerminals3dArrayList;
    private int[][][] nonTerms3dArray;
    private char[] terminals;
    private final HashMap<Character, Integer> integerConversionHashMap;
    private ArrayList<Character> uniqueNonTerminalLHS;

    private int ruleAsIntIndex;

    public CNFCFG(ArrayList<String> productionRules){

        this.productionRules = productionRules;
        this.nonTerminalRules = new ArrayList<>();
        this.terminalRules = new ArrayList<>();
        this.nonTerminals3dArrayList = new ArrayList<>();
        this.ruleAsIntIndex = 0;
        this.nonTerminalStartSymbols = new ArrayList<>();
        integerConversionHashMap = new HashMap<>();
        uniqueNonTerminalLHS = new ArrayList<>();

        fillHashMap();
        removeDuplicates(nonTerminalRules);
        constructNonTerminal3dArrayList();
        convertArrayListToArray();

        for(int i = 0; i < nonTerms3dArray.length; i++){
            for(int j = 0; j < nonTerms3dArray[i].length; j++){
                System.out.println(Arrays.toString(nonTerms3dArray[i][j]));
            }
        }
        System.out.println(Arrays.deepToString(nonTerms3dArray));
        this.terminals = new char[integerConversionHashMap.size()];
        constructTerminalsArray();
    }

    private void fillHashMap(){
        for(int i = 0; i < productionRules.size(); i++){
            char firstCharInRule = productionRules.get(i).charAt(0);
            char lastCharInRule = productionRules.get(i).charAt(productionRules.get(i).length()-1);

            if(i == 0){
                startRule = productionRules.get(i).charAt(0);
            }

            if(Character.isUpperCase(lastCharInRule)){
                nonTerminalRules.add(productionRules.get(i));
            }
            else if(Character.isLowerCase(lastCharInRule)){
                terminalRules.add(productionRules.get(i).charAt(productionRules.get(i).length()-1));
            }

            if(!integerConversionHashMap.containsKey(productionRules.get(i).charAt(0))){
                integerConversionHashMap.put(firstCharInRule, ruleAsIntIndex);
                System.out.println(integerConversionHashMap);
                ruleAsIntIndex++;
            }
            nonTerminalStartSymbols.add(productionRules.get(i).charAt(0));
        }
    }

    private void removeDuplicates(ArrayList<String> nonTerminalRules){
        for(int i = 0; i < nonTerminalRules.size(); i++){
            if(!uniqueNonTerminalLHS.contains(nonTerminalRules.get(i).charAt(0))){
                uniqueNonTerminalLHS.add(nonTerminalRules.get(i).charAt(0));
            }
        }
        System.out.println("uniqueLHS: " + uniqueNonTerminalLHS);
    }

    private void constructNonTerminal3dArrayList(){

        System.out.println(nonTerminalRules);
        nonTerms2dArray = new int[productionRules.size()- uniqueNonTerminalLHS.size()][2];
        //fills the nonTerminal3dArraylist
        for(int i = 0; i < uniqueNonTerminalLHS.size(); i++){
            ArrayList<int[]> allRHSForOneRule = new ArrayList<>(); //create a 2d array for each unique nonTerminal LHS
            nonTerminals3dArrayList.add(allRHSForOneRule);
        }

        for(int j = 0; j < nonTerminalRules.size(); j++){
            String oneProdRule = nonTerminalRules.get(j);

            int[] oneRHS = new int[2];
            oneRHS[0] = integerConversionHashMap.get(oneProdRule.charAt(oneProdRule.length() - 2));
            oneRHS[1] = integerConversionHashMap.get(oneProdRule.charAt(oneProdRule.length() - 1));

            int oneProdRuleHashMapConversion = integerConversionHashMap.get(oneProdRule.charAt(0));
            nonTerms2dArray[j] = oneRHS;
            nonTerminals3dArrayList.get(oneProdRuleHashMapConversion).add(oneRHS);
        }
    }

    private void constructTerminalsArray(){

        ArrayList<Character> usedSymbols = new ArrayList<>();
        for(int i = 0; i < productionRules.size(); i++){

            char firstCharInRule = productionRules.get(i).charAt(0);
            char lastCharInRule = productionRules.get(i).charAt(productionRules.get(i).length()-1);

            if(integerConversionHashMap.containsKey(firstCharInRule) && !usedSymbols.contains(firstCharInRule)){

                if(Character.isLowerCase(lastCharInRule)){
                    terminals[integerConversionHashMap.get(firstCharInRule)] = lastCharInRule;
                }
                else{
                    terminals[integerConversionHashMap.get(firstCharInRule)] = ' ';
                }

                usedSymbols.add(firstCharInRule);
            }
        }
        System.out.println(nonTerminals3dArrayList);
    }

    private void convertTermsArrayListToArray(){
        terminals = new char[terminalRules.size()];

        for(int i = 0; i < terminalRules.size(); i++) {
            terminals[i] = terminalRules.get(i);
        }

    }

    private void convertArrayListToArray(){
        nonTerms3dArray = new int[uniqueNonTerminalLHS.size()][][];
        for(int i = 0; i < nonTerminals3dArrayList.size(); i++){
            nonTerms3dArray[i] = nonTerminals3dArrayList.get(i).toArray(new int[nonTerminals3dArrayList.get(i).size()][]);
            for(int j = 0; j < nonTerminals3dArrayList.get(i).size(); j++){
                nonTerms3dArray[i][j] = nonTerminals3dArrayList.get(i).get(j);
            }
        }
    }

    public char getStartRule() {
        return startRule;
    }

    public HashMap<Character, Integer> getIntegerConversionHashMap() {
        return integerConversionHashMap;
    }

    public char getTerminal(int index){
        return terminals[index];
    }

    public ArrayList<Character> getNonTerminalStartSymbols(){
        return this.nonTerminalStartSymbols;
    }

    public char[] getTerminals(){
        return this.terminals;
    }

    public int[][][] getNonTerms3dArray(){
        return nonTerms3dArray;
    }

    public int[][] getNonTerms2dArray(){
        return nonTerms2dArray;
    }

    public ArrayList<Character> getUniqueNonTerminalLHS(){
        return this.uniqueNonTerminalLHS;
    }
}
