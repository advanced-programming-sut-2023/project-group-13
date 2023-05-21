package model;

import java.util.ArrayList;

public class Player {
    public static Player currentPlayer;
    public static ArrayList<Player> players = new ArrayList<>();
    private String username;
    private String password;

    private String slogan;
    private String email;
    private String nickname;
    private int questionNumber;
    private String securityQuestionAnswer;
    private int playerRank;
    private int highScore;
    private Boolean isLoggedIn= false;
    public static String[] securityQuestions = {"1. What is my father’s name?",
            "2. What was my first pet’s name?",
            "3. What is my mother’s last name?"};

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static void setPlayers(ArrayList<Player> players) {
        Player.players = players;
    }

    public int getPlayerRank() {
        return playerRank;
    }

    public void setPlayerRank(int playerRank) {
        this.playerRank = playerRank;
    }

    public Player(String username, String password, String slogan, String email, String nickname, int questionNumber, String securityQuestionAnswer) {
        this.username = username;
        this.password = password;
        this.slogan = slogan;
        this.email = email;
        this.nickname = nickname;
        this.questionNumber = questionNumber;
        this.securityQuestionAnswer = securityQuestionAnswer;

    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getSecurityQuestionAnswer() {
        return securityQuestionAnswer;
    }

    public void setSecurityQuestionAnswer(String securityQuestionAnswer) {
        this.securityQuestionAnswer = securityQuestionAnswer;
    }

    public static Player getPlayerByUsername(String username) {
        for (Player player : players) {
            if (player.getUsername().equals(username)) return player;
        }
        return null;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player currentPlayer) {
        Player.currentPlayer = currentPlayer;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore += highScore;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

}
