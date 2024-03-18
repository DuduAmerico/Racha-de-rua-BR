// Carro.java
package carros;

public class Carro {
    private String nome;
    private double velocidadeMaxima;

    public Carro(String nome, double velocidadeMaxima) {
        this.nome = nome;
        this.velocidadeMaxima = velocidadeMaxima;
    }

    public String getNome() {
        return nome;
    }

    public double getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    // Método para simular a velocidade do carro
    public double acelerar() {
        return (double) (Math.random() * velocidadeMaxima);
    }
}
