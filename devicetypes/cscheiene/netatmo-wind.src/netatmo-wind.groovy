/**
 *  netatmo-windmodule
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *     Based on Brian Steere's netatmo-basesatation handler
 *
 */
 
 
metadata {
	definition (name: "Netatmo Wind", namespace: "cscheiene", author: "Brian Steere, cscheiene") {
	    capability "Sensor"	
        attribute "WindStrengt", "number"
        attribute "WindAngle", "number"
        attribute "GustStrength", "number"
        attribute "GustAngle", "number"
        attribute "max_wind_str", "number"
        attribute "units", "string"
        
        command "poll"
	}

	simulator {
		// TODO: define status and reply messages here
	}

	tiles {
 		valueTile("WindStrength", "WindStrength", canChangeIcon: true, inactiveLabel: false) {
 			state "default", label:'${currentValue} Average'
 		}
 		valueTile("WindAngle", "WindAngle", inactiveLabel: false) {
 			state "default", label:'${currentValue}° Average'
 		}
 		valueTile("GustStrength", "GustStrength", inactiveLabel: false) {
 			state "default", label:'${currentValue} Gust'
 		}
        valueTile("GustAngle", "GustAngle", inactiveLabel: false) {
 			state "default", label:'${currentValue}° Gust'            
 		}
        valueTile("max_wind_str", "max_wind_str", inactiveLabel: false) {
 			state "default", label:'${currentValue} Todays Max'            
 		}
        valueTile("units", "units", inactiveLabel: false) {
 			state "default", label:'Units: ${currentValue}'            
 		}          
 		standardTile("refresh", "Netatmo Wind", inactiveLabel: false, decoration: "flat") {
 			state "default", action:"refresh.poll", icon:"st.secondary.refresh"
 		}      
 		main (["WindStrength", "WindAngle", "GustStrength"])
 		details(["WindStrength", "WindAngle", "units", "GustStrength", "GustAngle", "max_wind_str", "refresh" ])
	}
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
}

def poll() {
	parent.poll()
}
