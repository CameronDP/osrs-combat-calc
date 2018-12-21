package cameron.dp.gui;

import cameron.dp.backend.CombatLevelCalc;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
		skillManager.setSkillLevel("hitpoints", 10);
		// Create UI
		// Create GridPane
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		for (int i = 0; i < skillManager.getCombatSkillNames().size(); ++i) {
			skillLabels[i] = new Label(skillManager.getCombatSkillNames().get(i) + ":");
			grid.add(skillLabels[i], 0, i);

			skillInputs[i] = new TextField("" + skillManager.getSkillLevel(skillManager.getCombatSkillNames().get(i)));
			grid.add(skillInputs[i], 1, i);
		}

		Button btn = new Button("Calculate");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, skillManager.getCombatSkillNames().size());
		
		
		VBox vbCombat = new VBox(5);
		HBox hbCombat = new HBox(5);
		vbCombat.setAlignment(Pos.CENTER);
		hbCombat.setAlignment(Pos.CENTER);
		
		vbCombat.getChildren().add(new Text("Combat Level: "));
		
		final Text[] combatInfos = {new Text("3"), new Text("(Melee, Ranger, Mage)")};
		hbCombat.getChildren().add(combatInfos[0]);
		hbCombat.getChildren().add(combatInfos[1]);
		vbCombat.getChildren().add(hbCombat);
		grid.add(vbCombat, 2, 0, 1, skillManager.getCombatSkillNames().size());
		
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				for (int i = 0; i < skillManager.getCombatSkillNames().size(); ++i) {
					skillManager.setSkillLevel(skillLabels[i].getText().substring(0, skillLabels[i].getText().length() - 1), Integer.parseInt((skillInputs[i].getCharacters().toString())));
				}
				//TESTING
				System.out.println(skillManager.getSkillsString());
				combatInfos[0].setText("" + CombatLevelCalc.getGeneralCombatLevel(skillManager));
				combatInfos[1].setText(CombatLevelCalc.getGeneralCombatLevelType(skillManager));
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
