package hasun.puremagic.items.Crystals;

import hasun.puremagic.api.puressence.PureEssenceController;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;
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

    private void initNBT(ItemStack itemStack) {
        if (itemStack.stackTagCompound == null) {
            itemStack.setTagCompound(new NBTTagCompound());
            itemStack.stackTagCompound.setBoolean("isActive", false);
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
    public ItemStack onItemRightClick(ItemStack itemstack, World p_77659_2_, EntityPlayer p_77659_3_) {
        initNBT(itemstack);
        if (itemstack.stackTagCompound.getString("Owner").equals("@New")) {
            itemstack.stackTagCompound.setString("Owner", p_77659_3_.getDisplayName());
        } else {
            if (!itemstack.stackTagCompound.getString("Owner").equals(p_77659_3_.getDisplayName())) return itemstack;
        }
        if (p_77659_3_.isSneaking()) {
            if (itemstack.stackTagCompound != null) {
                boolean data = !itemstack.stackTagCompound.getBoolean("isActive");
                itemstack.stackTagCompound.setBoolean("isActive", data);
            }
        } else {
            if (!p_77659_2_.isRemote) {
                p_77659_3_.addChatComponentMessage(new ChatComponentText("Your Food Level:" + p_77659_3_.getFoodStats().getFoodLevel()));
                p_77659_3_.addChatComponentMessage(new ChatComponentText("Your Saturation Level:" + Math.floor(p_77659_3_.getFoodStats().getSaturationLevel())));
                p_77659_3_.addChatComponentMessage(new ChatComponentText("Your Exhaustion Level:" + Math.floor(getExhaustionLevel(p_77659_3_.getFoodStats()))));
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
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        initNBT(p_77624_1_);
        if (p_77624_1_.stackTagCompound != null) {
            if (p_77624_1_.stackTagCompound.getBoolean("isActive")) {
                p_77624_3_.add("Activated");
            } else {
                p_77624_3_.add("Deactivated");
            }
        }
        if (p_77624_1_.stackTagCompound.getString("Owner").equals("@New")) {
            p_77624_3_.add("Right click to set owner");
        } else {
            p_77624_3_.add("Owner:" + p_77624_1_.stackTagCompound.getString("Owner"));
        }
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        initNBT(itemStack);
        //if crystal is activated
        if (!itemStack.stackTagCompound.getBoolean("isActive")) return;
        //if crystal has owner
        if (itemStack.stackTagCompound.getString("Owner").equals("@New")) return;
        if (entity instanceof EntityPlayer) {
            if (!world.isRemote) {
                EntityPlayer player = (EntityPlayer) entity;
                //if crystal used by non-owner
                if (!itemStack.stackTagCompound.getString("Owner").equals(player.getDisplayName())) return;
                int trytoconsume = (20 - player.getFoodStats().getFoodLevel()) * consumptionPerOperation;
                int canconsume = PureEssenceController.DrainPureEssenceFromPlayer(itemStack.stackTagCompound.getString("Owner"), trytoconsume);
                player.getFoodStats().addStats(canconsume, 0F);
            }
        }
    }
}
