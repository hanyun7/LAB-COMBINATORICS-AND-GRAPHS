import java.util.*;
import java.io.*;

public class Graph{
	private Integer [][] matrix_graph;
	private int v;
	private ArrayList<Node> listEdge;

	public class Node{
		public int v1;
		public int v2;
		public Integer value;

		public Node(int v1, int v2, int value){
			this.v1 = v1;
			this.v2 = v2;
			this.value = value;
		}
	}
	
	public Graph (String in){
		BufferedReader br;
		try {
			br = new BufferedReader (new FileReader(in));
			String s = br.readLine();
			v = Integer.parseInt(s);
			matrix_graph = new Integer [v][v];
			for (int i =0 ; i< v; i++){
				String line = br.readLine();
				String[] w = line.split (" ");
				for (int j = 0 ;j< w.length;j++){
					matrix_graph[i][j] = Integer.parseInt(w[j]);
				}
			}
			br.close();
		} catch (IOException e){}
	}

	public void convertToListEdge(){
		int [] a = new int[v*v]; 
		boolean exits = false;
		System.out.println(a[1]);
		listEdge = new ArrayList<Node>();
		for(int i=0 ; i< v; i++){
			for(int j =0; j<v; j++){
				if(matrix_graph[i][j]!= 0){
					Node temp = new Node(i,j,matrix_graph[i][j]);  
					for(Node item : listEdge){
						if(item.v1 == j && item.v2 == i){
							exits = true; 
							break;
						}
					}
					if(!exits){
						listEdge.add(temp); 
					}else{
						exits = false;
					}
					
				}
			}
		}
		listEdge.sort(new Comparator<Node>() {
			@Override
			public int compare(Node node1, Node node2)
			{ 
				return  node1.value.compareTo(node2.value);
			}
		});
	}

	public void myKrushka(){
		int []b = new int[v];
		int min = 0;
		int max = 0;
		ArrayList<Node> temp = new ArrayList<Node>();
		for(int i =0 ; i<v;i++){
			b[i]=i; 
		}
		for(int i =1; i< listEdge.size(); i++){ 
			if(b[listEdge.get(i).v1] != b[listEdge.get(i).v2]){  
				System.out.print(" "+listEdge.get(i).v1+ "--"+listEdge.get(i).v2 + "--" + listEdge.get(i).value);
				if(b[listEdge.get(i).v1] > b[listEdge.get(i).v2]){  
					min = listEdge.get(i).v2; 	
				}else{
					min = listEdge.get(i).v1; 
				}for(int item :b){
					if(item)
				}
				System.out.print(" "+b[listEdge.get(i).v1]+ "--"+b[listEdge.get(i).v2]);
				System.out.println();
				temp.add(listEdge.get(i));
			}
		}
		System.out.println(temp.size() + "                   -----------------------------          ");
	
		for(int i =0 ; i<v;i++){
			System.out.print(" "+b[i]);
		}
		printList(temp);
	}

	public void printList(ArrayList<Node> temp){
		for(int i=0;i<temp.size() ; i++){
			System.out.println("(" + (temp.get(i).v1+1) + ","+ (temp.get(i).v2+1) + ") : " + temp.get(i).value);
		}
	}

	public void printListEdge(){
		for(int i=0;i<listEdge.size() ; i++){
			System.out.println("(" + (listEdge.get(i).v1+1) + ","+ (listEdge.get(i).v2+1) + ") : " + listEdge.get(i).value);
		}
	}
	
	public void DFS(int s){
		boolean[] visited = new boolean [v];
		dfs1(s,visited);
	}
	
	public void dfs1(int s, boolean [] visited){
		System.out.print( (s+1) + "\t");
		visited [s] = true ;
		for (int i = 0; i<v; i++)
		{
			if(matrix_graph [s][i]== 1 && visited[i]== false ){
				dfs1 (i,visited);
			}
		}
	}
	
	public void print(){
		System.out.println(v);
		for (int i = 0 ; i< v ; i++)
		{
			for (int j = 0 ; j< v;j++)
				System.out.print(matrix_graph[i][j] + " ");
			System.out.println("");
		}
	}
	
	public void bellmanFord(){
		Integer [] d= new Integer [v];
		Integer [] p= new Integer [v];
		boolean ok = true;
		
		
		for (int i = 0 ;i<v;i++){

				d [i] = Integer.MAX_VALUE;
				p [i] = 0;
		}
		d[0] = 0;
		
		while (ok){
			ok = false ;
			for (int i =0 ;i<v;i++){
				for (int j=0;j <v;j++){
					if(matrix_graph[i][j]>0 &&d[j]>d[i]+matrix_graph[i][j])
					{
						d[j] = d[i]+matrix_graph[i][j];
						p[j] = i ;
						ok = true;
					}
				}
			}
		}
		
		for (int i = 0 ;i<v;i++){
			System.out.println();
			System.out.print((i+1)+" ( "+d[i]+" , "+(p[i]+1)+" )");
		}
	}
	
	public static void main (String[] args){
		Graph gr = new Graph ("Input.txt");
		gr.print();
		gr.convertToListEdge();
		gr.printListEdge();
		System.out.println();
		gr.myKrushka();
	}
}