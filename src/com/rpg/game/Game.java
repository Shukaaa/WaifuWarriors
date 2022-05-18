package com.rpg.game;

import com.rpg.entity.character.npc_chars.*;

import java.util.concurrent.TimeUnit;

public class Game {
    public void startGame() throws InterruptedException {
        // FightHandler
        FightHandler fight = new FightHandler();

        // Add NPCs
        IceFish icefish = new IceFish();
        BobTheBird bobthebird = new BobTheBird();
        DirtBlock dirtblock = new DirtBlock();
        PikachuMitEisenschwert pikachu = new PikachuMitEisenschwert();
        WatergunWale watergunwale = new WatergunWale();
        ClashRoyaleMage clashroyalemage = new ClashRoyaleMage();

        fight.fight(dirtblock);
        System.out.println("\nOh nein das war es noch lange nicht!\n");
        fight.fight(dirtblock);
    }
}