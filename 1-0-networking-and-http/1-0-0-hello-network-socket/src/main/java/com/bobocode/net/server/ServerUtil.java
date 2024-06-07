package com.bobocode.net.server;

import com.bobocode.util.ExerciseNotCompletedException;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Util class for a {@link MessageBoardServer}. It provides all necessary method for network communication. Using these
 * utils you can create server socket, accept connection from client, read String message from client socket and print
 * it using special provided format.
 */
public class ServerUtil {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    private ServerUtil() {
    }

    /**
     * A simple method that returns localhost
     *
     * @return localhost address
     */
    @SneakyThrows
    public static String getLocalHost() {
        return InetAddress.getLocalHost().getHostAddress();
    }

    /**
     * Creates an instance of a {@link ServerSocket} based on the given port.
     *
     * @param port the port number to bind the server socket
     * @return a new bound {@link ServerSocket} instance
     */
    @SneakyThrows
    public static ServerSocket createServerSocket(int port) {
        return new ServerSocket(port);
    }

    /**
     * Accepts a client connection on the provided {@link ServerSocket} and returns the accepted {@link Socket} instance.
     *
     * @param serverSocket an open server socket
     * @return an accepted client {@link Socket} instance
     */
    @SneakyThrows
    public static Socket acceptClientSocket(ServerSocket serverSocket) {
        return serverSocket.accept();
    }

    /**
     * Reads a message sent by the client from the provided {@link Socket}.
     * It reads using {@link BufferedReader} to get a full line message.
     *
     * @param socket an accepted client {@link Socket} instance
     * @return the message received from the client
     */
    @SneakyThrows
    public static String readMessageFromSocket(Socket socket) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return reader.readLine();
    }

    /**
     * Prints the message received from a client to the console in a formatted way.
     *
     * @param socket  the client {@link Socket} instance
     * @param message the message received from the client
     */
    public static void printMessage(Socket socket, String message) {
        InetAddress clientAddress = socket.getInetAddress();
        System.out.print(LocalDateTime.now().format(TIME_FORMATTER) + " ");
        System.out.printf("[%s]", clientAddress.getHostAddress());
        System.out.println(" -- " + message);
    }

}
