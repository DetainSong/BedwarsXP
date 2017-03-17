package ldcr.BedwarsXP;

import ldcr.BedwarsXP.EventListeners.NewEventListeners;
import ldcr.BedwarsXP.EventListeners.OldEventListeners;
import ldcr.BedwarsXP.Utils.ActionBarUtils;
import ldcr.BedwarsXP.Utils.ReflectionUtils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	/*
	 * TODO: none
	 */

	public static Plugin plugin;
	public static CommandSender log;
	public static boolean isOldBedwarsPlugin = true;

	public void onEnable() {
		plugin = this;
		log = Bukkit.getConsoleSender();
		sendConsoleMessage("��6��l[BedwarsXP] &b���ڼ���BedwarsXP�����𴲲�� Version."
				+ this.getDescription().getVersion());
		BWVersionDelect();
		Config.loadConfig();
		ActionBarUtils.load();
		if (Main.isOldBedwarsPlugin) {
			Bukkit.getPluginManager().registerEvents(new OldEventListeners(),
					this);
		} else {
			Bukkit.getPluginManager().registerEvents(new NewEventListeners(),
					this);
		}
		this.getCommand("bedwarsxp").setExecutor(new CommandListener());
		sendConsoleMessage("��6��l[BedwarsXP] &b�ɹ�����BedwarsXP�����𴲲�� By.Ldcr");
	}

	private void BWVersionDelect() {
		if (ReflectionUtils.isClassFound("io.github.yannici.bedwars.Main")) {
			isOldBedwarsPlugin = true;
			sendConsoleMessage("��6��l[BedwarsXP] &a�ҵ���֧�ֵİ汾 [io.github.yannici.bedwars] !");
		} else if (ReflectionUtils
				.isClassFound("io.github.bedwarsrel.BedwarsRel.Main")) {
			isOldBedwarsPlugin = false;
			sendConsoleMessage("��6��l[BedwarsXP] &a�ҵ���֧�ֵİ汾 [io.github.bedwarsrel.BedwarsRel] !");
		} else {
			sendConsoleMessage("[ERROR] ��6��l[BedwarsXP] &cû���ҵ�֧�ֵ�BedwarsRel! �����û�а�װ��ʹ���˲���֧�ֵİ汾!");
			Bukkit.getPluginManager().disablePlugin(this);
		}
	}

	public static void sendConsoleMessage(String str) {
		log.sendMessage(str.replaceAll("&", "��"));
	}

}
