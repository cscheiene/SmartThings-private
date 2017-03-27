/**
 *  netatmo-rain module Date: 27.03.2017
 *
 *  Copyright 2014 Brian Steere
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
 *  Based on Brian Steere's netatmo-rain DTH
 *
 */
metadata {
	definition (name: "Netatmo Rain", namespace: "cscheiene", author: "Brian Steere,cscheiene") {
	    capability "Sensor"
        capability "Battery"
        attribute "rain", "number"
        attribute "rainSumHour", "number"
        attribute "rainSumDay", "number"
        attribute "units", "string"
        
        command "poll"
	}

	simulator {
		// TODO: define status and reply messages here
	}

	tiles (scale: 2) {
		multiAttributeTile(name:"main", type:"generic", width:6, height:4) {
			tileAttribute("rain", key: "PRIMARY_CONTROL") {
            	attributeState "rain",label:'${currentValue}', icon:"st.Weather.weather12", backgroundColor:"#00a0dc"
            }
            tileAttribute ("rainSumHour", key: "SECONDARY_CONTROL") {
				attributeState "rainSumHour", label:'Last Hour: ${currentValue}'
			}
		} 
 		valueTile("rainSumDay", "device.rainSumDay", width: 2, height: 2, inactiveLabel: false) {
 			state "default", label:'Total Day:${currentValue}'
 		}
        valueTile("units", "units", width: 2, height: 2, inactiveLabel: false) {
 			state "default", label:'Units: ${currentValue}'            
 		}
		valueTile("battery", "device.battery", inactiveLabel: false, width: 2, height: 2) {
			state "battery_percent", label:'Battery: ${currentValue}%', backgroundColors:[
                [value: 20, color: "#ff0000"],
                [value: 35, color: "#fd4e3a"],
                [value: 50, color: "#fda63a"],
                [value: 60, color: "#fdeb3a"],
                [value: 75, color: "#d4fd3a"],
                [value: 90, color: "#7cfd3a"],
                [value: 99, color: "#55fd3a"]
            ]
		}        
		valueTile("rain", "device.rain") {
 			state "rain",label:'${currentValue}', icon:"st.Weather.weather12", backgroundColor:"#00a0dc"
 		}
 		main (["rain",])
 		details(["main", "rainSumDay", "battery", "units"])
	}
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
}

def poll() {
	parent.poll()
}