package cameron.dp.backend;

/**
 * @author Cameron
 *
 */
public class NoSuchLevelException extends RuntimeException{
	//Variables
	private double level;
	
	public NoSuchLevelException(double level) {
		super("Levels only exist from 1 to 99, with virtual levels from 100 to 127.");
		this.level = level;
	}
	
	public double getLevel() {
		return this.level;
	}
}
