package com.example.littlelemonmkiii.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class MenuItem(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String
)

@Dao
interface MenuDao {
    // Here, MenuItem is actually the name of the table.
    @Query("SELECT * FROM MenuItem")
    fun getAll(): LiveData<List<MenuItem>>
    @Insert
    fun insertAll(vararg menuItems: MenuItem)

    @Query("SELECT (SELECT COUNT(*) FROM MenuItem) == 0")
    fun isEmpty(): Boolean

    @Query("DELETE FROM MenuItem")
    fun deleteAll()
}

@Database(entities = [MenuItem::class], version = 1)
abstract class MenuDatabase: RoomDatabase() {
    abstract fun menuDao(): MenuDao
}