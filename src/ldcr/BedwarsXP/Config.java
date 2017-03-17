package ldcr.BedwarsXP;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;

import ldcr.BedwarsXP.Utils.ListUtils;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
	private static int ConfigVersion = 1;
	static YamlConfiguration config;
	static File file;

	static YamlConfiguration enable;
	static File e_file;

	public static String Message;
	public static boolean Add_Res_Shop;

	public static double Death;
	public static boolean isDirect;

	public static boolean Full_XP_Bedwars;
	public static HashMap<Material, Integer> res = new HashMap<Material, Integer>();

	private static HashSet<String> enabled = new HashSet<String>();

	public static void loadConfig() {
		ldcr.BedwarsXP.Main.sendConsoleMessage("��6��l[BedwarsXP] &b��ʼ���������ļ�");
		file = new File("plugins/BedwarsXP/config.yml");
		if (!file.exists()) {
			ldcr.BedwarsXP.Main
					.sendConsoleMessage("��6��l[BedwarsXP] &b�����ļ�������,���ڴ���...");
			ldcr.BedwarsXP.Main.plugin.saveResource("config.yml", true);
		}
		config = YamlConfiguration.loadConfiguration(file);
	
		if (config.getInt("ConfigVersion") < ConfigVersion) {
			ldcr.BedwarsXP.Main
					.sendConsoleMessage("��6��l[BedwarsXP] &4���������ļ��汾�����޷�����");
			file.renameTo(new File("plugins/BedwarsXP/config.yml.bak"));
			ldcr.BedwarsXP.Main
					.sendConsoleMessage("��6��l[BedwarsXP] &c�ѱ������������ļ�Ϊ [config.yml.bak] ,��ʼ��ʼ���°汾�����ļ�");
			ldcr.BedwarsXP.Main.plugin.saveResource("config.yml", true);
			ldcr.BedwarsXP.Main
					.sendConsoleMessage("��6��l[BedwarsXP] &a�����ļ���ʼ�����,������������...");
			file = new File("plugins/BedwarsXP/config.yml");
			config = YamlConfiguration.loadConfiguration(file);
		}

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
		ldcr.BedwarsXP.Main.sendConsoleMessage("��6��l[BedwarsXP] &a��ʼ������Դ��ֵ����");
		if (Main.isOldBedwarsPlugin) {
			for (final String key : io.github.yannici.bedwars.Main
					.getInstance().getConfig()
					.getConfigurationSection("ressource").getKeys(true)) {
				final ConfigurationSection keySection = io.github.yannici.bedwars.Main
						.getInstance().getConfig()
						.getConfigurationSection("ressource." + key);
				if (keySection == null) {
					continue;
				}
				if (!keySection.contains("item")) {
					continue;
				}
				final Material mat = io.github.yannici.bedwars.Utils
						.parseMaterial(keySection.getString("item"));
				int xp = config.getInt("XP." + key, 0);
				res.put(mat, xp);
				ldcr.BedwarsXP.Main
						.sendConsoleMessage("��6��l[BedwarsXP] &a������Դ [" + key
								+ "] ��Ʒ:" + mat.toString() + " ��ֵ" + xp);
			}
		} else {
			for (final String key : io.github.bedwarsrel.BedwarsRel.Main
					.getInstance().getConfig()
					.getConfigurationSection("ressource").getKeys(true)) {
				final ConfigurationSection keySection = io.github.bedwarsrel.BedwarsRel.Main
						.getInstance().getConfig()
						.getConfigurationSection("ressource." + key);
				if (keySection == null) {
					continue;
				}
				if (!keySection.contains("item")) {
					continue;
				}
				final Material mat = io.github.bedwarsrel.BedwarsRel.Utils.Utils
						.parseMaterial(keySection.getString("item"));
				int xp = config.getInt("XP." + key, 0);
				res.put(mat, xp);
				ldcr.BedwarsXP.Main
						.sendConsoleMessage("��6��l[BedwarsXP] &a������Դ [" + key
								+ "] ��Ʒ:" + mat.toString() + " ��ֵ" + xp);
			}
		}
		e_file = new File("plugins/BedwarsXP/enabledGames.yml");
		if (!e_file.exists()) {
			ldcr.BedwarsXP.Main
					.sendConsoleMessage("��6��l[BedwarsXP] &cע��,���°汾������Ҫ�ֶ�ʹ��/bwxp enable��������Ϸ�ľ�����ģʽ");
			ldcr.BedwarsXP.Main.plugin.saveResource("enabledGames.yml", true);
		}
		enable = YamlConfiguration.loadConfiguration(e_file);
		enabled.addAll(enable.getStringList("enabledGame"));
	}

	public static String setGameEnableXP(String bw, boolean isEnabled) {
		if (isEnabled) {
			enabled.add(bw);
		} else {
			enabled.remove(bw);
		}
		enable.set("enabledGame", ListUtils.hashSetToList(enabled));
		try {
			enable.save(e_file);
		} catch (IOException e) {
			e.printStackTrace();
			return e.getLocalizedMessage();
		}
		return "";
	}

	public static boolean isGameEnabledXP(String bw) {
		return enabled.contains(bw);
	}
}
