package detectionsystem;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @author Asuncion Gomez
*/
public class DNSLookUp {

    /**
     * Method that do a DNS lookup to check if the IP address is in the whitelist.
     * In cases where it’s coming from an IP address that’s not on the whitelist, we’d want to do the nslookup. 
     * If the address is verified positively, it enters the whitelist. If not, it enters the blacklist 
     * https://www.onely.com/blog/detect-verify-crawlers/
     * @param allAccessLog the list of all the website logs
     * @param whiteList a list with all the ip addresses of the good bots or human's ip
     * @param blackList a list with all the bad bot's ip addresses
     * @return botsList a list of bad bots detected 
     */
    public static List<AccessLog> procedureDNS(List<AccessLog> allAccessLog, List<String> whiteList, List<String> blackList) {
        
        List<AccessLog> botsList = new ArrayList<>();

        for (AccessLog accessLog : allAccessLog) {
            String ip = accessLog.getRemoteHost(); 
            //if the ip address is not in the whitelist or in the blacklist
            if(!(whiteList.contains(ip) || blackList.contains(ip))){
                try {
                    //reverse DNS lookup --> request IP    
                    InetAddress hostname = InetAddress.getByName(ip);
                    String hostNameString = hostname.getHostName();

                    //if timeReques > ... --> add to the black (white) list

                    InetAddress ipAddress = InetAddress.getByName(hostNameString);
                    String ipAddressString = ipAddress.getHostAddress();

                    if(ipAddressString.equals(ip)){
                        whiteList.add(ip);                        
                    }
                    
                    else{
                        blackList.add(ip);
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
     * Improves the procedureDNS method
     * Another improve not implemented --> limite the number of hostname
     * @param allAccessLog
     * @param whiteList
     * @param blackList
     * @return a list of bad bots detected
     */
    public static List<AccessLog> procedureDNSImprove(List<AccessLog> allAccessLog, List<String> whiteList, List<String> blackList) {
        List<AccessLog> botsList = new ArrayList<>();
        for (AccessLog accessLog : allAccessLog) {
            
            String ip = accessLog.getRemoteHost();
            //if the hostname is not in the white or black list -->
            if(binarySearch(whiteList, ip) < 0 && binarySearch(blackList, ip) < 0 ){
                try {
                    //reverse DNS lookup --> request IP    
                    InetAddress hostname = InetAddress.getByName(ip);
                    String hostNameString = hostname.getHostName();

                    //if timeReques > ... --> add to the black (white) list

                    InetAddress ipAddress = InetAddress.getByName(hostNameString);
                    String ipAddressString = ipAddress.getHostAddress();

                    if(ipAddressString.equals(ip)){
                        whiteList.add(ip);
                    }
                    else{
                        blackList.add(ip);
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
     * Method that order the ip addresses from a list of access log
     * @param allAccessLog
     * @return a sorted ip addresses list
     */
    public static List<String> sortIPAddress(List<AccessLog> allAccessLog){

        List<String> addressList = new ArrayList<>();

        if(allAccessLog!=null){
            //Create a list only of the ip addresses
            for (AccessLog log : allAccessLog) {
                //there are some letters inside the ip address (3 logs only)
                if(!(log.getRemoteHost().contains("a") || log.getRemoteHost().contains("b"))){
                    addressList.add(log.getRemoteHost());
                }
            }

            //code from https://stackoverflow.com/questions/44079260/java-how-to-sort-a-list-of-ip-address 
            Collections.sort(addressList, (a, b) -> {
                int[] aOct = Arrays.stream(a.split("\\.")).mapToInt(Integer::parseInt).toArray();
                int[] bOct = Arrays.stream(b.split("\\.")).mapToInt(Integer::parseInt).toArray();
                int r = 0;
                for (int i = 0; i < aOct.length && i < bOct.length; i++) {
                    r = Integer.compare(aOct[i], bOct[i]);
                    if (r != 0) {
                        return r;
                    }
                }
                return r;
            });
        }
        return addressList;
    }

    /**
     * Implements the binary search on a list of string
     */
    public static int binarySearch(List<String> logsList, String ipAddress){
        Collections.sort(logsList);
        int index = Collections.binarySearch(logsList, ipAddress);
        return index;
    } 

    //TODO: lire ligne par ligne
    
    
    
}
