
public class Principal {
    private static Solucion solucion = new Solucion();

    /*Establecemos la llamada al algoritmo de backtracking para resolver el problema; para optimizar dicho algoritmo, le pasamos como parametro el maximo de operaciones
    * que puede realizar y una clase auxiliar solucion.
    *  */
    public static int numMinMutaciones(String cadena1, String cadena2) {
        solucion.setNumMovimientos(Integer.MAX_VALUE);
        if (cadena1.length()>cadena2.length()){
            algoritmoaux1(cadena1,cadena2, 0, 0, new Booleano(false),solucion,new Entero(cadena1.length()));
        }
        else{
            algoritmoaux1(cadena1,cadena2, 0, 0, new Booleano(false),solucion,new Entero(cadena2.length()));
        }
        int aux = solucion.getNumMovimientos();
        solucion = new Solucion();
        return aux;
    }

    private static void algoritmoaux1(String s1, String s2, int posicion, int numMovimientos, Booleano exito, Solucion solucion, Entero maxMov) {
        String aux = "";
        int operacion = 0;
        do {
            if (s1.length()==s2.length()&& posicion== s2.length() && s1.charAt(posicion-1)==s2.charAt(posicion-1)){
                exito.setExito(true);
                if (solucion.isInicializado() && numMovimientos<solucion.getNumMovimientos()){
                    solucion.setNumMovimientos(numMovimientos);
                }
                else if(!solucion.isInicializado()){
                    solucion.setNumMovimientos(numMovimientos);
                    solucion.setInicializado(true);
                }
            }
            else if (aceptable(operacion, s1, s2, posicion) && numMovimientos <= solucion.getNumMovimientos() && numMovimientos<=maxMov.getEntero()) {
                aux= operar(operacion,s1,s2,posicion);
                numMovimientos = numMovimientos+1;
                if (operacion==1 || operacion ==2){
                    algoritmoaux1(aux,s2,posicion,numMovimientos,exito,solucion,maxMov);//Desanotar
                }else if(operacion == 0){
                    algoritmoaux1(aux,s2,posicion+1,numMovimientos,exito,solucion,maxMov);//Desanotar
                }
                else{
                    numMovimientos = numMovimientos-1;
                    int aux1 = solucion.getAvanzar();
                    solucion.resetearAvanzar();
                    algoritmoaux1(aux,s2,posicion+aux1,numMovimientos,exito,solucion,maxMov);//Desanotar
                }
                if (operacion!=3) numMovimientos--;
            }operacion++;
        }
        while (operacion <= 3);
    }
    /*Este metodo se encarga de realizar las operaciones sobre la string destino en funcion del codigo de operacion y la posicion sobre la que se desea
    * realizar dicho cambio */
 
    private static String operar(int operador, String cadena1, String cadena2, int posicion) {
        StringBuilder resultado = new StringBuilder(cadena1);
        if (operador == 0) {
            resultado.setCharAt(posicion, cadena2.charAt(posicion));
        } else if (operador == 1 ) {
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
        }
        return resultado.toString();
    }
    /*Metodo cuya finalidad es conocer  si se puede llevar a cabo una operacion, todas aquellas operaciones 
    no permitidas devuelven un false y no se realizan*/
    
    private static boolean aceptable(int operador, String cadena, String cadena2, int posicion) {
        if (operador == 0 && cadena.length() > posicion && cadena2.length() > posicion && cadena.charAt(posicion)!= cadena2.charAt(posicion)) {
            return true;
        } else if (operador == 1  && cadena2.length() > posicion ) {
            return true;
        } else if (operador == 2 && cadena.length() > posicion) {
            return true;
        }else if(operador==3 && cadena.length()>posicion && cadena2.length()>posicion && cadena.charAt(posicion)==cadena2.charAt(posicion)){
            return true;
        }
        else {
            return false;
        }
    }

    /*Algoritmo de backtracking principal */
   /* private static void algoritmoaux1(String s1, String s2, int posicion, int numMovimientos, boolean exito, Solucion solucion, int maxMov) {
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
                    algoritmoaux1(aux,s2,posicion,numMovimientos,exito,solucion,maxMov);//Desanotar
                }else if(operacion == 0){
                    algoritmoaux1(aux,s2,posicion+1,numMovimientos,exito,solucion,maxMov);//Desanotar
                }
                else{
                    numMovimientos = numMovimientos-1;
                    int aux1 = solucion.getAvanzar();
                    solucion.resetearAvanzar();
                    algoritmoaux1(aux,s2,posicion+aux1,numMovimientos,exito,solucion,maxMov);//Desanotar
                }
                if (operacion!=3) numMovimientos--;
            }operacion++;
        }
            while (operacion <= 3);
    }
*/

}

