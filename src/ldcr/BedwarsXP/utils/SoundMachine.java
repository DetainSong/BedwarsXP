package ldcr.BedwarsXP.utils;

import org.bukkit.Sound;

public class SoundMachine {
	public static Sound get(final String v18, final String v19) {
		Sound sound = null;
		try {
			sound = Sound.valueOf(v18);
			if (sound == null) {
				sound = Sound.valueOf(v19);
			}
		} catch (final Exception ex) {
		}
		return sound;
	}

}
