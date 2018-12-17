package cameron.dp.backend;

public class CombatLevelCalc {
	// Variables
	private int def, hp, pyr, atk, str, rgd, mgc;

	// static
	public static double BASE_MULTIPLIER = 0.25;
	public static double SKILL_MULTIPLIER = 0.325;

	/**
	 * No args constructor. Sets all levels to 1
	 */
	public CombatLevelCalc() {
		this(1, 10, 1, 1, 1, 1, 1);
	}

	/**
	 * Sets the levels to the specified values.
	 */
	public CombatLevelCalc(int def, int hp, int pyr, int atk, int str, int rgd, int mgc) {
		this.def = def;
		this.hp = hp;
		this.pyr = pyr;
		this.atk = atk;
		this.str = str;
		this.rgd = rgd;
		this.mgc = mgc;
	}

	/**
	 * Returns the highest of the possible combat levels. This would be the combat
	 * level used by the game for general purposes.
	 * 
	 * @return general combat level
	 */
	public int getGeneralCombatLevel() {
		return (int) getGeneralCombatLevelUnrounded();
	}
	
	public double getGeneralCombatLevelUnrounded() {
		double lvl = getMelee();
		double compare = getRange();

		if (lvl < compare) {
			lvl = compare;
		}
		compare = getMage();
		if (lvl < compare) {
			lvl = compare;
		}
		
		return getBase() + lvl;
	}

	/**
	 * Returns the combat level for melee combat. Acts as if melee combat was the
	 * player's highest combat level.
	 * 
	 * @return melee combat level
	 */
	public int getMeleeCombatLevel() {
		return (int) (getBase() + getMelee());
	}

	/**
	 * Returns the combat level for ranged combat. Acts as if ranged combat was the
	 * player's highest combat level.
	 * 
	 * @return ranged combat level
	 */
	public int getRangedCombatLevel() {
		return (int) (getBase() + getRange());
	}

	/**
	 * Returns the combat level for magic combat. Acts as if magic combat was the
	 * player's highest combat level.
	 * 
	 * @return magic combat level
	 */
	public int getMagicCombatLevel() {
		return (int) (getBase() + getMage());
	}

	/**
	 * Returns the base level, used for all combat levels.
	 * 
	 * @return
	 */
	private double getBase() {
		return BASE_MULTIPLIER * (def + hp + (pyr / 2));
	}

	private double getMelee() {
		return SKILL_MULTIPLIER * (atk + str);
	}

	private double getRange() {
		return SKILL_MULTIPLIER * ((rgd / 2) + rgd);
	}

	private double getMage() {
		return SKILL_MULTIPLIER * ((mgc / 2) + mgc);
	}

	public void setDefenceLevel(int defence) {
		this.def = defence;
	}
	
	public void setRangedLevel(int ranged) {
		this.rgd = ranged;
	}
	
	public int getDefenceLevel() {
		return this.def;
	}

	public int getRangedLevel() {
		return this.rgd;
	}

	public String getSkillLevelString() {
		String msg = "Defence: " + def + "\nHP: " + hp + "\nPrayer: " + pyr + "\nAttack: " + atk + "\nStrength: " + str
				+ "\nRanged: " + rgd + "\nMagic: " + mgc;
		return msg;
	}

	/**
	 * Testing grounds
	 */
	public static void main(String[] args) {
		// Variables
		int startDef = 21, startRanged = 29;
		CombatLevelCalc calc = new CombatLevelCalc(25, 26, 22, 30, 11, 30, 10);

		System.out.println("Base: " + calc.getBase());
		System.out.println("General: " + calc.getGeneralCombatLevel());
		System.out.println("General(NR): " + calc.getGeneralCombatLevelUnrounded());
		System.out.println("Melee: " + calc.getMeleeCombatLevel());
		System.out.println("Magic: " + calc.getMagicCombatLevel());
		System.out.println("Ranged: " + calc.getRangedCombatLevel() + "\n");

		
		System.out.println("Pure Def: ");
		while (calc.getGeneralCombatLevel() < 30) {
			calc.setDefenceLevel(calc.getDefenceLevel() + 1);
		}
		
		System.out.println("General: " + calc.getGeneralCombatLevel());
		System.out.println("General(NR): " + calc.getGeneralCombatLevelUnrounded());
		System.out.println(calc.getSkillLevelString());
		
		calc.setDefenceLevel(startDef);
		
		System.out.println("Pure Ranged: ");
		while (calc.getGeneralCombatLevel() < 30) {
			calc.setRangedLevel(calc.getRangedLevel() + 1);
		}
		
		System.out.println("General: " + calc.getGeneralCombatLevel());
		System.out.println("General(NR): " + calc.getGeneralCombatLevelUnrounded());
		System.out.println(calc.getSkillLevelString());
	}

}
