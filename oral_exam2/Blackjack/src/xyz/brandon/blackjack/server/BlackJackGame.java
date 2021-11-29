package xyz.brandon.blackjack.server;

import java.io.IOException;

public class BlackJackGame {

    private int maxPlayers = 7;
    private int port = 12345;

    private GameServer blackJackServer;
    private int playerCount;

    public BlackJackGame(int port, int maxPlayers) {
        this.port = port;
        this.maxPlayers = maxPlayers;
        start();
    }

    public void start() {
        blackJackServer = new GameServer(port, maxPlayers);
        try {
            blackJackServer.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
