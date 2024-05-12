%====================================================================================
% cleaner24 description   
%====================================================================================
dispatch( stepdone, stepdone(V) ). %automessaggio
dispatch( stepfail, stepfail(X) ). %automessaggio
%====================================================================================
context(ctxcleaner24, "localhost",  "TCP", "8032").
 qactor( cleaner24, ctxcleaner24, "it.unibo.cleaner24.Cleaner24").
 static(cleaner24).
