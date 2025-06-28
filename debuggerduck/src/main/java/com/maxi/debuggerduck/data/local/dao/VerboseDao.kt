package com.maxi.debuggerduck.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.maxi.debuggerduck.data.local.model.Verbose
import com.maxi.debuggerduck.util.Constants.VERBOSE_LOGS_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
internal interface VerboseDao {

    @Insert
    suspend fun insertLog(log: String)

    @Query("SELECT * FROM $VERBOSE_LOGS_TABLE")
    fun getLogs(): Flow<List<Verbose>>
}