package gui;

import javafx.application.Application;
import javafx.application.Application.Parameters;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import logic.Converter;
import logic.BinaryConverter;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GUI extends Application {

	// I guess these could as well come from a settings file
	// if you really wanna be cool programmer!
	private static int WIDTH = 400;
	private static int HEIGHT = 300;

	@Override
	public void start(Stage window) {

		Parameters params = getParameters();

		List<Converter> converters = new ArrayList<>();
		converters.add(new BinaryConverter());

		// This is the center view where the following exists:
		// - input-field
		// - conversion selection
		// - convert button
		// - the result
		ObservableList<String> converterNames = FXCollections.observableArrayList();
		for (Converter c : converters) {
			converterNames.add(c.getName());
		}
		ChoiceBox converterChoiceBox = new ChoiceBox(converterNames);
		converterChoiceBox.setValue(converterNames.get(0));
		Label text = new Label("Hello World!");
		VBox mainComponents = new VBox();
		mainComponents.setAlignment(Pos.CENTER);
		mainComponents.getChildren().addAll(text, converterChoiceBox);

		// Create title on top and add BorderPane to center the actual content
		Label title = new Label(params.getNamed().get("title"));
		BorderPane componentGroup = new BorderPane();
		// Preferred window size
		componentGroup.setPrefSize(WIDTH, HEIGHT);
		componentGroup.setAlignment(title, Pos.TOP_CENTER);
		componentGroup.setTop(title);
		componentGroup.setCenter(mainComponents);

		Scene scene = new Scene(componentGroup);

		window.setScene(scene);
		window.show();
	}

}
