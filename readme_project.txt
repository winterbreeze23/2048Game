/**********************************************************************
 *  readme template                                                   
 *  Project
 **********************************************************************/

Name: Wenhan Zhang
PennKey: zwenhan
Recitation: 205
Project choice: 2048



/**********************************************************************
 *  Have you entered all help, collaboration, and outside resources
 *  in your help log?  If not, do so now.  (And in future, make sure
 *  you make your help log entries as you go, not at the end!)
 *
 *  If you did not get any help outside of TA office hours,
 *  and did not use any materials outside of the standard
 *  course materials and piazza, write the following statement below:
 *  "I did not receive any help outside of TA office hours.  I
 *  did not collaborate with anyone, and I did not use any
 *  resources beyond the standard course materials."
 **********************************************************************/

    I found the java class Random class online and used it to randomly select an
    empty tile. I have entered it in the help log.
    
    

/**********************************************************************
 *  How do you execute your program? Which class do you run, are there
 *  any command line arguments?
 **********************************************************************/

    Run the Game2048 class without command line arguments.


/**********************************************************************
 *  Did you add any additional features to your project beyond the
 *  specification that you added? If so, describe them here.
 **********************************************************************/

    I implemented some of the more subtle logic and rules that are present in 
    the real 2048 game. 
    - Each tile is only allowed to merge once, i.e. increment by one power of 2,
      every turn
    - A new tile will only appear if a valid move has been made. e.g. if the 
      tiles cannot be shifted or merged left, pressing 'a' will not cause a new 
      tile to appear
    - A new tile appears at the start of the execution even when no key has been
      pressed
    - The text color of a new tile will be red to help the user identify which
      one is the new tile every turn



/**********************************************************************
 *  Explain how you went about approaching the problem using
 *  object oriented programming.
 **********************************************************************/

    Two breakthroughs came to me while programming the game.
    
    The first is thinking of the grid as 16 fixed tiles whose values change
    instead of having a list of tiles moving around and changing values. This
    makes the logic and implementation a lot simpler. 
    
    The second is adding references to each tile in four directions. The 
    references work just like the next or prev references of nodes. By adding 
    these direction references, I immediately can organize the array of 16 tiles
    in rows and columns and perform the checks and actions for shift and merge.
    This idea came to me as I was thinking about what data structure to use. 
    I wanted to use an array but arrays do not have references to other objects;
    I wanted to use a linked list but I do not need to dynamically resize the 
    list. It then occur to me to use an array but to add in references to the 
    other tiles in four directions. 
        
    As a result, I have 4 errors in the form of: Variable 'up' must be private 
    and have get/set methods. I hope these 4 errors can be excused because it
    is my wish to use "fields" of left, right, up and down as references like 
    the next and prev references of nodes in linked lists. The references of 
    nodes are public too. I cannot make them private as I want to access them 
    from the Game2048 class. 
        
    I also represent the numbers by just the powers of 2. Power 0 is an empty
    tile, power 1 is number 2, power 2 is number 4, and so on. To me this 
    simplifies the implementation, especially when checking the printed output
    of the tiles every turn. 
    

/**********************************************************************
 *  List all the files in project.zip and explain their purpose.
 **********************************************************************/


    Game2048.java - the main class of the game
    Tile.java - the class for the Tile object

 
/**********************************************************************
 *  Please explain how we should use your program.                    
 **********************************************************************/

    Run: java Game2048
    Use w, s, a, d to move up, down, left and right respectively


/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/

    As I mentioned above in my approach, 
    I have 4 errors in the form of: Variable 'up' must be private 
    and have get/set methods. I hope these 4 errors can be excused because it
    is my wish to use "fields" of left, right, up and down as references like 
    the next and prev references of nodes in linked lists. The references of 
    nodes are public too. I cannot make the direction references private as I 
    want to access them from the Game2048 class. 
        
    Really enjoyed the project!
 
 
 
