package _05_Pixel_Art;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PixelArtMaker implements MouseListener, ActionListener{
    private JFrame window;
    private GridInputPanel gip;
    public GridPanel gp;
    ColorSelectionPanel csp;
    public JButton save;
    public boolean load;

    public void start() {
    	int response = JOptionPane.showOptionDialog(null, "Would you like to load the previously saved file?", "Load?", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION, null, null, null);
    	if(response==0) {
    		load= true;
    		System.out.println("Loading...");
            gip = new GridInputPanel(this);	
            window = new JFrame("Pixel Art");
            window.setLayout(new FlowLayout());
            window.setResizable(false);
            save = new JButton("SAVE");
    save.addActionListener(this);
            window.add(gip);
            window.add(save);
            window.pack();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        	try {
        		BufferedReader br = new BufferedReader(new FileReader("src/_05_Pixel_Art/SavedPixelArt.txt"));
String line = br.readLine();
int wi = Integer.parseInt(line);
line = br.readLine();
int he = Integer.parseInt(line);
line = br.readLine();
line = br.readLine();
line = br.readLine();
int ro = Integer.parseInt(line);
line = br.readLine();
int co = Integer.parseInt(line);
  this.submitGridData(wi, he, ro, co);      			
        		br.close();
        	} catch (FileNotFoundException e1) {
        		// TODO Auto-generated catch block
        		e1.printStackTrace();
        	} catch (IOException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
//        	this.submitGridData(wi, he, ro, co);
    	}else {
    		load=false;
    		System.out.println("Not loading...");
        gip = new GridInputPanel(this);	
        this.window = new JFrame("Pixel Art");
        window.setLayout(new FlowLayout());
        window.setResizable(false);
        save = new JButton("SAVE");
save.addActionListener(this);
        window.add(gip);
        window.add(save);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

    	}
        
    }

    
    public boolean getLoad() {
    	return load;
    }
    public void submitGridData(int w, int h, int r, int c) {
        gp = new GridPanel(w, h, r, c);

        window.remove(gip);
        window.add(gp);
        gp.repaint();
        if(load==true) {
        	gp.load();
        }
        gp.addMouseListener(this);
        csp = new ColorSelectionPanel();
        window.add(csp);
        window.pack();
    }

    public static void main(String[] args) {
        new PixelArtMaker().start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gp.setColor(csp.getSelectedColor());
        System.out.println(csp.getSelectedColor());
        gp.clickPixel(e.getX(), e.getY());
        gp.repaint();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==save) {
			gp.save();
		}
	}
}
