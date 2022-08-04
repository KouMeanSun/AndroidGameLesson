package com.gmy.gamelesson.utils;

import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Vector;

public class FileStorageHelper {
    private static final String TAG = "FileStorageHelper";
    private static final String SEPARATOR = File.separator;//路径分隔符

    /**
     * 把 fer_map 数据写到本地文件中
     * @param fer_map       fer_map 数据
     * @param filePath      文件路径
     * @param fileName      文件名
     */
    public static void writeFerToFile(float[] fer_map,String filePath,String fileName){
        JSONArray jsonArray = new JSONArray();
        if (null == fer_map){
            return ;
        }
        for (int i=0;i<fer_map.length;i++){
            try {
                jsonArray.put(fer_map[i]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        writeTxtToFile(jsonArray.toString(),filePath,fileName);
    }

    public static void transNv21ByteArrayToJPEGAndWriteIntoFile(byte[] mNv21Data,int imageWidth,int imageHeight,String filePath,String fileName){
        YuvImage image = new YuvImage(mNv21Data, ImageFormat.NV21,imageWidth,imageHeight,null);
        ByteArrayOutputStream outputSteam = new ByteArrayOutputStream();
        image.compressToJpeg(new Rect(0, 0, image.getWidth(), image.getHeight()), 70, outputSteam);
        byte[] jpegData = outputSteam.toByteArray();

        FileOutputStream fos = null;

        String fileFullPathName = filePath+File.separator+fileName;

        //文件
        File tempFile = new File(fileFullPathName);
        try {
            //
            fos = new FileOutputStream(tempFile);
            fos.write(jpegData);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 把视频 的帧 存到本地文件中
     * @param mNv21Data   视频帧数据流
     * @param filePath    文件名
     * @param fileName      文件名
     */
    public static void writeByteArrayIntoFile(byte[] mNv21Data,String filePath,String fileName){
        FileOutputStream fos = null;

        String fileFullPathName = filePath+File.separator+fileName;

        //文件
        File tempFile = new File(fileFullPathName);
        try {
            //
            fos = new FileOutputStream(tempFile);
            fos.write(mNv21Data);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 把 assets 中的 文件 复制到指定文件夹
     * @param context       上下文
     * @param fileName      文件名
     * @param storagePath   要存储的路径
     */
    public static void copyFilesFromAssets(Context context, String fileName, String storagePath){
        try {
            InputStream inputStream = context.getResources().getAssets().open(fileName);
            File file = new File(storagePath);

            if (!file.exists()) {//如果文件夹不存在，则创建新的文件夹
                file.mkdirs();
            }
            readInputStream(storagePath + SEPARATOR + fileName, inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //路径例如： /SD卡/Android/data/程序的包名/cache/uniqueName
    public static String getDiskCacheDir(Context context) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

    /**
     * 复制res/raw中的文件到指定目录
     *
     * @param context     上下文
     * @param id          资源ID
     * @param fileName    文件名
     * @param storagePath 目标文件夹的路径
     */
    public static void copyFilesFromRaw(Context context, int id, String fileName, String storagePath) {
        InputStream inputStream = context.getResources().openRawResource(id);
        File file = new File(storagePath);
        if (!file.exists()) {//如果文件夹不存在，则创建新的文件夹
            file.mkdirs();
        }
        readInputStream(storagePath + SEPARATOR + fileName, inputStream);
    }

    /**
     * 读取输入流中的数据写入输出流
     *
     * @param storagePath 目标文件路径
     * @param inputStream 输入流
     */
    public static void readInputStream(String storagePath, InputStream inputStream) {
        File file = new File(storagePath);
        try {
            if (!file.exists()) {
                // 1.建立通道对象
                FileOutputStream fos = new FileOutputStream(file);
                // 2.定义存储空间
                byte[] buffer = new byte[inputStream.available()];
                // 3.开始读文件
                int lenght = 0;
                while ((lenght = inputStream.read(buffer)) != -1) {// 循环从输入流读取buffer字节
                    // 将Buffer中的数据写到outputStream对象中
                    fos.write(buffer, 0, lenght);
                }
                fos.flush();// 刷新缓冲区
                // 4.关闭流
                fos.close();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Vector<String> getFileName(String fileAbsolutePath) {
        Vector<String> vecFile = new Vector<String>();
        File file = new File(fileAbsolutePath);
        File[] subFile = file.listFiles();

        for (int iFileLength = 0; iFileLength < subFile.length; iFileLength++) {
            // 判断是否为文件夹
            if (!subFile[iFileLength].isDirectory()) {
                String filename = subFile[iFileLength].getName();
                Log.e("FileStorageHelper","文件名 ： " + filename);
            }
        }
        return vecFile;
    }

    // 将字符串写入到文本文件中
    private static void writeTxtToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        makeFilePath(filePath, "");

        String strFilePath = filePath +"/"+ fileName;
        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d("TestFile", "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }
    }

//生成文件

    private static File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }else {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

//生成文件夹

    private static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }
}
