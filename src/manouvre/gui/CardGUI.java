/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manouvre.gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import manouvre.game.Card;
import manouvre.game.CardSet;
import manouvre.game.interfaces.CardInterface;

/**
 *
 * @author Bartosz
 */
public class CardGUI {
    Image imgFull;
    int OverCard=0;
    int Selected=0;
    Card card;
    int selectionSeq=0; //card was selected as selectionSeq in the row
//    int posX;
//    int posY;
    Image imgBackCover;
   
    /**
     * Gets the card image based on its ID in cards.csv 
     * @param ID    cardID
     * @see resources.cards
     */
    public CardGUI(Card newCard) {
        this.card=newCard;    
        setImg();  
        setBackCoverImg();
    }
    
    
    private void setImg(){
        try {
            imgFull = ImageIO.read(new File("resources\\cards\\"+card.getCardImg()));
        } catch (IOException ex) {
            Logger.getLogger(UnitGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String getBackCoverImgName(){
        switch (card.getCardFlag()){ //check first card in the list for flag
            case CardInterface.BR:  
                return "BRMask.JPG";      
            case CardInterface.FR: 
                return "FRMask.JPG";    
            case CardInterface.RU: 
                return "RUMask.JPG";    
            case CardInterface.PR: 
                return "PRMask.JPG";    
            case CardInterface.AU: 
                return "AUMask.JPG";    
            case CardInterface.SP: 
                return "SPMask.JPG";    
            case CardInterface.OT: 
                return "OTMask.JPG";  
            case CardInterface.US: 
                return "USMask.JPG"; 
                
        }   
        return "FRNapoleon.JPG";
    }
   
    private void setBackCoverImg(){
        try {
            imgBackCover = ImageIO.read(new File("resources\\cards\\"+getBackCoverImgName()));
        } catch (IOException ex) {
            Logger.getLogger(UnitGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Image getImgBackCover() {
        return imgBackCover;
    }
    /**
     * Returns card image to be used in GUI
     * @return card image
     */
    public Image getImgFull() {
        return imgFull;
    }
    
    public void setOverCard(int isOverCard) {
        this.OverCard = isOverCard;
    }
    
     public int isOverCard() {
        return OverCard;
    }

    public void setSelected(int isSelected) {
        this.Selected = isSelected;
    }
    
    public int isSelected() {
        return Selected;
    }

    public void printSelect(){
        System.out.println(card.getCardName()+" Card Selection Status:"+Selected);
        
    }
    
    public void printOverCard(){
        System.out.println("Mouse Over "+card.getCardName()+" Card Status: "+OverCard);
        
    }

    public void setSelectionSeq(int selectionSeq) {
        this.selectionSeq = selectionSeq;
    }
    
    public int getSelectionSeq() {
        return selectionSeq;
    }
    public int getFlag() {
        return card.getCardFlag();
    }
    public int getCardID() {
        return card.getCardID();
    }
  
}
