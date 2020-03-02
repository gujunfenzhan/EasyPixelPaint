package com.mhxks.paint.init;

import java.io.*;

/**
 * Creative by GoldMain on 2020/3/2
 */
public class ModConfigLoader {
    public final static String IMG_CONFIG_PATH = "config/PaintConfig.cfg";
    public final static String IMG_PATH = "paint/aaa.png";
    public final static String WEN = "paint";
    public int x;
    public int y;
    public int z;
    public int xRotate;
    public int yRotate;
    public int zRotate;
    public void loadConfig(){
        check();
        try {
            BufferedReader br = new BufferedReader(new FileReader(IMG_CONFIG_PATH));
            x = Integer.parseInt(br.readLine().replace("\n",""));
            y = Integer.parseInt(br.readLine().replace("\n",""));
            z = Integer.parseInt(br.readLine().replace("\n",""));
            xRotate = Integer.parseInt(br.readLine().replace("\n",""));;
            yRotate = Integer.parseInt(br.readLine().replace("\n",""));;
            zRotate = Integer.parseInt(br.readLine().replace("\n",""));;
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void check(){
        try {
            File file = new File(IMG_CONFIG_PATH);
            System.out.println(file.getAbsolutePath());
            File w = new File(WEN);

            if (!file.exists()) {
                file.createNewFile();
            }
            if(!w.exists()){
                w.mkdirs();
            }
        }catch (Exception e){

        }
    }
    private static ModConfigLoader INSTANCE;
    public static ModConfigLoader getInstance(){
        if(INSTANCE==null){
            INSTANCE = new ModConfigLoader();
            INSTANCE.loadConfig();
        }
        return INSTANCE;
    }

    public void save(){
        check();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(IMG_CONFIG_PATH));
            bufferedWriter.write(x+"\n"+y+"\n"+z+"\n"+xRotate+"\n"+yRotate+"\n"+zRotate);
            bufferedWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setX(int x) {
        this.x = x;
        save();
    }

    public void setY(int y) {
        this.y = y;
        save();
    }

    public void setZ(int z) {
        this.z = z;
        save();
    }

    public void setxRotate(int xRotate) {
        this.xRotate = xRotate;
        save();
    }

    public void setyRotate(int yRotate) {
        this.yRotate = yRotate;
        save();
    }

    public void setzRotate(int zRotate) {
        this.zRotate = zRotate;
        save();
    }

}
