package me.elordenador.laberinto;

public class FilaQuery {
    private int[][] laberinto;
    private int[] pos;

    public void setLaberinto(int[][] laberinto) {
        this.laberinto = laberinto;
    }

    public int[][] getLaberinto() {
        return laberinto;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }

    public int[] getPos() {
        return pos;
    }

    public FilaQuery() {

    }

    public FilaQuery(int[][] laberinto) {
        this.laberinto = laberinto;
    }

    public FilaQuery(int[] pos) {
        this.pos = pos;
    }

    public FilaQuery(int[][] laberinto, int[] pos) {
        this.laberinto = laberinto;
        this.pos = pos;
    }
}
