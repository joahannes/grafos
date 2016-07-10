
import java.util.ArrayList;
import java.util.List;

public class Questao6 {
	
	public static final byte branco = 0;
	public static byte cinza = 1;
	public static byte preto = 2;
	private int d [ ] , t [ ] , antecessor [ ] ;
	private String saida;
	private Grafo grafo;
	
	public Questao6 (Grafo g ){
		this.grafo = g;
		int	n =	grafo.numeroVertices;
		d =	new int[n];
		t = new int[n];  
		antecessor = new int[n];
		saida = "";
	}
	
	/**
	 * Metodo auxiliar da busca em profundidade.
	 * @param u
	 * @param tempo
	 * @param cor
	 * @return
	 */
	private int visitaDfs(int u, int tempo, int cor[]) {
		System.out.println(" Visitando o vertice: " + u);
		saida += u + ", ";	//Armazena a ordem de visita dos vertices em uma string
		cor[u] = cinza;
		this.d[u] = ++tempo;
		if (!grafo.vertices[u].isEmpty()) {
			List<Integer> listaAdj = grafo.vertices[u];
			for (Integer v : listaAdj) {
				if (cor[v] == branco) {
					this.antecessor[v] = u;
					tempo = this.visitaDfs(v, tempo, cor);
				}
			}
		}
		cor[u] = preto;
		this.t[u] = ++tempo;
		return tempo;
	}
	
	/**
	 * Metodo que realiza a busca em profundidade propriamente dita.
	 */
	public void buscaProfundidade() {
		int tempo = 0;
		int cor[] = new int[this.grafo.numeroVertices];
		for (int u = 0; u < this.grafo.numeroVertices; u++) {
			cor[u] = branco;
			this.antecessor[u] = -1;
		}
		for (int u = 0; u < grafo.numeroVertices; u++) {
			if (cor[u] == branco)
				tempo = this.visitaDfs(u, tempo, cor);
		}
		System.out.print("\n Ordem de visita: ");
		System.out.println(saida.substring(0, saida.lastIndexOf(",")));
		System.out.print("\n");
	}
	
	/**
	 * Classe que implementa o Grafo 'G' usando lista de adjacencias.
	 *
	 */
	public static class Grafo{
		@SuppressWarnings("unchecked")
		List<Integer> vertices[] = new List[10];
		int numeroVertices;
		
		public Grafo(int numVertices) {
			for (int i = 0; i < numVertices; i++)
				vertices[i] = new ArrayList<>(0);
			numeroVertices = numVertices;
			criarGrafo();
		}
		
		public void addArestas(int i, int... j) {
			for (Integer k : j) {
				vertices[i].add(k);
			}
		}
		
		//Cria o Digrafo 'G'
		public void criarGrafo() {
			addArestas(0, 1, 2);
			//addArestas(1, 4, 8);
			addArestas(2, 3);
			addArestas(3, 0, 4, 5);
			addArestas(4, 5);
			addArestas(5, 1);
			addArestas(6, 7, 8, 9);
			addArestas(7, 9);
			addArestas(8, 9);
			addArestas(9, 1);
		}
	}

	public static void main(String[] args) {
		
		Grafo grafo = new Grafo(10);
		
		Questao6 solucao = new Questao6(grafo);
		solucao.buscaProfundidade();

	}

}
