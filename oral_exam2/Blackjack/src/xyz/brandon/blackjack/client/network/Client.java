package xyz.brandon.blackjack.client.network;// Java implementation for a client
// Save file as Client.java

import xyz.brandon.blackjack.utils.ArgsParser;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Class for representing a player's client connection
 * to a game server.
 * @see xyz.brandon.blackjack.server.network.GameServer
 */
public class Client {

    /**
     * Address of server
     */
    private InetAddress address;
    /**
     * port of server
     */
    private int port;
    /**
     * input stream from server
     */
    private DataInputStream dis;
    /**
     * output stream to server
     */
    private DataOutputStream dos;
    /**
     * Socket connection to gameserver
     */
    private Socket s;
    /**
     * Connection status
     */
    private boolean connected;

    /**
     * Creates client object with stores address and port used when connect
     * is called.
     * @param address   address of server
     * @param port      port of server
     */
    public Client(InetAddress address, int port) {
        this.address = address;
        this.port = port;
        dis = null;
        dos = null;
        s = null;
        connected = false;
    }

    /**
     * Attempts a connection with server. Creates output and input stream if successful.
     * @throws IOException  thrown if connection fails
     */
    public void connect() throws IOException
    {
        if (connected) {
            System.out.println("A connection has already been established!");
            return;
        }

        // establish the connection with server port 5056
        s = new Socket(address, port);

        // obtaining input and out streams
        dis = new DataInputStream(s.getInputStream());
        dos = new DataOutputStream(s.getOutputStream());

        connected = true;

            // the following loop performs the exchange of
            // information between client and client handler
            /*
            while (true)
            {
                System.out.println(dis.readUTF());
                String tosend = scn.nextLine();
                dos.writeUTF(tosend);

                // If client sends exit,close this connection
                // and then break from the while loop
                if(tosend.equals("Exit"))
                {
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");
                    break;
                }

                // printing date or time as requested by client
                String received = dis.readUTF();
                System.out.println(received);
            }

            // closing resources
            scn.close();
            dis.close();
            dos.close();*/

    }

    /**
     * Closes connection with server
     */
    public void disconnect() {
        try {
            s.close();
            dis.close();
            dos.close();
            connected = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns whether client is currently connected to a server
     * @return  true if connected
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * Sends message to server. Follows ArgsParser format
     * @param identifier    message identifier
     * @param args          message arguments
     * @param awaitResponse whether a response is needed
     * @return              transmission success value
     * @see ArgsParser
     */
    public boolean sendString(String identifier, String args, boolean awaitResponse) {
        boolean success = false;
        try {
            dos.writeUTF(identifier + "=" + args); //send string to server
            dos.flush();
            if (awaitResponse) {
                String recieved = dis.readUTF();
                System.out.println(recieved.toString());
                if (recieved.equals(identifier)) {
                    success = true;
                }
            } else {
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * Tells the client to listen for a specific argument identifier.
     * @param identifier    identifier to listen to
     * @return              ArgsParser representation of message with identifier from server
     * @see ArgsParser
     */
    public ArgsParser listenForIdentifier(String identifier) {
        boolean heardArgs = false;
        ArgsParser result = null;

        try {
            while (!heardArgs) {
                String serverMsg = dis.readUTF();
                System.out.println("recieved msg " + serverMsg);

                ArgsParser receivedArgs = new ArgsParser(serverMsg);
                if (receivedArgs.getIdentifier().equals(identifier)) {
                    result = receivedArgs;
                    heardArgs = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Listen for an identifier WITH specified existing args in message. Follow
     * format of ArgsParser
     * @param identifier    argument identifier
     * @param args          message required arguments
     * @return
     * @see ArgsParser
     */
    public boolean listenFor(String identifier, String args) {
        boolean heardArgs = false;
        ArgsParser listeningArgs = new ArgsParser(identifier,args);

        try {
            while (!heardArgs) {
                String serverMsg = dis.readUTF();
                System.out.println("recieved msg " + serverMsg);

                ArgsParser receivedArgs = new ArgsParser(serverMsg);
                if (receivedArgs.getIdentifier().equals(listeningArgs.getIdentifier())) {
                    for (String arg : listeningArgs.getArgs()) {
                        if (listeningArgs.get(arg).equals(receivedArgs.get(arg))) {
                            heardArgs = true;
                        }
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return heardArgs;
    }

    /**
     * Returns string representation of client showing server address:port if
     * a active connection is present.
     * @return  string representation of client
     */
    @Override
    public String toString() {
        if (isConnected()) {
            return "Client connected to: " +address.toString() + ":"+port;
        } else {
            return "Client is not connected";
        }
    }
}