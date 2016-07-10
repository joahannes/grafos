
import java.util.ArrayList;
import java.util.List;

public class Questao5 {
	
	/**
	 * Classe que implementa o Grafo 'G' usando matriz de adjacencias.
	 *
	 */
	public static class GrafoMatriz{
		int matriz[][] = {{0,1,0,1,1,0,0,0},
						{1,0,1,0,0,1,0,0},
						{0,1,0,1,0,0,0,1},
						{1,0,1,0,0,0,1,0},
						{1,0,0,0,0,1,1,0},
						{0,1,0,0,1,0,0,1},
						{0,0,0,1,1,0,0,1},
						{0,0,1,0,0,1,1,0}};
		
		public List<Integer> obterVerticesAdjacentes(int i) throws InterruptedException {
			List<Integer> saida = new ArrayList<>(0);
			for (int k = 0; k < 8; k++){
				Thread.sleep(1000);
				if (matriz[i][k] == 1)
					saida.add(k);
			}
			return saida;
		}
		
		public String existeAresta(int i, int j) throws InterruptedException {
			Thread.sleep(1000);
			if (matriz[i][j] == 1)
				return "verdadeiro";
			else
				return "falso";
		}
	}
	
	/**
	 * Classe que implementa o Grafo 'G' usando lista de adjacencias.
	 *
	 */
	public static class GrafoLista{
		@SuppressWarnings("unchecked")
		List<Integer> vertices[] = new List[8];
		
		public GrafoLista() {
			for (int i = 0; i < 8; i++)
				vertices[i] = new ArrayList<>(0);
			criarGrafo();
		}
		
		public List<Integer> obterVerticesAdjacentes(int i) throws InterruptedException {
			Thread.sleep(1000);
			return vertices[i];
		}
		
		public String existeAresta(int i, int j) throws InterruptedException {
			for (Integer k : vertices[i]) {
				Thread.sleep(1000);
				if (k == j)
					return "verdadeiro";
			}
			return "falso";
		}
		
		public void addArestas(int i, int... j) {
			for (Integer k : j) {
				vertices[i].add(k);
			}
		}
		
		public void criarGrafo() {
			addArestas(0, 1, 3, 4);
			addArestas(1, 0, 2, 5);
			addArestas(2, 1, 3, 7);
			addArestas(3, 0, 2, 6);
			addArestas(4, 0, 5, 6);
			addArestas(5, 1, 4, 7);
			addArestas(6, 3, 4, 7);
			addArestas(7, 2, 5, 6);
		}
	}
	
	/**
	 * Classe que implementa o Grafo 'H' usando matriz de adjacencias.
	 *
	 */
	public static class GrafoMatrizCompleto extends GrafoMatriz {
		
		public GrafoMatrizCompleto() {
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++)
					if (i != j)
						matriz[i][j] = 1;
		}
	}
	
	/**
	 * Classe que implementa o Grafo 'H' usando lista de adjacencias.
	 *
	 */
	public static class GrafoListaCompleto extends GrafoLista {
		
		@Override
		public void criarGrafo() {
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++)
					if (i != j)
						addArestas(i, j);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		long tempoInicio, tempoFim;
		double tempoTotal;
		
		System.out.println("\n Criando o grafo 'H' usando matriz de adjacencias.");
		GrafoMatrizCompleto grafoMatrizCompleto = new GrafoMatrizCompleto();
		System.out.println(" -------------------------------------------------");
		for (int i = 0; i < 8; i++) {
			System.out.println(" Vertices adjacentes ao vertice: " + i);
			tempoInicio = System.currentTimeMillis();
			List<?> x = grafoMatrizCompleto.obterVerticesAdjacentes(i);
			tempoFim = System.currentTimeMillis();
			tempoTotal = tempoFim - tempoInicio;
			System.out.println(" Resultado: " + x);
			System.out.println(" Tempo em segundos: " + tempoTotal/1000.0);
		}
		
		System.out.println("\n Criando o grafo 'H' usando lista de adjacencias.");
		System.out.println(" --------------------------------------------------");
		GrafoListaCompleto grafoListaCompleto = new GrafoListaCompleto();
		for (int i = 0; i < 8; i++) {
			System.out.println(" Vertices adjacentes ao vertice: " + i);
			tempoInicio = System.currentTimeMillis();
			List<?> x = grafoListaCompleto.obterVerticesAdjacentes(i);
			tempoFim = System.currentTimeMillis();
			tempoTotal = tempoFim - tempoInicio;
			System.out.println(" Resultado: " + x);
			System.out.println(" Tempo em segundos: " + tempoTotal/1000.0);
		}
	}

}
