package com.example.bus_web_api

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository


interface BusRepositorys{
    fun find(): List<RouteStop>
    fun search(headsign: String): MutableList<String>
    fun getTimeTable(stopName:String): List<TimeTuple>
}

@Repository
class RouteStopRepositoryImpl(val namedParameterJdbcTemplate: NamedParameterJdbcTemplate) : BusRepositorys {
    override fun find(): List<RouteStop> {
        val sql = """
        SELECT
            DISTINCT
            trips.route_id,
            stops.stop_name
        FROM
            trips,
            stop_times,
            stops
        WHERE
            trips.trip_id = stop_times.trip_id
            and
            stop_times.stop_id = stops.stop_id
        ;
    """.trimIndent()
        val sqlParams = MapSqlParameterSource()
        val stopMap = namedParameterJdbcTemplate.queryForList(sql, sqlParams)
        return stopMap.map {
            RouteStop(
                it["route_id"].toString(),
                it["stop_name"].toString()
            )
        }
    }
    override fun search(headsign:String): MutableList<String> {
        val sql = """
        SELECT
            DISTINCT
            stops.stop_name
        FROM
            trips,
            stop_times,
            stops
        WHERE
            trips.trip_headsign = :headsign
            and
            trips.trip_id = stop_times.trip_id
            and
            stop_times.stop_id = stops.stop_id
        ;
    """.trimIndent()
        val sqlParams = MapSqlParameterSource().addValue("headsign", headsign)
        val stopMap = namedParameterJdbcTemplate.queryForList(sql, sqlParams)
        val li = mutableListOf<String>()
        stopMap.forEach{
            li.add(it["stop_name"].toString())
        }
        return li
    }

    override fun getTimeTable(stopName:String): List<TimeTuple>{
        val sql="""
            SELECT
                stop_times.departure_time,
                trips.trip_headsign
            FROM
                trips,
                stop_times,
                stops
        WHERE
            stops.stop_name = :stopName
            and
            stops.stop_id = stop_times.stop_id
            and
            stop_times.trip_id = trips.trip_id
        """.trimIndent()
        val sqlParams = MapSqlParameterSource().addValue("stopName", stopName)
        val timeMap = namedParameterJdbcTemplate.queryForList(sql, sqlParams)
        return timeMap.map {
            TimeTuple(
                it["departure_time"].toString(),
                it["trip_headsign"].toString()
            )
        }
    }
}
