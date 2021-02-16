#Technical Challenge 

##Step 1 (Data Analysis):

###Do you think there is bot traffic on this site? Why?
Yes for some many reasons. First, we can see there are a lot of logs from the same remote host in a short time. Then, we can also see in the user-agent some very famomus bots 
(like google bot, bing bot, AhrefsBot, etc).
Also, if we check the robots.txt file of the website, we can see there are some bots trying to enter into the forbidden folders.

###How would you qualify a "normal" human traffic on this site? Based on which criteria?

With the website log of this site, I would detect 

"Known bots are detected via technical detection and validation: HTTP fingerprinting, known AI/custom rule pattern matching, and good bot authentication.

New threats represent the real challenge. They are identified via statistical and behavioral detection, using data from server-side fingerprints, a JS rendering engine, SDK inputs and session tracking." https://datadome.co/bot-management-protection/bot-detection-how-to-identify-bot-traffic-to-your-website/ 

Somme patterns indicating malicious domain are:
• "Short lived domain – a domain, which suddenly appears in the global scope time series and disappears after a short period of activity. If a domain is benign, even if is is not very popular, the number of queries should exceed the threshold at least several times during the monitoring period.
• Daily similarity – this feature checks if there are domains that show daily similarities in their request count change over time.
• Repeating patterns – this feature aims to detect regularly repeating patterns.
• Access ratio – this feature checks whether the domain is generally in an idle state or is accessed continuously.

 Number of distinct IP addresses – the number of IP addresses re- solved for a given domain during defined time window.
• Number of distinct countries.
• Number of domains sharing the IP with.
• Reverse DNS query results – number of reverse DNS queries of the returned IP addresses.

• Average TTL – simple TTL average, used in various detection methods.
• Standard deviation of TTL.
• Number of distinct TTL values.
• Number of TTL changes.
• Percentage usage of specific TTL ranges – malicious traffic tends to set their TTL values to lower values.

• Percentage of numerical characters in domain name.
• Percentage of the length of the longest meaningful substring."

https://geant3.archive.geant.org/media_centre/media_library/media%20library/gn3_jra2_t4_m4_deliverable.pdf 

###Could you focus on 1 good bot and 1 bad bot to tell us more about them?
One good bot is AhrefBots (marketing bot) which, according to their website, it crawls the website making notes of outbound links and adding them to the ahrefbots database. It will periodically re-crawl the website to check the current status of previously found links. It does not collect or store any other information about the website. It does not trigger ads on your website (if any) and won’t add numbers to your Google Analytics traffic.
Also, the good bots will always respects the robots.txt file

A type of bad bot is a Mail address harvesting bot, that is a spider visiting the site harvesting mailto: mail addresses to send spam later. One example is the spam mail address harvesting bot, hosted by GWBN XIAMEN QIANPU broadband access company gwbn.net.cn, CN; later sending Nigerian 419-scam spam via bigpond.com. (http://www.kloth.net/internet/badbots-2003.php)


##Step 2
Implemented in the AccessLog java file and Main java file.