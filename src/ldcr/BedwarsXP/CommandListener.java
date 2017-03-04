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
					"��6��l[BedwarsXP] ��a/bwxp reload        ���ز������",
					"��6��l[BedwarsXP] ��a/bwxp enable  <��Ϸ> ����ָ����Ϸ�ľ�����ģʽ",
					"��6��l[BedwarsXP] ��a/bwxp disable <��Ϸ> ����ָ����Ϸ�ľ�����ģʽ");
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
		case "enable": {
			if (args.length != 2) {
				sender.sendMessage("��6��l[BedwarsXP] ��a/bwxp enable  <��Ϸ> ����ָ����Ϸ�ľ�����ģʽ");
				return true;
			}
			if (!BWGameUtils.isGameExists(args[1])) {
				sender.sendMessage("��6��l[BedwarsXP] ��c����: �Ҳ�����Ϸ [" + args[1]
						+ " ]");
				return true;
			}
			String result = Config.setGameEnableXP(args[1], true);
			if (result.equals("")) {
				sender.sendMessage("��6��l[BedwarsXP] ��a�ɹ���������Ϸ " + args[1]
						+ " �ľ�����ģʽ");
				if (BWGameUtils.isGameRunning(args[1])) {
					sender.sendMessage("��6��l[BedwarsXP] ��4��Ϸ " + args[1]
							+ " ��������,���ֶ�������Ϸ");
				}
			} else {
				SendMessageUtils.sendMessage(sender,
						"��6��l[BedwarsXP] ��c����: �ڳ��Լ�����Ϸ " + args[1]
								+ " �ľ�����ģʽʱ����", "��6��l[BedwarsXP] ��c" + result);
			}
			return true;
		}
		case "disable": {
			if (args.length != 2) {
				sender.sendMessage("��6��l[BedwarsXP] ��a/bwxp disable  <��Ϸ> ����ָ����Ϸ�ľ�����ģʽ");
				return true;
			}
			if (!BWGameUtils.isGameExists(args[1])) {
				sender.sendMessage("��6��l[BedwarsXP] ��c����: �Ҳ�����Ϸ [" + args[1]
						+ " ]");
				return true;
			}
			String result = Config.setGameEnableXP(args[1], false);
			if (result.equals("")) {
				sender.sendMessage("��6��l[BedwarsXP] ��a�ɹ���������Ϸ " + args[1]
						+ " �ľ�����ģʽ");
				if (BWGameUtils.isGameRunning(args[1])) {
					sender.sendMessage("��6��l[BedwarsXP] ��4��Ϸ " + args[1]
							+ " ��������,���ֶ�������Ϸ");
				}
			} else {
				SendMessageUtils.sendMessage(sender,
						"��6��l[BedwarsXP] ��c����: �ڳ��Խ�����Ϸ " + args[1]
								+ " �ľ�����ģʽʱ����", "��6��l[BedwarsXP] ��c" + result);
			}
			return true;
		}
		}
		return true;
	}

}
