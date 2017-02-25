package ldcr.BedwarsXP;

import ldcr.BedwarsXP.Utils.BWGameUtils;
import ldcr.BedwarsXP.Utils.SendMessageUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandListener implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] args) {
		if (args.length == 0) {
			SendMessageUtils.sendMessage(sender,
					"��6��l[BedwarsXP] ��b�����𴲲�� ��lBy.Ldcr",
					"��6��l[BedwarsXP] ��a/bwxp reload ���ز������");
			return true;
		}
		if (!sender.hasPermission("bedwarsxp.admin")) {
			sender.sendMessage("��6��l[BedwarsXP] ��c��û��Ȩ��ִ�д�����");
			return true;
		}
		switch (args[0].toLowerCase()) {
		default: {
			sender.sendMessage("��6��l[BedwarsXP] ��b/bwxp ���ؾ����𴲲������");
			break;
		}
		case "reload": {
			Config.loadConfig();
			sender.sendMessage("��6��l[BedwarsXP] ��b�ɹ����������ļ�~");
			if (BWGameUtils.isAnyBedwarsRunning()) {
				SendMessageUtils.sendMessage(sender,
						"��6��l[BedwarsXP] ��b��ǰ����Ϸ��������",
						"��6��l[BedwarsXP] ��b��ʼ���������е���Ϸ�ľ����̵�");
				BWGameUtils.replaceAllShop(sender);
			}
		}
		}
		return true;
	}

}
