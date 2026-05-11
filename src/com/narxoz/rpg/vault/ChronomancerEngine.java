package com.narxoz.rpg.vault;

import com.narxoz.rpg.combatant.Hero;
import java.util.List;
import com.narxoz.rpg.memento.Caretaker;
import com.narxoz.rpg.combatant.HeroMemento;

/**
 * Orchestrates the Chronomancer's Vault demo run.
 */
public class ChronomancerEngine {

    /**
     * Runs the vault sequence for the supplied party.
     *
     * @param party the heroes entering the vault
     * @return a placeholder result in the scaffold
     */
    public VaultRunResult runVault(List<Hero> party) {
        // TODO: wire together mementos, visitors, and the vault sequence.
        Caretaker caretaker = new Caretaker();
        int survivors = 0;
        int totalGold = 0;
        int totalHp = 0;
        for (Hero hero : party) {
            HeroMemento snapshot = hero.createMemento();
            caretaker.save(snapshot);

            hero.takeDamage(20);
            hero.addGold(50);

            if (!hero.isAlive()) {
                HeroMemento restorePoint = caretaker.undo();
                hero.restoreFromMemento(restorePoint);
            }
            if (hero.isAlive()) {
                survivors++;
            }
            totalGold += hero.getGold();
            totalHp += hero.getHp();
        }
        return new VaultRunResult(survivors, totalGold, totalHp);
    }
}
