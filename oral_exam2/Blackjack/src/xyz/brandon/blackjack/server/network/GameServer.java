package xyz.brandon.blackjack.server.network;

import xyz.brandon.blackjack.client.network.Client;
import xyz.brandon.blackjack.server.BlackJackGame;

import java.io.*;
import java.util.*;
import java.net.*;

// Server class
public class GameServer extends Thread {

    private int port;
    private int maxPlayers;
    private ArrayList<ClientHandler> connectedClients;
    private HashMap<String, ClientHandler> playerClientMapper;
    private BlackJackGame blackJackGame;
    private boolean gameActive;

    public GameServer(int port, int maxPlayers) {
        this.port = port;
        this.maxPlayers = maxPlayers;
        this.playerClientMapper = new HashMap<>();
        blackJackGame = new BlackJackGame(this);
        connectedClients = new ArrayList<>();
        gameActive = false;
    }

    @Override
    public void run()
    {
        // server is listening on port 5056
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Starting Blackjack server at IP: "+ss.getInetAddress().toString()+":"+ss.getLocalPort());
        // running infinite loop for getting
        // client request
        while (true)
        {
            Socket s = null;

            StringJoiner strBldr = new StringJoiner(" ");
            strBldr.add("Clients connected:");
            for (ClientHandler client : connectedClients) {
                strBldr.add(client.getUsername());
            }
            System.out.println(strBldr.toString());

            try
            {
                // socket object to receive incoming client requests
                s = ss.accept();

                System.out.println("A new client is connected : " + s);

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigning new thread for this client");

                // create a new thread object
                Thread t = new ClientHandler(this, s, dis, dos);

                // Invoking the start() method
                t.start();
                connectedClients.add((ClientHandler) t);
            }
            catch (Exception e){
                try {
                    s.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }

    public void addReadyPlayer(String username, ClientHandler client) {
        playerClientMapper.put(username, client);
    }

    public void removeClientHandler(ClientHandler client) {
        for (String key : playerClientMapper.keySet()) {
            if (playerClientMapper.get(key).equals(client)) {
                playerClientMapper.remove(key);
            }
        }
        connectedClients.remove(client);
    }

    public void startGame() {
        blackJackGame = new BlackJackGame(this);
        blackJackGame.start();
        gameActive = true;
    }

    public boolean isGameActive() {
        return gameActive;
    }

    public boolean gameCanStart() {
        return (connectedClients.size() == playerClientMapper.keySet().size());
    }

    public BlackJackGame getBlackJackGame() {
        return blackJackGame;
    }

    public Set<String> getPlayers() {
        return playerClientMapper.keySet();
    }

    public ClientHandler getClientFromUsername(String username) {
        if (playerClientMapper.containsKey(username)) {
            return playerClientMapper.get(username);
        }
        return null;
    }

    public void sendUserClientMsg(String username, String msg) {
        playerClientMapper.get(username).sendMessage(msg);
    }

    public void sendClientsMsg(String msg) {
        for (String username : playerClientMapper.keySet()) {
            System.out.println("Test:"+msg);
            playerClientMapper.get(username).sendMessage(msg);
        }
    }
}