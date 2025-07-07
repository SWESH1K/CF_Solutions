import java.io.*;
import java.util.*;

public class B_Li_Hua_and_Pattern {
    // Fast I/O
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int T = nextInt(); // Number of test cases

        while (T-- > 0) {
            solve();
        }

        out.flush();
        out.close();
    }

    public static int[][] rotateMatrixBy180(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] rotated = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotated[rows - 1 - i][cols - 1 - j] = matrix[i][j];
            }
        }

        return rotated;
    }

    static void solve() throws IOException {
        // MainCode goes here
        int n = nextInt();
        int k = nextInt();

        
        int[][] matrix = new int[n][n];
        
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                matrix[i][j] = nextInt();
            }
        }
        if(n==1) {
            out.println("YES");
            return;
        }
        int[][] rotatedMatrix = rotateMatrixBy180(matrix);

        int counter = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(matrix[i][j]!=rotatedMatrix[i][j]) {
                    counter++;
                }
            }
        }

        // for(int[] row: matrix) {
        //     out.println(Arrays.toString(row));
        // }
        // out.println();
        // for(int[] row: rotatedMatrix) {
        //     out.println(Arrays.toString(row));
        // }
        // out.println();
        // out.println("Counter = " + counter);
        counter/=2;

        if(k<counter) {
            out.println("NO");
            return;
        }
        k-=counter;

        if((k&1)==1 && (n&1)==0) {
            out.println("NO");
        }
        else {
            out.println("YES");
        }
        
    }

    // -- Fast I/O helpers --
    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String line = br.readLine();
            if (line == null) return null;
            st = new StringTokenizer(line);
        }
        return st.nextToken();
    }
    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
    static long nextLong() throws IOException {
        return Long.parseLong(next());
    }
    static double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }
    static String nextLine() throws IOException {
        return br.readLine();
    }

    // -- Common utilities --
    static final long MOD = 1_000_000_007;

    static long modAdd(long a, long b, long mod) {
        a += b;
        if (a >= mod) a -= mod;
        return a;
    }

    static long modSub(long a, long b, long mod) {
        a -= b;
        if (a < 0) a += mod;
        return a;
    }

    static long modMul(long a, long b, long mod) {
        return (a * b) % mod;
    }

    static long modExp(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = modMul(res, base, mod);
            base = modMul(base, base, mod);
            exp >>= 1;
        }
        return res;
    }

    static long modInv(long a, long mod) {
        return modExp(a, mod - 2, mod);
    }

    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static boolean isInteger(double a) {
        return Math.ceil((float) a) == Math.floor((float) a);
    }

    static float logN(long a, long N) {
        return (float)(Math.log(a) / Math.log(N));
    }
}

final class Pair<T1, T2> {
    T1 x; T2 y;

    public Pair(T1 x, T2 y) {
        this.x = x;
        this.y = y;
    }

    public Pair(Pair<T1, T2> other) {
        this.x = other.x;
        this.y = other.y;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}