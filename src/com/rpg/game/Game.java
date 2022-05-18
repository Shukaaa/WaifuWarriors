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

        System.out.println("\nDu triffst auf einen Clash Royale Magier Achtung!\n");
        TimeUnit.SECONDS.sleep(2);
        fight.fight(clashroyalemage);
        System.out.println("\nOh nein das war es noch lange nicht!\n");
        fight.fight(icefish);
    }
}