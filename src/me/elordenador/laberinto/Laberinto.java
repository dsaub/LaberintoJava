package me.elordenador.laberinto;

import java.util.Random;
import java.util.Scanner;

public class Laberinto {
    private int[][] laberinto;
    private int ancho, alto;
    private Random random = new Random();

    private static final int PARED = 0;
    private static final int PASILLO = 1;
    private static final int ENTRADA = 3;
    private static final int SALIDA = 4;
    private static final int POSBOT = 5;
    private static final int PASSEDBOT = 6;
    private static final int NOPATHINHERE = 7;

    // Constructor para inicializar el laberinto
    public Laberinto(int tamanyoX, int tamanyoY) {
        this.ancho = tamanyoX;
        this.alto = tamanyoY;
        laberinto = new int[alto][ancho];

        // Inicializar todas las celdas como paredes (0)
        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                laberinto[y][x] = 0;
            }
        }

        // Generar el laberinto desde un punto inicial
        generateLaberinto(1, 1);
        laberinto[1][0] = ENTRADA;
        laberinto[alto-2][ancho-1] = SALIDA;
    }

    // Generar el laberinto usando DFS y backtracking
    private void generateLaberinto(int x, int y) {
        // Direcciones de movimiento: derecha, izquierda, abajo, arriba
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        // Barajar las direcciones para obtener un laberinto aleatorio
        shuffleDirections(dx, dy);

        // Marcar la celda actual como camino
        laberinto[y][x] = 1;

        // Intentar moverse en cada dirección
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i] * 2;
            int ny = y + dy[i] * 2;

            // Verificar que el nuevo movimiento esté dentro del límite y sea una pared
            if (nx > 0 && nx < ancho - 1 && ny > 0 && ny < alto - 1 && laberinto[ny][nx] == 0) {
                // Marcar el camino entre celdas
                laberinto[y + dy[i]][x + dx[i]] = 1;
                generateLaberinto(nx, ny);
            }
        }
    }

    // Método para barajar las direcciones aleatoriamente
    private void shuffleDirections(int[] dx, int[] dy) {
        for (int i = 0; i < dx.length; i++) {
            int j = random.nextInt(dx.length);
            int tempDx = dx[i];
            int tempDy = dy[i];
            dx[i] = dx[j];
            dy[i] = dy[j];
            dx[j] = tempDx;
            dy[j] = tempDy;
        }
    }

    // Imprimir el laberinto
    public void printLaberinto() {
        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                if (laberinto[y][x] == PARED) {
                    System.out.print("█"); // Pared
                } else {
                    System.out.print(" "); // Camino
                }
            }
            System.out.println();
        }
    }

    public static void printLaberintoStatic(int[][] laberinto) {
        for (int y = 0; y < laberinto.length; y++) {
            for (int x = 0; x < laberinto[y].length; x++) {
                if (laberinto[y][x] == PARED) {
                    System.out.print("█"); // Pared
                } else if (laberinto[y][x] == PASSEDBOT) {
                    System.out.print("#");
                } else {
                    System.out.print(" "); // Camino
                }
            }
            System.out.println();
        }
    }

    public void resolveLaberinto() {
        int[] actualPos = {1, 0}; // y, x



        FilaQuery query = new FilaQuery(laberinto, actualPos);
        moveFila(query);

    }

    public static FilaResult moveFila(FilaQuery query) {
        FilaResult result = new FilaResult();
        int[] actualPos = query.getPos();
        int[][] laberinto = query.getLaberinto();
        int cantidadsalidas;

        boolean salida = false;

        try {
            if (laberinto[actualPos[0]-1][actualPos[1]] == SALIDA) {
                salida = true;
                System.out.println("He salido... 1");
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        try {
            if (laberinto[actualPos[0]+1][actualPos[1]] == SALIDA) {
                salida = true;
                System.out.println("He salido... 2");
            }
        } catch (ArrayIndexOutOfBoundsException e) {}
        try {
            if (laberinto[actualPos[0]][actualPos[1]-1] == SALIDA) {
                salida = true;
                System.out.println("He salido... 3");
            }
        } catch (ArrayIndexOutOfBoundsException e) {}
        try {
            if (laberinto[actualPos[0]][actualPos[1]+1] == SALIDA) {
                salida = true;
                System.out.println("He salido... 4");
            }

        } catch (ArrayIndexOutOfBoundsException e) {}




        while (!salida) {
            cantidadsalidas = 0;
            try {
                if (laberinto[actualPos[0]-1][actualPos[1]] == PASILLO) {
                    cantidadsalidas++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {

            }
            try {
                if (laberinto[actualPos[0]+1][actualPos[1]] == PASILLO) {
                    cantidadsalidas++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {}
            try {
                if (laberinto[actualPos[0]][actualPos[1]-1] == PASILLO) {
                    cantidadsalidas++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {}
            try {
                if (laberinto[actualPos[0]][actualPos[1]+1] == PASILLO) {
                    cantidadsalidas++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {}
            // Dependiendo de las salidas pues hacemos cierta accion o no
            if (cantidadsalidas <= 0) {
                // No hay salidas... Checkearemos si hemos terminado.
                boolean finalizado = false;

                try {
                    if (laberinto[actualPos[0]-1][actualPos[1]] == SALIDA) {
                        finalizado = true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }
                try {
                    if (laberinto[actualPos[0]+1][actualPos[1]] == SALIDA) {
                        finalizado = true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}
                try {
                    if (laberinto[actualPos[0]][actualPos[1]-1] == SALIDA) {
                        finalizado = true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}
                try {
                    if (laberinto[actualPos[0]][actualPos[1]+1] == SALIDA) {
                        finalizado = true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}

                if (finalizado) {
                    result.setResult(Result.FINISHED);
                } else {
                    result.setResult(Result.PATH_NOT_FOUND);
                }

                result.setLaberinto(laberinto);
                salida = true;
            }
            if (cantidadsalidas == 1) {
                try {
                    if (laberinto[actualPos[0]-1][actualPos[1]] == PASILLO) {
                        laberinto[actualPos[0]][actualPos[1]] = PASSEDBOT;
                        actualPos[0]--;

                    }
                } catch (ArrayIndexOutOfBoundsException e) {}
                try {
                    if (laberinto[actualPos[0]+1][actualPos[1]] == PASILLO) {
                        laberinto[actualPos[0]][actualPos[1]] = PASSEDBOT;
                        actualPos[0]++;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}
                try {
                    if (laberinto[actualPos[0]][actualPos[1]-1] == PASILLO) {
                        laberinto[actualPos[0]][actualPos[1]] = PASSEDBOT;
                        actualPos[1]--;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}
                try {
                    if (laberinto[actualPos[0]][actualPos[1]+1] == PASILLO) {
                        laberinto[actualPos[0]][actualPos[1]] = PASSEDBOT;
                        actualPos[1]++;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}



                // Hay una salida. Nos moveremos a esta, pero primero debemos buscarla.




            }
            if (cantidadsalidas >= 2) {
                int notFound = 0;
                try {
                    if (laberinto[actualPos[0]-1][actualPos[1]] == PASILLO) {
                        laberinto[actualPos[0]][actualPos[1]] = PASSEDBOT;
                        int[] newPos = {actualPos[0]-1, actualPos[1]};
                        int[] newPos2 = Clonado.clonarArray(newPos);
                        int[][] newLaberinto = Clonado.clonarMatriz(laberinto);

                        FilaQuery query1 = new FilaQuery(newLaberinto, newPos2);
                        FilaResult resultNew = moveFila(query1);
                        if (resultNew.getResult() == Result.FINISHED) return resultNew;
                        if (resultNew.getResult() == Result.PATH_NOT_FOUND) notFound++;

                        if (checkSalida(laberinto, actualPos)) {
                            result.setResult(Result.FINISHED);
                            salida = false;
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}
                try {
                    if (laberinto[actualPos[0]+1][actualPos[1]] == PASILLO) {
                        laberinto[actualPos[0]][actualPos[1]] = PASSEDBOT;
                        int[] newPos = {actualPos[0]+1, actualPos[1]};
                        int[] newPos2 = Clonado.clonarArray(newPos);
                        int[][] newLaberinto = Clonado.clonarMatriz(laberinto);

                        FilaQuery query1 = new FilaQuery(newLaberinto, newPos2);
                        FilaResult resultNew = moveFila(query1);
                        if (resultNew.getResult() == Result.FINISHED) return resultNew;
                        if (resultNew.getResult() == Result.PATH_NOT_FOUND) notFound++;

                        if (checkSalida(laberinto, actualPos)) {
                            result.setResult(Result.FINISHED);
                            salida = false;
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}
                try {
                    if (laberinto[actualPos[0]][actualPos[1]-1] == PASILLO) {
                        laberinto[actualPos[0]][actualPos[1]] = PASSEDBOT;
                        int[] newPos = {actualPos[0], actualPos[1] - 1};
                        int[] newPos2 = Clonado.clonarArray(newPos);
                        int[][] newLaberinto = Clonado.clonarMatriz(laberinto);

                        FilaQuery query1 = new FilaQuery(newLaberinto, newPos2);
                        FilaResult resultNew = moveFila(query1);
                        if (resultNew.getResult() == Result.FINISHED) return resultNew;
                        if (resultNew.getResult() == Result.PATH_NOT_FOUND) notFound++;

                        if (checkSalida(laberinto, actualPos)) {
                            result.setResult(Result.FINISHED);
                            salida = false;
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}
                try {
                    if (laberinto[actualPos[0]][actualPos[1]+1] == PASILLO) {
                        laberinto[actualPos[0]][actualPos[1]] = PASSEDBOT;
                        int[] newPos = {actualPos[0], actualPos[1]+1};
                        int[] newPos2 = Clonado.clonarArray(newPos);
                        int[][] newLaberinto = Clonado.clonarMatriz(laberinto);

                        FilaQuery query1 = new FilaQuery(newLaberinto, newPos2);
                        FilaResult resultNew = moveFila(query1);
                        if (resultNew.getResult() == Result.FINISHED) return resultNew;
                        if (resultNew.getResult() == Result.PATH_NOT_FOUND) notFound++;

                        if (checkSalida(laberinto, actualPos)) {
                            result.setResult(Result.FINISHED);
                            salida = false;
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}

                if (notFound >= cantidadsalidas) {
                    result.setResult(Result.PATH_NOT_FOUND);
                    result.setLaberinto(laberinto);
                    salida = true;
                }




            }
            printLaberintoStatic(laberinto);
            System.out.println("Salidas: "+cantidadsalidas);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }


    // Método principal para ejecutar el programa
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Dime el ancho del laberinto: ");
        int ancho = sc.nextInt();
        System.out.print("Dime el alto del laberinto: ");
        int alto = sc.nextInt();

        // Asegurarse de que el laberinto sea de tamaño impar para mejor generación
        if (ancho % 2 == 0) ancho++;
        if (alto % 2 == 0) alto++;

        Laberinto laberinto = new Laberinto(ancho, alto);
        laberinto.printLaberinto();

        laberinto.resolveLaberinto();
    }

    public static boolean checkSalida(int[][] laberinto, int[] pos) {
        try {
            if (laberinto[pos[0]][pos[1]] == SALIDA) {
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {}

        try {
            if (laberinto[pos[0]-1][pos[1]] == SALIDA) {
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {}

        try {
            if (laberinto[pos[0]+1][pos[1]] == SALIDA) {
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {}

        try {
            if (laberinto[pos[0]][pos[1]-1] == SALIDA) {
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {}

        try {
            if (laberinto[pos[0]][pos[1]+1] == SALIDA) {
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {}
        return false;
    }
}