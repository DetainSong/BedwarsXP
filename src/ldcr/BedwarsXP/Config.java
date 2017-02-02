package ldcr.BedwarsXP;

import io.github.yannici.bedwars.Main;
import io.github.yannici.bedwars.Game.RessourceSpawner;

import java.io.File;
import java.text.NumberFormat;
import java.text.ParseException;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
	static YamlConfiguration config;
	static File file;

	public static int XP_Brick;
	public static int XP_Iron;
	public static int XP_Gold;

	public static String Message;
	public static boolean Add_Res_Shop;

	public static double Death;
	public static boolean isDirect;

	public static boolean Full_XP_Bedwars;

	public static Material Bedwars_Brick_Type;
	public static Material Bedwars_Iron_Type;
	public static Material Bedwars_Gold_Type;

	public static void loadConfig() {
		ldcr.BedwarsXP.Main.sendConsoleMessage("��6��l[BedwarsXP] &b��ʼ���������ļ�");
		file = new File("plugins/BedwarsXP/config.yml");
		if (!file.exists()) {
			ldcr.BedwarsXP.Main
					.sendConsoleMessage("��6��l[BedwarsXP] &b�����ļ�������,���ڴ���...");
			ldcr.BedwarsXP.Main.plugin.saveResource("config.yml", true);
		}
		config = YamlConfiguration.loadConfiguration(file);
		XP_Brick = config.getInt("XP.Brick");
		XP_Iron = config.getInt("XP.Iron");
		XP_Gold = config.getInt("XP.Gold");
		ldcr.BedwarsXP.Main.sendConsoleMessage("��6��l[BedwarsXP] &a��Դ��ֵ: &4"
				+ XP_Brick + "&a/&7" + XP_Iron + "&a/&6" + XP_Gold);
		Message = config.getString("Message").replaceAll("&", "��")
				.replaceAll("���", "&");
		Add_Res_Shop = config.getBoolean("Add_Res_Shop");
		if (Add_Res_Shop) {
			ldcr.BedwarsXP.Main
					.sendConsoleMessage("��6��l[BedwarsXP] &a�����þ���һ���Դ�̵�");
		}
		String temp = config.getString("Death");
		if (temp.endsWith("%")) {
			NumberFormat nf = NumberFormat.getPercentInstance();
			try {
				Death = nf.parse(temp).doubleValue();
				isDirect = false;
			} catch (ParseException e) {
				ldcr.BedwarsXP.Main
						.sendConsoleMessage("��6��l[BedwarsXP] &c�����������۳������д���,�˹��ܹر�");
				Death = 0;
				isDirect = true;
				e.printStackTrace();
			}
		} else {
			Death = Integer.valueOf(temp);
			isDirect = true;
		}
		if (isDirect) {
			ldcr.BedwarsXP.Main
					.sendConsoleMessage("��6��l[BedwarsXP] &a����ֱ�ӿ۳�����: " + Death);
		} else {
			ldcr.BedwarsXP.Main
					.sendConsoleMessage("��6��l[BedwarsXP] &a�����ٷֱȿ۳�����: " + Death
							* 100 + "%");
		}
		Full_XP_Bedwars = config.getBoolean("Full_XP_Bedwars");
		if (Full_XP_Bedwars) {
			ldcr.BedwarsXP.Main
					.sendConsoleMessage("��6��l[BedwarsXP] &a��ȫ������ģʽ������");
		}
		Bedwars_Brick_Type = RessourceSpawner.createSpawnerStackByConfig(
				Main.getInstance().getConfig().get("ressource.bronze"))
				.getType();
		Bedwars_Iron_Type = RessourceSpawner.createSpawnerStackByConfig(
				Main.getInstance().getConfig().get("ressource.iron")).getType();
		Bedwars_Gold_Type = RessourceSpawner.createSpawnerStackByConfig(
				Main.getInstance().getConfig().get("ressource.gold")).getType();
	}
}
