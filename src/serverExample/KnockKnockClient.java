package serverExample;

import java.io.*;
import java.net.*;

public class KnockKnockClient {
    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.err.println(
                    "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }
        // this'll run if you just try to run the main() from IntelliJ
        // need to run using a command prompt

        String hostName = args[0];
        // get client name from the command prompt line
        // sample command prompt - "java KnockKnockClient knockknockserver.example.com 4444"
        int portNumber = Integer.parseInt(args[1]);
        // get port number from the command line above

        try (
                Socket kkSocket = new Socket(hostName, portNumber);
                // create the socket within client to communicate with the server
                PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(kkSocket.getInputStream()));
                // kkSocket is connected to the same local port
                // client socket is bound to any available local port (on the client computer)
        ) {
            // once connection with server created
            BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));
            String fromServer;  // string received from server
            String fromUser;    // string client gives

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                // print server message
                if (fromServer.equals("Bye."))
                    break;
                // if server sent "Bye.", end for while loop

                fromUser = stdIn.readLine();
                // next line typed is saved as user input
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    // print client response
                    out.println(fromUser);
                    // send message to server
                }
            }
        } catch (UnknownHostException e) {
            // if host is unknown
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
            // print appropriate error message, exit program
        } catch (IOException e) {
            // if I/O connection fails for some reason
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
            // print appropriate error message, exit program
        }
    }
}
