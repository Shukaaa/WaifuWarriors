package com.rpg.entity.character.chars;

import com.rpg.entity.character.PlayerChar;

public class Bonker extends PlayerChar {

    private int stamina;

    public Bonker(int posX, double HP, String name, double ATK, double DEF, int intelligence, Gender Gender, int stamina, int suspend, charElement element) {
        super(posX, HP, name, ATK, DEF, intelligence, Gender, suspend, element);
        this.stamina = stamina;
    }
}