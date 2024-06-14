package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class JokenpoClient {
    private static final int SERVER_PORT = 12345;  // Porta do servidor

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Uso: java JokenpoClient <endereco_ip_servidor> <nome_jogador>");
            System.exit(1);
        }

        String serverAddress = args[0]; // Obtém o endereço IP do servidor do argumento de linha de comando
        String playerName = args[1];    // Obtém o nome do jogador do argumento de linha de comando
        
        try (
            // Cria um socket para conectar ao servidor
            Socket socket = new Socket(serverAddress, SERVER_PORT);
            // Cria PrintWriter para enviar dados ao servidor
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Cria BufferedReader para receber dados do servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            Scanner scanner = new Scanner(System.in);  // Scanner para ler a entrada do usuário
            System.out.println(in.readLine());  // Recebe e imprime mensagem de boas-vindas do servidor

            out.println(playerName);  // Envia o nome do jogador para o servidor
            
            while (true) {
                System.out.println(in.readLine());  // Recebe e imprime mensagem do servidor sobre o adversário

                // Solicita ao jogador para escolher sua jogada
                System.out.println("Escolha sua jogada (Pedra, Papel, Tesoura): ");
                String move = scanner.nextLine();  // Lê a jogada do jogador
                out.println(move);  // Envia a jogada para o servidor
                System.out.println(in.readLine());  // Recebe e imprime confirmação da jogada
                System.out.println(in.readLine());  // Recebe e imprime o resultado da partida
            }

        } catch (IOException e) {
            e.printStackTrace();  // Imprime a pilha de exceções em caso de erro
        }
    }
}
