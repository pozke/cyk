import java.util.ArrayList;
import java.util.HashMap;

public class LinearConvertToCNF {
    private final ArrayList<String> linearProductionRules;
    private ArrayList<String> cnfProductionRules;
    private final ArrayList<Character> unusedNonTermStartSymbols;
    private final ArrayList<Character> usedTerminals;
    private final HashMap<Character, Character> termToNonTermMapping;

    public LinearConvertToCNF(ArrayList<String> grammar){
        System.out.println();
        this.linearProductionRules = grammar;
        termToNonTermMapping = new HashMap<>();
        unusedNonTermStartSymbols = new ArrayList<>();
        usedTerminals = new ArrayList<>();
        fillStartSymbolsArray();
        removeUsedStartSymbols();
        addRulesAndCreateRulesMapping();
        convertNonTermRules();

        System.out.println("LinearGrammar in CNF:" + cnfProductionRules);
    }

    private void fillStartSymbolsArray(){
        for(int i = 'A'; i < 'Z'; i++){
            unusedNonTermStartSymbols.add((char)i);
        }
    }

    private void removeUsedStartSymbols(){
        for(int i = 0; i < linearProductionRules.size(); i++){
            unusedNonTermStartSymbols.remove(Character.valueOf(linearProductionRules.get(i).charAt(0)));
        }
    }

    private void addRulesAndCreateRulesMapping(){
        for(int i = 0; i < linearProductionRules.size(); i++){

            String currentRule = linearProductionRules.get(i);
            if(currentRule.substring(2).length() == 2){

                char rhsTerminal = currentRule.charAt(2);
                if(Character.isLowerCase(rhsTerminal) && !usedTerminals.contains(rhsTerminal)){
                    //aA
                    char nonTerm = unusedNonTermStartSymbols.remove(0);
                    String newRule = nonTerm + " " + rhsTerminal;
                    usedTerminals.add(rhsTerminal);
                    termToNonTermMapping.put(rhsTerminal,nonTerm);
                    linearProductionRules.add(newRule);
                }

                rhsTerminal = linearProductionRules.get(i).charAt(3);
                if(Character.isLowerCase(rhsTerminal) && !usedTerminals.contains(rhsTerminal)){
                    //Aa,
                    char nonTerm = unusedNonTermStartSymbols.remove(0);
                    String newRule = nonTerm + " " + rhsTerminal;
                    usedTerminals.add(rhsTerminal);
                    termToNonTermMapping.put(rhsTerminal,nonTerm);
                    linearProductionRules.add(newRule);
                }
            }
        }
    }

    private void convertNonTermRules() {
        for (int i = 0; i < linearProductionRules.size(); i++) {
            String currentRule = linearProductionRules.get(i);
            if (currentRule.substring(2).length() == 2) {
                if (Character.isLowerCase(currentRule.charAt(2))) {
                    String editNonTermRHS = currentRule.replace(currentRule.charAt(2),
                            termToNonTermMapping.get(currentRule.charAt(2)));
                    linearProductionRules.set(i, editNonTermRHS);
                } else if (Character.isLowerCase(currentRule.charAt(3))) {
                    String editNonTermRHS = currentRule.replace(currentRule.charAt(3),
                            termToNonTermMapping.get(currentRule.charAt(3)));
                    linearProductionRules.set(i, editNonTermRHS);
                }
            }
        }
        cnfProductionRules = linearProductionRules;
    }

    public ArrayList<String> getCnfProductionRules(){
        return this.cnfProductionRules;
    }
}
