Google Doc proxy
================

A Google App Engine project which proxies any given Google Doc and caches it using Google's edge cache.

Last used for an interactive showing results for the [Australian general election](http://www.theguardian.com/world/interactive/2013/sep/07/australian-election-2013-senate-results-live)


I have a Google Doc which needs a caching proxy, what do I do?
==============================================================

1. fork this repo
2. create a new GAE app in [GAE's dashboard](https://appengine.google.com/) - make sure you enable billing
3. insert the GAE app name into appengine-web.xml
4. insert the "https://docs.google.com/spreadsheet/pub?key=XXXXXXXX&output=csv" URL into Proxy.scala
5. set APPENGINE_SDK_HOME locally for development (last used version was 1.8.4)
6. sbt
7. gen-idea
8. appengine-dev-server
9. hit http://127.0.0.1:8080/ (because Appengine does not work on localhost out of the box)
10. appengine-deploy
11. after your event has finished, copy the spreadsheet's data INTO your interactive, if possible and delete this app, it costs money
