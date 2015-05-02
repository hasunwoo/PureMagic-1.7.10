package hasun.puremagic.tileentity;

import hasun.puremagic.api.puressence.IPureEssenceStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class tilePureEssenceExtractor extends TileEntity implements IInventory {
    public ItemStack pureEssenceStorage;

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return pureEssenceStorage;
    }

    @Override
    public ItemStack decrStackSize(int slot, int size) {
        ItemStack returnItem = this.pureEssenceStorage.copy();
        this.pureEssenceStorage.stackSize--;
        return returnItem;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        this.pureEssenceStorage = itemStack;
    }

    @Override
    public String getInventoryName() {
        return "Essence Crystal";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
        return itemstack.getItem() instanceof IPureEssenceStorage;
    }
}
