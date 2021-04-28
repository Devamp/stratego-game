package com.example.strategotest.Stratego.actionMessage;

import com.example.strategotest.game.GameFramework.players.GamePlayer;

/**
 * @author Gareth Rice
 * @author Caden Deutscher
 * @author Hewlett De Lara
 * @author Devam Patel
 * @version 04/21
 */
public class PassTurnAction extends com.example.strategotest.game.GameFramework.actionMessage.GameAction {

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PassTurnAction(GamePlayer player) {
        super(player);
    }
}
