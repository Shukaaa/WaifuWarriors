package com.rpg;

import com.rpg.entity.character.chars.mage.Asuna;
import com.rpg.game.CharacterMenu;
import com.rpg.inventory.EquipMenu;
import com.rpg.inventory.Rune;

public class Main {

    public static void main(String[] args) throws InterruptedException {
//        Game game = new Game();
//
//        game.startGame();

        Asuna asuna = new Asuna();
        EquipMenu runeCreator = new EquipMenu();
        runeCreator.runeRegister("Test", Rune.runeType.ATK, 50);

        CharacterMenu menu = new CharacterMenu();
        menu.openMenu(asuna);
    }
}
