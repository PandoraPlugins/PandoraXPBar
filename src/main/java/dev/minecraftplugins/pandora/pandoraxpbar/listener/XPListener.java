package dev.minecraftplugins.pandora.pandoraxpbar.listener;

import com.azortis.azortislib.reflection.Reflections;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.server.v1_8_R3.PacketPlayOutExperience;

public class XPListener extends ChannelDuplexHandler {

    @Override
    public void write(ChannelHandlerContext channelHandlerContext, Object o, ChannelPromise channelPromise) throws Exception {
        // We check if the packet is an xp packet
        if (o instanceof PacketPlayOutExperience) {
            PacketPlayOutExperience experience = (PacketPlayOutExperience) o;
            // Getting the total amount of experience from the packet.
            int totalExperience = Reflections.getField(experience.getClass(), "b", int.class).get(experience);
            // We change the packet to a new packet which is
            o = new PacketPlayOutExperience(1, totalExperience, totalExperience);
        }
        super.write(channelHandlerContext, o, channelPromise);
    }
}
