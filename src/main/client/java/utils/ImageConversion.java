package client.java.utils;

import java.io.*;

public class ImageConversion {
    public static void byteToImage(byte[] a,String path,String fileName){
        BufferedOutputStream bos= null;
        FileOutputStream fos =null;
        File file =new File(path+"\\"+fileName);

        try {
            fos= new FileOutputStream(file);
            bos= new BufferedOutputStream(fos);
            bos.write(a);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
