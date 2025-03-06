package com.task.appv2.utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.task.appv2.data.models.ApiResponse;
import com.task.appv2.data.models.OrderData;


import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JsonUtils {
    private static final Object LOCK = new Object();
    private static final String TAG = "JsonUtils";
    private static JsonUtils mInstance;
//
    private JsonUtils() {
    }

    public static JsonUtils getInstance() {
        if (mInstance == null) {
            synchronized (LOCK) {
                mInstance = new JsonUtils();
            }
        }
        return mInstance;
    }

    public  boolean saveDataInFileFormatJson( String nameFile, Object data) {
        String dataJson = new Gson().toJson(data);
        boolean isS = com.task.appv2.utils.FileUtils.writeToFile(nameFile, dataJson, false);
        Log.d(TAG, "IsSave:" + isS);
        return isS;
    }

    /**
     *
     * @param context app
     * @param nameFile name file
     * @param type      Type listType = new TypeToken<List<Model>>(){}.getType();
     * @param <T> generics type parameter
     * @return list type Model
     */
    public <T> List<T> retrieveDataFromJsonList(Context context, String nameFile, Type type) {
        String jsonData =  FileUtils.readExternalFile(context, nameFile);
        return new Gson().fromJson(jsonData, type);
    }

    /**
     *map
     * @param context app
     * @param nameFile name file
     * @param type      Type listType = new TypeToken<List<Model>>(){}.getType();
     * @param <T> generics type parameter
     * @return list type Model
     */
    public <T,K> Map<K,T> retrieveDataFromJsonMap(Context context, String nameFile, Type type) {
        String jsonData = FileUtils.readExternalFile(context, nameFile);
        return new Gson().fromJson(jsonData, type);
    }



}
