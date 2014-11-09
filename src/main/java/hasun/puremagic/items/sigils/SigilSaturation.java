package hasun.puremagic.items.sigils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;

import java.lang.reflect.Field;
import java.util.List;

public class SigilSaturation extends Item implements ISigil{
    public SigilSaturation(){
        setMaxStackSize(1);
        setUnlocalizedName("SigilofSaturation");
    }
    private void initNBT(ItemStack itemStack){
        if(itemStack.stackTagCompound==null){
            itemStack.setTagCompound(new NBTTagCompound());
            itemStack.stackTagCompound.setBoolean("isActive",false);
            itemStack.stackTagCompound.setInteger("Charges", 0);
        }
    }
    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }

    @Override
    public void onCreated(ItemStack p_77622_1_, World p_77622_2_, EntityPlayer p_77622_3_) {
        initNBT(p_77622_1_);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World p_77659_2_, EntityPlayer p_77659_3_) {
        initNBT(itemstack);
            if(p_77659_3_.isSneaking()){
                if(itemstack.stackTagCompound!=null){
                    boolean data = !itemstack.stackTagCompound.getBoolean("isActive");
                    itemstack.stackTagCompound.setBoolean("isActive",data);
                    itemstack.stackTagCompound.setInteger("Charges", 18);
                }
            }else{
                if(!p_77659_2_.isRemote){
                    p_77659_3_.addChatComponentMessage(new ChatComponentText("Your Food Level:"+p_77659_3_.getFoodStats().getFoodLevel()));
                    p_77659_3_.addChatComponentMessage(new ChatComponentText("Your Saturation Level:"+Math.floor(p_77659_3_.getFoodStats().getSaturationLevel())));
                    p_77659_3_.addChatComponentMessage(new ChatComponentText("Your Exhaustion Level:"+Math.floor(getExhaustionLevel(p_77659_3_.getFoodStats()))));
                }
            }
        return itemstack;
    }
    private float getExhaustionLevel(FoodStats foodStats){
        float ret = -1.0F;
        try{
            Field f = FoodStats.class.getDeclaredField("foodExhaustionLevel");
            f.setAccessible(true);
            ret = (Float)f.get(foodStats);
        }catch(Exception e){
            return -1.0F;
        }
        return ret;
    }
    private void setSaturationLevel(FoodStats foodStats,float level){
        try{
            Field f = FoodStats.class.getDeclaredField("foodSaturationLevel");
            f.setAccessible(true);
            f.set(foodStats,level);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        initNBT(p_77624_1_);
        if(p_77624_1_.stackTagCompound!=null){
            if(p_77624_1_.stackTagCompound.getBoolean("isActive")){
                p_77624_3_.add("Activated");
            }else{
                p_77624_3_.add("Deactivated");
            }
            p_77624_3_.add("Current Charges:"+p_77624_1_.stackTagCompound.getInteger("Charges")+"/10000");
        }
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        initNBT(itemStack);
           if(!itemStack.stackTagCompound.getBoolean("isActive")) return;
           if(!world.isRemote){
               EntityPlayer player = (EntityPlayer)entity;
               int hunger = player.getFoodStats().getFoodLevel();
               int trytoconsume = 0;
               trytoconsume = 20-player.getFoodStats().getFoodLevel();
               int canconsume = SigilPowerManager.consume(itemStack,trytoconsume);
               player.getFoodStats().addStats(canconsume,0F);

           }
    }
}
