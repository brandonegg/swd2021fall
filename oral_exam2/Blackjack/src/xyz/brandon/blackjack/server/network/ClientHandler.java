package xyz.brandon.blackjack.server.network;

import xyz.brandon.blackjack.server.BlackJackGame;
import xyz.brandon.blackjack.utils.ArgsParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class ClientHandler extends Thread
{
    private GameServer gameServer;
    private String username;
    private final DataInputStream dis;
    private final DataOutputStream dos;
    private final Socket s;
    private ArrayList<String> dataOutQueue;
    private boolean inGame;


    // Constructor
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

    public String getUsername() {
        return username;
    }

    public boolean isInGame() {
        return inGame;
    }
}