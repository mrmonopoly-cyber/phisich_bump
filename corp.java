package com.mycompany.phisics_simulation;

public class corp{
  
  private boolean direction;                   //dx = true ;sx = false
  private  int vel;
  private  double vers[];
  private  int[] pos = new int[2];
  
  public corp(int[] pos){                          //costruttore
      this.pos = pos;
  }
  
  public corp(int vel,double[] verso,int[] pos){//costruttore
    this.pos=pos;
    this.vel = vel;
    this.vers = verso;
    // il verso è orientato seguendo 2 assi cartesiani e le 2 diagonali, i numero da 0 a 3 indicano i casi positivi partendo dall'asse x, i numeri 
    
  }
  
    public void changeVel(int vel){                     //metodo che cambia la velocità del corpo
        this.vel = vel;
    }  

    public void changeVer(double[] pos,boolean dir){       //metodo che cambia il verso del corpo
        this.vers =pos ;
        this.direction = dir;
    }

    public void changePos(int[] pos){                 //metodo che cambia la posizione del corpo
        this.pos = pos;
    }
    
    public int velocity(){                              // metodo che riporta la velocità del corpo
        return vel;
    }

    public double[] vers(){                                  //metodo che ritorna il verso del corpo
        return vers;
    }

    public int[] pos(){                                 //metodo che ritorna la posizione in un array
        return pos;
    }
    
    public boolean dir(){
        return this.direction;
    }
    
}