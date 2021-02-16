package detectionsystem;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @author Asuncion Gomez
 */
public class dnsLookUp {

    /**
     * Method that do a DNS lookup to check if the IP address is in the whitelist.
     * In cases where it’s coming from an IP address that’s not on the whitelist, we’d want to do the nslookup. 
     * If the address is verified positively, it enters the whitelist. If not, it enters the blacklist 
     * https://www.onely.com/blog/detect-verify-crawlers/
     * @param allAccessLog the list of all the website logs
     * @param whiteList a list with all the ip addresses of the good bots or human's ip
     * @param blackList a list with all the bad bot's ip addresses
     * @return botsList a list of bots detected 
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
     * Improves the procedureDNS method
     * Store the data in a structured way, so that queries are run relatively fast, although the storing itself is slower.

     *ordenarlos de mayor a menor y hacer busqueda binaria 
     * which is better: array or linked list?
     * @param allAccessLog
     * @param whiteList
     * @param blackList
     * @return a list of bots detected
     */
    public static List<AccessLog> procedureDNSImprove(List<AccessLog> allAccessLog, List<String> whiteList, List<String> blackList) {
        List<AccessLog> botsList = new ArrayList<>();
        

        return botsList;

    }
    //implement a compareTo

    //with binary search we have a complexity of O(logN) on the worst case (betther than linear search = O(N))
    public static boolean binarySearch(List<String> logsList, String ipAddress){

        return false;
    }
    
    
}
