package com.mycompany.phisics_simulation;


import java.util.ArrayList;
import javax.swing.JFrame;

public class Info {
    
    private ArrayList<Corp> list;           //lista dei corpi nella board
    private Board tav;                      //object with all position of the square
    private int dim ;                      //dimension of square
    private JFrame window;
    
    /*
    custructor
    @param list = new list of corps
    @param tav = new space were put the corps
    */
    protected Info(){
        list = new ArrayList<>();
        tav = new Board(dim);
    }
    
    /*
    RETURN the list of the corps
    */
    protected ArrayList<Corp> getArray(){
        return list;
    }
    
    /*
    MODIFY list adding a new corp
    */
    protected void addCorp(Corp corp){
        list.add(corp);
        Position position = corp.position();
        tav.add(position);
    }
       
    /*
    RETURN the board with the position of the corps
    */
    protected Board getBoard(){
        return tav;
    }
    
    /*
    RETURN the dimension of the square
    */
    protected int getDim(){
        return this.dim;
    }
    
    /*
    SET the variable dim
    */
    protected void setDim(int dim){
        this.dim = dim;
    }
    
    /*
    SET the content of the variable window
    @param = window actually rappresented
    */
    protected void setWindow(JFrame window){
        this.window = window;
    }
    
    /*
    RETURN the variable window of the object
    */
    protected JFrame getWindow(){
        return this.window;
    }
    
    /*
    RETURN the index or the corp in the list of the corp or RETURN -56
    @param corp = corp to search
    */
    private int corpIndex(Corp corp){                                            //orribile da sistemare ripetizione di codice
        for(int index =0;index<list.size();index++){
            Corp current = list.get(index);
            Position currentPos = current.position();
            
            if(currentPos.equals(corp.position())){
                return index;
            }
        }
        return -56;
    }
    
    /*
    RETURN the corp with coordinate pos in the list 
    @param pos = coordinate of corpo to search
    */
    protected Corp SearchCorp(Position pos){                                    //orribile da sistemare ripetizione di codice
        int index=0;
        while(index<list.size()){
            
            Corp current = list.get(index);
            Position currentPos = current.position();
            if(currentPos.equals(pos)){
                return current;
            }
            index++;
        }
        return null;
    }
    
    /*
    CHANGE the variable tav
    @param tav = variable to set
    */
    protected void changeBoard(Board tav){
        this.tav=tav;
    }
    
    /*
    MODIFY the list of corps modifing a corp in the list
    @param index = position of the corp in the list
    @param corp = corp to replace in the list
    */
    protected void ChangeCorp(int index,Corp corp){                             //brutto
        list.set(index, corp);
    }
    
    /*
    RETURN the corp at a specific position(index) in the list
    @param index = position of the corp in the list
    */
    protected Corp getCorp(int index){
        return list.get(index);
    }
    
    /*
    RETURN the number of corps in the list
    */
    protected int numberOfCorps(){
        return list.size();
    }
    
    /*
    starting function= create a new window witch simulate the hit of corps in a space
    */
    public static void main(String[] args) {
        Info inf = new Info();
        Board tav = new Board(50);
        inf.tav = tav;
        inf.dim = tav.dim();
        Screen.window(inf);
    }
    
    
}
