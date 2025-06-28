package com.maxi.debuggerduck.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.maxi.debuggerduck.data.local.model.Info
import com.maxi.debuggerduck.util.Constants.INFO_LOGS_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
internal interface InfoDao {

    @Insert
    suspend fun insertLog(log: Info)

    @Query("SELECT * FROM $INFO_LOGS_TABLE")
    fun getLogs(): Flow<List<Info>>
}