package hasun.puremagic.items.Crystals;

import hasun.puremagic.api.puressence.PureEssenceController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.List;

public class FunctionalCrystalEssenceDetection extends Item implements IFunctionalCrystal {
    public static int consumptionPerOperation;

    public FunctionalCrystalEssenceDetection(int consume) {
        consumptionPerOperation = consume;
        setMaxStackSize(1);
        setUnlocalizedName("FunctionalCrystalEssenceDetection");
    }

    private void initNBT(ItemStack itemStack) {
        if (itemStack.stackTagCompound == null) {
            itemStack.setTagCompound(new NBTTagCompound());
            itemStack.stackTagCompound.setString("Owner", "@New");
        }
    }

    @Override
    public void onCreated(ItemStack p_77622_1_, World p_77622_2_, EntityPlayer p_77622_3_) {
        initNBT(p_77622_1_);
    }

    @Override
    public int getConsumptionPerOperation() {
        return consumptionPerOperation;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {
        initNBT(stack);
        if (stack.stackTagCompound.getString("Owner").equals("@New")) {
            list.add("Right click to set owner");
        } else {
            list.add("Owner:" + stack.stackTagCompound.getString("Owner"));
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
        initNBT(itemstack);
        if (itemstack.stackTagCompound.getString("Owner").equals("@New")) {
            itemstack.stackTagCompound.setString("Owner", player.getDisplayName());
        } else {
            if (!itemstack.stackTagCompound.getString("Owner").equals(player.getDisplayName())) return itemstack;
        }
        if (!world.isRemote) {
            player.addChatComponentMessage(new ChatComponentText("Maximum capacity:" + PureEssenceController.getMaxCapacity(player.getDisplayName()) + "PE"));
            player.addChatComponentMessage(new ChatComponentText("Current Pure Essence:" + PureEssenceController.getCurrentPureEssence(player.getDisplayName()) + "PE"));
        }
        return itemstack;
    }
}
