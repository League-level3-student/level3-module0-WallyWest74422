package _05_Pixel_Art;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JPanel;
public class GridPanel extends JPanel{

    private static final long serialVersionUID = 1L;
    public int windowWidth;
    public int windowHeight;
    private int pixelWidth;
    private int pixelHeight;
    public int rows;
    public int cols;  
    private boolean loaded;
    // 1. Create a 2D array of pixels. Do not initialize it yet.
Pixel[][] pixels;
    private Color color;
    

    public GridPanel(int w, int h, int r, int c) {
        this.windowWidth = w;
        this.windowHeight = h;
        this.rows = r;
        this.cols = c;

        this.pixelWidth = windowWidth / cols;
        this.pixelHeight = windowHeight / rows;

        color = Color.BLACK;

        setPreferredSize(new Dimension(windowWidth, windowHeight));
loaded = false;

        
        // 2. Initialize the pixel array using the rows and cols variables.
pixels = new Pixel[rows][cols];

        // 3. Iterate through the array and initialize each element to a new pixel.
for(int i = 0; i < pixels.length; i++) {
	  for(int j = 0; j < pixels[i].length; j++) {
        pixels[i][j]= new Pixel(i*pixelWidth,j*pixelHeight);
        
    }
  }


    }

    public void setColor(Color c) {
        color = c;
    }

    public void clickPixel(int mouseX, int mouseY) {
        // 5. Use the mouseX and mouseY variables to change the color
        //    of the pixel that was clicked. *HINT* Use the pixel's dimensions.
pixels[mouseX/pixelWidth][mouseY/pixelHeight].color=color;        
    }

    @Override
    public void paintComponent(Graphics g) {
    	PixelArtMaker pam = new PixelArtMaker();
    	
        // 4. Iterate through the array.
        //    For every pixel in the list, fill in a rectangle using the pixel's color.
        //    Then, use drawRect to add a grid pattern to your display.

    	  for(int i = 0; i < pixels.length; i++) {
        	  for(int j = 0; j < pixels[i].length; j++) {
                  g.setColor(pixels[i][j].color);
                g.fillRect(pixels[i][j].x, pixels[i][j].y, pixelWidth,pixelHeight);
                g.setColor(color.BLACK);
                g.drawRect(pixels[i][j].x, pixels[i][j].y, pixelWidth, pixelHeight);
              }
    	  }

    }

    public void load() {
    	System.out.println("Attempting to load previous file.");
    
    		System.out.println("Loading previous file");
    	try {
    		BufferedReader br = new BufferedReader(new FileReader("src/_05_Pixel_Art/SavedPixelArt.txt"));
    		
    		String line = br.readLine();
    		if(line!=null&& !loaded) {
this.windowWidth = Integer.parseInt(line);
System.out.println("Changed ww...");
line = br.readLine();
this.windowHeight = Integer.parseInt(line);
System.out.println("Changed wh...");
line = br.readLine();
this.pixelWidth = Integer.parseInt(line);
System.out.println("Changed pw...");
line = br.readLine();
this.pixelHeight = Integer.parseInt(line);
System.out.println("Changed ph...");
line = br.readLine();
this.rows = Integer.parseInt(line);
System.out.println("Changed rows...");
line = br.readLine();
this.cols = Integer.parseInt(line);
System.out.println("Changed cols...");
line = br.readLine();
pixels = new Pixel[rows][cols];

for(int i = 0; i < pixels.length; i++) {
for(int j = 0; j < pixels[i].length; j++) {
pixels[i][j]= new Pixel(i*pixelWidth,j*pixelHeight);
String[] values = line.split(" ");
int re = Integer.parseInt(values[0]);
int bl = Integer.parseInt(values[1]);
int gr = Integer.parseInt(values[2]);
System.out.println(re + " "+bl+ " "+gr);
Color colores = new Color(re, gr, bl);
System.out.println("Creating color.");
pixels[i][j].color = colores;
line = br.readLine();
}
}
loaded = true;
    		} 		
    		br.close();
    	} catch (FileNotFoundException e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    }
    
    public void save() {
  	  try {
				FileWriter fw = new FileWriter("src/_05_Pixel_Art/SavedPixelArt.txt");
				fw.write(this.windowWidth+ "\n");
				fw.write(this.windowHeight+ "\n");
				fw.write(this.pixelWidth+ "\n");
				fw.write(this.pixelHeight+ "\n");
				fw.write(this.rows + "\n");
				fw.write(this.cols+ "\n");
		    	  for(int i = 0; i < pixels.length; i++) {
		        	  for(int j = 0; j < pixels[i].length; j++) {
		      
fw.write(pixels[i][j].color.getRed()+ " " +pixels[i][j].color.getBlue()+ " "+ pixels[i][j].color.getGreen() + "\n");

		              }
		            }					 
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    }
}
