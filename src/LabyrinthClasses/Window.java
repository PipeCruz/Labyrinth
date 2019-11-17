package LabyrinthClasses;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;



public class Window {
	

	JLabel[][] arr = new JLabel[15][28];
	boolean blind = false;
	boolean scramble = false;
	boolean kelton = false;
	public void setBlind() { blind = true; }
	public void deBlind() { blind = false; }
	public void setScram() { scramble = true; }
	public void deScram() { scramble = false; }
	public void keltonfy() { kelton = true; }
	public void dekeltonfy() { kelton = false; }
	
	
	public Window(AbstractAction n, AbstractAction s, AbstractAction e, AbstractAction Wall, MouseListener ex, MouseListener h) {
		
		
		JFrame obj = new JFrame();
		obj.setVisible(true);
		int something = 960;
		
//		obj.setMaximumSize(new Dimension(something,(int)(something*15.0/20.0)));
//		obj.setMinimumSize(new Dimension(something,(int)(something*15.0/20.0)));
		
		
		obj.getContentPane().setLayout(new BoxLayout(obj.getContentPane(),BoxLayout.Y_AXIS));
		
			JPanel maze = new JPanel(new GridLayout(15,28));
			maze.setFocusable(false);
			
			maze.setMaximumSize(new Dimension(something,(int)(something*15.0/28.0)));
			maze.setMinimumSize(new Dimension(something,(int)(something*15.0/28.0)));
			obj.add(maze);
			maze.setPreferredSize(new Dimension(something,(int)(something*15.0/28.0)));
			
			
				for(int r = 0; r < arr.length; r++) {
					for(int c = 0; c < arr[0].length; c++) {
						arr[r][c] = new JLabel();
						arr[r][c].setMaximumSize(new Dimension(40,40));
						arr[r][c].setMinimumSize(new Dimension(40,40));
						maze.add(arr[r][c]);
						arr[r][c].setFocusable(false);
			
					}
				}
		
				
		
		JPanel button = new JPanel();
		button.setMaximumSize(new Dimension(100,100));
		button.setMinimumSize(new Dimension(100,100));
		button.setPreferredSize(new Dimension(100,100));
		button.setLayout(new BoxLayout(button, BoxLayout.Y_AXIS));
		
			JButton exit = new JButton("Exit");
			exit.setAlignmentX(Component.CENTER_ALIGNMENT);
			button.add(exit);
			exit.setBorderPainted(true);
			exit.setBackground(Color.RED);
			exit.addMouseListener(ex);
			JButton help = new JButton("help");
			help.setAlignmentX(Component.CENTER_ALIGNMENT);
			button.add(help);
			help.addMouseListener(h);
		obj.add(button);
		obj.pack();
		maze.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke("W"), "up");
		maze.getActionMap().put("up",n); 
		
		maze.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke("S"), "down");
		maze.getActionMap().put("down",s); 
		
		maze.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke("D"), "right");
		maze.getActionMap().put("right",e); 
		
		maze.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke("A"), "left");
		maze.getActionMap().put("left", Wall);

	
		}
	private Icon Wall = new ImageIcon(getClass().getResource("stoneWall.jpg"));
	private Icon Path = new ImageIcon(getClass().getResource("stonePath.jpg"));
	private Icon Doge = new ImageIcon(getClass().getResource("dogeHead.png"));
	private Icon Trevor = new ImageIcon(getClass().getResource("trevor.png"));
	public void updateMap1(Player p, Maze z) {
			//_Maze = new JPanel(new GridLayout(15,20));
		
			for(int r = 0; r < z.getMaze().length; r++) {
				for(int c = 0; c < z.getMaze()[0].length; c++) {
					
					if(blind) {
						arr[r][c].setSize(new Dimension(40,40));
						(arr[r][c]).setIcon(Trevor);
						if(r == p.getPosition().getRow() && 
								c == p.getPosition().getCol()&&
								p.isAlive()) {
							(arr[r][c]).setIcon(Doge);
						}
					}
					
					else if(scramble) {
						arr[r][c].setSize(new Dimension(40,40));
//						(arr[r][c]).setIcon(pics.get((int) (Math.random()*2)));
						if(r == p.getPosition().getRow() && 
								c == p.getPosition().getCol()&&
								p.isAlive()) {
							(arr[r][c]).setIcon(Doge);
						}
					}
					else if(kelton) {
						arr[r][c].setSize(new Dimension(20,20));
						if(z.getMaze()[r][c]) {
							(arr[r][c]).setIcon(Wall);
						}
						else if(!z.getMaze()[r][c]) {
							(arr[r][c]).setIcon(Path);
						}
						if(r == p.getPosition().getRow() && 
								c == p.getPosition().getCol()&&
								p.isAlive()) {
							arr[r][c].setSize(new Dimension(40,40));
							(arr[r][c]).setIcon(Doge);
						}
					} else if(z.getMaze()[r][c]) {
						arr[r][c].setSize(new Dimension(40,40));
						(arr[r][c]).setIcon(Wall);
					}else if(r == p.getPosition().getRow() &&
							c == p.getPosition().getCol()&&
							p.isAlive()) {
						arr[r][c].setSize(new Dimension(40,40));
						(arr[r][c]).setIcon(Doge);
					}else {
						arr[r][c].setSize(new Dimension(40,40));
						(arr[r][c]).setIcon(Path);
						
					}
				
				}
			
			}
		}
	
public void msgI(int msg) {
		
		JOptionPane.showMessageDialog(null, msg);
	}
	
	public String in(String msg) {
		return JOptionPane.showInputDialog(msg);
	}
	
	public int option(String[] options, JPanel jPanel) {
		return JOptionPane.showOptionDialog(
				null, 
				jPanel, // my message
                "Click a button", // dialog box title
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, 
                options, // possible options
                options[0]); // default option
	}
	
	public int option2(String[] options, JLabel jLabel) {
		return JOptionPane.showOptionDialog(
				null, 
				jLabel, // my message
                "Click a button", // dialog box title
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, 
                options, // possible options
                options[0]); // default option
	}
	
	public static void println(String msg) {
		//System.out.println(msg);
	}
	
	public static void print(String msg) {
		System.out.println(msg);
	}

	public void msgS(String s) {
		System.out.println(s);
	}
}

