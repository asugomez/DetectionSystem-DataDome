package detectionsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
/**
 * @author Asuncion Gomez
 */
public class Main {

       public static void main(String[] args) throws FileNotFoundException { 

        int number = 0;
        File botsFolder = new File("botsFolder");
        if (!botsFolder.exists()){
            botsFolder.mkdirs();
        }
        // --------- TEST 0 ------------ //
        File botsFile = new File("./botsFolder/botsFile" + number +".txt");
        PrintStream stream = new PrintStream(botsFile);
        System.setOut(stream);   
        Test.testNoBots1();

        number++;

        // --------- TEST 1 ------------ //
        botsFile = new File("./botsFolder/botsFile" + number +".txt");
        stream = new PrintStream(botsFile);
        System.setOut(stream); 
        Test.testNoBots2();

        number++;

        // --------- TEST 2 ------------ //
        botsFile = new File("./botsFolder/botsFile" + number +".txt");
        stream = new PrintStream(botsFile);
        System.setOut(stream); 
        Test.testBotsUserAgent();

        number++;
        
        // --------- TEST 3 ------------ //
        botsFile = new File("./botsFolder/botsFile" + number +".txt");
        stream = new PrintStream(botsFile);
        System.setOut(stream); 
        Test.testROBotsTXT();   

        number++;

        // --------- TEST 4 ------------ //
        botsFile = new File("./botsFolder/botsFile" + number +".txt");
        stream = new PrintStream(botsFile);
        System.setOut(stream); 
        //Test.finalTestRobotsTXT();

        number++;

        // --------- TEST 5 ------------ //
        botsFile = new File("./botsFolder/botsFile" + number +".txt");
        stream = new PrintStream(botsFile);
        System.setOut(stream); 
        //Test.finalTestUserAgent();
        

        number++;

        // --------- TEST 6 ------------ //
        botsFile = new File("./botsFolder/botsFile" + number +".txt");
        stream = new PrintStream(botsFile);
        System.setOut(stream); 
        //Test.finalTestTwoAlgorithms();     
        
        number++;

        // --------- TEST 7 ------------ //
        botsFile = new File("./botsFolder/botsFile" + number +".txt");
        stream = new PrintStream(botsFile);
        System.setOut(stream); 
        Test.procedureDNStest();  

        
        number++;

        // --------- TEST 8 ------------ //
        botsFile = new File("./botsFolder/botsFile" + number +".txt");
        stream = new PrintStream(botsFile);
        System.setOut(stream); 
        //Test.testDNSImproveEasy();

        number++;

        // --------- TEST 9 ------------ //
        botsFile = new File("./botsFolder/botsFile" + number +".txt");
        stream = new PrintStream(botsFile);
        System.setOut(stream); 
        //Test.orderListTest();

        
        number++;

        // --------- TEST 10 (not fast) ------------ //
        botsFile = new File("./botsFolder/botsFile" + number +".txt");
        stream = new PrintStream(botsFile);
        System.setOut(stream); 
        //Test.testDNSlookupImprove();
        
    }
}
