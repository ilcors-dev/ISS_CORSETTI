%====================================================================================
% bw24 description   
%====================================================================================
dispatch( sonarstart, sonarstart(X) ).
dispatch( sonarstop, sonarstop(X) ).
event( sonardata, distance(D) ).
event( obstacle, obstacle(D) ).
dispatch( doread, doread(X) ).
event( wolf, wolf(D) ). %simile ad obstacle, ma propagato a bw24 in rete
%====================================================================================
context(ctxbw24, "localhost",  "TCP", "8120").
 qactor( sonarnomqtt, ctxbw24, "it.unibo.sonarnomqtt.Sonarnomqtt").
 static(sonarnomqtt).
  qactor( sonardevice, ctxbw24, "it.unibo.sonardevice.Sonardevice").
 static(sonardevice).
  qactor( datacleaner, ctxbw24, "it.unibo.datacleaner.Datacleaner").
 static(datacleaner).
  qactor( distancefilter, ctxbw24, "it.unibo.distancefilter.Distancefilter").
 static(distancefilter).
