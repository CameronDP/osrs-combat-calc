/**
 * 
 */
package cameron.dp.backend;

import java.util.ArrayList;

/**
 * May need to review the design of this class. Its built to support a different
 * set of skills, but does not allow additional skills to be added. It also
 * requires the 23 skills I've added because they cannot be removed.
 * 
 * @author Cameron
 *
 */
public class PlayerSkillManager {
	// Variables
	private ArrayList<Skill> skills = new ArrayList<>();

	/**
	 * Constructs a player skill manager with all skill levels set to 1 with 0
	 * experience.
	 */
	public PlayerSkillManager() {
		this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	}

	/**
	 * Constructs a player skill manager with the specified amount of experience in
	 * each free to play skill. The remaining pay to play skill levels are set to 1
	 * with 0 experience.
	 */
	public PlayerSkillManager(double attack, double strength, double defence, double ranged, double prayer,
			double magic, double runecrafting, double hitpoints, double crafting, double mining, double smithing,
			double fishing, double cooking, double firemaking, double woodcutting) {
		this(attack, strength, defence, ranged, prayer, magic, runecrafting, hitpoints, crafting, mining, smithing,
				fishing, cooking, firemaking, woodcutting, 0, 0, 0, 0, 0, 0, 0, 0);
	}

	/**
	 * Constructs a player skill manager with the specified amount of experience in
	 * each skill.
	 */
	public PlayerSkillManager(double attack, double strength, double defence, double ranged, double prayer,
			double magic, double runecrafting, double hitpoints, double crafting, double mining, double smithing,
			double fishing, double cooking, double firemaking, double woodcutting, double agility, double herblore,
			double thieving, double fletching, double slayer, double farming, double construction, double hunter) {
		skills.add(new Skill("Attack", attack));
		skills.add(new Skill("Strength", strength));
		skills.add(new Skill("Defence", defence));
		skills.add(new Skill("Ranged", ranged));
		skills.add(new Skill("Prayer", prayer));
		skills.add(new Skill("Magic", magic));
		skills.add(new Skill("Runecrafting", runecrafting));
		skills.add(new Skill("Hitpoints", hitpoints));
		skills.add(new Skill("Crafting", crafting));
		skills.add(new Skill("Mining", mining));
		skills.add(new Skill("Smithing", smithing));
		skills.add(new Skill("Fishing", fishing));
		skills.add(new Skill("Cooking", cooking));
		skills.add(new Skill("Firemaking", firemaking));
		skills.add(new Skill("Woodcutting", woodcutting));
		skills.add(new Skill("Agility", agility));
		skills.add(new Skill("Herblore", herblore));
		skills.add(new Skill("Thieving", thieving));
		skills.add(new Skill("Fletching", fletching));
		skills.add(new Skill("Slayer", slayer));
		skills.add(new Skill("Farming", farming));
		skills.add(new Skill("Construction", construction));
		skills.add(new Skill("Hunter", hunter));
	}

	/**
	 * Constructs a player skill manager with the specified amount of experience in
	 * each combat skill. The remaining skill levels are set to 1 with 0 experience.
	 */
	public PlayerSkillManager(double attack, double strength, double defence, double ranged, double prayer,
			double magic, double hitpoints) {
		this(attack, strength, defence, ranged, prayer, magic, 0, hitpoints, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0);
	}

	/**
	 * Sets the level of the skill with the specified name to the specified level.
	 * The name of the skill is not case sensitive.
	 * 
	 * @param name
	 *            - the name of the skill to change
	 * @param level
	 *            - the new level
	 * @throws SkillNotFoundException
	 *             If there is no skill with the specified name
	 */
	public void setSkillLevel(String name, int level) {
		getSkill(name.toLowerCase()).setLevel(level);
	}

	/**
	 * Sets the experience of the skill with the specified name to the specified
	 * amount of experience. The name of the skill is not case sensitive.
	 * 
	 * @param name
	 *            - the name of the skill to change
	 * @param exp
	 *            - the new amount of experience
	 * @throws SkillNotFoundException
	 *             If there is no skill with the specified name
	 */
	public void setSkillExp(String name, double exp) {
		getSkill(name.toLowerCase()).setExp(exp);
	}

	/**
	 * Returns the skill from the skill list with the specified name. The name
	 * argument is not case sensitive.
	 * 
	 * @param name
	 *            - the name of the skill to return
	 * @return the skill from the skill list with the specified name
	 * @throws SkillNotFoundException
	 *             If there is no skill with the specified name
	 */
	private Skill getSkill(String name) throws SkillNotFoundException {
		name = name.toLowerCase();
		for (Skill skill : skills) {
			if (skill.getName().toLowerCase().equals(name)) {
				return skill;
			}
		}

		throw new SkillNotFoundException(name);
	}

	/**
	 * Returns the level of the skill that matches the specified name. The name
	 * argument is not case sensitive.
	 * 
	 * @param name
	 *            - the name of the skill whose level to return
	 * @return the level of the skill that matches the specified name
	 */
	public int getSkillLevel(String name) {
		return getSkill(name.toLowerCase()).getLevel();
	}

	/**
	 * Returns the experience of the skill that matches the specified name. The name
	 * argument is not case sensitive.
	 * 
	 * @param name
	 *            - the name of the skill whose experience to return
	 * @return the experience of the skill that matches the specified name
	 */
	public double getSkillExp(String name) {
		name = name.toLowerCase();
		return getSkill(name.toLowerCase()).getExp();
	}

	/**
	 * Returns a string representation of the specified skill's name, level and
	 * amount of experience in the form of:
	 * <p>
	 * {@code <NAME>} {@code <LEVEL>} : {@code <EXP>}
	 * <p>
	 * The name argument is not case sensitive.
	 * 
	 * @return a string representation of the specified skill's name, level and
	 *         amount of experience
	 * @throws SkillNotFoundException
	 *             If there is no skill with the specified name
	 */
	public String getSkillString(String name) {
		return getSkill(name).toString();
	}

	/**
	 * Returns a string representation of a list of every skill's name, level and
	 * amount of experience in the form of:
	 * <p>
	 * {@code <NAME>} {@code <LEVEL>} : {@code <EXP>}
	 * <p>
	 * Each skill is separated by commas ({@code ,}) and the list is enclosed in
	 * brackets ({@code []})
	 * 
	 * @return string representation of a list of every skill's name, level and
	 *         amount of experience
	 */
	public String getSkillsString() {
		return skills.toString();
	}

	/**
	 * Returns an array list containing strings of name of each skill managed by
	 * this player skill manager.
	 * 
	 * @return array list containing the names of each skill managed by this player
	 *         skill manager
	 */
	public ArrayList<String> getSkillNames() {
		ArrayList<String> names = new ArrayList<>();
		for (Skill skill : skills) {
			names.add(skill.getName());
		}

		return names;
	}

	public static void main(String[] args) {
		PlayerSkillManager tester = new PlayerSkillManager();

		System.out.println(tester.getSkillString("Attack"));
	}
}
