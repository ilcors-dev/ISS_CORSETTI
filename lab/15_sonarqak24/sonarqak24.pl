%====================================================================================
% sonarqak24 description   
%====================================================================================
dispatch( sonarstart, sonarstart(X) ).
dispatch( sonarstop, sonarstop(X) ).
event( sonardata, distance(D) ).
event( obstacle, obstacle(D) ).
%====================================================================================
context(ctxsonarqak24, "localhost",  "TCP", "8000").
 qactor( sonar24, ctxsonarqak24, "it.unibo.sonar24.Sonar24").
 static(sonar24).
  qactor( distancefilter, ctxsonarqak24, "it.unibo.distancefilter.Distancefilter").
 static(distancefilter).
  qactor( datacleaner, ctxsonarqak24, "it.unibo.datacleaner.Datacleaner").
 static(datacleaner).
  qactor( sonardevice, ctxsonarqak24, "it.unibo.sonardevice.Sonardevice").
 static(sonardevice).
