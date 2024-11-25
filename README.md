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

2. Dijstra

```
import java.util.*;

class Pair {
    int first;
    int second;
    
    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class Graph {
    int n, e;
    Map<Integer, List<Pair>> adj = new HashMap<>();
    final int INF = Integer.MAX_VALUE;
    
    public Graph(int n, int e) {
        this.n = n + 1;
        this.e = e;
        
        for(int i = 0; i < this.n; i++) {
            adj.put(i, new ArrayList<>());
        }
    }
    
    public void addEdge(int from, int to, int w) {
        adj.get(from).add(new Pair(to, w));
    }
    
    private int getMinDistanceNode(List<Integer> distance, boolean[] visited) {
        int ans = -1;
        for(int i = 0; i < this.n; i++) {
            if(distance.get(i) == this.INF || visited[i]) continue;
            
            if(ans == -1 || distance.get(i) < distance.get(ans)) {
                ans = i;
            }
        }
        
        return ans;
    }
    
    public List<Integer> dijkstra(int src) {
        List<Integer> distance = new ArrayList<>();
        for(int i = 0; i < this.n; i++) {
            distance.add(this.INF);
        }
        
        boolean[] visited = new boolean[this.n];
        
        distance.set(src, 0);
        
        for(int i = 0; i < this.n; i++) {
            int u = getMinDistanceNode(distance, visited);
            
            if(u == -1) continue;
            
            visited[u] = true;
            
            for(Pair data : adj.get(u)) {
                int v = data.first;
                int w = data.second;
                
                if(distance.get(u) + w < distance.get(v)) {
                    distance.set(v, distance.get(u) + w);
                }
            }
        }
        
        return distance;
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
            int w = sc.nextInt();
            
            graph.addEdge(u, v, w);
            graph.addEdge(v, u, w);
        }
        
        System.out.print("Enter src for traversal-algorithsm : ");
        int src = sc.nextInt();
        
        System.out.print("Dijkstra : ");
        for(Integer distance : graph.dijkstra(src)) {
            System.out.print((distance == graph.INF ? "INF" : distance) + " ");
        }
    }
}


```

