package com.mhxks.paint.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

/**
 * Creative by GoldMain on 2020/3/2
 */
public class CommandPaint
extends CommandBase {
    @Override
    public String getName() {
        return "paint";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.paint.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        System.out.println(sender.getName());
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 1;
    }
}
