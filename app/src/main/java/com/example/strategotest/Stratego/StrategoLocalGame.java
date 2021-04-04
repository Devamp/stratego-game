package com.example.strategotest.Stratego;

import com.example.strategotest.Stratego.actionMessage.PassTurnAction;
import com.example.strategotest.Stratego.actionMessage.StrategoMoveAction;
import com.example.strategotest.game.GameFramework.LocalGame;
import com.example.strategotest.game.GameFramework.actionMessage.GameAction;
import com.example.strategotest.Stratego.infoMessages.StrategoGameState;
import com.example.strategotest.game.GameFramework.players.GamePlayer;

public class StrategoLocalGame extends LocalGame {

    StrategoGameState officialState;



    /**
     * Constructor for the StrategoLocalGame
     *
     */
    public StrategoLocalGame(){
    super();
    super.state = new StrategoGameState();

    }

    /**
     * Constructor for the StrategoLocalGame with loaded StrategoGameState
     * @param stState
     */
    public StrategoLocalGame(StrategoGameState stState){
        super();
        super.state = new StrategoGameState(stState);
        officialState = new StrategoGameState(stState);
    }

    /**
     * Notify the given player that its state has changed. This should involve sending
     * a GameInfo object to the player. If the game is not a perfect-information game
     * this method should remove any information from the game that the player is not
     * allowed to know.
     *
     * @param p
     * 			the player to notify
     */

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new StrategoGameState((StrategoGameState) state));
    }

    @Override
    protected boolean canMove(int playerIdx) {
        return playerIdx == ((StrategoGameState)state).getTurn();
    }

    @Override
    protected String checkIfGameOver() {

        if(((StrategoGameState)state).getPhase() != 0 ){ // make sure game phase is not in placement

            //check win by checking if flag has been captured
            if((((StrategoGameState)state).getBlueCharacter())[0] == 1){ // if flag counter is 1, that means a flag has been captured
                return "Congratulations! " + playerNames[0] + "has captured the enemy flag and has won the game!"; // blue flag has been captured, so red won the game
            } else if((((StrategoGameState)state).getRedCharacter())[0] == 1){
                return "Congratulations! " + playerNames[1] + "has captured the enemy flag and has won the game!"; // red flag has been captured, so blue won the game
            }

            //check win by checking if either array of pieces has maxed out, indicating all troops have been captured
            if((((StrategoGameState)state).getBlueCharacter())[1] == 1){
                //check the max troop limit for each piece, use && in if statement possibly?
            }
        }
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        if(action instanceof PassTurnAction){
//            officialState.endTurn();
            ((StrategoGameState)state).endTurn();
            return true;
        }else if(action instanceof StrategoMoveAction){
            StrategoMoveAction toUse = (StrategoMoveAction) action;
            ((StrategoGameState)state).action(toUse.getFromX(), toUse.getFromY(), toUse.getToX(), toUse.getToY());
            return true;
        }else{
            return false;
        }
    }
}
