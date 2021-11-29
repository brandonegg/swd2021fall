package xyz.brandon.blackjack.server;

import xyz.brandon.blackjack.server.network.GameServer;

public class BlackJackGame {

    private int maxPlayers = 7;
    private int port = 12345;

    private GameServer blackJackServer;
    private Deck deck;
    private int playerCount;

    public BlackJackGame(int port, int maxPlayers) {
        this.port = port;
        this.maxPlayers = maxPlayers;
        this.deck = new Deck();
    }

    public void start() {
        blackJackServer = new GameServer(port, maxPlayers);
        blackJackServer.start();
    }

    public void play() {
        System.out.println(deck.toString());
        deck.shuffle();
        System.out.println(deck.toString());
    }

}
