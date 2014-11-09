package hasun.puremagic.api.puressence;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class PureEssenceController {
    /**
     * Drains Pure Essence from Player.
     *
     * @param player            Player name.
     * @return drainable essence amount. -1 if worldobject is client.
     */
    public static int DrainPureEssenceFromPlayer(String player,int amount){
        if(FMLCommonHandler.instance().getSide()!= Side.SERVER) return -1;
        initPureEssenceStorage(player);
        //data is stored in world 0
        World world = MinecraftServer.getServer().worldServers[0];
        PureEssenceNetwork essence = (PureEssenceNetwork)world.loadItemData(PureEssenceNetwork.class,player);
        int current = essence.currentPureEssence;
        if(current<=0) return 0;
        int drained = current-amount;
        if(drained<=0){
            essence.currentPureEssence = 0;
            essence.markDirty();
            return amount;
        }
        essence.currentPureEssence = drained;
        essence.markDirty();
        return amount;
    }
    private static void initPureEssenceStorage(String player){
        if(FMLCommonHandler.instance().getSide()!=Side.SERVER) return;
        World world = MinecraftServer.getServer().worldServers[0];
        PureEssenceNetwork essence = (PureEssenceNetwork)world.loadItemData(PureEssenceNetwork.class,player);
        if(essence==null) {
            PureEssenceNetwork ess = new PureEssenceNetwork(player);
            world.setItemData(player,ess);
            ess.markDirty();
        }
    }
    /**
     * Charge Pure Essence from to Player.
     *
     * @param player            Player name.
     * @return charged amount. if player's storage is full then return chargeable amount. -1 if worldobject is client.
     */
    public static int chargePureEssence(String player,int amount){
        if(FMLCommonHandler.instance().getSide()!= Side.SERVER) return -1;
        initPureEssenceStorage(player);
        //data is stored in world 0
        World world = MinecraftServer.getServer().worldServers[0];
        PureEssenceNetwork essence = (PureEssenceNetwork)world.loadItemData(PureEssenceNetwork.class,player);
        int current = essence.currentPureEssence;
        int charged = current+amount;
        if(charged>essence.MaxCapacity){
            essence.currentPureEssence = essence.MaxCapacity;
            essence.markDirty();
            return amount-current;
        }
        essence.currentPureEssence = charged;
        essence.markDirty();
        return amount;
    }
    /**
     * Get Max Capacity of player Pure essence network.
     *
     * @param player            Player name.
     * @return  -1 if worldobject is client.
     */
    public static int getMaxCapacity(String player){
        if(FMLCommonHandler.instance().getSide()!= Side.SERVER) return -1;
        initPureEssenceStorage(player);
        //data is stored in world 0
        World world = MinecraftServer.getServer().worldServers[0];
        PureEssenceNetwork essence = (PureEssenceNetwork)world.loadItemData(PureEssenceNetwork.class,player);
        return essence.MaxCapacity;
    }
    /**
     * Get Max Capacity of player Pure essence network.
     *
     * @param player            Player name.
     * @return  -1 if worldobject is client.
     */
    public static int getCurrentPureEssence(String player){
        if(FMLCommonHandler.instance().getSide()!= Side.SERVER) return -1;
        initPureEssenceStorage(player);
        //data is stored in world 0
        World world = MinecraftServer.getServer().worldServers[0];
        PureEssenceNetwork essence = (PureEssenceNetwork)world.loadItemData(PureEssenceNetwork.class,player);
        return essence.currentPureEssence;
    }
    /**
     * Set Max Capacity of player Pure essence network.
     *
     * @param player            Player name.
     * @param capacity          capacity to set.
     * @return  -1 if worldobject is client. else return 0
     */
    public static int setMaxCapacity(String player,int capacity){
        if(FMLCommonHandler.instance().getSide()!= Side.SERVER) return -1;
        initPureEssenceStorage(player);
        //data is stored in world 0
        World world = MinecraftServer.getServer().worldServers[0];
        PureEssenceNetwork essence = (PureEssenceNetwork)world.loadItemData(PureEssenceNetwork.class,player);
        essence.MaxCapacity = capacity;
        essence.markDirty();
        return 0;
    }
}
