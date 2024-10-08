package aventuraenlamazmorra;

public class Enemigo {
    protected int x, y;
    protected int vida;
    protected int daño;
    protected String nombre;

    public Enemigo(int x, int y, int vida, int daño, String nombre) {
        this.x = x;
        this.y = y;
        this.vida = vida;
        this.daño = daño;
        this.nombre = nombre;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getVida() { return vida; }
    public int getDaño() { return daño; }
    public String getNombre() { return nombre; }

    public void atacar(Jugador jugador) {
        jugador.recibirDaño(daño);
    }

    public void recibirDaño(int daño) {
        vida -= daño;
    }
}
