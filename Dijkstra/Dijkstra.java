import java.util.*;
import java.io.*;
public class Dijkstra {
  private int antecessor[];
  private double p[];
  private Grafo grafo;

  public Dijkstra (Grafo grafo) { this.grafo = grafo; }  
  	
  //Implementação usando Heap_Fibonacci para fila de prioridades
  public void obterArvoreCMC (int raiz) throws Exception {
    int n = this.grafo.numVertices();
    this.p = new double[n]; // peso dos vertices
    int vs[] = new int[n+1]; // vertices
    this.antecessor = new int[n];
    for (int u = 0; u < n; u ++) {
      this.antecessor[u] = -1;
      p[u] = Double.MAX_VALUE; // infinito
      vs[u+1] = u; // Heap indireto a ser construido
    }
    p[raiz] = 0;
    
    FPHeapMinIndireto heap = new FPHeapMinIndireto (p, vs); 
    heap.constroi ();
    while (!heap.vazio ()) {
      int u = heap.retiraMin (); 
      if (!this.grafo.listaAdjVazia (u)) {
        Grafo.Aresta adj = grafo.primeiroListaAdj (u);
        while (adj != null) {
          int v = adj.v2 ();
          if (this.p[v] > (this.p[u] + adj.peso ())) {
            antecessor[v] = u; 
            heap.diminuiChave (v, this.p[u] + adj.peso ());
          }
          adj = grafo.proxAdj (u);
        }
      }
    }
  }

    //Implementação usando Vetor para fila de prioridades
    public void obterArvoreVetor(int raiz)throws Exception{
    int n = this.grafo.numVertices();
    this.p = new double[n]; // peso dos vertices
    int vs[] = new int[n+1]; // vertices
    this.antecessor = new int[n];
    List<Integer> lista = new ArrayList<>(); // fila de prioridade
    for (int u = 0; u < n; u ++) {
      this.antecessor[u] = -1;
      p[u] = Double.MAX_VALUE; // infinito
      vs[u+1] = u; //
      lista.add(u);
    }
    p[raiz] = 0; 
    
    while(!lista.isEmpty()){
        int u = retiraMin(lista);
        if (!this.grafo.listaAdjVazia (u)) {
        Grafo.Aresta adj = grafo.primeiroListaAdj (u);
        while (adj != null) {
          int v = adj.v2 ();
          if (this.p[v] > (this.p[u] + adj.peso ())) {
            antecessor[v] = u; 
            relaxar(v, this.p[u] + adj.peso ());
          }
          adj = grafo.proxAdj (u);
        }
      }
    } 
  }

    //Implementação usando Vetor para fila de prioridades
    public void obterArvoreBinario(int raiz)throws Exception{
    int n = this.grafo.numVertices();
    this.p = new double[n]; // peso dos vertices
    int vs[] = new int[n+1]; // vertices
    this.antecessor = new int[n];
    NavigableSet<Integer> lista = new TreeSet<>(); // fila de prioridade
    for (int u = 0; u < n; u ++) {
      this.antecessor[u] = -1;
      p[u] = Double.MAX_VALUE; // infinito
      vs[u+1] = u; //
      lista.add(u);
    }
    p[raiz] = 0; 
    
    while(!lista.isEmpty()){
        int u = lista.pollFirst();
        if (!this.grafo.listaAdjVazia (u)) {
        Grafo.Aresta adj = grafo.primeiroListaAdj (u);
        while (adj != null) {
          int v = adj.v2 ();
          double alternateDist = this.p[u] + adj.peso();
          if (alternateDist < this.p[v]){
            lista.remove(v);
            this.p[v] = alternateDist;
            antecessor[v] = u;
            lista.add(v);
            //relaxar(v, this.p[u] + adj.peso ());
          }
          adj = grafo.proxAdj (u);
        }
      }
    } 
  }

  private void relaxar(int v, double novocusto){
  	this.p[v] = novocusto;
  }
  
  private int retiraMin(List l){
      int min=0;
      for(int i=1;i<l.size();i++){
          if(this.p[(int)l.get(min)]>this.p[(int)l.get(i)])
              min = i;
      }
      int m = (int)l.get(min);
      l.remove(min);
      return m;
  }

  public int antecessor (int u) { return this.antecessor[u]; }
  
  public double peso (int u) { return this.p[u]; }
  
  public void imprime () {
    System.out.print("\n");
    for (int u = 0; u < this.p.length; u++)
      if (this.antecessor[u] != -1) 
        System.out.println (" (" +antecessor[u]+ "," +u+ ") - peso: " +peso (u));

  }
  public void imprimeCaminho (int origem, int v) {
    if (origem == v){
      System.out.print ("[" + origem + "]");
     }
    else if (this.antecessor[v] == -1){
      System.out.print ("Nao existe!");
    }
    else {
      imprimeCaminho (origem, this.antecessor[v]);
      System.out.print ("[" + v + "]");
    }  
  }

}