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

/**
 * @author Asuncion Gomez
 */
public class Main {

    /**
     * @return robosTxt a list of the folder that are forbidden
     */
    public static List<String> robotsTxtList() {
        List<String> robotsTxt = new ArrayList<>();
        robotsTxt.add("/administrator");
        robotsTxt.add("/cache");
        robotsTxt.add("/components");
        robotsTxt.add("/images");
        robotsTxt.add("/includes");
        robotsTxt.add("/installation");
        robotsTxt.add("/language");
        robotsTxt.add("/libraries");
        robotsTxt.add("/media");
        robotsTxt.add("/plugins");
        robotsTxt.add("/templates");
        robotsTxt.add("/tmp");
        robotsTxt.add("/xmlrpc");
        return robotsTxt;
    }

    /**
     * @return a list with good agents users
     */
    public static List<String> whiteList() {
        List<String> whiteList = new ArrayList<>();
        whiteList.add("123.123.123.123");
        whiteList.add("42.236.10.125");
        whiteList.add("87.247.143.30");
        whiteList.add("66.249.66.158");
        whiteList.add("54.36.149.55");
        return whiteList;
    }

    /**
     * @return a list with all the bad bots
     */
    public static List<String> blackList() {
        List<String> blackList = new ArrayList<>();
        blackList.add("42.236.10.125");
        blackList.add("42.236.10.117");
        return blackList;
    }

    /**
     * 
     * @return some of logs
     */
    public static List<String> logs(){
        List<String> logsList = new ArrayList<>(); //run the sorted list file and then put some ips here 
        logsList.add("1.53.169.162");
        logsList.add("3.88.103.130");
        logsList.add("193.106.31.130");
        logsList.add("173.255.176.5");
        logsList.add("81.16.140.75");
        logsList.add("162.158.203.24");
        logsList.add("193.106.31.130");
        logsList.add("13.66.139.0");
        logsList.add("54.36.149.55");
        logsList.add("223.238.202.132");
        logsList.add("3.91.100.6");
        return logsList;
    }


    
    /**
     * @return log string without bots
    */
    public static String listWithoutBots() {
        String listApache = "123.123.123.123 - - [26/Apr/2000:00:23:48 -0400] \"GET /pics/wpaper.gif HTTP/1.0\" 200 6248 \"http://www.jafsoft.com/asctortf/\" \"Mozilla/4.05 (Macintosh; I; PPC)\" \" -\" \n"
                + "123.123.123.123 - - [26/Apr/2000:00:23:47 -0400] \"GET /asctortf/ HTTP/1.0\" 200 8130 \"http://search.netscape.com/Computers/Data_Formats/Document/Text/RTF\" \"Mozilla/4.05 (Macintosh; I; PPC)\" \" -\" \n"
                + "123.123.123.124 - - [26/Apr/2000:00:23:48 -0400] \"GET /pics/5star2000.gif HTTP/1.0\" 200 4005 \"http://www.jafsoft.com/asctortf/\" \"Mozilla/4.05 (Macintosh; I; PPC)\" \" -\" \n"
                + "123.123.123.123 - - [26/Apr/2000:00:23:50 -0400] \"GET /pics/5star.gif HTTP/1.0\" 404 1031 \"http://www.jafsoft.com/asctortf/\" \"Mozilla/4.05 (Macintosh; I; PPC)\" \" -\" \n"
                + "123.123.123.126 - - [26/Apr/2000:00:23:51 -0400] \"GET /pics/a2hlogo.jpg HTTP/1.0\" 200 4282 \"http://www.jafsoft.com/asctortf/\" \"Mozilla/4.05 (Macintosh; I; PPC)\"\" -\" \n"
                + "123.123.123.123 - - [26/Apr/2000:00:23:51 -0400] \"GET /cgi-bin/newcount?jafsof3&width=4&font=digital&noshow HTTP/1.0\" 200 36 \"http://www.jafsoft.com/asctortf/\" \"Mozilla/4.05 (Macintosh; I; PPC)\" \" -\" \n"
                + "42.236.10.125 - - [19/Dec/2020:15:23:10 +0100] \"GET / HTTP/1.1\" 200 10479 \"http://baidu.com/\" \"Mozilla/5.0 (Linux; U; Android 8.1.0; zh-CN; EML-AL00 Build/HUAWEIEML-AL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.108 baidu.sogo.uc.UCBrowser/11.9.4.974 UWS/2.13.1.48  Mobile Safari/537.36 AliApp(DingTalk/4.5.11) com.alibaba.android.rimet/10487439 Channel/227200 language/zh-CN\" \"-\" \n"
                + "87.247.143.30 - - [23/Dec/2020:11:16:59 +0100] \"GET /index.php?option=com_contact&view=contact&id=1 HTTP/1.1\" 200 9873 \"-\" \"Mozilla/5.0(Linux;Android10;SM-A750FN)AppleWebKit/537.36(KHTML,likeGecko)Chrome/85.0.4183.101MobileSafari/537.36\" \"-\" \n"
                + "87.247.143.30 - - [23/Dec/2020:11:17:01 +0100] \"POST /index.php?option=com_contact&view=contact&id=1 HTTP/1.1\" 200 188 \"-\" \"Mozilla/5.0(Linux;Android10;SM-A750FN)AppleWebKit/537.36(KHTML,likeGecko)Chrome/85.0.4183.101MobileSafari/537.36\" \"-\" \n";

        return listApache;
    }

    /**
     * Creates a apache log string with some famous bots like google bot
     * 
     * @return log string
    */
    public static String listWithtBotsBOT() {
        String listApache = "216.244.66.230 - - [19/Dec/2020:14:14:26 +0100] \"GET /robots.txt HTTP/1.1\" 200 304 \"-\" \"MozillaDotBot/5.0 (compatible; DotBot/1.1; http://www.opensiteexplorer.org/dotbot, help@moz.com)\"\" -\" \n"
                + "54.36.149.55 - - [19/Dec/2020:16:06:42 +0100] \"GET /index.php?option=com_content&view=article&id=46&Itemid=54 HTTP/1.1\" 200 8938 \"-\" \"MoAhrefsBotzilla/5.0 (compatible; AhrefsBot/7.0; +http://ahrefs.com/robot/)\"\" -\" \n"
                + "66.249.66.158 - - [19/Dec/2020:17:11:04 +0100] \"GET / HTTP/1.1\" 200 10479 \"-\" \"Mozilla/5.0Googlebot (Linux; Android 6.0.1; Nexus 5X Build/MMB29P) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.90 Mobile Safari/537.36 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)\"\" -\" \n"
                + "157.48.153.185 - - [19/Dec/2020:14:08:06 +0100] \"GET /apache-log/access.log HTTP/1.1\" 200 233 \"-\" \"Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36\"\" -\" \n";
        return listApache;
    }

    /**
     * Creates a list of apache logs with bots trying to enter to some of the
     * forbidden folder
    */
    public static String listWithROBots() {
        String listApache = "42.236.10.114 - - [19/Dec/2020:15:23:11 +0100] \"GET /templates/jp_hotel/css/menu.css HTTP/1.1\" 200 1457 \"http://www.almhuette-raith.at/\" \"Mozilla/5.0 (Linux; U; Android 8.1.0; zh-CN; EML-AL00 Build/HUAWEIEML-AL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.108 baidu.sogo.uc.UCBrowser/11.9.4.974 UWS/2.13.1.48 Mobile Safari/537.36 AliApp(DingTalk/4.5.11) com.alibaba.android.rimet/10487439 Channel/227200 language/zh-CN \" \"-\" \n"
                + "42.236.10.114 - - [19/Dec/2020:15:23:11 +0100] \"GET /templates/jp_hotel/css/suckerfish.css HTTP/1.1\" 200 3465 \"http://www.almhuette-raith.at/\" \"Mozilla/5.0 (Linux; U; Android 8.1.0; zh-CN; EML-AL00 Build/HUAWEIEML-AL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.108 baidu.sogo.uc.UCBrowser/11.9.4.974 UWS/2.13.1.48 Mobile Safari/537.36 AliApp(DingTalk/4.5.11) com.alibaba.android.rimet/10487439 Channel/227200 language/zh-CN \" \"-\" \n"
                + "42.236.10.125 - - [19/Dec/2020:15:23:12 +0100] \"GET /media/system/js/caption.js HTTP/1.1\" 200 1963 \"http://www.almhuette-raith.at/\" \"Mozilla/5.0 (Linux; U; Android 8.1.0; zh-CN; EML-AL00 Build/HUAWEIEML-AL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.108 baidu.sogo.uc.UCBrowser/11.9.4.974 UWS/2.13.1.48 Mobile Safari/537.36 AliApp(DingTalk/4.5.11) com.alibaba.android.rimet/10487439 Channel/227200 language/zh-CN \" \"-\"\n";
        return listApache;
    }

    /**
     * test using the user agent detection in a list without bots
    */
    public static List<AccessLog> testNoBots1() {
        List<String> whitelist = whiteList();

        String apacheLog = listWithoutBots();
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
        List<String> robotsTxt = robotsTxtList();

        String apacheLog = listWithoutBots();
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
        List<String> whitelist = whiteList();

        String apacheLog = listWithtBotsBOT();
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
        List<String> robotsTxt = robotsTxtList();

        String apacheLog = listWithROBots();
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
        List<String> robotsTxt = robotsTxtList();

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
        List<String> whitelist = whiteList();

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
        List<String> robotsTxt = robotsTxtList();
        List<String> whitelist = whiteList();

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
        List<String> whitelist = whiteList();
        List<String> blacklist = blackList();

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
        List<String> whitelist = whiteList();
        List<String> blacklist = blackList();

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

        List<String> whiteList = whiteList();
        List<String> blackList = blackList();

        List<String> logsList= logs();
    
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
    public static void main(String[] args) throws FileNotFoundException {

        int number = 0;
        File botsFolder = new File("botsFolder");
        botsFolder.mkdirs();
        /*if (!botsFolder.exists()){
            botsFolder.mkdirs();
        }*/
        // --------- TEST 0 ------------ //
        File botsFile = new File("./botsFolder/botsFile" + number +".txt");
        PrintStream stream = new PrintStream(botsFile);
        System.setOut(stream);   
        testNoBots1();

        number++;

        // --------- TEST 1 ------------ //
        botsFile = new File("./botsFolder/botsFile" + number +".txt");
        stream = new PrintStream(botsFile);
        System.setOut(stream); 
        testNoBots2();

        number++;

        // --------- TEST 2 ------------ //
        botsFile = new File("./botsFolder/botsFile" + number +".txt");
        stream = new PrintStream(botsFile);
        System.setOut(stream); 
        testBotsUserAgent();

        number++;
        
        // --------- TEST 3 ------------ //
        botsFile = new File("./botsFolder/botsFile" + number +".txt");
        stream = new PrintStream(botsFile);
        System.setOut(stream); 
        testROBotsTXT();   

        number++;

        // --------- TEST 4 ------------ //
        botsFile = new File("./botsFolder/botsFile" + number +".txt");
        stream = new PrintStream(botsFile);
        System.setOut(stream); 
        //finalTestRobotsTXT();

        number++;

        // --------- TEST 5 ------------ //
        botsFile = new File("./botsFolder/botsFile" + number +".txt");
        stream = new PrintStream(botsFile);
        System.setOut(stream); 
        finalTestUserAgent();
        System.out.println("will not work until we fixe the regex problem in the method allAccessLog in AccessLoj.java file");

        number++;

        // --------- TEST 6 ------------ //
        botsFile = new File("./botsFolder/botsFile" + number +".txt");
        stream = new PrintStream(botsFile);
        System.setOut(stream); 
        finalTestTwoAlgorithms();     
        
        number++;

        // --------- TEST 7 ------------ //
        botsFile = new File("./botsFolder/botsFile" + number +".txt");
        stream = new PrintStream(botsFile);
        System.setOut(stream); 
        //procedureDNStest();  

        
        number++;

        // --------- TEST 8 ------------ //
        botsFile = new File("./botsFolder/botsFile" + number +".txt");
        stream = new PrintStream(botsFile);
        System.setOut(stream); 
        testDNSImproveEasy();

        number++;

        // --------- TEST 9 ------------ //
        botsFile = new File("./botsFolder/botsFile" + number +".txt");
        stream = new PrintStream(botsFile);
        System.setOut(stream); 
        orderListTest();

        /*
        number++;

        // --------- TEST 10 (not fast) ------------ //
        botsFile = new File("./botsFile/botsFile" + number +".txt");
        stream = new PrintStream(botsFile);
        System.setOut(stream); 
        testDNSlookupImprove();
        */
    

    }



}
