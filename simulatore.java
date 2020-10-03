package com.mycompany.phisics_simulation;

import java.util.*;


public class simulatore {

    
    private ArrayList<corp> corps;                                   //lista dei corpi nella board
    private board tav;    
    
    public simulatore(){
        corps = new ArrayList<corp>();
        tav = new board(100);
    }
    
    public void add(corp corp1){
        this.corps.add(corp1);
        tav.add(corp1.pos());
    }
    
    public board tav(){
        return this.tav;
    }
    
    public ArrayList<corp> list(){
        return this.corps;
    }
    
    public ArrayList<corp> corps(){
        return corps;
    }
    
    public void changeList(ArrayList<corp> list){
        this.corps = list;
    }
    
    public void changeBoard(board tav) {
        this.tav = tav;
    }
    
    public int searchCorp(int[] pos){                       //restituisce la posizione di un corpo nella lista
        
        for(int x=0;x<corps.size();x++){                    //scorre tutta la lista
            corp temp = corps.get(x);
            if(temp.pos() == pos){
                return x;                                   //se lo trova riporta la posizione 
            }
        }
        return -1;                                          //se non lo trova riporta -1
    }
    
    //-------------------------------------------------------------------------
    public static void main(String[] args) {
        simulatore sim = new simulatore();
        sim.corps = new ArrayList();
        sim.tav = new board(100);
        
        
        NewMain1.window(sim);                                          //nuova finestra
        
    }

   
}

