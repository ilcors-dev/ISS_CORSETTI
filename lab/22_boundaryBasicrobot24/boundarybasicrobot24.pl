%====================================================================================
% boundarybasicrobot24 description   
%====================================================================================
dispatch( cmd, cmd(MOVE) ).
request( engage, engage(CALLER,TIME) ).
reply( engagedone, engagedone(ARG) ).  %%for engage
reply( engagerefused, engagerefused(ARG) ).  %%for engage
event( obstacle, obstacle(X) ).
event( sonardata, sonar(DISTANCE) ).
request( step, step(TIME) ).
reply( stepdone, stepdone(V) ).  %%for step
reply( stepfailed, stepfailed(DURATION,CAUSE) ).  %%for step
dispatch( setdirection, dir(D) ).
%====================================================================================
context(ctxboundarybasicrobot, "localhost",  "TCP", "8088").
context(ctxbasicrobot, "127.0.0.1",  "TCP", "8020").
 qactor( basicrobot, ctxbasicrobot, "external").
  qactor( boundary, ctxboundarybasicrobot, "it.unibo.boundary.Boundary").
 static(boundary).
