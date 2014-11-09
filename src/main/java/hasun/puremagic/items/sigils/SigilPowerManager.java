package hasun.puremagic.items.sigils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SigilPowerManager {
    public static boolean charge(ItemStack itemStack,int amount){
        if(itemStack.getItem() instanceof ISigil){
            NBTTagCompound tag = itemStack.stackTagCompound;
            int current = tag.getInteger("Charges");
            if(current>10000) return false;
            int charged = current+amount;
            if(charged>10000) return false;
            tag.setInteger("Charges",charged);
            return true;
        }else{
            return false;
        }
    }
    //return consumed power
    public static int consume(ItemStack itemStack,int amount){
        if(itemStack.getItem() instanceof ISigil){
            NBTTagCompound tag = itemStack.stackTagCompound;
            int current = tag.getInteger("Charges");
            if(current<=0) return 0;
            int consumed = current-amount;
            if(consumed<=0){
                tag.setInteger("Charges",0);
                return current;
            };
            tag.setInteger("Charges",consumed);
            return amount;
        }else{
            return 0;
        }
    }
}
