package sample;


import MusicPlayer.audio.MusicPlayer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


public class EnemiesAttackApp extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(new Scene(createContent()));

        primaryStage.setTitle("Enemies Attack!");


        primaryStage.show();
        new MusicPlayer();


    }




    private TextArea output = new TextArea();
    private TextField input = new TextField();


    private Map<String, Command> commands = new HashMap<>();

    //System objects
    Scanner in = new Scanner(System.in);
    Random rand = new Random();

    //Game variables
    String[] enemies = {"Witch", "Goblin", "Vampire", "Spider"};
    int enemyAttackDamage = 25;
    int enemyHealth = 0 + rand.nextInt(75);
    String enemy = enemies[rand.nextInt(enemies.length)];


    // Player variables
    int health = 100;
    int attackDamage = 50;
    int numHeathPotions = 3;
    int healthPotionHealAmount = 30;
    int healthPotionDropChance = 50; //Percentage


    private Parent createContent() {

        output.setPrefHeight(600 - 80);
        output.setFont(Font.font(24));
        output.setEditable(false);
        output.setFocusTraversable(false);


        input.setOnAction(e -> {
            String inputText = input.getText();
            input.clear();
            onInput(inputText);
        });


        VBox root = new VBox(15, output, input);
        root.setPadding(new Insets(15));
        root.setPrefSize(800, 600);


        initGame();

        return root;
    }


    private void restart() {

        int enemyHealth = 0 + rand.nextInt(75);
        String enemy = enemies[rand.nextInt(enemies.length)];

        println("Welcome to Enemies Attack v 0.1");
        println("-------------------------------");


        if (enemyHealth > 0)
            println("\t# " + enemy + " appeared! #\n");
        println("\tYOUR HP: " + health);
        println("\t" + enemy + "'s HP: " + enemyHealth);
        println("\n\tWhat would you like to do?");
        println("\t1. Attack enemy");
        println("\t2. Drink potion");
        println("\t3. Run");

        initCommands();


    }


    private void initGame() {


        println("Welcome to Enemies Attack v 0.1");
        println("-------------------------------");


        if (enemyHealth > 0)
            println("\t# " + enemy + " appeared! #\n");
        println("\tYOUR HP: " + health);
        println("\t" + enemy + "'s HP: " + enemyHealth);
        println("\n\tWhat would you like to do?");
        println("\t1. Attack enemy");
        println("\t2. Drink potion");
        println("\t3. Run");

        initCommands();


    }

    private void initCommands() {
        commands.put("exit", new Command("exit", "Exit the game", Platform::exit));
        commands.put("help", new Command("help", "Prints all commands", this::runHelp));
        commands.put("Attack enemy", new Command("Attack enemy", "Attack enemy", this::enemyAttack));
        commands.put("Run", new Command("Run", "Run from the enemy", this::runAway));
        commands.put("Drink potion", new Command("Drink potion", "Drink health potion to heal HP", this::drinkPotion));
        commands.put("Exit dungeon", new Command("Exit dungeon", "End the game", this::exitDungeon));
        commands.put("Fight more enemies", new Command("Fight more enemies", "Fight more in the dungeon", this::fightdungeon));


    }

    public void fightdungeon() {
        restart();


    }


    private void println(String line) {
        output.appendText(line + "\n");
    }

    private void onInput(String line) {
        if (!commands.containsKey(line)) {
            println("command " + line + " not found");
            return;
        }
        commands.get(line).execute();

    }

    private void runHelp() {
        commands.forEach((name, cmd) -> {
            println(name + "\t" + cmd.getDescription());
        });


    }


    private void runAway() {


        println("\t You run away from the " + enemy + "!");

        println("\nWhat would you like to do now?");
        println("\t1. Fight more enemies");
        println("\t2. Exit dungeon");
        println("---------------------------------------------");


    }


    private void enemyAttack() {


        int damageDealt = rand.nextInt(attackDamage);
        int damageTaken = rand.nextInt(enemyAttackDamage);


        enemyHealth -= damageDealt;
        health -= damageTaken;


        if (enemyHealth < 1) {

            println("\t> You strike the " + enemy + " for " + damageDealt + " damage.");
            if (enemy == "Witch") {
                println("\t> You receive " + damageTaken + " damage from the Witch's poisonous potion");
            }
            if (enemy == "Vampire") {
                println("\t> You receive " + damageTaken + " damage from Vampire's bite");
            }
            if (enemy == "Goblin") {
                println("\t> You receive " + damageTaken + " damage from the Goblin's spell");
            }
            if (enemy == "Spider") {
                println("\t> You receive " + damageTaken + " damage from Spider's web throw");
            }
            println(" # " + enemy + " was defeated! # ");
            println(" # You have " + health + " HP left. #");
            println("---------------------------------------------");
            if (rand.nextInt(100) < healthPotionDropChance) {
                numHeathPotions++;
                println(" # You now have " + numHeathPotions + " health potion(s). #");

            }


            println("What would you like to do now?");
            println("\t1. Fight more enemies");
            println("\t2. Exit dungeon");
            println("---------------------------------------------");

            return;


        }

        if (enemyHealth >= 0) {

            println("\t> You strike the " + enemy + " for " + damageDealt + " damage.");
            if (enemy == "Witch") {
                println("\t> You receive " + damageTaken + " damage from the Witch's poisonous potion");
            }
            if (enemy == "Vampire") {
                println("\t> You receive " + damageTaken + " damage from Vampire's bite");
            }
            if (enemy == "Goblin") {
                println("\t> You receive " + damageTaken + " damage from the Goblin's spell");
            }
            if (enemy == "Spider") {
                println("\t> You receive " + damageTaken + " damage from Spider's web throw");}

            println("\t> You receive  " + damageTaken + " in retaliation!");
            println(" # You have " + health + " HP left. #");
            println(" # The " + enemy + " has " + enemyHealth + " HP left. #");
            println("---------------------------------------------");
            if (rand.nextInt(100) < healthPotionDropChance) {
                numHeathPotions++;
                println(" # You now have " + numHeathPotions + " health potion(s). #");

            }
            println("What would you like to do?");
            println("\t1. Attack enemy");
            println("\t2. Drink potion");
            println("\t3. Run");
            println("---------------------------------------------");

        }


        if (health < 1) {

            println("\t> You have taken too much damage, you are too weak to carry on!");
            println("############");
            println("# THANKS FOR PLAYING! #");
            println("############");


        }

        return;


    }

    private void drinkPotion() {

        if (numHeathPotions > 0) {
            health += healthPotionHealAmount;
            numHeathPotions--;
            println("\t> You drink a health potion, healing yourself for " + healthPotionHealAmount + "."
                    + "\n\t> You now have " + health + " HP."
                    + "\n\t> You have " + numHeathPotions + " health potions left.\n");
        } else {
            println("\t> You have no health potions left! Defeat enemies for a chance to get one.");
        }

        println("What would you like to do now?");
        println("\t1. Attack enemy");
        println("\t2. Drink potion");
        println("\t3. Run");
        println("---------------------------------------------");

        return;


    }


    private void exitDungeon() {
        println("You exit the dungeon, successful from your adventures!");
        println("############");
        println("# THANKS FOR PLAYING! #");
        println("############");


    }


    public static class Launcher {

        public static void main(String[] args) {



            Application.launch(EnemiesAttackApp.class, args);


        }


    }
}





