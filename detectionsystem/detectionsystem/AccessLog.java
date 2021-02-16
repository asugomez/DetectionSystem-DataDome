package detectionsystem;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Asuncion Gomez
 */
public class AccessLog {
    private final int identifier;
    private String remoteHost;
    private String identd;
    private String username;
    private String timeRequest; // see the class type
    private String requestClient;
    private String statusCode;
    private String sizeInBytes;
    private String referer;
    private String userAgent;

    private static int lastIdentifier = 0;

    private final String DEFAULT = "-";

    /**
     * Default constructor
     */
    public AccessLog() {
        lastIdentifier ++;
        this.identifier = lastIdentifier;
        this.remoteHost = null;
        this.identd = DEFAULT;
        this.username = DEFAULT;
        this.timeRequest = null;
        this.requestClient = null;
        this.statusCode = null; // TODO: what status Code put here
        this.sizeInBytes = null;
        this.referer = DEFAULT;
        this.userAgent = null;
    }

    /**
     * Constructor by
     * 
     * @param remoteHost
     * @param timeRequest
     * @param requestClient
     * @param statusCode
     * @param sizeInBytes
     * @param referer
     * @param userAgent
     */
    public AccessLog(String remoteHost, String timeRequest, String requestClient, String statusCode, String sizeInBytes,
            String referer, String userAgent) {
        this();
        this.remoteHost = remoteHost;
        this.timeRequest = timeRequest;
        this.requestClient = requestClient;
        this.statusCode = statusCode;
        this.sizeInBytes = sizeInBytes;
        this.referer = referer;
        this.userAgent = userAgent;
    }

    /**
     * Constructor by
     * 
     * @param remoteHost
     * @param identd
     * @param username
     * @param timeRequest
     * @param requestClient
     * @param statusCode
     * @param sizeInBytes
     * @param referer
     * @param userAgent
     */
    public AccessLog(String remoteHost, String identd, String username, String timeRequest, String requestClient,
            String statusCode, String sizeInBytes, String referer, String userAgent) {
                this(remoteHost, timeRequest, requestClient, statusCode, sizeInBytes, referer, userAgent);
                this.identd = identd;
                this.username = username;
    }

    /**
     * Constructor by copie
     */
    public AccessLog(AccessLog accessLog) {
        this();
        if (accessLog != null) {
            this.remoteHost = accessLog.remoteHost;
            this.identd = accessLog.identd;
            this.username = accessLog.username;
            this.timeRequest = accessLog.timeRequest;
            this.requestClient = accessLog.requestClient;
            this.statusCode = accessLog.statusCode;
            this.sizeInBytes = accessLog.sizeInBytes;
            this.referer = accessLog.referer;
            this.userAgent = accessLog.userAgent;
        }
    }

    /**
     * Accesor methos
     * @return identifier
     */
    public int getIdentifier(){
        return this.identifier;
    }

    /**
     * Accesor method
     * 
     * @return remoteHost
     */
    public String getRemoteHost() {
        String remoteHostCopie = this.remoteHost;
        return remoteHostCopie;
    }

    /**
     * Accesor method
     * 
     * @return identd
     */
    public String getIdentd() {
        String identdCopie = this.identd;
        return identdCopie;
    }

    /**
     * Accesor method
     * 
     * @return userName
     */
    public String getUserName() {
        String userNameCopie = this.username;
        return userNameCopie;
    }

    /**
     * Accesor method
     * 
     * @return requestClient
     */
    public String getRequestClient() {
        String requestClientCopie = this.requestClient;
        return requestClientCopie;
    }

    /**
     * Accesor method
     * 
     * @return statusCode
     */
    public String getStatusCode() {
        String statusCodeCopie = this.statusCode;
        return statusCodeCopie;
    }

    /**
     * Accesor method
     * 
     * @return referer
     */
    public String getReferer() {
        String refererCopie = this.referer;
        return refererCopie;
    }

    /**
     * Accesor method
     * 
     * @return userAgent
     */
    public String getUserAgent() {
        String userAgentCopie = this.userAgent;
        return userAgentCopie;
    }

    @Override
    public String toString() {
        return "Bot nº" + identifier + ": " + "\n" + "remoteHost = " + remoteHost + "\t" + "identd = " + identd + "\t" + "username = " + username + "\t"
                + "timerequest = " + timeRequest + "\t" + "requestClient = " + requestClient + "\t" + "statusCode = "
                + statusCode + "\t" + "sizeInBytes = " + sizeInBytes + "\t" + "referer = " + referer + "\t"
                + "userAgent = " + userAgent;
    }

    @Override
    public boolean equals(Object o) {
        AccessLog accessLog = this;
        if (o != null && accessLog.getRemoteHost() == ((AccessLog) o).getRemoteHost()) {
            return true;
        }
        return false;
    }

    /**
     * Function that creates a list of all the acces log
     * 
     * @param apacheLog
     * @return
     */
    public static List<AccessLog> allAccessLog(String apacheLog) {

        List<AccessLog> listAccessLog = new ArrayList<>();

        /**
         * This section is based on this code
         * https://www.geeksforgeeks.org/parsing-apache-access-log-in-java/
         */
        // ---------- BEGIN -------------
        // Creating a regular expression for the records REGEX
        final String accessExpression = "^(\\S+) (\\S+) (\\S+) " + "\\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+)"
                + " (\\S+)\\s*(\\S+)?\\s*\" (\\d{3}) (\\S+)" + " (\\S*)\\s* " + "(\\S*\\s*)";

        final Pattern pattern = Pattern.compile(accessExpression, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(apacheLog);

        // ---------- END -------------

        // Creating a acces log list from the web site log
        while (matcher.find()) {

            String remoteHost = matcher.group(1);
            String identd = matcher.group(2);
            String username = matcher.group(3);
            String timeRequest = matcher.group(4);
            String requestClient = matcher.group(5) + " " + matcher.group(6) + " " + matcher.group(7);
            String statusCode = matcher.group(8);
            String sizeInBytes = matcher.group(9);
            String referer = matcher.group(10);
            String userAgent = matcher.group(11);

            AccessLog accessLog = new AccessLog(remoteHost, identd, username, timeRequest, requestClient, statusCode,
                    sizeInBytes, referer, userAgent);
            listAccessLog.add(accessLog);
        }

        return listAccessLog;
    }

    /**
     * First detection function: uses the user agent information
     * 
     * @param allAccessLog
     * @param whiteList    a list of normal user agent (i.e. not bots)
     * @return a list of bots that are trying to enter the site
     */
    public static List<AccessLog> userAgentDetection(List<AccessLog> allAccessLog, List<String> whiteList) {
        List<AccessLog> botsList = new ArrayList<>();
        for (AccessLog accessLog : allAccessLog) {
            // if the user agent is not in the whitelist or its name contains some of these words--> it's a bot!
            // some of the common words used only for bots and crawlers
            // (https://gist.github.com/geerlingguy/a438b41a9a8f988ee106,
            // https://developers.google.com/search/docs/advanced/crawling/overview-google-crawlers
            // and
            // https://stackoverflow.com/questions/677419/how-to-detect-search-engine-bots-with-php)
            if (!whiteList.contains(accessLog.userAgent)) {
                if (accessLog.userAgent.toUpperCase().contains("BOT")
                        || accessLog.userAgent.toUpperCase().contains("SPIDER")
                        || accessLog.userAgent.toUpperCase().contains("CRAWL")
                        || accessLog.userAgent.toUpperCase().contains("SLURP")
                        || accessLog.userAgent.toUpperCase().contains("FETCH")
                        || accessLog.userAgent.toUpperCase().contains("CURL")
                        || accessLog.userAgent.toUpperCase().contains("MEDIAPARTNERS")
                        || accessLog.userAgent.toUpperCase().contains("TEOMA")
                        || accessLog.userAgent.toUpperCase().contains("XING")
                        || accessLog.userAgent.toUpperCase().contains("WEBMASTER")) {

                    AccessLog accessLogCopie = new AccessLog(accessLog);
                    botsList.add(accessLogCopie);
                }
            }
        }
        return botsList;
    }

    /**
     * Checking the robots.txt, If the bot makes a request to the link, it means
     * it's a bad bot
     * 
     * @param allAccessLog
     * @param robotsTxt
     * @return a list of bots that are trying to enter the site
     */
    public static List<AccessLog> robotsTxtDetection(List<AccessLog> allAccessLog, List<String> robotsTxt) {
        List<AccessLog> botsList = new ArrayList<>();
        for (AccessLog accessLog : allAccessLog) {
            for (String folderForbide : robotsTxt) {
                if (accessLog.requestClient.contains(folderForbide)) {
                    // if the log is not in the botsList
                    if (!botsList.contains(accessLog)) {
                        AccessLog accessLogCopie = new AccessLog(accessLog);
                        botsList.add(accessLogCopie);
                    }
                }

            }
        }
        return botsList;
    }


    /**
     * Function that implements the two algorithms we described below
     * @param allAccessLog
     * @param robotsTxt
     * @param whiteList
     * @return
     */
    public static List<AccessLog> detectionSystem1(List<AccessLog> allAccessLog, List<String> robotsTxt, List<String> whiteList){
        List<AccessLog> botsListRobotsTxt = robotsTxtDetection(allAccessLog, robotsTxt);
        List<AccessLog> botsListUserAgent = userAgentDetection(allAccessLog, whiteList);
        List<AccessLog> botsFinalList = botsListUserAgent;
        for(AccessLog bot : botsListRobotsTxt){
            if(!botsListUserAgent.contains(bot)){
                botsFinalList.add(bot);
            }
        }

        return botsFinalList;

        

    }

    /**
     * Function that do a DNS lookup to check if the IP address is in the whitelist.
     * In cases where it’s coming from an IP address that’s not on the whitelist, we’d want to do the nslookup. 
     * If the address is verified positively, it enters the whitelist. If not, it enters the blacklist 
     * https://www.onely.com/blog/detect-verify-crawlers/
     * @param allAccessLog the list of all the website logs
     * @param whiteList a list with all the ip addresses of the good bots or human's ip
     * @param blackList a list with all the bad bot's ip addresses
     * @return botsList a list of bots founded 
     */
    public static List<AccessLog> procedureDNS(List<AccessLog> allAccessLog, List<String> whiteList, List<String> blackList) {
        List<AccessLog> botsList = new ArrayList<>();
        for (AccessLog accessLog : allAccessLog) {
            //if the ip address is not in the whitelist or in the blacklist
            if(!(whiteList.contains(accessLog.getRemoteHost()) || blackList.contains(accessLog.getRemoteHost()))){
                try {
                    //reverse DNS lookup --> request IP    
                    String ip = accessLog.getRemoteHost();   

                    InetAddress hostname = InetAddress.getByName(ip);
                    String hostNameString = hostname.getHostName();

                    InetAddress ipAddress = InetAddress.getByName(hostNameString);
                    String ipAddressString = ipAddress.getHostAddress();

                    if(ipAddressString == accessLog.getRemoteHost()){
                        whiteList.add(accessLog.getRemoteHost());
                    }
                    else{
                        blackList.add(accessLog.getRemoteHost());
                        botsList.add(accessLog);
                    }
                } catch (UnknownHostException e) {
                    System.out.println("Unrecognized host");
                    e.printStackTrace();
                }

            }
            
        }
        return botsList;
    }
    
    /**
     * Function that implements all the detection algorithms and
     * @return a list of all bots 
    */
    public List<AccessLog> isABot(List<AccessLog> allAccessLog, List<String> whiteList, List<String> robotsTxt) {
        List<AccessLog> botsList = new ArrayList<>();

        //algorithms
        boolean botIsInTheList = false;
        AccessLog newBot = new AccessLog(); //it will be with the information that we recuperated with the algorithms
            for (AccessLog accessLog : botsList) {
                if(accessLog.equals(newBot)){
                    botIsInTheList = true;
                }
            }
            if(!botIsInTheList){
                botsList.add(newBot);
            }
        return botsList;
    }

    /**
     * from the normal range that we defined ??
     * 
     */
    public static List<AccessLog> normalRangeDetection(List<AccessLog> allAccessLog) {
        List<AccessLog> botsList = new ArrayList<>();
        return botsList;
    }

    /*
    * we could also use an algorithm that detect the p ercentage of numerical characters in domain name or/and
    * the percentage of the length of the longest meaningful substring to detect bots
    *
    * A normal human traffic won't show daily similarities in their request count change over time and will not regularly repeat patterns. 
    * And the number the number of IP addresses resolved for a given domain during defined time window shouldn't be so big.
    */

    /**
     * how to make it faster  ??
     * 2: by number og logs alg 3: c alg 4: reverse aproach
     */
}