package com.task.appv2.data.source.local;


import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.task.appv2.data.source.local.dao.OrderDao;
import com.task.appv2.data.source.local.dao.UserDao;
import com.task.appv2.data.source.local.entities.OrderDetailEntity;
import com.task.appv2.data.source.local.entities.OrderMasterEntity;
import com.task.appv2.data.source.local.entities.UserEntity;

import org.jetbrains.annotations.NotNull;


@Database(entities =
        {
                UserEntity.class,
                OrderMasterEntity.class,
                OrderDetailEntity.class
        },
        version = AppDatabase.Current_VERSION,
        exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    protected static final int OLD_VERSION = 1;
    protected static final int Current_VERSION = OLD_VERSION;
    //------------------
    private static final String TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    protected static final String DATABASE_NAME = "chef_app_db";

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    Log.d(TAG, "getInstance:Creating new database instance");
                    INSTANCE = createDatabase(context);
                }
            }
        }
        return INSTANCE;
    }

    @NotNull
    private static AppDatabase createDatabase(@NotNull Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                //.addCallback(mCallback)
                .build();
    }

    public abstract UserDao userDao();

    public abstract OrderDao orderDao();
}
