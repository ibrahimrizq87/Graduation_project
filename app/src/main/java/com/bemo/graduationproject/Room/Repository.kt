package com.bemo.graduationproject.Room

import androidx.lifecycle.LiveData



class Repository(private val databaseDao: DatabaseDao) {
 /*   val readAllStations : LiveData<List<Station>> = databaseDao.getAllStations()
    val readAllTrains : LiveData<List<Train>> = databaseDao.getAllTrains()


    fun findStationsByName (stationName:String): LiveData<List<Station>> {
    return databaseDao.findStationByName(stationName)
    }
    fun findTrainByNumber (number:String): LiveData<List<Train>> {
        return databaseDao.findByNumber(number)
    }
    fun findStopsByTrainNumber (number:String): LiveData<List<Stops>> {
        return databaseDao.findStopsByTrainNumber(number)
    }
    fun findStopsByStationName (station:String): LiveData<List<Stops>> {
        return databaseDao.findStopsByStationName(station)
    }


    suspend fun addStation(station: Station){
        databaseDao.insertStation(station)
    }
    suspend fun addTrain(train: Train){
        databaseDao.insertTrain(train)
    }
    suspend fun addStop(stop: Stops){
        databaseDao.insertStop(stop)
    }
*/}