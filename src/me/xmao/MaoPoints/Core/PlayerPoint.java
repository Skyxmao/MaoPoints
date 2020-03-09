package me.xmao.MaoPoints.Core;

import cn.nukkit.Player;
import me.xmao.MaoPoints.MaoPoints;

public class PlayerPoint {
	public String playername;
	public PlayerPoint(Player player) {
		this.playername = player.getName().toLowerCase();
	}
	public PlayerPoint(String playername) {
		this.playername = playername.toLowerCase();
	}
	public boolean has(int amount) {
		if(get() < amount) {
			return false;
		}
		return true;
	}
	public boolean give(int amount) {
		if(MaoPoints.point_sql.getValue(this.playername).get("amount") == null) {
			MaoPoints.point_sql.createValue(this.playername);
		}
		return MaoPoints.point_sql.addNumber(playername, amount);
	}
	public boolean take(int amount) {
		if(MaoPoints.point_sql.getValue(this.playername).get("amount") == null) {
			MaoPoints.point_sql.createValue(this.playername);
		}
		if(!has(amount)) {
			return false;
		}
		return MaoPoints.point_sql.subNumber(playername, amount);
	}
	public boolean set(int amount) {
		return MaoPoints.point_sql.setNumber(playername, amount);
	}
	public int get() {
		if(MaoPoints.point_sql.getValue(this.playername).get("amount") == null) {
			return 0;
		}
		return (int) MaoPoints.point_sql.getValue(this.playername).get("amount");
	}
	
}
