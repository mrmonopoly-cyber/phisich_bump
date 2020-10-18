package com.mycompany.phisics_simulation;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.BiPredicate;
import javax.swing.JFrame;


public class Mouse extends MouseAdapter{

private enum Status{
        CLICKED,NOTCLICKED
    }
    
    private JFrame window;
    private Info inf;
    private Status stat = Status.NOTCLICKED;
    private BiPredicate<Integer,Integer> verification; 
    private Position corpPosition = new Position();
    
    /*
    OBTAIN click of the mouse and modify the currently window based on the click:
        - if ,where i click, there is not a corp the metod put in that position a corpo
        - if ,where i click, there is a corp the metod memorize change the state and wait another click to set destinatio and velocity of the corp 
    @param MoueseEvent e = event click of the mouse
    */
@Override
    public void mouseClicked(MouseEvent e){
        
        int x = (e.getX()/10)-1;
        int y = (e.getY()/10)-1;
        Board board = inf.getBoard();
        int dim = board.dim();
        verification = (a,b) ->{
            return(a<dim) && (b<dim) && (a>-1) && (b>-1);
        };
       
        if(stat == Status.CLICKED){
            movement(x,y);
            stat = Status.NOTCLICKED;
        }else{
            if(verifyPut(x,y)){
                corpPosition.setX(x);
                corpPosition.setY(y);
                PutOrSave(x,y,inf);
            }
        }
        
        
    }
    
    /*
    MODIFY the corp with coordinate corpPosition adding  destination and velocity wich is calculating with pitagora
    @parama x,y = coordinates in the board of the mouse Click
    */
    private void movement(int x,int y){
        
        Position destination = new Position(x,y);
        Corp current = inf.SearchCorp(corpPosition);
        setVelocity(current,destination);
        current.setDestination(destination);
        update(inf);
        
    }
    
    /*
    SET and CALCULATE the velocity of the Corp corp using pitagora beetwen two Position: destination and CorpPosition(istance variable)
    @param corp = corp to set
    @param = coordinate for pitagora
    */
    private void setVelocity(Corp corp,Position destination){
        int distance =(int) pitagora(corpPosition,destination,0,0);
        if(distance > 20){
            distance = 20;
        }
        corp.setVelocity(distance);
    }
    
    /*
    RETURN the distance of pitagora beetwen two points
    @param current = first position
    @param dsetination = second position
    @param x1,y1,x2,y2 = translation parameters
    */
    protected static long pitagora(Position current,Position destination,int x1,int y1){
        int currentX = current.getX()+x1;
        int currentY = current.getY()+y1;
        int destinationX = destination.getX();
        int destinationY = destination.getY();
        
        return (long) Math.sqrt( (long) Math.pow( (long)(currentX-destinationX),2) + (long)Math.pow( (long)(currentY-destinationY),2));  //determina la velocità
               
    }
    
    /*
    VERIFY if coordinate are valide and then save coordinate, then if put a corp or save the state
    @param x,y = coordinate of the click of the mouse
    @param inf = object with all the info
    @param trash = use only to reclaim the methods
    */
    private boolean verifyPut(int x,int y){
        int a=x;
        int b=y;
        if(verification.test(a,b) ){
                return true;
            }else{
                return false; 
            }
    }
    
    /*
    DECIDE if put a corp in coordinate x,y or save the state if there is already a corp
    @param x,y = coordinate of corp to add
    @param inf = object with all the info of corps,board,list of corps on board
    @param trash = object used only to call methods
    */
    private void PutOrSave(int x,int y,Info inf){
        Screen trash = new Screen();
        Board board = inf.getBoard();
        if(board.verifyPos(corpPosition)){
            stat = Status.CLICKED;
            
        }else{
            putACorp(x,y);
            inf.setWindow(window);
            update(inf);
        }
    }
    
    /*
    MODIFY the window currently open adding a corp in position x,y
    @param = first coordinate of corp to add
    @param = second coordinate of corp to add
    */
    private void putACorp(int x,int y){
        Position pos = new Position(x,y);
        Corp temp = new Corp(pos);
        inf.addCorp(temp);
        inf.setWindow(window);
        update(inf);
    }
    
    /*
    CHANGE the board 
    */
    protected void changeInfo(Info inf){
        this.inf=inf;
    }
    
    /*
    SET the variable window of the object
    @param window = graphic window actually rappresented
    */
    protected void setWindow(JFrame window){
        this.window = window;
    }
    
    /*
    SEND the request to update the window
    @param inf = object with all the info
    */
    private void update(Info inf){
        Screen trash = new Screen();
        
        trash.refresh(inf);
    }
    
}
