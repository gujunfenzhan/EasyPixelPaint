package com.mhxks.paint.client.texture;

import com.mhxks.paint.init.ModConfigLoader;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Creative by GoldMain on 2020/3/2
 */
public class CustomSimpleTexture
extends SimpleTexture {
    public int width;
    public int height;
    public CustomSimpleTexture(ResourceLocation textureResourceLocation) {
        super(textureResourceLocation);
    }

    @Override
    public void loadTexture(IResourceManager resourceManager) throws IOException {
        this.deleteGlTexture();
        //IResource iresource = null;
        BufferedImage bufferedimage = TextureUtil.readBufferedImage(new FileInputStream(ModConfigLoader.IMG_PATH));
        width = bufferedimage.getWidth();
        height = bufferedimage.getHeight();
       /* try
        {
            iresource = resourceManager.getResource(this.textureLocation);
            BufferedImage bufferedimage = TextureUtil.readBufferedImage(new FileInputStream("C:\\Users\\戒酒的李白\\Desktop\\aaa.png"));
            boolean flag = false;
            boolean flag1 = false;

            if (iresource.hasMetadata())
            {
                try
                {
                    TextureMetadataSection texturemetadatasection = (TextureMetadataSection)iresource.getMetadata("texture");

                    if (texturemetadatasection != null)
                    {
                        flag = texturemetadatasection.getTextureBlur();
                        flag1 = texturemetadatasection.getTextureClamp();
                    }
                }
                catch (RuntimeException runtimeexception)
                {
                    //LOGGER.warn("Failed reading metadata of: {}", this.textureLocation, runtimeexception);
                }
            }
*/
            TextureUtil.uploadTextureImageAllocate(this.getGlTextureId(), bufferedimage, false, false);
       /* }
        finally
        {*/
            //IOUtils.closeQuietly((Closeable)iresource);
     //   }
    }
}
