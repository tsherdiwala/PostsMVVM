package net.tsherdiwala.mvvmposts.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import net.tsherdiwala.mvvmposts.model.Post
import net.tsherdiwala.mvvmposts.model.PostDao

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao

}