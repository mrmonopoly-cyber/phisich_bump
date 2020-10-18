package com.mycompany.phisics_simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.util.function.BiPredicate;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Screen extends JPanel{
    
    private enum DIRECTION{
        UP,DOWN,RIGHT,LEFT,RIGHT_UP,RIGHT_DOWN,LEFT_UP,LEFT_DOWN
    }
    
    private Board tav;
    private final DIRECTION[] dir = new DIRECTION[8];
        
            
    /*
    MODIFY image:create a square and put on red the position of a corp
    @param image = Graphic object;
    */
    @Override
    public void paint(Graphics image){
        boolean[][] positions = tav.getArray();   //position of the corps
        int dim = positions.length;
   
        image.drawRect(10,10,dim*10,dim*10);                        //disegno enorme quadrato
        paintCorps(image,positions,dim);
    }
    
    /*
    MODIFY the object adding the graphic rappresentation of corps
    @parama image = graphic object
    @positions = info about all positions of the board
    @dim = dimension of board
    */
    private void paintCorps(Graphics image,boolean[][] positions,int dim){
        image.setColor(Color.red);
        
        for(int x=0;x<dim; x++){
          for(int y=0;y<dim; y++){
              if(positions[x][y]){
                  image.fillRect((x+1)*10,(y+1)*10,10,10);
              }
          }}
    }
    
    
    /*
    MODIFY the param of the object this.tav with tav
    @param tav = object with all position of the square
    */
    protected void changeBoard(Board tav){
        this.tav = tav;
    }
    
    
    /*
    RETURN the dimension of the board
    */
    
    
    
    /*
    CREATE a new window
    @param screen = object with the info of window
    */
    private JFrame createWindow(Screen screen,Mouse mouse){
        JFrame window = new JFrame();
        window.setSize(400,400);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);
        mouse.setWindow(window);
        window.getContentPane().add(screen);
        window.getContentPane().addMouseListener(mouse);
        window.pack();
        
        
        return window;
    }
    
    /*
    CREATE a square were the corps are
    @param dim  = dimension of square
    @param tav = object with info  of the position of the corps
    */
    private Screen createScreen(Info inf){
        Screen screen = new Screen();
        Board tav = inf.getBoard();
        
        screen.changeBoard(tav);
        screen.repaint();
         
        return screen;
    }
    
    /*
    CREATE the control of the mouse
    @param dim = dimension of the square were the corps are
    @param tav = object with all position of the cops
    */
    private Mouse createMouse(Info inf){
        Mouse mouse = new Mouse();
        mouse.changeInfo(inf);
        
        return mouse;
    }
    
    /*
    RETURN the position were  the corp will ipotetically move
    @param current = corp to move
    */
    private Position nextPosition(Corp corp){
        Position current = corp.position();
        Position destination = corp.destination();
        
        long[] ipotetic = ipoteticMoves(current,destination);
        DIRECTION best = best(ipotetic);
        Position nextPos = shift(current,best);
        return nextPos;
    }
    
    /*
    MODIFY the coorindates of current 
    @param current = coordinates to modify
    @param dir = instruction of how change it
    */
    private Position shift(Position current,DIRECTION dir){
        
        int x = current.getX();
        int y = current.getY();
        
        switch(dir){
            case RIGHT :
                x++;
                break;
            case LEFT :
                x--;
                break;
            case DOWN :
                y++;
                break;
            case UP :
                y--;
                break;
            case RIGHT_DOWN :
                x++;
                y++;
                break;
            case RIGHT_UP :
                x++;
                y--;
                break;
            case LEFT_DOWN :
                x--;
                y++;
                break;
            default :
                x--;
                y--;
        }
        Position nextPos = new Position(x,y);
        
        return nextPos;
    }
    
    /*
    RETURN an index rappresenting the best direction
    */
    private DIRECTION best(long[] ipotetic){
        if(dir[0] == null){
            allDirections();
        }
        long distance = ipotetic[0];
        int x=1;
        int best = 0;
        while(x<8){ 
            if(ipotetic[x]<= distance){
                distance = ipotetic[x];
                best = x;
            }
            x++;
        }
        return dir[best];
    }
    
    /*
    FILL the variable dir with the all the enum DIRECTION
    */
    private void allDirections(){
        dir[0] = DIRECTION.RIGHT;
        dir[1] = DIRECTION.LEFT;
        dir[2] = DIRECTION.DOWN;
        dir[3] = DIRECTION.UP;
        dir[4] = DIRECTION.RIGHT_DOWN;
        dir[5] = DIRECTION.RIGHT_UP;
        dir[6] = DIRECTION.LEFT_DOWN;
        dir[7] = DIRECTION.LEFT_UP;
    }
    
    /*
    RETURN all the distance in all the possible directions
    @param current = start position
    @param destination = destination position
    */
    private long[] ipoteticMoves(Position current,Position destination){
        long[] ipotetic = new long[8];
        
        ipotetic[0] = Mouse.pitagora(current, destination,1,0); //dx
        ipotetic[1] = Mouse.pitagora(current, destination,-1,0);//sx
        ipotetic[2] = Mouse.pitagora(current, destination,0,+1);//down
        ipotetic[3] = Mouse.pitagora(current, destination,0,-1);//up        
        ipotetic[4] = Mouse.pitagora(current, destination,1,1);//dx down
        ipotetic[5] = Mouse.pitagora(current, destination,1,-1);//dx up
        ipotetic[6] = Mouse.pitagora(current, destination,-1,+1);//sx down        
        ipotetic[7] = Mouse.pitagora(current, destination,-1,-1);//sx up
        
        return ipotetic;
    }
    
    /*
    CREATE a window with the square were place the corps
    @param inf = object with the info of the square
    */
    protected static void window(Info inf){
        Screen trash = new Screen(); 
        
        Screen screen = trash.createScreen(inf);
        Mouse mouse = trash.createMouse(inf);
        JFrame window = trash.createWindow(screen,mouse);
        
        
    }
    
    /*
    MODIFY the window with the information in inf
    @param window = window actually rappresented
    @param inf = object with all information about corps and their position
    */
    protected void refresh(Info inf){
        JFrame window = inf.getWindow();
        Screen screen = new Screen();
        Board board  = inf.getBoard();
        screen.changeBoard(board);
        screen.repaint();
        
        window.getContentPane().add(screen);
        window.repaint();
        inf.setWindow(window);
        
        verifyMove(inf);
        
    }
    
    /*
    MOVE the corps wich has velocity>0 to destination (destination and  velocity are variable inside the object corp)
    @param inf = object with all the info about the corps
    */
    protected void verifyMove(Info inf){
        for(int count=0;count<inf.numberOfCorps();count++){
            Corp corp = inf.getCorp(count);
            if(corp.isMoving()){
                move(inf,count,corp);
            }
        }
    }
    
    /*
    ORGANIZE the movement of the variable corp
    @param count = index of corp in the list of inf
    @param corp = corp to move
    @param inf = object with all information
    */
    private void move(Info inf,int count,Corp corp){
        int dim = inf.getDim();
        Board board = inf.getBoard();
        Position current = corp.position();
        Position ipotetic = nextPosition(corp);
        //board.remove(current);
                   
        int x = current.getX();
        int y = current.getY();
        BiPredicate<Integer,Integer> nearWall = (a,b) -> ( (a==0) || (b==0) || (a==dim-1) || (b==dim-1) );
               
        if(nearWall.test(x,y)){                 //hit wall
                        
        }else if(board.verifyPos(ipotetic)){   //hit another corp
                        
        }else{                                  //hit nothing
        noHit(inf,corp,board,ipotetic,count);
        }
        refresh(inf);
    }
    
    /*
    MODIFY stats of the corp corp in case there is no hit wih nothing
    @param inf = objec with all the info about corps and board
    @param corp = corp to modify
    @param board = updated board 
    @Param ipotetic = next position of the corp
    @param count = index of the position of corp in the list of the object inf
    */
    private void noHit(Info inf,Corp corp,Board board,Position ipotetic,int count){
        
        int vel = corp.velocity();
        corp.update(ipotetic,vel-1,corp.destination());
        board.add(ipotetic);
        inf.changeBoard(board);
        inf.ChangeCorp(count,corp);
    }
    
}
