package com.rpg.game;

import com.rpg.entity.character.npc_chars.*;

public class Game {
    public void startGame() throws InterruptedException {
        // FightHandler
        FightHandler fight = new FightHandler();

        // Add NPCs
        IceFish icefish = new IceFish();
        BobTheBird bobthebird = new BobTheBird();
        DirtBlock dirtblock = new DirtBlock()
;
        fight.fight(bobthebird);
    }
}
