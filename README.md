# Data-Structure-Algorithms-Revision
DSA

GRAPHS : 

1. DFS + BFS :
```
import java.util.*;

class Graph {
    int n, e;
    Map<Integer, List<Integer>> adj = new HashMap<>();
    
    public Graph(int n, int e) {
        this.n = n + 1;
        this.e = e;
        
        for(int i = 0; i <= n; i++) {
            adj.put(i, new ArrayList<>());
        }
    }
    
    public void addEdge(int from, int to) {
        adj.get(from).add(to);
    }
    
    private void dfs(int u, List<Integer> ans, boolean[] visited) {
        visited[u] = true;
        ans.add(u);
        
        for(Integer v : adj.get(u)) {
            if(visited[v]) continue;
            dfs(v, ans, visited);
        }
    }
    
    public List<Integer> dfs(Integer src) {
        List<Integer> ans = new ArrayList<>();
        boolean[] visited = new boolean[this.n];
        dfs(src, ans, visited);
        return ans;
    }
    
    public List<Integer> bfs(Integer src) {
        List<Integer> ans = new ArrayList<>();
        
        Queue<Integer> Q = new LinkedList<>();
        boolean[] visited = new boolean[this.n];    
        
        Q.add(src);
        visited[src] = true;
        
        while(!Q.isEmpty()) {
            int u = Q.poll();
            ans.add(u);
            
            for(int v : adj.get(u)) {
                if(visited[v]) continue;
                Q.add(v);
                visited[v] = true;
            }
        }
        
        return ans;
    }
    
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        
        Graph graph = new Graph(n, e);
        
        System.out.println("Enter " + e + " edges :)");
        for(int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            
            graph.addEdge(u, v);
            graph.addEdge(v, u);
        }
        
        System.out.print("Enter src for traversal-algorithsm : ");
        int src = sc.nextInt();x
        
        System.out.print("DFS : ");
        for(Integer u : graph.dfs(src)) {
            System.out.print(u + " ");
        }
        
        System.out.println();
        
        System.out.print("BFS : ");
        for(Integer u : graph.bfs(src)) {
            System.out.print(u + " ");
        }
        
    }
}


```
