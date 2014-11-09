package hasun.puremagic.commands;

import hasun.puremagic.api.puressence.PureEssenceController;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class cheatSetEssenceLevel extends CommandBase {
    @Override
    public String getCommandName() {
        return "PM_SetPELevel";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "Sets Pure essence level./PM_SetPELevel playername amount";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] cmd) {
        String target = cmd[0];
        int amount = Integer.parseInt(cmd[1]);
        int charged = PureEssenceController.chargePureEssence(target, amount);
        sender.addChatMessage(new ChatComponentText("Charged:" + charged + "PE"));
    }
}
