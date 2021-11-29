package xyz.brandon.blackjack.server;

public class OpenGame {

    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame(5056, 7);
        blackJackGame.start();
    }

}
//TODO support 2-7 players