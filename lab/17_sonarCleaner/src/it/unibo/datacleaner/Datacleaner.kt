/* Generated by AN DISI Unibo */ 
package it.unibo.datacleaner

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

class Datacleaner ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		 var D = 0;  
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						subscribeToLocalActor("sonardevice") 
						CommUtils.outblue("$name subscribed to sonardevice")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t010",targetState="filter",cond=whenEvent("sonardata"))
				}	 
				state("filter") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("distance(D)"), Term.createTerm("distance(D)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								  D = payloadArg(0).toInt()  
								if(  D < 300  
								 ){emitLocalStreamEvent("sonardata", "distance($D)" ) 
								}
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t011",targetState="filter",cond=whenEvent("sonardata"))
				}	 
			}
		}
} 
