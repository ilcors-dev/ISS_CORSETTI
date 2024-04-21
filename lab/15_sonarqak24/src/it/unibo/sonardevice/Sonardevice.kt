/* Generated by AN DISI Unibo */ 
package it.unibo.sonardevice

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

class Sonardevice ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		 var D: String? = "0"; var process: Process? = null;  
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t06",targetState="start",cond=whenDispatch("sonarstart"))
					transition(edgeName="t07",targetState="stop",cond=whenDispatch("sonarstop"))
				}	 
				state("stop") { //this:State
					action { //it:State
						
									process?.destroy();
						CommUtils.outcyan("SONAR STOPPED")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t08",targetState="stop",cond=whenDispatch("sonarstop"))
				}	 
				state("start") { //this:State
					action { //it:State
						
									process = process ?: Runtime.getRuntime().exec("python3 /Users/ilcors-dev/src/unibo/ISS_CORSETTI/lab/15_sonarqak24/src/sonar_mock.py");
									val reader = java.io.BufferedReader(java.io.InputStreamReader(process?.inputStream))
									val D = reader.readLine();
						emitLocalStreamEvent("sonardata", "distance($D)" ) 
						delay(500) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="start", cond=doswitch() )
				}	 
			}
		}
} 
