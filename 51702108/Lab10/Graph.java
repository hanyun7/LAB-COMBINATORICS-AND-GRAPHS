import java.util.*;
import java.io.*;

public class Graph{
	private Integer [][] matrix_graph;
	private int v;
	private ArrayList<Node> listEdge;
	private int [][] b; 

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
					if(matrix_graph[i][j] == 0 && i!=j){
						matrix_graph[i][j] = 100000;
					}
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

	public void print(){
		System.out.println(v);
		for (int i = 0 ; i< v ; i++)
		{
			for (int j = 0 ; j< v;j++)
				if(matrix_graph[i][j] == 100000){
					System.out.print(0 + " "); 
				}else{ 
					System.out.print(matrix_graph[i][j] + " ");
				}
			System.out.println("");
		}
	}

	public void floyd(){ 
		b = new int[v][v];
		for(int i=0;i<v;i++){
			for(int j= 0; j<v;j++){
				b[i][j]= i;
			}
		}	 
		for(int k = 0 ;k<v ;k++){
			for(int i=0 ; i <v ;i++){
				for(int j= 0;j<v;j++){ 
					if(matrix_graph[i][j] > matrix_graph[i][k]+ matrix_graph[k][j]){
						matrix_graph[i][j] = matrix_graph[i][k]+ matrix_graph[k][j];
						b[i][j] = k; 
						
					}
				}
			} 
		}
		

		// 
		// for(int i =0;i<v;i++){
		// 	for(int j=0;j<v;j++){ 
		// 		System.out.print(b[i][j]+1+" "); 
		// 	}
		// 	System.out.println();
		// }  
	}

	public void findShorted(int start, int end){  
		ArrayList<Integer>  _back = new ArrayList<Integer>();
		do{	 
			_back.add(end); 
			end = b[start][end];    
		}while(start != end);

		// get last vertex
		_back.add(end);
		Collections.reverse(_back);
		for(Integer item: _back){
			System.out.print(item+1 + " -> ");
		}
	}
	
	public static void main (String[] args){
		Graph gr = new Graph ("input.txt"); 
		gr.print();
		System.out.println();
		gr.floyd();

		Scanner sr= new Scanner(System.in);

		int x,y = 0;
		System.out.print("Nhap dinh x: ");
		x = sr.nextInt();
		System.out.print("Nhap dinh y: ");
		y = sr.nextInt();

		System.out.println(x+""+y);
		gr.findShorted(x,y);
		
	}
}