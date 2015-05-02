package hasun.puremagic.items.Crystals;

import hasun.puremagic.api.puressence.IFunctionalCrystal;
import hasun.puremagic.api.puressence.PureEssenceController;
import hasun.puremagic.api.puressence.utils.itemFunctionalCrystal.CommonFCUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.List;

public class FunctionalCrystalEssenceDetection extends Item implements IFunctionalCrystal {
    private static int consumptionPerOperation;

    public FunctionalCrystalEssenceDetection(int consume) {
        consumptionPerOperation = consume;
        setMaxStackSize(1);
        setUnlocalizedName("FunctionalCrystalEssenceDetection");
    }

    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
        CommonFCUtil.initNBT(itemStack);
    }

    @Override
    public int getConsumptionPerOperation() {
        return consumptionPerOperation;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {
        CommonFCUtil.initNBT(itemStack);
        if (!CommonFCUtil.hasOwner(itemStack)) {
            list.add("Right click to set owner");
        } else {
            list.add("Owner:" + CommonFCUtil.getOwner(itemStack));
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
        CommonFCUtil.initNBT(itemstack);
        CommonFCUtil.setOwner(itemstack, player.getDisplayName());
        if (CommonFCUtil.checkPermission(itemstack, player.getDisplayName())) {
            if (!world.isRemote) {
                player.addChatComponentMessage(new ChatComponentText("Maximum capacity:" + PureEssenceController.getMaxCapacity(itemstack.stackTagCompound.getString("Owner")) + "PE"));
                player.addChatComponentMessage(new ChatComponentText("Current Pure Essence:" + PureEssenceController.getCurrentPureEssence(itemstack.stackTagCompound.getString("Owner")) + "PE"));
                player.addChatComponentMessage(new ChatComponentText("Current Tier:" + PureEssenceController.getTier(itemstack.stackTagCompound.getString("Owner"))));
            }
        }
        return itemstack;
    }
}
