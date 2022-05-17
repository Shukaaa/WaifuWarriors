package com.rpg.game;

import com.rpg.entity.character.npc_chars.IceFish;

public class Game {
    public void startGame() throws InterruptedException {
        FightHandler fight1 = new FightHandler();

        IceFish icefish = new IceFish();

        fight1.fight(icefish);
    }
}
