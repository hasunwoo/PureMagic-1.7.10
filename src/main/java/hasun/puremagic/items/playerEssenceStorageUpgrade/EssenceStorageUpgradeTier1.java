package hasun.puremagic.items.playerEssenceStorageUpgrade;

import hasun.puremagic.api.puressence.PureEssenceController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.List;

public class EssenceStorageUpgradeTier1 extends Item implements IPlayerEssenceStorageUpgrade {
    private static int capacity;
    public EssenceStorageUpgradeTier1(int capacity){
        this.capacity = capacity;
        setMaxStackSize(1);
        setUnlocalizedName("StorageUpgradeTier1");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
        if(!world.isRemote){
            if(PureEssenceController.getTier(player.getDisplayName())==0){
                PureEssenceController.setTier(player.getDisplayName(),1);
                PureEssenceController.setMaxCapacity(player.getDisplayName(),capacity);
                player.addChatComponentMessage(new ChatComponentText("You have successfully upgraded to Tier to 2"));
                 --itemstack.stackSize;
            }
            return itemstack;
        }
        return itemstack;
    }
    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        p_77624_3_.add("Right click to upgrade your essence");
        p_77624_3_.add("network to Tier 1(50000PE)");
        p_77624_3_.add("you must be Tier 0 to use this item");
    }
    @Override
    public int getTier() {
        return 1;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }
}
