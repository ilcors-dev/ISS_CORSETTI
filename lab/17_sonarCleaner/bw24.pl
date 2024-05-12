%====================================================================================
% bw24 description   
%====================================================================================
dispatch( sonarstart, sonarstart(X) ).
dispatch( sonarstop, sonarstop(X) ).
event( sonardata, distance(D) ).
event( obstacle, obstacle(D) ).
dispatch( doread, doread(X) ).
event( alarm, alarm(D) ). %
%====================================================================================
context(ctxsonar, "localhost",  "TCP", "8120").
context(ctxcleaner24, "192.168.148.174",  "TCP", "8032").
 qactor( cleaner24, ctxcleaner24, "external").
  qactor( sonarnomqtt, ctxsonar, "it.unibo.sonarnomqtt.Sonarnomqtt").
 static(sonarnomqtt).
  qactor( sonardevice, ctxsonar, "it.unibo.sonardevice.Sonardevice").
 static(sonardevice).
  qactor( datacleaner, ctxsonar, "it.unibo.datacleaner.Datacleaner").
 static(datacleaner).
  qactor( distancefilter, ctxsonar, "it.unibo.distancefilter.Distancefilter").
 static(distancefilter).
