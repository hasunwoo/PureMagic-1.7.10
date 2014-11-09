package hasun.puremagic.api.puressence;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

public class PureEssenceNetwork extends WorldSavedData {
    public int currentPureEssence = 0;
    public int MaxCapacity = 50000;

    public PureEssenceNetwork(String playerName) {
        super(playerName);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        currentPureEssence = tag.getInteger("currentPureEssence");
        MaxCapacity = tag.getInteger("MaxCapacity");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        tag.setInteger("currentPureEssence", currentPureEssence);
        tag.setInteger("MaxCapacity", MaxCapacity);
    }
}
