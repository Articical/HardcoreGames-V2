package uk.rythefirst.hcgames.enums;

public enum ChestTier {

	ONE, TWO, THREE, FOUR;

	public static ChestTier fromString(String string) {
		if (string == "ONE") {
			return ONE;
		} else if (string == "TWO") {
			return TWO;
		} else if (string == "THREE") {
			return THREE;
		} else if (string == "FOUR") {
			return FOUR;
		}
		return ONE;
	}

}
