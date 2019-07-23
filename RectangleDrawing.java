import java.util.*;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;


public class RectangleDrawing extends Application {
	
	private RadioButton purpleRadioButton, greenRadioButton, blueRadioButton, thinBorderRadioButton, thickBorderRadioButton, rectangleRadioButton, circleRadioButton;
	private CheckBox fillCheckBox;
	private Button clearButton;
	
	private Rectangle rectangle;
	private Circle circle;
	private Boolean shapeBeingDrawn;
	
	private Pane drawPane;
	
	private double centerX , centerY;
	
	public void start(Stage primaryStage) {
		shapeBeingDrawn = false;
		
		VBox primaryBox = new VBox();
		primaryBox.setAlignment(Pos.CENTER);
		primaryBox.setSpacing(10);
		
		drawPane = new Pane();
		drawPane.setOnMouseClicked(this::handleMouseClicks);
		drawPane.setOnMouseMoved(this::handleMouseMotion);
		
		drawPane.setMinSize(500, 500);
		drawPane.setStyle("-fx-background-color: white");
		primaryBox.getChildren().add(drawPane);
		
		rectangleRadioButton = new RadioButton("Draw Rectangle");
		circleRadioButton = new RadioButton("Draw Circle");
		
		ToggleGroup groupShape = new ToggleGroup();
		rectangleRadioButton.setToggleGroup(groupShape);
		circleRadioButton.setToggleGroup(groupShape);
		rectangleRadioButton.setSelected(true);
		
		HBox shapeBox = new HBox(rectangleRadioButton, circleRadioButton);
		shapeBox.setAlignment(Pos.CENTER);
		shapeBox.setSpacing(10);
		primaryBox.getChildren().add(shapeBox);
		
		purpleRadioButton = new RadioButton("Purple");
		greenRadioButton = new RadioButton("Green");
		blueRadioButton = new RadioButton("Blue");
		ToggleGroup groupColor = new ToggleGroup();
		purpleRadioButton.setToggleGroup(groupColor);
		greenRadioButton.setToggleGroup(groupColor);
		blueRadioButton.setToggleGroup(groupColor);
		purpleRadioButton.setSelected(true);
		
		HBox colorBox = new HBox(purpleRadioButton, greenRadioButton, blueRadioButton);
		colorBox.setAlignment(Pos.CENTER);
		colorBox.setSpacing(10);
		primaryBox.getChildren().add(colorBox);
		
		thinBorderRadioButton = new RadioButton("Thin Border");
		thickBorderRadioButton = new RadioButton("ThickBorder");
		ToggleGroup groupBorder = new ToggleGroup();
		thinBorderRadioButton.setToggleGroup(groupBorder);
		thickBorderRadioButton.setToggleGroup(groupBorder);
		thinBorderRadioButton.setSelected(true);	
		fillCheckBox = new CheckBox("Fill");
		
		HBox borderAndFillBox = new HBox(thinBorderRadioButton, thickBorderRadioButton, fillCheckBox);
		borderAndFillBox.setAlignment(Pos.CENTER);
		borderAndFillBox.setSpacing(10);
		primaryBox.getChildren().add(borderAndFillBox);
		
		clearButton = new Button("Clear");
		clearButton.setAlignment(Pos.CENTER);
		
		clearButton.setOnAction(this::handleButton);
		
		primaryBox.getChildren().add(clearButton);
		
		Scene scene = new Scene(primaryBox, 700, 700, Color.WHITESMOKE);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Draw Rectangles or Circles!");
		primaryStage.show();
		
	}
	
	private void handleMouseClicks(MouseEvent event) {
		
		if (!shapeBeingDrawn) {
			
			shapeBeingDrawn = true;
			 centerX = event.getX();
			 centerY = event.getY();
			 
			 if(rectangleRadioButton.isSelected()) {
				 rectangle = new Rectangle(centerX,centerY,0,0); 
				 

					if(thinBorderRadioButton.isSelected()) {
						rectangle.setStrokeWidth(2);
					}
					else {
						rectangle.setStrokeWidth(5);
					}
				
					if(!fillCheckBox.isSelected()) {
						if(purpleRadioButton.isSelected()) {
							rectangle.setStroke(Color.PURPLE);
							rectangle.setFill(Color.TRANSPARENT);
						}
						else if(greenRadioButton.isSelected()) {
							rectangle.setStroke(Color.GREEN);
							rectangle.setFill(Color.TRANSPARENT);
						}
						else if(blueRadioButton.isSelected()) {
							rectangle.setStroke(Color.BLUE);
							rectangle.setFill(Color.TRANSPARENT);
						}
					}
					else {
						if(purpleRadioButton.isSelected()) {
							rectangle.setFill(Color.PURPLE);
							rectangle.setStroke(Color.PURPLE);
						}
						else if(greenRadioButton.isSelected()) {
							rectangle.setFill(Color.GREEN);
							rectangle.setStroke(Color.GREEN);
						}
						else if(blueRadioButton.isSelected()) {
							rectangle.setFill(Color.BLUE);
							rectangle.setStroke(Color.BLUE);
						}
					}
					drawPane.getChildren().add(rectangle);
				 
			 }
			 else if (circleRadioButton.isSelected()) {
				
				 circle = new Circle(centerX, centerY, 10);
				 
					if(thinBorderRadioButton.isSelected()) {
						circle.setStrokeWidth(2);
					}
					else {
						circle.setStrokeWidth(5);
					}
				
					if(!fillCheckBox.isSelected()) {
						if(purpleRadioButton.isSelected()) {
							circle.setStroke(Color.PURPLE);
							circle.setFill(Color.TRANSPARENT);
						}
						else if(greenRadioButton.isSelected()) {
							circle.setStroke(Color.GREEN);
							circle.setFill(Color.TRANSPARENT);
						}
						else if(blueRadioButton.isSelected()) {
							circle.setStroke(Color.BLUE);
							circle.setFill(Color.TRANSPARENT);
						}
					}
					else {
						if(purpleRadioButton.isSelected()) {
							circle.setFill(Color.PURPLE);
							circle.setStroke(Color.PURPLE);
						}
						else if(greenRadioButton.isSelected()) {
							circle.setFill(Color.GREEN);
							circle.setStroke(Color.GREEN);
						}
						else if(blueRadioButton.isSelected()) {
							circle.setFill(Color.BLUE);
							circle.setStroke(Color.BLUE);
						}
					}
					drawPane.getChildren().add(circle);
			 }	
		}
		else {
			shapeBeingDrawn = false;
		}
	}
	
	private void handleMouseMotion(MouseEvent event) {
		if(shapeBeingDrawn) {
			
			if(rectangleRadioButton.isSelected()) {
					double width , height;
					 width = Math.abs(event.getX() - centerX)   ;
					 height = Math.abs(event.getY() - centerY);
					
					
					if(event.getX() >= centerX && event.getY() >= centerY) {
						
						rectangle.setWidth(width);
						rectangle.setHeight(height);
					}
					else if (event.getX() < centerX && event.getY() >= centerY) {
						rectangle.setX(event.getX());
						rectangle.setWidth(width);
						rectangle.setHeight(height);
						
					}
					else if (event.getX() >= centerX && event.getY() < centerY) {
						rectangle.setY(event.getY());
						rectangle.setWidth(width);
						rectangle.setHeight(height);
					}
					else {
						rectangle.setX(event.getX());
						rectangle.setY(event.getY());
						rectangle.setWidth(width);
						rectangle.setHeight(height);
					}
					
			}
			else if (circleRadioButton.isSelected()){
				double rX , rY, radius;
				rX = Math.abs(event.getX() - centerX)   ;
				rY = Math.abs(event.getY() - centerY);
				radius =Math.sqrt( Math.pow(rX, 2) + Math.pow(rY, 2));
				circle.setRadius(radius);
					
			}
		}
	}
	
	private void handleButton(ActionEvent event) {
		int lastElementIndex = drawPane.getChildren().size() - 1;
		
		while (lastElementIndex >= 0) {
			drawPane.getChildren().remove(lastElementIndex);
			lastElementIndex--;
		}
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
