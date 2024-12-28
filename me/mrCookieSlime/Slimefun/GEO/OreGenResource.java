package me.mrCookieSlime.Slimefun.GEO;

import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

public interface OreGenResource {
  int getDefaultSupply(Biome paramBiome);
  
  String getName();
  
  ItemStack getIcon();
  
  String getMeasurementUnit();
}


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\GEO\OreGenResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */