/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manouvre.game;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *  Nation
 *  int BR  = 0; //GreatBritain
    int FR  = 2; //France
    int RU  = 5; //Russland
    int PR  = 4; //Prussia
    int AU  = 1; //Austria
    int SP  = 6; //Spain
    int OT  = 3; //Ottoman
    int US  = 7; //USA
 * @author Piotr
 */
public class Player  implements Serializable{
    
    private static final long serialVersionUID = 43211L;
    String name;
     
    public enum Nation {
        BR(0, "Great Brittain"), AU(1, "Austria"), FR(2, "France"), OT(3, "Ottoman Empire"), PR(4 , "Prusia"), RU(5, "Russia"), SP(6, "Spain"), US(7,"USA"); 

        int number ;
        String name;

        Nation(int number, String name){
            this.number = ordinal();
            this.name = name;
        }

        final public int getNumber(){
        return number;
        }

        public static Nation fromValue(int value){
            return Nation.values()[value];
        }

       
        
        @Override
        public String toString(){
        return name;
        }
        String getShortName()
        {
            return name();
        }
    
    };
    
    Nation nation; //for nation description see Card
    
    CardSet hand;
    CardSet drawPile;
    CardSet discardPile;
    CardSet tablePile;
    /*
    Field to store whole card flow needed data.
    */
    CardCommandFactory cardCommandFactory ;
 
    /*
    Is player currently playing and is first player
    */
    boolean active, first, moved, draw , attacked;
    
    Unit[] army;
    
    int score;
    
    Unit lastMovedUnit;
 
    boolean host, finishedSetup;

    public Player(String name) {
       this.name = name;
       this.active = true;
       this.score = 0;
    }
    public void resetPlayer(){
    
    for(Unit unit : getArmy()) {
        if(unit.hasMoved())
             unit.setMoved(false);
        if(unit.isSelected())
            unit.setSelected(false);
        if(unit.isRetriving())
            unit.setRetriving(false);
        if(unit.isSupporting())
            unit.setSupporting(false);
        if(unit.getRestorationDice()!= null)
            unit.setRestorationDice(null);
        }   
        setMoved(false);
        setDraw(false);
        hasAttacked(false);
        getHand().unselectAllCards();
        setLastMovedUnit(null);
    }

       
    public void setCards() {
        this.drawPile = new CardSet(nation ,"DRAW"); 
        this.hand = new CardSet(nation ,"HAND"); 
        this.discardPile = new CardSet(nation ,"DISCARD"); 
        this.tablePile = new CardSet(nation ,"TABLE");
        if(nation==Nation.AU){
            hand.addCard(drawPile.getFirstCardByName("Committed Attack", true));
            hand.addCard(drawPile.getFirstCardByName("Guerrillas", true));
            hand.addCard(drawPile.getFirstCardByName("Ambush", true));
            hand.addCard(drawPile.getFirstCardByName("Duke of Schwarzenberg", true));
            hand.addCard(drawPile.getFirstCardByName("4th  Regiment", true));
        }
        else if(nation==Nation.FR){
            hand.addCard(drawPile.getFirstCardByName("Joachim Murat", true));
            hand.addCard(drawPile.getFirstCardByName("French Sappers", true));
            hand.addCard(drawPile.getFirstCardByName("Forced March", true));
            hand.addCard(drawPile.getFirstCardByName("Supply", true));
            hand.addCard(drawPile.getFirstCardByName("Supply", true));
        } 
        else
        drawPile.moveTopXCardsTo(5, hand);
    }
    public void generateUnits(){
     
        army = new Unit[8];
        /*
        If its host then place units on B row else place unit on G row
        */
        int y=0;
      for (int x=getNation().getNumber()*8  ;x<getNation().getNumber()*8+8;x++)
        {
            Unit unit =  new Unit(x+1);
          /*
            Startting position row B or G
            */
            unit.setPosition(new Position (  x- ( getNation().getNumber()*8)    , ( isHost() ? Position.ROW_2 : Position.ROW_7) ));
            unit.setOwner(this);
             army[y] =   unit  ;      
              y++;     
         }
 
    }
    
    /*
    Getters and setters
    */
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
 
    public void setNation(Nation nation) {
      //  System.out.println("Nation Set to:"+nation);
        this.nation = nation;
    }
    
    public Nation getNation() {
        return nation;
    }
    
    public CardSet getDrawPile() {
        return drawPile;
    }
    
    public CardSet getHand() {
        return hand;
    }

    public Unit[] getArmy() {
        return army;
    }
    
    public ArrayList<Position> getArmyPositions(){
        ArrayList<Position> unitsPositions = new ArrayList<>();
        for (Unit unit : getNotKilledUnits()  )
            if(!unit.isEliminated())
                unitsPositions.add(unit.getPosition());
        return unitsPositions;
    }

    public void setArmy(Unit[] army) {
        this.army = army;
    }
   
    public CardSet getDiscardPile() {
        return discardPile;
    }

    public CardSet getTablePile() {
        return tablePile;
    }

    public void setTablePile(CardSet tablePile) {
        this.tablePile = tablePile;
    }
     
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public boolean isActive() {
        return active;
    }

    public boolean isFinishedSetup() {
        return finishedSetup;
    }

    public void setFinishedSetup(boolean finishedSetup) {
        this.finishedSetup = finishedSetup;
    }


    public boolean isHost() {
        return host;
    }

    public void setHost(boolean host) {
        this.host = host;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean hasMoved() {
        return moved;
    }

    public boolean hasAttacked() {
        return attacked;
    }

    public void hasAttacked(boolean attacked) {
        this.attacked = attacked;
    }

    
    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public boolean hasDrawn() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    public void setUnit(Unit unit){
        for(int i=0; i< army.length; i++)
            if(army[i].equals(unit)){
                army[i].advanced = unit.advanced;
                army[i].eliminated = unit.eliminated;
                army[i].hasAttacked = unit.hasAttacked;
                army[i].hasMoved = unit.hasMoved;
                army[i].injured = unit.injured;
                army[i].position = unit.position;
                army[i].retrieving = unit.retrieving;
                army[i].selected = unit.selected;
                army[i].supporting = unit.supporting;
                return;
                }
    }
    
    @Override
    public String toString() {
        return getName() +
                ",Nation: " 
                + nation.toString()+  
                (isActive() ? " active" : " inactive");
    }

    public void setLastMovedUnit(Unit lastMovedUnit) {
        this.lastMovedUnit = lastMovedUnit;
    }
    
    public Unit getLastMovedUnit(){
        return lastMovedUnit;
    }
    
    public Unit getUnitByPosition(Position position){
           for(Unit unitSearch: getArmy()){
            if(unitSearch.getPosition().equals(position))
            {
                return unitSearch;
            }
        }
        return null;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public ArrayList<Unit> getNotKilledUnits(){
        
        ArrayList notKilledUnits = new ArrayList();
        for(Unit unit:getArmy())    
            if(!unit.isEliminated()) 
                notKilledUnits.add(unit);

        return notKilledUnits;
    }
    
    public int getUnitsKilled()
    {
        int unitskilled = 0;
        for(Unit unit:getArmy())    
        {
            if(unit.isEliminated()) unitskilled ++;
        }
        
        return unitskilled;
    }
    
}
