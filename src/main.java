
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

public class main extends JPanel{
	
	static int x = 1280;
	static int y = 720;
	int current_position = 0; 
	int dx, dy, previous_x, previous_y;
	boolean moving = false; 
	
	String local_piece_name = ""; 
	
	String target = ""; 
	String target_color = ""; 
	int target_key = 0;
	boolean targetExist = false;  // used for the piece you're moving onto
	boolean targetExist2 = false; // used for in between 
	int found = 0;
	
	static double VERSION = 0.1;   
	static JFrame frame = new JFrame();  
	static JPanel panel = new JPanel();
	static JPanel panel2 = new JPanel();
	Rectangle rec1 = new Rectangle();
	Rectangle rec2 = new Rectangle();
	
	int[][] board_position = new int[][]{
		  { 0, 1, 2, 3, 4, 5, 6, 7},
		  { 8, 9, 10, 11, 12, 13, 14, 15},
		  { 16, 17, 18, 19, 20, 21, 22, 23},
		  { 24, 25, 26, 27, 28, 29, 30, 31},
		  { 32, 33, 34, 35, 36, 37, 38, 39},
		  { 40, 41, 42, 43, 44, 45, 46, 47},
		  { 48, 49, 50, 51, 52, 53, 54, 55},
		  { 56, 57, 58, 59, 60, 61, 62, 63}
		};
		
	static JLabel Piece[] = new JLabel[32];
	JLabel Tile[][] = new JLabel[8][8]; 
	
	static LinkedList<Pieces> Pieces = new LinkedList<Pieces>();
	static HashMap<String, Integer> piecePosition = new HashMap<>();
    Set set = piecePosition.entrySet();
    Iterator iterator = set.iterator(); 
    
	public main() {

		frame.setSize(x, y);
		frame.setTitle("Baha Chess");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(panel2);
		frame.add(panel);
		frame.setLayout(null);
		panel2.setLayout(null);
		panel2.setSize(x,y);
		panel2.setBackground(new Color(0, 0, 0, 0));
		panel2.setOpaque(false);
		panel.setLayout(new GridLayout(8, 8));
		panel.setSize(new Dimension(540, 540));
		panel.setLocation(400, 0);
		
		//CreatePieces();
		
		BufferedImage white = null;
		BufferedImage black = null; 
		BufferedImage wpawn = null; 
		BufferedImage wbishop = null;
		BufferedImage wknightL = null;
		BufferedImage wknightR = null; 
		BufferedImage wqueen = null; 
		BufferedImage wking = null;
		BufferedImage wrook = null;
		BufferedImage bpawn = null; 
		BufferedImage bbishop = null;
		BufferedImage bknightL = null;
		BufferedImage bknightR = null; 
		BufferedImage bqueen = null; 
		BufferedImage bking = null;
		BufferedImage brook = null;
		
		try {
			System.out.print("Fetching Images...");
			white = ImageIO.read(this.getClass().getResource("white.png"));
			black = ImageIO.read(this.getClass().getResource("black.png"));
			wpawn = ImageIO.read(this.getClass().getResource("wPawn.png"));
			wbishop = ImageIO.read(this.getClass().getResource("wBishop.png"));
			wknightL = ImageIO.read(this.getClass().getResource("wKnightLeft.png"));
			wknightR = ImageIO.read(this.getClass().getResource("wKnightRight.png"));
			wqueen = ImageIO.read(this.getClass().getResource("wQueen.png"));
			wking = ImageIO.read(this.getClass().getResource("wKing.png"));
			wrook = ImageIO.read(this.getClass().getResource("wRook.png"));
			bpawn = ImageIO.read(this.getClass().getResource("bPawn.png"));
			bbishop = ImageIO.read(this.getClass().getResource("bBishop.png"));
			bknightL = ImageIO.read(this.getClass().getResource("bKnightLeft.png"));
			bknightR = ImageIO.read(this.getClass().getResource("bKnightRight.png"));
			bqueen = ImageIO.read(this.getClass().getResource("bQueen.png"));
			bking = ImageIO.read(this.getClass().getResource("bKing.png"));
			brook = ImageIO.read(this.getClass().getResource("bRook.png"));
			System.out.println("Done!");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int posx = 380;
		int posy = 70; 
		int id = 0;
		for (int i = 0; i < 8; i++) {
			
			Piece[i] = new JLabel(); 
		    Piece[i].setIcon(new ImageIcon(wpawn));
		    Piece[i].setLocation(posx, posy);
		    Piece[i].setSize(64, 64);
		    Piece[i].setName("wPawn" + i);
		    id = i; 
		    posx = posx + 67; 
		}
		
		Piece[8] = new JLabel();
	    Piece[8].setIcon(new ImageIcon(wrook));
	    Piece[8].setLocation(380, 0);
	    Piece[8].setSize(64, 64);
	    Piece[8].setName("wRook" + 1);
	    	    
		Piece[9] = new JLabel();
	    Piece[9].setIcon(new ImageIcon(wknightL));
	    Piece[9].setLocation(67 + 380, 0);
	    Piece[9].setSize(64, 64);
	    Piece[9].setName("wKnight" + 1);
	    
		Piece[10] = new JLabel();
	    Piece[10].setIcon(new ImageIcon(wbishop));
	    Piece[10].setLocation(67 * 2 + 380, 0);
	    Piece[10].setSize(64, 64);
	    Piece[10].setName("wBishop" + 1);
	    
		Piece[11] = new JLabel();
	    Piece[11].setIcon(new ImageIcon(wking));
	    Piece[11].setLocation(67*3 + 380, 0);
	    Piece[11].setSize(64, 64);
	    Piece[11].setName("wking");
	    
		Piece[12] = new JLabel();
	    Piece[12].setIcon(new ImageIcon(wqueen));
	    Piece[12].setLocation(67*4 + 380, 0);
	    Piece[12].setSize(64, 64);
	    Piece[12].setName("wQueen");
	    
		Piece[13] = new JLabel();
	    Piece[13].setIcon(new ImageIcon(wbishop));
	    Piece[13].setLocation(67 *5 + 380, 0);
	    Piece[13].setSize(64, 64);
	    Piece[13].setName("wBishop" + 2);
	    
		Piece[14] = new JLabel();
	    Piece[14].setIcon(new ImageIcon(wknightR));
	    Piece[14].setLocation(67*6 + 380, 0);
	    Piece[14].setSize(64, 64);
	    Piece[14].setName("wKnight" + 2);
	    
		Piece[15] = new JLabel();
	    Piece[15].setIcon(new ImageIcon(wrook));
	    Piece[15].setLocation(67*7 + 380, 0);
	    Piece[15].setSize(64, 64);
	    Piece[15].setName("wRook" + 2);
		
		System.out.print("Creating Pieces...");
		int black_posy = 400; 
		posx = 380; 
		for (int i = 16; i < 24; i++) {
			
			Piece[i] = new JLabel(); 
		    Piece[i].setIcon(new ImageIcon(bpawn));
		    Piece[i].setLocation(posx, black_posy);
		    Piece[i].setSize(64, 64);
		    Piece[i].setName("bPawn" + i);
		    id = i; 
		    posx = posx + 67; 
		}
		
		Piece[24] = new JLabel();
	    Piece[24].setIcon(new ImageIcon(brook));
	    Piece[24].setLocation(380, 470);
	    Piece[24].setSize(64, 64);
	    Piece[24].setName("bRook" + 1);
	    
		Piece[25] = new JLabel();
	    Piece[25].setIcon(new ImageIcon(bknightL));
	    Piece[25].setLocation(67 + 380, 470);
	    Piece[25].setSize(64, 64);
	    Piece[25].setName("bKnight" + 1);
	    
		Piece[26] = new JLabel();
	    Piece[26].setIcon(new ImageIcon(bbishop));
	    Piece[26].setLocation(67 * 2 + 380, 470);
	    Piece[26].setSize(64, 64);
	    Piece[26].setName("bBishop" + 1);
	    
		Piece[27] = new JLabel();
	    Piece[27].setIcon(new ImageIcon(bking));
	    Piece[27].setLocation(67*3 + 380, 470);
	    Piece[27].setSize(64, 64);
	    Piece[27].setName("bking");
	    
		Piece[28] = new JLabel();
	    Piece[28].setIcon(new ImageIcon(bqueen));
	    Piece[28].setLocation(67*4 + 380, 470);
	    Piece[28].setSize(64, 64);
	    Piece[28].setName("bQueen");
	    
		Piece[29] = new JLabel();
	    Piece[29].setIcon(new ImageIcon(bbishop));
	    Piece[29].setLocation(67 *5 + 380, 470);
	    Piece[29].setSize(64, 64);
	    Piece[29].setName("bBishop" + 2);
	    
		Piece[30] = new JLabel();
	    Piece[30].setIcon(new ImageIcon(bknightR));
	    Piece[30].setLocation(67*6 + 380, 470);
	    Piece[30].setSize(64, 64);
	    Piece[30].setName("bKnight" + 2);
	    
		Piece[31] = new JLabel();
	    Piece[31].setIcon(new ImageIcon(brook));
	    Piece[31].setLocation(67*7 + 380, 470);
	    Piece[31].setSize(64, 64);
	    Piece[31].setName("bRook" + 2);
		
		for (int i = 0; i < 32; i++) {
			panel2.add(Piece[i]); 
		    Piece[i].addMouseListener(new MouseAdapter() {
			    @Override
			    public void mousePressed(MouseEvent e) {                     
                     dx = e.getX();
                     dy = e.getY();
                     previous_x = ((JLabel)e.getSource()).getX();
                     previous_y = ((JLabel)e.getSource()).getY();
                     local_piece_name = ((JLabel)e.getSource()).getName().substring(0, 2); 
                     
			    }
			    @Override
			    public void mouseReleased(MouseEvent e) {   
			    	CheckIntersection(((JLabel)e.getSource()), ((JLabel)e.getSource()).getX(), ((JLabel)e.getSource()).getY());
			    	target_color = "";
			    	target = "";
			    	found = 0; 
			    }

			});
		    Piece[i].addMouseMotionListener(new MouseMotionAdapter() {
			    @Override
			    public void mouseDragged(MouseEvent e) {
			    	((JLabel)e.getSource()).setLocation(((JLabel)e.getSource()).getX() + e.getX() + dx - 100, ((JLabel)e.getSource()).getY() + e.getY() + dy - 70);
			    }
		    	
		    });
		}
		
		System.out.println(Piece.length + " Pieces Created!");
		 
	      for (int ii = 0; ii < 8; ii++) {
	    	  //Posy = Posy+64; 
	    	  for(int jj = 0; jj < 8; jj++) {
	    		  Tile[ii][jj] = new JLabel(); 
	    		  
	    		  //Even Columns && Even tile
	    		  if (ii % 2 == 0 && jj % 2 == 0) {
	    			  Tile[ii][jj].setName("" + board_position[ii][jj]);
	    			  Tile[ii][jj].setIcon(new ImageIcon(white));
	    		  //Even Column && Odd Tile	  
	    		  } else if (ii % 2 == 0 && jj % 2 == 1) {
	    			  Tile[ii][jj].setName("" + board_position[ii][jj]);
	    			  Tile[ii][jj].setIcon(new ImageIcon(black));
	    		  //Odd Column && Even tile	  
	    		  } else if (ii % 2 == 1 && jj % 2 == 0) {
	    			  Tile[ii][jj].setName("" + board_position[ii][jj]);
	    			  Tile[ii][jj].setIcon(new ImageIcon(black));
	    	      //Odd Column && Odd tile  		  
	    		  } else if (ii % 2 == 1 && jj % 2 == 1) {
	    			  Tile[ii][jj].setName("" + board_position[ii][jj]);
	    			  Tile[ii][jj].setIcon(new ImageIcon(white));
	    		  }
	    		  panel.add(Tile[ii][jj]);
	    	  }
	      }
		
	}
	
	public static void main(String[] args) {
		
		System.out.println("**********************************");
		System.out.println("*                                *");
		System.out.println("*    Baha Chess V" + VERSION + " *");
		System.out.println("*   created by Darren Bahadoor   *");
		System.out.println("*                                *");
		System.out.println("**********************************");
		
		System.out.print("*****************************************");
		System.out.println("");		
		new main();
		
		frame.setVisible(true);
		panel.setVisible(true);
		
		System.out.print("Setting inital positions...");
		SetInitialPosition(); 
        System.out.println(piecePosition.size() + " position created!");

	}
	
	public void CheckIntersection(JLabel piece_name, int pos_x, int pos_y) {
		int found = 0; 
		int new_x = 0;
		int new_y = 0; 
		int tileID = 0; 
        rec1.setSize(15, 15);
        rec1.setLocation(pos_x, pos_y);
		
		for (int ii = 0; ii < 8; ii++) {
			 for (int jj = 0; jj < 8; jj++) {
				 
	 	     rec2.setLocation(Tile[ii][jj].getX() + 375, Tile[ii][jj].getY() - 25);
	 	    rec2.setSize(Tile[ii][jj].getSize().width / 2 + 10, Tile[ii][jj].getSize().height /2 + 10);
	 	     
	 	     if (rec1.intersects(rec2)) {
	 	    	 
	 	         found++; 
	 	         new_x = (int) (rec2.getLocation().getX() + (rec2.getWidth() / 2) - 15);
	 	         new_y = (int ) (Tile[ii][jj].getY() +  (rec2.getHeight()/ 2) - 20);	
	 	         tileID = Integer.parseInt(Tile[ii][jj].getName());
	 	         
	 	         break; //no need to run the loop anymore. save memory 
	 	     }
	 	     
			} 
		}
		 if (found == 1) {
			 CheckPieceOnBoard(tileID); 
			 PieceMovementLogic(piece_name, tileID, new_x, new_y); 
		 } else {
			 piece_name.setLocation(previous_x, previous_y);
		 }
	}
	
	//bug : does not check its own tile
	public void CheckPieceOnBoard(int Checktile) {
		boolean yo = false; 
        for (Entry<String, Integer> entry : piecePosition.entrySet()) {
        	
            if (entry.getValue() == Checktile) {
            	target = entry.getKey(); 
            	target_color = target.substring(0, 1);
            	target_key = Checktile;
            	targetExist = true; 
            	break; 
            } else {
            	targetExist = false; 
            }
        }
	}
	
	public void CheckPieceOnBoard2(int Checktile) {
        for (Entry<String, Integer> entry : piecePosition.entrySet()) {
        	
            if (entry.getValue() == Checktile) {
            	target = entry.getKey(); 
            	found = found + 1; 
                break; //add breaks to save memory.
        }
	}
	}
	
	
public void PieceMovementLogic(JLabel piece_name, int tileID, int new_x, int new_y) {
	int valid[] = {15,17,-15,-17,10,-10,6,-6};
		//Black Pawns
if (local_piece_name.equals("bP")) {
	
		for (int i = 16; i < 24; i++) {
		 if (piece_name.getName().equals("bPawn" + i)) {
			 
			  //If moving straight pawn
	    	  if ((piecePosition.get("bPawn" + i) - 8) == tileID && targetExist == false) {
	    	  piece_name.setLocation(new_x, new_y);
	    	  piecePosition.put(piece_name.getName(), tileID); 
	    	  } 
	    	  //If diagonal & eat the piece
	    	   else if ((piecePosition.get("bPawn" + i) - 7) == tileID && targetExist == true && target_color.equals("w") || (piecePosition.get("bPawn" + i) - 9) == tileID && targetExist == true && target_color.equals("w")) {
		    	  piece_name.setLocation(new_x, new_y);
		    	  piecePosition.put(piece_name.getName(), tileID); 
		    	  targetExist = false; 
		    	  break; 
	    	  } else {
	    	  //If pond is at starting position, move double
	    		  switch(tileID) {
	    		  case 32 : 
	    			  if (piecePosition.get("bPawn" + i) == 48 && targetExist == false) {
	    		    	  piece_name.setLocation(new_x, new_y);
	    		    	  piecePosition.put(piece_name.getName(), tileID); 
	    			  } else {
	    				  piece_name.setLocation(previous_x, previous_y);
	    			  }
	    		      break; 
	    		  case 33 : 
	    			  if (piecePosition.get("bPawn" + i) == 49 && targetExist == false) {
	    		    	  piece_name.setLocation(new_x, new_y);
	    		    	  piecePosition.put(piece_name.getName(), tileID); 
	    			  } else {
	    				  piece_name.setLocation(previous_x, previous_y);
	    			  }
	    		      break;    
	    		  case 34 : 
	    			  if (piecePosition.get("bPawn" + i) == 50 && targetExist == false) {
	    		    	  piece_name.setLocation(new_x, new_y);
	    		    	  piecePosition.put(piece_name.getName(), tileID); 
	    			  } else {
	    				  piece_name.setLocation(previous_x, previous_y);
	    			  }
	    		      break;
	    		  case 35 : 
	    			  if (piecePosition.get("bPawn" + i) == 51 && targetExist == false) {
	    		    	  piece_name.setLocation(new_x, new_y);
	    		    	  piecePosition.put(piece_name.getName(), tileID); 
	    			  } else {
	    				  piece_name.setLocation(previous_x, previous_y);
	    			  }
	    		      break; 
	    		  case 36 : 
	    			  if (piecePosition.get("bPawn" + i) == 52 && targetExist == false) {
	    		    	  piece_name.setLocation(new_x, new_y);
	    		    	  piecePosition.put(piece_name.getName(), tileID); 
	    			  } else {
	    				  piece_name.setLocation(previous_x, previous_y);
	    			  }
	    		      break; 
	    		  case 37 : 
	    			  if (piecePosition.get("bPawn" + i) == 53 && targetExist == false) {
	    		    	  piece_name.setLocation(new_x, new_y);
	    		    	  piecePosition.put(piece_name.getName(), tileID); 
	    			  } else {
	    				  piece_name.setLocation(previous_x, previous_y);
	    			  }
	    		      break; 
	    		  case 38 : 
	    			  if (piecePosition.get("bPawn" + i) == 54 && targetExist == false) {
	    		    	  piece_name.setLocation(new_x, new_y);
	    		    	  piecePosition.put(piece_name.getName(), tileID); 
	    			  } else {
	    				  piece_name.setLocation(previous_x, previous_y);
	    			  }
	    		      break; 
	    		  case 39 : 
	    			  if (piecePosition.get("bPawn" + i) == 55 && targetExist == false) {
	    		    	  piece_name.setLocation(new_x, new_y);
	    		    	  piecePosition.put(piece_name.getName(), tileID); 
	    			  } else {
	    				  piece_name.setLocation(previous_x, previous_y);
	    			  }
	    		      break; 
	    	      default :
	    	        piece_name.setLocation(previous_x, previous_y);
	    	        break; 
	    		  }
	    	  }
	    	  break; //stops the loop  
		 } 
	}
}

//Black Knight

if (local_piece_name.equals("bK")) {
	//Movement
	if (piece_name.getName().equals("bKnight" + 1)) {
		
		for(int i = 0; i < valid.length; i++) {
			
			if ((piecePosition.get("bKnight" + 1) + valid[i]) == tileID) {
				if (targetExist  == true && target_color.equals("b")) {
					piece_name.setLocation(previous_x, previous_y);
				} else if (targetExist == true && target_color.equals("w")) {
					piecePosition.put(piece_name.getName(), tileID); 
					piece_name.setLocation(new_x, new_y);
					//ADD LINE TO DELETE THE TARGET
				} else if (targetExist == false) {
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
				}
				break; 
			} else {
				piece_name.setLocation(previous_x, previous_y);
			}
			
	   }
}
	
	if (piece_name.getName().equals("bKnight" + 2)) {
		
		for(int i = 0; i < valid.length; i++) {
			
			if ((piecePosition.get("bKnight" + 2) + valid[i]) == tileID) {
				if (targetExist  == true && target_color.equals("b")) {
					piece_name.setLocation(previous_x, previous_y);
				} else if (targetExist == true && target_color.equals("w")) {
					piecePosition.put(piece_name.getName(), tileID); 
					piece_name.setLocation(new_x, new_y);
					//ADD LINE TO DELETE THE TARGET
				} else if (targetExist == false) {
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
				}
				break; 
			} else {
				piece_name.setLocation(previous_x, previous_y);
			}
			
	   }
		
		
}
	

}

//Black rook 
if (local_piece_name.equals("bR")) {
	if (piece_name.getName().equals("bRook" + 1)) {
  	  int row1 = 0;
  	  int col1 = 0;
  	  int row2 = 0;
  	  int col2 = 0;
  	
	  for (int ii = 0; ii < 8; ii++) {
		  for (int jj = 0; jj < 8; jj++) {
			  if (tileID == board_position[ii][jj]) {
				  row1 = ii;
				  col1 = jj;
			  }
			  if (piecePosition.get(piece_name.getName()) == board_position[ii][jj]) {
				  row2 = ii;
				  col2 = jj;
			  }
		  }  
	  }
	  
	  if (row1 == row2 || col1 == col2) {
		  CheckInBetween(row1, col1, row2, col2, tileID, piece_name);
		  
		  if (found > 0) {
			  piece_name.setLocation(previous_x, previous_y);
			  targetExist2 = false; 
			  targetExist = false; 
		  }   else if (target_color.equals("b")) {
			  piece_name.setLocation(previous_x, previous_y);
			  targetExist2 = false; 
			  targetExist = false; 
		  }   else if (targetExist == true && target_color.equals("w") && found == 0) {
			  //ADD LINE TO TAKE AWAY PIECE
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
		  }   else if (targetExist == false && found == 0) {
			piecePosition.put(piece_name.getName(), tileID); 
			piece_name.setLocation(new_x, new_y);
		  }  
		  
	  } else {
		  piece_name.setLocation(previous_x, previous_y);
			row1 = 0;
			row2 = 0;
			col1 = 0;
			col2 = 0;
	  }
	}
	
	if (piece_name.getName().equals("bRook" + 2)) {
	  	  int row1 = 0;
	  	  int col1 = 0;
	  	  int row2 = 0;
	  	  int col2 = 0;
	  	
		  for (int ii = 0; ii < 8; ii++) {
			  for (int jj = 0; jj < 8; jj++) {
				  if (tileID == board_position[ii][jj]) {
					  row1 = ii;
					  col1 = jj;
				  }
				  if (piecePosition.get(piece_name.getName()) == board_position[ii][jj]) {
					  row2 = ii;
					  col2 = jj;
				  }
			  }  
		  }
		  
		  if (row1 == row2 || col1 == col2) {
			  CheckInBetween(row1, col1, row2, col2, tileID, piece_name);
			  
			  if (found > 0) {
				  piece_name.setLocation(previous_x, previous_y);
				  targetExist2 = false; 
				  targetExist = false; 
			  }   else if (target_color.equals("b")) {
				  piece_name.setLocation(previous_x, previous_y);
				  targetExist2 = false; 
				  targetExist = false; 
			  }   else if (targetExist == true && target_color.equals("w") && found == 0) {
				  //ADD LINE TO TAKE AWAY PIECE
					piecePosition.put(piece_name.getName(), tileID); 
					piece_name.setLocation(new_x, new_y);
			  }   else if (targetExist == false && found == 0) {
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
			  }  
			  
		  } else {
			  piece_name.setLocation(previous_x, previous_y);
				row1 = 0;
				row2 = 0;
				col1 = 0;
				col2 = 0;
		  }
		}
	
}

//black bishop 
if (local_piece_name.equals("bB")) {
	if (piece_name.getName().equals("bBishop" + 1)) { 
		
		int temp = (Math.abs(tileID - piecePosition.get("bBishop1"))); 
        int notAbstemp = ((tileID - piecePosition.get("bBishop1"))); 
		
		if (temp % 9 == 0 || temp % 7 == 0) {
			CheckDiagonalColision(tileID, piece_name, notAbstemp); 
			if (targetExist == true && target_color.equals("w") && found == 1) {
				//destroy target
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
			} else {
				if (target_color.equals("b")) {
					piece_name.setLocation(previous_x, previous_y);		
				} else if (targetExist == false && found == 0) {
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
			} else {
				piece_name.setLocation(previous_x, previous_y);	
			}
			}
		} else if (temp % 7 == 0) {
			CheckDiagonalColision(tileID, piece_name, notAbstemp); 
			if (targetExist == true && target_color.equals("w") && found == 1) {
				//destroy target
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
			} else {
				if (target_color.equals("b")) {
					piece_name.setLocation(previous_x, previous_y);		
				} else if (targetExist == false && found == 0) {
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
			} else {
				piece_name.setLocation(previous_x, previous_y);	
			}
			}
		} else {
			piece_name.setLocation(previous_x, previous_y);
		}
	}
	
	if (piece_name.getName().equals("bBishop" + 2)) { 
		
		int temp = (Math.abs(tileID - piecePosition.get("bBishop2"))); 
        int notAbstemp = ((tileID - piecePosition.get("bBishop2"))); 
		
		if (temp % 9 == 0 || temp % 7 == 0) {
			CheckDiagonalColision(tileID, piece_name, notAbstemp); 
			if (targetExist == true && target_color.equals("w") && found == 1) {
				//destroy target
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
			} else {
				if (target_color.equals("b")) {
					piece_name.setLocation(previous_x, previous_y);		
				} else if (targetExist == false && found == 0) {
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
			} else {
				piece_name.setLocation(previous_x, previous_y);	
			}
			}
		} else if (temp % 7 == 0) {
			CheckDiagonalColision(tileID, piece_name, notAbstemp); 
			if (targetExist == true && target_color.equals("w") && found == 1) {
				//destroy target
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
			} else {
				if (target_color.equals("b")) {
					piece_name.setLocation(previous_x, previous_y);		
				} else if (targetExist == false && found == 0) {
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
			} else {
				piece_name.setLocation(previous_x, previous_y);	
			}
			}
		} else {
			piece_name.setLocation(previous_x, previous_y);
		}
	}
}

//black king 
if (local_piece_name.equals("bk")) {
	if ((piecePosition.get("bking") + 8) == tileID || (piecePosition.get("bking") - 8) == tileID || (piecePosition.get("bking") + 1) == tileID || (piecePosition.get("bking") - 1) == tileID || (piecePosition.get("bking") + 9) == tileID || (piecePosition.get("bking") + 7) == tileID  || (piecePosition.get("bking") - 7) == tileID || (piecePosition.get("bking") - 9) == tileID ) {
		if (targetExist == true && target_color.equals("w")) {
			//destroy the piece
			piecePosition.put(piece_name.getName(), tileID); 
			piece_name.setLocation(new_x, new_y);
		} else if (target_color.equals("b")) {
			piece_name.setLocation(previous_x, previous_y);
		} else {
			piecePosition.put(piece_name.getName(), tileID); 
			piece_name.setLocation(new_x, new_y);
		}
		
	} else {
		piece_name.setLocation(previous_x, previous_y);
	}
}

//Black Queen
if (local_piece_name.equals("bQ")) {
	int temp = (Math.abs(tileID - piecePosition.get("bQueen"))); 
    int notAbstemp = ((tileID - piecePosition.get("bQueen"))); 
    
	int row1 = 0;
	int row2 = 0;
	int col1 = 0;
	int col2 = 0;
    
	for (int ii = 0; ii < 8; ii++) {
		for (int jj = 0; jj < 8; jj++) {
			if(board_position[ii][jj] == tileID) {
				row1 = ii;
				col1 = jj;
			}
			if (board_position[ii][jj] == piecePosition.get("bQueen")) {
				row2 = ii;
				col2 = jj; 
			}
		}
	}
	
	if (row1 == row2 || col1 == col2) {
		  CheckInBetween(row1, col1, row2, col2, tileID, piece_name);
		  
		  if (found > 0) {
			  piece_name.setLocation(previous_x, previous_y);
			  targetExist2 = false; 
			  targetExist = false; 
		  }   else if (target_color.equals("b")) {
			  piece_name.setLocation(previous_x, previous_y);
			  targetExist2 = false; 
			  targetExist = false; 
		  }   else if (targetExist == true && target_color.equals("w") && found == 0) {
			  //ADD LINE TO TAKE AWAY PIECE
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
		  }   else if (targetExist == false && found == 0) {
			piecePosition.put(piece_name.getName(), tileID); 
			piece_name.setLocation(new_x, new_y);
		  }  
		  
	} else if (temp % 9 == 0 || temp % 7 == 0) {
		CheckDiagonalColision(tileID, piece_name, notAbstemp); 
		if (targetExist == true && target_color.equals("w") && found == 1) {
			//destroy target
			piecePosition.put(piece_name.getName(), tileID); 
			piece_name.setLocation(new_x, new_y);
		} else {
			if (target_color.equals("b")) {
				piece_name.setLocation(previous_x, previous_y);		
			} else if (targetExist == false && found == 0) {
			piecePosition.put(piece_name.getName(), tileID); 
			piece_name.setLocation(new_x, new_y);
		} else {
			piece_name.setLocation(previous_x, previous_y);	
		}
		}
	} else {
			piece_name.setLocation(previous_x, previous_y);	
		}
}

		//white pieces

//white bishop 
if (local_piece_name.equals("wB")) {
	if (piece_name.getName().equals("wBishop" + 1)) { 
		
		int temp = (Math.abs(tileID - piecePosition.get("wBishop1"))); 
        int notAbstemp = ((tileID - piecePosition.get("wBishop1"))); 
		
		if (temp % 9 == 0) {
			CheckDiagonalColision(tileID, piece_name, notAbstemp); 
			if (targetExist == true && target_color.equals("b") && found == 1) {
				//destroy target
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
			} else {
				if (target_color.equals("w")) {
					piece_name.setLocation(previous_x, previous_y);		
				} else if (targetExist == false && found == 0) {
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
			} else {
				piece_name.setLocation(previous_x, previous_y);	
			}
			}
		} else if (temp % 7 == 0) {
			CheckDiagonalColision(tileID, piece_name, notAbstemp); 
			if (targetExist == true && target_color.equals("b") && found == 1) {
				//destroy target
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
			} else {
				if (target_color.equals("w")) {
					piece_name.setLocation(previous_x, previous_y);		
				} else if (targetExist == false && found == 0) {
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
			} else {
				piece_name.setLocation(previous_x, previous_y);	
			}
			}
		} else {
			piece_name.setLocation(previous_x, previous_y);
		}
	}
	
	if (piece_name.getName().equals("wBishop" + 2)) { 
		
		int temp = (Math.abs(tileID - piecePosition.get("wBishop2"))); 
        int notAbstemp = ((tileID - piecePosition.get("wBishop2"))); 
		
		if (temp % 9 == 0) {
			CheckDiagonalColision(tileID, piece_name, notAbstemp); 
			if (targetExist == true && target_color.equals("b") && found == 1) {
				//destroy target
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
			} else {
				if (target_color.equals("w")) {
					piece_name.setLocation(previous_x, previous_y);		
				} else if (targetExist == false && found == 0) {
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
			} else {
				piece_name.setLocation(previous_x, previous_y);	
			}
			}
		} else if (temp % 7 == 0) {
			CheckDiagonalColision(tileID, piece_name, notAbstemp); 
			if (targetExist == true && target_color.equals("b") && found == 1) {
				//destroy target
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
			} else {
				if (target_color.equals("w")) {
					piece_name.setLocation(previous_x, previous_y);		
				} else if (targetExist == false && found == 0) {
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
			} else {
				piece_name.setLocation(previous_x, previous_y);	
			}
			}
		} else {
			piece_name.setLocation(previous_x, previous_y);
		}
	}
}

//white king 
if (local_piece_name.equals("wk")) {
	if ((piecePosition.get("wking") + 8) == tileID || (piecePosition.get("wking") - 8) == tileID || (piecePosition.get("wking") + 1) == tileID || (piecePosition.get("wking") - 1) == tileID || (piecePosition.get("wking") + 9) == tileID || (piecePosition.get("wking") + 7) == tileID  || (piecePosition.get("wking") - 7) == tileID || (piecePosition.get("wking") - 9) == tileID ) {
		if (targetExist == true && target_color.equals("w")) {
			//destroy the piece
			piecePosition.put(piece_name.getName(), tileID); 
			piece_name.setLocation(new_x, new_y);
		} else if (target_color.equals("w")) {
			piece_name.setLocation(previous_x, previous_y);
		} else {
			piecePosition.put(piece_name.getName(), tileID); 
			piece_name.setLocation(new_x, new_y);
		}
		
	} else {
		piece_name.setLocation(previous_x, previous_y);
	}
}

//Black Queen
if (local_piece_name.equals("wQ")) {
	int temp = (Math.abs(tileID - piecePosition.get("wQueen"))); 
  int notAbstemp = ((tileID - piecePosition.get("wQueen"))); 
  
	int row1 = 0;
	int row2 = 0;
	int col1 = 0;
	int col2 = 0;
  
	for (int ii = 0; ii < 8; ii++) {
		for (int jj = 0; jj < 8; jj++) {
			if(board_position[ii][jj] == tileID) {
				row1 = ii;
				col1 = jj;
			}
			if (board_position[ii][jj] == piecePosition.get("wQueen")) {
				row2 = ii;
				col2 = jj; 
			}
		}
	}
	
	if (row1 == row2 || col1 == col2) {
		  CheckInBetween(row1, col1, row2, col2, tileID, piece_name);
		  
		  if (found > 0) {
			  piece_name.setLocation(previous_x, previous_y);
			  targetExist2 = false; 
			  targetExist = false; 
		  }   else if (target_color.equals("w")) {
			  piece_name.setLocation(previous_x, previous_y);
			  targetExist2 = false; 
			  targetExist = false; 
		  }   else if (targetExist == true && target_color.equals("b") && found == 0) {
			  //ADD LINE TO TAKE AWAY PIECE
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
		  }   else if (targetExist == false && found == 0) {
			piecePosition.put(piece_name.getName(), tileID); 
			piece_name.setLocation(new_x, new_y);
		  }  
		  
	} else if (temp % 9 == 0 || temp % 7 == 0) {
		CheckDiagonalColision(tileID, piece_name, notAbstemp); 
		if (targetExist == true && target_color.equals("b") && found == 1) {
			//destroy target
			piecePosition.put(piece_name.getName(), tileID); 
			piece_name.setLocation(new_x, new_y);
		} else {
			if (target_color.equals("w")) {
				piece_name.setLocation(previous_x, previous_y);		
			} else if (targetExist == false && found == 0) {
			piecePosition.put(piece_name.getName(), tileID); 
			piece_name.setLocation(new_x, new_y);
		} else {
			piece_name.setLocation(previous_x, previous_y);	
		}
		}
	} else {
			piece_name.setLocation(previous_x, previous_y);	
		}
}

		
//White Pawn
if (local_piece_name.equals("wP")) {
		for (int i = 0; i < 8; i++) {
		 if (piece_name.getName().equals("wPawn" + i)) {
			 
			  //If moving straight pawn
	    	  if ((piecePosition.get("wPawn" + i) + 8) == tileID && targetExist == false) {
	    	  piece_name.setLocation(new_x, new_y);
	    	  piecePosition.put(piece_name.getName(), tileID); 
	    	  } 
	    	  //If diagonal & eat the piece
	    	   else if ((piecePosition.get("wPawn" + i) + 7) == tileID && targetExist == true && target_color.equals("b") || (piecePosition.get("wPawn" + i) + 9) == tileID && targetExist == true && target_color.equals("b")) {
		    	  piece_name.setLocation(new_x, new_y);
		    	  piecePosition.put(piece_name.getName(), tileID); 
		    	  targetExist = false; 
		    	  break; 
	    	  } else {
	    	  //If pond is at starting position, move double
	    		  switch(tileID) {
	    		  case 24 : 
	    			  if (piecePosition.get("wPawn" + i) == 8 && targetExist == false) {
	    		    	  piece_name.setLocation(new_x, new_y);
	    		    	  piecePosition.put(piece_name.getName(), tileID); 
	    			  } else {
	    				  piece_name.setLocation(previous_x, previous_y);
	    			  }
	    		      break; 
	    		  case 25 : 
	    			  if (piecePosition.get("wPawn" + i) == 9 && targetExist == false) {
	    		    	  piece_name.setLocation(new_x, new_y);
	    		    	  piecePosition.put(piece_name.getName(), tileID); 
	    			  } else {
	    				  piece_name.setLocation(previous_x, previous_y);
	    			  }
	    		      break;    
	    		  case 26 : 
	    			  if (piecePosition.get("wPawn" + i) == 10 && targetExist == false) {
	    		    	  piece_name.setLocation(new_x, new_y);
	    		    	  piecePosition.put(piece_name.getName(), tileID); 
	    			  } else {
	    				  piece_name.setLocation(previous_x, previous_y);
	    			  }
	    		      break;
	    		  case 27 : 
	    			  if (piecePosition.get("wPawn" + i) == 11 && targetExist == false) {
	    		    	  piece_name.setLocation(new_x, new_y);
	    		    	  piecePosition.put(piece_name.getName(), tileID); 
	    			  } else {
	    				  piece_name.setLocation(previous_x, previous_y);
	    			  }
	    		      break; 
	    		  case 28 : 
	    			  if (piecePosition.get("wPawn" + i) == 12 && targetExist == false) {
	    		    	  piece_name.setLocation(new_x, new_y);
	    		    	  piecePosition.put(piece_name.getName(), tileID); 
	    			  } else {
	    				  piece_name.setLocation(previous_x, previous_y);
	    			  }
	    		      break; 
	    		  case 29 : 
	    			  if (piecePosition.get("wPawn" + i) == 13 && targetExist == false) {
	    		    	  piece_name.setLocation(new_x, new_y);
	    		    	  piecePosition.put(piece_name.getName(), tileID); 
	    			  } else {
	    				  piece_name.setLocation(previous_x, previous_y);
	    			  }
	    		      break; 
	    		  case 30 : 
	    			  if (piecePosition.get("wPawn" + i) == 14 && targetExist == false) {
	    		    	  piece_name.setLocation(new_x, new_y);
	    		    	  piecePosition.put(piece_name.getName(), tileID); 
	    			  } else {
	    				  piece_name.setLocation(previous_x, previous_y);
	    			  }
	    		      break; 
	    		  case 31 : 
	    			  if (piecePosition.get("wPawn" + i) == 15 && targetExist == false) {
	    		    	  piece_name.setLocation(new_x, new_y);
	    		    	  piecePosition.put(piece_name.getName(), tileID); 
	    			  } else {
	    				  piece_name.setLocation(previous_x, previous_y);
	    			  }
	    		      break; 
	    	      default :
	    	        piece_name.setLocation(previous_x, previous_y);
	    	        break; 
	    		  }
	    	  }
	    	  break; //stops the loop  
		 } 
	}
}

if (local_piece_name.equals("wK")) {
	//Movement
	if (piece_name.getName().equals("wKnight" + 1)) {
		
		for(int i = 0; i < valid.length; i++) {
			
			if ((piecePosition.get("wKnight" + 1) + valid[i]) == tileID) {
				if (targetExist  == true && target_color.equals("w")) {
					piece_name.setLocation(previous_x, previous_y);
				} else if (targetExist == true && target_color.equals("b")) {
					piecePosition.put(piece_name.getName(), tileID); 
					piece_name.setLocation(new_x, new_y);
					//ADD LINE TO DELETE THE TARGET
				} else if (targetExist == false) {
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
				}
				break; 
			} else {
				piece_name.setLocation(previous_x, previous_y);
			}
			
	   }
		
		
}
	
	if (piece_name.getName().equals("wKnight" + 2)) {
		
		for(int i = 0; i < valid.length; i++) {
			
			if ((piecePosition.get("wKnight" + 2) + valid[i]) == tileID) {
				if (targetExist  == true && target_color.equals("w")) {
					piece_name.setLocation(previous_x, previous_y);
				} else if (targetExist == true && target_color.equals("b")) {
					piecePosition.put(piece_name.getName(), tileID); 
					piece_name.setLocation(new_x, new_y);
					//ADD LINE TO DELETE THE TARGET
				} else if (targetExist == false) {
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
				}
				break; 
			} else {
				piece_name.setLocation(previous_x, previous_y);
			}
			
	   }		
		
}
	
}

if (local_piece_name.equals("wR")) {
	if (piece_name.getName().equals("wRook" + 1)) {
  	  int row1 = 0;
  	  int col1 = 0;
  	  int row2 = 0;
  	  int col2 = 0;
  	
	  for (int ii = 0; ii < 8; ii++) {
		  for (int jj = 0; jj < 8; jj++) {
			  if (tileID == board_position[ii][jj]) {
				  row1 = ii;
				  col1 = jj;
			  }
			  if (piecePosition.get(piece_name.getName()) == board_position[ii][jj]) {
				  row2 = ii;
				  col2 = jj;
			  }
		  }  
	  }
	  
	  if (row1 == row2 || col1 == col2) {
		  CheckInBetween(row1, col1, row2, col2, tileID, piece_name);
		  
		  if (found > 0) {
			  piece_name.setLocation(previous_x, previous_y);
			  targetExist2 = false; 
			  targetExist = false; 
		  }   else if (target_color.equals("w")) {
			  piece_name.setLocation(previous_x, previous_y);
			  targetExist2 = false; 
			  targetExist = false; 
		  }   else if (targetExist == true && target_color.equals("b") && found == 0) {
			  //ADD LINE TO TAKE AWAY PIECE
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
		  }   else if (targetExist == false && found == 0) {
			piecePosition.put(piece_name.getName(), tileID); 
			piece_name.setLocation(new_x, new_y);
		  }  
		  
	  } else {
		  piece_name.setLocation(previous_x, previous_y);
			row1 = 0;
			row2 = 0;
			col1 = 0;
			col2 = 0;
	  }
	}
	
	if (piece_name.getName().equals("wRook" + 2)) {
	  	  int row1 = 0;
	  	  int col1 = 0;
	  	  int row2 = 0;
	  	  int col2 = 0;
	  	
		  for (int ii = 0; ii < 8; ii++) {
			  for (int jj = 0; jj < 8; jj++) {
				  if (tileID == board_position[ii][jj]) {
					  row1 = ii;
					  col1 = jj;
				  }
				  if (piecePosition.get(piece_name.getName()) == board_position[ii][jj]) {
					  row2 = ii;
					  col2 = jj;
				  }
			  }  
		  }
		  
		  if (row1 == row2 || col1 == col2) {
			  CheckInBetween(row1, col1, row2, col2, tileID, piece_name);
			  
			  if (found > 0) {
				  piece_name.setLocation(previous_x, previous_y);
				  targetExist2 = false; 
				  targetExist = false; 
			  }   else if (target_color.equals("w")) {
				  piece_name.setLocation(previous_x, previous_y);
				  targetExist2 = false; 
				  targetExist = false; 
			  }   else if (targetExist == true && target_color.equals("b") && found == 0) {
				  //ADD LINE TO TAKE AWAY PIECE
					piecePosition.put(piece_name.getName(), tileID); 
					piece_name.setLocation(new_x, new_y);
			  }   else if (targetExist == false && found == 0) {
				piecePosition.put(piece_name.getName(), tileID); 
				piece_name.setLocation(new_x, new_y);
			  }  
			  
		  } else {
			  piece_name.setLocation(previous_x, previous_y);
				row1 = 0;
				row2 = 0;
				col1 = 0;
				col2 = 0;
		  }
		}
	
}
	
}

	private void CheckDiagonalColision(int tileID, JLabel piece_name, int temp) { 
		int position = piecePosition.get(piece_name.getName());
		int diagonalintersec = 0; 
		int temp2 = 0;
		
		//Up Diagonal left
	if (temp < 0 && temp % 9 == 0 )	{
		temp2 = Math.abs(temp / 9); 
		
	for (int i = 1; i < (temp2 + 1); i++) {   	
		CheckPieceOnBoard2(position - (9 * i));
	}
	
	}
	//Up Diagonal Right
	if (temp < 0 && temp % 7 == 0) {
		temp2 = Math.abs(temp / 7);

		for (int i = 1; i < (temp2 + 1); i++) {   	
			CheckPieceOnBoard2(position - (7 * i));
		}
	}
        //Down Diagonal Left
	if (temp > 0 && temp % 7 == 0) {
		temp2 = Math.abs(temp / 7);
		for (int i = 1; i < (temp2 + 1); i++) {   	
			CheckPieceOnBoard2(position + (7 * i));
		}
	}
	if (temp > 0 && temp % 9 == 0) {
		Math.abs(temp2 = temp / 9); 
		for (int i = 1; i < (temp2 + 1); i++) {   	
			CheckPieceOnBoard2(position + (9 * i));
		}
	}
}

	private void CheckInBetween(int row1, int col1, int row2, int col2, int tileID, JLabel piece_name) {
        int temp; 
        int x = 0; 
        int y = piecePosition.get(piece_name.getName());
        boolean piece = false; 
        
		//this means we're in a colmun 
		if (row1 == row2) {
        	for (int i = 0; i < 8; i++) {
        		x = board_position[row2][i];
        		if (y - tileID > 0) {
        			if (x > tileID && x < y) {
        				CheckPieceOnBoard2(x);
        			}
        		} else if (y - tileID < 0) {
        			if (x < tileID && x > y) {
        				CheckPieceOnBoard2(x);
        			}
        		}
        	}	
		 //we're in a row
        } else if ((col1 == col2)) {
        	for (int i = 0; i < 8; i++) {
        		x = board_position[i][col2];
        		if (y - tileID > 0) {
        			if (x > tileID && x < y) {
        				CheckPieceOnBoard2(x);
        			}
        		} else if (y - tileID < 0) {
        			if (x < tileID && x > y) {
        				CheckPieceOnBoard2(x);
        			}
        		}
        	}	
        }
    }

	public static void SetInitialPosition() {
		
		//White
		piecePosition.put(Piece[0].getName(), 8);
		piecePosition.put(Piece[1].getName(), 9);
		piecePosition.put(Piece[2].getName(), 10);
		piecePosition.put(Piece[3].getName(), 11);
		piecePosition.put(Piece[4].getName(), 12);
		piecePosition.put(Piece[5].getName(), 13);
		piecePosition.put(Piece[6].getName(), 14);
		piecePosition.put(Piece[7].getName(), 15);
		piecePosition.put(Piece[8].getName(), 0);
		piecePosition.put(Piece[9].getName(), 1);
		piecePosition.put(Piece[10].getName(), 2);
		piecePosition.put(Piece[11].getName(), 3);
		piecePosition.put(Piece[12].getName(), 4);
		piecePosition.put(Piece[13].getName(), 5);
		piecePosition.put(Piece[14].getName(), 6);
		piecePosition.put(Piece[15].getName(), 7);
		
		//Black
		piecePosition.put(Piece[16].getName(), 48);
		piecePosition.put(Piece[17].getName(), 49);
		piecePosition.put(Piece[18].getName(), 50);
		piecePosition.put(Piece[19].getName(), 51);
		piecePosition.put(Piece[20].getName(), 52);
		piecePosition.put(Piece[21].getName(), 53);
		piecePosition.put(Piece[22].getName(), 54);
		piecePosition.put(Piece[23].getName(), 55);
		piecePosition.put(Piece[24].getName(), 56);
		piecePosition.put(Piece[25].getName(), 57);
		piecePosition.put(Piece[26].getName(), 58);
		piecePosition.put(Piece[27].getName(), 59);
		piecePosition.put(Piece[28].getName(), 60);
		piecePosition.put(Piece[29].getName(), 61);
		piecePosition.put(Piece[30].getName(), 62);
		piecePosition.put(Piece[31].getName(), 63);
		
	}
}
		