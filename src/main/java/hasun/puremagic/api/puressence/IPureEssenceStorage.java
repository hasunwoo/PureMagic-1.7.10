package hasun.puremagic.api.puressence;

public interface IPureEssenceStorage {
    //return true if can drained from other machine
    public boolean canDrain(int amount);

    //return false if unable to accept
    public boolean StorePureEssence(int amount);

    //return true if this tank  have room for more essence
    public boolean hasSpace(int amount);

    //return drained pure essence value
    public int DrawPureEssence(int amount);
}
