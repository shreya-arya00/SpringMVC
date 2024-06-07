package com.bobocode.net.client;

import com.bobocode.net.server.MessageBoardServer;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.net.Socket;

import static com.bobocode.net.client.ClientUtil.*;

/**
 * {@link MessageBoardClient} is a client app that allows connecting to the {@link MessageBoardServer} in order to send
 * a message. This app reads a message from the console, connects to the server socket, and sends a message writing
 * data to the output stream. The message then will be printed on the {@link MessageBoardServer} side. The app will
 * ask the user to type a message infinitely until the user enters `q`.
 * <p>
 * PLEASE MAKE SURE THAT {@link MessageBoardServer} is running before using the client app.
 * <p>
 * If you have another machine, you can try to run {@link MessageBoardServer} on that machine. In order to do that, you
 * need to make sure that you can find that machine by IP address, and the port you want to use is exposed properly.
 * If so, you will need to specify corresponding HOST and PORT values below.
 */
public class MessageBoardClient {
    private static final String SERVER_ADDRESS = MessageBoardServer.HOST;
    private static final int SERVER_PORT = MessageBoardServer.PORT;

    @SneakyThrows
    public static void main(String[] args) {
        try (BufferedReader reader = openConsoleReader()) {
            System.out.println("Message Board Client started. Type 'q' to quit.");

            while (true) {
                String message = readMessage(reader);

                if (message.equalsIgnoreCase("q")) {
                    System.out.println("Exiting Message Board Client...");
                    break;
                }

                try (Socket socket = openSocket(SERVER_ADDRESS, SERVER_PORT)) {
                    writeToSocket(message, socket);
                    System.out.println("Message sent to server: " + message);
                } catch (Exception e) {
                    System.err.println("Error sending message: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading from console: " + e.getMessage());
        }
    }
}
