package cameron.dp.gui;

import javafx.application.Application;
import javafx.scene.Scene;
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

	@Override
	public void start(Stage primaryStage) {
		// Create UI
		// Create Pane
		GridPane pane = new GridPane();
		
		// Create Scene
		Scene scene = new Scene(pane, WIDTH * SCALE, HEIGHT * SCALE);

		// Add the scene to primaryStage
		primaryStage.setScene(scene);
		primaryStage.setTitle(NAME);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
