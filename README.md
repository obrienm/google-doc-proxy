Google Doc proxy
================

A Google App Engine project which proxies any given Google Doc and caches it using Google's edge cache.

Last used for an interactive showing results for the [Australian general election](http://www.theguardian.com/world/datablog/interactive/2013/sep/06/australian-election-results-map)

You must have APPENGINE_SDK_HOME set (last used version was 1.8.4)

sbt
gen-idea
appengine-dev-server
appengine-deploy

Use this URL because Appengine does not work on localhost out of the box http://127.0.0.1:8080/
