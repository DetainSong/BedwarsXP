package ldcr.BedwarsXP;

import ldcr.BedwarsXP.Utils.ActionBarUtils;
import ldcr.BedwarsXP.Utils.ReflectionUtils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static Plugin plugin;
	public static CommandSender log;

	public void onEnable() {
		plugin = this;
		log = Bukkit.getConsoleSender();
		sendConsoleMessage("��6��l[BedwarsXP] &b���ڼ���BedwarsXP�����𴲲��");
		if (!(ReflectionUtils.isClassFound("io.github.yannici.bedwars.Main") && ReflectionUtils
				.isClassFound("io.github.yannici.bedwars.SoundMachine"))) {
			sendConsoleMessage("��6��l[BedwarsXP] &c�������֧�����BedwarsRel�汾!");
		}
		Config.loadConfig();
		ActionBarUtils.load();
		Bukkit.getPluginManager().registerEvents(new EventListeners(), this);
		this.getCommand("bedwarsxp").setExecutor(new CommandListener());
		sendConsoleMessage("��6��l[BedwarsXP] &b�ɹ�����BedwarsXP�����𴲲�� By.Ldcr");
	}

	public static void sendConsoleMessage(String str) {
		log.sendMessage(str.replaceAll("&", "��"));
	}

}
