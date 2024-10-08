package aventuraenlamazmorra;

import java.util.Random;
import java.util.Scanner;

public class Mazamorra {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Mapa mapa = new Mapa();

     // Crear paredes en la mazmorra
        for (int i = 0; i < 14; i++) {
            mapa.crearPared(i, 0);
            mapa.crearPared(i, 24);
        }
        for (int j = 0; j < 25; j++) {
            mapa.crearPared(0, j);
            mapa.crearPared(13, j);
        }
        mapa.crearPared(5, 5);
        mapa.crearPared(5, 6);
        mapa.crearPared(5, 7);
        mapa.crearPared(11, 10);
        mapa.crearPared(11, 11);
        mapa.crearPared(11, 12);
        mapa.crearPared(10, 12);
        mapa.crearPared(9, 12);
        mapa.crearPared(10, 14);
        mapa.crearPared(11, 14);
        mapa.crearPared(12, 14);
        mapa.crearPared(2, 2);
        mapa.crearPared(3, 2);
        mapa.crearPared(2, 3);
        mapa.crearPared(2, 4);
        mapa.crearPared(2, 5);
        mapa.crearPared(2, 6);
        mapa.crearPared(4, 2);
        mapa.crearPared(3, 6);
        mapa.crearPared(4, 6);
        mapa.crearPared(7, 6);
        mapa.crearPared(8, 6);
        mapa.crearPared(9, 6);
        mapa.crearPared(7, 5);
        mapa.crearPared(7, 4);
        mapa.crearPared(7, 3);
        mapa.crearPared(7, 1);
        mapa.crearPared(10, 10);
        mapa.crearPared(10, 9);
        mapa.crearPared(10, 8);
        mapa.crearPared(10, 6);
        mapa.crearPared(10, 5);
        mapa.crearPared(10, 4);
        mapa.crearPared(1, 13);
        mapa.crearPared(2, 13);
        mapa.crearPared(3, 13);
        mapa.crearPared(4, 13);
        mapa.crearPared(5, 13);
        mapa.crearPared(6, 13);
        mapa.crearPared(7, 13);
        mapa.crearPared(4, 14);
        mapa.crearPared(4, 15);
        mapa.crearPared(4, 16);
        mapa.crearPared(4, 17);
        mapa.crearPared(4, 18);
        mapa.crearPared(5, 18);
        mapa.crearPared(6, 18);
        mapa.crearPared(9, 18);
        mapa.crearPared(10, 18);

     
        while (true) {
            System.out.println("Bienvenido a Mazamorra");
            System.out.println("1. Instrucciones");
            System.out.println("2. Iniciar Juego");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            if (opcion == 1) {
                mostrarInstrucciones();
            } else if (opcion == 2) {
                iniciarJuego(scanner, random, mapa);
            } else if (opcion == 3) {
                System.out.println("¡Gracias por jugar! Hasta la próxima.");
                break;
            } else {
                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }

        scanner.close();
    }

    private static void mostrarInstrucciones() {
        System.out.println("Instrucciones del Juego:");
        System.out.println("1. Usa las teclas 'w', 'a', 's', 'd' para moverte por la mazmorra.");
        System.out.println("2. Encuentra objetos como pociones y armas para ayudarte en tu aventura.");
        System.out.println("3. Enfréntate a enemigos y usa tu inventario para combatir.");
        System.out.println("4. Encuentra la salida para ganar el juego.");
        System.out.println("5. ¡Buena suerte!");
    }

    private static void iniciarJuego(Scanner scanner, Random random, Mapa mapa) {
        // Crear jugador, enemigos, objetos y salida en posiciones aleatorias
        Jugador jugador = new Jugador(random.nextInt(14), random.nextInt(25));
        Enemigo[] enemigos = {
            new EnemigoNivel2(random.nextInt(14), random.nextInt(25), "Elfo"),
            new EnemigoNivel2(random.nextInt(14), random.nextInt(25), "Slime"),
            new EnemigoNivel3(random.nextInt(14), random.nextInt(25), "Orco")
        };
        Objeto pocion = new Objeto(random.nextInt(14), random.nextInt(25), "pocion");
        Objeto arma = new Objeto(random.nextInt(14), random.nextInt(25), "arma");
        Salida salida = new Salida(random.nextInt(14), random.nextInt(25));

        // Colocar elementos en la mazmorra
        mapa.colocarElemento(jugador.getX(), jugador.getY(), 'J');
        for (Enemigo enemigo : enemigos) {
            // Asegurarse de que los enemigos no se coloquen en la misma posición
            while (mapa.obtenerElemento(enemigo.getX(), enemigo.getY()) != '.') {
                enemigo.x = random.nextInt(14);
                enemigo.y = random.nextInt(25);
            }
            mapa.colocarElemento(enemigo.getX(), enemigo.getY(), 'E');
        }
        mapa.colocarElemento(pocion.getX(), pocion.getY(), 'P');
        mapa.colocarElemento(arma.getX(), arma.getY(), 'A');
        mapa.colocarElemento(salida.getX(), salida.getY(), 'S');

        while (true) {
            // Mostrar mazmorra
            mapa.mostrarMazmorra();

            System.out.println("Jugador está en (" + jugador.getX() + ", " + jugador.getY() + ")");
            System.out.println("Mover (w/a/s/d): ");
            char movimiento = scanner.next().charAt(0);
            mapa.colocarElemento(jugador.getX(), jugador.getY(), '.'); // Limpiar posición anterior
            jugador.mover(movimiento, mapa);
            mapa.colocarElemento(jugador.getX(), jugador.getY(), 'J'); // Actualizar nueva posición

            for (Enemigo enemigo : enemigos) {
                if (jugador.getX() == enemigo.getX() && jugador.getY() == enemigo.getY()) {
                    while (enemigo.getVida() > 0 && jugador.getVida() > 0) {
                        System.out.println("¡Encuentras un " + enemigo.getNombre() + "! Vida del " + enemigo.getNombre() + ": " + enemigo.getVida());
                        System.out.println("1. Atacar");
                        System.out.println("2. Usar poción");
                        System.out.println("3. Equipar espada");
                        int opcion = scanner.nextInt();

                        switch (opcion) {
                            case 1:
                                enemigo.recibirDaño(jugador.getDaño());
                                System.out.println("Atacas al " + enemigo.getNombre() + ". Vida del " + enemigo.getNombre() + ": " + enemigo.getVida());
                                break;
                            case 2:
                                if (jugador.tieneObjeto("pocion")) {
                                    jugador.usarObjeto("pocion");
                                    System.out.println("Usas una poción. Vida del jugador: " + jugador.getVida());
                                } else {
                                    System.out.println("No tienes pociones.");
                                }
                                break;
                            case 3:
                                if (jugador.tieneObjeto("arma")) {
                                    jugador.usarObjeto("arma");
                                    System.out.println("Equipas una espada. Daño del jugador: " + jugador.getDaño());
                                } else {
                                    System.out.println("No tienes armas.");
                                }
                                break;
                            default:
                                System.out.println("Opción no válida.");
                        }

                        if (enemigo.getVida() > 0) {
                            enemigo.atacar(jugador);
                            System.out.println("El " + enemigo.getNombre() + " te ataca. Vida del jugador: " + jugador.getVida());
                        }
                    }

                    if (jugador.getVida() <= 0) {
                        System.out.println("¡Has sido derrotado!");
                        return;
                    } else {
                        System.out.println("¡Has derrotado al " + enemigo.getNombre() + "!");
                    }
                }
            }

            if (jugador.getX() == pocion.getX() && jugador.getY() == pocion.getY()) {
                jugador.agregarAlInventario(pocion);
                System.out.println("¡Encuentras una poción!");
            }

            if (jugador.getX() == arma.getX() && jugador.getY() == arma.getY()) {
                jugador.agregarAlInventario(arma);
                System.out.println("¡Encuentras un arma!");
            }

            if (jugador.getX() == salida.getX() && jugador.getY() == salida.getY()) {
                System.out.println("¡Has encontrado la salida! ¡Felicidades!");
                return;
            }

            if (jugador.getVida() <= 0) {
                System.out.println("¡Has sido derrotado!");
                return;
            }
        }
    }
}