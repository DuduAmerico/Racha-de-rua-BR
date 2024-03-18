import java.util.Scanner;
import carros.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class CorridaDeCarros {
    private long tempoInicio;
    private long tempoFim;

    public CorridaDeCarros() {
        // Aqui você pode inicializar atributos ou realizar outras tarefas necessárias
    }

    public void iniciarJogo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo à corrida de carros!");
        System.out.println("Escolha seu carro:");
        System.out.println("1. Nissan March 1.6  SRT");
        System.out.println("2. Chevette Ap 2.0");
        System.out.println("3. Gol Bolinha");
        System.out.print("Digite o número do carro: ");
        int escolha = scanner.nextInt();

        Carro carroSelecionado = null;
        String audioCarro = null;
        switch (escolha) {
            case 1:
                carroSelecionado = new NissanMarch("Nissan March 1.6 SRT");
                audioCarro = "C:\\Users\\Eduardo Americo\\Desktop\\Dev\\AulaJava\\Racha de rua BR\\src\\Roncos\\Nissan Micra K13 Turbo  260hp.wav";
                break;
            case 2:
                carroSelecionado = new Chevette("Cevrolet Chevette Ap 2.0");
                audioCarro = "C:\\Users\\Eduardo Americo\\Desktop\\Dev\\AulaJava\\Racha de rua BR\\src\\Roncos\\ANDA MUITOO! #arrancada #chevette #drag.wav";
                break;
            case 3:
                carroSelecionado = new GolBolinha("Gol Bolinha");
                break;
            default:
                System.out.println("Opção inválida. Escolhendo carro padrão...");
                carroSelecionado = new Carro("Carro Padrão", 2); // Carro padrão com velocidade máxima de 100 km/h
        }

        System.out.println("Pressione ENTER quando estiver preparado!");
        scanner.nextLine(); // Limpar o buffer do scanner
        scanner.nextLine(); // Esperar pela entrada do usuário

        System.out.println("A corrida vai começar em...");
        try {
            for (int i = 3; i >= 1; i--) {
                System.out.println(i);
                Thread.sleep(1000); // Espera 1 segundo
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("GO!");
        tempoInicio = System.currentTimeMillis();
        reproduzirAudio(audioCarro);

        // Simulação da corrida
        int distanciaTotal = 100; // Distância total da pista
        int posicaoCarro = 0;

        while (posicaoCarro < distanciaTotal) {
            // Simulação do carro avançando
            posicaoCarro += carroSelecionado.acelerar();

            // Desenha a pista e o carro
            desenharPista(distanciaTotal, posicaoCarro);

            try {
                Thread.sleep(100); // Espera 0.1 segundo entre os avanços dos carros
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Limpa a tela do console
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        finalizarJogo(carroSelecionado);
        scanner.close();
    }

    public void finalizarJogo(Carro carroSelecionado) {
        tempoFim = System.currentTimeMillis();
        long tempoCorrida = tempoFim - tempoInicio;
        long converter = tempoCorrida / 1000;
        System.out.println("Tempo de corrida: " + converter + " milissegundos");
        enviarTempoFinal(tempoCorrida);
        System.out.println("Você terminou a corrida com o " + carroSelecionado.getNome() + "!");
    }

    public void enviarTempoFinal(long tempo) {
        // Aqui você envia o tempo final para o cliente ou servidor
        // Implemente a lógica de envio de tempo conforme necessário
    }

    public static void reproduzirAudio(String caminhoAudio) {
        try {
            File arquivoAudio = new File(caminhoAudio);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(arquivoAudio);
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void desenharPista(int comprimentoPista, int posicaoCarro) {
        StringBuilder pista = new StringBuilder();
        for (int i = 0; i < comprimentoPista; i++) {
            if (i == posicaoCarro) {
                pista.append("o(_;))o"); // Carro
            } else {
                pista.append("_"); // Trecho vazio da pista
            }
        }
        System.out.println(pista.toString());
    }
}
