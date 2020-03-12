
package pl.martyna.server.network;

import pl.martyna.server.model.Draw;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 *  Class representing pl.martyna.server
 *  @author Martyna Drabinska
 *  @version 1.0
 *
 */
public class Server implements Closeable{


    private final Draw draw;
    private ServerSocket serverSocket;

    private Server(){
        draw = new Draw();
        Properties properties = new Properties();
        try(InputStream fileInput = new FileInputStream("server/config.properties")) {

            properties.load(fileInput);
            int port = Integer.parseInt(properties.getProperty("port"));
            serverSocket = new ServerSocket( port);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Main method of pl.martyna.server
     * @param args command line arguments
     */
    public static void main(String args[]) {

        Socket socket;
        try(Server server = new Server()) {
            System.out.println("Server started");
            while (true){
                socket =  server.serverSocket.accept();

                try(SingleService service = new SingleService(socket, server.draw)){
                   service.perform();
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        }
        catch(IOException  ex){
            ex.printStackTrace();

        }
    }

    /**
     * Closes the serverSocket
     * @throws IOException thrown if an I/O exception has occurred
     */
    @Override
    public void close() throws IOException {

        if(serverSocket != null){
            serverSocket.close();
        }
    }
}
