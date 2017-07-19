/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manouvre.state;

import java.io.Serializable;
import java.util.ArrayList;
import manouvre.game.Game;
import manouvre.game.Position;
import manouvre.game.Unit;
import manouvre.game.commands.CommandQueue;
import manouvre.game.commands.MoveUnitCommand;
import manouvre.gui.CustomDialog;

/**
 *
 * @author xeon
 */
public class MapPickUnitMovePositionState implements MapState, Serializable{

    @Override
    public void handleInput(Position pos, Game game, CommandQueue cmdQueue , MapInputStateHandler handler) {
       
        Unit selectedUnit = game.getSelectedUnit();
        
        
        if(game.getCurrentPlayer().hasMoved() && ! game.freeMove){
        CustomDialog cd = new CustomDialog(CustomDialog.CONFIRMATION_TYPE, "You have moved already, \n play card or proceed to next phase");
        cd.setVisible(true);
        game.unselectAllUnits();
        handler.setState(MapInputStateHandler.PICK_ONE_UNIT);
        return;
        }
        
        
        ArrayList<Position> avalaiblePositions =  game.getCurrentPlayerAvalibleMoveUnitPositions();

        if(avalaiblePositions.contains(pos))
        {
           MoveUnitCommand moveUnit = new MoveUnitCommand(game.getCurrentPlayer().getName() , selectedUnit,  pos);
            /*
             If we done play card and we are not in setup
           */
            if(game.getPhase() == Game.MOVE )
            {        
            cmdQueue.storeAndExecuteAndSend(moveUnit);
            game.unselectAllUnits();  
            handler.setState(MapInputStateHandler.PICK_ONE_UNIT);
            }
            /*
            If we dont play card or we are in setup
            */
            if(game.getPhase() == Game.SETUP)
            {   
            /*
            Just execute on client
            */
            cmdQueue.storeAndExecute(moveUnit);
            game.unselectAllUnits();  
            handler.setState(MapInputStateHandler.PICK_ONE_UNIT);
            }
            
        }
            
        else
        {
            game.unselectAllUnits();
            handler.setState(MapInputStateHandler.PICK_ONE_UNIT);
        }
            
            

    }

    
}
