/**
 *  netatmo-windmodule Date: 11.03.2017
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
        attribute "WindStrength", "number"
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
            	attributeState "WindStrength", label:'${currentValue}', icon:"st.Weather.weather1", backgroundColors:[
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
				attributeState "WindAngle", label:'${currentValue}°'
			}
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
		valueTile("battery_percent", "battery_percent", inactiveLabel: false, width: 2, height: 2) {
			state "battery_percent", label:'${currentValue}%', unit:"", backgroundColors:[
                [value: 20, color: "#ff0000"],
                [value: 35, color: "#fd4e3a"],
                [value: 50, color: "#fda63a"],
                [value: 60, color: "#fdeb3a"],
                [value: 75, color: "#d4fd3a"],
                [value: 90, color: "#7cfd3a"],
                [value: 99, color: "#55fd3a"]
            ]
		}             
 		main (["main"])
 		details(["main", "GustStrength", "GustAngle", "max_wind_str", "units"])
	}
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
}

def poll() {
	parent.poll()
}
