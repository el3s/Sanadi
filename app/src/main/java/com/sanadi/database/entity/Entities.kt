package com.sanadi.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

/**
 * Surah Entity - تخزين سور القرآن
 */
@Entity(tableName = "surahs")
@Serializable
data class SurahEntity(
    @PrimaryKey
    val number: Int,
    val name: String,
    val nameArabic: String,
    val englishNameTranslation: String,
    val numberOfAyahs: Int,
    val revelationType: String, // MECCAN or MEDINAN
    val ayahsDownloaded: Int = 0,
    val isDownloaded: Boolean = false,
    val downloadedAt: Long? = null
)

/**
 * Ayah Entity - تخزين الآيات
 */
@Entity(tableName = "ayahs")
@Serializable
data class AyahEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val surahNumber: Int,
    val ayahNumber: Int,
    val arabicText: String,
    val arabicTextWarsh: String? = null,
    val arabicTextHafs: String? = null,
    val translation: String? = null,
    val tafsir: String? = null,
    val audioUrlHafs: String? = null,
    val audioUrlWarsh: String? = null,
    val audioDownloadedHafs: Boolean = false,
    val audioDownloadedWarsh: Boolean = false
)

/**
 * Bookmark Entity - الكتب والعلامات المرجعية
 */
@Entity(tableName = "bookmarks")
@Serializable
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val surahNumber: Int,
    val ayahNumber: Int,
    val title: String,
    val notes: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val color: Int = 0xFFBB86FC.toInt() // Purple by default
)

/**
 * Reading Progress Entity - متابعة تقدم القراءة
 */
@Entity(tableName = "reading_progress")
@Serializable
data class ReadingProgressEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val surahNumber: Int,
    val ayahNumber: Int,
    val readAt: Long = System.currentTimeMillis(),
    val timeSpentSeconds: Int = 0,
    val recitationType: String = "HAFS" // HAFS or WARSH
)

/**
 * Prayer Times Entity - أوقات الصلاة
 */
@Entity(tableName = "prayer_times")
@Serializable
data class PrayerTimesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String,
    val latitude: Double,
    val longitude: Double,
    val fajrTime: String,
    val sunriseTime: String,
    val dhuhrTime: String,
    val asrTime: String,
    val maghribTime: String,
    val ishaTime: String,
    val method: Int = 2, // Aladhan method
    val school: Int = 0, // Fiqh school
    val adjustments: String = "{}", // JSON adjustments
    val fetchedAt: Long = System.currentTimeMillis(),
    val isFavorite: Boolean = false
)

/**
 * Prayer Notification Entity - إخطارات الصلاة
 */
@Entity(tableName = "prayer_notifications")
@Serializable
data class PrayerNotificationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val prayerType: String, // FAJR, DHUHR, ASR, MAGHRIB, ISHA
    val notificationType: String = "SOUND", // SOUND, VIBRATION, SILENT
    val notificationMinutesBefore: Int = 0,
    val isEnabled: Boolean = true,
    val isAutoPlayAdhan: Boolean = true,
    val reminderCount: Int = 0 // Number of times reminded
)

/**
 * Downloaded Content Entity - المحتوى المُحمل
 */
@Entity(tableName = "downloaded_content")
@Serializable
data class DownloadedContentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val contentType: String, // SURAH, TAFSIR, HADITH
    val contentId: String,
    val contentName: String,
    val fileSize: Long,
    val filePath: String,
    val downloadedAt: Long = System.currentTimeMillis(),
    val version: String = "1.0"
)

/**
 * User Settings Entity - إعدادات المستخدم
 */
@Entity(tableName = "user_settings")
@Serializable
data class UserSettingsEntity(
    @PrimaryKey
    val id: Int = 1,
    val theme: String = "SYSTEM", // SYSTEM, LIGHT, DARK
    val language: String = "AR",
    val fontSize: Int = 18,
    val recitationType: String = "HAFS",
    val defaultMufti: String = "Abdul Basit",
    val prayerMethod: Int = 2,
    val prayerSchool: Int = 0,
    val enableNotifications: Boolean = true,
    val enableLocation: Boolean = false,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val autoDownloadAudio: Boolean = false,
    val wifiOnlyDownload: Boolean = true,
    val lastBackupTime: Long? = null
)

/**
 * Daily Verse Entity - الآية اليومية
 */
@Entity(tableName = "daily_verses")
@Serializable
data class DailyVerseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String,
    val surahNumber: Int,
    val ayahNumber: Int,
    val arabicText: String,
    val translation: String? = null,
    val featuredAt: Long = System.currentTimeMillis()
)
