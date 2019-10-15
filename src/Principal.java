import javafx.animation.ScaleTransition;

import java.util.ArrayList;

public class Principal {




    public static int numMinMutaciones(String cadena1, String cadena2) {
        //SUTITUIR ESTA LINEA E IMPLEMENTAR
        return -1;
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
  public static void algoritmoAux(String cadena1, String cadena2, boolean exito, int mejorSolucion, ArrayList<Integer> solucion, int movimientos, int posicion){
        int operador; operador=0;
        String auxiliar ="";
        do {

             if(aceptable(operador,cadena1,cadena2,posicion)){
                solucion.add(operador);
                auxiliar = operar(operador,cadena1,cadena2,posicion);
                    if(auxiliar.equals(cadena2) && movimientos<mejorSolucion){
                        exito = true;
                        mejorSolucion = movimientos;
                        System.out.println("De "+cadena2+" a "+ auxiliar+" con tantos movimientos: "+movimientos);

                }else{

                    algoritmoAux(auxiliar,cadena2,exito,Integer.MAX_VALUE,solucion,movimientos+1,posicion+1);
                }
            }
        operador++;
        }while (operador<3 && !exito);

    }


    public static  String operar(int operador,String cadena1,String cadena2,int posicion){
        StringBuilder resultado = new StringBuilder(cadena1);
        if(operador==0){
            resultado.setCharAt(posicion,cadena2.charAt(posicion));
        }
        else if(operador==1){
            resultado.append(cadena2.charAt(posicion));
        }
        else{
            resultado.deleteCharAt(posicion);
        }
        return resultado.toString();
    }

    public static boolean aceptable(int operador,String cadena,String cadena2,int posicion){
        if(operador==0  && cadena.length()>posicion && cadena2.length()>posicion ){
            return true;
        }else if(operador == 1 && cadena.length()<cadena2.length() && cadena2.length()>posicion){
            return true;
        }else if(operador== 2 && cadena.length()>posicion){
            return true;
        }else{
            return false;
        }
    }


    public static void main(String[] args) {
     /*   System.out.println(operar(0,"aaa","bbb",0));
        System.out.println(operar(1,"aaa","bbb",0));
        System.out.println(operar(2,"aaa","bbb",0));
        System.out.println(operar(0,"aaa","bbb",1));
        System.out.println(operar(1,"aaa","bbb",1));
        System.out.println(operar(2,"aaa","bbb",1));*/
       ArrayList<Integer> vector = new ArrayList<Integer>();
        algoritmoAux("bba","aeaf",false,Integer.MAX_VALUE,vector,0,0);
        System.out.println("---------------------------------------------------------------------------------");
        algoritmoAux("bbb","aaaaa",false,Integer.MAX_VALUE,vector,0,0);
        System.out.println("---------------------------------------------------------------------------------");
        algoritmoAux("feas","aeaf",false,Integer.MAX_VALUE,vector,0,0);
        System.out.println("---------------------------------------------------------------------------------");
        algoritmoAux("ffffffffffffffffffffffffffffff","aeaf",false,Integer.MAX_VALUE,vector,0,0);

        System.out.println("---------------------------------------------------------------------------------");algoritmoAux("xxxxxxxxx","aeaf",false,0,vector,0,0);
        algoritmoAux("aeaf","aeaf",false,0,vector,0,0);
    }
}
