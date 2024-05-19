%====================================================================================
% coldstorageservice description   
%====================================================================================
request( store, store(Quantity) ).
request( unload, unload(T) ).
reply( reply, reply(T) ).  %%for store
reply( reject, reject(M) ).  %%for store
reply( unloadFailed, unloadFailed(Reason) ).  %%for unload
reply( unloadOk, unloadOk(OK) ).  %%for unload
%====================================================================================
context(ctxcoldstorage, "localhost",  "TCP", "8000").
 qactor( coldstorageservice, ctxcoldstorage, "it.unibo.coldstorageservice.Coldstorageservice").
 static(coldstorageservice).
  qactor( drivermock, ctxcoldstorage, "it.unibo.drivermock.Drivermock").
 static(drivermock).
