package pl.martyna.server.network;

import pl.martyna.server.model.Draw;
import pl.martyna.server.model.IllegalValueException;

import java.io.*;
import java.net.Socket;
import java.util.Set;

/**
 * Class representing service performing pl.martyna.server's task
 * @author Martyna Drabinska
 * @version 1.0
 */
 class SingleService implements Closeable {

    private Draw draw;
    private final Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    SingleService(Socket socket, Draw draw) throws IOException{
        this.socket = socket;
        output = new PrintWriter( new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.draw = draw;
    }

    /** Performs single service */
    void perform(){

        try{
            boolean quit = false;
            while (!quit) {
                String command = input.readLine();
                System.out.println("Users command: " + command);
                switch (command){
                    case "SETTINGS": changeSettings(); break;
                    case "SIMULATE": simulate(); break;
                    case "HELP": help(); break;
                    case "QUIT": quit = true; output.print("OK Closing connection"); break;
                    default:  output.println("ERR Unknown command"); break;
                }
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Changes settings if drawings
     * @throws IOException thrown if an I/O exception has occurred
     */
    private void changeSettings() throws IOException{
        output.println("OK Send min max quantity");
      System.out.println("s");
        int min = Integer.parseInt(input.readLine());

        int max = Integer.parseInt(input.readLine());
        int quantity = Integer.parseInt(input.readLine());
        try {
            System.out.println("ok");
            draw.changeSettings(min, max, quantity);
            System.out.println("ok");
            output.println("OK Settings changed");
        }catch (IllegalValueException ex){
            output.println("ERR Settings not changed");
        }
    }

    /** Carries out a pl.martyna.server drawing */
    private void simulate() {
        Set<Integer> results = draw.randomResults();
        output.println("OK Sending results");
        for(Integer i: results){
            output.print(i);
            output.println("");
        }
        output.println("STOP The end of results");
    }

    /**
     * Sends help to the pl.martyna.server
     */
   private void help(){
        output.println("OK Available commends: SETTINGS, SIMULATE, HELP, QUIT");
    }

    /**
     * Closes socket
     * @throws IOException thrown if an I/O exception has occurred
     */
    @Override
    public void close() throws IOException {
        if(socket != null){
            socket.close();
        }
    }
}
