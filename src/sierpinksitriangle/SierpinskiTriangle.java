package sierpinksitriangle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sierpinksitriangle.view.TrianglePane;

import java.io.IOException;

public class SierpinskiTriangle extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		TrianglePane pane =  new TrianglePane();
		TextField order = new TextField();
		order.setOnAction(e-> pane.setOrder(Integer.parseInt(order.getText())));
		
		order.setPrefColumnCount(4);
		order.setAlignment(Pos.BOTTOM_CENTER);
		
		HBox hbox = new HBox(10);
		hbox.getChildren().addAll(new Label("Enter an order: "),order);
		hbox.setAlignment(Pos.CENTER);
		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		borderPane.setBottom(hbox);
		
		MenuBar menuBar = new MenuBar();
		Menu file = new Menu("_File");
		
		MenuItem save = new MenuItem("_Save");
		MenuItem exit = new MenuItem("_Exit");
		save.setOnAction(e->{
			try {
				pane.save();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		exit.setOnAction(e->Platform.exit());
		
		file.getItems().addAll(save,exit);
		menuBar.getMenus().add(file);
		
		borderPane.setTop(menuBar);
		
		
		Scene scene = new Scene(borderPane,200,210);
		primaryStage.setTitle("Sierpinski Triangle");
		primaryStage.setScene(scene);
		primaryStage.show();
		scene.getStylesheets().add("/style.css");
		pane.widthProperty().addListener(ov -> pane.paint());
		pane.heightProperty().addListener(ov-> pane.paint());
	}

}
