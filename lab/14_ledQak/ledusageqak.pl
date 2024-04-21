%====================================================================================
% ledusageqak description   
%====================================================================================
dispatch( ledOn, ledOn(N) ). %led command ON
dispatch( ledOff, ledOff(N) ). %led command OFF
%====================================================================================
context(ctxled, "localhost",  "TCP", "8111").
 qactor( ledusage, ctxled, "it.unibo.ledusage.Ledusage").
 static(ledusage).
  qactor( led, ctxled, "it.unibo.led.Led").
 static(led).
