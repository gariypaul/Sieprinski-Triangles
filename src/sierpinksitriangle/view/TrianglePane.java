package sierpinksitriangle.view;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Point2D;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.FileChooser;

public class TrianglePane extends Pane {
	private int order =0;
	
	public TrianglePane() {
		
	}
	
	public void setOrder(int order) {
		this.order=order;
		this.getStyleClass().add("bg-black-style");
		paint();
		
	}
	public void paint() {
		Point2D p1 = new Point2D(getWidth()/2,10);
		Point2D p2 = new Point2D(10,getHeight()-10);
		Point2D p3 = new Point2D(getWidth()-10,getHeight()-10);
		
		this.getChildren().clear();
		displayTriangles(order,p1,p2,p3);
	}
	
	public void displayTriangles(int order, Point2D p1, Point2D p2, Point2D p3) {
		
		if(order == 0) {
			Polygon triangle = new Polygon();
			triangle.getPoints().addAll(p1.getX(),p1.getY(),p2.getX(),p2.getY(),p3.getX(),p3.getY());
			triangle.setStroke(Color.ORANGE);
			triangle.setFill(Color.BEIGE);
			
			this.getChildren().add(triangle);
		}
		else {
			Point2D p12 = p1.midpoint(p2);
			Point2D p23 = p2.midpoint(p3);
			Point2D p31 = p3.midpoint(p1);
			
			displayTriangles(order -1,p1,p12,p31);
			displayTriangles(order-1,p12,p2,p23);
			displayTriangles(order-1,p31,p23,p3);
		}
	}

	public void save() throws IOException{
		FileChooser selector = new FileChooser();
		selector.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)","*.png"));
		
		File file = selector.showSaveDialog(null);
		
		if(file != null){
	        try {
	            //Pad the capture area
	            WritableImage writableImage = new WritableImage((int)getWidth() + 20,
	                    (int)getHeight() + 20);
	            snapshot(null, writableImage);
	            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
	            //Write the snapshot to the chosen file
	            ImageIO.write(renderedImage, "png", file);
	        } catch (IOException ex) { ex.printStackTrace(); }
	    }

		
	}
}
