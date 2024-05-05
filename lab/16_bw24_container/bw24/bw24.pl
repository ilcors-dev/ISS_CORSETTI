%====================================================================================
% bw24 description   
%====================================================================================
dispatch( stepdone, stepdone(X) ).
dispatch( stepfailed, stepfailed(X) ).
event( sonardata, sonar(DISTANCE) ).
event( vrinfo, vrinfo(A,B) ).
dispatch( vrinfo, vrinfo(A,B) ).
event( obstacle, obstacle(D) ). %emesso da WEnv
event( wolf, wolf(D) ). %emesso dal raspberry
%====================================================================================
context(ctxbw24, "192.168.0.200",  "TCP", "8120").
 qactor( bw24nomqtt, ctxbw24, "it.unibo.bw24nomqtt.Bw24nomqtt").
 static(bw24nomqtt).
  qactor( bwobserver, ctxbw24, "it.unibo.bwobserver.Bwobserver").
 static(bwobserver).
