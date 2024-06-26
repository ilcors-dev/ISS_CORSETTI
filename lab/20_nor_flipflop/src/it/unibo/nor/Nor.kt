/* Generated by AN DISI Unibo */ 
package it.unibo.nor

import it.unibo.kactor.*
import alice.tuprolog.*
import unibo.basicomm23.*
import unibo.basicomm23.interfaces.*
import unibo.basicomm23.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import it.unibo.kactor.sysUtil.createActor   //Sept2023

//User imports JAN2024

class Nor ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outgreen("$name STARTS")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t04",targetState="handleState",cond=whenRequest("norSET"))
					transition(edgeName="t05",targetState="handleState",cond=whenRequest("norRESET"))
				}	 
				state("handleState") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("norSET(A,B)"), Term.createTerm("norSET(A,B)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 val A = payloadArg(0).toInt() == 1  
								 val B = payloadArg(1).toInt() == 1  
								 val Output = !(A || B);  
								 val O = if(Output) 1 else 0;  
								answer("norSET", "norSETReply", "norSETReply($O)"   )  
						}
						if( checkMsgContent( Term.createTerm("norRESET(A,B)"), Term.createTerm("norRESET(A,B)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 val A = payloadArg(0).toInt() == 1  
								 val B = payloadArg(1).toInt() == 1  
								 val Output = !(A || B);  
								 val O = if(Output) 1 else 0;  
								answer("norRESET", "norRESETReply", "norRESETReply($O)"   )  
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t06",targetState="handleState",cond=whenRequest("norSET"))
					transition(edgeName="t07",targetState="handleState",cond=whenRequest("norRESET"))
				}	 
			}
		}
} 
