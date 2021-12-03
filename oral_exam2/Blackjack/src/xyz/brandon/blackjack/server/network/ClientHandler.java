package xyz.brandon.blackjack.server.network;

import xyz.brandon.blackjack.utils.ArgsParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Object for handling a client to server connection. Threaded to allow
 * for multiple client connections. Interface for sending and receiving data
 * between server and client.
 */
class ClientHandler extends Thread
{
    /**
     * Associated gameserver the client is speaking with
     */
    private GameServer gameServer;
    /**
     * stored username of client (initially null)
     */
    private String username;
    /**
     * client input stream
     */
    private final DataInputStream dis;
    /**
     * client output stream
     */
    private final DataOutputStream dos;
    /**
     * Socket handling stream
     */
    private final Socket s;
    /**
     * Stored queue for output data (when needing to wait for a response to output)
     */
    private ArrayList<String> dataOutQueue;
    /**
     * Whether client in game
     */
    private boolean inGame;


    /**
     * Creates a client connection, initializes member variables
     * @param gameServer    GameServer object client is speaking to
     * @param s             Socket clienthandler and client are on
     * @param dis           input data stream
     * @param dos           output data stream
     */
    public ClientHandler(GameServer gameServer, Socket s, DataInputStream dis, DataOutputStream dos)
    {
        this.gameServer = gameServer;
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this.inGame = false;
        username = null;
        dataOutQueue = new ArrayList<>();
    }

    /**
     * Main method which runs when clienthandler thread is started. Continually listens to send
     * data from server to client and listen for client data sent to server. Processes accordingly
     * @see xyz.brandon.blackjack.client.network.Client
     */
    @Override
    public void run()
    {
        String received;
        String toreturn;

        boolean connectionActive = true;
        while (connectionActive)
        {
            try {
                System.out.println("Size of data out " +dataOutQueue.size());
                if (dataOutQueue.size() != 0) {
                    System.out.println("Sending message " +dataOutQueue.get(0));
                    dos.writeUTF(dataOutQueue.remove(0));
                    dos.flush();
                }

                // receive the answer from client
                received = dis.readUTF();
                System.out.println("Received message " + received);
                ArgsParser args = new ArgsParser(received);
                String identifier = args.getIdentifier();

                boolean transmissionSuccess = false;

                if (identifier.equals("connection")) {
                    if (args.has("status")) {
                        if (args.get("status").equals("end")) {
                            connectionActive = false;
                        } else if (args.get("status").equals("ingame")) {
                            inGame = true;
                        }
                    }
                } else if (identifier.equals("ready")) {
                    if (args.has("username")) {
                        System.out.println(args.get("username") + " is ready!"); //TODO: ready player on server side
                        transmissionSuccess = true;
                        gameServer.addReadyPlayer(args.get("username"), this);
                        if (gameServer.gameCanStart()) {
                            gameServer.startGame();
                        }
                    }
                } else if (identifier.equals("action")) {
                    if (args.has("username") && args.has("type") && gameServer.getBlackJackGame().isGameActive()) {
                        if (args.get("type").equals("hit") || args.get("type").equals("bust") || args.get("type").equals("stand")) {
                            gameServer.getBlackJackGame().queueAction(args.get("username"), args.get("type"));
                        } else {
                            System.out.println("Received unrecognized action: " + args.get("type"));
                        }
                    }
                }

                if (transmissionSuccess) {
                    System.out.println("Doing transmission success");
                    dos.writeUTF(identifier);
                    dos.flush();
                }

            } catch (IOException e) {
                e.printStackTrace();
                connectionActive = false;
            }
        }
        System.out.println("Doing stff success");

        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();

        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("Ended client handler loop transmission success");
    }

    /**
     * Send a output message to the connected client. Skips queue
     * @param message   message to send
     */
    public void sendMessage(String message) {
        //System.out.println("last Test:"+message);
        //dataOutQueue.add(message);
        try {
            dos.writeUTF(message);
            dos.flush();
            System.out.println("Sending message " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the associated username of the client's player
     * @return  username of player
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns whether player is in game or not
     * @return  true if player in game
     */
    public boolean isInGame() {
        return inGame;
    }
}