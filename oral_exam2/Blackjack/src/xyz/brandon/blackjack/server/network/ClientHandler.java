package xyz.brandon.blackjack.server.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class ClientHandler extends Thread
{
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    private String username;
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;


    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        username = null;
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

                // Ask user what he wants
                dos.writeUTF("Enter a username: ");

                // receive the answer from client
                received = dis.readUTF();

                if(username == null) {
                    username = received;
                    dos.writeUTF("Your username is: " + username);
                }

                if (received.equalsIgnoreCase("exit")) {
                    connectionActive = false;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }
}