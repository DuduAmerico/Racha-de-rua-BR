import java.io.*;
import java.net.*;

public class CorridaServidor {
    private static final int PORTA = 12346;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORTA);
            System.out.println("Servidor aguardando conexões na porta " + PORTA);

            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clienteSocket);

                Thread threadCliente = new Thread(new ClienteHandler(clienteSocket));
                threadCliente.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClienteHandler implements Runnable {
        private Socket clienteSocket;

        public ClienteHandler(Socket clienteSocket) {
            this.clienteSocket = clienteSocket;
        }

        @Override
        public void run() {
            try {
                ObjectOutputStream outputStream = new ObjectOutputStream(clienteSocket.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(clienteSocket.getInputStream());

                // Envie uma mensagem de boas-vindas para o cliente
                outputStream.writeObject("Bem-vindo ao jogo de corrida!");

                // Crie uma instância do jogo de corrida
                CorridaDeCarros jogo = new CorridaDeCarros();

                jogo.iniciarJogo();

                // Encerre as conexões
                outputStream.close();
                inputStream.close();
                clienteSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
