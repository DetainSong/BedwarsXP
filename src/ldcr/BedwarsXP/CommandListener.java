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
					"��6��l[BedwarsXP] ��b/bwxp reload ���ز������",
					"��6��l[BedwarsXP] ��b/bwxp updateshop �����̵�");
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
		case "updateshop": {
			SendMessageUtils.sendMessage(sender, "��6��l[BedwarsXP] ��b��ʼ���¾����̵�",
					"��6��l[BedwarsXP] ��c**** W.I.P ����һ����Թ��� ****");
			BWGameUtils.replaceAllShop(sender);
			break;
		}
		case "reload": {
			Config.loadConfig();
			sender.sendMessage("��6��l[BedwarsXP] ��b�ɹ����������ļ�~");
			if (BWGameUtils.isAnyBedwarsRunning()) {
				SendMessageUtils
						.sendMessage(
								sender,
								"��6��l[BedwarsXP] ��c��ǰ����Ϸ��������",
								"��6��l[BedwarsXP] ��c�������̵��еļ۸񱣳־����ò���",
								"��6��l[BedwarsXP] ��c��ִ��/bwxp updateshop�����̵��4��L(W.I.P - ���Թ���)",
								"��6��l[BedwarsXP] ��c���ȷ���ù����ȶ�,�˹��̽��������ز���",
								"��6��l[BedwarsXP] ��cĿǰ������ֱ�����¿�ʼ������Ϸ�Ա�֤��ȫ��");
			}
		}
		}
		return true;
	}

}
