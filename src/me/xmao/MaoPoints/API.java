package me.xmao.MaoPoints;

import cn.nukkit.Player;
import me.xmao.MaoPoints.Core.PlayerPoint;

public class API {
	public static boolean hasPoint(Player player,int amount) {
		return (new PlayerPoint(player)).has(amount);
	}
	public static boolean givePoint(Player player,int amount) {
		return (new PlayerPoint(player)).give(amount);
	}
	public static boolean takePoint(Player player,int amount) {
		return (new PlayerPoint(player)).take(amount);
	}
	public static boolean setPoint(Player player,int amount) {
		return (new PlayerPoint(player)).set(amount);
	}
}
