package hasun.puremagic.commands;

import hasun.puremagic.api.puressence.PureEssenceController;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class cheatSetMaxEssence extends CommandBase {
    @Override
    public String getCommandName() {
        return "PM_SetMaxPELevel";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "Sets max Pure essence level./PM_SetMaxPELevel playername amount";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] cmd) {
        String target = cmd[0];
        int amount = Integer.parseInt(cmd[1]);
        PureEssenceController.setMaxCapacity(target, amount);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
}
