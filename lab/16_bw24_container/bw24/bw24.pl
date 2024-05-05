%====================================================================================
% bw24 description   
%====================================================================================
mqttBroker("broker.hivemq.com", "1883", "batman_bw24data").
dispatch( stepdone, stepdone(X) ).
dispatch( stepfailed, stepfailed(X) ).
event( sonardata, sonar(DISTANCE) ).
event( vrinfo, vrinfo(A,B) ).
dispatch( vrinfo, vrinfo(A,B) ).
event( obstacle, obstacle(D) ). %emesso da WEnv
event( wolf, wolf(D) ). %emesso dal raspberry
%====================================================================================
context(ctxbw24, "localhost",  "TCP", "8120").
 qactor( bw24mqtt, ctxbw24, "it.unibo.bw24mqtt.Bw24mqtt").
 static(bw24mqtt).
  qactor( bwobserver, ctxbw24, "it.unibo.bwobserver.Bwobserver").
 static(bwobserver).
