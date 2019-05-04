class EX2  
{ 
    static class Graph 
    { 
        int v, e; 
        int[][] dir; 
   
        Graph(int v, int e) { 
            this.v = v; 
            this.e = e; 
            dir = new int[v][]; 
            for (int i = 0; i < v; i++) 
                dir[i] = new int[v]; 
        } 
    } 
    static Graph createGraph(int v, int e)  
    { 
        Graph G = new Graph(v, e); 

        G.dir[0][1] = 1; 
        G.dir[0][2] = 1; 
        G.dir[0][3] = 1; 
  
        //direction from 1 
        G.dir[1][0] = 1; 
        G.dir[1][3] = 1; 
  
        //direction from 2 
        G.dir[2][0] = 1; 
        G.dir[2][3] = 1; 
  
        //direction from 3 
        G.dir[3][0] = 1; 
        G.dir[3][1] = 1; 
        G.dir[3][2] = 1; 
  
        return G; 
    }


    static int totEdge(int n) 
    { 
        int result = 0; 
  
        result = (n * (n - 1)) / 2; 
  
        return result; 
    } 
  
    static int findDegree(Graph G, int ver)  
    { 
        int degree = 0; 
        for (int i = 0; i < G.v; i++) { 
            if (G.dir[ver][i] == 1) 
                degree++; 
        } 
        return degree; 
    } 
  
    public static void main(String[] args) 
    { 
        int vertices = 4; 
        int edges = 5; 
        Graph G = createGraph(vertices, edges); 
        int ver = 0;
        int degree = findDegree(G, ver);
        int n = 6; 
        System.out.println("Number of edges: " + totEdge(n)); 
        System.out.println("Degree of a each vertex in a Graph" + degree);



        String fileName = "EX2.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }


    } 
} 