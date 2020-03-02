package com.mhxks.paint.client.render;

import com.mhxks.paint.PaintMain;
import com.mhxks.paint.client.texture.CustomSimpleTexture;
import com.mhxks.paint.init.ModConfigLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static org.lwjgl.opengl.GL11.*;

/**
 * Creative by GoldMain on 2020/3/2
 */
public class RenderPaint {
    //public static ResourceLocation TEXTURE = new ResourceLocation(PaintMain.MODID,"textures/render/aaa.png");
    public static ResourceLocation TEXTURE = new ResourceLocation(PaintMain.MODID,"../../../../../paint/aaa.png");
    ITextureObject itextureobject = new CustomSimpleTexture(TEXTURE);
    private static RenderPaint INSTANCE;
    public boolean isOpen = false;
    public RenderPaint(){
        Minecraft.getMinecraft().getTextureManager().loadTexture(TEXTURE,itextureobject);
    }
    public void setStartPostion(int x,int y,int z){
        ModConfigLoader modConfigLoader = ModConfigLoader.getInstance();
        modConfigLoader.setX(x);
        modConfigLoader.setY(y);
        modConfigLoader.setZ(z);
        isOpen = !isOpen;
    }
    public void setOpen(){
        isOpen = !isOpen;
    }
    public static RenderPaint getInstance(){
        if(INSTANCE == null)INSTANCE = new RenderPaint();
        return INSTANCE;
    }
    @SideOnly(Side.CLIENT)
    public void render(double x,double y,double z){

        if(isOpen){
            glPushMatrix();
            GlStateManager.bindTexture(itextureobject.getGlTextureId());
            CustomSimpleTexture customSimpleTexture = (CustomSimpleTexture)itextureobject;
            int width = customSimpleTexture.width;
            int height = customSimpleTexture.height;
            //Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
            /*
            try {
                BufferedImage bufferedimage = TextureUtil.readBufferedImage(new FileInputStream("C:\\Users\\戒酒的李白\\Desktop\\aaa.png"));
                int i = GlStateManager.generateTexture();
                TextureUtil.uploadTextureImageAllocate(i,bufferedimage,false,false);
                GlStateManager.bindTexture(1);
            }catch (Exception e){
                e.printStackTrace();
            }*/
            ModConfigLoader modConfigLoader = ModConfigLoader.getInstance();
            double rx = modConfigLoader.x-x;
            double ry = modConfigLoader.y-y;
            double rz = modConfigLoader.z-z;
            glTranslated(rx,ry,rz);
            glRotated(modConfigLoader.xRotate,1,0,0);
            glRotated(modConfigLoader.yRotate,0,1,0);
            glRotated(modConfigLoader.zRotate,0,0,1);
            glDisable(GL_CULL_FACE);
            glEnable(GL_BLEND);
            glDisable(GL_LIGHTING);
            glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder builder = tessellator.getBuffer();
            builder.begin(GL_TRIANGLE_STRIP, DefaultVertexFormats.POSITION_TEX);
            builder.pos(width,height,0).tex(0,0).endVertex();
            builder.pos(0,height,0).tex(1,0).endVertex();
            builder.pos(width,0,0).tex(0,1).endVertex();
            builder.pos(0,0,0).tex(1,1).endVertex();
            tessellator.draw();
            glEnable(GL_CULL_FACE);
            glEnable(GL_LIGHTING);
            glDisable(GL_BLEND);
            glPopMatrix();
        }
    }
}
