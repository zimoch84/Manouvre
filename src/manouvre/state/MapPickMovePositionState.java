/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manouvre.state;

import manouvre.interfaces.MapState;
import java.io.Serializable;
import java.util.ArrayList;
import manouvre.game.Game;
import manouvre.game.Position;
import manouvre.game.Unit;
import manouvre.commands.CommandQueue;
import manouvre.commands.MoveUnitCommand;
import manouvre.commands.WithrdawCommand;
import manouvre.gui.CustomDialogFactory;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author xeon
 */
public class MapPickMovePositionState implements MapState, Serializable{
private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(MapPickAvalibleUnitState.class.getName());   

    @Override
    public void handleInput(Position pos, Game game, CommandQueue cmdQueue , MapStateHandler handler) {
       
        Unit selectedUnit = game.getSelectedUnit();
        if(game.getCurrentPlayer().hasMoved() && ! game.freeMove){
            CustomDialogFactory.showConfirmationDialog("You have moved already, \n play card or proceed to next phase");
      
        LOGGER.debug(game.getCurrentPlayer().getName() + " game.unselectAllUnits()" );
        game.unselectAllUnits();    
        LOGGER.debug(game.getCurrentPlayer().getName() + " Zmiana stanu na MapInputStateHandler.PICK_ONE_UNIT" );
        handler.setState(MapStateHandler.PICK_ONE_UNIT);
        return;
        }
        
        ArrayList<Position> avalaiblePositions =  game.positionCalculator.getCurrentPlayerAvalibleMoveUnitPositions();

        if(avalaiblePositions.contains(pos))
        {
           MoveUnitCommand moveUnit = new MoveUnitCommand(game.getCurrentPlayer().getName() , selectedUnit,  pos);
            /*
             If we done play card and we are not in setup
           */
            switch(game.getPhase()){
            case Game.SETUP:
                /*             Just execute on client            */
                cmdQueue.storeAndExecute(moveUnit);
                game.unselectAllUnits();  
                handler.setState(MapStateHandler.PICK_ONE_UNIT);
            break;
            
            case Game.MOVE:
                cmdQueue.storeAndExecuteAndSend(moveUnit);
                game.unselectAllUnits(); 
                handler.setState(MapStateHandler.PICK_ONE_UNIT);
            break;
                
            case Game.COMBAT:
                if(selectedUnit.isRetriving()){
                WithrdawCommand withdrawCommand = new WithrdawCommand(
                        moveUnit ,  game.getCurrentPlayer().getName() );
                cmdQueue.storeAndExecuteAndSend(withdrawCommand);
                LOGGER.debug(game.getCurrentPlayer().getName() + " Zmiana stanu na MapInputStateHandler.NOSELECTION" );
                handler.setState(MapStateHandler.NOSELECTION);
                }
            }
        }
            
        /* click on unavalaible posiotion */
        else
        {
            if(game.getPhase() != Game.COMBAT)
            {
            LOGGER.debug(game.getCurrentPlayer().getName() + " game.unselectAllUnits()" );
            game.unselectAllUnits();
            LOGGER.debug(game.getCurrentPlayer().getName() + " Zmiana stanu na MapInputStateHandler.PICK_ONE_UNIT" );
            handler.setState(MapStateHandler.PICK_ONE_UNIT);
            }
        }
    }

    @Override
    public String toString() {
        return MapStateHandler.PICK_MOVE_POSITION;
    }
    
    
}
