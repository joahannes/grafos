import java.util.ArrayList;
import java.util.List;

public class Questao1 {
	
	/**
	 * Classe que implementa o Grafo 'G' usando matriz de adjacencias.
	 *
	 */
	public static class GrafoMatriz{
		//Grafo G
		int matriz[][] = {
					/*A B C D E F G H I J*/
				/*A*/{0,1,1,0,0,0,0,0,0,0},
				/*B*/{0,0,0,0,0,0,0,0,0,0},
				/*C*/{0,0,0,1,0,0,0,0,0,0},
				/*D*/{1,0,0,0,1,1,0,0,0,0},
				/*E*/{0,0,0,0,0,1,0,0,0,0},
				/*F*/{0,1,0,0,0,0,0,0,0,0},
				/*G*/{0,0,0,0,0,0,0,1,1,1},
				/*H*/{0,0,0,0,0,0,0,0,0,1},
				/*I*/{0,0,0,0,0,0,0,0,0,1},
				/*J*/{0,1,0,0,0,0,0,0,0,0}};
		
		public String existeAresta(int i, int j) throws InterruptedException {
			Thread.sleep(1000);
			if (matriz[i][j] == 1)
				return "verdadeiro";
			else
				return "falso";
		}
		
		public void ImprimeGrafo() {
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++){
						System.out.print(matriz[i][j]);
					}
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
		
		System.out.println("Criando o grafo 'G' usando matriz de adjacencias.");
		GrafoMatriz grafoMatriz = new GrafoMatriz();
		
		System.out.println(" Grafo usando implementacao Matriz de Adjacencias:");
		System.out.println("\n A aresta (0,3) existe no grafo 'G' ? ");
		System.out.println(" Resultado: " + grafoMatriz.existeAresta(0, 3));
		System.out.println("\n A aresta (3,0) existe no grafo 'G' ? ");
		System.out.println(" Resultado: " + grafoMatriz.existeAresta(3, 0));
		System.out.println("\n A aresta (4,1) existe no grafo 'G' ? ");
		System.out.println(" Resultado: " + grafoMatriz.existeAresta(4, 1));
		
		System.out.println("\n---------------------------------------------------");
		System.out.println("Criando o grafo 'G' usando lista de adjacencias.");
		GrafoLista grafoLista = new GrafoLista();
		
		System.out.println(" Grafo usando implementacao Lista de Adjacencias:");
		System.out.println("\n A aresta (7,2) existe no grafo 'G' ? ");
		System.out.println(" Resultado: " + grafoLista.existeAresta(7, 2));
		System.out.println("\n A aresta (3,4) existe no grafo 'G' ? ");
		System.out.println(" Resultado: " + grafoLista.existeAresta(3, 4));

		System.out.println("\n ---------------------------------------------------");
		System.out.println(" Criando grafo 'H' usando matriz de adjacencias.");
		GrafoMatrizCompleto grafoMatrizCompleto = new GrafoMatrizCompleto();
		
		System.out.println(" Buscando a aresta (7,6) no Grafo H - Matriz.");
		long tempoInicio = System.currentTimeMillis();
		String saida = grafoMatrizCompleto.existeAresta(7, 6);
		long tempoFim = System.currentTimeMillis();
		System.out.println(" Resultado: " + saida + " \n Tempo gasto em segundos: " + ((tempoFim - tempoInicio)/1000.0));
		
		System.out.println("\n ---------------------------------------------------");
		System.out.println(" Criando o grafo 'H' usando lista de adjacencias.");
		GrafoListaCompleto grafoListaCompleto = new GrafoListaCompleto();
		
		System.out.println(" Buscando a aresta (7,6) no Grafo H - Lista.");
		tempoInicio = System.currentTimeMillis();
		saida = grafoListaCompleto.existeAresta(7, 6);
		tempoFim = System.currentTimeMillis();
		System.out.println(" Resultado: " + saida + " \n Tempo gasto em segundos: " + ((tempoFim - tempoInicio)/1000.0));
		
		System.out.println("\n\n Para obter um melhor resultado na comparacao do tempo de execucao"
				+ "\n da busca de uma aresta no grafo H usando matriz e lista de adjacencia, foi"
				+ "\n adicionado um sleep de 1000 millisegundos sempre que um vertice eh visitado,"
				+ "\n foi verificado de forma explicita que o tempo de execucao para a implementacao"
				+ "\n por matriz se manteve constante, ja a implementacao por lista ficou mais custosa,"
				+ "\n isso se deve ao simples fato de que na implementacao por lista, eh preciso"
				+ "\n visitar todos os vertices presentes na lista de adjacencia do vertice i,"
				+ "\n enquanto que na implementacao por matriz, o acesso eh unico e por isso muito"
				+ "\n mais rapido.");
	}

}
