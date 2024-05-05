/* Generated by AN DISI Unibo */ 
package it.unibo.sonarmqtt

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

class Sonarmqtt ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		 var D = 0;  
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						delay(7000) 
						CommUtils.outblack("$name STARTS")
						forward("sonarstart", "sonarstart(1)" ,"sonardevice" ) 
						subscribeToLocalActor("distancefilter") 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t00",targetState="handleObstacle",cond=whenEvent("obstacle"))
				}	 
				state("handleObstacle") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("obstacle(D)"), Term.createTerm("obstacle(D)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								  D = payloadArg(0).toInt()  
								CommUtils.outgreen("$name OBSTACLE DETECTED ($D)")
								emit("wolf", "wolf($D)" ) 
								delay(200) 
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t01",targetState="handleObstacle",cond=whenEvent("obstacle"))
				}	 
			}
		}
} 
