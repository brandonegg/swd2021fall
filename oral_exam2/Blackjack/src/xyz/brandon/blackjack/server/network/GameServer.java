package xyz.brandon.blackjack.server.network;

import xyz.brandon.blackjack.server.network.ClientHandler;

import java.io.*;
import java.util.*;
import java.net.*;

// Server class
public class GameServer extends Thread {

    private int port;
    private int maxPlayers;
    private ArrayList<ClientHandler> connectedClients;

    public GameServer(int port, int maxPlayers) {
        this.port = port;
        this.maxPlayers = maxPlayers;
        connectedClients = new ArrayList<>();
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
                Thread t = new ClientHandler(s, dis, dos);

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
}