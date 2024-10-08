package aventuraenlamazmorra;

public class Mapa {
    private static final int FILAS = 14;
    private static final int COLUMNAS = 25;
    private char[][] mazmorra;

    public Mapa() {
        mazmorra = new char[FILAS][COLUMNAS];
        inicializarMazmorra();
    }

    private void inicializarMazmorra() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                mazmorra[i][j] = '.';
            }
        }
    }

    public void crearPared(int x, int y) {
        if (x >= 0 && x < FILAS && y >= 0 && y < COLUMNAS) {
            mazmorra[x][y] = '#';
        }
    }

    public void colocarElemento(int x, int y, char elemento) {
        if (x >= 0 && x < FILAS && y >= 0 && y < COLUMNAS) {
            mazmorra[x][y] = elemento;
        }
    }

    public char obtenerElemento(int x, int y) {
        if (x >= 0 && x < FILAS && y >= 0 && y < COLUMNAS) {
            return mazmorra[x][y];
        }
        return ' ';
    }

    public void mostrarMazmorra() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print(mazmorra[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean esPared(int x, int y) {
        return mazmorra[x][y] == '#';
    }
}
