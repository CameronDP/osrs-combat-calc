package cameron.dp.backend;

public class CombatLevelCalc {
	// Static Constants
	public static final double COMBAT_BASE_MULTIPLIER = 0.25;
	public static final double COMBAT_SKILL_MULTIPLIER = 0.325;

	/**
	 * No args constructor.
	 */
	private CombatLevelCalc() {
	}

	/**
	 * Returns the highest of the possible combat levels. This would be the combat
	 * level used by the game for general purposes.
	 * 
	 * @return general combat level
	 */
	public static int getGeneralCombatLevel(PlayerSkillManager skillManager) {
		return (int) getGeneralCombatLevelUnrounded(skillManager);
	}

	public static double getGeneralCombatLevelUnrounded(PlayerSkillManager skillManager) {
		double lvl = getMelee(skillManager);
		double compare = getRange(skillManager);

		if (lvl < compare) {
			lvl = compare;
		}
		compare = getMage(skillManager);
		if (lvl < compare) {
			lvl = compare;
		}

		return getBase(skillManager) + lvl;
	}

	/**
	 * Returns the combat level for melee combat. Acts as if melee combat was the
	 * player's highest combat level.
	 * 
	 * @return melee combat level
	 */
	public static int getMeleeCombatLevel(PlayerSkillManager skillManager) {
		return (int) (getBase(skillManager) + getMelee(skillManager));
	}

	/**
	 * Returns the combat level for ranged combat. Acts as if ranged combat was the
	 * player's highest combat level.
	 * 
	 * @return ranged combat level
	 */
	public static int getRangedCombatLevel(PlayerSkillManager skillManager) {
		return (int) (getBase(skillManager) + getRange(skillManager));
	}

	/**
	 * Returns the combat level for magic combat. Acts as if magic combat was the
	 * player's highest combat level.
	 * 
	 * @return magic combat level
	 */
	public static int getMagicCombatLevel(PlayerSkillManager skillManager) {
		return (int) (getBase(skillManager) + getMage(skillManager));
	}

	/**
	 * Returns the base level, used for all combat levels.
	 * 
	 * @return
	 */
	private static double getBase(PlayerSkillManager skillManager) {
		return COMBAT_BASE_MULTIPLIER * (skillManager.getSkillLevel("defence") + skillManager.getSkillLevel("hitpoints")
				+ (skillManager.getSkillLevel("prayer") / 2));
	}

	private static double getMelee(PlayerSkillManager skillManager) {
		return COMBAT_SKILL_MULTIPLIER
				* (skillManager.getSkillLevel("attack") + skillManager.getSkillLevel("strength"));
	}

	private static double getRange(PlayerSkillManager skillManager) {
		return COMBAT_SKILL_MULTIPLIER
				* ((skillManager.getSkillLevel("ranged") / 2) + skillManager.getSkillLevel("ranged"));
	}

	private static double getMage(PlayerSkillManager skillManager) {
		return COMBAT_SKILL_MULTIPLIER
				* ((skillManager.getSkillLevel("magic") / 2) + skillManager.getSkillLevel("magic"));
	}

	/**
	 * Testing grounds
	 */
	public static void main(String[] args) {
		// Variables
		PlayerSkillManager skillManager = new PlayerSkillManager(30, 12, 25, 30, 23, 10, 28);
		
		System.out.println(skillManager.getSkillsString());
		System.out.println("General: " + CombatLevelCalc.getGeneralCombatLevel(skillManager));
		System.out.println("Melee:   " + CombatLevelCalc.getMeleeCombatLevel(skillManager));
		System.out.println("Magic:   " + CombatLevelCalc.getMagicCombatLevel(skillManager));
		System.out.println("Ranged:  " + CombatLevelCalc.getRangedCombatLevel(skillManager));
	}

}
