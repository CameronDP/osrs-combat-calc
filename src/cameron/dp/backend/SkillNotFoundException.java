package cameron.dp.backend;

/**
 * @author Cameron
 *
 */
public class SkillNotFoundException extends RuntimeException{
	//Variables
	private String name;
	
	public SkillNotFoundException(String name) {
		super("Skill \"" + name + "\" was not found.");
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
