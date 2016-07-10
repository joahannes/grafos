import java.io.*;
import java.util.*;
public class MainAutomatico {
  
  /*
  static BufferedReader in = new BufferedReader (
                             new InputStreamReader (System.in));
  public static Grafo.Aresta lerAresta () throws Exception {
    System.out.println ("Aresta: ");
    System.out.print (" V1: ");
    int v1 = Integer.parseInt (in.readLine());
    System.out.print (" V2: ");
    int v2 = Integer.parseInt (in.readLine());
    System.out.print (" Peso: ");
    int peso = Integer.parseInt (in.readLine());
    return new Grafo.Aresta (v1, v2, peso);
  }
  public static Grafo.Aresta lerAresta (int u, int v, int p) {
    int v1 = u, v2 = v, peso = p;
    return new Grafo.Aresta (v1, v2, peso);
  }
  */
  public static void main (String[] args) throws Exception {
    //Para contagem do tempo de execucao
    int nVertices = 100;
    int nArestas = ((nVertices*(nVertices - 1))/2);
    int raiz = 0;

    //Complexidade do Vetor = V^2 + E
    
    System.out.println (" Numero de vertices: " + nVertices); 
    System.out.println (" Numero de arestas: " + nArestas); 
    System.out.println (" Raiz: " + raiz);
    
    Grafo grafo = new Grafo (nVertices);
    int v1, v2, peso;

    for (int i=0; i < nArestas; i++){
            Random n = new Random();
            v1 = n.nextInt(nVertices);
            v2 = n.nextInt(nVertices);
            peso = n.nextInt(10);
            grafo.insereAresta(v1, v2, peso);
    }
     
    System.out.print("\n");
    grafo.imprime ();

    //Chama Dijkstra
    Dijkstra dj = new Dijkstra (grafo);

    //Tempo para Vetor
    double temp_inicio_vetor = System.currentTimeMillis();
    dj.obterArvoreVetor (raiz);
    double temp_final_vetor = System.currentTimeMillis();
    
    //Tempo para Heap de Fibonacci
    double temp_inicio_fibo = System.currentTimeMillis();
    dj.obterArvoreCMC (raiz);
    double temp_final_fibo = System.currentTimeMillis();

    //Tempo para Heap Binário
    double temp_inicio_binario = System.currentTimeMillis();
    dj.obterArvoreBinario (raiz);
    double temp_final_binario = System.currentTimeMillis();

    double tempoTotal_vetor = temp_final_vetor - temp_inicio_vetor;
    double tempoTotal_fibo = temp_final_fibo - temp_inicio_fibo;
    double tempoTotal_binario = temp_final_binario - temp_inicio_binario;

    //Imprime o caminho da raiz (0) para cada vértice
    System.out.print("\n");
    for (int i = 0; i < nVertices; i++) {
      System.out.print(" Caminho entre " + raiz + " e " + i + ": ");
      dj.imprimeCaminho (raiz, i);
      System.out.print("\n");
     }

    System.out.print("\n");
    System.out.println("Tempo de execucao VETOR: " + tempoTotal_vetor + "ms.");
    System.out.print("\n");
    System.out.println("Tempo de execucao FIBONACCI: " + tempoTotal_fibo + "ms.");
    System.out.print("\n");
    System.out.println("Tempo de execucao BINARIO: " + tempoTotal_binario + "ms.");
    System.out.print("\n");

  }
  
}