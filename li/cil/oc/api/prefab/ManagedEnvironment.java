package li.cil.oc.api.prefab;

import li.cil.oc.api.network.Message;
import li.cil.oc.api.network.Node;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Simple base implementation of the <tt>ManagedEnvironment</tt> interface, so
 * unused methods don't clutter the implementing class.
 */
public abstract class ManagedEnvironment implements li.cil.oc.api.network.ManagedEnvironment {
    // Should be initialized using api.Network.newNode(). See TileEntityEnvironment.
    protected Node node;

    @Override
    public Node node() {
        return node;
    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    @Override
    public void update() {
    }

    @Override
    public void onConnect(Node node) {
    }

    @Override
    public void onDisconnect(Node node) {
    }

    @Override
    public void onMessage(Message message) {
    }

    @Override
    public void load(NBTTagCompound nbt) {
        if (node != null) node.load(nbt.getCompoundTag("node"));
    }

    @Override
    public void save(NBTTagCompound nbt) {
        if (node != null) {
            NBTTagCompound nodeTag = new NBTTagCompound();
            node.save(nodeTag);
            nbt.setCompoundTag("node", nodeTag);
        }
    }
}
