package me.xmao.MaoPoints.Listener;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import me.xmao.MaoPoints.View.MainView;
import org.json.*;

public class WindowListener implements Listener{
	 @EventHandler
	 public void onClickWindow(PlayerFormRespondedEvent event) {
		 JSONObject data = new JSONObject(event.getWindow().getJSONData());
		 if(data.get("title").toString().equalsIgnoreCase(MainView.MainViewTitle)) {
			 JSONObject responses = data.getJSONObject("response");
			 String buttonName = responses.getJSONObject("clickedButton").get("text").toString();
			 if(buttonName.equalsIgnoreCase("查询")) {
				 event.getPlayer().getServer().dispatchCommand(event.getPlayer(),"points me");
			 }else if(buttonName.equalsIgnoreCase("转账")) {
				 MainView.showPayView(event.getPlayer());
			 }
		 }else if(data.get("title").toString().equalsIgnoreCase(MainView.PayViewTitle)) {
			 JSONObject input =  data.getJSONObject("response").getJSONObject("inputResponses");
			 event.getPlayer().getServer().dispatchCommand(event.getPlayer(),"points pay " + input.getString("1") + " " + input.getString("3"));
			 
		 }
	 }

}
