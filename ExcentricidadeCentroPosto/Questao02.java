import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Questao02 {

	static int INFINITO = 99999;
	static int distancias[];
	static int excent[];
	static int centro[];
	
	/**
	 * Metodo que utiliza a busca em largura para calcular a distancia de um 
	 * vertice v para todos os outros vertices no grafo e armazena as distancias
	 * encontradas no vetor 'distancias', ou seja, a distancia do vertice vi
	 * para o vertice vj e igual a distancias[j].
	 * @param g
	 * @param v
	 */
	public static void bfs(Grafo g, Vertice v) {
		distancias = new int[g.getNumeroVertices()];
		for (int i = 0; i < g.getNumeroVertices(); i++){
			distancias[i] = INFINITO;
			//System.out.println("BRANCO " + i);
		}
		Queue<Vertice> fila = new LinkedList<>();
		distancias[v.getChave()] = 0; //0 para o nó visitado
		fila.add(v);
		//System.out.println("VERTICE RAIZ " + v);
		while(!fila.isEmpty()) {
			Vertice x = fila.remove();
			for (int k = 0; k < g.getNumeroVertices(); k++) {
				if (g.existeAresta(x.getChave(), k) && distancias[k] == INFINITO) {
					distancias[k] = distancias[x.getChave()] + 1;
					//System.out.println("\nCINZA " + distancias[k]);
					fila.add(g.getVertices().get(k));
					//System.out.println("PRETO " + g.getVertices().get(k));
				}
			}
		}
	}
	
	/**
	 * Metodo usado para calcular a distancia entre dois vertices no grafo.
	 * @param g
	 * @param i
	 * @param j
	 */
	public static int buscaUm(Grafo g, int i, int j) {
		bfs(g, g.getVertices().get(i));
		if (distancias[j] == INFINITO)
			System.out.println("  Nao existe um caminho entre o vertice (" + i + ") e o vertice (" + j + ")");
		else
			System.out.println(" A distancia entre v(" + i + ") e v(" + j + ") e': " + distancias[j]);

		return distancias[j];
	}

	public static int busca(Grafo g, int i, int j) {
		bfs(g, g.getVertices().get(i));
	
		System.out.println("Distancia de " + i + " para " + j + ": " + distancias[j]);

		return distancias[j];
	}

	
	public static int maiorVetor(int distancias[]){
		int maior = 0;
		for (int i = 0; i < distancias.length; i++) {
			if(distancias[i] > maior){
				maior = distancias[i];
			}
		}
		return maior;
	}

	public static int menorVetor(int excent[]){
		int menor = INFINITO;
		for (int i = 0; i < excent.length; i++) {
			if(excent[i] < menor){
				menor = excent[i];
			}
		}
		return menor;
	}
	
	
	/**
	 * Classe que implementa o Grafo 'G'
	 *
	 */
	public static class Grafo {
		
		List<Vertice> vertices;
		int numeroVertices;
		
		public Grafo(int numeroVertices) {
			this.numeroVertices = numeroVertices;
			this.vertices = new ArrayList<Vertice>(0);
			for (int i = 0; i < numeroVertices; i++)
				this.vertices.add(new Vertice(i));
		}
		
		public void insereAresta(int i, int j) {
			Vertice v1 = this.vertices.get(i);
			Vertice v2 = this.vertices.get(j);
			v1.listaAdj.add(v2); //Duas chamadas para grafo nao direcionado
			v2.listaAdj.add(v1);
		}
		
		public boolean existeAresta(int i, int j) {
			return this.vertices.get(i).existeAresta(j);
		}
		
		public int getNumeroVertices() {
			return this.numeroVertices;
		}
		
		public List<Vertice> getVertices() {
			return this.vertices;
		}
	}
	
	/**
	 * Classe que implementa um vertice do grafo.
	 *
	 */
	public static class Vertice {
		int marca;
		int chave;
		List<Vertice> listaAdj;
		
		public Vertice(int chave) {
			this.chave = chave;
			this.listaAdj = new ArrayList<Vertice>();
		}
		
		public boolean existeAresta(int j) {
			for (Vertice v : listaAdj)
				if (v.chave == j)
					return true;
			return false;
		}
		
		@Override
		public String toString() {
			return chave + "";
		}
		
		public int getChave() {
			return this.chave;
		}
		
		public List<Vertice> getListaAdj() {
			return this.listaAdj;
		}
	}
	
	public static void main(String[] args) {

		//Criando o Grafo 'G'
		/*
		Scanner scan = new Scanner(System.in);
		System.out.print("Quantidade de vertices: ");
		int nVertices = scan.nextInt();

		Grafo grafo = new Grafo(nVertices);

		System.out.print("Quantidade de arestas: ");
		int nArestas = scan.nextInt();

		for (int i = 0; i < nArestas; i++) {
			System.out.println(" -- Aresta " + (i+1) + " --");
			//Vertice 1
			System.out.print(" Vertice 1: ");
			int v1 = scan.nextInt();
			//Vertice 2
			System.out.print(" Vertice 2: ");
			int v2 = scan.nextInt();

			grafo.insereAresta(v1, v2);
			System.out.print("\n");
			
		}

		*/
		int nVertices = 7;
		int nArestas = 8;

		Grafo grafo = new Grafo(nVertices);
		excent = new int[grafo.getNumeroVertices()]; //Vetor para tabela de excentricidade
			
		grafo.insereAresta(0, 2);
		grafo.insereAresta(2, 3);
		grafo.insereAresta(3, 1);		
		grafo.insereAresta(1, 0);
		grafo.insereAresta(2, 6);
		grafo.insereAresta(4, 2);
		grafo.insereAresta(3, 4);
		grafo.insereAresta(4, 5);

		//EXCENTRICIDADE
		for (int i = 0; i < nVertices; i++) {
			for (int j = 0; j < nVertices; j++) {
				int vet1 = i;
				int vet2 = j;
				busca(grafo, vet1, vet2);
				
			}
			System.out.print("\n");
			excent[i] = maiorVetor(distancias);
		}
		for (int t = 0; t < nVertices; t++) {
			System.out.println(" Excentricidade de " + t + ": " + excent[t]);
		}

		//CENTRO
		int cont = 0;
		int menor = menorVetor(excent);
		for (int x = 0; x < excent.length; x++) {
			if(menor == excent[x]){
				cont++;
			}
		}
		centro = new int[cont]; //Tamanho do Subconjunto CENTRO igual ao número de menores de Excent[]
		System.out.print("\n Centro: [");
		for (int j = 0; j < cont; j++) {
			centro[j] = menor;
			System.out.print(" " + centro[j]);
		}
		System.out.print(" ]");

		//POSTO
		int posto = nArestas - nVertices + 1;
		System.out.print("\n\n Posto: " + posto);
		System.out.println("\n");
		

	}
	
}
