package com.sanadi.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.sanadi.database.entity.*

/**
 * Surah DAO - الإجراءات على سور القرآن
 */
@Dao
interface SurahDao {
    @Query("SELECT * FROM surahs ORDER BY number ASC")
    fun getAllSurahs(): Flow<List<SurahEntity>>

    @Query("SELECT * FROM surahs WHERE number = :surahNumber")
    suspend fun getSurahByNumber(surahNumber: Int): SurahEntity?

    @Query("SELECT * FROM surahs WHERE isDownloaded = 1 ORDER BY number ASC")
    fun getDownloadedSurahs(): Flow<List<SurahEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSurahs(surahs: List<SurahEntity>)

    @Update
    suspend fun updateSurah(surah: SurahEntity)

    @Query("UPDATE surahs SET isDownloaded = 1, downloadedAt = :timestamp WHERE number = :surahNumber")
    suspend fun markSurahAsDownloaded(surahNumber: Int, timestamp: Long)
}

/**
 * Ayah DAO - الإجراءات على الآيات
 */
@Dao
interface AyahDao {
    @Query("SELECT * FROM ayahs WHERE surahNumber = :surahNumber ORDER BY ayahNumber ASC")
    fun getAyahsBySurah(surahNumber: Int): Flow<List<AyahEntity>>

    @Query("SELECT * FROM ayahs WHERE surahNumber = :surahNumber AND ayahNumber = :ayahNumber")
    suspend fun getAyah(surahNumber: Int, ayahNumber: Int): AyahEntity?

    @Query("SELECT * FROM ayahs WHERE arabicText LIKE '%' || :query || '%' LIMIT 20")
    fun searchAyahs(query: String): Flow<List<AyahEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAyahs(ayahs: List<AyahEntity>)

    @Update
    suspend fun updateAyah(ayah: AyahEntity)
}

/**
 * Bookmark DAO - الإجراءات على الكتب والعلامات
 */
@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmarks ORDER BY createdAt DESC")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    @Query("SELECT * FROM bookmarks WHERE surahNumber = :surahNumber ORDER BY createdAt DESC")
    fun getBookmarksBySurah(surahNumber: Int): Flow<List<BookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkEntity): Long

    @Update
    suspend fun updateBookmark(bookmark: BookmarkEntity)

    @Delete
    suspend fun deleteBookmark(bookmark: BookmarkEntity)
}

/**
 * Reading Progress DAO - الإجراءات على متابعة التقدم
 */
@Dao
interface ReadingProgressDao {
    @Query("SELECT * FROM reading_progress ORDER BY readAt DESC LIMIT 1")
    fun getLastReadingProgress(): Flow<ReadingProgressEntity?>

    @Query("SELECT SUM(timeSpentSeconds) FROM reading_progress")
    fun getTotalReadingTimeSeconds(): Flow<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: ReadingProgressEntity)
}

/**
 * Prayer Times DAO - الإجراءات على أوقات الصلاة
 */
@Dao
interface PrayerTimesDao {
    @Query("SELECT * FROM prayer_times ORDER BY fetchedAt DESC LIMIT 1")
    fun getLatestPrayerTimes(): Flow<PrayerTimesEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrayerTimes(prayerTimes: PrayerTimesEntity)

    @Update
    suspend fun updatePrayerTimes(prayerTimes: PrayerTimesEntity)
}

/**
 * Prayer Notification DAO - الإجراءات على إخطارات الصلاة
 */
@Dao
interface PrayerNotificationDao {
    @Query("SELECT * FROM prayer_notifications WHERE prayerType = :prayerType")
    suspend fun getNotificationSettings(prayerType: String): PrayerNotificationEntity?

    @Query("SELECT * FROM prayer_notifications")
    fun getAllNotificationSettings(): Flow<List<PrayerNotificationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotificationSettings(notification: PrayerNotificationEntity)
}

/**
 * User Settings DAO - الإجراءات على إعدادات المستخدم
 */
@Dao
interface UserSettingsDao {
    @Query("SELECT * FROM user_settings WHERE id = 1")
    fun getUserSettings(): Flow<UserSettingsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserSettings(settings: UserSettingsEntity)

    @Update
    suspend fun updateUserSettings(settings: UserSettingsEntity)
}

/**
 * Daily Verse DAO - الإجراءات على الآية اليومية
 */
@Dao
interface DailyVerseDao {
    @Query("SELECT * FROM daily_verses WHERE date = :date")
    suspend fun getDailyVerse(date: String): DailyVerseEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyVerse(verse: DailyVerseEntity)
}
