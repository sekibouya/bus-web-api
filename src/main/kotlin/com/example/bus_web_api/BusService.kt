package com.example.bus_web_api

import org.springframework.stereotype.Service


interface BusService {
    fun find(): List<RouteStop>
    fun search(headsign:String): List<String>
    fun getTimeTable(stopName:String): List<TimeTuple>
}

@Service
class AllStopsServiceImpl(val busRepository: BusRepositorys) : BusService {
    override fun find(): List<RouteStop> {
        return busRepository.find()
    }
    override fun search(headsign:String): List<String> {
        return busRepository.search(headsign)
    }
    override fun getTimeTable(stopName:String): List<TimeTuple>{
        return busRepository.getTimeTable(stopName)
    }
}