package cameron.dp.backend;

/**
 * The amount of experience needed for each level is stored as an array of ints,
 * but experience points should be represented as doubles in all other cases
 * because they can be decimal values
 * 
 * @author Cameron
 *
 */
public class Skill {
	// Constants
	private static final int[] levelTable = { 0, 83, 174, 276, 388, 512, 650, 801, 969, 1154, 1358, 1584, 1833, 2107,
			2411, 2746, 3115, 3523, 3973, 4470, 5018, 5624, 6291, 7028, 7842, 8740, 9730, 10824, 12031, 13363, 14833,
			16456, 18247, 20224, 22406, 24815, 27473, 30408, 33648, 37224, 41171, 45529, 50339, 55649, 61512, 67983,
			75127, 83014, 91721, 101333, 111945, 123660, 136594, 150872, 166636, 184040, 203254, 224466, 247886, 273742,
			302288, 333804, 368599, 407015, 449428, 496254, 547953, 605032, 668051, 737627, 814445, 899257, 992895,
			1096278, 1210421, 1336443, 1475581, 1629200, 1798808, 1986068, 2192818, 2421087, 2673114, 2951373, 3258594,
			3597792, 3972294, 4385776, 4842295, 5346332, 5902831, 6517253, 7195629, 7944614, 8771558, 9684577, 10692629,
			11805606, 13034431, 14391160, 15889109, 17542976, 19368992, 21385073, 23611006, 26068632, 28782069,
			31777943, 35085654, 38737661, 42769801, 47221641, 52136869, 57563718, 63555443, 70170840, 77474828,
			85539082, 94442737, 104273167, 115126838, 127110260, 140341028, 154948977, 171077457, 188884740,
			200000000 };// Levels are the indexes, offset by -1

	// Variables
	private final String name;
	private double exp;
	private final boolean isF2P;

	/**
	 * Constructs a skill with the specified name and experience.
	 * 
	 * @param name
	 *            - the name of the new skill
	 * @param exp
	 *            - the amount of experience in the new skill
	 */
	public Skill(String name, boolean isF2P, double exp) {
		this.name = name;
		this.isF2P = isF2P;
		setExp(exp);
	}

	/**
	 * Constructs a skill with the specified name and zero experience.
	 * 
	 * @param -
	 *            the name of the new skill
	 */
	public Skill(String name, boolean isF2P) {
		this(name, isF2P, 0);
	}

	/**
	 * Returns the level reached with the specified amount of experience.
	 * 
	 * @param exp
	 *            - the amount of experience in a skill
	 * @return the level reached with the specified amount of experience
	 * @throws NoSuchLevelException
	 *             If the amount of experience is negative or greater than
	 *             200,000,000
	 */
	public static int expToLevel(double exp) throws NoSuchLevelException {
		if (exp < 0) {
			throw new NoSuchLevelException(-1);
		} else if (exp > 200000000) {
			throw new NoSuchLevelException(128);
		} else {
			for (int i = levelTable.length; i > 0; --i) {
				// Direct access to avoid the unnecessary safety checks of getFromLevelTable()
				if (levelTable[i - 1] <= exp) {
					return i;
				}
			}
			return -1;
		}
	}

	/**
	 * Returns the amount of experience needed to go from the specified level to the
	 * next level
	 * 
	 * @param level
	 *            - the current level
	 * @return the amount of experience needed to go from the specified level to the
	 *         next level
	 * @throws NoSuchLevelException
	 *             If {@code level} is less than 1 or greater than 126
	 */
	public static double expToNextLevelFromLevel(int level) throws NoSuchLevelException {
		return getFromLevelTable(level + 1) - getFromLevelTable(level);
	}

	/**
	 * Returns the level reached with the specified amount of experience, with
	 * decimal precision.
	 * <p>
	 * For example, 84 experience would return about 2.01
	 * 
	 * @param exp
	 *            - the amount of experience in a skill
	 * @return the level reached with the specified amount of experience, with
	 *         decimal precision
	 */
	public static double expToDecimalLevel(double exp) {
		int level = expToLevel(exp);
		double remaining = exp - getFromLevelTable(level);

		return level + remaining / expToNextLevelFromLevel(level);
	}

	/**
	 * Returns the minimum amount of experience required to reach the specified
	 * level. This method is equivalent to {@code levelToExpMin(int)}
	 * 
	 * @param level
	 *            - the level to find the minimum experience to achieve
	 * @return the minimum amount of experience required to reach the specified
	 *         level.
	 * @see #levelToExpMin(int)
	 */
	public static double levelToExp(int level) throws NoSuchLevelException {
		return getFromLevelTable(level);
	}

	/**
	 * Returns the minimum amount of experience required to reach the specified
	 * level.
	 * 
	 * @param level
	 *            - the level to find the minimum experience to achieve
	 * @return the minimum amount of experience required to reach the specified
	 *         level.
	 */
	public static double levelToExpMin(int level) throws NoSuchLevelException {
		return getFromLevelTable(level);
	}

	/**
	 * Returns the maximum amount of experience that a skill can have at the
	 * specified level.
	 * <p>
	 * For example, at level 1 a skill can have at most 82 experience. At 83
	 * experience, the skill will reach level 2.
	 * 
	 * @param level
	 *            - the level to find the associated maximum experience
	 * @return the maximum amount of experience that a skill can have at the
	 *         specified level.
	 */
	public static double levelToExpMax(int level) throws NoSuchLevelException {
		return getFromLevelTable(level + 1) - 1;
	}

	public int getLevel() {
		return expToLevel(this.exp);
	}

	public double getDecimalLevel() {
		return expToDecimalLevel(this.exp);
	}

	public double getExp() {
		return this.exp;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * Returns true if this skill is a Free-to-Play skill, otherwise returns false.
	 * 
	 * @return true if this skill is a Free-to-Play skill, otherwise returns false.
	 */
	public boolean isF2P() {
		return this.isF2P;
	}

	/**
	 * Set the amount of experience in this skill to the specified amount and return
	 * the new amount of experience.
	 * 
	 * @param exp
	 *            - the new amount of experience
	 * @return the new amount of experience
	 * @throws IllegalArgumentException
	 *             If the specified amount of experience is not between 0 and
	 *             200000000, inclusive.
	 */
	public double setExp(double exp) {
		if (exp > -1 && exp < 200000001) {
			this.exp = exp;
		} else {
			throw new IllegalArgumentException(
					"The amount of experience a skill can have is between 0 and 200000000, inclusive.");
		}

		return this.exp;
	}

	/**
	 * Sets the amount of experience in this skill to the minimum amount required
	 * for the specified level
	 * 
	 * @param level
	 *            - the desired level
	 * @return the new amount of experience
	 */
	public double setLevel(int level) {
		// levelTable should never give an invalid amount of experience
		this.exp = getFromLevelTable(level);
		return this.exp;
	}

	public String toString() {
		return this.name + " " + this.getLevel() + " : " + this.exp;
	}

	/**
	 * Returns the specified level's associated amount of experience from
	 * {@code levelTable}
	 * 
	 * @param level
	 *            - the level to find the associated amount of experience
	 * @return the associated amount of experience from {@code levelTable}
	 * @throws NoSuchLevelException
	 *             If the level is not between 1 and 127, inclusive
	 */
	private static double getFromLevelTable(int level) throws NoSuchLevelException {
		if (level > 0 && level < 128) {
			return levelTable[level - 1];
		}
		throw new NoSuchLevelException(level);
	}

	/**
	 * Testing
	 */
	public static void main(String[] args) {
		for (int i = 1; i < 126; ++i) {
			System.out.println(i + "\t\t" + expToNextLevelFromLevel(i));
		}
		System.out.println(200000000 + "\t\t" + expToLevel(200000000));
		System.out.println(expToDecimalLevel(82));
		System.out.println(expToDecimalLevel(84));
		System.out.println(levelToExpMax(1));
	}
}
