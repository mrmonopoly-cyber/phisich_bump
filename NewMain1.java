package com.mycompany.phisics_simulation;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;




public class NewMain1 extends JPanel{
    private board b;
    
    @Override
    public void paint(Graphics g){
        
        boolean[][] tav = b.array();                        //array del quadrato
        
        g.drawRect(10,10,1000,1000);                        //disegno enorme quadrato
        g.setColor(Color.red);  
        
        
        for(int x=0;x<tav.length;x++){
            for(int y=0;y<tav.length;y++){
                if(tav[x][y] != false){
                    g.fillRect((x+1)*10, (y+1)*10, 10, 10); //disegno corpo
                 }
            }}
        
    }
    
    public static void window(simulatore sim) {     //metodo statico principale per creazione della finestra e avvio del programma
        
        JFrame window = new JFrame();               //nuova finestra
        mouse cursore = new mouse();
        NewMain1 image = new NewMain1();            //crea oggetto grafico 
        board tav = sim.tav();
        
        //-----------------------------
        
        cursore.addS(sim);                          //aggiunge il sim all'oggetto mouse
        
        //------------------------------
        image.b = tav;                      
        image.repaint();                            //aggiorna oggetto grafico
        
        //caratteristiche della finestra
        
        window.setSize(400, 400);                                   //dimensione
        window.setVisible(true);                                    //rende visibile
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      //concludi programma alla chiusura del programma
        cursore.addW(window);
        //fine settaggio finestra
        
        window.getContentPane().addMouseListener(cursore);          //aggiunge il mouse
        window.getContentPane().add(image);                         //aggiunge immagine
        
        
        
    }
    
    
    
     public static void refresh(JFrame window,simulatore sim){        //aggiorna la finestra
        
        
        NewMain1 image = new NewMain1();                                        //nuovo ggetto della classe grafica
        board tav = sim.tav();                                                  //ricavo array di posizione corpi da oggetto generale
        image.b = tav;                                                          //imposto array riferimento per posizione corpi 
        image.repaint();                                                        //ricreo l'immagine
        
        
        
        window.getContentPane().add(image);                                     //sovrappondo immagine appena creata alla finestra corrente
        window.repaint();                                                       //aggiorno finestra
        mover(window,sim);
        
        
    }
 
    public static void mover(JFrame window, simulatore sim){         //metodo principale per movimento
        
        
        board tav = sim.tav();
        NewMain1 image = new NewMain1();
        image.b = tav;
        
        
                        
            ArrayList<corp> list = sim.corps();                     //lista di tutti i corpi nella simulazione
            
            for(int count=0;count<list.size();count++){             //scorrimento della lista
                
                
                
                corp temp = list.get(count);                        //si ottiene il corpo da analizzare
                int vel = temp.velocity();                          //velocità del corpo in questione
                
                
                if(vel != 0){
                    int[] pos = temp.pos();             //ricava posiziona attuale del corpo nello spazio
                    double[] verso = temp.vers();          //ricava il verso del corpo
                    boolean dir = temp.dir();
                    tav.remove(pos);                    //lo rimuove dallo spazio
                    
                    if(dir == true){
                        pos[0] = pos[0]+1;
                        
                    }else{
                        pos[0] = pos[0]-1;
                    }
                    double[] pos1 = new double[1];
                    pos1[0] = (verso[0]*pos[0]) + verso[1];
                    
                    pos[1]=(int)pos1[0];
                    
                    temp.changePos(pos);                //aggiorna la posizione del corpo
                    temp.changeVel(vel-1);              //dimunuisce la velocità del corpo di 1
                    list.set(count,temp);               //aggiorna il corpo nella lista
                    sim.changeList(list);               //aggiorna la lista dell'oggetto
                    tav.add(pos);                       //aggiunge la posizione attuale del corpo traslato al corpo
                    sim.changeBoard(tav);               //aggiorna la board dell'oggetto
                        
                    refresh(window,sim);                //ricorsione infinita attenzione!!! da modificare con un if
                    
                }
            }
            
            
            
    }
    
}
    
    

