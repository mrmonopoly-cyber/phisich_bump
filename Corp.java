package com.mycompany.phisics_simulation;

public final class Corp {
    
    private final Position position = new Position();
    private final Position destination = new Position();
    private int velocity;
                                                                     
    /*
    COSTRUCTOR
    @param position = position of the corps in an array
    */
    protected Corp(Position pos){
        this.setPosition(pos);
    }
    /*
    RETURN the value of velocity
    */
    protected int velocity(){
        return this.velocity;
    }
    
    /*
    SET the velocity of the corp
    */
    protected void setVelocity(int vel){
        this.velocity = vel;
    }
    
    /*
    RETURN if the corp is moving 
    */
    protected boolean isMoving(){
        return this.velocity>0;
    }
    
    /*
    SET the variable destination
    @param destination = coordinate of the point to achive
    */
    protected void setDestination(Position dest){
        int x = dest.getX();
        int y = dest.getY();
        this.destination.setX(x);
        this.destination.setY(y);
    }
    
    protected Position destination(){
        return destination;
    }
    
    /*
    RETURN the current posistion of the corp
    */
    protected Position position(){
        return this.position;
    }
    
    protected void setPosition(Position pos){
        int x = pos.getX();
        int y = pos.getY();
        this.position.setX(x);
        this.position.setY(y);
    }
    
    /*
    MODIFY the variable of the object
    @param current = new current position
    @param velocity = new current velocity
    @param destination = new current destination
    */
    protected void update(Position current,int velocity,Position destination){
        this.setDestination(destination);
        this.velocity = velocity;
        this.setPosition(current);
    }
}
