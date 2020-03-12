package pl.martyna.client.network;

import pl.martyna.client.view.LottoView;
import pl.martyna.client.view.MenuOption;

import java.io.*;
import java.net.Socket;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class representing a pl.martyna.client, contains communication protocol for pl.martyna.client
 * @author Martyna Drabinska
 * @version 1.0
 *
 */
public class Client implements Closeable {

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private final LottoView view;

     private Client() throws IOException {

        Properties properties = new Properties();
        view = new LottoView();
        InputStream fileInput = new FileInputStream("client/config.properties");

            properties.load(fileInput);
            int port = Integer.parseInt(properties.getProperty("port"));
            String host = properties.getProperty("host");
            socket = new Socket(host, port);

            output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            fileInput.close();
    }

    /**
     * Main method of pl.martyna.client
     * @param args command line arguments
     */
    public static void main(String[] args) {

        try(Client client = new Client()){
            System.out.println("Connected to pl.martyna.client");
            MenuOption chosenMenuOption;
            do {
                client.view.displayMenu();
                chosenMenuOption = client.view.retrieveMenuOption();

                switch (chosenMenuOption) {
                    case SETTINGS:
                        client.changeSettings(); break;
                    case SIMULATE:
                        client.simulate(); break;
                    case QUIT: client.output.println("QUIT"); break;
                    case HELP:
                        client.help(); break;
                    default:
                        System.out.println("Unknown menu option");
                }
            }while(chosenMenuOption != MenuOption.QUIT);
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Changes settings of drawing
     * @throws IOException thrown if an I/O exception has occurred
     */
    private void changeSettings() throws IOException {
        output.println("SETTINGS");
        String answer = input.readLine();
        if(answer.startsWith("OK")){
            output.println(view.retrieveMin());
            output.println(view.retrieveMax());
            output.println(view.retrieveResultsQuantity());
            answer = input.readLine();
            if(answer.startsWith("ERR")){
                view.displayMessage(answer);
            }
        }
        else{
            view.displayMessage(answer);
        }
    }

    /**
     * Carries out a pl.martyna.client drawing
     * @throws IOException thrown if an I/O exception has occurred
     */
    private void simulate() throws IOException{
        output.println("SIMULATE");
        Set<Integer> results = new TreeSet<>();
        String answer = input.readLine();
        if(answer.startsWith("OK")){
            while(true){
                answer = input.readLine();
                if(answer.startsWith("STOP")){
                    break;
                }else{
                    results.add(Integer.parseInt(answer));
                }
            }
        }
        view.displayResults(results);
    }

    /**
     * Displays available pl.martyna.client commands
     * @throws IOException thrown if an I/O exception has occurred
     */
    private void help() throws IOException{
        output.println("HELP");
        view.displayMessage(input.readLine());
    }

    /**
     * Closes clients socket
     * @throws IOException thrown if an I/O exception has occurred
     */
    @Override
    public void close() throws IOException {
        if(socket != null){
            socket.close();
        }
    }
}
