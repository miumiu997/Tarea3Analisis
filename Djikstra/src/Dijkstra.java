import java.util.ArrayList;
import java.util.List; 
import java.util.Collections;


/* TAREA #3 An‡liss de Algoritmos  
 * Profesor: Mauricio Rojas 
 * Estudiante: Miuyin Yong 
 * Carnet: 2014079293*/ 


//Programa en Java para el algoritmo de Dijkstra que encuentra
//el camino mas corto para un unico origen
//Se emplea una matrix de adjacencia para representar el grafo

public class Dijkstra {

 
// Numero de vertices en el grafo
static final int vertices  = 9;
 

// Funcion utilitaria para encontrar el vertice con la distancia minima, 
// a partir del conjunto de los vertices todavia no incluidos en el 
// camino mas corto 

private static int minDistance(int[] dist, boolean[] verticeYaProcesado)
{
   // Initialize min value (como infinito)
   int min = Integer.MAX_VALUE; 
   int min_index = 0;
 
   for (int v = 0; v < vertices; v++)
     if (verticeYaProcesado[v] == false && dist[v] <= min) {
         min = dist[v];
         min_index = v;
      }
   return min_index;
}
 
// Funcion utilitaria para imprimir el arreglo de distancias calculadas
private static void printSolution(int[] dist, int n)
{
   System.out.println("Distancia del vertice desde el origen\n");
   for (int i = 0; i < vertices; i++)
      System.out.println(i + " \t\t " + dist[i]);
}
 
private static void dijkstra(int[][] grafo, int src)
{
     int[] dist = new int[vertices];  
     // dist[i] guarda la distancia mas corta desde src hasta el vertice i
 
     List<Integer> myList = new ArrayList<Integer>(); 
     
     boolean[] verticeYaProcesado = new boolean[vertices]; 
     //Este arreglo tiene true si el vertice i ya fue procesado
 
     // Initialize all distances as INFINITE and stpSet[] as false
     for (int i = 0; i < vertices; i++) {
        dist[i] = Integer.MAX_VALUE;
        verticeYaProcesado[i] = false;  
     } 
     
     // La distancia del vertice origen hacia el mismo es siempre 0
     dist[src] = 0;
 
     //Encuentra el camino mas corto para todos los vertices
     for (int count = 0; count < vertices-1; count++)
     {

       //Toma el vertice con la distancia minima del cojunto de vertices aun no procesados
       //En la primera iteracion siempre se devuelve src
       int u = minDistance(dist, verticeYaProcesado); 
 
       //System.out.println(u); 
       
       // Se marca como ya procesado
       verticeYaProcesado[u] = true;   
       
     
 
       // Update dist value of the adjacent vertices of the picked vertex.
       for (int v = 0; v < vertices; v++){

         //Se actualiza la dist[v] solo si no esta en verticeYaProcesado, hay un
         //arco desde u a v y el peso total del camino desde src hasta v a traves de u es 
         // mas pequeno que el valor actual de dist[v]
         if (!verticeYaProcesado[v] && grafo[u][v] > 0 && dist[u] != Integer.MAX_VALUE && dist[u]+grafo[u][v] < dist[v]){
            dist[v] = dist[u] + grafo[u][v]; 
            myList.add(v);  //Agrega al la lista el nodo donde se quiere llegar
            myList.add(u);  //Agrega a la lista el nodo donde se pasa para llegar al nodo v
           // System.out.println(myList);
          
         }  
       	
       }  
       
       
     } 
     int valor = 8;  // El valor del ultimo nodo que seria el 8
     List<Integer> listaFinal = new ArrayList<Integer>(); // se crea una nueva lista donde se almacenaran el orden de los nodos del menor camino
     while(valor != 0){   // ciclo que recorre la lista myList desde el œltimo valor de 8 (en este caso) hasta llegar a 0
    	 int indice = myList.lastIndexOf(valor); // Busca el œltimo indice de 8 (en este caso)
         if (indice % 2 != 0){                   // Revisa que este en la posici—n del nodo v y no de u
    		 indice = myList.subList(0, indice).indexOf(valor);  // si esta en la posici—n u hay que buscar otro indice del valor
    	 }  
    	 
    	 listaFinal.add(valor);              // Se agrega el valor(nodo) a la lista
    	 valor = myList.get(indice + 1);    // Se modifica el nuevo valor que esta en una posici—n mayor de la lista
     }  
     listaFinal.add(0);                     // Se agrega 0 a la lista
     Collections.reverse(listaFinal);       // Se le da vuelta a la lista
     System.out.println("Tarea#3 ----- Orden de los nodos del menor Camino");  // Se imprime la lista
     System.out.println(listaFinal);
    
     // se imprime el arreglo con las distancias 
     printSolution(dist, vertices);
}
 
// driver program to test above function
public static void main(String[] args)
{
   /* Let us create the example graph discussed above */
   int[][] graph = {{0, 4, 0, 0, 0, 0, 0, 8, 0},
                    {4, 0, 8, 0, 0, 0, 0, 11, 0},
                    {0, 8, 0, 7, 0, 4, 0, 0, 2},
                    {0, 0, 7, 0, 9, 14, 0, 0, 0},
                    {0, 0, 0, 9, 0, 10, 0, 0, 0},
                    {0, 0, 4, 0, 10, 0, 2, 0, 0},
                    {0, 0, 0, 14, 0, 2, 0, 1, 6},
                    {8, 11, 0, 0, 0, 0, 1, 0, 7},
                    {0, 0, 2, 0, 0, 0, 6, 7, 0}
                     };
 
    dijkstra(graph, 0);
}
}