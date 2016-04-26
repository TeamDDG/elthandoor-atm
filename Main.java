       package Elthandorr.ATM.main;
       
       import java.util.ArrayList;
       import java.util.Collection;
       import java.util.Date;
       import java.util.List;
       import org.bukkit.Bukkit;
       import org.bukkit.ChatColor;
       import org.bukkit.Location;
       import org.bukkit.Material;
       import org.bukkit.Server;
       import org.bukkit.World;
       import org.bukkit.command.Command;
       import org.bukkit.command.CommandSender;
       import org.bukkit.configuration.file.FileConfiguration;
       import org.bukkit.entity.HumanEntity;
       import org.bukkit.entity.Player;
       import org.bukkit.event.EventHandler;
       import org.bukkit.event.inventory.InventoryClickEvent;
       import org.bukkit.event.inventory.InventoryCloseEvent;
       import org.bukkit.event.player.AsyncPlayerChatEvent;
       import org.bukkit.event.player.PlayerChangedWorldEvent;
       import org.bukkit.event.player.PlayerInteractEvent;
       import org.bukkit.event.player.PlayerJoinEvent;
       import org.bukkit.event.player.PlayerQuitEvent;
       import org.bukkit.inventory.Inventory;
       import org.bukkit.inventory.ItemStack;
       import org.bukkit.inventory.PlayerInventory;
       import org.bukkit.inventory.meta.ItemMeta;
       import org.bukkit.potion.PotionEffect;
       import org.bukkit.potion.PotionEffectType;
       import org.bukkit.scoreboard.Objective;
       import org.bukkit.scoreboard.Score;
       import org.bukkit.scoreboard.ScoreboardManager;
       
       public class Main extends org.bukkit.plugin.java.JavaPlugin implements org.bukkit.event.Listener
       {
       private ArrayList<Player> PlayerLoan = new ArrayList();
       private ArrayList<Player> rek = new ArrayList();
         
         public void onEnable() {
         Bukkit.getServer().getPluginManager().registerEvents(new Fitheid(this), this);
         Bukkit.getServer().getPluginManager().registerEvents(this, this);
         getConfig().addDefault("Scoreboard.title", "Minetopia");
         getConfig().addDefault("loan", Integer.valueOf(30));
         getConfig().addDefault("Accounts", Integer.valueOf(0));
         getConfig().addDefault("Scoreboard.money", "Geld");
         getConfig().addDefault("Scoreboard.time", "Tijd");
         getConfig().addDefault("Scoreboard.fitheid", "Fitheid");
         getConfig().addDefault("Scoreboard.level", "level");
         getConfig().addDefault("Counter", Integer.valueOf(0));
         getConfig().addDefault("worldWhitelist", "no");
         getConfig().addDefault("Time", Integer.valueOf(0));
         getConfig().addDefault("Message", "enabled");
         List<String> stringList = getConfig().getStringList("Ranks");
         stringList.add(" ");
         getConfig().addDefault("Ranks", stringList);
         List<String> stringList2 = getConfig().getStringList("Jobs");
         stringList2.add(" ");
         getConfig().addDefault("Jobs", stringList2);
         List<String> stringList3 = getConfig().getStringList("account");
         stringList3.add(" ");
         getConfig().addDefault("account", stringList3);
         saveConfig();
         getConfig().options().copyDefaults(true);
         for (Player p : Bukkit.getOnlinePlayers()) {
           ScoreBoardUpdate(p);
             
           this.PlayerLoan.add(p);
           }
         Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
             public void run() {
             for (Player p : ) {
               Main.this.ScoreBoardUpdate(p);
               Main.this.CheckFitheid(p);
               }
             }
         }, 0L, 300L);
         Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
             public void run() {
             for (Player p : Main.this.PlayerLoan)
               {
       
               if (Main.this.getConfig().getInt(p.getName() + ".minutes") < Main.this.getConfig().getInt("loan")) {
                 Main.this.getConfig().set(p.getName() + ".minutes", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".minutes") + 1));
                 } else {
                 Main.this.getConfig().set(p.getName() + ".minutes", Integer.valueOf(0));
                 if (Main.this.getConfig().getInt(p.getName() + ".fitheid") > 0) {
                   Main.this.getConfig().set(p.getName() + ".fitheid", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".fitheid") - 1));
                   }
                 if (Main.this.getConfig().getInt(p.getName() + ".rank") == 1) {
                   Main.this.getConfig().set(p.getName() + ".money", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".money") + 500));
                   Main.this.getConfig().set(p.getName() + ".iron", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".iron") + 1));
                   p.sendMessage(ChatColor.GREEN + "Je hebt €500 euro loon gekregen!");
                 } else if (Main.this.getConfig().getInt(p.getName() + ".rank") == 2) {
                   Main.this.getConfig().set(p.getName() + ".iron", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".iron") + 1));
                   Main.this.getConfig().set(p.getName() + ".gblock", Integer.valueOf(Main.this.getConfig().getInt(p.getPlayer().getName() + ".gblock") + 2));
                   Main.this.getConfig().set(p.getName() + ".gold", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".gold") + 5));
                   Main.this.getConfig().set(p.getName() + ".money", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".money") + 750));
  101                p.sendMessage(ChatColor.GREEN + "Je hebt €750 euro loon gekregen!");
  102              } else if (Main.this.getConfig().getInt(p.getName() + ".rank") == 3) {
  103                Main.this.getConfig().set(p.getName() + ".emerald", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".emerald") + 1));
  104                Main.this.getConfig().set(p.getName() + ".money", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".money") + 1000));
  105                p.sendMessage(ChatColor.GREEN + "Je hebt €1000 euro loon gekregen!");
  106              } else if (Main.this.getConfig().getInt(p.getName() + ".rank") == 4) {
  107                Main.this.getConfig().set(p.getPlayer().getName() + ".emerald", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".emerald") + 1));
  108                Main.this.getConfig().set(p.getName() + ".gblock", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".gblock") + 2));
  109                Main.this.getConfig().set(p.getName() + ".gold", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".gold") + 5));
  110                Main.this.getConfig().set(p.getName() + ".money", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".money") + 1250));
  111                p.sendMessage(ChatColor.GREEN + "Je hebt €1250 euro loon gekregen!");
  112              } else if (Main.this.getConfig().getInt(p.getName() + ".rank") == 5) {
  113                Main.this.getConfig().set(p.getName() + ".emerald", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".emerald") + 1));
  114                Main.this.getConfig().set(p.getName() + ".iron", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".iron") + 1));
  115                Main.this.getConfig().set(p.getName() + ".money", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".money") + 1500));
  116                p.sendMessage(ChatColor.GREEN + "Je hebt €1500 euro loon gekregen!");
  117              } else if (Main.this.getConfig().getInt(p.getName() + ".rank") == 6) {
  118                Main.this.getConfig().set(p.getName() + ".gblock", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".gblock") + 2));
  119                Main.this.getConfig().set(p.getName() + ".gold", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".gold") + 5));
  120                Main.this.getConfig().set(p.getName() + ".emerald", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".emerald") + 1));
  121                Main.this.getConfig().set(p.getName() + ".iron", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".iron") + 1));
  122                Main.this.getConfig().set(p.getName() + ".money", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".money") + 1750));
  123                p.sendMessage(ChatColor.GREEN + "Je hebt €1750 euro loon gekregen!");
  124              } else if (Main.this.getConfig().getInt(p.getName() + ".rank") > 6) {
  125                Main.this.getConfig().set(p.getName() + ".emerald", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".emerald") + 2));
  126                Main.this.getConfig().set(p.getName() + ".money", Integer.valueOf(Main.this.getConfig().getInt(p.getName() + ".money") + 2000));
  127                p.sendMessage(ChatColor.GREEN + "Je hebt €2000 euro loon gekregen!");
                   }
  129              Main.this.saveConfig();
                 }
                 
               }
               
             }
             
  136      }, 0L, getConfig().getInt("loan") * 60 * 20);
         }
         
         public String getRank(Player p) {
  140      String rank = "Level" + getConfig().getInt(new StringBuilder(String.valueOf(p.getName())).append(".rank").toString());
           
       
  143      return rank;
         }
         
         @EventHandler
         public void PlayerJoin(final PlayerJoinEvent e) {
  148      this.PlayerLoan.add(e.getPlayer());
  149      getConfig().addDefault(e.getPlayer().getName() + ".emerald", Integer.valueOf(5));
  150      getConfig().addDefault(e.getPlayer().getName() + ".money", Integer.valueOf(5000));
           
  152      e.setJoinMessage(ChatColor.GREEN + "§l[+] " + e.getPlayer().getName());
  153      Player p = e.getPlayer();
  154      getConfig().addDefault(p.getName() + ".money", Integer.valueOf(0));
  155      getConfig().addDefault(p.getName() + ".food", Integer.valueOf(0));
  156      getConfig().addDefault(p.getName() + ".fitheid", Integer.valueOf(50));
  157      getConfig().addDefault(p.getName() + ".dia", Integer.valueOf(0));
  158      getConfig().addDefault(p.getName() + ".emerald", Integer.valueOf(0));
  159      getConfig().addDefault(p.getName() + ".iron", Integer.valueOf(0));
  160      getConfig().addDefault(p.getName() + ".dorst", Integer.valueOf(0));
  161      getConfig().addDefault(p.getName() + ".gblock", Integer.valueOf(0));
  162      getConfig().addDefault(p.getName() + ".gold", Integer.valueOf(0));
  163      getConfig().addDefault(p.getName() + ".gnugget", Integer.valueOf(0));
  164      getConfig().addDefault(p.getName() + ".minutes", Integer.valueOf(0));
  165      getConfig().addDefault(p.getName() + ".rank", Integer.valueOf(1));
  166      getConfig().addDefault("Job.players." + p.getName(), "Burger");
  167      getConfig().addDefault("players." + p.getName(), "Default");
  168      if (getConfig().getString("Job.players." + p.getName()).equalsIgnoreCase("Default")) {
  169        getConfig().set("Job.players." + p.getName(), "Burger");
           }
  171      if (getConfig().getInt(p.getName() + ".fitheid") < 0) {
  172        getConfig().set(p.getName() + ".fitheid", Integer.valueOf(50));
           }
  174      saveConfig();
  175      ScoreBoardUpdate(p);
  176      Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
             public void run() {
  178          if (Bukkit.getOnlinePlayers().contains(e.getPlayer())) {
  179            Main.this.ScoreBoardUpdate(e.getPlayer());
               }
             }
  182      }, 0L, 300L);
           
       
       
       
       
       
       
       
       
       
  193      if ((p.hasPermission("atm.command")) && 
  194        (!getConfig().getString("Message").equals("disabled"))) {
  195        p.sendMessage(ChatColor.DARK_RED + "LET OP: DEZE PLUGIN IS GRATIS! \n" + ChatColor.GREEN + "Als je deze plugin gekocht hebt, vraag dan je geld terug!\n" + "Deze plugin is gemaakt door: Elthandorr. \n" + ChatColor.BLUE + "Officiële download link: http://dev.bukkit.org/bukkit-plugins/simpleatm-plugin/" + ChatColor.RED + "\n Om dit bericht uit te schakelen, typ /atm disablemessage");
           }
         }
         
       
       
         @EventHandler
         public void onleave(PlayerQuitEvent e)
         {
  204      while (this.PlayerLoan.contains(e.getPlayer())) {
  205        this.PlayerLoan.remove(e.getPlayer());
           }
  207      e.setQuitMessage(ChatColor.DARK_RED + "§l[-] " + e.getPlayer().getName());
  208      for (Player p : Bukkit.getOnlinePlayers())
  209        ScoreBoardUpdate(p);
         }
         
         public String getRankNR(String p) {
  213      String rank = getConfig().getString(p);
  214      return rank;
         }
         
  217    public String getJob(String p) { String job = getConfig().getString("Job.players." + p);
  218      return job;
         }
         
         @EventHandler
         public void PlayerChat(AsyncPlayerChatEvent e) {
  223      Player p = e.getPlayer();
  224      if (((getConfig().getString("worldWhitelist").equalsIgnoreCase("yes")) && (p.getLocation().getWorld().getName().equalsIgnoreCase(getConfig().getString("WhitelistWorld")))) || (getConfig().getString("worldWhitelist").equalsIgnoreCase("no")))
           {
       
  227        if (getConfig().getString("players." + p.getName()).equals("Default")) {
  228          if (getConfig().getString("Job.players." + p.getName()).equals("Default")) {
  229            e.setFormat(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + getRank(p) + ChatColor.DARK_AQUA + "] " + ChatColor.WHITE + p.getName() + ": " + ChatColor.GRAY + e.getMessage());
               } else {
  231            e.setFormat(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + getRank(p) + ChatColor.DARK_AQUA + "] " + ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + getJob(p.getName()) + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE + p.getName() + ": " + ChatColor.GRAY + e.getMessage());
               }
             }
  234        else if (getConfig().getString("Job.players." + p.getName()).equals("Default")) {
  235          if (getConfig().getString("Rank.r" + getConfig().getString(getConfig().getString(new StringBuilder("players.").append(p.getName()).toString())) + ".color").equalsIgnoreCase("rood")) {
  236            e.setFormat(ChatColor.DARK_RED + "[" + ChatColor.RED + getConfig().getString(new StringBuilder("players.").append(p.getName()).toString()) + ChatColor.DARK_RED + "] " + ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + getRank(p) + ChatColor.DARK_AQUA + "] " + ChatColor.WHITE + p.getName() + ": " + ChatColor.GRAY + e.getMessage());
  237          } else if (getConfig().getString("Rank.r" + getConfig().getString(getConfig().getString(new StringBuilder("players.").append(p.getName()).toString())) + ".color").equalsIgnoreCase("blauw")) {
  238            e.setFormat(ChatColor.DARK_BLUE + "[" + ChatColor.BLUE + getConfig().getString(new StringBuilder("players.").append(p.getName()).toString()) + ChatColor.DARK_BLUE + "] " + ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + getRank(p) + ChatColor.DARK_AQUA + "] " + ChatColor.WHITE + p.getName() + ": " + ChatColor.GRAY + e.getMessage());
  239          } else if (getConfig().getString("Rank.r" + getConfig().getString(getConfig().getString(new StringBuilder("players.").append(p.getName()).toString())) + ".color").equalsIgnoreCase("geel")) {
  240            e.setFormat(ChatColor.GOLD + "[" + ChatColor.YELLOW + getConfig().getString(new StringBuilder("players.").append(p.getName()).toString()) + ChatColor.GOLD + "] " + ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + getRank(p) + ChatColor.DARK_AQUA + "] " + ChatColor.WHITE + p.getName() + ": " + ChatColor.GRAY + e.getMessage());
               } else {
  242            e.setFormat(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + getConfig().getString(new StringBuilder("players.").append(p.getName()).toString()) + ChatColor.DARK_AQUA + "] " + ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + getRank(p) + ChatColor.DARK_AQUA + "] " + ChatColor.WHITE + p.getName() + ": " + ChatColor.GRAY + e.getMessage());
               }
             }
  245        else if (getConfig().getString("Rank.r" + getConfig().getString(getConfig().getString(new StringBuilder("players.").append(p.getName()).toString())) + ".color").equalsIgnoreCase("rood")) {
  246          e.setFormat(ChatColor.DARK_RED + "[" + ChatColor.RED + getConfig().getString(new StringBuilder("players.").append(p.getName()).toString()) + ChatColor.DARK_RED + "] " + ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + getRank(p) + ChatColor.DARK_AQUA + "] " + ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + getJob(p.getName()) + ChatColor.DARK_GRAY + "]" + ChatColor.WHITE + p.getName() + ": " + ChatColor.GRAY + e.getMessage());
  247        } else if (getConfig().getString("Rank.r" + getConfig().getString(getConfig().getString(new StringBuilder("players.").append(p.getName()).toString())) + ".color").equalsIgnoreCase("blauw")) {
  248          e.setFormat(ChatColor.DARK_BLUE + "[" + ChatColor.BLUE + getConfig().getString(new StringBuilder("players.").append(p.getName()).toString()) + ChatColor.DARK_BLUE + "] " + ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + getRank(p) + ChatColor.DARK_AQUA + "] " + ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + getJob(p.getName()) + ChatColor.DARK_GRAY + "]" + ChatColor.WHITE + p.getName() + ": " + ChatColor.GRAY + e.getMessage());
  249        } else if (getConfig().getString("Rank.r" + getConfig().getString(getConfig().getString(new StringBuilder("players.").append(p.getName()).toString())) + ".color").equalsIgnoreCase("geel")) {
  250          e.setFormat(ChatColor.GOLD + "[" + ChatColor.YELLOW + getConfig().getString(new StringBuilder("players.").append(p.getName()).toString()) + ChatColor.GOLD + "] " + ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + getRank(p) + ChatColor.DARK_AQUA + "] " + ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + getJob(p.getName()) + ChatColor.DARK_GRAY + "]" + ChatColor.WHITE + p.getName() + ": " + ChatColor.GRAY + e.getMessage());
             } else {
  252          e.setFormat(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + getConfig().getString(new StringBuilder("players.").append(p.getName()).toString()) + ChatColor.DARK_AQUA + "] " + ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + getRank(p) + ChatColor.DARK_AQUA + "] " + ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + getJob(p.getName()) + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE + p.getName() + ": " + ChatColor.GRAY + e.getMessage());
             }
           }
         }
         
         public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
         {
  259      if (cmd.getName().equalsIgnoreCase("rekening")) {
  260        if ((args.length == 0) || (args[0].equalsIgnoreCase("help"))) {
  261          sender.sendMessage(ChatColor.GREEN + "====================Rekening help====================");
  262          sender.sendMessage(ChatColor.GOLD + "/rekening help " + ChatColor.GRAY + "- Laat deze pagina zien");
  263          sender.sendMessage(ChatColor.GOLD + "/rekening create <rekening> " + ChatColor.GRAY + "- Maak een rekening. (kost 1k)");
  264          sender.sendMessage(ChatColor.GOLD + "/rekening add <speler> <rekening> " + ChatColor.GRAY + "- Voeg een speler toe aan een rekening.");
  265          sender.sendMessage(ChatColor.GOLD + "/rekening remove <speler> <rekening> " + ChatColor.GRAY + "- Verwijder een speler van een rekening");
  266          sender.sendMessage(ChatColor.GREEN + "====================Rekening help====================");
             }
  268        if (args[0].equalsIgnoreCase("create")) {
  269          if (args.length == 2) {
  270            if (getConfig().getInt(sender.getName() + ".money") >= 1000) {
  271              if (getConfig().getInt(sender.getName() + ".emerald") >= 1) {
  272                if (!getConfig().getStringList("account").contains(args[1])) {
  273                  getConfig().set(sender.getName() + ".iron", Integer.valueOf(getConfig().getInt(sender.getName() + ".emerald") - 1));
  274                  getConfig().set(sender.getName() + ".money", Integer.valueOf(getConfig().getInt(sender.getName() + ".money") - 1000));
  275                  List<String> stringList3 = getConfig().getStringList("account");
  276                  stringList3.add(args[1]);
  277                  getConfig().set("account", stringList3);
  278                  getConfig().set("accounts." + args[1], Integer.valueOf(getConfig().getInt("Accounts") + 1));
  279                  getConfig().set("accounts.r" + (getConfig().getInt("Accounts") + 1) + ".name", args[1]);
  280                  List<String> stringList4 = getConfig().getStringList("accounts.r" + (getConfig().getInt("Accounts") + 1) + ".admins");
  281                  stringList4.add(sender.getName());
  282                  getConfig().set("accounts.r" + (getConfig().getInt("Accounts") + 1) + ".admins", stringList4);
  283                  List<String> stringList5 = getConfig().getStringList("accounts.r" + (getConfig().getInt("Accounts") + 1) + ".players");
  284                  stringList5.add(sender.getName());
  285                  getConfig().set("accounts.r" + (getConfig().getInt("Accounts") + 1) + ".players", stringList5);
  286                  getConfig().set("accounts.r" + (getConfig().getInt("Accounts") + 1) + ".money", Integer.valueOf(0));
  287                  getConfig().set("accounts.r" + (getConfig().getInt("Accounts") + 1) + ".dia", Integer.valueOf(0));
  288                  getConfig().set("accounts.r" + (getConfig().getInt("Accounts") + 1) + ".emerald", Integer.valueOf(0));
  289                  getConfig().set("accounts.r" + (getConfig().getInt("Accounts") + 1) + ".iron", Integer.valueOf(0));
  290                  getConfig().set("accounts.r" + (getConfig().getInt("Accounts") + 1) + ".gblock", Integer.valueOf(0));
  291                  getConfig().set("accounts.r" + (getConfig().getInt("Accounts") + 1) + ".gold", Integer.valueOf(0));
  292                  getConfig().set("accounts.r" + (getConfig().getInt("Accounts") + 1) + ".gnugget", Integer.valueOf(0));
  293                  getConfig().set("Accounts", Integer.valueOf(getConfig().getInt("Accounts") + 1));
  294                  saveConfig();
  295                  sender.sendMessage(ChatColor.GREEN + "Je bankrekening is gemaakt voor €1000!");
  296                  sender.sendMessage(ChatColor.GREEN + "Gebruik /rekening open <rekeningnaam> om je rekening te bekijken.");
  297                  this.rek.add((Player)sender);
  298                  openGUI((Player)sender, "accounts.r" + getConfig().getInt("Accounts"));
                     }
                   }
                   else {
  302                sender.sendMessage(ChatColor.RED + "Wissel Je geld in voor emerald blocks om een bedrijfsrekening te kunnen kopen.");
                   }
                 } else {
  305              sender.sendMessage(ChatColor.RED + "Je hebt niet genoeg geld voor een bedrijfsrekening (€1000).");
                 }
               }
  308        } else if (args[0].equalsIgnoreCase("open")) {
  309          if (args.length == 2) {
  310            this.rek.add((Player)sender);
  311            if (getConfig().getStringList("account").contains(args[1])) {
  312              if ((getConfig().getStringList("accounts.r" + getConfig().getInt(new StringBuilder("accounts.").append(args[1]).toString()) + ".admins").contains(sender.getName())) || (getConfig().getStringList("accounts.r" + getConfig().getInt(new StringBuilder("accounts.").append(args[1]).toString()) + ".players").contains(sender.getName()))) {
  313                openGUI((Player)sender, "accounts.r" + getConfig().getInt(new StringBuilder("accounts.").append(args[1]).toString()));
                   } else {
  315                sender.sendMessage(ChatColor.RED + "Je hebt geen toegang om deze bankrekening te openen!");
                   }
                 } else {
  318              sender.sendMessage(ChatColor.RED + "Deze rekening bestaat niet! (Let op hoofdletters!)");
  319              sender.sendMessage(ChatColor.GREEN + "Maak een rekening met /rekening create <rekening>");
                 }
               }
  322        } else if (args[0].equalsIgnoreCase("add")) {
  323          if (args.length == 3) {
  324            if (getConfig().getStringList("account").contains(args[2])) {
  325              if (getConfig().getStringList("accounts.r" + getConfig().getInt(new StringBuilder("accounts.").append(args[2]).toString()) + ".admins").contains(sender.getName()))
                   {
  327                List<String> stringList = getConfig().getStringList("accounts.r" + getConfig().getInt(new StringBuilder("accounts.").append(args[2]).toString()) + ".players");
  328                stringList.add(args[1]);
  329                getConfig().set("accounts.r" + getConfig().getInt(new StringBuilder("accounts.").append(args[2]).toString()) + ".players", stringList);
  330                sender.sendMessage(ChatColor.GREEN + "Speler " + args[1] + " kan nu de bankrekening " + args[2] + " gebruiken.");
                   } else {
  332                sender.sendMessage(ChatColor.RED + "Je bent geen beheerder van deze bankrekening!");
                   }
                 } else {
  335              sender.sendMessage(ChatColor.RED + "Deze rekening bestaat niet! (Let op hoofdletters!)");
  336              sender.sendMessage(ChatColor.GREEN + "Maak een rekening met /rekening create <rekening>");
                 }
               } else {
  339            sender.sendMessage(ChatColor.RED + "Fout commandgebruik!");
  340            sender.sendMessage(ChatColor.GREEN + "Gebruik: /rekening add <speler> <rekening>");
               }
  342        } else if (args[0].equalsIgnoreCase("remove")) {
  343          if (args.length == 3) {
  344            if (getConfig().getStringList("account").contains(args[2])) {
  345              if (getConfig().getStringList("accounts.r" + getConfig().getInt(new StringBuilder("accounts.").append(args[2]).toString()) + ".admins").contains(sender.getName())) {
  346                if ((getConfig().getStringList("accounts.r" + getConfig().getInt(new StringBuilder("accounts.").append(args[2]).toString()) + ".players").contains(args[1])) || (getConfig().getStringList("accounts.r" + getConfig().getInt(new StringBuilder("accounts.").append(args[2]).toString()) + ".admins").contains(args[1]))) {
  347                  List<String> stringList = getConfig().getStringList("accounts.r" + getConfig().getInt(new StringBuilder("accounts.").append(args[2]).toString()) + ".players");
  348                  stringList.remove(args[1]);
  349                  getConfig().set("accounts.r" + getConfig().getInt(new StringBuilder("accounts.").append(args[2]).toString()) + ".players", stringList);
  350                  sender.sendMessage(ChatColor.GREEN + "Speler " + args[1] + " kan nu de bankrekening " + args[2] + " niet meer gebruiken.");
                     } else {
  352                  sender.sendMessage(ChatColor.RED + "De speler " + args[1] + " heeft al geen toegang tot jouw rekening! \n Let op dat de hoofdletters kloppen!");
                     }
                   }
                   else {
  356                sender.sendMessage(ChatColor.RED + "Je bent geen beheerder van deze bankrekening!");
                   }
                 } else {
  359              sender.sendMessage(ChatColor.RED + "Deze rekening bestaat niet! (Let op hoofdletters!)");
  360              sender.sendMessage(ChatColor.GREEN + "Maak een rekening met /rekening create <rekening>");
                 }
               } else {
  363            sender.sendMessage(ChatColor.RED + "Fout commandgebruik!");
  364            sender.sendMessage(ChatColor.GREEN + "Gebruik: /rekening remove <speler> <rekening>");
               }
             }
  367      } else if ((cmd.getName().equalsIgnoreCase("baan")) || (cmd.getName().equalsIgnoreCase("job"))) {
  368        if ((args.length == 0) || (args[0].equalsIgnoreCase("help"))) {
  369          sender.sendMessage(ChatColor.GREEN + "====================Job help====================");
  370          sender.sendMessage(ChatColor.GOLD + "/job help " + ChatColor.GRAY + "- Laat deze pagina zien");
  371          sender.sendMessage(ChatColor.GOLD + "/job create <baan> " + ChatColor.GRAY + "- Maak een baan.");
  372          sender.sendMessage(ChatColor.GOLD + "/job add <speler> <baan> " + ChatColor.GRAY + "- Voeg een speler toe aan een baan.");
  373          sender.sendMessage(ChatColor.GOLD + "/job remove <speler> <baan> " + ChatColor.GRAY + "- verwijdert de spelers baan..");
  374          sender.sendMessage(ChatColor.GREEN + "====================Job help====================");
  375        } else if (args[0].equalsIgnoreCase("create")) {
  376          if (args.length == 2) {
  377            if (sender.hasPermission("atm.command")) {
  378              if (!getConfig().getStringList("Jobs").contains(args[1])) {
  379                List<String> stringList = getConfig().getStringList("Jobs");
  380                stringList.add(args[1]);
  381                getConfig().set("Jobs", stringList);
  382                saveConfig();
  383                sender.sendMessage(ChatColor.GREEN + "Baan " + ChatColor.RED + args[1] + " is succesvol gemaakt!");
  384                sender.sendMessage(ChatColor.GOLD + "Gebruik /baan add <speler> <rank>" + ChatColor.GRAY + " Om iemand toe te voegen!");
                   }
                   else {
  387                sender.sendMessage(ChatColor.DARK_RED + "Deze baan bestaat al.");
                   }
                 } else {
  390              sender.sendMessage(ChatColor.DARK_RED + "Je hebt geen permission voor dit command.");
                 }
               } else {
  393            sender.sendMessage(ChatColor.DARK_RED + "Fout command gebruik!");
  394            sender.sendMessage(ChatColor.GREEN + "Gebruik: /baan create <naam>");
               }
  396        } else if (args[0].equalsIgnoreCase("remove")) {
  397          if (sender.hasPermission("atm.command")) {
  398            if (args.length == 2) {
  399              getConfig().set("Job.players." + args[1], "Default");
  400              saveConfig();
  401              sender.sendMessage(ChatColor.GREEN + "Speler " + args[1] + " heeft nu geen baan meer!");
                 }
                 else {
  404              sender.sendMessage(ChatColor.GREEN + "Gebruik: /baan remove <speler>");
                 }
               } else {
  407            sender.sendMessage(ChatColor.DARK_RED + "Je hebt geen permissie om dit te doen!");
               }
  409        } else if (args[0].equalsIgnoreCase("add")) {
  410          if (sender.hasPermission("atm.command")) {
  411            if (args.length == 3) {
  412              if (getConfig().getStringList("Jobs").contains(args[2])) {
  413                getConfig().set("Job.players." + args[1], args[2]);
  414                saveConfig();
  415                sender.sendMessage(ChatColor.GREEN + "Speler " + args[1] + " heeft nu de baan " + args[2] + "!");
                   } else {
  417                sender.sendMessage(ChatColor.RED + "Deze baan bestaat niet.");
  418                sender.sendMessage(ChatColor.GREEN + "Maak een rank met /baan create <naam>");
                   }
                 } else {
  421              sender.sendMessage(ChatColor.GREEN + "Gebruik: /baan add <speler> <baan>");
                 }
               } else {
  424            sender.sendMessage(ChatColor.DARK_RED + "Je hebt geen permissie om dit te doen!");
               }
             }
  427      } else if (cmd.getName().equalsIgnoreCase("rank")) {
  428        if ((args.length == 0) || (args[0].equalsIgnoreCase("help"))) {
  429          sender.sendMessage(ChatColor.GREEN + "====================Rank help====================");
  430          sender.sendMessage(ChatColor.GOLD + "/rank help " + ChatColor.GRAY + "- Laat deze pagina zien");
  431          sender.sendMessage(ChatColor.GOLD + "/rank create <rank> " + ChatColor.GRAY + "- Maak een rank.");
  432          sender.sendMessage(ChatColor.GOLD + "/rank setColor <blauw/aqua/rood/geel> <rank> " + ChatColor.GRAY + "- verander de kleur.");
  433          sender.sendMessage(ChatColor.GOLD + "/rank add <speler> <rank> " + ChatColor.GRAY + "- Voeg een speler toe.");
  434          sender.sendMessage(ChatColor.GOLD + "/rank remove <speler> <rank> " + ChatColor.GRAY + "- verwijdert de spelers rank.");
  435          sender.sendMessage(ChatColor.GREEN + "====================Rank help====================");
  436        } else if (args[0].equalsIgnoreCase("setColor")) {
  437          if (sender.hasPermission("atm.command")) {
  438            if (args.length == 3) {
  439              if (getConfig().getStringList("Ranks").contains(args[2])) {
  440                if ((args[1].equalsIgnoreCase("rood")) || (args[1].equalsIgnoreCase("blauw")) || (args[1].equalsIgnoreCase("geel")) || (args[1].equalsIgnoreCase("aqua"))) {
  441                  getConfig().set("Rank.r" + getRankNR(args[2]) + ".color", args[1]);
  442                  saveConfig();
  443                  sender.sendMessage(ChatColor.GREEN + "De kleur van de rank " + args[2] + " is naar " + args[1] + " gezet!");
                     } else {
  445                  sender.sendMessage(ChatColor.GREEN + "Gebruik: /rank setColor <kleur> <rank>");
  446                  sender.sendMessage(ChatColor.GREEN + "Kies uit: Rood, blauw, geel, aqua");
                     }
                   } else {
  449                sender.sendMessage(ChatColor.RED + "Deze rank bestaat niet.");
  450                sender.sendMessage(ChatColor.GREEN + "Maak een rank met /rank create <naam>");
                   }
                 } else {
  453              sender.sendMessage(ChatColor.GREEN + "Gebruik: /rank setColor <kleur> <rank>");
  454              sender.sendMessage(ChatColor.GREEN + "Kies uit: Rood, blauw, geel, aqua");
                 }
               } else {
  457            sender.sendMessage(ChatColor.DARK_RED + "Je hebt geen permissie om dit te doen!");
               }
  459        } else if (args[0].equalsIgnoreCase("remove")) {
  460          if (sender.hasPermission("atm.command")) {
  461            if (args.length == 2) {
  462              getConfig().set("players." + args[1], "Default");
  463              saveConfig();
  464              sender.sendMessage(ChatColor.GREEN + "Speler " + args[1] + " heeft nu geen rank meer!");
                 }
                 else {
  467              sender.sendMessage(ChatColor.GREEN + "Gebruik: /rank remove <speler>");
                 }
               } else {
  470            sender.sendMessage(ChatColor.DARK_RED + "Je hebt geen permissie om dit te doen!");
               }
  472        } else if (args[0].equalsIgnoreCase("add")) {
  473          if (sender.hasPermission("atm.command")) {
  474            if (args.length == 3) {
  475              if (getConfig().getStringList("Ranks").contains(args[2])) {
  476                List<String> stringList2 = getConfig().getStringList("Rank.r" + getRankNR(args[2]) + ".members");
  477                stringList2.add(args[1]);
  478                getConfig().set("Rank.r" + getRankNR(args[2]) + ".members", stringList2);
  479                getConfig().set("players." + args[1], args[2]);
  480                saveConfig();
  481                sender.sendMessage(ChatColor.GREEN + "Speler " + args[1] + " heeft nu de rank " + args[2] + "!");
                   } else {
  483                sender.sendMessage(ChatColor.RED + "Deze rank bestaat niet.");
  484                sender.sendMessage(ChatColor.GREEN + "Maak een rank met /rank create <naam>");
                   }
                 } else {
  487              sender.sendMessage(ChatColor.GREEN + "Gebruik: /rank add <speler> <rank>");
                 }
               } else {
  490            sender.sendMessage(ChatColor.DARK_RED + "Je hebt geen permissie om dit te doen!");
               }
  492        } else if (args[0].equalsIgnoreCase("create")) {
  493          if (sender.hasPermission("atm.command")) {
  494            if (args.length == 2) {
  495              sender.sendMessage(ChatColor.RED + "Controleren of rank al bestaat...");
  496              if (!getConfig().getStringList("Ranks").contains(args[1])) {
  497                sender.sendMessage(ChatColor.GREEN + "Rank bestaat nog niet!");
  498                List<String> stringList = getConfig().getStringList("Ranks");
  499                stringList.add(args[1]);
  500                getConfig().set("Ranks", stringList);
  501                getConfig().set("counter", Integer.valueOf(getConfig().getInt("counter") + 1));
  502                getConfig().set("Rank.r" + getConfig().getInt("counter") + ".name", args[1]);
  503                getConfig().set("Rank.r" + getConfig().getInt("counter") + ".color", "rood");
  504                getConfig().set(args[1], Integer.valueOf(getConfig().getInt("counter")));
  505                List<String> stringList2 = getConfig().getStringList("Rank.r" + getConfig().getInt("counter") + ".members");
  506                stringList2.add(" ");
  507                getConfig().set("Rank.r" + getConfig().getInt("counter") + ".members", stringList2);
  508                saveConfig();
  509                sender.sendMessage(ChatColor.GREEN + "Rank " + ChatColor.RED + args[1] + " is succesvol gemaakt!");
  510                sender.sendMessage(ChatColor.GOLD + "Gebruik /rank add <speler> <rank>" + ChatColor.GRAY + " Om iemand toe te voegen!");
  511                sender.sendMessage(ChatColor.GOLD + "Gebruik /rank setColor <kleur> <rank>" + ChatColor.GRAY + " Verander de kleur van je rank!");
                   } else {
  513                sender.sendMessage(ChatColor.RED + "Deze rank bestaat al!");
                   }
                 } else {
  516              sender.sendMessage(ChatColor.GREEN + "Gebruik: /rank create <naam>");
                 }
               } else {
  519            sender.sendMessage(ChatColor.DARK_RED + "Je hebt geen permissie om dit te doen!");
               }
             }
           }
  523      if ((cmd.getName().equalsIgnoreCase("atm")) || (cmd.getName().equalsIgnoreCase("minetopia"))) {
  524        if ((args[0].equalsIgnoreCase("help")) || (args.length == 0)) {
  525          sender.sendMessage(ChatColor.GREEN + "==================Minetopia Help==================");
  526          sender.sendMessage(ChatColor.GOLD + "/atm setScoreboard <title/money/online> <text> -" + ChatColor.GRAY + " Verandert het scoreboard");
  527          sender.sendMessage(ChatColor.GOLD + "/atm help -" + ChatColor.GRAY + " Laat deze pagina zien.");
  528          sender.sendMessage(ChatColor.GOLD + "/atm pay <speler> <50000/100000> -" + ChatColor.GRAY + " Geeft een speler geld.");
  529          sender.sendMessage(ChatColor.GOLD + "/atm openBank <player> -" + ChatColor.GRAY + " Opent iemands bank account.");
  530          sender.sendMessage(ChatColor.GOLD + "/atm setTime <aantal uren> -" + ChatColor.GRAY + " verandert de tijd.");
  531          sender.sendMessage(ChatColor.GOLD + "/atm levelup <player> -" + ChatColor.GRAY + " Zet iemand spelerlevel omhoog.");
  532          sender.sendMessage(ChatColor.GOLD + "/atm leveldown <player> -" + ChatColor.GRAY + " Zet iemand spelerlevel omlaag.");
  533          sender.sendMessage(ChatColor.GOLD + "/atm setWorld <wereld> -" + ChatColor.GRAY + " Kies de wereld voor MinetopiaATM");
  534          sender.sendMessage(ChatColor.GOLD + "/atm disableMessage -" + ChatColor.GRAY + " Haalt het bericht weg als ops joinen.");
  535          sender.sendMessage(ChatColor.GOLD + "/atm setLoanTime <tijd in min>-" + ChatColor.GRAY + " Veranderd hoe snel mensen salaris krijgen.");
  536          sender.sendMessage(ChatColor.GREEN + "==================Minetopia Help==================");
  537        } else if (args[0].equalsIgnoreCase("setworld")) {
  538          if (sender.hasPermission("atm.command")) {
  539            if (args.length == 2) {
  540              getConfig().set("worldWhitelist", "yes");
  541              getConfig().set("WhitelistWorld", args[1]);
  542              sender.sendMessage(ChatColor.GREEN + "MinetopiaATM werkt nu alleen in de wereld: " + args[1]);
                 } else {
  544              sender.sendMessage(ChatColor.RED + "Fout commandgebruik!");
  545              sender.sendMessage(ChatColor.GREEN + "Gebruik: /atm setWorld <wereldnaam>");
                 }
               } else {
  548            sender.sendMessage(ChatColor.RED + "Je hebt geen toestemming om dit te doen!");
               }
  550        } else if (args[0].equalsIgnoreCase("setLoanTime")) {
  551          if (sender.hasPermission("atm.command")) {
  552            if (args.length == 2) {
  553              int i = Integer.parseInt(args[1]);
  554              getConfig().set("loan", Integer.valueOf(i));
  555              sender.sendMessage(ChatColor.GREEN + "Het duurt nu " + i + " minuten voordat mensen hun salaris krijgen.");
                 } else {
  557              sender.sendMessage(ChatColor.RED + "Fout commandgebruik!");
  558              sender.sendMessage(ChatColor.GREEN + "Gebruik: /atm setloantime <tijd in minuten>");
                 }
               } else {
  561            sender.sendMessage(ChatColor.RED + "Je hebt geen toestemming om dit te doen!");
               }
  563        } else if (args[0].equalsIgnoreCase("setTime")) {
  564          if (sender.hasPermission("atm.command")) {
  565            if (args.length == 2) {
  566              int i = Integer.parseInt(args[1]);
  567              getConfig().set("Time", Integer.valueOf(getConfig().getInt("Time") + i));
  568              saveConfig();
  569              sender.sendMessage(ChatColor.GREEN + "De tijd is " + i + " uur verzet!");
                 }
                 else {
  572              sender.sendMessage(ChatColor.DARK_RED + "Fout!");
  573              sender.sendMessage(ChatColor.GREEN + "Gebruik: /atm setTime <hoeveelheid uur>");
                 }
               }
  576        } else if (args[0].equalsIgnoreCase("leveldown")) {
  577          if (sender.hasPermission("atm.command")) {
  578            if (args.length == 2) {
  579              getConfig().set(args[1] + ".rank", Integer.valueOf(getConfig().getInt(args[1] + ".rank") - 1));
  580              saveConfig();
  581              sender.sendMessage(ChatColor.GREEN + "Speler " + args[1] + " is nu level " + getConfig().getInt(new StringBuilder(String.valueOf(args[1])).append(".rank").toString()) + "!");
                 } else {
  583              sender.sendMessage(ChatColor.GREEN + "Gebruik: /atm LevelUp <spelernaam>");
                 }
               } else {
  586            sender.sendMessage(ChatColor.DARK_RED + "Je hebt geen permissie om dit te doen!");
               }
  588        } else if (args[0].equalsIgnoreCase("levelup")) {
  589          if (sender.hasPermission("atm.command")) {
  590            if (args.length == 2) {
  591              getConfig().set(args[1] + ".rank", Integer.valueOf(getConfig().getInt(args[1] + ".rank") + 1));
  592              saveConfig();
  593              sender.sendMessage(ChatColor.GREEN + "Speler " + args[1] + " is nu level " + getConfig().getInt(new StringBuilder(String.valueOf(args[1])).append(".rank").toString()) + "!");
                 } else {
  595              sender.sendMessage(ChatColor.GREEN + "Gebruik: /atm LevelUp <spelernaam>");
                 }
               } else {
  598            sender.sendMessage(ChatColor.DARK_RED + "Je hebt geen permissie om dit te doen!");
               }
  600        } else if (args[0].equalsIgnoreCase("pay")) {
  601          if (sender.hasPermission("atm.command")) {
  602            if (args.length == 3)
                 {
  604              int i = Integer.parseInt(args[2]);
  605              if ((i == 50000) || (i == 100000)) {
  606                getConfig().set(args[1] + ".money", Integer.valueOf(getConfig().getInt(args[1] + ".money") + i));
  607                getConfig().set(args[1] + ".dia", Integer.valueOf(getConfig().getInt(args[1] + ".dia") + i / 5000));
  608                saveConfig();
                   } else {
  610                sender.sendMessage("Kies uit: 100000 of 50000");
                   }
                 } else {
  613              sender.sendMessage(ChatColor.GREEN + "Gebruik: /Atm pay <speler> <hoeveelheid>");
                 }
               } else {
  616            sender.sendMessage(ChatColor.DARK_RED + "Je hebt geen permissie om dit te doen.");
               }
  618        } else if ((args[0].equalsIgnoreCase("openbank")) || (args[0].equalsIgnoreCase("openatm"))) {
  619          if (args.length == 2) {
  620            if (sender.hasPermission("atm.command"))
                 {
  622              Player player2 = sender.getServer().getPlayer(args[1]);
  623              if (Bukkit.getOnlinePlayers().contains(player2)) {
  624                List<String> stringList = getConfig().getStringList("watcherslist");
  625                stringList.add(sender.getName());
  626                getConfig().set("watcherslist", stringList);
  627                Player player = (Player)sender;
  628                openGUI(player, player2.getName());
  629                saveConfig();
                   } else {
  631                sender.sendMessage(ChatColor.RED + "Deze speler is niet online!");
                   }
                   
                 }
                 else
                 {
  637              sender.sendMessage(ChatColor.RED + "Je hebt geen toestemming om dit te doen!");
                 }
                 
               }
               else {
  642            sender.sendMessage(ChatColor.RED + "Gebruik: /atm openbank <speler> of /atm openatm <speler>");
               }
               
             }
  646        else if (args[0].equalsIgnoreCase("setScoreboard")) {
  647          if (sender.hasPermission("atm.command")) {
  648            if (args.length == 4) {
  649              if ((args[1].equalsIgnoreCase("title")) || (args[1].equalsIgnoreCase("money")) || (args[1].equalsIgnoreCase("online")) || (args[1].equalsIgnoreCase("level")) || (args[1].equalsIgnoreCase("time")) || (args[1].equalsIgnoreCase("fitheid")))
                   {
  651                getConfig().set("Scoreboard." + args[1], args[2] + " " + args[3]);
                     
  653                sender.sendMessage(ChatColor.GREEN + args[1] + " Is veranderd naar: " + args[2] + " " + args[3]);
                     
  655                saveConfig();
                   } else {
  657                sender.sendMessage(ChatColor.GREEN + "Kies uit: Title, Money, Online, time, fitheid, level");
  658                sender.sendMessage(ChatColor.GREEN + "bijvoorbeeld: /ATM setscoreboard title &4&lGeweldige&8&lServer");
                   }
                   
                 }
  662            else if (args.length == 3) {
  663              if ((args[1].equalsIgnoreCase("title")) || (args[1].equalsIgnoreCase("money")) || (args[1].equalsIgnoreCase("online")) || (args[1].equalsIgnoreCase("level")) || (args[1].equalsIgnoreCase("time")) || (args[1].equalsIgnoreCase("fitheid")))
                   {
  665                getConfig().set("Scoreboard." + args[1], args[2]);
                     
  667                sender.sendMessage(ChatColor.GREEN + args[1] + " Is veranderd naar: " + args[2] + " " + args[3]);
                     
  669                saveConfig();
                   } else {
  671                sender.sendMessage(ChatColor.GREEN + "Kies uit: Title, Money, Online, time, fitheid, level");
  672                sender.sendMessage(ChatColor.GREEN + "bijvoorbeeld: /ATM setscoreboard title &4&lGeweldige&8&lServer");
                   }
                 }
                 else {
  676              sender.sendMessage(ChatColor.GREEN + "Kies uit: Title, Money, Online, time, fitheid, level");
  677              sender.sendMessage(ChatColor.GREEN + "bijvoorbeeld: /ATM setscoreboard title &4&lGeweldige&8&lServer");
                 }
                 
               }
               else {
  682            sender.sendMessage(ChatColor.RED + "Je hebt geen toestemming om dit te doen!");
               }
             }
  685        else if (args[0].equalsIgnoreCase("disableMessage")) {
  686          if (sender.hasPermission("atm.command")) {
  687            getConfig().set("Message", "disabled");
               } else {
  689            sender.sendMessage(ChatColor.RED + "Je hebt geen toestemming om dit te doen!");
               }
             }
           }
           
  694      saveConfig();
  695      return true;
         }
         
         @EventHandler
  699    public void playerInteract(PlayerInteractEvent e) { if (e.getAction() == org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK) {
  700        org.bukkit.block.Block block = e.getClickedBlock();
             
  702        if ((block.getType() == Material.DROPPER) && (
  703          ((getConfig().getString("worldWhitelist").equalsIgnoreCase("yes")) && (e.getPlayer().getLocation().getWorld().getName().equalsIgnoreCase(getConfig().getString("WhitelistWorld")))) || (getConfig().getString("worldWhitelist").equalsIgnoreCase("no"))))
             {
  705          e.setCancelled(true);
  706          openGUI2(e.getPlayer());
  707          if (this.rek.contains(e.getPlayer())) {
  708            while (this.rek.contains(e.getPlayer())) {
  709              this.rek.remove(e.getPlayer());
                 }
               }
             }
           }
  714      else if (e.getAction() == org.bukkit.event.block.Action.RIGHT_CLICK_AIR) {
  715        ItemStack bottle = new ItemStack(Material.WATER_BUCKET);
  716        if (e.getPlayer().getItemInHand().equals(bottle)) {
  717          Player p = e.getPlayer();
  718          if ((getConfig().getInt(p.getName() + ".dorst") <= 60) && (getConfig().getInt(p.getName() + ".dorst") >= 45)) {
  719            p.setItemInHand(null);
  720            getConfig().set(p.getName() + ".dorst", Integer.valueOf(40));
  721            p.sendMessage(ChatColor.AQUA + "Je hebt water gedronken! \n Drink nog 2 water bucket om volledig te herstellen!");
  722          } else if ((getConfig().getInt(p.getName() + ".dorst") <= 44) && (getConfig().getInt(p.getName() + ".dorst") >= 25)) {
  723            p.setItemInHand(null);
  724            getConfig().set(p.getName() + ".dorst", Integer.valueOf(20));
  725            p.sendMessage(ChatColor.AQUA + "Je hebt water gedronken! \n Drink nog 1 water bucket om volledig te herstellen!");
  726          } else if (getConfig().getInt(p.getName() + ".dorst") <= 24) {
  727            p.setItemInHand(null);
  728            getConfig().set(p.getName() + ".dorst", Integer.valueOf(0));
  729            p.sendMessage(ChatColor.AQUA + "Je hebt water gedronken! \n Je bent volledig hersteld!");
               }
             }
           }
           
  734      saveConfig();
         }
         
  737    public void openGUI2(Player p) { Inventory inv = Bukkit.createInventory(null, 9, ChatColor.BLACK + "Welkom, " + p.getName() + "!");
           
       
       
  741      ItemStack survival = new ItemStack(Material.GOLD_NUGGET);
           
  743      ItemMeta survivalMeta = survival.getItemMeta();
           
       
  746      ItemStack creative = new ItemStack(Material.DIAMOND_BLOCK);
  747      ItemMeta creativeMeta = creative.getItemMeta();
           
  749      survivalMeta.setDisplayName(ChatColor.GOLD + "Wissel Machine.");
  750      survival.setItemMeta(survivalMeta);
           
       
       
       
  755      creativeMeta.setDisplayName(ChatColor.AQUA + "Bank account.");
  756      creative.setItemMeta(creativeMeta);
           
  758      inv.setItem(3, survival);
  759      inv.setItem(5, creative);
  760      p.openInventory(inv);
         }
         
         @EventHandler
         public void onInventoryClick2(InventoryClickEvent event) {
  765      if (!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Welkom, " + event.getWhoClicked().getName() + "!"))
           {
  767        return; }
  768      Player player = (Player)event.getWhoClicked();
  769      event.setCancelled(true);
  770      if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType() == Material.AIR) || (!event.getCurrentItem().hasItemMeta()))
           {
  772        return;
           }
           
  775      switch (event.getCurrentItem().getType()) {
           case COAL: 
  777        event.setCancelled(true);
  778        openGUI(player, player.getName());
  779        break;
           case SLIME_BLOCK: 
  781        event.setCancelled(true);
  782        openGUI3(player);
  783        break;
           }
           
         }
         
         public void openGUI3(Player p)
         {
  790      Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GOLD + "Jouw geld: " + getConfig().getInt(new StringBuilder(String.valueOf(p.getName())).append(".money").toString()));
           
       
  793      ItemStack survival = new ItemStack(Material.DIAMOND_BLOCK, getConfig().getInt(p.getName() + ".dia"));
  794      ItemMeta survivalMeta = survival.getItemMeta();
           
       
  797      ItemStack creative = new ItemStack(Material.EMERALD_BLOCK, getConfig().getInt(p.getName() + ".emerald"));
  798      ItemMeta creativeMeta = creative.getItemMeta();
           
       
  801      ItemStack adventure = new ItemStack(Material.IRON_BLOCK, getConfig().getInt(p.getName() + ".iron"));
  802      ItemMeta adventureMeta = adventure.getItemMeta();
           
  804      ItemStack spectator = new ItemStack(Material.GOLD_BLOCK, getConfig().getInt(p.getName() + ".gblock"));
  805      ItemMeta spectatorMeta = spectator.getItemMeta();
           
  807      ItemStack gold = new ItemStack(Material.GOLD_INGOT, getConfig().getInt(p.getName() + ".gold"));
  808      ItemMeta goldMeta = gold.getItemMeta();
           
  810      ItemStack gNugget = new ItemStack(Material.GOLD_NUGGET, getConfig().getInt(p.getName() + ".gnugget"));
  811      ItemMeta gNuggetMeta = gNugget.getItemMeta();
           
       
       
       
       
  817      survivalMeta.setDisplayName("Diamond Block = " + ChatColor.AQUA + "5 emerald blocks.");
  818      survival.setItemMeta(survivalMeta);
           
       
  821      creativeMeta.setDisplayName("Emerald Block = " + ChatColor.AQUA + "2 iron blocks.");
  822      creative.setItemMeta(creativeMeta);
           
  824      adventureMeta.setDisplayName("Iron Block = " + ChatColor.AQUA + "5 gold blocks.");
  825      adventure.setItemMeta(adventureMeta);
           
  827      spectatorMeta.setDisplayName("Gold Block = " + ChatColor.AQUA + "10 gold ingots.");
  828      spectator.setItemMeta(spectatorMeta);
           
  830      goldMeta.setDisplayName("Gold Ingot = " + ChatColor.AQUA + "10 gold nuggets.");
  831      gold.setItemMeta(goldMeta);
           
  833      gNuggetMeta.setDisplayName("Gold Nugget = " + ChatColor.AQUA + "Minder kan niet ;) ;).");
  834      gNugget.setItemMeta(gNuggetMeta);
           
       
  837      inv.setItem(1, survival);
  838      inv.setItem(2, creative);
  839      inv.setItem(3, adventure);
  840      inv.setItem(4, spectator);
  841      inv.setItem(5, gold);
  842      inv.setItem(6, gNugget);
  843      p.openInventory(inv);
         }
         
         @EventHandler
         public void onInventoryClick3(InventoryClickEvent event) {
  848      if ((!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Jouw geld: " + getConfig().getInt(new StringBuilder(String.valueOf(event.getWhoClicked().getName())).append(".money").toString()))) || (!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Jouw geld: " + getConfig().getInt(new StringBuilder(String.valueOf(event.getWhoClicked().getName())).append(".money").toString()))))
  849        return;
  850      Player player = (Player)event.getWhoClicked();
  851      event.setCancelled(true);
  852      if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType() == Material.AIR) || (!event.getCurrentItem().hasItemMeta()))
           {
  854        return;
           }
  856      switch (event.getCurrentItem().getType()) {
           case COAL: 
  858        event.setCancelled(true);
  859        if (getConfig().getInt(player.getName() + ".dia") > 0) {
  860          getConfig().set(player.getName() + ".dia", Integer.valueOf(getConfig().getInt(player.getName() + ".dia") - 1));
  861          getConfig().set(player.getName() + ".emerald", Integer.valueOf(getConfig().getInt(player.getName() + ".emerald") + 5));
  862          openGUI3(player);
  863          saveConfig();
             } else {
  865          player.sendMessage("Zo veel geld heb je niet!.");
             }
  867        break;
           case FURNACE: 
  869        event.setCancelled(true);
  870        if (getConfig().getInt(player.getName() + ".emerald") > 0) {
  871          getConfig().set(player.getName() + ".emerald", Integer.valueOf(getConfig().getInt(player.getName() + ".emerald") - 1));
  872          getConfig().set(player.getName() + ".iron", Integer.valueOf(getConfig().getInt(player.getName() + ".iron") + 2));
  873          openGUI3(player);
  874          saveConfig();
             } else {
  876          player.sendMessage("Zo veel geld heb je niet!.");
             }
  878        break;
           case CAKE_BLOCK: 
  880        event.setCancelled(true);
  881        if (getConfig().getInt(player.getName() + ".iron") > 0) {
  882          getConfig().set(player.getName() + ".iron", Integer.valueOf(getConfig().getInt(player.getName() + ".iron") - 1));
  883          getConfig().set(player.getName() + ".gblock", Integer.valueOf(getConfig().getInt(player.getName() + ".gblock") + 5));
  884          openGUI3(player);
  885          saveConfig();
             } else {
  887          player.sendMessage("Zo veel geld heb je niet!.");
             }
  889        break;
           case CAKE: 
  891        event.setCancelled(true);
  892        if (getConfig().getInt(player.getName() + ".gblock") > 0) {
  893          getConfig().set(player.getName() + ".gblock", Integer.valueOf(getConfig().getInt(player.getName() + ".gblock") - 1));
  894          getConfig().set(player.getName() + ".gold", Integer.valueOf(getConfig().getInt(player.getName() + ".gold") + 10));
  895          openGUI3(player);
  896          saveConfig();
             } else {
  898          player.sendMessage("Zo veel geld heb je niet!.");
             }
  900        break;
           case LEAVES_2: 
  902        event.setCancelled(true);
  903        if (getConfig().getInt(player.getName() + ".gold") > 0) {
  904          getConfig().set(player.getName() + ".gold", Integer.valueOf(getConfig().getInt(player.getName() + ".gold") - 1));
  905          getConfig().set(player.getName() + ".gnugget", Integer.valueOf(getConfig().getInt(player.getName() + ".gnugget") + 10));
  906          openGUI3(player);
  907          saveConfig();
             } else {
  909          player.sendMessage("Zo veel geld heb je niet!.");
             }
  911        break;
           case SLIME_BLOCK: 
  913        event.setCancelled(true);
  914        break;
           }
           
         }
         
         public void openGUI(Player p, String p2)
         {
  921      Inventory inv = Bukkit.createInventory(null, 54, ChatColor.AQUA + "Welkom! " + p2);
  922      ItemStack survival = new ItemStack(Material.DIAMOND_BLOCK, getConfig().getInt(p2 + ".dia"));
  923      ItemMeta survivalMeta = survival.getItemMeta();
           
       
  926      ItemStack creative = new ItemStack(Material.EMERALD_BLOCK, getConfig().getInt(p2 + ".emerald"));
  927      ItemMeta creativeMeta = creative.getItemMeta();
           
       
  930      ItemStack adventure = new ItemStack(Material.IRON_BLOCK, getConfig().getInt(p2 + ".iron"));
  931      ItemMeta adventureMeta = adventure.getItemMeta();
           
  933      ItemStack spectator = new ItemStack(Material.GOLD_BLOCK, getConfig().getInt(p2 + ".gblock"));
  934      ItemMeta spectatorMeta = spectator.getItemMeta();
           
  936      ItemStack gold = new ItemStack(Material.GOLD_INGOT, getConfig().getInt(p2 + ".gold"));
  937      ItemMeta goldMeta = gold.getItemMeta();
           
  939      ItemStack gNugget = new ItemStack(Material.GOLD_NUGGET, getConfig().getInt(p2 + ".gnugget"));
  940      ItemMeta gNuggetMeta = gNugget.getItemMeta();
           
       
       
  944      ItemStack dia = new ItemStack(Material.DIAMOND_BLOCK);
  945      ItemMeta diaMeta = dia.getItemMeta();
           
       
  948      ItemStack emer = new ItemStack(Material.EMERALD_BLOCK);
  949      ItemMeta emerMeta = emer.getItemMeta();
           
       
  952      ItemStack ironb = new ItemStack(Material.IRON_BLOCK);
  953      ItemMeta ironbMeta = ironb.getItemMeta();
           
  955      ItemStack gb = new ItemStack(Material.GOLD_BLOCK);
  956      ItemMeta gbMeta = gb.getItemMeta();
           
  958      ItemStack gi = new ItemStack(Material.GOLD_INGOT);
  959      ItemMeta giMeta = gi.getItemMeta();
           
  961      ItemStack gn = new ItemStack(Material.GOLD_NUGGET);
  962      ItemMeta gnMeta = gn.getItemMeta();
           
       
       
  966      ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE);
  967      ItemMeta glassMeta = glass.getItemMeta();
           
  969      ItemStack bar = new ItemStack(Material.BARRIER);
  970      ItemMeta barMeta = bar.getItemMeta();
           
  972      survivalMeta.setDisplayName("Diamond Block - " + ChatColor.AQUA + "€5000 euro waard.");
  973      survival.setItemMeta(survivalMeta);
           
       
  976      creativeMeta.setDisplayName("Emerald Block - " + ChatColor.AQUA + "€1000 euro waard.");
  977      creative.setItemMeta(creativeMeta);
           
  979      adventureMeta.setDisplayName("Iron Block - " + ChatColor.AQUA + "€500 euro waard.");
  980      adventure.setItemMeta(adventureMeta);
           
  982      spectatorMeta.setDisplayName("Gold Block - " + ChatColor.AQUA + "€100 euro waard.");
  983      spectator.setItemMeta(spectatorMeta);
           
  985      goldMeta.setDisplayName("Gold Ingot - " + ChatColor.AQUA + "€10 euro waard.");
  986      gold.setItemMeta(goldMeta);
           
  988      gNuggetMeta.setDisplayName("Gold Nugget - " + ChatColor.AQUA + "€1 euro waard.");
  989      gNugget.setItemMeta(gNuggetMeta);
           
       
       
       
  994      diaMeta.setDisplayName(ChatColor.GREEN + "Stort 5000 euro." + ChatColor.AQUA + " (Diamond block)");
  995      dia.setItemMeta(diaMeta);
           
  997      emerMeta.setDisplayName(ChatColor.GREEN + "Stort 1000 euro." + ChatColor.DARK_GREEN + " (Emerald block)");
  998      emer.setItemMeta(emerMeta);
           
 1000      ironbMeta.setDisplayName(ChatColor.GREEN + "Stort 500 euro." + ChatColor.GRAY + " (Iron block)");
 1001      ironb.setItemMeta(ironbMeta);
           
 1003      gbMeta.setDisplayName(ChatColor.GREEN + "Stort 100 euro." + ChatColor.GOLD + " (Gold block)");
 1004      gb.setItemMeta(gbMeta);
           
 1006      giMeta.setDisplayName(ChatColor.GREEN + "Stort 10 euro." + ChatColor.YELLOW + " (Gold ingot)");
 1007      gi.setItemMeta(giMeta);
           
 1009      gnMeta.setDisplayName(ChatColor.GREEN + "Stort 1 euro." + ChatColor.RED + " (Gold nugget)");
 1010      gn.setItemMeta(gnMeta);
           
       
 1013      glassMeta.setDisplayName(ChatColor.AQUA + "Storten");
 1014      glass.setItemMeta(glassMeta);
           
 1016      barMeta.setDisplayName(ChatColor.RED + "Verlaten");
 1017      bar.setItemMeta(barMeta);
           
 1019      inv.setItem(0, survival);
 1020      inv.setItem(1, creative);
 1021      inv.setItem(2, adventure);
 1022      inv.setItem(3, spectator);
 1023      inv.setItem(4, gold);
 1024      inv.setItem(5, gNugget);
 1025      inv.setItem(36, glass);
 1026      inv.setItem(37, glass);
 1027      inv.setItem(38, glass);
 1028      inv.setItem(39, glass);
 1029      inv.setItem(40, glass);
 1030      inv.setItem(41, glass);
 1031      inv.setItem(42, glass);
 1032      inv.setItem(43, glass);
 1033      inv.setItem(44, glass);
 1034      inv.setItem(45, bar);
 1035      inv.setItem(46, dia);
 1036      inv.setItem(47, emer);
 1037      inv.setItem(48, ironb);
 1038      inv.setItem(49, gb);
 1039      inv.setItem(50, gi);
 1040      inv.setItem(51, gn);
 1041      inv.setItem(52, bar);
 1042      inv.setItem(53, bar);
 1043      p.openInventory(inv);
         }
         
         @EventHandler
 1047    public void onInventoryClick(InventoryClickEvent event) { if (!ChatColor.stripColor(event.getInventory().getName()).contains("Welkom! ")) {
 1048        return;
           }
 1050      Player getting = (Player)event.getWhoClicked();
 1051      String player; String player; if (this.rek.contains(event.getWhoClicked())) { String player;
 1052        if (event.getInventory().getTitle().contains("accounts")) {
 1053          player = event.getInventory().getTitle().replace(ChatColor.AQUA + "Welkom! ", "");
             } else {
 1055          String l = event.getInventory().getTitle().replace(ChatColor.AQUA + "Welkom! ", "");
 1056          int ac = getConfig().getInt("accounts." + l);
 1057          player = "accounts.r" + ac;
             }
           }
           else
           {
 1062        player = event.getWhoClicked().getName();
           }
           
 1065      if (getConfig().getStringList("watcherslist").contains(player)) {
 1066        event.setCancelled(true);
           } else {
 1068        event.setCancelled(true);
 1069        if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType() == Material.AIR) || (!event.getCurrentItem().hasItemMeta()))
             {
 1071          return;
             }
 1073        switch (event.getCurrentItem().getType()) {
             case COAL: 
 1075          if (event.getSlot() == 46) {
 1076            event.setCancelled(true);
 1077            if (getting.getInventory().contains(Material.DIAMOND_BLOCK))
                 {
 1079              ItemStack m = new ItemStack(Material.DIAMOND_BLOCK, 1);
 1080              getting.getInventory().removeItem(new ItemStack[] { m });
 1081              getting.updateInventory();
 1082              getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") + 5000));
 1083              getConfig().set(player + ".dia", Integer.valueOf(getConfig().getInt(player + ".dia") + 1));
 1084              openGUI(getting, player);
 1085              ScoreBoardUpdate(getting);
                   
 1087              getting.sendMessage(ChatColor.GREEN + "Je hebt 5000 euro gestort!");
                 } else {
 1089              getting.sendMessage(ChatColor.RED + "Je hebt geen diamond blocks!");
                 }
                 
       
               }
 1094          else if (!event.isShiftClick()) {
 1095            event.setCancelled(true);
                 
 1097            if (getConfig().getInt(player + ".dia") > 0) {
 1098              getConfig().set(player + ".dia", Integer.valueOf(getConfig().getInt(player + ".dia") - 1));
 1099              getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") - 5000));
 1100              ScoreBoardUpdate(getting);
 1101              saveConfig();
 1102              getting.sendMessage(ChatColor.GREEN + "Je hebt 5000 euro opgenomen!");
                   
 1104              ItemStack m = new ItemStack(Material.DIAMOND_BLOCK, 1);
 1105              getting.getInventory().addItem(new ItemStack[] { m });
 1106              openGUI(getting, player);
       
                 }
                 else
                 {
       
 1112              getting.sendMessage(ChatColor.RED + "Zo veel geld heb je niet!!");
                 }
               }
               else {
 1116            event.setCancelled(true);
                 
 1118            if (getConfig().getInt(player + ".dia") > 0) {
 1119              ItemStack m = new ItemStack(Material.DIAMOND_BLOCK, getConfig().getInt(player + ".dia"));
 1120              getting.getInventory().addItem(new ItemStack[] { m });
 1121              getting.sendMessage(ChatColor.GREEN + "Je hebt " + getConfig().getInt(new StringBuilder(String.valueOf(player)).append(".dia").toString()) * 5000 + " euro opgenomen!");
 1122              getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") - getConfig().getInt(player + ".dia") * 5000));
 1123              getConfig().set(player + ".dia", Integer.valueOf(0));
 1124              ScoreBoardUpdate(getting);
 1125              saveConfig();
                   
       
       
 1129              openGUI(getting, player);
       
                 }
                 else
                 {
       
 1135              getting.sendMessage(ChatColor.RED + "Zo veel geld heb je niet!!"); } }
 1136          break;
             
       
       
       
             case FURNACE: 
 1142          if (event.getSlot() == 47) {
 1143            event.setCancelled(true);
 1144            if (getConfig().getInt(player + ".emerald") != 4) {
 1145              if (getting.getInventory().contains(Material.EMERALD_BLOCK)) {
 1146                ItemStack m = new ItemStack(Material.EMERALD_BLOCK, 1);
 1147                getting.getInventory().removeItem(new ItemStack[] { m });
 1148                getting.updateInventory();
 1149                getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") + 1000));
 1150                getConfig().set(player + ".emerald", Integer.valueOf(getConfig().getInt(player + ".emerald") + 1));
 1151                openGUI(getting, player);
 1152                ScoreBoardUpdate(getting);
 1153                getting.sendMessage(ChatColor.GREEN + "Je hebt 1000 euro gestort!");
                   } else {
 1155                getting.sendMessage(ChatColor.RED + "Je hebt geen emerald blocks!");
                   }
                   
       
                 }
 1160            else if (getting.getInventory().contains(Material.EMERALD_BLOCK))
                 {
 1162              ItemStack m = new ItemStack(Material.EMERALD_BLOCK, 1);
 1163              getting.getInventory().removeItem(new ItemStack[] { m });
 1164              getting.updateInventory();
 1165              getConfig().set(player + ".emerald", Integer.valueOf(0));
 1166              getConfig().set(player + ".dia", Integer.valueOf(getConfig().getInt(player + ".dia") + 1));
 1167              getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") + 1000));
 1168              openGUI(getting, player);
 1169              ScoreBoardUpdate(getting);
 1170              getting.sendMessage(ChatColor.GREEN + "Je hebt 1000 euro gestort!");
                 }
                 else {
 1173              getting.sendMessage(ChatColor.RED + " Je hebt geen emerald blocks!");
       
                 }
                 
       
               }
 1179          else if (!event.isShiftClick()) {
 1180            event.setCancelled(true);
                 
 1182            if (getConfig().getInt(player + ".emerald") > 0) {
 1183              getConfig().set(player + ".emerald", Integer.valueOf(getConfig().getInt(player + ".emerald") - 1));
 1184              getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") - 1000));
 1185              ScoreBoardUpdate(getting);
 1186              saveConfig();
 1187              getting.sendMessage(ChatColor.GREEN + "Je hebt 1000 euro opgenomen!");
 1188              ItemStack m = new ItemStack(Material.EMERALD_BLOCK, 1);
 1189              getting.getInventory().addItem(new ItemStack[] { m });
 1190              openGUI(getting, player);
                 }
                 else
                 {
 1194              getting.sendMessage(ChatColor.RED + "Zo veel geld heb je niet!!");
                 }
               }
               else {
 1198            event.setCancelled(true);
                 
 1200            if (getConfig().getInt(player + ".emerald") > 0) {
 1201              ItemStack m = new ItemStack(Material.EMERALD_BLOCK, getConfig().getInt(player + ".emerald"));
 1202              getting.getInventory().addItem(new ItemStack[] { m });
 1203              getting.sendMessage(ChatColor.GREEN + "Je hebt " + getConfig().getInt(new StringBuilder(String.valueOf(player)).append(".emerald").toString()) * 1000 + " euro opgenomen!");
 1204              getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") - getConfig().getInt(player + ".emerald") * 1000));
 1205              getConfig().set(player + ".emerald", Integer.valueOf(0));
                   
 1207              ScoreBoardUpdate(getting);
 1208              saveConfig();
                   
       
 1211              openGUI(getting, player);
                 }
                 else
                 {
 1215              getting.sendMessage(ChatColor.RED + "Zo veel geld heb je niet!!"); } }
 1216          break;
             
       
       
       
             case CAKE_BLOCK: 
 1222          if (event.getSlot() == 48) {
 1223            event.setCancelled(true);
 1224            if (getConfig().getInt(player + ".iron") != 1) {
 1225              if (getting.getInventory().contains(Material.IRON_BLOCK))
                   {
 1227                ItemStack m = new ItemStack(Material.IRON_BLOCK, 1);
 1228                getting.getInventory().removeItem(new ItemStack[] { m });
 1229                getting.updateInventory();
 1230                getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") + 500));
 1231                getConfig().set(player + ".iron", Integer.valueOf(getConfig().getInt(player + ".iron") + 1));
 1232                openGUI(getting, player);
 1233                ScoreBoardUpdate(getting);
 1234                getting.sendMessage(ChatColor.GREEN + "Je hebt 500 euro gestort!");
                   } else {
 1236                getting.sendMessage(ChatColor.RED + " Je hebt geen iron blocks!");
                   }
                   
       
                 }
 1241            else if (getting.getInventory().contains(Material.IRON_BLOCK))
                 {
 1243              ItemStack m = new ItemStack(Material.IRON_BLOCK, 1);
 1244              getting.getInventory().removeItem(new ItemStack[] { m });
 1245              getting.updateInventory();
 1246              getConfig().set(player + ".iron", Integer.valueOf(0));
                   
 1248              if (getConfig().getInt(player + ".emerald") != 4) {
 1249                getConfig().set(player + ".emerald", Integer.valueOf(getConfig().getInt(player + ".emerald") + 1));
 1250                getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") + 500));
 1251                openGUI(getting, player);
 1252                ScoreBoardUpdate(getting);
 1253                getting.sendMessage(ChatColor.GREEN + "Je hebt 500 euro gestort!");
                   }
                   else {
 1256                getConfig().set(player + ".dia", Integer.valueOf(getConfig().getInt(player + ".dia") + 1));
 1257                getConfig().set(player + ".emerald", Integer.valueOf(0));
 1258                getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") + 500));
 1259                openGUI(getting, player);
 1260                ScoreBoardUpdate(getting);
 1261                getting.sendMessage(ChatColor.GREEN + "Je hebt 500 euro gestort!");
                   }
                 }
                 else {
 1265              getting.sendMessage(ChatColor.RED + "Je hebt geen iron blocks!");
       
                 }
                 
       
               }
 1271          else if (!event.isShiftClick()) {
 1272            event.setCancelled(true);
                 
 1274            if (getConfig().getInt(player + ".iron") > 0) {
 1275              getConfig().set(player + ".iron", Integer.valueOf(getConfig().getInt(player + ".iron") - 1));
 1276              getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") - 500));
 1277              ScoreBoardUpdate(getting);
 1278              saveConfig();
 1279              getting.sendMessage(ChatColor.GRAY + "Je hebt 500 euro opgenomen!");
 1280              ScoreBoardUpdate(getting);
 1281              ItemStack m = new ItemStack(Material.IRON_BLOCK, 1);
 1282              getting.getInventory().addItem(new ItemStack[] { m });
 1283              openGUI(getting, player);
                 }
                 else
                 {
 1287              getting.sendMessage(ChatColor.RED + "Zo veel geld heb je niet!!");
                 }
               }
               else {
 1291            event.setCancelled(true);
                 
 1293            if (getConfig().getInt(player + ".iron") > 0) {
 1294              getting.sendMessage(ChatColor.GRAY + "Je hebt " + getConfig().getInt(new StringBuilder(String.valueOf(player)).append(".iron").toString()) * 500 + " euro opgenomen!");
 1295              ItemStack m = new ItemStack(Material.IRON_BLOCK, getConfig().getInt(player + ".iron"));
 1296              getting.getInventory().addItem(new ItemStack[] { m });
 1297              getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") - getConfig().getInt(player + ".iron") * 500));
 1298              getConfig().set(player + ".iron", Integer.valueOf(0));
                   
 1300              ScoreBoardUpdate(getting);
 1301              saveConfig();
                   
 1303              ScoreBoardUpdate(getting);
                   
 1305              openGUI(getting, player);
                 }
                 else
                 {
 1309              getting.sendMessage(ChatColor.RED + "Zo veel geld heb je niet!!"); } }
 1310          break;
             
       
       
             case CAKE: 
 1315          if (event.getSlot() == 49) {
 1316            event.setCancelled(true);
 1317            if (getConfig().getInt(player + ".gblock") != 4) {
 1318              if (getting.getInventory().contains(Material.GOLD_BLOCK))
                   {
 1320                ItemStack m = new ItemStack(Material.GOLD_BLOCK, 1);
 1321                getting.getInventory().removeItem(new ItemStack[] { m });
 1322                getting.updateInventory();
 1323                getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") + 100));
 1324                getConfig().set(player + ".gblock", Integer.valueOf(getConfig().getInt(player + ".gblock") + 1));
 1325                openGUI(getting, player);
 1326                ScoreBoardUpdate(getting);
 1327                getting.sendMessage(ChatColor.GREEN + "Je hebt 100 euro gestort!");
                   } else {
 1329                getting.sendMessage(ChatColor.RED + " Je hebt geen gold blocks!");
                   }
                   
       
                 }
 1334            else if (getting.getInventory().contains(Material.GOLD_BLOCK))
                 {
 1336              ItemStack m = new ItemStack(Material.GOLD_BLOCK, 1);
 1337              getting.getInventory().removeItem(new ItemStack[] { m });
 1338              getting.updateInventory();
 1339              getConfig().set(player + ".gblock", Integer.valueOf(0));
                   
 1341              if (getConfig().getInt(player + ".iron") != 1) {
 1342                getConfig().set(player + ".iron", Integer.valueOf(getConfig().getInt(player + ".iron") + 1));
 1343                getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") + 100));
 1344                openGUI(getting, player);
 1345                ScoreBoardUpdate(getting);
 1346                getting.sendMessage(ChatColor.GREEN + "Je hebt 100 euro gestort!");
                   }
                   else {
 1349                getConfig().set(player + ".emerald", Integer.valueOf(getConfig().getInt(player + ".emerald") + 1));
 1350                getConfig().set(player + ".iron", Integer.valueOf(0));
 1351                getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") + 100));
 1352                openGUI(getting, player);
 1353                ScoreBoardUpdate(getting);
 1354                getting.sendMessage(ChatColor.GREEN + "Je hebt 100 euro gestort!");
                   }
                 }
                 else {
 1358              getting.sendMessage(ChatColor.RED + " Je hebt geen gold blocks!");
                 }
                 
               }
               else
               {
 1364            event.setCancelled(true);
 1365            if (!event.isShiftClick()) {
 1366              if (getConfig().getInt(player + ".gblock") > 0)
                   {
 1368                getConfig().set(player + ".gblock", Integer.valueOf(getConfig().getInt(player + ".gblock") - 1));
 1369                getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") - 100));
 1370                ScoreBoardUpdate(getting);
 1371                saveConfig();
 1372                getting.sendMessage(ChatColor.GREEN + "Je hebt 100 euro opgenomen!");
                     
 1374                getting.getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLD_BLOCK, 1) });
 1375                openGUI(getting, player);
                   }
                   else
                   {
 1379                getting.sendMessage(ChatColor.RED + "Zo veel geld heb je niet!!");
                   }
                   
                 }
 1383            else if (getConfig().getInt(player + ".gblock") > 0) {
 1384              getting.sendMessage(ChatColor.GREEN + "Je hebt " + getConfig().getInt(new StringBuilder(String.valueOf(player)).append(".gblock").toString()) * 100 + " euro opgenomen!");
 1385              getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") - getConfig().getInt(player + ".gblock") * 100));
 1386              getting.getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLD_BLOCK, getConfig().getInt(player + ".gblock")) });
 1387              getConfig().set(player + ".gblock", Integer.valueOf(0));
                   
 1389              ScoreBoardUpdate(getting);
 1390              saveConfig();
                   
       
       
 1394              openGUI(getting, player);
                 }
                 else
                 {
 1398              getting.sendMessage(ChatColor.RED + "Zo veel geld heb je niet!!"); } }
 1399          break;
             
       
       
             case LEAVES_2: 
 1404          if (event.getSlot() == 50) {
 1405            event.setCancelled(true);
 1406            if (getConfig().getInt(player + ".gold") != 9) {
 1407              if (getting.getInventory().contains(Material.GOLD_INGOT))
                   {
 1409                ItemStack m = new ItemStack(Material.GOLD_INGOT, 1);
 1410                getting.getInventory().removeItem(new ItemStack[] { m });
 1411                getting.updateInventory();
 1412                getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") + 10));
 1413                getConfig().set(player + ".gold", Integer.valueOf(getConfig().getInt(player + ".gold") + 1));
 1414                openGUI(getting, player);
 1415                ScoreBoardUpdate(getting);
 1416                getting.sendMessage(ChatColor.GREEN + "Je hebt 10 euro gestort!");
                   } else {
 1418                getting.sendMessage(ChatColor.RED + " Je hebt geen gold ingots!");
                   }
                   
       
                 }
 1423            else if (getting.getInventory().contains(Material.GOLD_INGOT))
                 {
 1425              ItemStack m = new ItemStack(Material.GOLD_INGOT, 1);
 1426              getting.getInventory().removeItem(new ItemStack[] { m });
 1427              getting.updateInventory();
 1428              getConfig().set(player + ".gold", Integer.valueOf(0));
                   
 1430              if (getConfig().getInt(player + ".gblock") != 9) {
 1431                getConfig().set(player + ".gblock", Integer.valueOf(getConfig().getInt(player + ".gblock") + 1));
 1432                getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") + 10));
 1433                openGUI(getting, player);
 1434                ScoreBoardUpdate(getting);
 1435                getting.sendMessage(ChatColor.GREEN + "Je hebt 10 euro gestort!");
                   }
                   else {
 1438                getConfig().set(player + ".iron", Integer.valueOf(getConfig().getInt(player + ".iron") + 1));
 1439                getConfig().set(player + ".gblock", Integer.valueOf(0));
 1440                getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") + 10));
 1441                openGUI(getting, player);
 1442                ScoreBoardUpdate(getting);
 1443                getting.sendMessage(ChatColor.GREEN + "Je hebt 10 euro gestort!");
                   }
                 }
                 else {
 1447              getting.sendMessage(ChatColor.RED + "Je hebt geen gold ingots!");
                 }
                 
               }
               else
               {
 1453            event.setCancelled(true);
 1454            if (!event.isShiftClick()) {
 1455              if (getConfig().getInt(player + ".gold") > 0) {
 1456                getConfig().set(player + ".gold", Integer.valueOf(getConfig().getInt(player + ".gold") - 1));
 1457                getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(getting.getName() + ".money") - 10));
 1458                ScoreBoardUpdate(getting);
 1459                saveConfig();
 1460                getting.sendMessage(ChatColor.GREEN + "Je hebt 10 euro opgenomen!");
                     
 1462                getting.getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 1) });
 1463                openGUI(getting, player);
                   }
                   else
                   {
 1467                getting.sendMessage(ChatColor.RED + "Zo veel geld heb je niet!!");
                   }
                   
                 }
 1471            else if (getConfig().getInt(player + ".gold") > 0) {
 1472              getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") - getConfig().getInt(player + ".gold") * 10));
 1473              getting.getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, getConfig().getInt(player + ".gold")) });
 1474              getConfig().set(player + ".gold", Integer.valueOf(0));
                   
 1476              ScoreBoardUpdate(getting);
 1477              saveConfig();
 1478              getting.sendMessage(ChatColor.GREEN + "Je hebt" + getConfig().getInt(new StringBuilder(String.valueOf(player)).append(".gold").toString()) * 10 + " euro opgenomen!");
                   
       
 1481              openGUI(getting, player);
                 }
                 else
                 {
 1485              getting.sendMessage(ChatColor.RED + "Zo veel geld heb je niet!!"); } }
 1486          break;
             
       
       
             case SLIME_BLOCK: 
 1491          if (event.getSlot() == 51) {
 1492            event.setCancelled(true);
 1493            if (getConfig().getInt(player + ".gnugget") != 9) {
 1494              if (getting.getInventory().contains(Material.GOLD_NUGGET))
                   {
 1496                ItemStack m = new ItemStack(Material.GOLD_NUGGET, 1);
 1497                getting.getInventory().removeItem(new ItemStack[] { m });
 1498                getting.updateInventory();
 1499                getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") + 1));
 1500                getConfig().set(player + ".gnugget", Integer.valueOf(getConfig().getInt(player + ".gnugget") + 1));
 1501                openGUI(getting, player);
 1502                ScoreBoardUpdate(getting);
 1503                getting.sendMessage(ChatColor.GREEN + "Je hebt 1 euro gestort");
                   } else {
 1505                getting.sendMessage(ChatColor.RED + " Je hebt geen gold nuggets!");
                   }
                   
       
                 }
 1510            else if (getting.getInventory().contains(Material.GOLD_NUGGET))
                 {
 1512              ItemStack m = new ItemStack(Material.GOLD_NUGGET, 1);
 1513              getting.getInventory().removeItem(new ItemStack[] { m });
 1514              getting.updateInventory();
 1515              getConfig().set(player + ".gnugget", Integer.valueOf(0));
 1516              if (getConfig().getInt(player + ".gold") != 9) {
 1517                getConfig().set(player + ".gold", Integer.valueOf(getConfig().getInt(player + ".gold") + 1));
 1518                getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") + 1));
 1519                openGUI(getting, player);
 1520                ScoreBoardUpdate(getting);
 1521                getting.sendMessage(ChatColor.GREEN + "Je hebt 1 euro gestort!");
                   }
                   else {
 1524                getConfig().set(player + ".gblock", Integer.valueOf(getConfig().getInt(player + ".gblock") + 1));
 1525                getConfig().set(player + ".gold", Integer.valueOf(0));
 1526                getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") + 1));
 1527                openGUI(getting, player);
 1528                ScoreBoardUpdate(getting);
 1529                getting.sendMessage(ChatColor.GREEN + "Je hebt 1 euro gestort!");
                   }
                 }
                 else
                 {
 1534              getting.sendMessage(ChatColor.RED + "Je hebt geen golden nuggets!");
                 }
                 
               }
               else
               {
 1540            event.setCancelled(true);
 1541            if (!event.isShiftClick()) {
 1542              if (getConfig().getInt(player + ".gnugget") > 0) {
 1543                getConfig().set(player + ".gnugget", Integer.valueOf(getConfig().getInt(player + ".gnugget") - 1));
 1544                getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") - 1));
 1545                ScoreBoardUpdate(getting);
 1546                saveConfig();
 1547                getting.sendMessage(ChatColor.GREEN + "Je hebt 1 euro opgenomen!!");
                     
 1549                getting.getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLD_NUGGET, 1) });
 1550                openGUI(getting, player);
                   }
                   else
                   {
 1554                getting.sendMessage(ChatColor.RED + "Zo veel geld heb je niet!!");
                   }
                   
                 }
 1558            else if (getConfig().getInt(player + ".gnugget") > 0) {
 1559              getting.getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLD_NUGGET, getConfig().getInt(player + ".gnugget")) });
                   
 1561              getConfig().set(player + ".money", Integer.valueOf(getConfig().getInt(player + ".money") - getConfig().getInt(player + ".gnugget")));
 1562              getConfig().set(player + ".gnugget", Integer.valueOf(0));
                   
 1564              ScoreBoardUpdate(getting);
 1565              saveConfig();
 1566              getting.sendMessage(ChatColor.GREEN + "Je hebt " + getConfig().getInt(new StringBuilder(String.valueOf(player)).append(".gnugget").toString()) + " euro opgenomen!!");
                   
 1568              openGUI(getting, player);
                 }
                 else
                 {
 1572              getting.sendMessage(ChatColor.RED + "Zo veel geld heb je niet!!"); } }
 1573          break;
             
       
       
             case HUGE_MUSHROOM_1: 
 1578          getting.closeInventory();
 1579          getting.sendMessage(ChatColor.GREEN + "Tot ziens!!");
 1580          break;
             default: 
 1582          getting.closeInventory();
             }
             
 1585        saveConfig();
           }
         }
         
         @EventHandler
 1590    public void invClose(InventoryCloseEvent e) { if (getConfig().getStringList("watcherslist").contains(e.getPlayer().getName())) {
 1591        List<String> stringList = getConfig().getStringList("watcherslist");
 1592        stringList.remove(e.getPlayer().getName());
 1593        getConfig().set("watcherslist", stringList);
 1594        saveConfig();
           }
         }
         
         @EventHandler
         public void changeWorld(PlayerChangedWorldEvent e)
         {
 1601      if (e.getFrom().getName().equalsIgnoreCase(getConfig().getString("WhitelistWorld"))) {
 1602        Player p = e.getPlayer();
 1603        p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
           }
         }
         
         public void ScoreBoardUpdate(Player p)
         {
 1609      if (((getConfig().getString("worldWhitelist").equalsIgnoreCase("yes")) && (p.getLocation().getWorld().getName().equalsIgnoreCase(getConfig().getString("WhitelistWorld")))) || (getConfig().getString("worldWhitelist").equalsIgnoreCase("no")))
           {
 1611        if ((getConfig().getInt(p.getName() + ".money") >= 50000) && (getConfig().getInt(p.getName() + ".rank") == 1)) {
 1612          getConfig().set(p.getName() + ".rank", Integer.valueOf(2));
 1613          p.sendMessage(ChatColor.GREEN + "§lGefeliciteerd! Je bent level up gegaan! Je bent nu level 2!");
 1614        } else if ((getConfig().getInt(p.getName() + ".money") >= 100000) && (getConfig().getInt(p.getName() + ".rank") == 2)) {
 1615          getConfig().set(p.getName() + ".rank", Integer.valueOf(3));
 1616          p.sendMessage(ChatColor.GREEN + "§lGefeliciteerd! Je bent level up gegaan! Je bent nu level 3!");
 1617        } else if ((getConfig().getInt(p.getName() + ".money") >= 200000) && (getConfig().getInt(p.getName() + ".rank") == 3)) {
 1618          getConfig().set(p.getName() + ".rank", Integer.valueOf(4));
 1619          p.sendMessage(ChatColor.GREEN + "§lGefeliciteerd! Je bent level up gegaan! Je bent nu level 4!");
 1620        } else if ((getConfig().getInt(p.getName() + ".money") >= 600000) && (getConfig().getInt(p.getName() + ".rank") == 4)) {
 1621          getConfig().set(p.getName() + ".rank", Integer.valueOf(5));
 1622          p.sendMessage(ChatColor.GREEN + "§lGefeliciteerd! Je bent level up gegaan! Je bent nu level 5!");
 1623        } else if ((getConfig().getInt(p.getName() + ".money") >= 800000) && (getConfig().getInt(p.getName() + ".rank") == 5)) {
 1624          getConfig().set(p.getName() + ".rank", Integer.valueOf(6));
 1625          p.sendMessage(ChatColor.GREEN + "§lGefeliciteerd! Je bent level up gegaan! Je bent nu level 6!");
 1626        } else if ((getConfig().getInt(p.getName() + ".money") >= 1000000) && (getConfig().getInt(p.getName() + ".rank") == 6)) {
 1627          getConfig().set(p.getName() + ".rank", Integer.valueOf(7));
 1628          p.sendMessage(ChatColor.GREEN + "§lGefeliciteerd! Je bent level up gegaan! Je bent nu level 7!");
             }
             
 1631        ScoreboardManager manager = Bukkit.getScoreboardManager();
             
 1633        org.bukkit.scoreboard.Scoreboard scoreboard = manager.getNewScoreboard();
             
 1635        Objective obj = scoreboard.registerNewObjective("Board", "dummy");
 1636        obj.setDisplaySlot(org.bukkit.scoreboard.DisplaySlot.SIDEBAR);
 1637        String title = getConfig().getString("Scoreboard.title").replaceAll("(&([a-f0-9]))", "§$2");
 1638        String title2 = title.replaceAll("&", "§");
 1639        String titl = title2.replaceAll("§§", "&");
 1640        obj.setDisplayName(titl);
 1641        p.setScoreboard(scoreboard);
             
 1643        String money = getConfig().getString("Scoreboard.money").replaceAll("(&([a-f0-9]))", "§$2");
 1644        String money2 = money.replaceAll("&", "§");
 1645        String mon = money2.replaceAll("§§", "&");
 1646        Score Money = obj.getScore(ChatColor.DARK_AQUA + mon + ": ");
 1647        Money.setScore(12);
 1648        Score Money2 = obj.getScore(" €" + getConfig().getString(new StringBuilder(String.valueOf(p.getName())).append(".money").toString()));
 1649        Money2.setScore(11);
             
       
 1652        String fit = getConfig().getString("Scoreboard.fitheid").replaceAll("(&([a-f0-9]))", "§$2");
 1653        String fit2 = fit.replaceAll("&", "§");
 1654        String fith = fit2.replaceAll("§§", "&");
 1655        Score fitheid = obj.getScore(ChatColor.DARK_AQUA + fith + ": ");
 1656        fitheid.setScore(10);
 1657        Score fitheid2 = obj.getScore(" " + getConfig().getString(new StringBuilder(String.valueOf(p.getName())).append(".fitheid").toString()) + "/100");
 1658        fitheid2.setScore(9);
             
 1660        Score pl = obj.getScore(ChatColor.BOLD.toString() + ChatColor.RED);
 1661        pl.setScore(8);
 1662        Score pl2 = obj.getScore(ChatColor.BOLD.toString() + ChatColor.RED + "  ");
 1663        pl2.setScore(5);
 1664        Score pl23 = obj.getScore(ChatColor.BOLD.toString() + ChatColor.BLUE + "   ");
 1665        pl23.setScore(2);
 1666        Score pl234 = obj.getScore(ChatColor.BOLD.toString() + ChatColor.YELLOW);
 1667        pl234.setScore(11);
             
 1669        String online = getConfig().getString("Scoreboard.online").replaceAll("(&([a-f0-9]))", "§$2");
 1670        String online2 = online.replaceAll("&", "§");
 1671        String onl = online2.replaceAll("§§", "&");
 1672        Score o3 = obj.getScore(ChatColor.DARK_AQUA + onl + ": ");
 1673        o3.setScore(7);
             
 1675        Score o7 = obj.getScore(" " + Bukkit.getOnlinePlayers().size());
 1676        o7.setScore(6);
             
 1678        String lvl = getConfig().getString("Scoreboard.level").replaceAll("(&([a-f0-9]))", "§$2");
 1679        String lvl2 = lvl.replaceAll("&", "§");
 1680        String lv = lvl2.replaceAll("§§", "&");
 1681        Score o5 = obj.getScore(ChatColor.DARK_AQUA + lv + ": ");
 1682        o5.setScore(4);
             
 1684        Score o634 = obj.getScore(" Level " + getConfig().getInt(new StringBuilder(String.valueOf(p.getName())).append(".rank").toString()));
 1685        o634.setScore(3);
             
 1687        String tijd = getConfig().getString("Scoreboard.time").replaceAll("(&([a-f0-9]))", "§$2");
 1688        String tijd2 = tijd.replaceAll("&", "§");
 1689        String time = tijd2.replaceAll("§§", "&");
 1690        Score o100 = obj.getScore(ChatColor.DARK_AQUA + time + ": ");
 1691        o100.setScore(1);
             
 1693        Date date = new Date();
 1694        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("HH:mm");
 1695        date.setHours(date.getHours() + getConfig().getInt("Time"));
 1696        Score o6 = obj.getScore(" " + format.format(date));
 1697        o6.setScore(0);
             
 1699        p.setScoreboard(scoreboard);
 1700        saveConfig();
           }
         }
         
       
       
       
       
         public void CheckFitheid(Player p)
         {
 1710      if (getConfig().getInt(p.getName() + ".fitheid") >= 65) {
 1711        for (PotionEffect effect : p.getActivePotionEffects()) {
 1712          p.removePotionEffect(effect.getType());
             }
 1714        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 0));
 1715      } else if (getConfig().getInt(p.getName() + ".fitheid") >= 80) {
 1716        for (PotionEffect effect : p.getActivePotionEffects()) {
 1717          p.removePotionEffect(effect.getType());
             }
 1719        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 1));
 1720      } else if (getConfig().getInt(p.getName() + ".fitheid") >= 100) {
 1721        for (PotionEffect effect : p.getActivePotionEffects()) {
 1722          p.removePotionEffect(effect.getType());
             }
 1724        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 2));
 1725      } else if (getConfig().getInt(p.getName() + ".fitheid") < 50) {
 1726        for (PotionEffect effect : p.getActivePotionEffects()) {
 1727          p.removePotionEffect(effect.getType());
             }
 1729        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999999, 0));
 1730      } else if (getConfig().getInt(p.getName() + ".fitheid") < 40) {
 1731        for (PotionEffect effect : p.getActivePotionEffects()) {
 1732          p.removePotionEffect(effect.getType());
             }
 1734        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999999, 1));
 1735      } else if (getConfig().getInt(p.getName() + ".fitheid") < 20) {
 1736        for (PotionEffect effect : p.getActivePotionEffects()) {
 1737          p.removePotionEffect(effect.getType());
             }
 1739        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999999, 2));
           }
 1741      else if (getConfig().getInt(p.getName() + ".fitheid") < 5) {
 1742        for (PotionEffect effect : p.getActivePotionEffects()) {
 1743          p.removePotionEffect(effect.getType());
             }
 1745        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999999, 3));
           }
 1747      else if (getConfig().getInt(p.getName() + ".fitheid") == 0) {
 1748        for (PotionEffect effect : p.getActivePotionEffects()) {
 1749          p.removePotionEffect(effect.getType());
             }
 1751        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999999, 5));
           }
         }
       }


 Location:              C:\Users\Michael\Downloads\MinetopiaATM.jar!\karsten\ATM\main\Main.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 