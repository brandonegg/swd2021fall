package xyz.brandon.blackjack.server;

import xyz.brandon.blackjack.server.network.GameServer;

/**
 * Game server launcher and executor
 */
public class OpenGame {

    /**
     * Main task of class, creates a blackjack server and starts it.
     * @param args  input args
     * @see GameServer
     */
    public static void main(String[] args) {
        GameServer blackJackServer = new GameServer(5056, 7);
        blackJackServer.start();
    }

}
//TODO support 2-7 players