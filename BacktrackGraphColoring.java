import java.util.*;

public class BacktrackGraphColoring {
    private int numVertices;
    private int[][] graph;
    private int[] colors;

    public BacktrackGraphColoring(int[][] adjacencyMatrix) {
        this.graph = adjacencyMatrix;
        this.numVertices = graph.length;
        this.colors = new int[numVertices];
    }

    public boolean isSafe(int vertex, int c) {
        for (int i = 0; i < numVertices; i++) {
            if (graph[vertex][i] == 1 && colors[i] == c) {
                return false;
            }
        }
        return true;
    }

    public boolean colorGraph(int vertex, int maxColors) {
        if (vertex == numVertices)
            return true;

        for (int c = 1; c <= maxColors; c++) {
            if (isSafe(vertex, c)) {
                colors[vertex] = c;
                if (colorGraph(vertex + 1, maxColors))
                    return true;
                colors[vertex] = 0;
            }
        }

        return false;
    }

    public void solve() {
        for (int k = 1; k <= numVertices; k++) {
            Arrays.fill(colors, 0);
            if (colorGraph(0, k)) {
                System.out.println("Coloração mínima encontrada com " + k + " cores:");
                for (int i = 0; i < numVertices; i++) {
                    System.out.println("Vértice " + i + " -> Cor " + colors[i]);
                }
                return;
            }
        }
    }

    public static void main(String[] args) {
        // Exemplo: Triângulo (grafo completo de 3 vértices)
        int[][] adjMatrix = {
            {0, 1, 1},
            {1, 0, 1},
            {1, 1, 0}
        };

        BacktrackGraphColoring solver = new BacktrackGraphColoring(adjMatrix);
        solver.solve();
    }
}
