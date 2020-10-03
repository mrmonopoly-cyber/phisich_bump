package com.mycompany.phisics_simulation;

public class board {                            //tavola di riferimento per posizione oggetti
    
    
    
    public boolean[][] board;                   
    
    public board(int b){//costruttore della board dove verranno inseriti i corpi 
         
        board = new boolean[b][b];
            for(int x=0;x<10;x++){
                for(int y=0;y<10;y++){
                    board[x][y] = false;
                }}
    }
    
    public void add(int[] pos){//il metodo aggiunge un corpo nelle coordinate trovate nell'array
        board[pos[0]][pos[1]] = true;
        
    }
    
    public void remove(int[] pos){//il metodo rimuove un corpo nelle coordinate trovate nell'array
        board[pos[0]][pos[1]] = false;
    }
    
    public boolean[][] array(){
        return this.board;
    }
    
}
