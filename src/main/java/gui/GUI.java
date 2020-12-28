package gui;

import javafx.application.Application;
import javafx.application.Application.Parameters;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import logic.Converter;
import logic.BinaryConverter;
import java.util.ArrayList;
import java.util.List;

public class GUI extends Application {

	@Override
	public void start(Stage window) {
	
		Parameters params = getParameters();
		
		List<Converter> converters = new ArrayList<>();
		converters.add(new BinaryConverter());

		Label text = new Label("Hello World!");
		VBox mainComponents = new VBox();
		mainComponents.setAlignment(Pos.CENTER);
		mainComponents.getChildren().add(text);

		Label title = new Label(params.getNamed().get("title"));
		BorderPane componentGroup = new BorderPane();
		componentGroup.setPrefSize(400, 300);
		componentGroup.setAlignment(title, Pos.TOP_CENTER);
		componentGroup.setTop(title);
		componentGroup.setCenter(mainComponents);

		Scene scene = new Scene(componentGroup);

		window.setScene(scene);
		window.show();
	}

}
