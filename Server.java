import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 5000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for client...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected.");

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String name = in.readLine();
                String email = in.readLine();

                String utmLink = "https://www.industryacademiacommunity.com/?ref=" + name.replaceAll(" ", "_");

                EmailSender.sendEmail(email, name, utmLink);
                System.out.println("UTM link sent to: " + email);

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
