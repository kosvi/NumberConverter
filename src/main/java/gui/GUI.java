package gui;

import javafx.application.Application;
// import javafx.application.Application.Parameters;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import logic.Converter;
import logic.BinaryConverter;
import logic.HexConverter;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GUI extends Application {

	// I guess these could as well come from a settings file
	// if you really wanna be cool programmer!
	private static int WIDTH = 400;
	private static int HEIGHT = 300;

	private int currentConverter = 0;
	private long decimalNumber = 0;

	@Override
	public void start(Stage window) {

		Parameters params = getParameters();

		List<Converter> converters = new ArrayList<>();
		// add converters
		converters.add(new BinaryConverter());
		converters.add(new HexConverter());

		// create a menu to choose the converter from
		ObservableList<String> converterNames = FXCollections.observableArrayList();
		for (Converter c : converters) {
			converterNames.add(c.getName());
		}
		ChoiceBox<String> converterChoiceBox = new ChoiceBox<String>(converterNames);
		converterChoiceBox.setValue(converterNames.get(0));

		// and the inputs
		// ... for decimal numbers
		Label decimalLabel = new Label("Decimal");
		TextField decimalTextField = new TextField();
		decimalTextField.setText(String.valueOf(this.decimalNumber));
		VBox decimalBox = new VBox();
		decimalBox.setAlignment(Pos.CENTER_LEFT);
		decimalBox.getChildren().setAll(decimalLabel, decimalTextField);
		// ... for the buttons
		Button convertToButton = new Button("->");
		Button convertFromButton = new Button("<-");
		VBox buttons = new VBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.getChildren().addAll(convertToButton, convertFromButton);
		// ... for the number in other format
		TextField otherFormatTextField = new TextField();
		VBox otherFormatBox = new VBox();
		otherFormatBox.setAlignment(Pos.CENTER_RIGHT);
		otherFormatBox.getChildren().setAll(converterChoiceBox, otherFormatTextField);

		// HBox where we add these inputs
		HBox inputs = new HBox();
		inputs.setAlignment(Pos.BOTTOM_CENTER);
		inputs.getChildren().addAll(decimalBox, buttons, otherFormatBox);

		// Create title on top and add BorderPane to center the actual content
		Label title = new Label(params.getNamed().get("title"));
		BorderPane componentGroup = new BorderPane();
		// Preferred window size
		componentGroup.setPrefSize(WIDTH, HEIGHT);
		BorderPane.setAlignment(title, Pos.TOP_CENTER);
		componentGroup.setTop(title);
		componentGroup.setCenter(inputs);

		Scene scene = new Scene(componentGroup);

		window.setScene(scene);
		window.show();
	}

}
