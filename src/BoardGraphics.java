import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import States.ChessBoard;


public class BoardGraphics {
	private JFrame frame;
	private JPanel boardPanel;
	
	public BoardGraphics(){
		createWindow();
	}
	
	private void createWindow(){
		frame = new JFrame("K-Queens");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
        GridLayout g = new GridLayout(8,8);
        g.setHgap(2);
        g.setVgap(2);
		boardPanel = new JPanel(g);
		JPanel layout = new JPanel(new GridBagLayout());
		layout.add(boardPanel);
		
		frame.add(layout);
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		
		updateBoard(null);
        frame.pack();
        //Display the window.
        frame.setVisible(true);
		
	}
	
	public void updatePiece(boolean i, int x, int y){
		JLabel t = (JLabel) boardPanel.getComponent(x+y*4);
			try {		
				BufferedImage b;
				
				if (i)
					b = ImageIO.read(new File("./Resources/queen.png"));
				else
					b = ImageIO.read(new File("./Resources/notqueen.png"));
				
				ImageIcon j = new ImageIcon(b);
				t.setIcon(j);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
		}
	}
	
	public void updateBoard(ChessBoard b){
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8;j++)
					updatePiece(b.getPiece(i, j),i,j);
		
		
	}
	

}
