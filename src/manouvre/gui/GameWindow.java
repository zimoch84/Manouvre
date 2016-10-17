/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manouvre.gui;

import java.awt.Color;
import manouvre.network.client.Message;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.text.DefaultCaret;
import manouvre.game.Game;
import manouvre.game.Player;
import manouvre.game.Position;
import manouvre.game.Unit;
import manouvre.network.client.SocketClient;
import manouvre.game.MoveUnitCommand;
import manouvre.game.interfaces.FrameInterface;
import static java.lang.Math.abs;
import manouvre.game.interfaces.CardInterface;


/**
 *
 * @author Piotr
 */
public class GameWindow extends javax.swing.JFrame implements FrameInterface{

    /*
    Network variables
    */
    
    public SocketClient client;
    public int port;
    public String serverAddr,  password;
    public Thread clientThread;
    public Player player;
    
    private int handMouseCoorX,handMouseCoorY;
    private int handMouseCoorXdeaf=0;
    private int handMouseCoorYdeaf=0;
    int mouseClickedOnHand=0;
    
    CardSetGUI cardSetsGUI;
    int windowMode;
    
    /*
    Object hold whole game logically
    */
    Game game;

    GameGUI gameGui;
    
    private Image bgImage;
   
    
    
 
    public GameWindow(SocketClient passSocket, Player player) throws IOException{
        
        bgImage = ImageIO.read( new File("resources\\backgrounds\\24209cb208yezho.jpg"));
        client = passSocket;
        game = new Game(player);
        gameGui = new GameGUI(game, CreateRoomWindow.AS_HOST);
        
        /*
        game.generateMap();
        game.setCurrentPlayer(player);
        */
   
        initComponents();
    
        this.addWindowListener(new WindowListener() {
            @Override public void windowOpened(WindowEvent e) {}
            @Override public void windowClosing(WindowEvent e) { try{ client.send(new Message("message", game.getCurrentPlayer().getName(), ".bye", "SERVER")); clientThread.stop();  }catch(Exception ex){} }
            @Override public void windowClosed(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });
        
     DefaultCaret caret = (DefaultCaret)chatTextArea.getCaret();
     caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }
    
     public GameWindow(Game game, SocketClient passSocket,  int windowMode) throws IOException{
        
        /*
         Game has generated players army , hand and comes from serwver
         */
        this.game = game;
        this.windowMode = windowMode;
        client = passSocket;
        
        /*
        Sets current Player based on HOST/GUEST settings
        */
        game.setCurrentPlayer(windowMode);

        /*
        Creates new GUI respects HOST/GUEST settings
        */
        gameGui = new GameGUI(this.game, windowMode);
        
        bgImage = ImageIO.read( new File("resources\\backgrounds\\24209cb208yezho.jpg"));
        initComponents();
    
        this.addWindowListener(new WindowListener() {
            @Override public void windowOpened(WindowEvent e) {}
            @Override public void windowClosing(WindowEvent e) { try{ client.send(new Message("message", game.getCurrentPlayer().getName(), ".bye", "SERVER")); clientThread.stop();  }catch(Exception ex){} }
            @Override public void windowClosed(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });
        
     DefaultCaret caret = (DefaultCaret)chatTextArea.getCaret();
     caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }
    
    /**
     * Creates new form ClientUI
     */
    public GameWindow() throws Exception {
        
        bgImage = ImageIO.read( new File("resources\\backgrounds\\24209cb208yezho.jpg"));
        initComponents();
        game=new Game(new Player("Bartek"));
        gameGui = new GameGUI(game, CreateRoomWindow.AS_HOST ); //TEMP
      
        gameGui.getGame().getMap(); //TEMP
       
        gameGui.mapGui = new MapGUI(game.getMap(), CreateRoomWindow.AS_HOST);
        this.addWindowListener(new WindowListener() {

            @Override public void windowOpened(WindowEvent e) {}
            @Override public void windowClosing(WindowEvent e) { try{ client.send(new Message("message",game.getCurrentPlayer().getName(), ".bye", "SERVER")); clientThread.stop();  }catch(Exception ex){} }
            @Override public void windowClosed(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });
        
            
    }

    public Game getGame() {
         return game;
    }

     public void setGame(Game game) {
         this.game = game;
    }

    public void drawMainBackground(Graphics g){
    
     g.drawImage(bgImage, 0, 0, this.getSize().width, this.getSize().height,Color.red, null);
    }
     
    private void drawMap(Graphics g )                   
    {
        gameGui.drawMap(g,windowMode );
     
    }
 
    private void drawCard(Graphics g )                 
    {   
        playCardButton.setEnabled(!gameGui.getSelectionSeqIsEmpty()&&game.getPhase()!=0&&game.getPhase()!=1);         
        gameGui.paintHand(g,  handMouseCoorX, handMouseCoorY, mouseClickedOnHand); 
        mouseClickedOnHand=0; 
         //set switches
         buttonPhaseName.setEnabled(false);
         switch(game.getPhase()){
           case 0:
           {
            buttonPhaseName.setEnabled(!gameGui.getSelectionSeqIsEmpty());
            buttonPhaseName.setText("Discard");
            return;
           }
           case 1:
           {
             buttonPhaseName.setEnabled(gameGui.numberOfDiscardedCards>0);
             buttonPhaseName.setText("Draw");
              return;
           }
           case 2:
           {
            buttonPhaseName.setEnabled(true);  
            buttonPhaseName.setText("Move");
             return;
           }
           case 3:
           {
            buttonPhaseName.setEnabled(true);  
            buttonPhaseName.setText("Combat");
             return;
           }
            case 4:
           {
            buttonPhaseName.setEnabled(true);  
            buttonPhaseName.setText("Restoration");
             return;
           }
         }
        
    }
    
    private void drawDiscard(Graphics g )                 
    {   
        gameGui.paintDiscard(g); 
        
    }
    
    private void drawDrawLeft(Graphics g ) {
        gameGui.paintDrawLeft(g); 
    }
    
    private void drawCombatPanel(Graphics g){
        gameGui.paintCombatPanel(g);
        
    }
    private void drawTablePanel (Graphics g){
        gameGui.paintTablePanel(g);
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jLabel5 = new javax.swing.JLabel();
        mainWindowPanel = new javax.swing.JPanel()
        {
            @Override
            public void paintComponent(Graphics g) {
                drawMainBackground(g);

            }
        }
        ;
        rightSidePanel = new javax.swing.JPanel();
        currentPlayerPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        discardPanel = new javax.swing.JPanel()
        {
            @Override
            public void paintComponent(Graphics g) {
                drawDiscard(g);

            }
        }
        ;
        playerDrawLeftPanel = new javax.swing.JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                drawDrawLeft(g);

            }
        }
        ;
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        buttonsPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        chatPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatTextArea = new javax.swing.JTextArea();
        sendMessageButton = new javax.swing.JButton();
        sendText = new javax.swing.JTextField();
        buttonToNextPhase = new javax.swing.JButton();
        playCardButton = new javax.swing.JButton();
        buttonPhaseName = new javax.swing.JButton();
        dicePanel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        opponentPlayerPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        discardPanel1 = new javax.swing.JPanel()
        {
            @Override
            public void paintComponent(Graphics g) {
                drawDiscard(g);

            }
        }
        ;
        playerDrawLeftPanel1 = new javax.swing.JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                drawDrawLeft(g);

            }
        }
        ;
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        combatPanel = new javax.swing.JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                drawCombatPanel(g);

            }
        }
        ;
        tablePanel = new javax.swing.JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                drawTablePanel(g);

            }
        }
        ;
        mainMapPanel = new javax.swing.JPanel()
        {
            @Override
            public void paintComponent(Graphics g) {
                drawMap(g);

            }
        }
        ;
        jLabel4 = new javax.swing.JLabel();
        playerHandPanel = new javax.swing.JPanel()
        {
            @Override
            public void paintComponent(Graphics g) {
                drawCard(g);

            }
        }
        ;
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });

        rightSidePanel.setOpaque(false);

        currentPlayerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Player Me"));
        currentPlayerPanel.setOpaque(false);

        jLabel6.setText("Score:");

        discardPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Discard Pile"));

        javax.swing.GroupLayout discardPanelLayout = new javax.swing.GroupLayout(discardPanel);
        discardPanel.setLayout(discardPanelLayout);
        discardPanelLayout.setHorizontalGroup(
            discardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 88, Short.MAX_VALUE)
        );
        discardPanelLayout.setVerticalGroup(
            discardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        playerDrawLeftPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Draw Pile"));

        javax.swing.GroupLayout playerDrawLeftPanelLayout = new javax.swing.GroupLayout(playerDrawLeftPanel);
        playerDrawLeftPanel.setLayout(playerDrawLeftPanelLayout);
        playerDrawLeftPanelLayout.setHorizontalGroup(
            playerDrawLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 86, Short.MAX_VALUE)
        );
        playerDrawLeftPanelLayout.setVerticalGroup(
            playerDrawLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel8.setText("Active Player:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Units Killed"));
        jPanel1.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 103, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout currentPlayerPanelLayout = new javax.swing.GroupLayout(currentPlayerPanel);
        currentPlayerPanel.setLayout(currentPlayerPanelLayout);
        currentPlayerPanelLayout.setHorizontalGroup(
            currentPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentPlayerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(currentPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGap(71, 71, 71)
                .addComponent(discardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(playerDrawLeftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        currentPlayerPanelLayout.setVerticalGroup(
            currentPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, currentPlayerPanelLayout.createSequentialGroup()
                .addGroup(currentPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(playerDrawLeftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(discardPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, currentPlayerPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                        .addComponent(jLabel8)))
                .addGap(15, 15, 15))
        );

        buttonsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Phase Panel"));
        buttonsPanel.setOpaque(false);

        jLabel7.setText("Phase");

        chatPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Chat / Game Log"));
        chatPanel.setOpaque(false);

        jScrollPane1.setAutoscrolls(true);

        chatTextArea.setColumns(20);
        chatTextArea.setRows(5);
        chatTextArea.setText("Chat");
        jScrollPane1.setViewportView(chatTextArea);

        sendMessageButton.setText("Send");
        sendMessageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMessageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout chatPanelLayout = new javax.swing.GroupLayout(chatPanel);
        chatPanel.setLayout(chatPanelLayout);
        chatPanelLayout.setHorizontalGroup(
            chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(chatPanelLayout.createSequentialGroup()
                        .addComponent(sendText, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sendMessageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        chatPanelLayout.setVerticalGroup(
            chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendText, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendMessageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        buttonToNextPhase.setText("to Next Phase");
        buttonToNextPhase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonToNextPhaseActionPerformed(evt);
            }
        });

        playCardButton.setText("Play Card");
        playCardButton.setEnabled(false);
        playCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playCardButtonActionPerformed(evt);
            }
        });

        buttonPhaseName.setText("Phase Name");
        buttonPhaseName.setEnabled(false);
        buttonPhaseName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPhaseNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buttonsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(chatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(buttonsPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(buttonsPanelLayout.createSequentialGroup()
                                .addComponent(buttonPhaseName)
                                .addGap(29, 29, 29)
                                .addComponent(buttonToNextPhase))
                            .addComponent(playCardButton))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonsPanelLayout.setVerticalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(buttonPhaseName)
                    .addComponent(buttonToNextPhase))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(playCardButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(chatPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        dicePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Dice Panel"));
        dicePanel.setOpaque(false);

        jLabel15.setText("Dice Panel");

        javax.swing.GroupLayout dicePanelLayout = new javax.swing.GroupLayout(dicePanel);
        dicePanel.setLayout(dicePanelLayout);
        dicePanelLayout.setHorizontalGroup(
            dicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dicePanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel15)
                .addGap(0, 101, Short.MAX_VALUE))
        );
        dicePanelLayout.setVerticalGroup(
            dicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dicePanelLayout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        opponentPlayerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Player Two"));
        opponentPlayerPanel.setOpaque(false);

        jLabel10.setText("Score:");

        discardPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Discard Pile"));

        javax.swing.GroupLayout discardPanel1Layout = new javax.swing.GroupLayout(discardPanel1);
        discardPanel1.setLayout(discardPanel1Layout);
        discardPanel1Layout.setHorizontalGroup(
            discardPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 88, Short.MAX_VALUE)
        );
        discardPanel1Layout.setVerticalGroup(
            discardPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        playerDrawLeftPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Draw Pile"));

        javax.swing.GroupLayout playerDrawLeftPanel1Layout = new javax.swing.GroupLayout(playerDrawLeftPanel1);
        playerDrawLeftPanel1.setLayout(playerDrawLeftPanel1Layout);
        playerDrawLeftPanel1Layout.setHorizontalGroup(
            playerDrawLeftPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 92, Short.MAX_VALUE)
        );
        playerDrawLeftPanel1Layout.setVerticalGroup(
            playerDrawLeftPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel13.setText("Active Player:");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Units Killed"));
        jPanel2.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 103, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout opponentPlayerPanelLayout = new javax.swing.GroupLayout(opponentPlayerPanel);
        opponentPlayerPanel.setLayout(opponentPlayerPanelLayout);
        opponentPlayerPanelLayout.setHorizontalGroup(
            opponentPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opponentPlayerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(opponentPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel13))
                .addGap(72, 72, 72)
                .addComponent(discardPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(playerDrawLeftPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        opponentPlayerPanelLayout.setVerticalGroup(
            opponentPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opponentPlayerPanelLayout.createSequentialGroup()
                .addGroup(opponentPlayerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, opponentPlayerPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(27, 27, 27))
                    .addComponent(discardPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(playerDrawLeftPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        combatPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Combat Panel"));
        combatPanel.setOpaque(false);

        javax.swing.GroupLayout combatPanelLayout = new javax.swing.GroupLayout(combatPanel);
        combatPanel.setLayout(combatPanelLayout);
        combatPanelLayout.setHorizontalGroup(
            combatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 209, Short.MAX_VALUE)
        );
        combatPanelLayout.setVerticalGroup(
            combatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 97, Short.MAX_VALUE)
        );

        tablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Table Panel"));
        tablePanel.setName(""); // NOI18N
        tablePanel.setOpaque(false);

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 313, Short.MAX_VALUE)
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 461, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout rightSidePanelLayout = new javax.swing.GroupLayout(rightSidePanel);
        rightSidePanel.setLayout(rightSidePanelLayout);
        rightSidePanelLayout.setHorizontalGroup(
            rightSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightSidePanelLayout.createSequentialGroup()
                .addGroup(rightSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rightSidePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(opponentPlayerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rightSidePanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(rightSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dicePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 15, Short.MAX_VALUE))
            .addGroup(rightSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rightSidePanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(currentPlayerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(360, Short.MAX_VALUE)))
        );
        rightSidePanelLayout.setVerticalGroup(
            rightSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightSidePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dicePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(rightSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(rightSidePanelLayout.createSequentialGroup()
                        .addComponent(combatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rightSidePanelLayout.createSequentialGroup()
                        .addComponent(opponentPlayerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58))
            .addGroup(rightSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rightSidePanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(currentPlayerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(626, Short.MAX_VALUE)))
        );

        tablePanel.getAccessibleContext().setAccessibleName("");

        mainMapPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainMapPanelMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mainMapPanelMouseReleased(evt);
            }
        });

        jLabel4.setText("Map");

        javax.swing.GroupLayout mainMapPanelLayout = new javax.swing.GroupLayout(mainMapPanel);
        mainMapPanel.setLayout(mainMapPanelLayout);
        mainMapPanelLayout.setHorizontalGroup(
            mainMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMapPanelLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        mainMapPanelLayout.setVerticalGroup(
            mainMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMapPanelLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addContainerGap(785, Short.MAX_VALUE))
        );

        playerHandPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        playerHandPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                playerHandPanelMouseMoved(evt);
            }
        });
        playerHandPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playerHandPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playerHandPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                playerHandPanelMouseExited(evt);
            }
        });

        jLabel1.setText("Hand");

        javax.swing.GroupLayout playerHandPanelLayout = new javax.swing.GroupLayout(playerHandPanel);
        playerHandPanel.setLayout(playerHandPanelLayout);
        playerHandPanelLayout.setHorizontalGroup(
            playerHandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerHandPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(706, Short.MAX_VALUE))
        );
        playerHandPanelLayout.setVerticalGroup(
            playerHandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerHandPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(220, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainWindowPanelLayout = new javax.swing.GroupLayout(mainWindowPanel);
        mainWindowPanel.setLayout(mainWindowPanelLayout);
        mainWindowPanelLayout.setHorizontalGroup(
            mainWindowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainWindowPanelLayout.createSequentialGroup()
                .addGroup(mainWindowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mainWindowPanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(playerHandPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainWindowPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mainMapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rightSidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        mainWindowPanelLayout.setVerticalGroup(
            mainWindowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainWindowPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainWindowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rightSidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainWindowPanelLayout.createSequentialGroup()
                        .addComponent(mainMapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playerHandPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1312, 1312, 1312)
                .addComponent(jLabel5)
                .addContainerGap(447, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainWindowPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainWindowPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentHidden
    

    
    private void playerHandPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerHandPanelMouseClicked
     
                mouseClickedOnHand=1;       
                this.repaint();
                  
    }//GEN-LAST:event_playerHandPanelMouseClicked

    private void playerHandPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerHandPanelMouseEntered
         // TODO add your handling code here:
    }//GEN-LAST:event_playerHandPanelMouseEntered

    private void playerHandPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerHandPanelMouseExited
         // TODO add your handling code here:
    }//GEN-LAST:event_playerHandPanelMouseExited

    private void playerHandPanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerHandPanelMouseMoved
                int deafband=20; //repaint after mouse change of
                
                handMouseCoorX = evt.getPoint().x;
		handMouseCoorY = evt.getPoint().y;
                if(abs(handMouseCoorX-handMouseCoorXdeaf)>deafband){
                    handMouseCoorXdeaf=handMouseCoorX;
                    this.repaint();
                }
              
                if(abs(handMouseCoorY-handMouseCoorYdeaf)>deafband){
                    handMouseCoorYdeaf=handMouseCoorY; 
                    this.repaint();
                }
                
                
                //System.out.println("x:" + handMouseCoorX + " y:"+handMouseCoorY);
                
                
    }//GEN-LAST:event_playerHandPanelMouseMoved

    private void mainMapPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainMapPanelMouseClicked
        int x = evt.getPoint().x;
        int y = evt.getPoint().y;
       
        if(game.getPhase()==2){ //player must be in phase 2 to be able to move units
            
        
        if(! gameGui.mapGui.isUnitSelected() )
        {
            //Position clickedPosition = new Position(  Position.convertMouseXToX(x)   , Position.convertMouseYToY(y)) ;
            //gameGui.mapGui.
            
            
            for(TerrainGUI terrainGUI: gameGui.mapGui.getTerrainsGUI())
            {

                terrainGUI.setSelected(false);
                if(terrainGUI.getPos().checkIfMouseFitInPositon(x, y))
                {
                    terrainGUI.setSelected(true);
                    Position selectedPosition = terrainGUI.getPos();
                    System.out.println("manouvre.gui.GameWindow.mainMapPanelMouseClicked() " + terrainGUI.getPos());
                    if(game.checkCurrentPlayerUnitAtPosition(selectedPosition) ) {

                        gameGui.mapGui.setUnitSelected(true);
                        gameGui.getUnitGuiOnMapGui(selectedPosition).setSelected(true);

                    }

                    //game.getMap().getTileAtIndex(terrainGUI.getPos().getX(), terrainGUI.getPos().getY()).setSelected(true);
                    this.repaint();

                }

            }
        }
        /*
        If unit is selected find which unit to move and move into
        */
        else  {
            Unit selectedUnit = gameGui.getSelectedUnit().getUnit();
            Position clickedPosition = new Position(  Position.convertMouseXToX(x)   , Position.convertMouseYToY(y)) ;

            if(!selectedUnit.getPosition().equals(clickedPosition))
            {
                System.out.println("manouvre.gui.ClientUI.mainMapPanelMouseClicked().clickedPosition :" + clickedPosition) ;

                ArrayList<Position> movePositions =
                game.getPossibleMovement(selectedUnit);

                for(Position checkPosition: movePositions){

                    if(checkPosition.equals(clickedPosition))
                    {

                        MoveUnitCommand moveUnit = new MoveUnitCommand(selectedUnit,  clickedPosition);
                        moveUnit.execute(game);
                        
                        //Move in game and GUI
                      // game.moveUnit(selectedUnit, clickedPosition);
                       //selectedUnit.setPos(clickedPosition);
                        //Unselect all
                        gameGui.unselectAllUnits();
                        //exit loop
                        repaint();
                        break;
                    }
                }
            }
            /*
            Clicking on the same unit - deselects it.
            */
            else
            {
                gameGui.unselectAllUnits();
                repaint();
            }

            // game.moveUnit(  , newPosition);

        }
        
        }
        //else
       
        
    }//GEN-LAST:event_mainMapPanelMouseClicked

    private void mainMapPanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainMapPanelMouseReleased
  //nothing
    }//GEN-LAST:event_mainMapPanelMouseReleased

    private void buttonPhaseNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPhaseNameActionPerformed
       switch(game.getPhase()){
           case 0:
           {
               gameGui.discardSelCards();
               this.repaint();
               return;
           }
           case 1:
           {
               gameGui.drawCards();
               this.repaint();
               return;
           }
           case 2:
           {
                game.nextPhase();  //move
                this.repaint();
                return;
           }
           case 3:
                game.nextPhase();   //restoration
                this.repaint();
                return;
       }
           
    }//GEN-LAST:event_buttonPhaseNameActionPerformed

    private void playCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playCardButtonActionPerformed
        gameGui.playSelectedCard();
        this.repaint();
    }//GEN-LAST:event_playCardButtonActionPerformed

    private void buttonToNextPhaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonToNextPhaseActionPerformed
        game.nextPhase();        // TODO add your handling code here:
        this.repaint();
    }//GEN-LAST:event_buttonToNextPhaseActionPerformed

    private void sendMessageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMessageButtonActionPerformed
        String msg = sendText.getText();
        String target = "All";

        if(!msg.isEmpty() && !target.isEmpty()){
            sendText.setText("");
            client.send(new Message("message", game.getCurrentPlayer().getName(), msg, target));
        }
    }//GEN-LAST:event_sendMessageButtonActionPerformed
    
    /**
	 * check whether the mouse is currently over this piece
	 * @param piece the playing piece
	 * @param x x coordinate of mouse
	 * @param y y coordinate of mouse
	 * @return true if mouse is over the piece
	 */
	
        private boolean mouseOverPiece(TerrainGUI guiTerrain, int x, int y) {

		return guiTerrain.getPos().getMouseX() < x 
			&& guiTerrain.getPos().getMouseX() +guiTerrain.getWidth() > x
			&& guiTerrain.getPos().getMouseY() < y
			&& guiTerrain.getPos().getMouseY()+guiTerrain.getHeight() > y;
	
        }
        
    @Override
    public void printOnChat(String inString)    {
    
    chatTextArea.append(inString+ "\n");
    }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
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
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    new GameWindow().setVisible(true);
//                } catch (Exception ex) {
//                    Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
        
        Player hostPlayer = new Player("Piotr");
        hostPlayer.setHost(true);
        hostPlayer.setNation(CardInterface.AU);
        
        Player guestPlayer = new Player("Bartek");
        guestPlayer.setHost(false);
        guestPlayer.setNation(CardInterface.FR);
        
        ArrayList<Player> players = new ArrayList<>();
        
        players.add(hostPlayer);
        players.add(guestPlayer);
        
        
        Game game = new Game(players);
        
        SocketClient socket = new SocketClient();
        
        //Host window
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GameWindow(game, socket, 0 ).setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        //Guest window
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GameWindow(game, socket, 1 ).setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }

   
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonPhaseName;
    private javax.swing.JButton buttonToNextPhase;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JPanel chatPanel;
    private javax.swing.JTextArea chatTextArea;
    private javax.swing.JPanel combatPanel;
    private javax.swing.JPanel currentPlayerPanel;
    private javax.swing.JPanel dicePanel;
    private javax.swing.JPanel discardPanel;
    private javax.swing.JPanel discardPanel1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainMapPanel;
    private javax.swing.JPanel mainWindowPanel;
    private javax.swing.JPanel opponentPlayerPanel;
    private javax.swing.JButton playCardButton;
    private javax.swing.JPanel playerDrawLeftPanel;
    private javax.swing.JPanel playerDrawLeftPanel1;
    private javax.swing.JPanel playerHandPanel;
    private javax.swing.JPanel rightSidePanel;
    private javax.swing.JButton sendMessageButton;
    private javax.swing.JTextField sendText;
    private javax.swing.JPanel tablePanel;
    // End of variables declaration//GEN-END:variables



}
