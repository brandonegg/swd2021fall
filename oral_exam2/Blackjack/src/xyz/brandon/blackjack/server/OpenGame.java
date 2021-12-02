package xyz.brandon.blackjack.server;

import xyz.brandon.blackjack.server.network.GameServer;

public class OpenGame {

    public static void main(String[] args) {
        GameServer blackJackServer = new GameServer(5056, 7);
        blackJackServer.start();
    }

}
//TODO support 2-7 players