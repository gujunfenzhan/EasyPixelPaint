package com.mhxks.paint;

import com.mhxks.paint.common.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Creative by GoldMain on 2020/3/2
 */
@Mod(name = PaintMain.NAME,version = PaintMain.VERSION,modid = PaintMain.MODID)
public class PaintMain {
    @SidedProxy(serverSide = "com.mhxks.paint.common.CommonProxy",clientSide = "com.mhxks.paint.client.ClientProxy")
    public static CommonProxy proxy;
    public final static String NAME = "paint";
    public final static String VERSION = "1.0.0";
    public final static String MODID = "paint";
    @Mod.Instance
    public static PaintMain INSTANCE;
    @Mod.EventHandler
    public void pre(FMLPreInitializationEvent event){
        INSTANCE = this;
        MinecraftForge.EVENT_BUS.register(proxy);
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event){

    }
    @Mod.EventHandler
    public void post(FMLPostInitializationEvent event){

    }

}
