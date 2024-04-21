%====================================================================================
% pingpong24 description   
%====================================================================================
dispatch( ball, ball(N) ). %info exchanged by the players
%====================================================================================
context(ctxping, "localhost",  "TCP", "8014").
 qactor( ping, ctxping, "it.unibo.ping.Ping").
 static(ping).
  qactor( pong, ctxping, "it.unibo.pong.Pong").
 static(pong).
