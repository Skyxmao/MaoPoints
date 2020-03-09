package me.xmao.MaoPoints.View;

import java.util.ArrayList;
import java.util.List;

import cn.nukkit.Player;
import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;

public class MainView {
	public static String MainViewTitle = "§6MaoPoints - 请选择";
	public static String PayViewTitle = "§6MaoPoints - 转账";
    public static void showMainView(Player p) {
        final List<ElementButton> buttons = new ArrayList<ElementButton>();
        buttons.add(new ElementButton("查询"));
        buttons.add(new ElementButton("转账"));
        final FormWindowSimple window = new FormWindowSimple(MainViewTitle, "", (List)buttons);
        p.showFormWindow((FormWindow)window);
    }
    public static void showPayView(Player p) {
        final List<Element> el = new ArrayList<Element>();
        el.add((Element)new ElementLabel("玩家姓名"));
        el.add((Element)new ElementInput(""));
        el.add((Element)new ElementLabel("转账金额"));
        el.add((Element)new ElementInput(""));
        p.showFormWindow((FormWindow)new FormWindowCustom(PayViewTitle, (List)el));
    }
}
