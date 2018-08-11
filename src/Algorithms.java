/*
 * Samuel Akinmulero, Nelson Conyers, Luis Cuadros, Brittany Saunders
 * 05-03-2018
 * CS560 Spring 2018
 * Algorithms Group Programming Assignment
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Algorithms {
    static int[][] hexArray = new int[233][2];
    static int[] values = new int[233];
    static int[] keys = new int[233];
    static int[][] position = new int[15][16];
    static Map<Integer, Integer> map = new HashMap();
    static int[] leftEdge = new int[16];
    static int[] rightEdge = new int[16];
    static double[][] adj_matrix = new double[234][234];
    static double[] hexFinal = new double[233];
    static double[] path = new double[233];

    //public Alg() {
    //}

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new BufferedReader(new FileReader("C:\\Users\\Samuel (Yomi)\\IdeaProjects\\Optimal Matrix\\src\\TextTester")));

        int eKey;
        int oKey;
        while(scan.hasNextLine()) {
            for(eKey = 0; eKey < hexArray.length; ++eKey) {
                String[] string = scan.nextLine().trim().split(" ");

                for(oKey = 0; oKey < string.length; ++oKey) {
                    hexArray[eKey][oKey] = Integer.parseInt(string[oKey]);
                }
            }
        }

        for(eKey = 0; eKey < hexArray.length; ++eKey) {
            values[eKey] = hexArray[eKey][1];
            keys[eKey] = hexArray[eKey][0];
            map.put(keys[eKey], values[eKey]);
        }

        eKey = 1;
        oKey = 1;

        int i;
        int j;
        for(i = 0; i <= 14; i += 2) {
            for(j = 0; j <= 15; ++j) {
                if (i == 0) {
                    leftEdge[j] = keys[eKey - 1];
                }

                if (i == 14) {
                    rightEdge[j] = keys[eKey - 1];
                }

                position[i][j] = keys[eKey - 1];
            }

            eKey += 15;
        }

        for(i = 1; i <= 13; i += 2) {
            for(j = 0; j <= 14; ++j) {
                position[i][j] = keys[oKey - 1];

            }

            oKey += 15;

        }

        for(i = 0; i < 233; ++i) {
            for(j = 0; j < 233; ++j) {
                adj_matrix[i][j] = 0.0D;
            }
        }

        for(i = 1; i <= 233; ++i) {
            if ((Integer)map.get(i) != -1) {
                Program(i);
            }
        }

        Algorithms t = new Algorithms();
        t.dijkstra(adj_matrix, 226);
    }

    public static void Program(int currKey) {
        int caseNum = 3;

        int i;
        for(i = 0; i < leftEdge.length; ++i) {
            if (currKey == leftEdge[i]) {
                caseNum = 1;
            }
        }

        for(i = 0; i < rightEdge.length; ++i) {
            if (currKey == rightEdge[i]) {
                caseNum = 2;
            }
        }

        switch(caseNum) {
            case 1:
                leftEdgeCase(currKey);
                break;
            case 2:
                rightEdgeCase(currKey);
                break;
            case 3:
                centerCase(currKey);
        }

    }

    public static void leftEdgeCase(int key) {
        int top = key - 15;
        int topRight = key - 7;
        int bottomRight = key + 8;
        int bottom = key + 15;
        if (key == 1) {
            if ((Integer)map.get(bottom) != -1) {
                adj_matrix[key][bottom] = (double)((Integer)map.get(key) + (Integer)map.get(bottom)) / 2.0D;
                adj_matrix[bottom][key] = (double)((Integer)map.get(key) + (Integer)map.get(bottom)) / 2.0D;
            }

            if ((Integer)map.get(bottomRight) != -1) {
                adj_matrix[key][bottomRight] = (double)((Integer)map.get(key) + (Integer)map.get(bottomRight)) / 2.0D;
                adj_matrix[bottomRight][key] = (double)((Integer)map.get(key) + (Integer)map.get(bottomRight)) / 2.0D;
            }
        } else if (key == 226) {
            if ((Integer)map.get(top) != -1) {
                adj_matrix[key][top] = (double)((Integer)map.get(key) + (Integer)map.get(top)) / 2.0D;
                adj_matrix[top][key] = (double)((Integer)map.get(key) + (Integer)map.get(top)) / 2.0D;
            }

            if ((Integer)map.get(topRight) != -1) {
                adj_matrix[key][topRight] = (double)((Integer)map.get(key) + (Integer)map.get(topRight)) / 2.0D;
                adj_matrix[topRight][key] = (double)((Integer)map.get(key) + (Integer)map.get(topRight)) / 2.0D;
            }
        } else {
            if ((Integer)map.get(bottom) != -1) {
                adj_matrix[key][bottom] = (double)((Integer)map.get(key) + (Integer)map.get(bottom)) / 2.0D;
                adj_matrix[bottom][key] = (double)((Integer)map.get(key) + (Integer)map.get(bottom)) / 2.0D;
            }

            if ((Integer)map.get(bottomRight) != -1) {
                adj_matrix[key][bottomRight] = (double)((Integer)map.get(key) + (Integer)map.get(bottomRight)) / 2.0D;
                adj_matrix[bottomRight][key] = (double)((Integer)map.get(key) + (Integer)map.get(bottomRight)) / 2.0D;
            }

            if ((Integer)map.get(top) != -1) {
                adj_matrix[key][top] = (double)((Integer)map.get(key) + (Integer)map.get(top)) / 2.0D;
                adj_matrix[top][key] = (double)((Integer)map.get(key) + (Integer)map.get(top)) / 2.0D;
            }

            if ((Integer)map.get(topRight) != -1) {
                adj_matrix[key][topRight] = (double)((Integer)map.get(key) + (Integer)map.get(topRight)) / 2.0D;
                adj_matrix[topRight][key] = (double)((Integer)map.get(key) + (Integer)map.get(topRight)) / 2.0D;
            }
        }

    }

    public static void rightEdgeCase(int key) {
        int top = key - 15;
        int topLeft = key - 8;
        int bottomLeft = key + 7;
        int bottom = key + 15;
        if (key == 8) {
            if ((Integer)map.get(bottom) != -1) {
                adj_matrix[key][bottom] = (double)((Integer)map.get(key) + (Integer)map.get(bottom)) / 2.0D;
                adj_matrix[bottom][key] = (double)((Integer)map.get(key) + (Integer)map.get(bottom)) / 2.0D;
            }

            if ((Integer)map.get(bottomLeft) != -1) {
                adj_matrix[key][bottomLeft] = (double)((Integer)map.get(key) + (Integer)map.get(bottomLeft)) / 2.0D;
                adj_matrix[bottomLeft][key] = (double)((Integer)map.get(key) + (Integer)map.get(bottomLeft)) / 2.0D;
            }
        } else if (key == 233) {
            if ((Integer)map.get(top) != -1) {
                adj_matrix[key][top] = (double)((Integer)map.get(key) + (Integer)map.get(top)) / 2.0D;
                adj_matrix[top][key] = (double)((Integer)map.get(key) + (Integer)map.get(top)) / 2.0D;
            }

            if ((Integer)map.get(topLeft) != -1) {
                adj_matrix[key][topLeft] = (double)((Integer)map.get(key) + (Integer)map.get(topLeft)) / 2.0D;
                adj_matrix[topLeft][key] = (double)((Integer)map.get(key) + (Integer)map.get(topLeft)) / 2.0D;
            }
        } else {
            if ((Integer)map.get(bottom) != -1) {
                adj_matrix[key][bottom] = (double)((Integer)map.get(key) + (Integer)map.get(bottom)) / 2.0D;
                adj_matrix[bottom][key] = (double)((Integer)map.get(key) + (Integer)map.get(bottom)) / 2.0D;
            }

            if ((Integer)map.get(bottomLeft) != -1) {
                adj_matrix[key][bottomLeft] = (double)((Integer)map.get(key) + (Integer)map.get(bottomLeft)) / 2.0D;
                adj_matrix[bottomLeft][key] = (double)((Integer)map.get(key) + (Integer)map.get(bottomLeft)) / 2.0D;
            }

            if ((Integer)map.get(top) != -1) {
                adj_matrix[key][top] = (double)((Integer)map.get(key) + (Integer)map.get(top)) / 2.0D;
                adj_matrix[top][key] = (double)((Integer)map.get(key) + (Integer)map.get(top)) / 2.0D;
            }

            if ((Integer)map.get(topLeft) != -1) {
                adj_matrix[key][topLeft] = (double)((Integer)map.get(key) + (Integer)map.get(topLeft)) / 2.0D;
                adj_matrix[topLeft][key] = (double)((Integer)map.get(key) + (Integer)map.get(topLeft)) / 2.0D;
            }
        }

    }

    public static void centerCase(int key) {
        int top = key - 15;
        int topLeft = key - 8;
        int topRight = key - 7;
        int bottomRight = key + 8;
        int bottomLeft = key + 7;
        int bottom = key + 15;
        if (key > 0 && key < 9) {
            if ((Integer)map.get(bottom) != -1) {
                adj_matrix[key][bottom] = (double)((Integer)map.get(key) + (Integer)map.get(bottom)) / 2.0D;
                adj_matrix[bottom][key] = (double)((Integer)map.get(key) + (Integer)map.get(bottom)) / 2.0D;
            }

            if ((Integer)map.get(bottomLeft) != -1) {
                adj_matrix[key][bottomLeft] = (double)((Integer)map.get(key) + (Integer)map.get(bottom)) / 2.0D;
                adj_matrix[bottomLeft][key] = (double)((Integer)map.get(key) + (Integer)map.get(bottom)) / 2.0D;
            }

            if ((Integer)map.get(bottomRight) != -1) {
                adj_matrix[key][bottomRight] = (double)((Integer)map.get(key) + (Integer)map.get(bottomRight)) / 2.0D;
                adj_matrix[bottomRight][key] = (double)((Integer)map.get(key) + (Integer)map.get(bottomRight)) / 2.0D;
            }
        } else if (key > 8 && key < 16) {
            if ((Integer)map.get(bottom) != -1) {
                adj_matrix[key][bottom] = (double)((Integer)map.get(key) + (Integer)map.get(bottom)) / 2.0D;
                adj_matrix[bottom][key] = (double)((Integer)map.get(key) + (Integer)map.get(bottom)) / 2.0D;
            }

            if ((Integer)map.get(bottomLeft) != -1) {
                adj_matrix[key][bottomLeft] = (double)((Integer)map.get(key) + (Integer)map.get(bottomLeft)) / 2.0D;
                adj_matrix[bottomLeft][key] = (double)((Integer)map.get(key) + (Integer)map.get(bottomLeft)) / 2.0D;
            }

            if ((Integer)map.get(bottomRight) != -1) {
                adj_matrix[key][bottomRight] = (double)((Integer)map.get(key) + (Integer)map.get(bottomRight)) / 2.0D;
                adj_matrix[bottomRight][key] = (double)((Integer)map.get(key) + (Integer)map.get(bottomRight)) / 2.0D;
            }

            if ((Integer)map.get(topLeft) != -1) {
                adj_matrix[key][topLeft] = (double)((Integer)map.get(key) + (Integer)map.get(topLeft)) / 2.0D;
                adj_matrix[topLeft][key] = (double)((Integer)map.get(key) + (Integer)map.get(topLeft)) / 2.0D;
            }

            if ((Integer)map.get(topRight) != -1) {
                adj_matrix[key][topRight] = (double)((Integer)map.get(key) + (Integer)map.get(topRight)) / 2.0D;
                adj_matrix[topRight][key] = (double)((Integer)map.get(key) + (Integer)map.get(topRight)) / 2.0D;
            }
        } else if (key > 218 && key < 226) {
            if ((Integer)map.get(top) != -1) {
                adj_matrix[key][top] = (double)((Integer)map.get(key) + (Integer)map.get(top)) / 2.0D;
                adj_matrix[top][key] = (double)((Integer)map.get(key) + (Integer)map.get(top)) / 2.0D;
            }

            if ((Integer)map.get(topLeft) != -1) {
                adj_matrix[key][topLeft] = (double)((Integer)map.get(key) + (Integer)map.get(topLeft)) / 2.0D;
                adj_matrix[topLeft][key] = (double)((Integer)map.get(key) + (Integer)map.get(topLeft)) / 2.0D;
            }

            if ((Integer)map.get(topRight) != -1) {
                adj_matrix[key][topRight] = (double)((Integer)map.get(key) + (Integer)map.get(topRight)) / 2.0D;
                adj_matrix[topRight][key] = (double)((Integer)map.get(key) + (Integer)map.get(topRight)) / 2.0D;
            }

            if ((Integer)map.get(bottomLeft) != -1) {
                adj_matrix[key][bottomLeft] = (double)((Integer)map.get(key) + (Integer)map.get(bottomLeft)) / 2.0D;
                adj_matrix[bottomLeft][key] = (double)((Integer)map.get(key) + (Integer)map.get(bottomLeft)) / 2.0D;
            }

            if ((Integer)map.get(bottomRight) != -1) {
                adj_matrix[key][bottomRight] = (double)((Integer)map.get(key) + (Integer)map.get(bottomRight)) / 2.0D;
                adj_matrix[bottomRight][key] = (double)((Integer)map.get(key) + (Integer)map.get(bottomRight)) / 2.0D;
            }
        } else if (key > 225 && key < 234) {
            if ((Integer)map.get(top) != -1) {
                adj_matrix[key][top] = (double)((Integer)map.get(key) + (Integer)map.get(top)) / 2.0D;
                adj_matrix[top][key] = (double)((Integer)map.get(key) + (Integer)map.get(top)) / 2.0D;
            }

            if ((Integer)map.get(topLeft) != -1) {
                adj_matrix[key][topLeft] = (double)((Integer)map.get(key) + (Integer)map.get(topLeft)) / 2.0D;
                adj_matrix[topLeft][key] = (double)((Integer)map.get(key) + (Integer)map.get(topLeft)) / 2.0D;
            }

            if ((Integer)map.get(topRight) != -1) {
                adj_matrix[key][topRight] = (double)((Integer)map.get(key) + (Integer)map.get(topRight)) / 2.0D;
                adj_matrix[topRight][key] = (double)((Integer)map.get(key) + (Integer)map.get(topRight)) / 2.0D;
            }
        } else {
            if ((Integer)map.get(top) != -1) {
                adj_matrix[key][top] = (double)((Integer)map.get(key) + (Integer)map.get(top)) / 2.0D;
                adj_matrix[top][key] = (double)((Integer)map.get(key) + (Integer)map.get(top)) / 2.0D;

            }

            if ((Integer)map.get(topLeft) != -1) {
                adj_matrix[key][topLeft] = (double)((Integer)map.get(key) + (Integer)map.get(topLeft)) / 2.0D;
                adj_matrix[topLeft][key] = (double)((Integer)map.get(key) + (Integer)map.get(topLeft)) / 2.0D;
            }

            if ((Integer)map.get(topRight) != -1) {
                adj_matrix[key][topRight] = (double)((Integer)map.get(key) + (Integer)map.get(topRight)) / 2.0D;
                adj_matrix[topRight][key] = (double)((Integer)map.get(key) + (Integer)map.get(topRight)) / 2.0D;
            }

            if ((Integer)map.get(bottom) != -1) {
                adj_matrix[key][bottom] = (double)((Integer)map.get(key) + (Integer)map.get(bottom)) / 2.0D;
                adj_matrix[bottom][key] = (double)((Integer)map.get(key) + (Integer)map.get(bottom)) / 2.0D;
            }

            if ((Integer)map.get(bottomLeft) != -1) {
                adj_matrix[key][bottomLeft] = (double)((Integer)map.get(key) + (Integer)map.get(bottomLeft)) / 2.0D;
                adj_matrix[bottomLeft][key] = (double)((Integer)map.get(key) + (Integer)map.get(bottomLeft)) / 2.0D;
            }

            if ((Integer)map.get(bottomRight) != -1) {
                adj_matrix[key][bottomRight] = (double)((Integer)map.get(key) + (Integer)map.get(bottomRight)) / 2.0D;
                adj_matrix[bottomRight][key] = (double)((Integer)map.get(key) + (Integer)map.get(bottomRight)) / 2.0D;
            }
        }

    }

    double minDistance(double[] dist, Boolean[] sptSet) {
        double min = 2.147483647E9D;
        int min_index = -1;

        for(int v = 0; v < 233; ++v) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }

        return (double)min_index;
    }

    void printSolution(double[] dist, int n) {
        int target = 8;
        int min = target;
        int num = 1;

        int i;
        for(path[0] = (double)target; target != 226; target = min) {
            for(i = 1; i <= 233; ++i) {
                double t = adj_matrix[target][i];
                if (t != 0.0D && dist[min] > dist[i]) {
                    min = i;
                }
            }

            path[num] = (double)min;
            ++num;
        }

        for(i = 232; i >= 0; --i) {
            if (path[i] != 0.0D) {
                System.out.println((int)path[i]);
            }
        }

        System.out.println("MINIMAL-COST PATH COSTS: " + (int)dist[8]);
    }

    void dijkstra(double[][] graph, int src) {
        double[] dist = new double[233];
        Boolean[] sptSet = new Boolean[233];

        int num;
        for(num = 0; num < 233; ++num) {
            dist[num] = 2.147483647E9D;
            sptSet[num] = false;
        }

        dist[src] = 0.0D;
        num = 0;

        int add;
        for(add = 0; add < 232; ++add) {
            int u = (int)this.minDistance(dist, sptSet);
            sptSet[u] = true;

            for(int v = 0; v < 233; ++v) {
                if (!sptSet[v] && graph[u][v] != 0.0D && dist[u] != 2.147483647E9D && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    hexFinal[num] = (double)u;
                    ++num;
                }
            }
        }

        add = ((Integer)map.get(position[0][15]) + (Integer)map.get(position[14][0])) / 2;
        dist[8] += (double)add;
        this.printSolution(dist, 233);
    }
}