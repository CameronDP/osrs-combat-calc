package cameron.dp.gui;

import cameron.dp.backend.PlayerSkillManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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

	// Variables
	private PlayerSkillManager skillManager = new PlayerSkillManager();
	private Label[] skillLabels = new Label[skillManager.getCombatSkillNames().size()];
	private TextField[] skillInputs = new TextField[skillManager.getCombatSkillNames().size()];

	@Override
	public void start(Stage primaryStage) {
		// Create UI
		// Create GridPane
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(15, 25, 25, 15));

		for (int i = 0; i < skillManager.getCombatSkillNames().size(); ++i) {
			skillLabels[i] = new Label(skillManager.getCombatSkillNames().get(i) + ":");
			grid.add(skillLabels[i], 0, i);

			skillInputs[i] = new TextField("1");
			grid.add(skillInputs[i], 1, i);
		}

		Button btn = new Button("Calculate");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, skillManager.getCombatSkillNames().size());
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				for (int i = 0; i < skillManager.getCombatSkillNames().size(); ++i) {
					skillManager.setSkillLevel(skillLabels[i].getText().substring(0, skillLabels[i].getText().length() - 1), Integer.parseInt((skillInputs[i].getCharacters().toString())));
				}
				
				//TESTING
				System.out.println(skillManager.getSkillsString());
			}
		});
		
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
