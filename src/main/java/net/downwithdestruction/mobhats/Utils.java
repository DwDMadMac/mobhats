package net.downwithdestruction.mobhats;

import net.minecraft.server.v1_10_R1.EntityLiving;
import net.minecraft.server.v1_10_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;

/**
 * Created by madmac on 11/17/15.
 *
 * TODO: setEntityNBT(entity, tagName, tagValue)
 *
 * INFO: http://minecraft.gamepedia.com/Tutorials/Command_NBT_Tags
 * INFO: http://minecraft.gamepedia.com/Chunk_format
 * INFO: http://minecraft.gamepedia.com/Entity  Go Down to Video above, click Common NBT data [show] link
 */
public class Utils {
    public static void setNoAI(Entity e) {
        net.minecraft.server.v1_10_R1.Entity nmsEntity = ((CraftEntity) e).getHandle();

        NBTTagCompound tag = new NBTTagCompound();

        nmsEntity.c(tag);

        tag.setBoolean("NoAI", true);

        EntityLiving entityLiving = (EntityLiving) nmsEntity;

        entityLiving.a(tag);
    }
}
