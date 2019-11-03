/*
 * Name : Wenhan Zhang
 * PennKey : zwenhan
 * Recitation : 205
 * 
 * Description: The game 2048
 * 
 * Execution: java Game2048
 * 
 */

import java.util.Random;

public class Game2048 {
    public static void main(String[] args) {
        PennDraw.setCanvasSize(500, 500);
        
        // create array of 16 tiles
        Tile[] tiles = new Tile[16];
        
        // assign x and y location to the 16 empty tiles 
        for (int i = 0; i < tiles.length; i++) {
            double xCoord = ((i % 4) + 1) * 0.2;
            double yCoord = 0.8 - ((i / 4) * 0.2);
            tiles[i] = new Tile(xCoord, yCoord);
        }
        
        // assign references to Tile in 4 directions
        for (int i = 0; i < tiles.length; i++) {
            // right references
            // right-most column of tiles do not have right reference
            if (i % 4 == 3) {
                tiles[i].right = null;
            } else {
                tiles[i].right = tiles[i + 1];
            }
            
            // left references
            // left-most column of tiles do not have left reference
            if (i % 4 == 0) {
                tiles[i].left = null;
            } else {
                tiles[i].left = tiles[i - 1];
            }
            
            // down references
            if (i < 12) {
                tiles[i].down = tiles[i + 4];
            // bottom row of tiles do not have down reference
            } else {
                tiles[i].down = null;
            }
            
            // up references
            if (i > 3) {
                tiles[i].up = tiles[i - 4];
            // top row of tiles do not have up reference
            } else {
                tiles[i].up = null;
            }
        }
        
        PennDraw.enableAnimation(5);
        boolean firstTime = true;
        boolean lost = false;
        boolean win = false;
        int numMoves = 0;
        
        while (!lost && !win) {
            /* opening the program for the first time or typing one of four 
             * keys - w, s, a, d generates a new number tile with 2 or 4 on it
             */
            // initialize random char
            char c = 'e';
            if (PennDraw.hasNextKeyTyped() || firstTime == true) {
                
                boolean tilesChangedThisTurn = false;
                
                if (PennDraw.hasNextKeyTyped()) {
                    numMoves++;
                    c = PennDraw.nextKeyTyped();
                    // loop through 3 times to ensure that in any row/column,
                    // if 3 tiles are filled, no tile would be blocked from 
                    // moving
                    for (int i = 0; i < tiles.length * 3; i++) {
                        switch (c) {
                            // pressing any of the four keys - w, s, a, d 
                            // represents moving up, down, left, right 
                            // respectively
                            case 'A': 
                            case 'a': tiles[i % 16].moveLeft(); 
                                break;
                            case 'D':
                            case 'd': tiles[i % 16].moveRight();
                                break;
                            case 'W': 
                            case 'w': tiles[i % 16].moveUp();
                                break;
                            case 'S':
                            case 's': tiles[i % 16].moveDown();
                                break;
                            default: 
                                break;
                        }
                        
                        if (tiles[i % 16].ifChangedThisTurn()) {
                            tilesChangedThisTurn = true;
                        }
                    }
                }
                
                // draw background
                PennDraw.clear(250, 248, 239);
                // draw the base of the grid
                PennDraw.setPenColor(187, 173, 160);
                PennDraw.filledSquare(0.5, 0.5, 0.41);
                
                // if one of the a, s, d, w keys is pressed or firstTime is true
                // and if there is at least one tile that is moved or changed,
                // display 2 or 4 on a randomly chosen empty tile
                if (tilesChangedThisTurn == true || firstTime == true) {
                    //System.out.println(tilesChangedThisTurn);
                    double choose2Or4 = Math.random();
                    int randomEmptyIndex = getRandomEmpty(tiles);
                    
                    // makeNewTile() triggers the change of color of text of the 
                    // new tile to red
                    tiles[randomEmptyIndex].makeNewTile();
                    
                    // display 2 or 4 on the randomly chosen empty tile
                    if (choose2Or4 < 0.5) {
                        // turn tile to 2
                        tiles[randomEmptyIndex].setRank(1);
                    } else {
                        // turn tile to 4
                        tiles[randomEmptyIndex].setRank(2);
                    }
                }
                
                // keep track of number of filled tiles
                int numFilled = 0;
                // number of tiles with possible moves
                int tilesWithPossibleMoves = 0;
                
                for (int i = 0; i < tiles.length; i++) {
                    // draw the tiles
                    tiles[i].draw();
                    
                    // print out values to check
                    if (i % 4 == 3) {
                        System.out.println(tiles[i].getRank());
                    } else {
                        System.out.print(tiles[i].getRank() + " ");
                    }
                    
                    // newTurn() method sets the fields mergedThisTurn, 
                    // changedThisTurn and newTile all to false, preparing for 
                    // the next keypress
                    tiles[i].newTurn();
                    
                    // if tile is not empty
                    if (tiles[i].getRank() != 0) {
                        // increment current number of filled tiles
                        numFilled++;
                    }

                    // if tile is same as any tile in any of the 4 directions,
                    // there are possible moves
                    if (tiles[i].isSame(tiles[i].left) || 
                        tiles[i].isSame(tiles[i].right) ||
                        tiles[i].isSame(tiles[i].up) || 
                        tiles[i].isSame(tiles[i].down)) {
                        // increment current number of tiles with possible moves
                        tilesWithPossibleMoves++;
                    }  
                    
                    // if any tile is 2048, the player wins
                    if (tiles[i].getRank() == 11) {
                        win = true;
                    }
                }
                
                // if all 16 tiles are filled and none of them has possible 
                // moves, the player loses
                if (numFilled == 16 && tilesWithPossibleMoves == 0) {
                    lost = true;
                }
                
                firstTime = false;
            }
            PennDraw.advance(); // display next frame
        }
        
        PennDraw.disableAnimation();
        
        // draw text containing a failure message
        PennDraw.setPenColor(143, 122, 102);
        PennDraw.setFontSize(40);
        // add a translucent layer to make text more visible
        PennDraw.clear(250, 248, 239, 200);
        
        // draw victory/failure message
        if (lost == true) {
            PennDraw.text(0.5, 0.6, "Game Over");
        } else if (win == true) {
            PennDraw.text(0.5, 0.6, "Congratulations!");
        }
        PennDraw.text(0.5, 0.4, "You made " + numMoves + " moves");
    }
    
    /*
     * Returns the index of an empty tile, randomly chosen
     * Input: array of Tile objects
     * Output: int of index of randomly chosen empty tile
     */
    private static int getRandomEmpty(Tile[] tiles) {   
        int randomIndex = new Random().nextInt(tiles.length);
        
        // choose again if the chosen tile is not empty
        while (tiles[randomIndex].getRank() != 0) {
            randomIndex = new Random().nextInt(tiles.length);
        }

        return randomIndex;
    }
}