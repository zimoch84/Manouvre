/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manouvre.gui;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import manouvre.game.Card;
import manouvre.game.CardSet;
import manouvre.game.Game;
import manouvre.game.commands.CommandQueue;
import manouvre.game.commands.DiscardCardCommand;
import manouvre.game.commands.DrawCardCommand;
import manouvre.network.client.Message;
import static java.lang.Thread.sleep;
import manouvre.game.interfaces.Command;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;

/**
 *
 * @author Bartosz
 */
public class TestWindow extends javax.swing.JFrame {

    int numberOfPossibleCards=0, tempNumberOfPossibleCards=0, numberOfChosenCards=0;
    GameWindow gameWindow;
    CardSet testCards;
    Game game;
    GameGUI gameGui; 
    Card ambush,commitedAttack,engineers,forcedMarch,guerrillas, redoubt,regroup,royalEngineers,scout,skirmish,spy,supply,withdraw;
    String flag;
    int phase;
    CommandQueue cmdQueue;
                    
    /**
     * Creates new form TestWindow
     */
    public TestWindow(Game game, GameWindow gameWindow, int phase, CommandQueue cmdQueue ) {
        this.cmdQueue=cmdQueue;
        this.gameWindow=gameWindow;
        this.game=game;
        this.gameGui=gameWindow.getGameGui();
        this.testCards=new CardSet();
        this.phase=phase;
        
        initComponents();
        initAllTheHqCards();
        setNumberOfPossibleCards();
        
    }
    private void setNumberOfPossibleCards(){
        this.numberOfPossibleCards=game.getCurrentPlayer().getHand().getCardSetSize()-game.getCurrentPlayer().getHand().cardsLeftInSet();
        this.tempNumberOfPossibleCards=numberOfPossibleCards;
        ProgressBar.setValue(numberOfPossibleCards);
        TextField.setText("Possible to select " + numberOfPossibleCards + " more"  );
    }
    
    private void initAllTheHqCards(){
    this.flag=game.getCurrentPlayer().getNationAsString(true);
    
    this.ambush = new Card("Ambush", flag);   
    if(ambush.isCardNotFoundInNation()==true) AmbushBox.setEnabled(false);
    
    this.commitedAttack = new Card("Committed Attack", flag);
    if(commitedAttack.isCardNotFoundInNation()==true) CommitedAttackBox.setEnabled(false);
    
    this.engineers = new Card("Engineers", flag);  
    if(engineers.isCardNotFoundInNation()==true) EngineersBox.setEnabled(false);
    
    this.forcedMarch = new Card("Forced March", flag);
    if(forcedMarch.isCardNotFoundInNation()==true) ForcedMarchBox.setEnabled(false);
    
    this.guerrillas = new Card("Guerrillas", flag);
    if(guerrillas.isCardNotFoundInNation()==true) GuerrillasBox.setEnabled(false);
    
    this.redoubt = new Card("Redoubt", flag);
    if(redoubt.isCardNotFoundInNation()==true) RedoubtBox.setEnabled(false);
    
    this.regroup = new Card("Regroup", flag);
    if(regroup.isCardNotFoundInNation()==true) RegroupBox.setEnabled(false);
    
    this.royalEngineers = new Card("Royal Engineers", flag);
    if(royalEngineers.isCardNotFoundInNation()==true) RoyalEngineersBox.setEnabled(false);
 
    this.scout = new Card("Scout", flag);
    if(scout.isCardNotFoundInNation()==true) ScoutBox.setEnabled(false);
   
    this.skirmish = new Card("Skirmish", flag);
    if(skirmish.isCardNotFoundInNation()==true) SkirmishBox.setEnabled(false);
 
    this.spy = new Card("Spy", flag);
    if(spy.isCardNotFoundInNation()==true) SpyBox.setEnabled(false);
 
    this.supply = new Card("Supply", flag);  
    if(supply.isCardNotFoundInNation()==true) SupplyBox.setEnabled(false);
 
    this.withdraw = new Card("Withdraw",flag);
    if(withdraw.isCardNotFoundInNation()==true) WithdrawBox.setEnabled(false);
 
    }
    
    
    private void addPossibleToSelectCard(){
        if (tempNumberOfPossibleCards<5){
        tempNumberOfPossibleCards++;
        numberOfChosenCards--;
        }
    }
     private void subPossibleToSelectCard(){
        if (tempNumberOfPossibleCards>0){
        tempNumberOfPossibleCards--;
        numberOfChosenCards++;
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        SkirmishBox = new javax.swing.JCheckBox();
        AmbushBox = new javax.swing.JCheckBox();
        SupplyBox = new javax.swing.JCheckBox();
        WithdrawBox = new javax.swing.JCheckBox();
        CommitedAttackBox = new javax.swing.JCheckBox();
        EngineersBox = new javax.swing.JCheckBox();
        ForcedMarchBox = new javax.swing.JCheckBox();
        GuerrillasBox = new javax.swing.JCheckBox();
        RedoubtBox = new javax.swing.JCheckBox();
        RegroupBox = new javax.swing.JCheckBox();
        ScoutBox = new javax.swing.JCheckBox();
        ProgressBar = new javax.swing.JProgressBar();
        TextField = new javax.swing.JTextField();
        RoyalEngineersBox = new javax.swing.JCheckBox();
        SpyBox = new javax.swing.JCheckBox();
        CloseButton = new javax.swing.JButton();
        drawCards = new javax.swing.JButton();
        drawCards1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cards"));

        SkirmishBox.setText("Skirmish");
        SkirmishBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkirmishBoxActionPerformed(evt);
            }
        });

        AmbushBox.setText("Ambush");
        AmbushBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AmbushBoxActionPerformed(evt);
            }
        });

        SupplyBox.setText("Supply");
        SupplyBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupplyBoxActionPerformed(evt);
            }
        });

        WithdrawBox.setText("Withdraw");
        WithdrawBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WithdrawBoxActionPerformed(evt);
            }
        });

        CommitedAttackBox.setText("Commited Attack");
        CommitedAttackBox.setToolTipText("");
        CommitedAttackBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CommitedAttackBoxActionPerformed(evt);
            }
        });

        EngineersBox.setText("Engineers");
        EngineersBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EngineersBoxActionPerformed(evt);
            }
        });

        ForcedMarchBox.setText("Forced March");
        ForcedMarchBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ForcedMarchBoxActionPerformed(evt);
            }
        });

        GuerrillasBox.setText("Guerrillas");
        GuerrillasBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuerrillasBoxActionPerformed(evt);
            }
        });

        RedoubtBox.setText("Redoubt");
        RedoubtBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RedoubtBoxActionPerformed(evt);
            }
        });

        RegroupBox.setText("Regroup");
        RegroupBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegroupBoxActionPerformed(evt);
            }
        });

        ScoutBox.setText("Scout");
        ScoutBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ScoutBoxActionPerformed(evt);
            }
        });

        ProgressBar.setMaximum(5);
        ProgressBar.setToolTipText("");

        TextField.setEditable(false);
        TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextField.setText("Text");
        TextField.setName("Test Popup"); // NOI18N
        TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldActionPerformed(evt);
            }
        });

        RoyalEngineersBox.setText("Royal Engineers");
        RoyalEngineersBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoyalEngineersBoxActionPerformed(evt);
            }
        });

        SpyBox.setText("Spy");
        SpyBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SpyBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SkirmishBox)
                            .addComponent(RoyalEngineersBox)
                            .addComponent(AmbushBox)
                            .addComponent(CommitedAttackBox)
                            .addComponent(RegroupBox)
                            .addComponent(SpyBox)
                            .addComponent(EngineersBox)
                            .addComponent(GuerrillasBox)
                            .addComponent(RedoubtBox)
                            .addComponent(SupplyBox)
                            .addComponent(ForcedMarchBox)
                            .addComponent(ScoutBox)
                            .addComponent(WithdrawBox)
                            .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AmbushBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CommitedAttackBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EngineersBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RoyalEngineersBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ForcedMarchBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GuerrillasBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RedoubtBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RegroupBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScoutBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SpyBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SkirmishBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SupplyBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(WithdrawBox)
                .addGap(18, 18, 18)
                .addComponent(TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        CloseButton.setText("Close");
        CloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButtonActionPerformed(evt);
            }
        });

        drawCards.setText("Draw Cards");
        drawCards.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawCardsActionPerformed(evt);
            }
        });

        drawCards1.setText("Refresh");
        drawCards1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(drawCards)
                        .addComponent(CloseButton, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(drawCards1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(drawCards1)
                        .addGap(18, 18, 18)
                        .addComponent(drawCards)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CloseButton)
                        .addGap(28, 28, 28))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // <editor-fold defaultstate="collapsed" desc="Typ function"> 
    private void AmbushBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AmbushBoxActionPerformed
        if(!AmbushBox.isSelected()&& (tempNumberOfPossibleCards<5)) {
            addPossibleToSelectCard();  
            testCards.removeCardFromThisSet(ambush);
        }
        if(AmbushBox.isSelected() && (tempNumberOfPossibleCards>0)) {
            subPossibleToSelectCard();
            testCards.addCardToThisSet(ambush);
        } else AmbushBox.setSelected(false);   
        
        ProgressBar.setValue(tempNumberOfPossibleCards);
        TextField.setText("Possible to select " + tempNumberOfPossibleCards + " more" );
//        for(int i=0; i<testCards.cardsLeftInSet(); i++){
//        System.out.println("Test Cards to be added: " + testCards.getCardNameByPosInSet(i));
//        }
    }//GEN-LAST:event_AmbushBoxActionPerformed
// <editor-fold defaultstate="collapsed" desc="Typ function"> 
    private void EngineersBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EngineersBoxActionPerformed
        if(!EngineersBox.isSelected() && (tempNumberOfPossibleCards<5)) {
            testCards.removeCardFromThisSet(engineers);
            addPossibleToSelectCard();
        }
        if(EngineersBox.isSelected() && tempNumberOfPossibleCards>0) {
            subPossibleToSelectCard();
            testCards.addCardToThisSet(engineers);
        } else EngineersBox.setSelected(false);   
        
        
        ProgressBar.setValue(tempNumberOfPossibleCards);
        TextField.setText("Possible to select " + tempNumberOfPossibleCards + " more" );
//        for(int i=0; i<testCards.cardsLeftInSet(); i++){
//        System.out.println("Test Cards to be added: " + testCards.getCardNameByPosInSet(i));
//        }
    }//GEN-LAST:event_EngineersBoxActionPerformed
// <editor-fold defaultstate="collapsed" desc="Typ function"> 
    private void WithdrawBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WithdrawBoxActionPerformed
        if(!WithdrawBox.isSelected() && (tempNumberOfPossibleCards<5)) {
            testCards.removeCardFromThisSet(withdraw);
            addPossibleToSelectCard();
        }
        if(WithdrawBox.isSelected() && tempNumberOfPossibleCards>0) {
            subPossibleToSelectCard();
            testCards.addCardToThisSet(withdraw);
        } else WithdrawBox.setSelected(false);   
        
        ProgressBar.setValue(tempNumberOfPossibleCards);
        TextField.setText("Possible to select " + tempNumberOfPossibleCards + " more" );
//        for(int i=0; i<testCards.cardsLeftInSet(); i++){
//        System.out.println("Test Cards to be added: " + testCards.getCardNameByPosInSet(i));
//        }
    }//GEN-LAST:event_WithdrawBoxActionPerformed
// <editor-fold defaultstate="collapsed" desc="Typ function"> 
    private void CloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButtonActionPerformed
        game.setPhase(phase); //put back the game to previous phase
        gameWindow.refreshAll();
        this.dispose();
    }//GEN-LAST:event_CloseButtonActionPerformed
// <editor-fold defaultstate="collapsed" desc="Typ function"> 
    private void CommitedAttackBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CommitedAttackBoxActionPerformed
        if(!CommitedAttackBox.isSelected() && (tempNumberOfPossibleCards<5)) {
            testCards.removeCardFromThisSet(commitedAttack);
            addPossibleToSelectCard();
        }
        if(CommitedAttackBox.isSelected() && tempNumberOfPossibleCards>0) {
            subPossibleToSelectCard();
            testCards.addCardToThisSet(commitedAttack);
        } else CommitedAttackBox.setSelected(false);   
        ProgressBar.setValue(tempNumberOfPossibleCards);
        TextField.setText("Possible to select " + tempNumberOfPossibleCards + " more" );
//        for(int i=0; i<testCards.cardsLeftInSet(); i++){
//        System.out.println("Test Cards to be added: " + testCards.getCardNameByPosInSet(i));
//        }
       // this.repaint();
    }//GEN-LAST:event_CommitedAttackBoxActionPerformed
// <editor-fold defaultstate="collapsed" desc="Typ function"> 
    private void ForcedMarchBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ForcedMarchBoxActionPerformed
        if(!ForcedMarchBox.isSelected() && (tempNumberOfPossibleCards<5)) {
            testCards.removeCardFromThisSet(forcedMarch);
            addPossibleToSelectCard();
        }
        if(ForcedMarchBox.isSelected() && tempNumberOfPossibleCards>0) {
            subPossibleToSelectCard();
            testCards.addCardToThisSet(forcedMarch);
        } else ForcedMarchBox.setSelected(false);   
        ProgressBar.setValue(tempNumberOfPossibleCards);
        TextField.setText("Possible to select " + tempNumberOfPossibleCards + " more" );
//        for(int i=0; i<testCards.cardsLeftInSet(); i++){
//        System.out.println("Test Cards to be added: " + testCards.getCardNameByPosInSet(i));
//        }
       // this.repaint();
    }//GEN-LAST:event_ForcedMarchBoxActionPerformed
// <editor-fold defaultstate="collapsed" desc="Typ function"> 
    private void GuerrillasBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuerrillasBoxActionPerformed
        if(!GuerrillasBox.isSelected() && (tempNumberOfPossibleCards<5)) {
            testCards.removeCardFromThisSet(guerrillas);
            addPossibleToSelectCard();
        }
         if(GuerrillasBox.isSelected() && tempNumberOfPossibleCards>0) {
             subPossibleToSelectCard();
             testCards.addCardToThisSet(guerrillas);
         } else GuerrillasBox.setSelected(false);   
        ProgressBar.setValue(tempNumberOfPossibleCards);
        TextField.setText("Possible to select " + tempNumberOfPossibleCards + " more" );
//        for(int i=0; i<testCards.cardsLeftInSet(); i++){
//        System.out.println("Test Cards to be added: " + testCards.getCardNameByPosInSet(i));
//        }
    }//GEN-LAST:event_GuerrillasBoxActionPerformed
// <editor-fold defaultstate="collapsed" desc="Typ function"> 
    private void RedoubtBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RedoubtBoxActionPerformed
        if(!RedoubtBox.isSelected() && (tempNumberOfPossibleCards<5)) {
            testCards.removeCardFromThisSet(redoubt);
            addPossibleToSelectCard();
        }
        if(RedoubtBox.isSelected() && tempNumberOfPossibleCards>0) {
            subPossibleToSelectCard();
            testCards.addCardToThisSet(redoubt);
        } else RedoubtBox.setSelected(false);   
        ProgressBar.setValue(tempNumberOfPossibleCards);
        TextField.setText("Possible to select " + tempNumberOfPossibleCards + " more" );
//        for(int i=0; i<testCards.cardsLeftInSet(); i++){
//        System.out.println("Test Cards to be added: " + testCards.getCardNameByPosInSet(i));
//        }
    }//GEN-LAST:event_RedoubtBoxActionPerformed
// <editor-fold defaultstate="collapsed" desc="Typ function"> 
    private void RegroupBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegroupBoxActionPerformed
        if(!RegroupBox.isSelected() && (tempNumberOfPossibleCards<5)) {
            testCards.removeCardFromThisSet(regroup);
            addPossibleToSelectCard();
        }
        if(RegroupBox.isSelected() && tempNumberOfPossibleCards>0) {
            subPossibleToSelectCard();
            testCards.addCardToThisSet(regroup);
        } else RegroupBox.setSelected(false);   
        ProgressBar.setValue(tempNumberOfPossibleCards);
        TextField.setText("Possible to select " + tempNumberOfPossibleCards + " more" );
//        for(int i=0; i<testCards.cardsLeftInSet(); i++){
//        System.out.println("Test Cards to be added: " + testCards.getCardNameByPosInSet(i));
//        }
    }//GEN-LAST:event_RegroupBoxActionPerformed
// <editor-fold defaultstate="collapsed" desc="Typ function"> 
    private void ScoutBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ScoutBoxActionPerformed
        if(!ScoutBox.isSelected() && (tempNumberOfPossibleCards<5)) {
            testCards.removeCardFromThisSet(scout);
            addPossibleToSelectCard();
        }
        if(ScoutBox.isSelected() && tempNumberOfPossibleCards>0) {
            subPossibleToSelectCard();
            testCards.addCardToThisSet(scout);
        } else ScoutBox.setSelected(false);   
        ProgressBar.setValue(tempNumberOfPossibleCards);
        TextField.setText("Possible to select " + tempNumberOfPossibleCards + " more" );
//        for(int i=0; i<testCards.cardsLeftInSet(); i++){
//        System.out.println("Test Cards to be added: " + testCards.getCardNameByPosInSet(i));
//        }
    }//GEN-LAST:event_ScoutBoxActionPerformed
// <editor-fold defaultstate="collapsed" desc="Typ function"> 
    private void SkirmishBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkirmishBoxActionPerformed
        if(!SkirmishBox.isSelected() && (tempNumberOfPossibleCards<5)) {
            testCards.removeCardFromThisSet(skirmish);
            addPossibleToSelectCard();
        }
        if(SkirmishBox.isSelected() && tempNumberOfPossibleCards>0) {
            subPossibleToSelectCard();
            testCards.addCardToThisSet(skirmish);
        } else SkirmishBox.setSelected(false);   
        ProgressBar.setValue(tempNumberOfPossibleCards);
        TextField.setText("Possible to select " + tempNumberOfPossibleCards + " more" );
//        for(int i=0; i<testCards.cardsLeftInSet(); i++){
//        System.out.println("Test Cards to be added: " + testCards.getCardNameByPosInSet(i));
//        }
    }//GEN-LAST:event_SkirmishBoxActionPerformed
// <editor-fold defaultstate="collapsed" desc="Typ function"> 
    private void SupplyBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupplyBoxActionPerformed
        if(!SupplyBox.isSelected() && (tempNumberOfPossibleCards<5)) {
            addPossibleToSelectCard();
            testCards.removeCardFromThisSet(supply);
        }
        if(SupplyBox.isSelected() && tempNumberOfPossibleCards>0) {
            subPossibleToSelectCard();
            testCards.addCardToThisSet(supply);
        } else SupplyBox.setSelected(false);   
        ProgressBar.setValue(tempNumberOfPossibleCards);
        TextField.setText("Possible to select " + tempNumberOfPossibleCards + " more" );
//        for(int i=0; i<testCards.cardsLeftInSet(); i++){
//        System.out.println("Test Cards to be added: " + testCards.getCardNameByPosInSet(i));
//        }
    }//GEN-LAST:event_SupplyBoxActionPerformed

    private void TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_TextFieldActionPerformed

    private void drawCardsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawCardsActionPerformed
        
        
         Command drawToHand = game.getCardCommandFactory().createMoveToHandCommand(testCards,numberOfChosenCards, false);
        cmdQueue.storeAndExecuteAndSend(drawToHand);
           game.setPhase(phase); //put back the game to previous phase
//        gameGui.getHandSetGui().reSet();
        gameWindow.refreshAll();
        this.dispose();
        
    }//GEN-LAST:event_drawCardsActionPerformed

    private void RoyalEngineersBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoyalEngineersBoxActionPerformed
         if(!RoyalEngineersBox.isSelected()&& (tempNumberOfPossibleCards<5)) {
            addPossibleToSelectCard();      
        }
        if(RoyalEngineersBox.isSelected() && (tempNumberOfPossibleCards>0)) {
            subPossibleToSelectCard();
            testCards.addCardToThisSet(royalEngineers);
        } else RoyalEngineersBox.setSelected(false);   
        ProgressBar.setValue(tempNumberOfPossibleCards);
        TextField.setText("Possible to select " + tempNumberOfPossibleCards + " more" );
//        for(int i=0; i<testCards.cardsLeftInSet(); i++){
//        System.out.println("Test Cards to be added: " + testCards.getCardNameByPosInSet(i));
//        }
    }//GEN-LAST:event_RoyalEngineersBoxActionPerformed

    private void SpyBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SpyBoxActionPerformed
         if(!SpyBox.isSelected()&& (tempNumberOfPossibleCards<5)) {
            addPossibleToSelectCard();      
        }
        if(SpyBox.isSelected() && (tempNumberOfPossibleCards>0)) {
            subPossibleToSelectCard();
            testCards.addCardToThisSet(spy);
        } else SpyBox.setSelected(false);   
        
        ProgressBar.setValue(tempNumberOfPossibleCards);
        TextField.setText("Possible to select " + tempNumberOfPossibleCards + " more" );
//        for(int i=0; i<testCards.cardsLeftInSet(); i++){
//        System.out.println("Test Cards to be added (i): " + testCards.getCardNameByPosInSet(i));
//        }
    }//GEN-LAST:event_SpyBoxActionPerformed

    private void refreshPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshPerformed
        setNumberOfPossibleCards();
    }//GEN-LAST:event_refreshPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new TestWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox AmbushBox;
    private javax.swing.JButton CloseButton;
    private javax.swing.JCheckBox CommitedAttackBox;
    private javax.swing.JCheckBox EngineersBox;
    private javax.swing.JCheckBox ForcedMarchBox;
    private javax.swing.JCheckBox GuerrillasBox;
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JCheckBox RedoubtBox;
    private javax.swing.JCheckBox RegroupBox;
    private javax.swing.JCheckBox RoyalEngineersBox;
    private javax.swing.JCheckBox ScoutBox;
    private javax.swing.JCheckBox SkirmishBox;
    private javax.swing.JCheckBox SpyBox;
    private javax.swing.JCheckBox SupplyBox;
    private javax.swing.JTextField TextField;
    private javax.swing.JCheckBox WithdrawBox;
    private javax.swing.JButton drawCards;
    private javax.swing.JButton drawCards1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
