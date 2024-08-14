package com.example.bus_web_api

data class RouteStop(
    val routeId: String,
    val stopName: String
)

data class TimeTuple(
    val time: String,
    val destination: String
)