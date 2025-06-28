package com.maxi.debuggerduck.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maxi.debuggerduck.data.local.dao.DebugDao
import com.maxi.debuggerduck.data.local.dao.ErrorDao
import com.maxi.debuggerduck.data.local.dao.InfoDao
import com.maxi.debuggerduck.data.local.dao.VerboseDao
import com.maxi.debuggerduck.data.local.dao.WarningDao
import com.maxi.debuggerduck.data.local.model.Debug
import com.maxi.debuggerduck.data.local.model.Error
import com.maxi.debuggerduck.data.local.model.Info
import com.maxi.debuggerduck.data.local.model.Verbose
import com.maxi.debuggerduck.data.local.model.Warning
import com.maxi.debuggerduck.util.Constants.DEBUGGER_DUCK_DATABASE

@Database(
    entities = [Verbose::class, Debug::class, Info::class,
        Warning::class, Error::class],
    version = 1,
    exportSchema = false
)
internal abstract class DebuggerDuckDatabase : RoomDatabase() {

    abstract fun verboseDao(): VerboseDao

    abstract fun debugDao(): DebugDao

    abstract fun infoDao(): InfoDao

    abstract fun warningDao(): WarningDao

    abstract fun errorDao(): ErrorDao

    companion object {

        @Volatile
        private var INSTANCE: DebuggerDuckDatabase? = null

        fun getInstance(context: Context): Any {
            return INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(
                        context,
                        DebuggerDuckDatabase::class.java,
                        DEBUGGER_DUCK_DATABASE
                    )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}