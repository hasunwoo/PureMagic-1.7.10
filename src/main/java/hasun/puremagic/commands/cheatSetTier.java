package hasun.puremagic.commands;

import hasun.puremagic.api.puressence.PureEssenceController;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class cheatSetTier extends CommandBase {
    @Override
    public String getCommandName() {
        return "PM_SetTier";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "Set player's tier./PM_SetTier playername tier";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] cmd) {
        String target = cmd[0];
        int tier = Integer.parseInt(cmd[1]);
        PureEssenceController.setTier(target, tier);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
}
