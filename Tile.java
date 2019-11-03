/*
 * Name : Wenhan Zhang
 * PennKey : zwenhan
 * Recitation : 205
 * 
 * Description: Tile class
 * 
 */

import java.awt.Color;

public class Tile {
    
    // rank is the power of 2 of the number of the tile
    private int rank;
    private Color tileColor;
    private Color textColor;
    private double x;
    private double y;
    public static final double SIZE = 0.18;
    private boolean newTile;
    private boolean mergedThisTurn;
    private boolean changedThisTurn;
    
    // references in 4 directions like a node in a linked list
    public Tile up;
    public Tile down;
    public Tile left;
    public Tile right;
    
    // constructor of empty tile
    public Tile(double x, double y) {
        this.x = x;
        this.y = y;
        rank = 0;
    }
    
    /*
     * Gets x coordinate of tile
     * Input: nil
     * Output: double - x coordinate
     */
    public double getX() {
        return x;
    }
    
    /*
     * Gets y coordinate of tile
     * Input: nil
     * Output: double - y coordinate
     */
    public double getY() {
        return y;
    }
    
    /*
     * Sets field newTile to true
     * Input: nil
     * Output: Nil, side-effect is setting the field newTile of the tile to true
     */
    public void makeNewTile() {
        newTile = true;
    }
    
    /*
     * Gets the rank of the tile
     * Input: nil
     * Output: int - rank, i.e. the power of 2
     */
    public int getRank() {
        return rank;
    }
    
    /*
     * Sets the rank of the tile
     * Input: int - rank that the tile is set to
     * Output: nil, side-effect is setting the rank of the tile
     */
    public void setRank(int rank) {
        this.rank = rank;
    }
    
    /*
     * Increments the rank of the tile by one, if the tile has not been merged
     * this turn
     * Input: nil
     * Output: nil, side-effect is incrementing the rank of the tile by one
     */
    public void upRank() {
        if (this.mergedThisTurn == false) {
            rank++;
            this.mergedThisTurn = true;
        }
    }
    
    /*
     * Returns the field changedThisTurn
     * Input: nil
     * Output: boolean - if tile has been moved or merged this turn
     */
    public boolean ifChangedThisTurn() {
        return changedThisTurn;
    }
    
    /*
     * Sets the three fields mergedThisTurn, changedThisTurn and newTile all to 
     * default which is false
     * Input: nil
     * Output: nil, side-effect is setting mergedThisTurn, changedThisTurn and 
     * newTile to false
     */
    public void newTurn() {
        mergedThisTurn = false;
        changedThisTurn = false;
        newTile = false;
    }
    
    /*
     * Returns if the tile is empty, i.e. has rank 0. Returns null if tile is 
     * null
     * Input: nil
     * Output: boolean - whether rank of tile is 0
     */
    public boolean isEmpty() {
        if (this == null) {
            return false;
        }
        return rank == 0;
    }
    
    /*
     * Returns if this tile is same as another tile. Returns null if the other
     * tile is null. 
     * Input: Tile - another Tile object
     * Output: boolean - whether this tile and the other tile are the same and 
     * both are not rank 0
     */
    public boolean isSame(Tile next) {
        if (next == null) {
            return false;
        }
        return rank != 0 && this.rank == next.rank;
    }
    
    /*
     * Method to move left, including shifting left and merging left
     * Input: nil
     * Output: nil, side-effects are shifting left if tile on left is empty and
     * merging left if tile on left is the same rank
     */
    public void moveLeft() {
        // return if current tile is empty or at left most
        if (this.rank == 0 || this.left == null) {
            return;
        // if tile on left is empty, recursively move left
        } else if (this.left.isEmpty()) {
            this.left.setRank(this.rank);
            this.rank = 0;
            this.changedThisTurn = true;
            System.out.println("shifted left");
        // if tile on left is same and this tile has not been merged this click, 
        // try to merge
        } else if (isSame(this.left) && this.mergedThisTurn == false && 
                   this.left.mergedThisTurn == false) {
            this.left.upRank();
            this.rank = 0;
            this.left.mergedThisTurn = true;
            this.changedThisTurn = true;
            System.out.println("merged left");
        }
    }
    
    /*
     * Method to move right, including shifting right and merging right
     * Input: nil
     * Output: nil, side-effects are shifting right if tile on left is empty and
     * merging right if tile on right is the same rank
     */
    public void moveRight() {
        // return if current tile is empty or at right most
        if (this.rank == 0 || this.right == null) {
            return;
        // if tile on right is empty, recursively move right
        } else if (this.right.isEmpty()) {
            this.right.setRank(this.rank);
            this.rank = 0;
            this.changedThisTurn = true;
            System.out.println("shifted right");
        // if tile on right is same and this tile has not been merged this click 
        // try to merge
        } else if (isSame(this.right) && this.mergedThisTurn == false && 
                   this.right.mergedThisTurn == false) {
            this.right.upRank();
            this.rank = 0;
            this.right.mergedThisTurn = true;
            this.changedThisTurn = true;
            System.out.println("merged right");
        }
    }
    
    /*
     * Method to move up, including shifting up and merging up
     * Input: nil
     * Output: nil, side-effects are shifting up if tile above is empty and
     * merging up if tile above is the same rank
     */
    public void moveUp() {
        // return if current tile is empty or at upper most
        if (this.rank == 0 || this.up == null) {
            return;
        // if tile above is empty, recursively move up
        } else if (this.up.isEmpty()) {
            this.up.setRank(this.rank);
            this.rank = 0;
            this.changedThisTurn = true;
            System.out.println("shifted up");
        // if tile above is same and this tile has not been merged this click, 
        // try to merge
        } else if (isSame(this.up) && this.mergedThisTurn == false && 
                   this.up.mergedThisTurn == false) {
            this.up.upRank();
            this.rank = 0;
            this.up.mergedThisTurn = true;
            this.changedThisTurn = true;
            System.out.println("merged up");
        }
    }
    
    /*
     * Method to move down, including shifting down and merging down
     * Input: nil
     * Output: nil, side-effects are shifting down if tile below is empty and
     * merging down if tile below is the same rank
     */
    public void moveDown() {
        // return if current tile is empty or at lower most
        if (this.rank == 0 || this.down == null) {
            return;
        // if tile below is empty, recursively move down
        } else if (this.down.isEmpty()) {
            this.down.setRank(this.rank);
            this.rank = 0;
            this.changedThisTurn = true;
            System.out.println("shifted down");
        // if tile below is same and this tile has not been merged this click, 
        // try to merge
        } else if (isSame(this.down) && this.mergedThisTurn == false && 
                   this.down.mergedThisTurn == false) {
            this.down.upRank();
            this.rank = 0;
            this.down.mergedThisTurn = true;
            this.changedThisTurn = true;
            System.out.println("merged down");
        }
    }
    
    /*
     * Draws the tile with tile color and text color according to the rank
     * Input: nil
     * Output: nil, side-effect is drawing the tile
     */
    public void draw() {
        // initialize the color of empty tile
        if (rank == 0) {
            tileColor = new Color(205, 192, 180);
        // initialize colors of number 2
        } else if (rank == 1) {
            tileColor = new Color(238, 228, 218);
            textColor = new Color(119, 110, 101);
        // initialize colors of number 4
        } else if (rank == 2) {
            tileColor = new Color(237, 224, 200);
            textColor = new Color(119, 110, 101);
        // initialize colors of number 8
        } else if (rank == 3) {
            tileColor = new Color(242, 177, 121);
            textColor = new Color(249, 246, 242);
        // initialize colors of number 16
        } else if (rank == 4) {
            tileColor = new Color(243, 149, 99);
            textColor = new Color(249, 246, 242);
        // initialize colors of number 32
        } else if (rank == 5) {
            tileColor = new Color(240, 123, 95);
            textColor = new Color(249, 246, 242);
        // initialize colors of number 64
        } else if (rank == 6) {
            tileColor = new Color(238, 92, 58);
            textColor = new Color(249, 246, 242);
        // initialize colors of number 128
        } else if (rank == 7) {
            tileColor = new Color(237, 207, 114);
            textColor = new Color(249, 246, 242);
        // initialize colors of number 256
        } else if (rank == 8) {
            tileColor = new Color(241, 208, 75);
            textColor = new Color(249, 246, 242);
        // initialize colors of number 512
        } else if (rank == 9) {
            tileColor = new Color(227, 193, 45);
            textColor = new Color(249, 246, 242);
        // initialize colors of number 1024
        } else if (rank == 10) {
            tileColor = new Color(226, 185, 45);
            textColor = new Color(249, 246, 242);
        // initialize colors of number 2048
        } else if (rank == 11) {
            tileColor = new Color(239, 195, 48);
            textColor = new Color(249, 246, 242);
        }
        
        PennDraw.setPenColor(tileColor);
        PennDraw.filledSquare(x, y, SIZE / 2);
        
        // draw text if rank > 0, i.e. tile not empty
        if (rank > 0) {
            
            // if new tile, use red text color, if not use normal text color
            if (newTile == true) {
                PennDraw.setPenColor(240, 123, 99);
            } else {
                PennDraw.setPenColor(textColor);
            }
            
            if (rank < 7) {
                PennDraw.setFontSize(50);
            // smaller font size for 3-digit numbers
            } else if (rank < 10) {
                PennDraw.setFontSize(38);
            // even smaller font size for 4-digit numbers
            } else {
                PennDraw.setFontSize(28);
            }
            
            // draw text displsying the number on tile
            PennDraw.setFontBold();
            PennDraw.text(x, y - 0.01, 
                          Integer.toString((int) Math.pow(2, rank)));
        }
    }
}