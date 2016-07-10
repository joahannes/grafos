import java.util.ArrayList;
import java.util.List;

public class Questao8 {

	public static int BRANCO = -1;
	public static int CINZA = 0;
	public static int PRETO = 1;
	
	/**
	 * Metodo usado para verificar a existencia de
	 * ciclos no grafo.
	 * @param g
	 * @return
	 */
	public static String existeCicloNoGrafo(Grafo g) {
		for (Vertice v : g.vertices)
			v.marca = BRANCO;	//inicia todos os vertices com a marca 'BRANCO'
		for (Vertice v : g.vertices) {
			if (v.marca == PRETO)
				continue;			
			v.marca = CINZA;
			if (buscaProfundidade(v) == 1)	//Ao encontrar o primeiro ciclo no grafo ja retorna um resultado.
				return " Grafo Possui Ciclo.\n";
		}
		return " Grafo Nao Possui Ciclo.";	//Caso nao encontre nenhum ciclo, tambem retorna um resultado.
	}
	
	/**
	 * Metodo que usa busca em profundidade e auxilia
	 * na deteccao de ciclos no grafo.
	 * @param x
	 * @return
	 */
	public static int buscaProfundidade(Vertice x) {
		System.out.println(" Visitando vertice: " + x.chave);
		for (Vertice v : x.listaAdj) {
			if (v.marca == PRETO)
				continue;
			if (v.marca == CINZA)	
				return 1;
			v.marca = CINZA;
			if (buscaProfundidade(v) == 1)
				return 1;
		}
		x.marca = PRETO;
		return 0;
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
			v1.listaAdj.add(v2);
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
			return chave+"";
		}
		
		public int getChave() {
			return this.chave;
		}
		
		public List<Vertice> getListaAdj() {
			return this.listaAdj;
		}
	}
	
	public static void main(String[] args) {
		
		Grafo digrafo = new Grafo(10);
		
		//Criando o Grafo 'G'
		digrafo.insereAresta(0, 1);
		digrafo.insereAresta(0, 2);

		digrafo.insereAresta(2, 3);

		digrafo.insereAresta(3, 0);
		digrafo.insereAresta(3, 4);
		digrafo.insereAresta(3, 5);

		digrafo.insereAresta(4, 5);
		digrafo.insereAresta(5, 1);
		digrafo.insereAresta(6, 7);
		digrafo.insereAresta(6, 8);
		digrafo.insereAresta(6, 9);
		digrafo.insereAresta(7, 9);
		digrafo.insereAresta(8, 9);
		digrafo.insereAresta(9, 1);
		
		System.out.println(existeCicloNoGrafo(digrafo));
	}
}
