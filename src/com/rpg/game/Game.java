package com.rpg.game;

import com.rpg.entity.character.chars.mage.Megumin;
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

        fight.fight(icefish);
        fight.fight(dirtblock);
    }
}