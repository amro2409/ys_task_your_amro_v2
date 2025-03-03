package com.task.appv2.data.models;

import com.google.gson.annotations.SerializedName;
import com.task.appv2.data.source.local.entities.OrderMasterEntity;
import com.task.appv2.data.source.local.entities.UserEntity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UsrData {
    @SerializedName("UserData")
    private UserEntity userEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @NotNull
    @Override
    public String toString() {
        return "UsrData{" +
                "userEntity=" + userEntity +
                '}';
    }
}
