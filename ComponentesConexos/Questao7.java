
import java.util.ArrayList;
import java.util.List;

public class Questao7 {
	
	private Grafo grafo;
	static int sc[];
	static int sop[], sopR[];
	static int cnt, id;
	
	public Questao7(Grafo digrafo) {
		grafo = digrafo;
		sc = new int[grafo.numeroVertices];
		sop = new int[grafo.numeroVertices];
		sopR =  new int[grafo.numeroVertices];
	}
	
	public int digrafoSc() {
		Grafo reverso = grafoReverso(grafo);
		cnt = 0;
		for (int k = 0; k < reverso.numeroVertices; k++)
			sc[k] = -1;
		for (int k = 0; k < reverso.numeroVertices; k++)
			if (sc[k] == -1)
				dfsRsc(reverso, k, 0);
		for (int k = 0; k < grafo.numeroVertices; k++)
			sopR[k] = sop[k];
		cnt = id = 0;
		for (int k = 0; k < grafo.numeroVertices; k++)
			sc[k] = -1;
		for (int k = grafo.numeroVertices - 1; k >= 0; k--)
			if (sc[sopR[k]] == -1)
				dfsRsc(grafo, sopR[k], id++);
		return id;
	}
	
	public void dfsRsc(Grafo grafo, int v, int id) {
		sc[v] = id;
		for (Integer vAdj : grafo.vertices[v]) {
			if (sc[vAdj] == -1)
				dfsRsc(grafo, vAdj, id);
		}
		sop[cnt++] = v;
	}
	
	public Grafo grafoReverso(Grafo g) {
		Grafo gReverse = new Grafo(this.grafo.numeroVertices, false);
		for (int i = 0; i < this.grafo.numeroVertices; i++) {
			for (Integer v2 : this.grafo.vertices[i]) {
				gReverse.addArestas(v2, i);
			}
		}
		return gReverse;
	}
	
	/**
	 * Classe que implementa o Grafo 'G' usando lista de adjacencias.
	 *
	 */
	public static class Grafo{
		@SuppressWarnings("unchecked")
		List<Integer> vertices[] = new List[10];
		int numeroVertices;
		
		public Grafo(int numVertices, boolean criarGrafo) {
			for (int i = 0; i < numVertices; i++)
				vertices[i] = new ArrayList<>(0);
			numeroVertices = numVertices;
			if (criarGrafo)
				criarGrafo();
		}
		
		public void addArestas(int i, int... j) {
			for (Integer k : j) {
				vertices[i].add(k);
			}
		}
		
		/**
		 * Cria o Digrafo 'G'
		 */
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
	
	public static void imprimeComponentes() {
		int componentes[] = new int[id];
		String saidas[] = new String[id];
		System.out.print("\n");
		for (int i = 0; i < id; i++) {
			componentes[i] = i;
			saidas[i] = " Componente fortemente conexo (" + (i + 1) + "): ";
		}
		for (int k = 0; k < sc.length; k++) {
			if (sc[k] != -1)
				saidas[sc[k]] += " " + k;
		}
		for (int i = 0; i < id; i++) {
			System.out.println(saidas[i]);
		}
		System.out.print("\n");
	}

	public static void main(String[] args) {

		Grafo grafo = new Grafo(10, true);
		Questao7 solucao = new Questao7(grafo);
		int quantidadeComponentes = solucao.digrafoSc();
		imprimeComponentes();

	}

}
