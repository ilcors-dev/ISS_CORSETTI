%====================================================================================
% flipflopsystem description   
%====================================================================================
request( state, state(A,B) ).
reply( output, output(O) ).  %%for state
request( norSET, norSET(A,B) ).
reply( norSETReply, norSETReply(O) ).  %%for norSET
request( norRESET, norRESET(A,B) ).
reply( norRESETReply, norRESETReply(O) ).  %%for norRESET
%====================================================================================
context(ctxflipflop, "localhost",  "TCP", "8000").
 qactor( flipflop, ctxflipflop, "it.unibo.flipflop.Flipflop").
 static(flipflop).
  qactor( nor, ctxflipflop, "it.unibo.nor.Nor").
 static(nor).
  qactor( flipflopmock, ctxflipflop, "it.unibo.flipflopmock.Flipflopmock").
 static(flipflopmock).
