import java.io.*;
public class MainManual {
  
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
  	double tempoInicio = 0, tempoFim = 0, tempoTotal = 0;

    int nVertices = 5;
    int nArestas = 7;
    int raiz = 0;

    System.out.println ("\n Numero de vertices: " + nVertices); 
    System.out.println (" Numero de arestas: " + nArestas); 
    System.out.println (" Raiz: " + raiz);
    
    Grafo grafo = new Grafo (nVertices);
    //insere aresta entre V1 e V2 mais o PESO dela -> (V1, V2, PESO);
    grafo.insereAresta(0, 1, 1);
    grafo.insereAresta(0, 3, 3);
    grafo.insereAresta(0, 4, 10);
    grafo.insereAresta(1, 2, 5);
    grafo.insereAresta(2, 4, 1);
    grafo.insereAresta(3, 2, 2);
    grafo.insereAresta(3, 4, 6);

    /*
    //Insercao manual via Terminal
    System.out.print (" Numero de vertices: "); 
    int nVertices = Integer.parseInt (in.readLine());
    System.out.print (" Numero de arestas: "); 
    int nArestas = Integer.parseInt (in.readLine());
    System.out.print (" Raiz: ");
    int raiz = Integer.parseInt (in.readLine());
    //Chama a leitura das arestas
    Grafo grafo = new Grafo (nVertices);
    for (int i = 0; i < nArestas; i++) {
      Grafo.Aresta a = lerAresta ();
      //Duas chamadas porque o grafo 'e nao direcionado
      grafo.insereAresta (a.v1 (), a.v2 (), a.peso ());     
      //grafo.insereAresta (a.v2 (), a.v1 (), a.peso ());     
    }
    */
    
    System.out.print("\n");
    grafo.imprime ();

    //Inicia contagem do tempo de execucao
    tempoInicio = System.currentTimeMillis();

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

    System.out.print("\n Relaxamentos: ");
    dj.imprime ();

    //Finaliza contagem do tempo de execucao
    tempoFim = System.currentTimeMillis();
    //Tempo de execucao final
    tempoTotal = tempoFim - tempoInicio;

    //Imprime o caminho da raiz (0) para cada vértice
    System.out.print("\n");
    for (int i = 0; i < nVertices; i++) {
      System.out.print(" Caminho entre " + raiz + " e " + i + ": ");
      dj.imprimeCaminho (raiz, i);
      System.out.print("\n");
     }

    System.out.print("\n");
    System.out.println(" Tempo de execucao VETOR: " + tempoTotal_vetor + "ms.");
    System.out.print("\n");
    System.out.println(" Tempo de execucao FIBONACCI: " + tempoTotal_fibo + "ms.");
    System.out.print("\n");
    System.out.println(" Tempo de execucao BINARIO: " + tempoTotal_binario + "ms.");
    System.out.print("\n");
  }
  
}
