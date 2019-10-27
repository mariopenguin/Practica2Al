
public class Principal {
    private static Solucion solucion = new Solucion();

    public static int numMinMutaciones(String cadena1, String cadena2) {
        if (cadena1.length()>cadena2.length()){
            algoritmoAux1(cadena1,cadena2, 0, 0, false,solucion,cadena1.length());
        }else if(cadena2.length()>30){
            algoritmoAux1(cadena1,cadena2, 0, 0, false,solucion,16);
        }
        else{
            algoritmoAux1(cadena1,cadena2, 0, 0, false,solucion,cadena2.length());
        }
        int aux = solucion.getNumMovimientos();
        solucion = new Solucion();
        return aux;
    }

    private static String operar(int operador, String cadena1, String cadena2, int posicion) {
        StringBuilder resultado = new StringBuilder(cadena1);
        if (operador == 0) {
            resultado.setCharAt(posicion, cadena2.charAt(posicion));
        } else if (operador == 1 && cadena1.length()<cadena2.length()) {
            if(posicion>cadena1.length()){
                resultado.append(cadena2.charAt(posicion));
            }else{
            resultado.insert(posicion,cadena2.charAt(posicion));}
        } else if (operador==2 ){
            resultado.deleteCharAt(posicion);
        }else if (operador==3){
            while(posicion<cadena1.length() && posicion< cadena2.length() &&cadena1.charAt(posicion) == cadena2.charAt(posicion)){
                //avanzar++;
                solucion.incrementarAvanzar();
                posicion++;
            }

        }else{

        }
        return resultado.toString();
    }

    private static boolean aceptable(int operador, String cadena, String cadena2, int posicion) {
        if (operador == 0 && cadena.length() > posicion && cadena2.length() > posicion && cadena.charAt(posicion)!= cadena2.charAt(posicion)) {
            return true;
        } else if (operador == 1 && cadena.length() < cadena2.length() && cadena2.length() > posicion) {
            return true;
        } else if (operador == 2 && cadena.length() > posicion && cadena.length()>cadena2.length()) {
            return true;
        }else if(operador==3 && cadena.length()>posicion && cadena2.length()>posicion && cadena.charAt(posicion)==cadena2.charAt(posicion)){
            return true;
        }
        else {
            return false;
        }
    }


    private static void algoritmoAux1(String s1, String s2, int posicion, int numMovimientos, boolean exito,Solucion solucion,int maxMov) {
        String aux = "";
        int operacion = 0;
        do {
            if (s1.length()==s2.length()&& posicion== s2.length() && s1.charAt(posicion-1)==s2.charAt(posicion-1)){
                exito=true;
                if (solucion.isInicializado() && numMovimientos<solucion.getNumMovimientos()){
                    solucion.setNumMovimientos(numMovimientos);
                }
                else if(!solucion.isInicializado()){
                    solucion.setNumMovimientos(numMovimientos);
                    solucion.setInicializado(true);
                }
            }
            else if (aceptable(operacion, s1, s2, posicion) && !exito && numMovimientos<=maxMov) {
                aux= operar(operacion,s1,s2,posicion);
                numMovimientos = numMovimientos+1;
                if (operacion==1 || operacion ==2){
                    algoritmoAux1(aux,s2,posicion,numMovimientos,exito,solucion,maxMov);//Desanotar
                }else if(operacion == 0){
                    algoritmoAux1(aux,s2,posicion+1,numMovimientos,exito,solucion,maxMov);//Desanotar
                }
                else{
                    numMovimientos = numMovimientos-1;
                    int aux1 = solucion.getAvanzar();
                    solucion.resetearAvanzar();
                    algoritmoAux1(aux,s2,posicion+aux1,numMovimientos,exito,solucion,maxMov);//Desanotar
                }
                if (operacion!=3) numMovimientos--;
            }operacion++;
        }
            while (operacion <= 3);
    }

    /*public static void main(String[] args) {
        System.out.println(numMinMutaciones("ATCGATCGGGGGCCCCTTTTTTTAAAA"
                                           ,"ATCGATCGATCGATAGCTAGATCGGGGGCCCCTTTTTTTAAAA"));
        System.out.println(numMinMutaciones("AAAGGGGTTTTCGCTAGTCGATCGATCGATCGATAGCTAGATCGGGGGCCCCTTTTTTTAAAA","AGGTGGTTTTCGCTGGAGTCGACGATCGATAGCTAAGATCGGGGGCCCCCTATTTTTTAA"));
    }*/
}

