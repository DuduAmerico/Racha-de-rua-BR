
// CorridaCliente.java
import java.io.*;
import java.net.*;

public class CorridaCliente {
    private static final String ENDERECO_SERVIDOR = "localhost";
    private static final int PORTA_SERVIDOR = 12346;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(ENDERECO_SERVIDOR, PORTA_SERVIDOR);
            System.out.println("Conexão estabelecida com o servidor.");

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            // Receber mensagem de boas-vindas do servidor
            String mensagem = (String) inputStream.readObject();
            System.out.println("Servidor: " + mensagem);

            CorridaDeCarros jogo = new CorridaDeCarros();

            jogo.iniciarJogo();

            // Fechar conexões
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
