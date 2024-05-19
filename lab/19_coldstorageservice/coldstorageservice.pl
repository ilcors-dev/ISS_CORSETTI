%====================================================================================
% coldstorageservice description   
%====================================================================================
request( store, store(Quantity) ).
reply( reply, reply(M) ).  %%for store
reply( reject, reject(M) ).  %%for store
%====================================================================================
context(ctxcoldstorage, "localhost",  "TCP", "8000").
 qactor( coldstorageservice, ctxcoldstorage, "it.unibo.coldstorageservice.Coldstorageservice").
 static(coldstorageservice).
  qactor( drivermock, ctxcoldstorage, "it.unibo.drivermock.Drivermock").
 static(drivermock).
