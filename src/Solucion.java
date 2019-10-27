import java.util.ArrayList;

public class Solucion {
   private ArrayList<Integer> listaOperaciones;
    private int numMovimientos;
    private boolean inicializado;
    private int avanzar;
    public Solucion(ArrayList<Integer> listaOperaciones) {
        this.listaOperaciones = listaOperaciones;
        this.numMovimientos = listaOperaciones.size();
        inicializado = false;
        this.avanzar=0;
    }

    public Solucion() {
        this.listaOperaciones = new ArrayList<Integer>();
    }

    public ArrayList<Integer> getListaOperaciones() {
        return listaOperaciones;
    }

    public void setListaOperaciones(ArrayList<Integer> listaOperaciones) {
        this.listaOperaciones = listaOperaciones;
    }

    public int getNumMovimientos() {
        return numMovimientos;
    }

    public void setNumMovimientos(int numMovimientos) {
        this.numMovimientos = numMovimientos;
        inicializado = true;
    }

    public boolean isInicializado() {
        return inicializado;
    }

    public int getAvanzar() {
        return avanzar;
    }

    public void incrementarAvanzar() {
        this.avanzar++;
    }
    public void resetearAvanzar(){
        this.avanzar=0;
    }

    public void setInicializado(boolean inicializado) {
        this.inicializado = inicializado;
    }
}
