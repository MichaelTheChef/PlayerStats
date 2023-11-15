package me.mike.playerstats.manager;

public class PlayerData {
    private int kills;
    private int deaths;
    private double money;

    public PlayerData() {
        this.kills = 0;
        this.deaths = 0;
        this.money = 0.0;
    }

    // Getters and setters for kills, deaths, and money

    public void incrementKills() {
        this.kills++;
    }

    public void incrementDeaths() {
        this.deaths++;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void addMoney(double amount) {
        this.money += amount;
    }

    public void subtractMoney(double amount) {
        this.money -= amount;
    }

    public double getMoney() {
        return money;
    }
}
