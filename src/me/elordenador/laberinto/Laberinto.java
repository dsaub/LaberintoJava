package me.elordenador.laberinto;

/*
0 = PARED
1 = CAMINO
 */

import java.util.Random;
import java.util.Scanner;

public class Laberinto {
    private int[][] laberinto;
    private int ancho, alto;
    private Random random = new Random();
    public Laberinto(int tamanyox, int tamanyoy) {
        this.ancho = tamanyox;
        this.alto = tamanyoy;
        laberinto = new int[tamanyox][tamanyoy];

        generateLaberinto(0, 0);
    }


    private void generateLaberinto(int x, int y) {
        int[] dx = {0, 0, -1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int dir = random.nextInt(4);
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx >= 0 && nx < ancho && ny >= 0 && ny < alto && laberinto[ny][nx] == 0) {
                laberinto[y + dy[dir]][x + dx[dir]] = 1;
                laberinto[ny][nx] = 1;
                generateLaberinto(nx, ny);
            }
        }
    }
    public void printLaberinto() {
        for (int y = 0; y < ancho; y++) {
            for (int x = 0; x < alto; x++) {
                if (laberinto[x][y] == 0) {
                    System.out.print("█");
                } else {
                    System.out.print(" ");
                }

            }
            System.out.println();
        }
    }




    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dime la posición X: ");
        int x = sc.nextInt();
        System.out.println("Dime la posición Y: ");
        int y = sc.nextInt();
        Laberinto laberinto = new Laberinto(x, y);
        laberinto.printLaberinto();
    }
}
