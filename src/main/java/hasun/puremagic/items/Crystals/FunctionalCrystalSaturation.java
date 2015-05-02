package hasun.puremagic.items.Crystals;

import hasun.puremagic.api.puressence.IFunctionalCrystal;
import hasun.puremagic.api.puressence.PureEssenceController;
import hasun.puremagic.api.puressence.utils.itemFunctionalCrystal.CommonFCUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;

import java.lang.reflect.Field;
import java.util.List;

public class FunctionalCrystalSaturation extends Item implements IFunctionalCrystal {
    private static int consumptionPerOperation;

    public FunctionalCrystalSaturation(int consume) {
        consumptionPerOperation = consume;
        setMaxStackSize(1);
        setUnlocalizedName("FunctionalCrystalSaturation");
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
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
        CommonFCUtil.initNBT(itemstack);
        CommonFCUtil.setOwner(itemstack, player.getDisplayName());
        if (CommonFCUtil.checkPermission(itemstack, player.getDisplayName())) {
            if (player.isSneaking()) {
                CommonFCUtil.toggleActivationStatus(itemstack);
            } else {
                if (!world.isRemote) {
                    player.addChatComponentMessage(new ChatComponentText("Your Food Level:" + player.getFoodStats().getFoodLevel()));
                    player.addChatComponentMessage(new ChatComponentText("Your Saturation Level:" + Math.floor(player.getFoodStats().getSaturationLevel())));
                    player.addChatComponentMessage(new ChatComponentText("Your Exhaustion Level:" + Math.floor(getExhaustionLevel(player.getFoodStats()))));
                }
            }
        }
        return itemstack;
    }

    private float getExhaustionLevel(FoodStats foodStats) {
        try {
            Field f = FoodStats.class.getDeclaredField((Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment") ? "foodExhaustionLevel" : "field_75126_c");
            f.setAccessible(true);
            return (Float) f.get(foodStats);
        } catch (Exception e) {
            return -1.0F;
        }
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List data, boolean unknown) {
        CommonFCUtil.initNBT(itemStack);
        data.add("Consumption rate:1 food bar/" + consumptionPerOperation + "PE");
        if (CommonFCUtil.isActive(itemStack)) {
            data.add("Activated");
        } else {
            data.add("Deactivated");
        }
        if (!CommonFCUtil.hasOwner(itemStack)) {
            data.add("Right click to set owner");
        } else {
            data.add("Owner:" + CommonFCUtil.getOwner(itemStack));
        }
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        CommonFCUtil.initNBT(itemStack);
        if (entity instanceof EntityPlayer) {
            if (CommonFCUtil.checkPermission(itemStack, ((EntityPlayer) entity).getDisplayName()) && CommonFCUtil.isActive(itemStack)) {
                if (entity instanceof EntityPlayer) {
                    if (!world.isRemote) {
                        EntityPlayer player = (EntityPlayer) entity;
                        //if crystal used by non-owner
                        int requiredfood = (20 - player.getFoodStats().getFoodLevel());
                        if (requiredfood >= 1) {
                            if (PureEssenceController.getCurrentPureEssence(((EntityPlayer) entity).getDisplayName()) >= consumptionPerOperation) {
                                ((EntityPlayer) entity).getFoodStats().addStats(1, 0F);
                                PureEssenceController.DrainPureEssenceFromPlayer(((EntityPlayer) entity).getDisplayName(), consumptionPerOperation);
                            }
                        }
                    }
                }
            }
        }
    }
}
