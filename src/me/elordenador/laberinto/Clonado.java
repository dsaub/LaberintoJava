package me.elordenador.laberinto;

public class Clonado {
    public static int[] clonarArray(int[] myarr) {
        int[] ret = new int[myarr.length];
        for (int i = 0; i < myarr.length; i++) {
            ret[i] = myarr[i];
        }
        return ret;
    }

    public static int[][] clonarMatriz(int[][] matr) {
        int[][] ret = new int[matr.length][matr[0].length];
        for (int i = 0; i < matr.length; i++) {
            for (int j = 0; j < matr[0].length; j++) {
                ret[i][j] = matr[i][j];
            }
        }
        return ret;
    }
}
