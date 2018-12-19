package cameron.dp.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author Cameron
 *
 */
public class CombatCalcMainWindow extends Application {
	public static final int WIDTH = 160;
	public static final int HEIGHT = (int) (WIDTH / 12.0 * 9);
	public static final int SCALE = 4;
	public static final String NAME = "Old School RuneScape Combat Calculator";
	
	//Testing
	public static final String[] skillNames = {"Defence", "Hitpoints", "Prayer", "Attack", "Strength", "Ranged", "Magic"};
	
	
	@Override
	public void start(Stage primaryStage) {
		// Create UI
		// Create GridPane
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(15, 25, 25, 15));
		
		Label[] skillLabels = new Label[skillNames.length];
		TextField[] skillInputs = new TextField[skillNames.length];
		
		for (int i = 0; i < skillNames.length; ++i) {
			skillLabels[i] = new Label(skillNames[i] + ":");
			grid.add(skillLabels[i], 0, i);
			
			skillInputs[i] = new TextField();
			grid.add(skillInputs[i], 1, i);
		}
		
		
		// Create Scene
		Scene scene = new Scene(grid, WIDTH * SCALE, HEIGHT * SCALE);

		// Add scene to primaryStage
		primaryStage.setScene(scene);
		primaryStage.setTitle(NAME);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
