package com.task.appv2.data.source.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.task.appv2.data.source.local.entities.UserEntity;

@Dao
public interface UserDao extends BaseDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity entity);

    @Query("DELETE FROM UserData")
    void delete();

    @Query("SELECT *FROM UserData")
    UserEntity getUserData1();

    @Query("SELECT *FROM UserData")
    LiveData<UserEntity> getUserData();
}
