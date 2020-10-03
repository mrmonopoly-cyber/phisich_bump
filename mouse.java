package com.mycompany.phisics_simulation;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import java.util.*;

public class mouse extends  MouseAdapter {
    
  
    private boolean alt = false;
    private JFrame window;
    private final int[] start = new int[2];
    private simulatore sim;
    
    
    @Override
   public void mouseClicked(MouseEvent e){
       int x = (e.getX()/10)-1;
       int y = (e.getY()/10)-1;
      
                    
       
       if( (x>=0)&&(x<=99)&&(y>=0)&&(y<=99) ){            //il click deve essere compreso tra 0 e 99 perchè sia valido
           boolean[][] array = sim.tav().array();
           
           if(alt == false){
           start[0] = x;
           start[1] = y;
           
           
           
            if(array[x][y] == false){                   //verifica presenseza corpo
                                                                 
                        corp temp = new corp(start);    //crea il nuovo corpo da aggiungere
                        sim.add(temp);                  //lo aggiunge alla lista
                        NewMain1.refresh(window,sim);   //refresh della finestra
                        
                    
                    
            }else{                 
                        alt =true;                      //salva in memoria lo stato
            }
            
           }else{//alt == true                          //corpo già selezionato
               
               int distance = (int) Math.sqrt( (int) (Math.pow((start[0]-x),2) + (int)Math.pow( (start[1]-y),2)));  //determina la velocità
               int pos = sim.searchCorp(start);                 //posizione del corpo nella lista
               ArrayList<corp> list = sim.list();               //lista dei corpi
               corp temp = list.get(pos);                       //corpo nella posizione interessata
               
               //gestione verso
               
               //verso.direction
               boolean dir = true;              //di default va verso dx
               
               double m=((start[1]-y)/(start[0]-x));      //coefficiente angolare
               double q = y - (m*x);                      //y = mx+q
               double[] vers = new double[2];
               
               vers[0] = m;
               vers[1] = q;
               
               
               if(start[0]>x){                  // va verso destra
                   dir = false;
               }
               //fine verso.direction
               
               //verso.vers    
               
               //fine verso.vers
               temp.changeVer(vers,dir);
               
               //fine gestione verso
               
               //gestione velocità
               if(distance >20){                                //cambia la velocità del corpo ponendola 0<=vel<=20 
                   temp.changeVel(20);
               }else{
                   temp.changeVel(distance);
               }
               //fine gestione velocità
              
               //aggiornamento
               list.set(pos, temp);                             //aggiorna la lista con il corpo aggiornato
               sim.changeList(list);                            //aggiorna l'oggetto comune
               NewMain1.refresh(window,sim);                    //refresh della schermata
               alt = false;                                     //ripone condizione di ascolto per nuovi corpi
               //fine aggiornamento
           }
           
          
       }
   }
      
   
   //----------------------------------------------------------
   
   public void addW(JFrame window){
       this.window = window;
   }
   
   public void addS(simulatore sim){
       this.sim = sim;
   }
   
}
