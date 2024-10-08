package aventuraenlamazmorra;

public class Objeto {
    private int x, y;
    private String tipo;

    public Objeto(int x, int y, String tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public String getTipo() { return tipo; }

    public void usar(Jugador jugador) {
        if (tipo.equals("pocion")) {
            jugador.recibirDaño(-20); // Recupera vida
        } else if (tipo.equals("arma")) {
            jugador.aumentarDaño(10); // Aumenta el daño del jugador
        }
    }
}