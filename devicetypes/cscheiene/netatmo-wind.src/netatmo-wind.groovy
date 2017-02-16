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

	tiles (scale: 2) {
		multiAttributeTile(name:"main", type:"generic", width:6, height:4) {
			tileAttribute("WindStrength", key: "PRIMARY_CONTROL") {
            	attributeState "WindStrength",label:'${currentValue}', icon:"st.Weather.weather1", backgroundColors:[
                	[value: 32, color: "#153591"],
                    [value: 44, color: "#1e9cbb"],
                    [value: 59, color: "#90d2a7"],
					[value: 74, color: "#44b621"],
					[value: 84, color: "#f1d801"],
					[value: 92, color: "#d04e00"],
					[value: 98, color: "#bc2323"]
				]
            }
            tileAttribute ("WindAngle", key: "SECONDARY_CONTROL") {
				attributeState "WindAngle", label:'${currentValue}° Average'
			}
		}        
 		valueTile("WindStrength", "WindStrength", width: 2, height: 2, canChangeIcon: false, inactiveLabel: false) {
 			state "default", label:'${currentValue} Average', icon:"st.Weather.weather1"
 		}
 		valueTile("WindAngle", "WindAngle", inactiveLabel: false) {
 			state "default", label:'${currentValue}° Average'
 		}
 		valueTile("GustStrength", "GustStrength", width: 2, height: 2, inactiveLabel: false) {
 			state "default", label:'${currentValue} Gust'
 		}
        valueTile("GustAngle", "GustAngle", width: 2, height: 2, inactiveLabel: false) {
 			state "default", label:'${currentValue}° Gust'            
 		}
        valueTile("max_wind_str", "max_wind_str", width: 2, height: 2, inactiveLabel: false) {
 			state "default", label:'${currentValue} Todays Max'            
 		}
        valueTile("units", "units", width: 2, height: 2, inactiveLabel: false) {
 			state "default", label:'Units: ${currentValue}'            
 		}          
 		standardTile("refresh", "Netatmo Wind", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
 			state "default", action:"refresh.poll", icon:"st.secondary.refresh"
 		}      
 		main (["main"])
 		details(["main", "GustStrength", "GustAngle", "max_wind_str", "units", "refresh" ])
	}
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
}

def poll() {
	parent.poll()
}
