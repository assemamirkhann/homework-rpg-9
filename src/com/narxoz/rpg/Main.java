package com.narxoz.rpg;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.Inventory;
import com.narxoz.rpg.artifact.Weapon;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.HeroMemento;
import com.narxoz.rpg.memento.Caretaker;
import com.narxoz.rpg.vault.ChronomancerEngine;
import com.narxoz.rpg.vault.VaultRunResult;

import java.util.Arrays;
import java.util.List;
/**
 * Entry point for Homework 9 — Chronomancer's Vault: Visitor + Memento.
 *
 * The scaffold prints the banner only; students fill in the vault demo.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 9 Demo: Visitor + Memento ===");

        // 1. Create at least 2 heroes with different starting states.
        // 2. Build an artifact inventory and exercise the visitor interface.
        // 3. Capture a hero snapshot through the memento workflow.
        // 4. Rewind the hero after a vault trap changes state.
        // 5. Run the ChronomancerEngine demo sequence.
        // 6. Print a final VaultRunResult summary.
        Inventory inventory1 = new Inventory();
        inventory1.addArtifact(new Weapon("Sword", 100, 5, 15));
        inventory1.addArtifact(new Armor("Knight Armor", 150, 10, 20));

        Inventory inventory2 = new Inventory();
        inventory2.addArtifact(new Weapon("Bow", 80, 3, 10));
        inventory2.addArtifact(new Armor("Leather Armor", 60, 4, 8));

        Hero hero1 = new Hero("Arthas", 100, 50, 25, 10, 200, inventory1);
        Hero hero2 = new Hero("Sylvanas", 80, 70, 30, 5, 150, inventory2);

        System.out.println("\n=== Heroes Created ===");
        System.out.println(hero1);
        System.out.println(hero2);

        Caretaker caretaker = new Caretaker();

        HeroMemento snapshot = hero1.createMemento();
        caretaker.save(snapshot);

        hero1.takeDamage(60);
        hero1.spendGold(50);
        hero1.spendMana(20);

        System.out.println("\n=== After Trap ===");
        System.out.println(hero1);

        hero1.restoreFromMemento(caretaker.undo());

        System.out.println("\n=== After Rewind ===");
        System.out.println(hero1);

        ChronomancerEngine engine = new ChronomancerEngine();

        List<Hero> party = Arrays.asList(hero1, hero2);

        VaultRunResult result = engine.runVault(party);

        System.out.println("\n=== Vault Run Result ===");
        System.out.println(result);
    }
}
