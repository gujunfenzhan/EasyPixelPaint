package com.mhxks.paint.client;

import com.mhxks.paint.client.render.RenderPaint;
import com.mhxks.paint.common.CommonProxy;
import com.mhxks.paint.init.ModConfigLoader;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Creative by GoldMain on 2020/3/2
 */
public class ClientProxy
extends CommonProxy {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void PlayerMessage(ClientChatEvent event){
        String message = event.getMessage();
        if(message.indexOf("paint")!=-1){
            event.setCanceled(true);
            String[] strs = message.split(" ");
            if(strs.length==1) {
                RenderPaint.getInstance().setOpen();

            }else if(strs.length==2&&strs[1].equals("reload")) {
                EntityPlayerSP entityPlayerSP = FMLClientHandler.instance().getClientPlayerEntity();
                RenderPaint.getInstance().setStartPostion((int)entityPlayerSP.posX, (int)entityPlayerSP.posY, (int)entityPlayerSP.posZ);
                System.out.println("设置坐标成功");
            }
            else if(strs.length==3&&RenderPaint.getInstance().isOpen){
                try {
                    int n = Integer.parseInt(strs[2]);
                    ModConfigLoader modConfigLoader = ModConfigLoader.getInstance();
                if(strs[1].equals("x")){
                    modConfigLoader.setX(n+modConfigLoader.x);
                }else if(strs[1].equals("y")){
                    modConfigLoader.setY(n+modConfigLoader.y);
                }else if(strs[1].equals("z")){
                    modConfigLoader.setZ(n+modConfigLoader.z);
                }else if(strs[1].equals("xRotate")){
                    modConfigLoader.setxRotate(n);
                }else if(strs[1].equals("yRotate")){
                    modConfigLoader.setyRotate(n);
                }else if(strs[1].equals("zRotate")){
                    modConfigLoader.setzRotate(n);
                }else{
                    System.out.println("您的参数有误");
                }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void PaintRender(RenderWorldLastEvent event){
        EntityPlayerSP entityPlayerSP = FMLClientHandler.instance().getClientPlayerEntity();
        float partialTicks = event.getPartialTicks();
        double d0 = entityPlayerSP.lastTickPosX + (entityPlayerSP.posX - entityPlayerSP.lastTickPosX) * (double)partialTicks;
        double d1 = entityPlayerSP.lastTickPosY + (entityPlayerSP.posY - entityPlayerSP.lastTickPosY) * (double)partialTicks;
        double d2 = entityPlayerSP.lastTickPosZ + (entityPlayerSP.posZ - entityPlayerSP.lastTickPosZ) * (double)partialTicks;
        RenderPaint.getInstance().render(d0,d1,d2);
    }
}
