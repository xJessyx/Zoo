package com.jessy.zoo.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

//@Database(entities = [DaraAll::class], version = 1, exportSchema = false)
abstract class StylishDatabase : RoomDatabase() {
    abstract val publisherDatabaseDao: PublisherDatabaseDao

}