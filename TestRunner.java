import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Beautiful class running tests
 */
public class TestRunner {
    private CNFCFG dyckGrammar;
    private CNFCFG stupidGrammar;
    private CNFCFG startAGrammar;
    private CNFCFG endAGrammar;
    private CNFCFG aAnyWhere;
    private CNFCFG equalAandBGrammar;
    private CNFCFG linearInCnfGrammar;
    private LinearGrammar linearGrammar;

    private CnfParser dyckParser;
    private CnfParser startACnfParser;
    private CnfParser endACnfParser;
    private CnfParser aAnyWhereParser;
    private CnfParser equalAandBCnfParser;
    private CnfParser stupidCnfParser;
    private CnfParser linearCnfCnfParser;
    private LinearParser linearParser;


    public TestRunner(ArrayList<ArrayList<String>> grammars){

        dyckGrammar = new CNFCFG(grammars.get(0));
        stupidGrammar = new CNFCFG(grammars.get(1));
        startAGrammar = new CNFCFG(grammars.get(2));
        endAGrammar = new CNFCFG(grammars.get(3));
        aAnyWhere = new CNFCFG(grammars.get(4));
        equalAandBGrammar = new CNFCFG(grammars.get(5));
        linearInCnfGrammar = new CNFCFG(grammars.get(6));
        linearGrammar = new LinearGrammar(grammars.get(7));

        dyckParser = new CnfParser(dyckGrammar);
        stupidCnfParser = new CnfParser(stupidGrammar);
        startACnfParser = new CnfParser(startAGrammar);
        endACnfParser = new CnfParser(endAGrammar);
        aAnyWhereParser = new CnfParser(aAnyWhere);
        equalAandBCnfParser = new CnfParser(equalAandBGrammar);
        linearCnfCnfParser = new CnfParser(linearInCnfGrammar);
        linearParser = new LinearParser(linearGrammar);

        runTestsBottomUpDyckLanguage(dyckParser, 30, 8, "dyckparser BU");
        runTestsTopDownDyckLangugage(dyckParser, 30, 8, "dyckparser TD");
        runTestsNaive(dyckParser, 11, 5, "dyckParse Naive");
        runUncheckedTests(dyckParser, 20, 10);
        //runLinearTDTests(linearParser, 30, 20, "linearParse");
        //runStupidTests(20, 10);
    }

    private void runTestsBottomUpDyckLanguage(CnfParser cnfParser, int iterations, int averageTimes, String parserName){

        DyckLanguageAppend dyckLanguageAppend = new DyckLanguageAppend();
        DyckLanguageRepeat dyckLanguageRepeat = new DyckLanguageRepeat();
        DyckLanguageOpeningEnd dyckLanguageOpeningEnd = new DyckLanguageOpeningEnd();
        DyckLanguageClosingStart dyckLanguageClosingStart = new DyckLanguageClosingStart();
        StringsStartA stringsStartA = new StringsStartA();
        StringsEndA stringsEndA = new StringsEndA();
        AAnywhere aAnywhere = new AAnywhere();
        EqualAAndB equalAAndB = new EqualAAndB();

        System.out.println("BottomUp tests");

        System.out.println("bottom up");
        System.out.println("dyck repeat");
        System.out.println(parserName);

        for(int i = 0; i < iterations; i++){
            String testString = dyckLanguageRepeat.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            boolean result = false;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                result = cnfParser.parseBU(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedBU()));
            System.out.println(result);
        }
        System.out.println();

        System.out.println("dyck append");
        for(int i = 0; i < iterations; i++){
            String testString = dyckLanguageAppend.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseBU(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedBU()));
        }
        System.out.println();

        System.out.println("dyck closingStart");
        for(int i = 0; i < iterations; i++){
            String testString = dyckLanguageClosingStart.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseBU(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedBU()));
        }
        System.out.println();

        System.out.println("dyck openingEnd");
        for(int i = 0; i < iterations; i++){
            String testString = dyckLanguageOpeningEnd.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseBU(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedBU()));
        }
        System.out.println();

        System.out.println("strings starting a");
        for(int i = 0; i < iterations; i++){
            String testString = stringsStartA.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseBU(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedBU()));
        }
        System.out.println();

        System.out.println("strings ending a");
        for(int i = 0; i < iterations; i++){
            String testString = stringsEndA.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseBU(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedBU()));
        }
        System.out.println();

        System.out.println("equalAandB");
        for(int i = 0; i < iterations; i++){
            String testString = equalAAndB.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseBU(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedBU()));
        }
        System.out.println();

        System.out.println("a anywhere strings");
        for(int i = 0; i < iterations; i++){
            String testString = aAnywhere.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseBU(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedBU()));
        }
        System.out.println();
    }

    private void runTestsTopDownDyckLangugage(CnfParser cnfParser, int iterations, int averageTimes, String parserName){

        DyckLanguageAppend dyckLanguageAppend = new DyckLanguageAppend();
        DyckLanguageRepeat dyckLanguageRepeat = new DyckLanguageRepeat();
        DyckLanguageOpeningEnd dyckLanguageOpeningEnd = new DyckLanguageOpeningEnd();
        DyckLanguageClosingStart dyckLanguageClosingStart = new DyckLanguageClosingStart();
        StringsStartA stringsStartA = new StringsStartA();
        StringsEndA stringsEndA = new StringsEndA();
        AAnywhere aAnywhere = new AAnywhere();
        EqualAAndB equalAAndB = new EqualAAndB();

        System.out.println("TopDown");
        System.out.println("append");
        System.out.println(parserName);

        System.out.println("strings starting a");
        for(int i = 0; i < iterations; i++){
            String testString = stringsStartA.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseTD(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedBU()));
        }
        System.out.println();

        System.out.println("strings ending a");
        for(int i = 0; i < iterations; i++){
            String testString = stringsEndA.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseTD(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedBU()));
        }
        System.out.println();

        System.out.println("a anywhere strings");
        for(int i = 0; i < iterations; i++){
            String testString = aAnywhere.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseTD(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedBU()));
        }
        System.out.println();

        System.out.println("equalAandB");
        for(int i = 0; i < iterations; i++){
            String testString = equalAAndB.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseTD(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedBU()));
        }
        System.out.println();

        System.out.println("dyck append");
        for(int i = 0; i < iterations; i++){
            String testString = dyckLanguageAppend.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseTD(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedTD()));
        }
        System.out.println();

        System.out.println("dyck opening");
        for(int i = 0; i < iterations; i++){
            String testString = dyckLanguageOpeningEnd.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseTD(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedTD()));
        }
        System.out.println();

        System.out.println("dyck repeat");
        for(int i = 0; i < iterations; i++){
            String testString = dyckLanguageRepeat.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseTD(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedTD()));
        }
        System.out.println();

        System.out.println("dyck closing");
        for(int i = 0; i < iterations; i++){
            String testString = dyckLanguageClosingStart.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseTD(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedTD()));
        }
        System.out.println();
    }

    private void runExperimentGrammarTestsBU(){

    }
    private void runExperimentGrammarTestsTD(){

    }
    private void runExperimentGrammarTestsNaive(){

    }

    private void runLinearTDTests(LinearParser linearParser, int iterations, int averageTimes, String parserName){
        LinearLanguageEnumerator linearLanguageEnumerator = new LinearLanguageEnumerator();
        System.out.println("linearLanguage");

        for(int i = 0; i < iterations; i++){
            String testString = linearLanguageEnumerator.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                linearParser.parseLinearTD(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size()));
        }
        System.out.println();
    }


    private void runTestsNaive(CnfParser cnfParser, int iterations, int averageTimes, String parserName){

        DyckLanguageAppend dyckLanguageAppend = new DyckLanguageAppend();
        DyckLanguageRepeat dyckLanguageRepeat = new DyckLanguageRepeat();
        DyckLanguageOpeningEnd dyckLanguageOpeningEnd = new DyckLanguageOpeningEnd();
        DyckLanguageClosingStart dyckLanguageClosingStart = new DyckLanguageClosingStart();

        System.out.println("Naive parser");

        System.out.println("repeat");
        System.out.println(parserName);

        for(int i = 0; i < iterations; i++){
            String testString = dyckLanguageRepeat.nextElement();
            System.out.println(testString);
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseNaive(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecuted()));
        }
        System.out.println();

        System.out.println("closing");
        for(int i = 0; i < iterations; i++){
            String testString = dyckLanguageClosingStart.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseNaive(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecuted()));
        }
        System.out.println();

        System.out.println("opening");
        for(int i = 0; i < iterations; i++){
            String testString = dyckLanguageOpeningEnd.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseNaive(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecuted()));
        }
        System.out.println();

        System.out.println("append");
        for(int i = 0; i < iterations; i++){
            String testString = dyckLanguageAppend.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseNaive(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecuted()));
        }
        System.out.println();
    }

    private void runStupidTests(int iterations, int averageTimes){
        StupidGrammarAppender stupidGrammarAppender = new StupidGrammarAppender();

        System.out.println("stupidgrammar parser topdown");
        for(int i = 0; i < iterations; i++){
            String testString = stupidGrammarAppender.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                stupidCnfParser.parseTD(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + stupidCnfParser.getOperationsExecuted()));
        }

        System.out.println("stupidgrammar parser bottomup");
        for(int i = 0; i < iterations; i++){
            String testString = stupidGrammarAppender.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                stupidCnfParser.parseBU(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size()));
        }
    }

    private long removeExtremes(ArrayList<Long> testTimes, long totalTime){
        //remove the two extremes
        Long minTime = Collections.min(testTimes);
        Long maxTime = Collections.max(testTimes);
        testTimes.remove(minTime);
        if(!Objects.equals(minTime, maxTime)){
            testTimes.remove(maxTime);
        }
        totalTime -= (minTime + maxTime);
        return totalTime;
    }

    /*private void runTestTopDown(){
        //dry run test on the longest string
        System.out.println("running test on longest string: " + enumeratedStrings[enumeratedStrings.length-1]);
        //parser.parseTD(enumeratedStrings[enumeratedStrings.length-1]);

        //System.out.println("TD: " + className);

        for(int i = 0; i < enumeratedStrings.length; i++){
            String testString = enumeratedStrings[i];
            ArrayList<Long> testTimes = new ArrayList<>();
            long totalTime = 0;
            Boolean truthValueTD = null;

            for(int j = 0; j < 10; j++){
                System.gc();
                long startTimeTD = (System.nanoTime()/1000000000); //milliseconds
                //truthValueTD = parser.parseTD(testString);
                long stopTimeTD = (System.nanoTime()/1000000000);

                testTimes.add(stopTimeTD-startTimeTD);
                totalTime += (stopTimeTD-startTimeTD);
            }
            removeExtremes(testTimes);

            //print out the string length and average time
            System.out.println(testString.length() + ", " + (totalTime/ testTimes.size()) + " " + truthValueTD);
        }
    }*/

    /*private void runTestBottomUp(){
        //dry run test on the longest string
        System.out.println("parsing on longest string: " + enumeratedStrings[enumeratedStrings.length-1]);
        //parser.parseTD(enumeratedStrings[enumeratedStrings.length-1]);

        System.out.println("BU: " + className);

        for(int i = 0; i < enumeratedStrings.length; i++){
            String testString = enumeratedStrings[i];
            ArrayList<Long> testTimes = new ArrayList<>();
            long totalTime = 0;
            Boolean truthValueBU = null;

            run tests 10 times on same string
            for(int j = 0; j < 10; j++){
                System.gc();
                long startTime = (System.nanoTime()/1000000); //milliseconds
                truthValueBU = parser.parseBU(testString);
                long stopTime = (System.nanoTime()/1000000);

                testTimes.add(stopTime-startTime);
                totalTime += (stopTime-startTime);
            }
            removeExtremes(testTimes);

            //print out the string length and average time
            System.out.println(testString.length() + ", " + (stopTime - startTime) + " " + truthValueBU);
        }
    }*/

    /*private boolean controlClass(){
        enumeratorClass = null;
        enumerateMethod = null;

        try{
            enumeratorClass = Class.forName(this.className);
        }
        catch(ClassNotFoundException e){
            System.out.println("There was no class found with that class name: " + e.getMessage());
        }

        try{
            enumerateMethod = enumeratorClass.getMethod("nextElement");
        }
        catch(NoSuchMethodException e){
            System.out.println("There was no such method for the given class: " + e.getMessage());
        }

        return enumeratorClass != null && enumerateMethod != null;
    }*/

    /*private Object createClassObject(){
        Object obj = null;
        if(controlClass()) {
            try {
                obj = enumeratorClass.getDeclaredConstructor().newInstance();
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                System.out.println("Exception when trying to get declared constructor for enumerator class: " +
                        e.getMessage());
            }
            assert obj != null;
        }
        return obj;
    }*/

    /*public void fillTestArray(){
        enumeratedStrings = new String[50];

        for(int i = 0; i < 50; i++){
            String repeatString = null;
            try{
                repeatString = (String) enumerateMethod.invoke(this.obj);
                //System.out.println(repeatString);
            }
            catch(IllegalAccessException | InvocationTargetException e){
                System.out.println("exception: " + e.getMessage());
            }
            assert repeatString != null;
            enumeratedStrings[i] = repeatString;
        }
    }*/

    private void runUncheckedTests(CnfParser cnfParser, int iterations, int averageTimes){

        DyckLanguageAppend dyckLanguageAppend = new DyckLanguageAppend();
        DyckLanguageRepeat dyckLanguageRepeat = new DyckLanguageRepeat();
        DyckLanguageClosingStart dyckLanguageClosingStart = new DyckLanguageClosingStart();
        DyckLanguageOpeningEnd dyckLanguageOpeningEnd = new DyckLanguageOpeningEnd();
        StringsStartA stringsStartA = new StringsStartA();
        StringsEndA stringsEndA = new StringsEndA();
        AAnywhere aAnywhere = new AAnywhere();
        EqualAAndB equalAAndB = new EqualAAndB();

        /*System.out.println("equalAandB");
        for(int i = 0; i < iterations; i++){
            String testString = equalAAndB.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseBU(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedBU()));
        }
        System.out.println();

        System.out.println("strings starting a");
        for(int i = 0; i < iterations; i++){
            String testString = stringsStartA.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseBU(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedBU()));
        }
        System.out.println();

        System.out.println("strings ending a");
        for(int i = 0; i < iterations; i++){
            String testString = stringsEndA.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseBU(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedBU()));
        }
        System.out.println();

        System.out.println("a anywhere strings");
        for(int i = 0; i < iterations; i++){
            String testString = aAnywhere.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseBU(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedBU()));
        }
        System.out.println();*/

        System.out.println("equalAandB");
        for(int i = 0; i < iterations; i++){
            String testString = equalAAndB.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseTD(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedTD()));
        }
        System.out.println();

        System.out.println("strings starting a");
        for(int i = 0; i < iterations; i++){
            String testString = stringsStartA.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseTD(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedTD()));
        }
        System.out.println();

        System.out.println("strings ending a");
        for(int i = 0; i < iterations; i++){
            String testString = stringsEndA.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseTD(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedTD()));
        }
        System.out.println();

        System.out.println("a anywhere strings");
        for(int i = 0; i < iterations; i++){
            String testString = aAnywhere.nextElement();
            ArrayList<Long> testTimes = new ArrayList<>();
            long totaltime = 0;
            for(int j = 0; j < averageTimes; j++){
                System.gc();
                long startTime = System.nanoTime() / 1000000;
                cnfParser.parseTD(testString.toCharArray());
                long stopTime = System.nanoTime() / 1000000;
                testTimes.add((stopTime - startTime));
                totaltime += (stopTime - startTime);
            }
            if(testTimes.size() > 2){
                removeExtremes(testTimes, totaltime);
            }
            System.out.println(testString.length() + ", " + (totaltime/ testTimes.size() + ", " + cnfParser.getOperationsExecutedTD()));
        }
        System.out.println();
    }
}
