#Technical Challenge 
 
detect some normal use (humman traffic) --> put this dinamically into a whitelist and 
so first check is the user is inside thw white listthen, if not --> search for the ip and do the DNS work
if it a human --> put into the whitelist
if it's not --> it is a bot
??BUUT is it's a bot we will have to do this a lot of times

it is good to detect bots by their ip?


##Step 1 (Data Analysis):

###Do you think there is bot traffic on this site? Why?
Yes for some many reasons. First, we can see there are a lot of logs from the same remote host in a short time. Then, we can also see in the user-agent some very famomus bots 
(like google bot, bing bot, AhrefsBot, etc).
Also, if we check the robots.txt file of the website, we can see there are some logs trying to enter to the forbidden folders 

###How would you qualify a "normal" human traffic on this site? Based on which criteria?
According to the paper http://ceur-ws.org/Vol-2086/AICS2017_paper_41.pdf 
"The authors identified four feature sets (...) that could signal a domain with malicious intent :
– Time-based features: Short life, daily similarity (in terms of number of re- quests per time of day), repeating patterns and access ratio (popular versus idle domain).
– DNS answer-based features: Number of distinct IP addresses, number of distinct countries, number of domains sharing the IP address, reverse DNS query results.
– TTL-based features: Average TTL, TTL standard deviation, number of dis- tinct TTL values, number of TTL changes, percentage usage of specific TTL values.
- Domain name-based features: Percentage of numerical characters in a domain name, percentage of the length of LMS."

https://geant3.archive.geant.org/media_centre/media_library/media%20library/gn3_jra2_t4_m4_deliverable.pdf

These set of features is based on the time, when the request was made. The time itself isn’t very useful by itself, however, when we analyze many requests to a particular domain over time, patterns indicating malicious domain may emerge. The authors propose to use following set of features:
• Short lived domain – a domain, which suddenly appears in the global scope time series and disappears after a short period of activity. If a domain is benign, even if is is not very popular, the number of queries should exceed the threshold at least several times during the monitoring period.
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

1. Percentage of numerical characters in domain name.
2. Percentage of the length of the longest meaningful substring.

###Could you focus on 1 good bot and 1 bad bot to tell us more about them?
Marketing bots such as AhrefsBots
and Scraper bots 
https://datadome.co/bot-detection/good-bots-vs-bad-bots-and-when-you-should-block-them/ 

##Step 2
Implemented in the AccessLog java file and Main java file.