/*
ioutils.js
*/

    const infoDisplay     = document.getElementById("infoDisplay");
    //const webcamip        = document.getElementById("webcamip");
    const robotDisplay    = document.getElementById("robotDisplay");
    const planexecDisplay = document.getElementById("planexecDisplay");
    const sonarDisplay    = document.getElementById("sonarDisplay");

    function setMessageToWindow(outfield, message) {
         var output = message.replace("\n","<br/>")
         outfield.innerHTML = `<pre>${output}</pre>`
    }

    function addMessageToWindow(outfield, message) {
         var output = message.replace("\n","<br/>")
          outfield.innerHTML += `<div>${output}</div>`
    }
 
//short-hand for $(document).ready(function() { ... });
$(function () {
    $( "#h" ).click(function() { callServerUsingAjax("h") });   
    $( "#w" ).click(function() { callServerUsingAjax("w") });
    $( "#s" ).click(function() { callServerUsingAjax("s") });
    $( "#r" ).click(function() { callServerUsingAjax("r") });
    $( "#l" ).click(function() { callServerUsingAjax("l") });
    $( "#p" ).click(function() { callServerUsingAjax("p") });
    $( "#z" ).click(function() { callDoPlanUsingAjax() });
    //$( "#z" ).click(function() { alert( document.getElementById("platodo") ) });

    //$( "#steptime" ).click(function() { callServerUsingAjax("steptime") });
});


//POST usando Ajax
function callDoPlanUsingAjax( ) {
     planTodo = document.getElementById("platodo").value
     console.log(planTodo)
    $.ajax({
     //imposto il tipo di invio dati (GET O POST)
      type: "POST",
      //Dove  inviare i dati
      url: "doplan",
      //Dati da inviare
      data: "plan=" + planTodo,
      dataType: "html",
     
      //Visualizzazione risultato o errori
      success: function(msg){  //msg CONTIENE LA PAGINA HTML se uso iframe con VR
      
        var r = msg.indexOf("Planresult:")
        if( r > 0 ){
	        //alert(r);
	        var mm = msg.substring(r,r+40)
	        console.log(mm);
	        //setMessageToWindow(infoDisplay,"callDoPlanUsingAjax | " +  planTodo+" sent")
	        setMessageToWindow(infoDisplay, mm)
        }else{
        	setMessageToWindow(infoDisplay, msg)
        }
      },
      error: function(){
        alert("Ajax - Chiamata planTodo fallita, si prega di riprovare...");
        //sempre meglio impostare una callback in caso di fallimento
      }
     });
}
function callServerUsingAjax(message) {
    //alert("callServerUsingAjax "+message)
    $.ajax({
     //imposto il tipo di invio dati (GET O POST)
      type: "POST",
      //Dove  inviare i dati
      url: "robotmove",  //ex robotmove
      //Dati da inviare
      data: "move=" + message,
      dataType: "html",
      //Visualizzazione risultato o errori
      success: function(msg){  //msg ha tutta la pagina ...
        //console.log("call msg="+msg);
        setMessageToWindow(infoDisplay,"callServerUsingAjax | " +  message+" done")
      },
      error: function(){
        alert("Ajax - Chiamata fallita, si prega di riprovare..."); 
        //sempre meglio impostare una callback in caso di fallimento
      }
     });
}

