/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manouvre.gui;

import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import manouvre.game.Player;
import manouvre.game.interfaces.CardInterface;
import manouvre.network.client.SocketClient;

/**
 *
 * @author Piotr
 */
public class RoomWindow extends javax.swing.JFrame {

    public SocketClient client;
    public int port;
    public String serverAddr, username, password;
    public Thread clientThread;
    public DefaultListModel model;

    int windowMode ;
    
    Player player;
    /**
     * Creates new form RoomWindow
     */
    public RoomWindow() {
        initComponents();
    }
    
       /**
     * Creates new form RoomWindow
     */
    public RoomWindow(SocketClient passSocket, Player player, int mode ) {
        this.client = passSocket;
        this.player = player;
        this.windowMode = mode;
               
       
        
        initComponents();
        
        setEditableButtons(windowMode);
        
        String modeString;
                
                if(mode == CreateRoomWindow.AS_HOST )
                    modeString = " as Host";
                else 
                    modeString = " as Quest";
                      
        this.setTitle(player.getName()  + modeString);
    }
    

     private void setEditableButtons(int mode){
     
         switch(mode)
         {
             case CreateRoomWindow.AS_HOST :
                 
               AustriaRB2.setEnabled(false);
               BritainRB2.setEnabled(false);
               FranceRB2.setEnabled(false);
               OttomanRB2.setEnabled(false);
               PrussiaRB2.setEnabled(false);
               RussiaRB2.setEnabled(false);
               SpainRB2.setEnabled(false);
               USARB2.setEnabled(false);

               
                       
             
             break;
             case CreateRoomWindow.AS_GUEST :
               
               AustriaRB1.setEnabled(false);
               BritainRB1.setEnabled(false);
               FranceRB1.setEnabled(false);
               OttomanRB1.setEnabled(false);
               PrussiaRB1.setEnabled(false);
               RussiaRB1.setEnabled(false);
               SpainRB1.setEnabled(false);
               USARB1.setEnabled(false);
               
               startButton.setEnabled(false);
                 
             break;    
                 
     
     }}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        player1Group = new javax.swing.ButtonGroup();
        player2Group = new javax.swing.ButtonGroup();
        austriaSel = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainChat = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        messageTextField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        playersPanel = new javax.swing.JPanel();
        player1Panel = new javax.swing.JPanel();
        FranceRB1 = new javax.swing.JRadioButton();
        USARB1 = new javax.swing.JRadioButton();
        AustriaRB1 = new javax.swing.JRadioButton();
        PrussiaRB1 = new javax.swing.JRadioButton();
        OttomanRB1 = new javax.swing.JRadioButton();
        BritainRB1 = new javax.swing.JRadioButton();
        SpainRB1 = new javax.swing.JRadioButton();
        RussiaRB1 = new javax.swing.JRadioButton();
        player1Panel1 = new javax.swing.JPanel();
        FranceRB2 = new javax.swing.JRadioButton();
        USARB2 = new javax.swing.JRadioButton();
        AustriaRB2 = new javax.swing.JRadioButton();
        PrussiaRB2 = new javax.swing.JRadioButton();
        OttomanRB2 = new javax.swing.JRadioButton();
        BritainRB2 = new javax.swing.JRadioButton();
        SpainRB2 = new javax.swing.JRadioButton();
        RussiaRB2 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainChat.setColumns(20);
        mainChat.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        mainChat.setRows(5);
        jScrollPane1.setViewportView(mainChat);

        jLabel5.setForeground(new java.awt.Color(255, 51, 0));
        jLabel5.setText("Message : ");

        sendButton.setText("Send Message ");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(175, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendButton)
                    .addComponent(jLabel5)
                    .addComponent(messageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(72, Short.MAX_VALUE)))
        );

        player1Group.add(FranceRB1);
        FranceRB1.setText("France");

        player1Group.add(USARB1);
        USARB1.setText("USA");

        player1Group.add(AustriaRB1);
        AustriaRB1.setSelected(true);
        AustriaRB1.setText("Austria");
        AustriaRB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AustriaRB1ActionPerformed(evt);
            }
        });

        player1Group.add(PrussiaRB1);
        PrussiaRB1.setText("Prussia");

        player1Group.add(OttomanRB1);
        OttomanRB1.setText("Ottoman");

        player1Group.add(BritainRB1);
        BritainRB1.setText("Britain");
        BritainRB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BritainRB1ActionPerformed(evt);
            }
        });

        player1Group.add(SpainRB1);
        SpainRB1.setText("Spain");
        SpainRB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SpainRB1ActionPerformed(evt);
            }
        });

        player1Group.add(RussiaRB1);
        RussiaRB1.setText("Russia");

        javax.swing.GroupLayout player1PanelLayout = new javax.swing.GroupLayout(player1Panel);
        player1Panel.setLayout(player1PanelLayout);
        player1PanelLayout.setHorizontalGroup(
            player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(player1PanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BritainRB1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AustriaRB1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(OttomanRB1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(USARB1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PrussiaRB1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FranceRB1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SpainRB1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RussiaRB1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        player1PanelLayout.setVerticalGroup(
            player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, player1PanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RussiaRB1)
                    .addComponent(FranceRB1)
                    .addComponent(USARB1)
                    .addComponent(AustriaRB1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addGroup(player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BritainRB1)
                    .addComponent(OttomanRB1)
                    .addComponent(PrussiaRB1)
                    .addComponent(SpainRB1))
                .addContainerGap())
        );

        player2Group.add(FranceRB2);
        FranceRB2.setText("France");

        player2Group.add(USARB2);
        USARB2.setSelected(true);
        USARB2.setText("USA");

        player2Group.add(AustriaRB2);
        AustriaRB2.setText("Austria");
        AustriaRB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AustriaRB2ActionPerformed(evt);
            }
        });

        player2Group.add(PrussiaRB2);
        PrussiaRB2.setText("Prussia");

        player2Group.add(OttomanRB2);
        OttomanRB2.setText("Ottoman");

        player2Group.add(BritainRB2);
        BritainRB2.setText("Britain");
        BritainRB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BritainRB2ActionPerformed(evt);
            }
        });

        player2Group.add(SpainRB2);
        SpainRB2.setText("Spain");

        player2Group.add(RussiaRB2);
        RussiaRB2.setText("Russia");

        javax.swing.GroupLayout player1Panel1Layout = new javax.swing.GroupLayout(player1Panel1);
        player1Panel1.setLayout(player1Panel1Layout);
        player1Panel1Layout.setHorizontalGroup(
            player1Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(player1Panel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(player1Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BritainRB2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AustriaRB2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(player1Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(OttomanRB2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(USARB2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(player1Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PrussiaRB2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FranceRB2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(player1Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SpainRB2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RussiaRB2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        player1Panel1Layout.setVerticalGroup(
            player1Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, player1Panel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(player1Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RussiaRB2)
                    .addComponent(FranceRB2)
                    .addComponent(USARB2)
                    .addComponent(AustriaRB2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(player1Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BritainRB2)
                    .addComponent(OttomanRB2)
                    .addComponent(PrussiaRB2)
                    .addComponent(SpainRB2))
                .addContainerGap())
        );

        javax.swing.GroupLayout playersPanelLayout = new javax.swing.GroupLayout(playersPanel);
        playersPanel.setLayout(playersPanelLayout);
        playersPanelLayout.setHorizontalGroup(
            playersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playersPanelLayout.createSequentialGroup()
                .addGap(0, 38, Short.MAX_VALUE)
                .addGroup(playersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(player1Panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(player1Panel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        playersPanelLayout.setVerticalGroup(
            playersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(player1Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        startButton.setText("Start Game");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(playersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(playersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sendButtonActionPerformed

    private void BritainRB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BritainRB1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BritainRB1ActionPerformed

    private void AustriaRB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AustriaRB1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AustriaRB1ActionPerformed

    private void AustriaRB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AustriaRB2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AustriaRB2ActionPerformed

    private void BritainRB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BritainRB2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BritainRB2ActionPerformed

    private void SpainRB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SpainRB1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SpainRB1ActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
         
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    
                        for (Enumeration<AbstractButton> buttons = player1Group.getElements(); buttons.hasMoreElements();) {
                                AbstractButton button = buttons.nextElement();
                                if (button.isSelected()) {
                                    switch(button.getText()){
                                        case "Austria": 
                                            
                                            player.setNation(CardInterface.AU);
                                            break;
                                        case "France" :
                                            player.setNation(CardInterface.FR);
                                            break;
                                        case "Russia": 
                                            
                                            player.setNation(CardInterface.RU);
                                            break;
                                        case "Prussia" :
                                            player.setNation(CardInterface.PR);
                                            break;
                                        case "Britain": 
                                            
                                            player.setNation(CardInterface.BR);
                                            break;
                                        case "Spain" :
                                            player.setNation(CardInterface.SP);
                                            break;
                                        case "Ottoman": 
                                            
                                            player.setNation(CardInterface.OT);
                                            break;
                                        case "USA" :
                                            player.setNation(CardInterface.US);
                                            break;
                                                }
                                    break;
                                }
                      }
                                player.generateUnits();
                                if(windowMode == CreateRoomWindow.AS_HOST)
                                    player.setHost(true);
                                else 
                                    player.setHost(false);
                                
                                 new GameWindow(client, player).setVisible(true);
                            
                                setVisible(false);
                   
                } catch (IOException ex) {
                    Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }//GEN-LAST:event_startButtonActionPerformed

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
            java.util.logging.Logger.getLogger(RoomWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoomWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoomWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoomWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RoomWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton AustriaRB1;
    private javax.swing.JRadioButton AustriaRB2;
    private javax.swing.JRadioButton BritainRB1;
    private javax.swing.JRadioButton BritainRB2;
    private javax.swing.JRadioButton FranceRB1;
    private javax.swing.JRadioButton FranceRB2;
    private javax.swing.JRadioButton OttomanRB1;
    private javax.swing.JRadioButton OttomanRB2;
    private javax.swing.JRadioButton PrussiaRB1;
    private javax.swing.JRadioButton PrussiaRB2;
    private javax.swing.JRadioButton RussiaRB1;
    private javax.swing.JRadioButton RussiaRB2;
    private javax.swing.JRadioButton SpainRB1;
    private javax.swing.JRadioButton SpainRB2;
    private javax.swing.JRadioButton USARB1;
    private javax.swing.JRadioButton USARB2;
    private javax.swing.ButtonGroup austriaSel;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea mainChat;
    public javax.swing.JTextField messageTextField;
    private javax.swing.ButtonGroup player1Group;
    private javax.swing.JPanel player1Panel;
    private javax.swing.JPanel player1Panel1;
    private javax.swing.ButtonGroup player2Group;
    private javax.swing.JPanel playersPanel;
    public javax.swing.JButton sendButton;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}
