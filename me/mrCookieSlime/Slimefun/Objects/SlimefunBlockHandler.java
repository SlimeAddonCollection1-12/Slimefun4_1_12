package me.mrCookieSlime.Slimefun.Objects;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public interface SlimefunBlockHandler {
  void onPlace(Player paramPlayer, Block paramBlock, SlimefunItem paramSlimefunItem);
  
  boolean onBreak(Player paramPlayer, Block paramBlock, SlimefunItem paramSlimefunItem, UnregisterReason paramUnregisterReason);
}


/* Location:              C:\Users\Administrator\Desktop\服务器2\server\plugins\Slimefun4_远古工艺 v4.1.15(不支持1.13+).jar!\me\mrCookieSlime\Slimefun\Objects\SlimefunBlockHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */