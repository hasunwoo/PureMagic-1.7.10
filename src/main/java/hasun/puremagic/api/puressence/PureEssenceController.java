package hasun.puremagic.api.puressence;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class PureEssenceController {
    /**
     * Drains Pure Essence from Player.
     *
     * @param player Player name.
     * @return drainable essence amount. -1 if worldobject is client.
     */
    public static int DrainPureEssenceFromPlayer(String player, int amount) {
        if (!isServer()) return -1;
        initPureEssenceStorage(player);
        //data is stored in world 0
        World worlds = MinecraftServer.getServer().worldServers[0];
        PureEssenceNetwork essence = (PureEssenceNetwork) worlds.loadItemData(PureEssenceNetwork.class, player);
        int current = essence.currentPureEssence;
        if (current <= 0) return 0;
        int drained = current - amount;
        if (drained <= 0) {
            essence.currentPureEssence = 0;
            essence.markDirty();
            return amount;
        }
        essence.currentPureEssence = drained;
        essence.markDirty();
        return amount;
    }

    private static void initPureEssenceStorage(String player) {
        World world = MinecraftServer.getServer().worldServers[0];
        PureEssenceNetwork essence = (PureEssenceNetwork) world.loadItemData(PureEssenceNetwork.class, player);
        if (essence == null) {
            PureEssenceNetwork ess = new PureEssenceNetwork(player);
            world.setItemData(player, ess);
            ess.markDirty();
        }
    }

    /**
     * Charge Pure Essence from to Player.
     *
     * @param player Player name.
     * @return charged amount. if player's storage is full then return chargeable amount. -1 if worldobject is client.
     */
    public static int chargePureEssence(String player, int amount) {
        if (!isServer()) return -1;
        initPureEssenceStorage(player);
        //data is stored in world 0
        World worlds = MinecraftServer.getServer().worldServers[0];
        PureEssenceNetwork essence = (PureEssenceNetwork) worlds.loadItemData(PureEssenceNetwork.class, player);
        int current = essence.currentPureEssence;
        int charged = current + amount;
        if (charged > essence.MaxCapacity) {
            essence.currentPureEssence = essence.MaxCapacity;
            essence.markDirty();
            return essence.currentPureEssence - current;
        }
        essence.currentPureEssence = charged;
        essence.markDirty();
        return amount;
    }

    /**
     * Get Max Capacity of player Pure essence network.
     *
     * @param player Player name.
     * @return -1 if worldobject is client.
     */
    public static int getMaxCapacity(String player) {
        if (!isServer()) return -1;
        initPureEssenceStorage(player);
        //data is stored in world 0
        World worlds = MinecraftServer.getServer().worldServers[0];
        PureEssenceNetwork essence = (PureEssenceNetwork) worlds.loadItemData(PureEssenceNetwork.class, player);
        return essence.MaxCapacity;
    }

    /**
     * Get Max Capacity of player Pure essence network.
     *
     * @param player Player name.
     * @return -1 if worldobject is client.
     */
    public static int getCurrentPureEssence(String player) {
        if (!isServer()) return -1;
        initPureEssenceStorage(player);
        //data is stored in world 0
        World worlds = MinecraftServer.getServer().worldServers[0];
        PureEssenceNetwork essence = (PureEssenceNetwork) worlds.loadItemData(PureEssenceNetwork.class, player);
        return essence.currentPureEssence;
    }

    /**
     * Set Max Capacity of player Pure essence network.
     *
     * @param player   Player name.
     * @param capacity capacity to set.
     * @return -1 if worldobject is client. else return 0
     */
    public static int setMaxCapacity(String player, int capacity) {
        if (!isServer()) return -1;
        initPureEssenceStorage(player);
        //data is stored in world 0
        World worlds = MinecraftServer.getServer().worldServers[0];
        PureEssenceNetwork essence = (PureEssenceNetwork) worlds.loadItemData(PureEssenceNetwork.class, player);
        essence.MaxCapacity = capacity;
        essence.markDirty();
        return 0;
    }

    /**
     * Get Tier of player Pure essence network.
     *
     * @param player Player name.
     * @return -1 if worldobject is client. else return player's tier
     */
    public static int getTier(String player) {
        if (!isServer()) return -1;
        initPureEssenceStorage(player);
        World worlds = MinecraftServer.getServer().worldServers[0];
        PureEssenceNetwork essence = (PureEssenceNetwork) worlds.loadItemData(PureEssenceNetwork.class, player);
        return essence.currentTier;
    }

    /**
     * Set Tier of player Pure essence network.
     *
     * @param player Player name.
     * @param tier   Tier to set.
     * @return -1 if worldobject is client. else return 0
     */
    public static int setTier(String player, int tier) {
        if (!isServer()) return -1;
        initPureEssenceStorage(player);
        World worlds = MinecraftServer.getServer().worldServers[0];
        PureEssenceNetwork essence = (PureEssenceNetwork) worlds.loadItemData(PureEssenceNetwork.class, player);
        essence.currentTier = tier;
        essence.markDirty();
        return 0;
    }

    public static boolean isServer() {
        return FMLCommonHandler.instance().getEffectiveSide().isServer();
    }
}
