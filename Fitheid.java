/*    */ package Elthandorr.ATM.main;
/*    */ 
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.Sign;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.SignChangeEvent;
/*    */ import org.bukkit.event.player.PlayerMoveEvent;
/*    */ 
/*    */ 
/*    */ public class Fitheid
/*    */   implements Listener
/*    */ {
/*    */   private final Main plugin;
/*    */   
/* 20 */   public Fitheid(Main plugin) { this.plugin = plugin; }
/*    */   
/*    */   @EventHandler
/*    */   public void signChange(SignChangeEvent e) {
/* 24 */     if (e.getLine(0).equalsIgnoreCase("[Fitness]")) {
/* 25 */       e.setLine(0, ChatColor.AQUA + "§l[Fitness]");
/* 26 */       e.setLine(1, ChatColor.GOLD + "loopBand");
/*    */     }
/*    */   }
/*    */   
/*    */   @EventHandler
/* 31 */   public void Fitness(PlayerMoveEvent e) { Player p = e.getPlayer();
/* 32 */     Location loc = p.getLocation();
/* 33 */     loc.setY(loc.getY() + 1.0D);
/*    */     
/* 35 */     Block block = loc.getWorld().getBlockAt(loc);
/* 36 */     if ((block.getState() instanceof Sign)) {
/* 37 */       org.bukkit.block.BlockState bs = block.getState();
/* 38 */       Sign s = (Sign)bs;
/* 39 */       if (s.getLine(0).equalsIgnoreCase(ChatColor.AQUA + "§l[Fitness]")) {
/* 40 */         if (this.plugin.getConfig().getInt(p.getName() + ".dorst") != 60) {
/* 41 */           double locx = p.getLocation().getX() - 2.0D;
/* 42 */           double locy = p.getLocation().getY();
/* 43 */           double locz = p.getLocation().getZ();
/* 44 */           float OneDirection = p.getLocation().getYaw();
/* 45 */           float pi = p.getLocation().getPitch();
/*    */           
/* 47 */           Location loc2 = new Location(p.getWorld(), locx, locy, locz, OneDirection, pi);
/* 48 */           p.teleport(loc2);
/* 49 */           this.plugin.getConfig().set(p.getName() + ".dorst", Integer.valueOf(this.plugin.getConfig().getInt(p.getName() + ".dorst") + 1));
/* 50 */           this.plugin.getConfig().set(p.getName() + ".food", Integer.valueOf(this.plugin.getConfig().getInt(p.getName() + ".food") + 1));
/* 51 */           while (this.plugin.getConfig().getInt(p.getName() + ".food") >= 20)
/*    */           {
/* 53 */             this.plugin.getConfig().set(p.getName() + ".food", Integer.valueOf(this.plugin.getConfig().getInt(p.getName() + ".food") - 20));
/* 54 */             this.plugin.getConfig().set(p.getName() + ".fitheid", Integer.valueOf(this.plugin.getConfig().getInt(p.getName() + ".fitheid") + 1));
/* 55 */             this.plugin.saveConfig();
/* 56 */             this.plugin.ScoreBoardUpdate(p);
/*    */           }
/*    */         }
/*    */         else
/*    */         {
/* 61 */           p.sendMessage(ChatColor.RED + "Je hebt dorst! Drink " + ChatColor.AQUA + " water " + ChatColor.RED + " om weer te kunnen sporten!");
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Michael\Downloads\MinetopiaATM.jar!\karsten\ATM\main\Fitheid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */