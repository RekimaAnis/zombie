package projet;
import javax.swing.*;
import java.awt.*;
import projet.celulle.*;
import projet.item.Item;
import projet.item.heal.Heal;
import java.lang.*;
import java.util.List;
import java.util.*;
import projet.zombie.*;
import projet.player.*;

public class Board {

    public Celulle[][] board;
    private int nbLigne;
    private int nbColonne;
    private Random random;
    private ArrayList<Player> player;
    private ArrayList<Zombie> zombie;     
    private ArrayList<Item> item;
    private ArrayList<Item> medoc;

    /**
     * Constructor for the Board class, initializes the board and sets up the initial configuration.
     *
     * @param nbLigne    Number of rows on the board.
     * @param nbColonne  Number of columns on the board.
     */
    public Board(int nbLigne, int nbColonne) {
        this.nbLigne = nbLigne;
        this.nbColonne = nbColonne;
        this.board = new Celulle[nbLigne][nbColonne];
        this.random = new Random();
        this.initboard();
        this.creerboard();
        this.placePiece();    
        this.player = new ArrayList<Player>();
        this.zombie = new ArrayList<Zombie>();
        this.item = new ArrayList<Item>();
        this.medoc = new ArrayList<Item>();
    }

    /**
     * Gets the number of rows on the board.
     *
     * @return The number of rows.
     */
    public int getLigne() {
        return this.nbLigne;
    }

    /**
     * Gets the number of columns on the board.
     *
     * @return The number of columns.
     */
    public int getColonne() {
        return this.nbColonne;
    }
    
    public Celulle[][] getCell(){
    	return this.board;
    }

    
    public List<Player> getPlayer(){
    	return this.player;
    }
    
    public List<Zombie> getZombie(){
    	return this.zombie;
    }
    
    public List<Item> getItem(){
    	return this.item;
    }
    
    public List<Item> getHeal(){
    	return this.getHeal();
    }
    
    /**
     * Initializes the board with Piece cells.
     */
    public void initboard() {
        for (int i = 0; i < this.nbLigne; i++) {
            for (int j = 0; j < this.nbColonne; j++) {
                Piece piece = new Piece(i,j);
                piece.canDoor(this.nbLigne,this.nbColonne);
                piece.addDoor(i,j);
                this.board[i][j] = piece;

            }
        }

    }

    /**
     * Creates the initial configuration of the board.
     */
    public void creerboard() {
        this.create(0, 0, this.nbLigne - 1, this.nbColonne - 1);
    }

    /**
     * Recursively creates streets on the board based on a given range.
     *
     * @param rowStart   Starting row index.
     * @param colStart   Starting column index.
     * @param rowEnd     Ending row index.
     * @param colEnd     Ending column index.
     */
    public void create(int rowStart, int colStart, int rowEnd, int colEnd) {

        int rowLength = rowEnd - rowStart + 1;
        int colLength = colEnd - colStart + 1;
        
        if (rowLength > 4 && colLength > 4) {
            this.createStreetInCross(rowStart, colStart, rowEnd, colEnd);
        }
        else if (rowLength > 4 ) {
            this.createStreetInRow(rowStart, colStart, rowEnd, colEnd);
        }
        else if (colLength > 4 ) {
            this.createStreetInColumn(rowStart, colStart, rowEnd, colEnd);
        }
    }

    /**
     * Creates a street in a specific row.
     *
     * @param rowStart   Starting row index.
     * @param colStart   Starting column index.
     * @param rowEnd     Ending row index.
     * @param colEnd     Ending column index.
     */
    public void createStreetInRow(int rowStart, int colStart, int rowEnd, int colEnd) {

        int streetRow = random.nextInt((rowEnd - rowStart - 3)) + rowStart + 2;
        for (int i = colStart; i <= colEnd; i++) {
                this.board[streetRow][i] = new Street(streetRow,i);
            }
        if (rowEnd - rowStart >= 1) {
            this.create(rowStart, colStart, streetRow - 1, colEnd);
            this.create(streetRow + 1, colStart, rowEnd, colEnd);
        }
    }

    /**
     * Creates a street in a specific column.
     *
     * @param rowStart   Starting row index.
     * @param colStart   Starting column index.
     * @param rowEnd     Ending row index.
     * @param colEnd     Ending column index.
     */
    public void createStreetInColumn(int rowStart, int colStart, int rowEnd, int colEnd) {

        int streetCol = random.nextInt((colEnd - colStart - 3)) + colStart + 2 ;
        for (int i = rowStart; i <= rowEnd; i++) {
                this.board[i][streetCol] = new Street(i,streetCol);
            }
        if (colEnd - colStart >= 1) {
            this.create(rowStart, colStart, rowEnd, streetCol - 1);
            this.create(rowStart, streetCol + 1, rowEnd, colEnd);
        }
    }

    /**
     * Creates a street in both rows and columns forming a cross shape.
     *
     * @param rowStart   Starting row index.
     * @param colStart   Starting column index.
     * @param rowEnd     Ending row index.
     * @param colEnd     Ending column index.
     */
    public void createStreetInCross(int rowStart, int colStart, int rowEnd, int colEnd) {

            int streetRow = random.nextInt((rowEnd - rowStart - 3)) + rowStart + 2;
            int streetCol = random.nextInt((colEnd - colStart - 3)) + colStart + 2;

            for (int i = colStart; i <= colEnd; i++) {
                this.board[streetRow][i] = new Street(streetRow,i);
            }

            for (int i = rowStart; i <= rowEnd; i++) {
                this.board[i][streetCol] = new Street(i,streetCol);
            }
    
        this.create(rowStart, colStart, streetRow - 1, streetCol - 1);
        this.create(rowStart, streetCol + 1, streetRow - 1, colEnd);
        this.create(streetRow + 1, colStart, rowEnd, streetCol - 1);
        this.create(streetRow + 1, streetCol + 1, rowEnd, colEnd);
    }

    /**
     * Places Pieces on the board, including Continental and Medical Pieces.
     */
    public void placePiece() {
        this.placeContinental();
        this.placeDrogStore();
        this.placeIntersection();
        this.placeSewer();
    }
    
    /**
     * Places Continental Pieces randomly on the board.
     */
    public void  placeContinental() {

        int rowaleat = random.nextInt(nbLigne );
        int colaleat = random.nextInt(nbColonne );
        if (this.board[rowaleat][colaleat] instanceof Piece) {
            Continental continental = new Continental(rowaleat,colaleat);
            continental.canDoor(nbLigne,nbColonne);
            continental.addDoor(rowaleat,colaleat);
            
            this.board[rowaleat][colaleat] = continental;
            
        }
        else {
            this.placeContinental();
        }
    }

    /**
     * Places Medical Pieces randomly on the board.
     */
    public void placeDrogStore() {
        int rowaleat = random.nextInt(nbLigne );
        int colaleat = random.nextInt(nbColonne);
        if (this.board[rowaleat][colaleat] instanceof Piece) {
            DrogStore drogStore = new DrogStore(rowaleat, colaleat) ;
            drogStore.canDoor(nbLigne, nbColonne);
            drogStore.addDoor(rowaleat,colaleat);
            this.board[rowaleat][colaleat] = drogStore;
            
         }
        else {
            this.placeDrogStore();
        }
    }

    /**
     * Places Intersection on the board.
     */
    public void placeIntersection(){
        for(int i=1;i<this.nbLigne-1;i++){
            for(int j=1;j<this.nbColonne-1;j++){
                if(this.board[i][j] instanceof Street && this.board[i+1][j] instanceof Street && this.board[i-1][j] instanceof Street && this.board[i][j+1] instanceof Street && this.board[i][j-1] instanceof Street){
                    this.board[i][j]= new Intersection(i,j);
                }
            }
        }
    }
    
    /**
     * Places sewer on the board.
     */
    public void placeSewer() {
    	for(int i=0;i<this.nbLigne;i++){
            for(int j=0;j<this.nbColonne;j++){
                if(this.board[i][j] instanceof Street &&( i==0|| i == this.nbLigne-1 || j==0 || j==this.nbColonne-1)){
                    this.board[i][j] = new Sewer(i,j);
                }
            }
        }
    }
    
    /**
     * place player in the map
     */
    public void placePlayer(ArrayList<Player> player) {
    	int randrow = random.nextInt(this.nbLigne);
        int randcol = random.nextInt(this.nbColonne);
        if(this.board[randrow][randcol] instanceof Intersection) {
        	for(int i=0;i<player.size(); i++) {
        		this.board[randrow][randcol].setPlayer(player.get(i));
        		player.get(i).setx(randrow);
        		player.get(i).sety(randcol);
        		this.player.add(player.get(i));
        	}
        }
        else {
        	this.placePlayer(player);
        }
    }
    
    /**
     * place zombie in the map
     */

    public void placeZombie(){
        for(int i=0;i<this.nbLigne;i++){
            for(int j=0;j<this.nbColonne;j++){
                if(this.board[i][j] instanceof Sewer){
                    int type = random.nextInt(5);
                    if(type==0){
                        Zombie z = new Zombie();
                        this.board[i][j].SetZombie(z);
                        z.setx(i);
                        z.sety(j);
                        this.zombie.add(z);
                    }
                    if(type==1){
                        Zombie z = new Runners();
                        this.board[i][j].SetZombie(z);
                        z.setx(i);
                        z.sety(j);
                        this.zombie.add(z);
                    }
                    if(type==2){
                        Zombie z = new Walkers();
                        this.board[i][j].SetZombie(z);
                        z.setx(i);
                        z.sety(j);
                        this.zombie.add(z);
                    }
                    if(type==3){
                        Zombie z = new Balaise();
                        this.board[i][j].SetZombie(z);
                        z.setx(i);
                        z.sety(j);
                        this.zombie.add(z);
                    }
                    if(type==4){
                        Zombie z = new Abomination();
                        this.board[i][j].SetZombie(z);
                        z.setx(i);
                        z.sety(j);
                        this.zombie.add(z);
                    }
                }
            }
        }
    }
    
    /**
     * place the item
     */
    public void placeItem(ArrayList<Item> item){
        int randrow = random.nextInt(this.nbLigne);
        int randcol = random.nextInt(this.nbColonne);
        if(!(this.board[randrow][randcol] instanceof Street)){
            Iterator<Item> iterator = item.iterator();
            while(iterator.hasNext()){
                Item i = iterator.next();
                this.board[randrow][randcol].SetItem(i);
                this.item.add(i);
                iterator.remove(); 
                placeItem(item); 
            }
        }
        else{
            this.placeItem(item);
        }
    }
    
    /**
     * Place the heal item in the map
     */
    public void placeHeal(ArrayList<Item> heal){
        int randrow = random.nextInt(this.nbLigne);
        int randcol = random.nextInt(this.nbColonne);
        if(this.board[randrow][randcol] instanceof DrogStore){
            for(int i=0;i<heal.size();i++){
                this.board[randrow][randcol].SetItem(heal.get(i));
                this.medoc.add(heal.get(i));
            }
        }
        else{
            this.placeHeal(heal);
        }
    }


    /**
     * 
     * @param i cordonnee x of the cell
     * @param j cordonnee y of the cell
     * @return the number of the Zombie in this cell
     */
    public String getCellZombie(int i, int j) {
    	if(this.board[i][j].getZombie().size()<1) {
    		return " ";
    	}
    	else {
    		return Integer.toString(this.board[i][j].getZombie().size());
    	}
    }
    /**
     * 
     * @param i cordonnee x of the cell
     * @param j cordonnee y of the cell
     * @return the number of Player in this cell
     */
    public String getCellPlayer(int i, int j) {
    	if(this.board[i][j].getPlayer().size()<1) {
    		return " ";
    	}
    	else {
    		return Integer.toString(this.board[i][j].getPlayer().size());
    	}
    } 
    /**
     * 
     * @param i cordonnee x of the cell
     * @param j cordonnee y of the cell
     * @return the number of item in this celulle
     */
    public String getCellItem(int i,int j) {
    	if(this.board[i][j].getItem().size()<1) {
    		return " ";
    	}
    	else {
    		return Integer.toString(this.board[i][j].getItem().size());
    	}
    }
    /**
     * 
     * @param celulle get the cell
     * @return return the cell color
     */
    public String getCellColor(Celulle celulle) {
    	return "";
    }
    
    /**
     * 
     * @param celulle get the cell 
     * @return return what is the type of this cell
     */
    public String getCellSymbole(Celulle celulle) {
    	if(celulle instanceof DrogStore) {
    		return "D ";
    	}
    	else if(celulle instanceof Continental) {
    		return "C ";
    	}
    	else if(celulle instanceof Piece) {
    		return "P ";
    	}
    	else if(celulle instanceof Sewer) {
    		return "S ";
    	}
    	else if(celulle instanceof Intersection) {
    		return "I ";
    	}
    	else {
    		return "  ";
    	}
    }
    
    /**
     * 
     * @param text who undergo the color
     * @param colorCode value for the color
     * @return
     */
    public static String getColorString(String text, String colorCode) {
        return "\u001B[" + colorCode + "m" + text + "\u001B[0m";
    }
    
    /**
     * 
     * @param i cordonnee of x
     * @param j cordonne of y
     * @param a a number of door
     * @return a string with the color door
     */
    public String getDoorColor(int i, int j, int a) {
    	if(this.board[i][j] instanceof Piece || (j<this.nbLigne-1 && this.board[i][j+1] instanceof Piece)) {
    		String coloredText = getColorString("|", "31");
    		return coloredText;
    	}
    	return "|";
    }
    
    /**
     * 
     * @param i cordonnee of x
     * @param j cordonne of y
     * @param a number of door
     * @return a string with the color door
     */
    public String getDoorColor2(int i, int j, int a) {
    	if(this.board[i][j] instanceof Piece && i<this.nbColonne-1 && this.board[i+1][j] instanceof Piece) {
    		String coloredText = getColorString("_", "31");
        	return coloredText;
    	}
    	return "_";
    }
    
    
    
    
    /**
     * Displays the representation of the board with cells.
     */ 

     public void display() {
    	 for(int j=0;j<this.nbColonne;j++) {
    		 System.out.print("____");
    	 }
    	 System.out.println();
    	 for(int i=0; i<this.nbLigne;i++) {
    		 for(int j=0 ; j<this.nbColonne;j++) {
    			 System.out.print("|"+ getCellZombie(i,j) + getCellPlayer(i,j) + getCellItem(i,j));
    		 }
    		 System.out.print("|");
    		 System.out.println();
    		 System.out.print("|");
    		 for(int j=0; j<this.nbColonne;j++) {
    			 System.out.print(" " + getCellColor(this.board[i][j])+ getCellSymbole(this.board[i][j])  + getDoorColor(i,j,1)+ ""  ) ;
    			 
    		 }
    		 System.out.println();
    		 for(int j=0;j< this.nbColonne;j++) {
    			 System.out.print("|_" + getDoorColor2(i,j,2) + "_" );
    		 }
    		 System.out.print("|");
    		 System.out.println();
    	 }
    	 
     }
     /*
      * check if even a player is alive on the board
      * @return true if eve one player is alive else false
      */
     public boolean checkAllPlayerAlive() {
    	 boolean res = false;
    	 int cmp =0;
    	 for (Player p : this.player) {
    		 if (p.getInlife()) {
    			 cmp++;
    		 }
    	 }
    	 if (cmp>0) {
    		 res = true;
    	 }
    	 return res;
     }
     
     /*
      * check if even a zombie is alive on the board
      * @return true if eve one zombie is alive else false
      */
     public boolean checkAllZombieAlive() {
    	 boolean res = false;
    	 int cmp =0;
    	 for (Zombie p : this.zombie) {
    		 if (p.getInLife()) {
    			 cmp++;
    		 }
    	 }
    	 if (cmp>0) {
    		 res = true;
    	 }
    	 return res;
     }
     
     /*
      * check if a player have the level thirty
      * @return true if a player is level 30 or more else false
      */
     public boolean checkLevel30() {
    	 boolean res = false;
    	 int cmp=0;
    	 for (Player p : this.player) {
    		 if (p.getLevel()>=30) {
    			 cmp++;
    		 }
    	 }
    	 if (cmp>0) {
    		 res = true;
    	 }
    	 return res;
     }
     
     /*
      * remove player when they're dead
      */
      public void removeplayerWhenDead() {
        Iterator<Player> iterator = this.player.iterator();
        
        while (iterator.hasNext()) {
            Player p = iterator.next();
            if (!p.getInlife()) {
                iterator.remove(); // Utilisation de l'itérateur pour supprimer en toute sécurité
                this.board[p.getx()][p.gety()].getPlayer().remove(p);
            }
        }
    }
     
     /*
      * remove zombie when they're dead
      */
     public void removezombieWhenDead() {
        Iterator<Zombie> iterator = this.zombie.iterator();
    
        while (iterator.hasNext()) {
            Zombie z = iterator.next();
            if (!z.getInLife()) {
                iterator.remove(); // Utilisation de l'itérateur pour supprimer en toute sécurité
                this.board[z.getx()][z.gety()].getZombie().remove(z);
            }
        }
     }

     /**
      * averange of player
      * @return the averange of the level player
      */
     public int averangePlayer(){
        int nb = 0;
        for(int i=0; i<this.player.size(); i++){
            nb+= this.player.get(i).getLevel();
        }
        if(this.player.size()<1){
            nb=1;
            return nb;
        }
        else{
            nb = nb/this.player.size();
            return nb;
        }
     }
     
}
