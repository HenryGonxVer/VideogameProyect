package aventuraenlamazmorra;

import java.util.ArrayList;

public class Jugador {
    private int x, y;
    private int vida;
    private int daño;
    private ArrayList<Objeto> inventario;

    public Jugador(int x, int y) {
        this.x = x;
        this.y = y;
        this.vida = 100;
        this.daño = 10;
        this.inventario = new ArrayList<>();
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getVida() { return vida; }
    public int getDaño() { return daño; }

    public void mover(char direccion, Mapa mapa) {
        int nuevoX = x, nuevoY = y;
        switch(direccion) {
            case 'a': nuevoY--; break;
            case 'd': nuevoY++; break;
            case 'w': nuevoX--; break;
            case 's': nuevoX++; break;
        }
        if (!mapa.esPared(nuevoX, nuevoY)) {
            x = nuevoX;
            y = nuevoY;
        }
    }

    public void recibirDaño(int daño) {
        vida -= daño;
    }

    public void aumentarDaño(int incremento) {
        daño += incremento;
    }

    public void agregarAlInventario(Objeto objeto) {
        inventario.add(objeto);
    }

    public void mostrarInventario() {
        System.out.println("Inventario:");
        for (Objeto objeto : inventario) {
            System.out.println("- " + objeto.getTipo());
        }
    }

    public boolean tieneObjeto(String tipo) {
        for (Objeto objeto : inventario) {
            if (objeto.getTipo().equals(tipo)) {
                return true;
            }
        }
        return false;
    }

    public void usarObjeto(String tipo) {
        for (Objeto objeto : inventario) {
            if (objeto.getTipo().equals(tipo)) {
                objeto.usar(this);
                inventario.remove(objeto);
                break;
            }
        }
    }
}