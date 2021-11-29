package xyz.brandon.blackjack.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class StartPlayer {

    public static void main(String[] args) {
        Client player = null;
        try {
            player = new Client(InetAddress.getByName("localhost"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        ;
        try {
            player.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
