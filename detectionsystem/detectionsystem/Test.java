package detectionsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {
     /**
     * test using the user agent detection in a list without bots
    */
    public static List<AccessLog> testNoBots1() {
        List<String> whitelist = BotsList.whiteList();

        String apacheLog = BotsList.listWithoutBots();
        List<AccessLog> listOfLogs = AccessLog.allAccessLog(apacheLog);

        List<AccessLog> listOfBots = AccessLog.userAgentDetection(listOfLogs, whitelist);
        
        if (listOfBots.isEmpty()) {
            System.out.println("It works!");
        } else {
            //System.out.println(listOfBots);
            System.out.println("Something got wrong :(");
            System.out.println(listOfBots);
        }

        System.out.println("Number of bots: "+ listOfBots.size());
        return listOfBots;
    }

    /**
     * Test using the robots.txt detection in a list without bots
    */
    public static List<AccessLog> testNoBots2() {
        List<String> robotsTxt = BotsList.robotsTxtList();

        String apacheLog = BotsList.listWithoutBots();
        List<AccessLog> listOfLogs = AccessLog.allAccessLog(apacheLog);

        List<AccessLog> listOfBots = AccessLog.robotsTxtDetection(listOfLogs, robotsTxt);
        if (listOfBots.isEmpty()) {
            System.out.println("It works!");
        } else {
            System.out.println("Something got wrong :(");
        }
        System.out.println("Number of bots: "+ listOfBots.size());
        return listOfBots;

    }

    /**
     * test using the user agent detection in a list with bots
    */
    public static List<AccessLog> testBotsUserAgent() {
        List<String> whitelist = BotsList.whiteList();

        String apacheLog = BotsList.listWithtBotsBOT();
        List<AccessLog> listOfLogs = AccessLog.allAccessLog(apacheLog);

        List<AccessLog> listOfBots = AccessLog.userAgentDetection(listOfLogs, whitelist);

        if (listOfBots.isEmpty()) {
            System.out.println("It works!");
        } else {
            for (AccessLog bot : listOfBots) {
                System.out.println(bot.toString());
            }
        }

        System.out.println("Number of bots: "+ listOfBots.size());
        return listOfBots;

    }

    /**
     * test using the robotsTxtyDetection in a list with bots attempting the robots.txt file
    */
    public static List<AccessLog> testROBotsTXT() {
        List<String> robotsTxt = BotsList.robotsTxtList();

        String apacheLog = BotsList.listWithROBots();
        List<AccessLog> listOfLogs = AccessLog.allAccessLog(apacheLog);

        List<AccessLog> listOfBots = AccessLog.robotsTxtDetection(listOfLogs, robotsTxt);
        if (listOfBots.isEmpty()) {
            System.out.println("Something got wrong :(");
        } else {
            for (AccessLog bot : listOfBots) {
                System.out.println(bot.toString());
            }
        }
        System.out.println("Number of bots: "+ listOfBots.size());
        return listOfBots;

    }

    
    /**
    * test using the robotsTxtyDetection and the entire website log
    */
    public static List<AccessLog> finalTestRobotsTXT(){
        List<AccessLog> listOfBots =  new ArrayList<>();
        List<String> robotsTxt = BotsList.robotsTxtList();

        String pathWebSiteLog = "./webSiteLog.txt";

        try{
            String apacheLog= Files.readString(Paths.get(pathWebSiteLog));

            List<AccessLog> listOfLogs = AccessLog.allAccessLog(apacheLog);

            listOfBots = AccessLog.robotsTxtDetection(listOfLogs, robotsTxt);
            if (listOfBots.isEmpty()) {
                System.out.println("Something got wrong :(");
            } else {
                for (AccessLog bot : listOfBots) {
                    System.out.println(bot.toString());
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("Number of bots: "+ listOfBots.size());
        return listOfBots;
    }
    

    /**
    * test using the agentUserDetection and the entire website log
    */
    public static List<AccessLog> finalTestUserAgent(){
        List<AccessLog> listOfBots =  new ArrayList<>();
        List<String> whitelist = BotsList.whiteList();

        String pathWebSiteLog = "./webSiteLog.txt";

        try{
            String apacheLog= Files.readString(Paths.get(pathWebSiteLog));

            List<AccessLog> listOfLogs = AccessLog.allAccessLog(apacheLog);

            listOfBots = AccessLog.userAgentDetection(listOfLogs, whitelist);
            if (listOfBots.isEmpty()) {
                System.out.println("Something got wrong :(");
            } else {
                for (AccessLog bot : listOfBots) {
                    System.out.println(bot.toString());
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("Number of bots: "+ listOfBots.size());
        return listOfBots;
    }
   
    /**
    * test using the two algorithms detection and the entire website log
    */
    public static List<AccessLog> finalTestTwoAlgorithms(){
        List<AccessLog> listOfBots =  new ArrayList<>();
        List<String> robotsTxt = BotsList.robotsTxtList();
        List<String> whitelist = BotsList.whiteList();

        String pathWebSiteLog = "./webSiteLog.txt";

        try{
            String apacheLog= Files.readString(Paths.get(pathWebSiteLog));

            List<AccessLog> listOfLogs = AccessLog.allAccessLog(apacheLog);

            listOfBots = AccessLog.detectionSystem1(listOfLogs, robotsTxt, whitelist);

            if (listOfBots.isEmpty()) {
                System.out.println("Something got wrong :(");
            } else {
                for (AccessLog bot : listOfBots) {
                    System.out.println(bot.toString());
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("Number of bots: "+ listOfBots.size());
        return listOfBots;

    }

    
    /**
     * test using the dns lookup detection and the entire website log
    */
    public static List<AccessLog> procedureDNStest(){
        List<AccessLog> listOfBots =  new ArrayList<>();
        List<String> whitelist = BotsList.whiteList();
        List<String> blacklist = BotsList.blackList();

        String pathWebSiteLog = "./webSiteLog.txt";

        try{
            String apacheLog= Files.readString(Paths.get(pathWebSiteLog));
            List<AccessLog> listOfLogs = AccessLog.allAccessLog(apacheLog);

            listOfBots = DNSLookUp.procedureDNS(listOfLogs,whitelist, blacklist);

            if (listOfBots.isEmpty()) {
                System.out.println("Something got wrong :(");
            } else {
                for (AccessLog bot : listOfBots) {
                    System.out.println(bot.toString());
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("whitelist");
        System.out.println(whitelist);
        System.out.println("Number of bots: "+ listOfBots.size());
        return listOfBots;

    }

    /**
     * test the sortIPaddresses method
     * @return a sorted list of the ip addresses
    */
    public static List<String> orderListTest(){
        List<String> ipAddress = new ArrayList<>();
        String pathWebSiteLog = "./webSiteLog.txt";

        try{
            String apacheLog= Files.readString(Paths.get(pathWebSiteLog));

            List<AccessLog> listOfLogs = AccessLog.allAccessLog(apacheLog);
            ipAddress = DNSLookUp.sortIPAddress(listOfLogs);

        }catch (IOException e){
            e.printStackTrace();
        }
        for(String ip: ipAddress){
            System.out.println("ip: "+ ip);
        }
        
        return ipAddress;
    }

    /**
     * test the second dns lookup method (improved)
     * @return a list of the bots detected
     */
    public static List<AccessLog> testDNSlookupImprove(){
        List<AccessLog> listOfBots =  new ArrayList<>();
        List<String> whitelist = BotsList.whiteList();
        List<String> blacklist = BotsList.blackList();

        String pathWebSiteLog = "./webSiteLog.txt";

        try{
            String apacheLog= Files.readString(Paths.get(pathWebSiteLog));
            List<AccessLog> listOfLogs = AccessLog.allAccessLog(apacheLog);


            System.out.println("im here");
            listOfBots = DNSLookUp.procedureDNSImprove(listOfLogs,whitelist, blacklist);
            System.out.println("im here 2");

            if (listOfBots.isEmpty()) {
                System.out.println("Something got wrong :(");
            } else {
                for (AccessLog bot : listOfBots) {
                    System.out.println(bot.toString());
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Number of bots: "+ listOfBots.size());
        return listOfBots;

    }
    
    public static void testDNSImproveEasy(){
        //List<AccessLog> listOfBots =  new ArrayList<>();

        List<String> whiteList = BotsList.whiteList();
        List<String> blackList = BotsList.blackList();

        List<String> logsList= BotsList.logs();
    
        Collections.sort(logsList);
        int i=0;
        for(String ip: logsList){
            if(DNSLookUp.binarySearch(whiteList, ip)<0 && DNSLookUp.binarySearch(blackList, ip)<0){
                try {
                    //reverse DNS lookup --> request IP    
                    System.out.println(ip)  ;

                    InetAddress hostname = InetAddress.getByName(ip);
                    String hostNameString = hostname.getHostName();

                    InetAddress ipAddress = InetAddress.getByName(hostNameString);
                    String ipAddressString = ipAddress.getHostAddress();

                    if(ipAddressString == ip){
                        whiteList.add(ip);
                        System.out.println("good bot");
                    }
                    
                    else{
                       
                        blackList.add(ip);
                        System.out.println(i + " "+"bad bot");
                        i++;
                    }
                } catch (UnknownHostException e) {
                    System.out.println("Unrecognized host");
                    e.printStackTrace();
                }

            }
        }

    }

    
}
