package xyz.brandon.blackjack.server.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

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

                // receive the answer from client
                received = dis.readUTF();
                String identifier = received.split("=")[0];
                String[] tempargs = received.split("=")[1].split("_");
                HashMap<String, String> args = new HashMap<String, String>();
                boolean transmissionSuccess = false;

                for (String arg : tempargs) {
                    if (args.containsKey(arg)) {
                        System.out.println(arg + " sent has conflicting values.");
                    } else {
                        args.put(arg.split(":")[0], arg.split(":")[1]);
                    }
                }

                if (identifier.equals("connection")) {
                    if (args.containsKey("status") && args.get("status").equals("end")) {
                        connectionActive = false;
                    }
                } else if (identifier.equals("ready")) {
                    if (args.containsKey("username")) {
                        System.out.println(args.get("username") + " is ready!"); //TODO: ready player on server side
                        transmissionSuccess = true;
                    }
                }

                if (transmissionSuccess) {
                    dos.writeUTF(identifier);
                    dos.flush();
                }

            } catch (IOException e) {
                e.printStackTrace();
                connectionActive = false;
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