%====================================================================================
% cleaner24 description   
%====================================================================================
dispatch( stepdone, stepdone(V) ). %automessaggio
dispatch( stepfail, stepfail(X) ). %automessaggio
event( alarm, alarm(TIME) ). %alarm
event( pause, pause(TIME) ). %pause
%====================================================================================
context(ctxcleaner24, "localhost",  "TCP", "8032").
 qactor( cleaner24, ctxcleaner24, "it.unibo.cleaner24.Cleaner24").
 static(cleaner24).
  qactor( sentinel, ctxcleaner24, "it.unibo.sentinel.Sentinel").
 static(sentinel).
