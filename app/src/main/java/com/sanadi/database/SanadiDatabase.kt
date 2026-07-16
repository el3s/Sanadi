package com.sanadi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sanadi.database.dao.*
import com.sanadi.database.entity.*

/**
 * Sanadi Database - قاعدة البيانات المركزية
 */
@Database(
    entities = [
        SurahEntity::class,
        AyahEntity::class,
        BookmarkEntity::class,
        ReadingProgressEntity::class,
        PrayerTimesEntity::class,
        PrayerNotificationEntity::class,
        DownloadedContentEntity::class,
        UserSettingsEntity::class,
        DailyVerseEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class SanadiDatabase : RoomDatabase() {
    abstract fun surahDao(): SurahDao
    abstract fun ayahDao(): AyahDao
    abstract fun bookmarkDao(): BookmarkDao
    abstract fun readingProgressDao(): ReadingProgressDao
    abstract fun prayerTimesDao(): PrayerTimesDao
    abstract fun prayerNotificationDao(): PrayerNotificationDao
    abstract fun userSettingsDao(): UserSettingsDao
    abstract fun dailyVerseDao(): DailyVerseDao

    companion object {
        @Volatile
        private var INSTANCE: SanadiDatabase? = null

        fun getInstance(context: Context): SanadiDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    SanadiDatabase::class.java,
                    "sanadi_database"
                )
                    .enableMultiInstanceInvalidation()
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
