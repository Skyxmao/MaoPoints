package me.xmao.MaoPoints;

import java.awt.Color;
import java.util.List;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.NukkitRunnable;
import cn.nukkit.utils.Config;
import me.xmao.MaoPoints.Core.PlayerPoint;
import me.xmao.MaoPoints.Listener.WindowListener;
import me.xmao.MaoPoints.SQL.PlayerModel;
import me.xmao.MaoPoints.View.MainView;

public class MaoPoints extends PluginBase{
	public static Config config;
	public static PlayerModel point_sql;
	@Override
    public void onLoad() {
		getLogger().info("MaoPoints Load! Author QQ: 124520547");
    }
    @Override
    public void onEnable() {
		this.saveDefaultConfig();
		this.config = this.getConfig();
		this.getServer().getPluginManager().registerEvents((Listener)new WindowListener(), (Plugin)this);
		if(this.config.getString("Storage.type").equalsIgnoreCase("sqlite")) {
			this.point_sql = new PlayerModel("plugins\\MaoPoints\\database.db");
		}else if(this.config.getString("Storage.type").equalsIgnoreCase("mysql")) {
			//等待填坑
			String user = this.config.getString("Storage.user");
			String pass = this.config.getString("Storage.pass");
			String host = this.config.getString("Storage.host");
			String db = this.config.getString("Storage.db");
			this.point_sql = new PlayerModel(host,user,pass,db);
			this.point_sql.isShow = true;
	        (new NukkitRunnable() {
	            @Override
	            public void run() {
	            	MaoPoints.point_sql.getValue("sss");
	           }
	      }).runTaskTimerAsynchronously(this, 60 * 20, 60 * 20);
		}
		System.out.println("\r\n    __  ___            ____        _       __      \r\n" + 
				"   /  |/  /___ _____  / __ \\____  (_)___  / /______\r\n" + 
				"  / /|_/ / __ `/ __ \\/ /_/ / __ \\/ / __ \\/ __/ ___/\r\n" + 
				" / /  / / /_/ / /_/ / ____/ /_/ / / / / / /_(__  ) \r\n" + 
				"/_/  /_/\\__,_/\\____/_/    \\____/_/_/ /_/\\__/____/  \r\n" + 
				"                                                   ");
    	getLogger().info("MaoPoints Enable! Author QQ: 124520547");
    }
    @Override
    public void onDisable() {
    	getLogger().info("MaoPoints Disable! Author QQ: 124520547");
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	   if (command.getName().equalsIgnoreCase("points")) {
               if (args.length == 0) {
            	   if(! (sender instanceof Player)) {
            		   return true;
            	   }
              	   MainView.showMainView((Player) sender);
                   return true;
               }
    		   if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
    			   sender.sendMessage("§a====== §f[§aMaoPoints§f]§a ======"
    			   		+ "\n/points me => 查询剩余点券"
    			   		+ "\n/points pay <id> <amount> => 向玩家转账");
    			   if(sender.isOp()) {
    				   sender.sendMessage("\n/points give <id> <amount> => 给玩家点券"
    	    			   		+ "\n/points take <id> <amount> => 拿走玩家点券"
    	    			   		+ "\n/points set <id> <amount> => 设置玩家点券"
    	    			   		+ "\n/points look <id> => 查看玩家点券");
    			   }
                   return true;
              }
    		  if (args.length == 1 && args[0].equalsIgnoreCase("me")) {
    			   PlayerPoint mp = new PlayerPoint(sender.getName());
    			   sender.sendMessage("§f[§aMaoPoints§f]§a剩余点券:" + mp.get());
                   return true;
              }
    		  if (args.length == 3 && args[0].equalsIgnoreCase("pay")) {
    			  PlayerPoint mp1 = new PlayerPoint(sender.getName());
    			  PlayerPoint mp2 = new PlayerPoint(args[1]);
            	  int amount  = Integer.parseInt(args[2]);
            	  if(amount <= 0) {
            		  sender.sendMessage("§f[§aMaoPoints§f]§c转账点券必须大于等于1!");
            		  return true;
            	  }
            	  if(!mp1.has(amount)) {
            		  sender.sendMessage("§f[§aMaoPoints§f]§c你没有这么多点券!");
            		  return true;
            	  }
            	  if(! (mp1.take(amount) && mp2.give(amount))) {
            		  sender.sendMessage("§f[§aMaoPoints§f]§c失败!");
            		  return true;
            	  }
            	  sender.sendMessage("§f[§aMaoPoints§f]§a成功转账给玩家" + args[1] + " " + amount  + "点券!");
                  return true;
             }
    		  /*  OP指令   */
    		  if (args.length == 1 && args[0].equalsIgnoreCase("reload") && sender.hasPermission("points.admin")) {
              		this.reloadConfig();
              		config = this.getConfig();
              		sender.sendMessage("§f[§aMaoPoints§f]§a重载成功!");
                    return true;
              }
    		  if (args.length == 3 && args[0].equalsIgnoreCase("give") && sender.hasPermission("points.admin")) {
            	  PlayerPoint mp = new PlayerPoint(args[1]);
            	  int amount  = Integer.parseInt(args[2]);
 	           	 if(amount < 1) {
 	        		  sender.sendMessage("§f[§aMaoPoints§f]§c点券必须大于等于1!");
 	        		  return true;
 	        	 }
    			  if(mp.give(amount)) {
    				  sender.sendMessage("§f[§aMaoPoints§f]§a玩家" + args[1] + "成功增加" + amount  + "点券!");
    				  return true;
            	  }
				  sender.sendMessage("§f[§aMaoPoints§f]§c失败!");
                  return true;
             }
    		 if (args.length == 2 && args[0].equalsIgnoreCase("look") && sender.hasPermission("points.admin")) {
            	  PlayerPoint mp = new PlayerPoint(args[1]);
    			  sender.sendMessage("§f[§aMaoPoints§f]§a玩家" + args[1] + "拥有" +  mp.get() +"点券");
                  return true;
             }
    		 if (args.length == 3 && args[0].equalsIgnoreCase("set") && sender.hasPermission("points.admin")) {
    			 PlayerPoint mp = new PlayerPoint(args[1]);
    			 int amount  = Integer.parseInt(args[2]);
	           	 if(amount < 0) {
	        		  sender.sendMessage("§f[§aMaoPoints§f]§c点券必须大于等于0!");
	        		  return true;
	        	 }
           	  	 if(!mp.set(amount)) {
           	  		 sender.sendMessage("§f[§aMaoPoints§f]§c失败!");
           	  		 return true;
           	  	 }
   			  	 sender.sendMessage("§f[§aMaoPoints§f]§a玩家" + args[1] + "拥有" +  mp.get() +"点券");
                 return true;
            }
    		 if (args.length == 3 && args[0].equalsIgnoreCase("take") && sender.hasPermission("points.admin")) {
           	  	 PlayerPoint mp = new PlayerPoint(args[1]);
           	  	 int amount  = Integer.parseInt(args[2]);
	           	 if(amount < 1) {
	        		  sender.sendMessage("§f[§aMaoPoints§f]§c点券必须大于等于1!");
	        		  return true;
	        	 }
           	  	 if(!mp.has(amount)) {
           	  		 sender.sendMessage("§f[§aMaoPoints§f]§c没有这么多点券");
           	  		 return true;
           	  	 }
           	  	 if(!mp.take(amount)) {
	           	  	 sender.sendMessage("§f[§aMaoPoints§f]§c失败!");
	      	  		 return true;
           	  	 }
           	  	 sender.sendMessage("§f[§aMaoPoints§f]§a玩家目前" + args[1] + "拥有" +  mp.get() +"点券");
                 return true;
             }
             sender.sendMessage("§f[§aMaoPoints§f]§c指令也许不存在哦!请输入/p help!");
    	   }
    	   return true;
    }
    
}