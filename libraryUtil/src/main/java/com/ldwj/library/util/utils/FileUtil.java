package com.ldwj.library.util.utils;

import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 文件辅助类
 */
public class FileUtil {

    /**
     * 在SD卡上创建文件
     *
     * @throws IOException
     */
    public static File creatSDFile(String fileName) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
        }
        return file;
    }

    /**
     * 在SD卡上创建目录
     *
     * @param dirName
     */
    public static File creatSDDir(String dirName) {
        File dir = new File(dirName);
        dir.mkdirs();
        return dir;
    }

    /**
     * 判断SD卡上的文件夹是否存在
     */
    public static boolean isFileExist(String fileName) {

        try {
            File file = new File(fileName);
            return file.exists();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据文件绝对路径获取文件名
     *
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath))
            return "";
        return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
    }

    /**
     * 将一个InputStream里面的数据写入到SD卡中
     */
    public static File saveFile(File file, Bitmap photo) {

        FileOutputStream fOut = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fOut = new FileOutputStream(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        photo.compress(Bitmap.CompressFormat.PNG, 100, fOut);// 把Bitmap对象解析成流
        try {
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public static String getSizeName(double size) {
        if (String.valueOf(size).length() > 0) {
            DecimalFormat format = new DecimalFormat("0.00");

            if (size < 1024) {
                return size + "B";
            } else if (size >= 1024 && size < 1024 * 1024) {
                return format.format((double) size / 1024) + "KB";
            } else {
                return format.format((double) size / 1024 / 1024) + "M";

            }
        }
        return null;
    }



    /**
     * 删除本地文件
     */
    public static void deleleFile(String path) {
        if (checkSDCard() && !TextUtils.isEmpty(path)) {// 存在sdcard
            File file = new File(path);
            if (file.exists()) {
                file.delete();
                System.out.println("--执行本地删除成功--");
            }
        }
    }

    public static boolean checkSDCard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }

//	public static int freeSpaceOnSd() {
//		StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
//		double sdFreeMB = ((double) stat.getAvailableBlocks() * (double) stat.getBlockSize()) / Common.MB;
//		return (int) sdFreeMB;
//	}

    /**
     * 文件重命名
     *
     */
    public static void makefilenewName(File orifile, String newName) {
        try {
            String newFileName = parseServerName(newName);
            File file = new File(orifile.getAbsolutePath());
            File newFile = new File(orifile.getParent(), newFileName);
            if (file.exists()) {
                file.renameTo(newFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String parseSeverstr(String str) {
        String newName = null;
        try {
            if (null != str && str.length() > 5) {
                JSONObject j = new JSONObject(str);
                newName = j.getString("filePath");
                return newName;
            }
        } catch (Exception e) {
        }
        return newName;
    }

    public static String parseServerName(String str) {
        String newFileName = null;
        if (null != str && !"".equals(str) && str.contains("/")) {
            newFileName = str.substring(str.lastIndexOf("/") + 1, str.length());
            return newFileName;
        }
        return newFileName;
    }

    public static void deleteFile(File file) {
        if (file.exists()) {                    //判断文件是否存在
            if (file.isFile()) {                    //判断是否是文件
                file.delete();                       //delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory()) {              //否则如果它是一个目录
                File files[] = file.listFiles();               //声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) {            //遍历目录下所有的文件
                    deleteFile(files[i]);             //把每个文件 用这个方法进行迭代
                }
            }
            file.delete();
        } else {
            System.out.println("所删除的文件不存在！" + '\n');
        }
    }
    /**
     * 将html文件保存到sd卡
     */
    private static void writeFile(String content, String path)
    {
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try
        {
            File file = new File(path);
            if (!file.exists())
            {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            bw = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"));
            bw.write(content);
        }
        catch (FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        finally
        {
            try
            {
                if (bw != null) bw.close();
                if (fos != null) fos.close();
            }
            catch (IOException ie)
            {
                ie.printStackTrace();

            }
        }
    }

    /**
     * 压缩文件
     * @param src
     * @param outDest
     * @throws IOException
     */
    public static void zip(String src, String outDest) throws IOException {
        // 提供了一个数据项压缩成一个ZIP归档输出流
        ZipOutputStream out = null;
        try {

            File outFile = new File(outDest);// 源文件或者目录
            File fileOrDirectory = new File(src);// 压缩文件路径
            out = new ZipOutputStream(new FileOutputStream(outFile));
            // 如果此文件是一个文件，否则为false。
            if (fileOrDirectory.isFile()) {
                zipFileOrDirectory(out, fileOrDirectory, "");
            } else {
                // 返回一个文件或空阵列。
                File[] entries = fileOrDirectory.listFiles();
                for (int i = 0; i < entries.length; i++) {
                    // 递归压缩，更新curPaths
                    zipFileOrDirectory(out, entries[i], "");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            // 关闭输出流
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private static void zipFileOrDirectory(ZipOutputStream out,
                                           File fileOrDirectory, String curPath) throws IOException {
        // 从文件中读取字节的输入流
        FileInputStream in = null;
        try {
            // 如果此文件是一个目录，否则返回false。
            if (!fileOrDirectory.isDirectory()) {
                // 压缩文件
                byte[] buffer = new byte[4096];
                int bytes_read;
                in = new FileInputStream(fileOrDirectory);
                // 实例代表一个条目内的ZIP归档
                ZipEntry entry = new ZipEntry(curPath
                        + fileOrDirectory.getName());
                // 条目的信息写入底层流
                out.putNextEntry(entry);
                while ((bytes_read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytes_read);
                }
                out.closeEntry();
            } else {
                // 压缩目录
                File[] entries = fileOrDirectory.listFiles();
                for (int i = 0; i < entries.length; i++) {
                    // 递归压缩，更新curPaths
                    zipFileOrDirectory(out, entries[i], curPath
                            + fileOrDirectory.getName() + "/");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            // throw ex;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * 解压
     * @param zipFileName
     * @param outputDirectory
     * @throws IOException
     */
    public static void unzip(String zipFileName, String outputDirectory)
            throws IOException {
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(zipFileName);
            Enumeration e = zipFile.entries();
            ZipEntry zipEntry = null;
            File dest = new File(outputDirectory);
            dest.mkdirs();
            while (e.hasMoreElements()) {
                zipEntry = (ZipEntry) e.nextElement();
                String entryName = zipEntry.getName();
                InputStream in = null;
                FileOutputStream out = null;
                try {
                    if (zipEntry.isDirectory()) {
                        String name = zipEntry.getName();
                        name = name.substring(0, name.length() - 1);
                        File f = new File(outputDirectory + File.separator
                                + name);
                        f.mkdirs();
                    } else {
                        int index = entryName.lastIndexOf("\\");
                        if (index != -1) {
                            File df = new File(outputDirectory + File.separator
                                    + entryName.substring(0, index));
                            df.mkdirs();
                        }
                        index = entryName.lastIndexOf("/");
                        if (index != -1) {
                            File df = new File(outputDirectory + File.separator
                                    + entryName.substring(0, index));
                            df.mkdirs();
                        }
                        File f = new File(outputDirectory + File.separator
                                + zipEntry.getName());
                        // f.createNewFile();
                        in = zipFile.getInputStream(zipEntry);
                        out = new FileOutputStream(f);
                        int c;
                        byte[] by = new byte[1024];
                        while ((c = in.read(by)) != -1) {
                            out.write(by, 0, c);
                        }
                        out.flush();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    throw new IOException("解压失败：" + ex.toString());
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException ex) {
                        }
                    }
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException ex) {
                        }
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException("解压失败：" + ex.toString());
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException ex) {
                }
            }
        }
    }
}