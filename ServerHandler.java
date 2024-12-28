import java.io.*;
import java.net.*;

public class ServerHandler {
    public static void main(String[] args) {
        int port = 12345;
        try (java.net.ServerSocket serverSocket = new java.net.ServerSocket(port)) {
            System.out.println("Server is running and waiting for a client to connect...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client is connected: " + clientSocket.getInetAddress().getHostAddress());

            
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = input.readLine()) != null) {
                System.out.println("Received from the client: " + inputLine);
                output.println("Echo: " + inputLine);
            }

            
            input.close();
            output.close();
            clientSocket.close();
            System.out.println("Client is disconnected.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
