package com.maxi.debuggerduck.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.maxi.debuggerduck.data.local.model.Error
import com.maxi.debuggerduck.util.Constants.ERROR_LOGS_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
internal interface ErrorDao {

    @Insert
    suspend fun insertLog(log: Error)

    @Query("SELECT * FROM $ERROR_LOGS_TABLE")
    fun getLogs(): Flow<List<Error>>
}


