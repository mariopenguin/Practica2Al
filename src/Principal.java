import javafx.animation.ScaleTransition;

import java.util.ArrayList;

public class Principal {
    public static ArrayList<Integer> vector = new ArrayList<Integer>();


    public static int numMinMutaciones(String cadena1, String cadena2) {
        algoritmoAux1(cadena1,cadena2, 0, 0, false);
        int mejor = Integer.MAX_VALUE;
        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i) <= mejor) {
                mejor = vector.get(i);
            }
        }
        return mejor;
    }

    /*
        void algoritmoSeleccionOptima(Objeto[] candidatos, int pesoTotal, int pesoActual,
                                      ArrayList<Objeto> solucion, Booleano encontrado,
                                      int objActual, ArrayList<Objeto> solucionMejor){
            while (objActual<candidatos.length){
                if ((pesoTotal-pesoActual) >= candidatos[objActual].getPeso()){
                    solucion.add(candidatos[objActual])
                    pesoActual = pesoActual + candidatos[objActual].getPeso();
                    if (pesoActual == pesoTotal) {
                        if (!encontrado.getValor()) {
                            encontrado.setValor(true);
                            copiar(solucion, solucionMejor);
                        }
                        else if (mejor(solucion, solucionMejor)){
                            copiar(solucion, solucionMejor);
                        }
                    } else algoritmoSeleccionOptima(candidatos, pesoTotal, pesoActual, solucion, encontrado, objActual+1, solucionMejor);
                    solucion.remove(solucion.size()-1);
                    pesoActual = pesoActual - candidatos[objActual].getPeso());
                }
                objActual++;
            }
        }*/
    public static void algoritmoAux(String cadena1, String cadena2, boolean exito, int movimientos, int posicion) {
        int operador=0;
        String auxiliar = "";
        do {

            if (aceptable(operador, cadena1, cadena2, posicion)) {

                if (cadena1.length() > posicion && cadena2.length() > posicion && cadena1.charAt(posicion) == cadena2.charAt(posicion)) {
                    algoritmoAux(cadena1, cadena2, exito, movimientos, posicion + 1);
                } else {
                    auxiliar = operar(operador, cadena1, cadena2, posicion);
                    movimientos++;
                    if (auxiliar.equals(cadena2)) {
                        exito = true;
                        vector.add(movimientos);
                        //System.out.println("He terminado! "+auxiliar +"La mejor solucion es: ");
                        // System.out.println("De "+cadena2+" a "+ auxiliar+" con tantos movimientos: "+movimientos);

                    } else {
                        algoritmoAux(auxiliar, cadena2, exito, movimientos, posicion + 1);
                    }
                }
            }
        } while (operador < 3);

    }


    public static String operar(int operador, String cadena1, String cadena2, int posicion) {
        StringBuilder resultado = new StringBuilder(cadena1);
        if(posicion==0){
            System.out.println("Hacemos: "+operador+" sobre la posicion "+posicion+" con las cadenas: "+cadena1 +" para que de "+ cadena2 );
        }
        if (operador == 0) {
            resultado.setCharAt(posicion, cadena2.charAt(posicion));
        } else if (operador == 1) {
            if(posicion>cadena1.length()){
                resultado.append(cadena2.charAt(posicion));
            }else{
            resultado.insert(posicion,cadena2.charAt(posicion));}
        } else {
            resultado.deleteCharAt(posicion);
        }
        if(posicion==0){
            System.out.println("Resultado "+resultado);
        }
        return resultado.toString();
    }

    public static boolean aceptable(int operador, String cadena, String cadena2, int posicion) {
        if (operador == 0 && cadena.length() > posicion && cadena2.length() > posicion) {
            return true;
        } else if (operador == 1 && cadena.length() < cadena2.length() && cadena2.length() > posicion) {
            return true;
        } else if (operador == 2 && cadena.length() > posicion) {
            return true;
        } else {
            return false;
        }
    }


    public static void algoritmoAux1(String s1, String s2, int posicion, int numMovimientos, boolean exito) {
        String aux = "";
        int operacion = 0;
        do {
            if (aceptable(operacion, s1, s2, posicion)) {

                if (s1.equals(s2)){
                    exito=true;
                    vector.add(numMovimientos);
                }else{
                    aux= operar(operacion,s1,s2,posicion);
                    algoritmoAux1(aux,s2,posicion+1,numMovimientos,exito);
                    numMovimientos = numMovimientos+1;
                }

            }operacion++;
        }
            while (operacion < 3) ;
    }

    public static void main(String[] args) {

        StringBuilder resultado = new StringBuilder("abcd");
        resultado.insert(2,"abc".charAt(2));

        //System.out.println(numMinMutaciones("bb","abb"));
        //System.out.println(numMinMutaciones("b","bb"));
        System.out.println(numMinMutaciones("a","bbae"));
        //System.out.println(numMinMutaciones("aa","bb"));
    }
}