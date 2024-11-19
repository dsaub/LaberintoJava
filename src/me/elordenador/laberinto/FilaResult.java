package me.elordenador.laberinto;

public class FilaResult {
    private Result result;
    private int[][] laberinto;

    public Result getResult() {
        return result;
    }
    public void setResult(Result result) {
        this.result = result;
    }

    public void setLaberinto(int[][] laberinto) {
        this.laberinto = laberinto;
    }

    public int[][] getLaberinto() {
        return laberinto;
    }
}
