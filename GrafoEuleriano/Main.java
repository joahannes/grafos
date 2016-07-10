import java.util.*;
public class Main {
	private static int time = 1;
	private static void removeAresta(Grafo g, int u, int v)
	{
		if(g == null)
			return;
		Integer vNode = g.adj[u].get(v);
		g.list[u].remove(vNode);
		g.adj[u].remove(vNode);
 
		Integer uNode = g.adj[v].get(u);
		g.list[v].remove(uNode);
		g.adj[v].remove(uNode);
	}
 
	private static boolean doDFS(Grafo g, int source, int u, int v, int[] disc, int[] low, int[] parent)
	{
		disc[source] = low[source] = time++;
		int i=0, size=0, next=0;
		size = g.list[source].size();
 
		for(i=0; i<size; i++)
		{
			next = (int) g.list[source].get(i);		
			if(disc[next] == -1)
			{
				parent[next] = source;
				if(doDFS(g, next, u, v, disc, low, parent) == true)
					return true;
				low[source] = Math.min(low[source], low[next]);
				if(low[next] > disc[source] && ( (source == u && next == v) || (source == v && next == u) ))
					return true;
			}
			else if(next != parent[source])
				low[source] = Math.min(low[source], disc[next]);
		}
		return false;
	}
 
	private static boolean isBridgeEdge(Grafo g, int u, int v)
	{
		int[] disc = new int[g.V];
		int[] low = new int[g.V];
		int[] parent = new int[g.V];
		Arrays.fill(disc,  -1);
		Arrays.fill(low,  -1);
		Arrays.fill(parent, -1);
		return doDFS(g, 0, u, v, disc, low, parent);
	}
 
	private static boolean isValidNextEdge(Grafo g, int u, int v)
	{
		int size = g.list[u].size();//System.out.println(u + " " + v + " " + size);
		if(size == 1)
			return true;
		return !isBridgeEdge(g, u, v);
	}
 
	private static void mostraEulerUtil(Grafo g, int u)
	{
		if(g == null)
			return;
		int v = 0;
		for(int i=0; i<g.list[u].size(); i++)
		{
			v = (int) g.list[u].get(i);
			if(isValidNextEdge(g, u, v))
			{
				System.out.print("| " + u + " - " + v + " | ");
				removeAresta(g, u, v);
				mostraEulerUtil(g, v);
			}
		}
	}
 
	private static void mostraEuler(Grafo g)
	{
		if(g == null)
			return;
 
		int u = 0, i=0;
		for(i=0; i<g.V; i++)
		{
			if((g.adj[i].size() & 1) == 1)
			{
				u = i;
				System.out.println("\nO grafo não é euleriano.");
				System.out.print("Caminho Euleriano: ");
				mostraEulerUtil(g, u);
				System.out.print("\n");
				break;
			}
		}
		if(i == g.V){
			System.out.println("\nO grafo é Euleriano.");
			System.out.print("Circuito Euleriano: ");
			mostraEulerUtil(g, u);
			System.out.print("\n");
		}
		System.out.println("");
	}
 
	public static void main(String[] args)
	{
		int V = 10;
		Grafo grafo = new Grafo(V);
		grafo.addAresta(0, 1);
		grafo.addAresta(0, 2);
		grafo.addAresta(1, 6);
		//addArestas(1, 4, 8);
		grafo.addAresta(2, 3);
		//digrafo.insereAresta(3, 0);
		grafo.addAresta(3, 4);
		//grafo.addAresta(3, 5);

		grafo.addAresta(4, 5);
		grafo.addAresta(5, 1);
		grafo.addAresta(6, 7);
		grafo.addAresta(6, 8);
		grafo.addAresta(6, 9);
		grafo.addAresta(7, 9);
		grafo.addAresta(8, 9);
		grafo.addAresta(9, 1);
		//g.addAresta(3, 0);
		mostraEuler(grafo);
	}
}
 
class Grafo {
	int V;
	HashMap<Integer, Integer> adj[];
	LinkedList<Integer> list[];
 
	public Grafo(int V)
	{
		this.V = V;
		adj = new HashMap[V];
		list = new LinkedList[V];
		for(int i=0 ; i<V ; i++)
		{
			adj[i] = new HashMap<Integer, Integer>();
			list[i] = new LinkedList<Integer>();
		}
	}
 
	public void addAresta(int u, int v)
	{
		//Duas chamadas para grafo não direcionado
		//Vertice_1
		Integer obj1 = new Integer(v); 
		list[u].addLast(obj1);
		adj[u].put(v, obj1);
		//Vertice_2
		Integer obj2 = new Integer(u);
		list[v].addLast(obj2);
		adj[v].put(u, obj2);
	}
} 
