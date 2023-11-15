public class CnfParser {
    private final CNFCFG grammar;
    private long operationsExecutedNaive;
    private long operationsExecutedTD;
    private int operationsExecutedBU;
    public char[] naiveInputString;
    public char[] tdInputString;

    public Boolean[][][] boolTableTD;
    public Boolean[][][] boolTableBU;

    public CnfParser(CNFCFG grammar){
        this.grammar = grammar;
    }

    public boolean parseNaive(char[] inputString){
        this.naiveInputString = inputString;
        operationsExecutedNaive = 0;
        return parse(grammar.getIntegerConversionHashMap().get(grammar.getStartRule()), 0, inputString.length);
    }

    /**
     * a recursive function that checks if a given nonTerminal leads to a terminal in the given string at a certain index
     * if it does not, the function will instead check if the given nonTerminal leads to two more nonTerminals and continue
     * by recursively checking if they eventually will lead to correct terminals
     * @param nonTerminal the nonTerminalRule that is currently being checked
     * @param i startIndex
     * @param j endIndex
     * @return true or false
     */
    private boolean parse(int nonTerminal, int i, int j) {
        operationsExecutedNaive++;
        int[][][] nonTerms = grammar.getNonTerms3dArray();

        if (i == j - 1) {
            return grammar.getTerminal(nonTerminal) == naiveInputString[i];
        }

        else {
            for(int ruleArray = 0; ruleArray < nonTerms.length; ruleArray++){
                for(int rule = 0; rule < nonTerms[ruleArray].length; rule++){

                    int leftRHSTerminal = nonTerms[ruleArray][rule][0];
                    int rightRHSTerminal = nonTerms[ruleArray][rule][1];

                    for (int k = i + 1; k <= j - 1; k++) {
                        if (parse(leftRHSTerminal, i, k) && parse(rightRHSTerminal, k, j)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    /**
     * parseBU initalizes global char[] and fills every index with corresponding index in StringToBeParsed, then fills the
     * bool[][][] in a bottom-up manner and uses solutions to subproblems to solve larger and larger problems until it is done
     * @param stringToBeParsed the stringToBeParsed
     * @return true or false
     */
    public boolean parseBU(char[] stringToBeParsed) {
        operationsExecutedBU = 0;

        //P[Row][Column][nonTerminal]

        boolTableBU = new Boolean[stringToBeParsed.length + 1][stringToBeParsed.length + 1][grammar.getNonTerminalStartSymbols().size()];

        //fill bottom row
        for (int column = 0; column < stringToBeParsed.length; column++) {
            for (int terminal = 0; terminal < grammar.getTerminals().length; terminal++) {
                if (grammar.getTerminal(terminal) == stringToBeParsed[column]) {
                    boolTableBU[1][column][terminal] = true;
                }
            }
        }

        int[][] nonTerms = grammar.getNonTerms2dArray();

        for (int i = 2; i <= stringToBeParsed.length; i++) {
            for (int j = 0; j < stringToBeParsed.length - i + 1; j++) {
                for (int k = 1; k <= i - 1; k++) {
                    for (int ruleArray = 0; ruleArray < nonTerms.length; ruleArray++) {
                            operationsExecutedBU++;
                            int[] nonTermRule = nonTerms[ruleArray];

                            System.out.println("if boolTableBU["+k+"]["+j+"]["+nonTermRule[0]+"]] and boolTableBU["+(i-k)+"][" + (j+k)+"]["+nonTermRule[1]+"]]");
                            if (boolTableBU[k][j][nonTermRule[0]] != null && boolTableBU[i - k][j + k][nonTermRule[1]] != null) {
                                System.out.println("boolTableBU["+i+"]["+j+"]["+ruleArray+"] = true");
                                boolTableBU[i][j][ruleArray] = true;
                                break;
                            }
                    }
                }
            }
        }

        return boolTableBU[stringToBeParsed.length][0][0] != null;
    }

    /**
     * parseTD initializes the global char[] with each character from the stringToBeParsed
     * and then calls the recursive parseTD func
     * @param stringToBeParsed string that will be parsed
     * @return true or false
     */
    public boolean parseTD(char[] stringToBeParsed){
        boolTableTD = new Boolean[stringToBeParsed.length][stringToBeParsed.length][grammar.getNonTerminalStartSymbols().size()];
        this.tdInputString = stringToBeParsed;
        operationsExecutedTD = 0;
        return parseRecTD( 0, stringToBeParsed.length, grammar.getIntegerConversionHashMap().get(grammar.getStartRule()));
    }

    /*
        parse RecursiveTopDown function that is very similar to the naive implementation but uses a 3-dimensional bool
        to keep track of reusable answers
     */
    private boolean parseRecTD(int i, int j, int nonTerminal) {
        operationsExecutedTD++;

        int[][][] nonTerm3dArray = grammar.getNonTerms3dArray();

        //look if answer already exists
        if(boolTableTD[i][j-1][nonTerminal] != null){
            return boolTableTD[i][j-1][nonTerminal];
        }
        else {
            if (i == j - 1) {
                return grammar.getTerminal(nonTerminal) == tdInputString[i];
            }
            else {
                //iterate through all the nonTerminalRule-RightHandSides

                for (int ruleArray = 0; ruleArray < nonTerm3dArray.length; ruleArray++) {
                    for(int rule = 0; rule < nonTerm3dArray[ruleArray].length; rule++){

                        int leftRHSTerminal = nonTerm3dArray[ruleArray][rule][0];
                        int rightRHSTerminal = nonTerm3dArray[ruleArray][rule][1];

                        for (int k = i + 1; k <= j - 1; k++) {
                            if (parseRecTD(i, k, leftRHSTerminal) && parseRecTD(k, j, rightRHSTerminal)) {
                                boolTableTD[i][j-1][nonTerminal] = true;
                                return true;
                            }
                        }
                    }
                }
                boolTableTD[i][j-1][nonTerminal] = false;
                return false;
            }
        }
    }

    public long getOperationsExecuted() {
        return this.operationsExecutedNaive;
    }

    public int getOperationsExecutedBU(){
        return this.operationsExecutedBU;
    }

    public long getOperationsExecutedTD(){
        return this.operationsExecutedTD;
    }

    public void resetOperationsExecutedNaive(){
        this.operationsExecutedNaive = 0;
    }
}
