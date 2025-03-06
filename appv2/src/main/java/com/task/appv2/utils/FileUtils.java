package com.task.appv2.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.FileProvider;


import com.task.appv2.BuildConfig;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;


public final class FileUtils {

    @SuppressLint("SdCardPath")
    public static final String STORAGE_INTERNAL_PARENT_PATH =  String.format("/data/user/0/%s/files", BuildConfig.APPLICATION_ID);
    public static final String STORAGE_EXTERNAL_PARENT_PATH = String.format("/storage/emulated/0/Android/data/%s/files", BuildConfig.APPLICATION_ID);

    private static final String CAMERA_FOLDER_NAME = "camera";
    private static final String THUMBS_FOLDER_NAME = "Thumbs";
    private static final String CAMERA_IMAGE_NAME = "camera_image1";
    private static final String FILE_PROVIDER_AUTHORITY = "com.task.appv2.fileprovider";
    private static final String TAG = FileUtils.class.getSimpleName();

    // Private constructor to prevent instantiation
    private FileUtils() {
    }

    /**
     * التحقق من وجود المجلد أو إنشائه إذا لم يكن موجودًا
     *
     * @param folder المجلد المراد التحقق منه
     */
    private static void ensureDirectoryExists(@NotNull File folder) {
        if (!folder.exists() && folder.mkdirs()) {
            Log.v(TAG, "Directory created: " + folder.getAbsolutePath());
        }
    }


    /**
     * الحصول على مسار الصورة من الـ URI
     *
     * @param context السياق
     * @param uri     URI للصورة
     * @return مسار الصورة
     */
    public static String getImagePath(Context context, Uri uri) {
        String path = null;
        try (Cursor cursor = context.getContentResolver().query(uri, null, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                String documentId = cursor.getString(0);
                documentId = documentId.substring(documentId.lastIndexOf(":") + 1);
                try (Cursor pathCursor = context.getContentResolver().query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        null,
                        MediaStore.Images.Media._ID + " = ?",
                        new String[]{documentId},
                        null)) {
                    if (pathCursor != null && pathCursor.moveToFirst()) {
                        path = pathCursor.getString(pathCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting image path", e);
        }
        return path;
    }



    /**
     * ضغط الصورة وتحويلها إلى مصفوفة بايت
     *
     * @param bitmap  الصورة
     * @param quality جودة الضغط
     * @return مصفوفة بايت للصورة المضغوطة
     */
    @NotNull
    public static byte[] bitmapToBytes(@NotNull Bitmap bitmap, int quality) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
        return outputStream.toByteArray();
    }


    /**
     * إنشاء مصفوفة بايت بجودة 70 من الصورة المحددة
     *
     * @param bitmap الصورة
     * @return مصفوفة بايت للصورة
     */
    @NotNull
    public static byte[] getGalleryImageBytes(Bitmap bitmap) {
        return bitmapToBytes(bitmap, 70);
    }


    /**
     * فتح ملف باستخدام تطبيقات الجهاز المثبتة
     *
     * @param file    الملف المراد فتحه
     * @param context السياق
     */
    public static void openFile(File file, Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = FileProvider.getUriForFile(context, FILE_PROVIDER_AUTHORITY, file);
            intent.setData(uri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "Cannot open file: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e(TAG, "Error opening file", e);
        }
    }

    /**
     * حفظ بيانات في ملف داخلي أو خارجي
     *
     * @param nameFile   اسم الملف
     * @param data       البيانات المراد حفظها
     * @param isInternal تحديد إذا كان الحفظ داخليًا
     * @return نجاح العملية أم لا
     */
    public static boolean writeToFile(String nameFile, Object data, boolean isInternal) {
        if (nameFile == null || data == null) return false;

        File subDir = isInternal
                ? new File(STORAGE_INTERNAL_PARENT_PATH)
                : new File(STORAGE_EXTERNAL_PARENT_PATH);

        ensureDirectoryExists(subDir);
        File targetFile = new File(subDir, nameFile);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile))) {
            writer.write(data.toString());
            return true;
        } catch (IOException e) {
            Log.e(TAG, "Error writing to file: " + targetFile.getAbsolutePath(), e);
            return false;
        }
    }

    /**
     * قراءة ملف من التخزين الداخلي
     *
     * @param context  السياق
     * @param nameFile اسم الملف
     * @return محتوى الملف أو null في حال حدوث خطأ
     */
    @Nullable
    public static String readInternalFile(Context context, @NotNull String nameFile) {
        StringBuilder data = new StringBuilder();
        //InputStream inputStream = context != null ?
        // new InputStreamReader(context.openFileInput(nameFile)):
        // new FileInputStream(new File(STORAGE_INTERNAL_PARENT_PATH,nameFile));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(context.openFileInput(nameFile)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
            Log.i(TAG, "Read internal file: " + data.toString());
            return data.toString();
        } catch (IOException e) {
            Log.e(TAG, "Error reading internal file: " + nameFile, e);
            return null;
        }
    }


    /**
     * قراءة ملف من التخزين الخارجي
     *
     * @param nameFile اسم الملف
     * @return محتوى الملف أو null في حال حدوث خطأ
     */
    @Nullable
    public static String readExternalFile(Context context, @NotNull String nameFile) {
        //context.getExternalFilesDir(null)
        StringBuilder data = new StringBuilder();
        File externalFile = context != null ?
                new File( context.getExternalFilesDir(null) , nameFile):
                new File(STORAGE_EXTERNAL_PARENT_PATH, nameFile);
        try (BufferedReader reader = new BufferedReader(new FileReader(externalFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
            return data.toString();
        } catch (IOException e) {
            final String msg = "Error reading external file: " + externalFile.getAbsolutePath();
            Log.e(TAG, msg, e);
            return null;
        }
    }

    public static String loadJSONFromAsset(@NotNull Context context, String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

    /**
     * مساعدة لتحميل Bitmap من ملف مع تحديد حجم العينة
     *
     * @param filePath   مسار الملف
     * @param sampleSize حجم العينة
     * @return Bitmap المحملة أو null في حال الفشل
     */
    private static Bitmap decodeBitmapFromFile(String filePath, int sampleSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = sampleSize;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * تغيير حجم الصورة بناءً على sampleSize
     *
     * @param bitmap     الصورة الأصلية
     * @param sampleSize عامل تقليص الحجم
     * @return الصورة بعد تغيير الحجم
     */
    private static Bitmap resizeBitmap(Bitmap bitmap, int sampleSize) {
        if (sampleSize <= 0) {
            throw new IllegalArgumentException("Sample size must be greater than 0");
        }
        int width = bitmap.getWidth() / sampleSize;
        int height = bitmap.getHeight() / sampleSize;
        return Bitmap.createScaledBitmap(bitmap, width, height, true);
    }

}
