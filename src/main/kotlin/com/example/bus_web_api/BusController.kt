package com.example.bus_web_api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

@RestController
class BusController(val busService: BusService) {

    @GetMapping("/stops")
    fun read(): String {
        val m = mutableMapOf<String, MutableList<String>>()
        busService.find().forEach{
            if(m.containsKey(it.routeId)){
                m[it.routeId]?.add(it.stopName)
            }else{
                m[it.routeId]= mutableListOf(it.stopName)
            }
        }

        return Json.encodeToString(m)
    }

    @GetMapping("/stops/{trip_headsign}")
    fun search(@PathVariable("trip_headsign") headsign:String) :String{
        return Json.encodeToString(busService.search(headsign))
    }

    @GetMapping("/timeTable/{stopName}")
    fun getTimeTable(@PathVariable("stopName") stopName:String) :String{
        val m = mutableMapOf<String, MutableList<String>>()
        busService.getTimeTable(stopName).forEach{
            if(m.containsKey(it.destination)){
                m[it.destination]?.add(it.time)
            }else{
                m[it.destination]= mutableListOf(it.time)
            }
        }
        return Json.encodeToString(m)
    }
}