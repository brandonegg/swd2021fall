package xyz.brandon.blackjack.server.network;

import xyz.brandon.blackjack.client.network.Client;
import xyz.brandon.blackjack.server.BlackJackGame;

import java.io.*;
import java.util.*;
import java.net.*;

/**
 * Object for handling socket creation and client connection for a blackjack game.
 * @see ClientHandler
 */
public class GameServer extends Thread {

    /**
     * port of server
     */
    private int port;
    /**
     * maximum players that can connect to the server
     */
    private int maxPlayers;
    /**
     * List of all active client connections
     */
    private ArrayList<ClientHandler> connectedClients;
    /**
     * mapped client connections to client usernames
     */
    private HashMap<String, ClientHandler> playerClientMapper;
    /**
     * reference to BlackJackGame object, started when players ready
     * @see BlackJackGame
     */
    private BlackJackGame blackJackGame;
    /**
     * Stores whether game is active or not
     */
    private boolean gameActive;

    /**
     * Game server constructor, initializes member variables
     * @param port          Port of server
     * @param maxPlayers    maximum number of players
     */
    public GameServer(int port, int maxPlayers) {
        this.port = port;
        this.maxPlayers = maxPlayers;
        this.playerClientMapper = new HashMap<>();
        blackJackGame = new BlackJackGame(this);
        connectedClients = new ArrayList<>();
        gameActive = false;
    }

    /**
     * Main method called when game server thread is started. Creates
     * sockets and individual threads for handling each client as they
     * connect to the address.
     * @see ClientHandler
     * @see Client
     */
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

    /**
     * Called by player client handler when a player is ready. Adds them to client mapper.
     * @param username  Username of player
     * @param client    ClientHandler object
     */
    public void addReadyPlayer(String username, ClientHandler client) {
        playerClientMapper.put(username, client);
    }

    /**
     * Removes a clienthandler, invoked when a client disconnects
     * @param client    ClientHandler of disconnected client
     */
    public void removeClientHandler(ClientHandler client) {
        for (String key : playerClientMapper.keySet()) {
            if (playerClientMapper.get(key).equals(client)) {
                playerClientMapper.remove(key);
            }
        }
        connectedClients.remove(client);
    }

    /**
     * Starts the BlackJackGame thread once all players are ready.
     * Called by last clienthandler to receive ready response.
     * @see ClientHandler
     */
    public void startGame() {
        blackJackGame = new BlackJackGame(this);
        blackJackGame.start();
        gameActive = true;
    }

    /**
     * Returns if game is active
     * @return  true if active
     */
    public boolean isGameActive() {
        return gameActive;
    }

    /**
     * Determines whether game is able to start, a game is ready to start when
     * all connected clients have indicated they are ready and submit a username.
     * @return  true when all clients ready.
     */
    public boolean gameCanStart() {
        return (connectedClients.size() == playerClientMapper.keySet().size());
    }

    /**
     * Returns the blackjack game manager
     * @return  BlackJackGame manager
     */
    public BlackJackGame getBlackJackGame() {
        return blackJackGame;
    }

    /**
     * Returns the list of usernames that have readied and are connected
     * @return  list of users
     */
    public Set<String> getPlayers() {
        return playerClientMapper.keySet();
    }

    /**
     * Gets the associated ClientHandler for a given username
     * @param username  username of player
     * @return          Associated clienthandler for that player
     * @see ClientHandler
     */
    public ClientHandler getClientFromUsername(String username) {
        if (playerClientMapper.containsKey(username)) {
            return playerClientMapper.get(username);
        }
        return null;
    }

    /**
     * Send a output message to a specific user client
     * @param username  recipient of message
     * @param msg       message to send to username
     */
    public void sendUserClientMsg(String username, String msg) {
        playerClientMapper.get(username).sendMessage(msg);
    }

    /**
     * Send message to all connected and readied clients in the game.
     * @param msg   message to send
     */
    public void sendClientsMsg(String msg) {
        for (String username : playerClientMapper.keySet()) {
            System.out.println("Test:"+msg);
            playerClientMapper.get(username).sendMessage(msg);
        }
    }
}