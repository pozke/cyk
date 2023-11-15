public class LinearNonTermRule {
    public int nonTerminalLHS;
    public int nonTerminalRHS;
    public char terminalRHS;
    public boolean rightLinear;

    public LinearNonTermRule(int nonTerminalLHS ,int nonTerminal, char terminal, boolean rightLinear){
        this.nonTerminalLHS = nonTerminalLHS;
        this.nonTerminalRHS = nonTerminal;
        this.terminalRHS = terminal;
        this.rightLinear = rightLinear;
    }
}
