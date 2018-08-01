/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manouvre.gui;

import manouvre.commands.CommandQueue;
import manouvre.game.Game;
import manouvre.interfaces.Command;

/**
 *
 * @author piotr_grudzien
 */
public class CustomDialogFactory {

static CustomDialog dialog;
    
public static void showSureToPlayCardDialog(CommandQueue cmdQueue, Command command, Game game){
    showYesNoDialog("Are You sure to play that card?", cmdQueue, command);
    }
public static void showCannotPlayCardDialog(){
    showConfirmationDialog( "You cannot play this card");
}
public static void showCardNoValidTargetDialog(){
     showConfirmationDialog("This card doesn't have valid target");
    }

public static void showEndSetupDialog(CommandQueue cmdQueue, Command command, Game game){
     
    showYesNoDialog("Are You sure to end setup?", cmdQueue, command);
     
    }

public static void showConfirmationDialog(String infoText){
    if(dialog!= null)
        dialog.setVisible(false);
    dialog = new CustomDialog(CustomDialog.Type.CONFIRMATION_TYPE, infoText);   
    dialog.setVisible(true);
    }    

public static void showYesNoDialog(String decistionText,  CommandQueue cmdQueue, Command yesCommand){
     if(dialog!= null)
        dialog.setVisible(false);
    dialog = new CustomDialog(CustomDialog.Type.YES_NO_TYPE, 
                    decistionText ,
                    cmdQueue);
    dialog.setOkCommand(yesCommand);
    dialog.setVisible(true);
    }

}
