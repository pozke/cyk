public class LinearParser {
    private int operationsExecutedTD;
    private Boolean[][][] boolTableTD;
    private LinearGrammar linearGrammar;
    private char[] tdInputString;

    public LinearParser(LinearGrammar grammar) {
        this.linearGrammar = grammar;
    }

    public boolean parseLinearTD(char[] stringToBeParsed) {
        boolTableTD = new Boolean[stringToBeParsed.length][stringToBeParsed.length][linearGrammar.getLinearGrammarObjects().size()];
        tdInputString = stringToBeParsed;
        return parseLinearRecTD(0, tdInputString.length, linearGrammar.getIntegerConversionHashMap().get(linearGrammar.getStartRule()));
    }

    private boolean parseLinearRecTD(int i, int j, int nonTerminal) {

        LinearNonTermRule[][] linearNonTermRules = linearGrammar.getNonTerm2dArray();

        if (boolTableTD[i][j - 1][nonTerminal] != null) {
            return boolTableTD[i][j - 1][nonTerminal];
        } else {
            if (i == j - 1) {
                return linearGrammar.getTerminal(nonTerminal) == tdInputString[i];
            }
            else {
                //iterate through all the nonTerminalRule-RightHandSides
                for (int ruleArray = 0; ruleArray < linearNonTermRules.length; ruleArray++) {
                    for (int rule = 0; rule < linearNonTermRules[ruleArray].length; rule++) {

                        if(linearNonTermRules[ruleArray][rule].nonTerminalLHS == nonTerminal){
                            if (linearNonTermRules[ruleArray][rule].rightLinear) {
                                if(linearNonTermRules[ruleArray][rule].terminalRHS == tdInputString[i] && parseLinearRecTD(i+1, j, linearNonTermRules[ruleArray][rule].nonTerminalRHS)){
                                    boolTableTD[i][j-1][nonTerminal] = true;
                                    return true;
                                }
                            }
                            else{
                                if(linearNonTermRules[ruleArray][rule].terminalRHS == tdInputString[j-1] && parseLinearRecTD(i, j-1, linearNonTermRules[ruleArray][rule].nonTerminalRHS)){
                                    boolTableTD[i][j-1][nonTerminal] = true;
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            boolTableTD[i][j - 1][nonTerminal] = false;
            return false;
        }
    }
}

