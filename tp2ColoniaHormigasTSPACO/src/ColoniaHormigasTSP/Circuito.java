/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColoniaHormigasTSP;

/**
 *
 * @author usuario
 */
public class Circuito {

//    public int CANTIDADCIUDADES=14; //se asume que representa la cantidad de ciudades

    
    //burma14
    public Double [][] lecturaDesdeArchivo;/*={
    {16.47,96.10},
    {16.47,94.44},
    {20.09,92.54},
    {22.39,93.37},
    {25.23,97.24},  
    {22.00,96.05},    
    {20.47,97.02},
    {17.20,96.29},
    {16.30,97.38},
    {14.05,98.12},
    {16.53,97.38},
    {21.52,95.59},
    {19.41,97.13},
    {20.09,94.55}
    };
*/
/*
//berlin    
public Double [][] lecturaDesdeArchivo={
{565.0, 575.0},
{25.0, 185.0},
{345.0, 750.0},
{945.0, 685.0},
{845.0, 655.0},
{880.0, 660.0},
{25.0, 230.0},
{525.0, 1000.0},
{580.0, 1175.0},
{650.0, 1130.0},
{1605.0, 620.0}, 
{1220.0, 580.0},
{1465.0, 200.0},
{1530.0, 5.0},
{845.0, 680.0},
{725.0, 370.0},
{145.0, 665.0},
{415.0, 635.0},
{510.0, 875.0},  
{560.0, 365.0},
{300.0, 465.0},
{520.0, 585.0},
{480.0, 415.0},
{835.0, 625.0},
{975.0, 580.0},
{1215.0, 245.0},
{1320.0, 315.0},
{1250.0, 400.0},
{660.0, 180.0},
{410.0, 250.0},
{420.0, 555.0},
{575.0, 665.0},
{1150.0, 1160.0},
{700.0, 580.0},
{685.0, 595.0},
{685.0, 610.0},
{770.0, 610.0},
{795.0, 645.0},
{720.0, 635.0},
{760.0, 650.0},
{475.0, 960.0},
{95.0, 260.0},
{875.0, 920.0},
{700.0, 500.0},
{555.0, 815.0},
{830.0, 485.0},
{1170.0, 65.0},
{830.0, 610.0},
{605.0, 625.0},
{595.0, 360.0},
{1340.0, 725.0},
{1740.0, 245.0}       
};
*/
    
    
    public int CANTIDADCIUDADES;//=lecturaDesdeArchivo.length; //se asume que representa la cantidad de ciudades
    //listado con los nodos para las ciudades
    public NodoCiudad [] listadoCiudades;//=new NodoCiudad[CANTIDADCIUDADES];
    
    // se utiliza para el calculo de las distancias entre ciudades
    public Double [][] matrizDistanciasCiudades;//=new Double[CANTIDADCIUDADES][CANTIDADCIUDADES];
    
    //archivo
    public ReadTextFile archivo = new ReadTextFile();
    
    
    public Circuito(){
        //----------------------------------   
        //AQUIII SE CONFIGURA EL PROBLEMA
        archivo.openFile("burma14.tsp",7); //0..7
        //archivo.openFile("berlin52.tsp",5);
        listadoCiudades=archivo.readRecords();
        archivo.closeFile();          
        //----------------------------------                
        //configurando las variables globales en el constructor
        CANTIDADCIUDADES=listadoCiudades.length;        
        matrizDistanciasCiudades=new Double[CANTIDADCIUDADES][CANTIDADCIUDADES];


        //cargaCiudadesLista();
        calcularMatrizDistanciasCiudades();
    }//

    //Falta un constructor para que lea del archivo.
    
    public void cargaCiudadesLista(){
        for(int i=0;i<CANTIDADCIUDADES;i++){
            listadoCiudades[i]=new NodoCiudad(" ",lecturaDesdeArchivo[i][0],lecturaDesdeArchivo[i][1]);
        }//fin for          
    }
    
    public void mostrarCiudadesLista(){
        System.out.println("LISTA DE CIUDADES");
        for(int fila=0;fila<CANTIDADCIUDADES;fila++){
            System.out.printf("%2d) %s \n",fila,listadoCiudades[fila].toString());
        }//fin for              
    }
    
    //calcula distancia entre dos puntos de acuerdo a sus coordenadas
    public Double calcularDistanciaEuclideana(Double x1, Double y1,Double x2, Double y2){
            Double distancia=0.0;
            distancia=Math.sqrt(Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2));
            return distancia;
    }
    
    //crea una matriz de distancias entre los puntos
    public void calcularMatrizDistanciasCiudades(){
        NodoCiudad ciudad1;
        NodoCiudad ciudad2;        
        for(int fila=0;fila<CANTIDADCIUDADES;fila++){
            //elije una ciudad indice
            ciudad1=new NodoCiudad();
            ciudad1=listadoCiudades[fila];
            //recorre
            for(int col=0;col<CANTIDADCIUDADES;col++){
               ciudad2=new NodoCiudad();
               ciudad2=listadoCiudades[col];
               //en la diagonal principal es cero     
               if(fila==col){
                   matrizDistanciasCiudades[fila][col]=0.0;
               }else{
                   matrizDistanciasCiudades[fila][col]=calcularDistanciaEuclideana(ciudad1.coordenadaX,ciudad1.coordenadaY,ciudad2.coordenadaX, ciudad2.coordenadaY);
               }//fin if fila==col
            }//fin for columnas
        }//fin for filas
    }
    
    //recorre y muestra la matriz de distancias
    public void mostrarMatrizDistancias(){
        System.out.println("MATRIZ DE DISTANCIAS");
        for(int fila=0;fila<CANTIDADCIUDADES;fila++){
            System.out.printf("%2d) ",fila);
            for(int col=0;col<CANTIDADCIUDADES;col++){
                System.out.printf("%.3f ",matrizDistanciasCiudades[fila][col]);
            }//fin for columnas
            System.out.println();
        }//fin for filas
    }
    
    //datos del grafo
    public int getNumeroNodos(){
        return CANTIDADCIUDADES+1;    
    }

    public int getNumeroArcos(){
        int aristas=(CANTIDADCIUDADES*(CANTIDADCIUDADES-1))/2;
        return aristas;
    }
    
    //Desde el punto 1 al punto 2 calcula el reocrrido    
    //asume un recorrido bien formado, eso implica que tiene que venir
    //con el tamano adecuado CANTIDAD DE NODOS + 1 por ser hamiltoniano
    public Double getLongitudRecorrido(int [] recorrido){
        int tope=recorrido.length-1;
        Double suma=0.0;
        
        int i=0;
        int j=i+1;
        int punto1,punto2;
        
        for(int contador=0;contador<tope;contador++){
            //obtiene del listado el valor del nodo
            punto1=recorrido[i];
            punto2=recorrido[j];
            //guarda la suma del ciclo
            suma=suma+matrizDistanciasCiudades[punto1][punto2];
            //avanza lectura
            i=j;
            j=i+1;
        }//for
        
        return suma;
    }//getLongitudRecorrido
    
    //la distancia entre dos puntos usando matriz de distancias
    public Double getLongitudDosPuntos(int punto1,int punto2){
        Double longitud=0.0;        
        if(punto1 < CANTIDADCIUDADES && punto2 < CANTIDADCIUDADES){
            longitud=matrizDistanciasCiudades[punto1][punto2];
        }        
        return longitud;
    }//getLongitudDosPuntos
    
    public NodoCiudad [] getVectorCiudades(){
        
        return listadoCiudades;
    }    
    
    //se usa para indices de matrices de distancias
    //joder tio, que tecnologia
    public int [] getVectorCiudadesEnteros(){
        int [] listadoCiudadesEnteros=new int [listadoCiudades.length];
        
        for(int i=0;i<listadoCiudades.length;i++){
            listadoCiudadesEnteros[i]=i;
        }
        return listadoCiudadesEnteros;
    }        
    
}//fin Circuito
