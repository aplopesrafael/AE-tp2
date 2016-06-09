/*
 TP 2
Un paradigma en la solución de problemas combinatorios es el problema del Cajero Viajante, más conocido como TSP (Traveling Salesman Problem), como puede verse en Wikipedia:
http://es.wikipedia.org/wiki/Problema_del_viajante

Este trabajo práctico consiste en resolver el TSP utilizando la heurística de Colonia de Hormigas o ACO (Ant Colony Optimization), estudiado en clase.

Los problemas tipos a ser resueltos pueden ser bajados del TSPLIB, disponible en:
http://www.iwr.uni-heidelberg.de/groups/comopt/software/TSPLIB95/tsp/
En particular, se verificará si el programa funciona utilizando las 2 instancias del TSP que se citan a continuación:
1. Burma14
2. Berlin 52

Ejemplo del archivo Berlin 52 (archivo: berlin52.tsp.gz)
NAME: berlin52
TYPE: TSP
COMMENT: 52 locations in Berlin (Groetschel)
DIMENSION: 52
EDGE_WEIGHT_TYPE: EUC_2D
NODE_COORD_SECTION
1 565.0 575.0
2 25.0 185.0
3 345.0 750.0
:
51 1340.0 725.0
52 1740.0 245.0
EOF

NOTA: eventualmente, se podrá verificar si el algoritmo funciona con la instancia: pcb1173

En el onstructor de la clase Circuito se configura el problema, sea burma14.tsp 
o berlin52.tsp

 archivo.openFile("burma14.tsp",7); //0..7
 archivo.openFile("berlin52.tsp",5); //PARA BERLIN 52 CIUDADES

 */
package ColoniaHormigasTSP;

/**
 *
 * @author usuario
 */
public class ColoniaHormigasTSP {
    
    //------ INICIO CONFIGURACION DEL ALGORITMO -----
    public static int CANT_GENERACIONES=500;
    public static int CANT_HORMIGAS=100;
    public static Double TASA_EVAPORACION=0.01;
    public static int ALFA=1;    
    public static int BETA=1;        
    //alfa, beta=1-alfa
    //delta tau=1
    
    //------ FIN CONFIGURACION DEL ALGORITMO -----    
    
    //--- INICIALIZACION DE FEROMONAS ---
    public static Circuito circuito; //=new Circuito();
    public static Feromonas feromonas; //=new Feromonas(circuito.getNumeroNodos(),circuito.getNumeroNodos());    
    public static Visibilidad visibilidad;
    //-------------------------------
    
    
    
    
    public static void main(String[] args) {

    //--- CONFIGURACION DE PARAMETROS -----------------------------------------
    circuito=new Circuito();   
    feromonas=new Feromonas(circuito.getNumeroNodos(),circuito.getNumeroNodos());
    visibilidad=new Visibilidad(circuito.matrizDistanciasCiudades);   

    circuito.mostrarCiudadesLista();
    circuito.mostrarMatrizDistancias();

    //--------------------------------------
    int [] recorrido=new int [circuito.CANTIDADCIUDADES];
    Double longitud=0.0;
    //------se guardan las mejores soluciones
    int [] mejorRecorrido=new int [circuito.CANTIDADCIUDADES];
    Double mejorLongitud=0.0;
    int mejorGeneracion=0;
    int mejorHormiga=0;
    

        //programa principal
        for(int generacion=0;generacion<CANT_GENERACIONES;generacion++){
            for(int hormigaK=0;hormigaK<CANT_HORMIGAS;hormigaK++){

                //---------- LAMADO A LA HORMIGAS
                Hormiga hormiga=new Hormiga(circuito,feromonas,visibilidad);
                //CONTRUIR SOLUCION
                recorrido=hormiga.construirSolucion();
                //obtener longitud
                longitud=circuito.getLongitudRecorrido(recorrido);

            System.out.printf("Generacion=%3d Hormiga=%3d R-> Longitud=%5.14f -->",generacion,hormigaK,longitud);
            for(int i=0; i<recorrido.length;i++){
                System.out.printf(" %3d",recorrido[i]);                
            }
            System.out.println();

                //ACTUALIZAR FEROMONAS
                feromonas.actualizarFeromonasCamino(recorrido, longitud);
//                feromonas.mostrarFeromonas();
                //EVAPORAR FEROMONAS
                feromonas.evaporarFeromonas(TASA_EVAPORACION);
                //----------------------------------------------------------
                //EVALUAR SOLUCION
                //----------------------------------------------------------.
                if(generacion==0 && hormigaK==0){
                    mejorLongitud=longitud;
                    mejorRecorrido=recorrido;
                    mejorGeneracion=0;
                    mejorHormiga=0;
                }                
                if(longitud<mejorLongitud){
                    mejorLongitud=longitud;
                    mejorRecorrido=recorrido;
                    mejorGeneracion=generacion;
                    mejorHormiga=hormigaK;                    
                }
                //----------------------------------------------------------                
                //----------- FIN LLAMADO HORMIGAS
                
            }//fin hormigasK

            System.out.println(" ");                
            System.out.println("------------------------");    
            System.out.println("RESULTADOS");     
            System.out.println("------------------------");                 
            
            System.out.println("MEJOR TOUR HASTA EL MOMENTO --> Logitud="+mejorLongitud);            
            
            for(int i=0; i<mejorRecorrido.length;i++){
                System.out.print(mejorRecorrido[i]+" ");                
            }
            System.out.println();
        }//fin for principal

            System.out.println("TOUR FINAL --> Logitud="+mejorLongitud+" generacion="+mejorGeneracion+" hormiga="+mejorHormiga);            
            
            for(int i=0; i<mejorRecorrido.length;i++){
                System.out.print(mejorRecorrido[i]+" ");                
            }
            System.out.println(" ");

            //---IMPRESION DE PARAMETROS----------------------------------------
            System.out.println("------------------------");    
            System.out.println("PARAMETROS DEL ALGORITMO");     
            System.out.println("------------------------");     
            System.out.println("CANT_GENERACIONES="+CANT_GENERACIONES); 
            System.out.println("CANT_HORMIGAS="+CANT_HORMIGAS);
            System.out.println("TASA_EVAPORACION="+TASA_EVAPORACION);
            System.out.println("CANT_HORMIGAS="+CANT_HORMIGAS);
            System.out.println("ALFA="+ALFA);
            System.out.println("BETA="+BETA);
            //-------------------------------------------  

    }//main
    
}//fin de clase
