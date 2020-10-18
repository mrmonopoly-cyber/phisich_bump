package com.mycompany.phisics_simulation;

public class Board{

    private boolean[][] board;
    
    /*
    COSTRUCTOR : create a new array 
    */
    protected Board(int dim){
        board  = new boolean[dim][dim];
    }
    
    /*
    RETURN the array of the positions
    */
    protected boolean[][] getArray(){
        return board;
    }
    
    /*
    MODIFY board = change the array of the positions of corps
    @param board = array with positions
    */
    protected void changeBoard(boolean[][] board){
        this.board = board;
    }
    
    /*
    MODIFY board : add a position to board
    */
    protected void add(Position pos){
        int x=pos.getX();
        int y=pos.getY();
        
        board[x][y]= true;
    }
    
    /*
    MODIFY board : remove a position from board
    */
    protected void remove(Position pos){
        int x = pos.getX();
        int y = pos.getY();
        
        board[x][y] = false;
    }
    
    /*
    VERIFY if in pos there is a corp
    */
    protected boolean verifyPos(Position pos){
        int x = pos.getX();
        int y = pos.getY();
        
        return board[x][y];
    }
    
    /*
    RETURN dimension of board
    */
    protected int dim(){
        return this.board.length;
    }
    
}
