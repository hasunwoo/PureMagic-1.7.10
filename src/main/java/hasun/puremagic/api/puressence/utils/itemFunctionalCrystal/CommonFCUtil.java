package hasun.puremagic.api.puressence.utils.itemFunctionalCrystal;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Common class for Functional Crystals with Owner and Activation status.
 * PLEASE DO NOT USE IT IF YOU DON'T UNDERSTAND WHAT THIS CLASS IS.
 */
public class CommonFCUtil {
    /**
     * Initializes NBT data for PE item(Owner,Activation status).
     *
     * @param itemstack item to Initialize nbt data
     */
    public static void initNBT(ItemStack itemstack) {
        if (itemstack.stackTagCompound == null) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        if (!(itemstack.stackTagCompound.hasKey("isActive") && itemstack.stackTagCompound.hasKey("Owner"))) {
            itemstack.stackTagCompound.setBoolean("isActive", false);
            itemstack.stackTagCompound.setString("Owner", "@New");
        }
    }

    /**
     * Check if item has owner.
     *
     * @param itemstack item to check owner.
     * @return true if item has owner.
     */
    public static boolean hasOwner(ItemStack itemstack) {
        initNBT(itemstack);
        return itemstack.stackTagCompound.getString("Owner") != "@New";
    }

    /**
     * Sets owner if this item does not have owner.
     *
     * @param itemstack item to set name.
     * @param name      player name.
     */
    public static void setOwner(ItemStack itemstack, String name) {
        if (!hasOwner(itemstack)) {
            setOwnerForce(itemstack, name);
        }
    }

    /**
     * Sets owner regardless of if item has owner.
     *
     * @param itemstack item to set name.
     * @param name      player name.
     */
    public static void setOwnerForce(ItemStack itemstack, String name) {
        initNBT(itemstack);
        itemstack.stackTagCompound.setString("Owner", name);
    }

    /**
     * Resets item owner.
     *
     * @param itemstack item to clear owner.
     */
    public static void clearOwner(ItemStack itemstack) {
        setOwnerForce(itemstack, "@New");
    }

    /**
     * retrieves owner name.
     *
     * @param itemstack item to retrieve owner name.
     * @return owner name if does not have owner return null
     */
    public static String getOwner(ItemStack itemstack) {
        initNBT(itemstack);
        return itemstack.stackTagCompound.getString("Owner") == "@New" ? null : itemstack.stackTagCompound.getString("Owner");
    }

    /**
     * check if player can use item(owner==name).
     *
     * @param itemstack item to check.
     * @param name      player name to compare.
     */
    public static boolean checkPermission(ItemStack itemstack, String name) {
        String player = getOwner(itemstack);
        if (player != null) {
            if (player.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * retrieves item activation status.
     *
     * @param itemstack item to retrieve activation status.
     * @return Activation status
     */
    public static boolean isActive(ItemStack itemstack) {
        initNBT(itemstack);
        return itemstack.stackTagCompound.getBoolean("isActive");
    }

    /**
     * Sets item Activation status.
     *
     * @param itemstack item to set activation status.
     * @param status    status to set.
     */
    public static void setActive(ItemStack itemstack, boolean status) {
        initNBT(itemstack);
        itemstack.stackTagCompound.setBoolean("isActive", status);
    }

    /**
     * toggles activation status.
     *
     * @param itemstack item to toggle activation status.
     */
    public static void toggleActivationStatus(ItemStack itemstack) {
        if (isActive(itemstack)) {
            setActive(itemstack, false);
        } else {
            setActive(itemstack, true);
        }
    }
}
