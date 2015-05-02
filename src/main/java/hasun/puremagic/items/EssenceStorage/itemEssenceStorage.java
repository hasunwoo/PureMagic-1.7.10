package hasun.puremagic.items.EssenceStorage;

import hasun.puremagic.api.puressence.IPureEssenceStorage;
import net.minecraft.item.Item;

public class itemEssenceStorage extends Item implements IPureEssenceStorage {
    @Override
    public boolean canDrain(int amount) {
        return false;
    }

    @Override
    public boolean StorePureEssence(int amount) {
        return false;
    }

    @Override
    public boolean hasSpace(int amount) {
        return false;
    }

    @Override
    public int DrawPureEssence(int amount) {
        return 0;
    }
}

