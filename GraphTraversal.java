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
    
    
    public List<Integer> optimizedDijkstraPQ(int src) {
        Integer[] distance = new Integer[this.n];
        Arrays.fill(distance, this.INF);
        
        Comparator<Pair> cmp = (a, b) -> a.second - b.second; //min-weight
        
        PriorityQueue<Pair> Q = new PriorityQueue<>(cmp); 
        boolean[] visited = new boolean[this.n];
        
        Q.add(new Pair(src, 0));
        
        
        while(!Q.isEmpty()) {
            Pair data = Q.poll();
            
            int u = data.first;
            if(visited[u]) continue;
            
            distance[u] = data.second;
            visited[u] = true;
            
            for(Pair p : adj.get(u)) {
                int v = p.first;
                int w = p.second;
                
                if(distance[u] + w < distance[v]) {
                    Q.add(new Pair(v, distance[u] + w));
                }
            }            
        }
        
        return Arrays.asList(distance);
    }
    
    
    public List<Integer> optimizedDijkstraSET(int src) {
        Integer[] distance = new Integer[this.n];
        Arrays.fill(distance, this.INF);
        
        boolean[] visited = new boolean[this.n];
        
        Comparator<Pair> cmp = (a, b) -> a.second - b.second;
        TreeSet<Pair> set = new TreeSet<>(cmp);
        
        set.add(new Pair(src, 0));
        
        while(!set.isEmpty()) {
            Pair data = set.pollFirst();
            
            int u = data.first;
            if(visited[u]) continue;
            
            distance[u] = data.second;
            visited[u] = true;
            
            for(Pair pair : adj.get(u)) {
                int v = pair.first;
                int w = pair.second;
                
                if(distance[u] + w < distance[v]) {
                    set.add(new Pair(v, distance[u] + w));
                }
            }
        }
        
        return Arrays.asList(distance);
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
        
        System.out.print("Dijkstra [BruteForce]: ");
        for(Integer distance : graph.dijkstra(src)) {
            System.out.print((distance == graph.INF ? "INF" : distance) + " ");
        }
        
        System.out.println();
        
        System.out.print("Dijkstra [using PQ]: ");
        for(Integer distance : graph.optimizedDijkstraPQ(src)) {
            System.out.print((distance == graph.INF ? "INF" : distance) + " ");
        }
        
        System.out.println();
        System.out.print("Dijkstra [using Set]: ");
        for(Integer distance : graph.optimizedDijkstraSET(src)) {
            System.out.print((distance == graph.INF ? "INF" : distance) + " ");
        }
    }
}
