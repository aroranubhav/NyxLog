package com.maxi.debuggerduck.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.maxi.debuggerduck.data.local.model.Warning
import com.maxi.debuggerduck.util.Constants.WARNING_LOGS_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
internal interface WarningDao {

    @Insert
    suspend fun insertLog(log: Warning)

    @Query("SELECT * FROM $WARNING_LOGS_TABLE")
    fun getLogs(): Flow<List<Warning>>
}
