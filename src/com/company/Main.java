package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 2000;
    public static int bossDamage = 50;
    public static String bossDefenceType;
    public static int[] heroesHealth = {260, 250, 270, 1000, 250,25,400,300};
    public static int[] heroesDamage = {20, 15, 10, 10,75,40,50, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Tank","Lucky","Berserk","Thor"," Medic"};
    public static int roundNumber;

    public static void main(String[] args) {
        printStatistics();

        while (!isGameFinished()) {
            round();
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss chose: " + bossDefenceType);
    }

    public static void round() {
        roundNumber++;
        sewUp();
        bossHits();
        stan();
        berserk();
        lucky();
        heroesHit();
        printStatistics();
        unStan();
        healing();
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i]; //  bossHealth -= heroesDamage[i];
                    }
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println(roundNumber + " ROUND -------------");
        System.out.println("Boss health: " + bossHealth + " (" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
                    + heroesHealth[i] + " (" + heroesDamage[i] + ")");
        }
    }

    public static void healing() {
        Random random = new Random();
        int X = random.nextInt(heroesHealth.length - 1);
        for (int i = 0; i < heroesHealth[X]; i++) {
            if (heroesHealth[X] <= 100 && heroesHealth[X] > 0) {
                heroesHealth[X] = heroesHealth[X] + 50;
                System.out.println("вылечил"+heroesAttackType[X + 1]);
            }

        }
    }

    public static void sewUp() {
        int part = 50 / 5;
        bossDamage = 40;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i == 3) {
                continue;
            }
        }
        heroesHealth[3] = heroesHealth[3] - part * heroesHealth.length;


        // heroesHealth[3]=heroesHealth[3]-;
    }
    public static void lucky(){
         Random random = new Random();
        /* boolean plu = random.nextBoolean();
         if(plu==true){
             heroesHealth[4]+=40;
         }*/
        int a = random.nextInt(9) + 1;
        switch (a){
            case 6:
                heroesHealth[4]+=40;
                System.out.println("Lucky");
                break;
        }

    }
    public static void berserk(){

        Random random=new Random();
        int block=random.nextInt(40)+1;
        heroesHealth[5]+=block;
        heroesDamage[5]+=block;
        System.out.println("обратка"+block);
    }
    public static void stan(){
        Random random=new Random();
                int s= random.nextInt(10);
        if (s==5){
            bossDamage-= bossDamage;
            System.out.println("*********стан***********");
        }
    }
    public static void unStan(){
        bossDamage=50;
    }

}
