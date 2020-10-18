/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.phisics_simulation;

import java.util.function.BiPredicate;


public class Position {
    
    private int x;
    private int y;
    private final BiPredicate<String,String> equals = (a,b) -> (a.equals(b));
//costructor = create an empty point
    protected Position(){
    }
    
    //costructor = create a point with coordinates
    protected Position(int x,int y){
        this.x=x;
        this.y=y;
    }
    
    //RETURN coordinate x
    protected int getX(){
        return this.x;
    }
    
    //RETURN coordinate y
    protected int getY(){
        return this.y;
    }
    //SET variable x 
    protected void setX(int x){
        this.x=x;
    }
    //set variable y
    protected void setY(int y){
        this.y=y;
    }
    
    /*
    verify if two positions are equal
    */
    protected boolean equals(Position pos){
        String string1 = ""+this.getX() + this.getY();
        String string2 = ""+pos.getX() + pos.getY();
        return equals.test(string1,string2);
    }
}
