package li.cil.oc.integration.opencomputers

import li.cil.oc.Settings
import li.cil.oc.api
import li.cil.oc.api.driver
import li.cil.oc.api.driver.EnvironmentHost
import li.cil.oc.api.internal
import li.cil.oc.common.Tier
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

trait Item extends driver.Item {
  override def tier(stack: ItemStack) = Tier.One

  override def dataTag(stack: ItemStack) = Item.dataTag(stack)

  protected def isOneOf(stack: ItemStack, items: api.detail.ItemInfo*) = items.filter(_ != null).contains(api.Items.get(stack))

  protected def isRotatable(host: Class[_ <: EnvironmentHost]) = classOf[internal.Rotatable].isAssignableFrom(host)

  protected def isComputer(host: Class[_ <: EnvironmentHost]) = classOf[internal.Case].isAssignableFrom(host)

  protected def isRobot(host: Class[_ <: EnvironmentHost]) = classOf[internal.Robot].isAssignableFrom(host)

  protected def isServer(host: Class[_ <: EnvironmentHost]) = classOf[internal.Server].isAssignableFrom(host)

  protected def isTablet(host: Class[_ <: EnvironmentHost]) = classOf[internal.Tablet].isAssignableFrom(host)
}

object Item {
  def dataTag(stack: ItemStack) = {
    if (!stack.hasTagCompound) {
      stack.setTagCompound(new NBTTagCompound())
    }
    val nbt = stack.getTagCompound
    if (!nbt.hasKey(Settings.namespace + "data")) {
      nbt.setTag(Settings.namespace + "data", new NBTTagCompound())
    }
    nbt.getCompoundTag(Settings.namespace + "data")
  }
}