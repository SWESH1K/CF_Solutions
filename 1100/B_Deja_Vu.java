import java.io.*;
import java.util.*;

public class B_Deja_Vu {
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

    static void solve() throws IOException {
        // MainCode goes here
        int n = nextInt();
        int m = nextInt();
        List<Pair<Long, Integer>> li = new ArrayList<>();
        for(int i=0; i<n; i++) {
            long x = nextLong();
            li.add(new Pair<>(x, i));
        }

        Collections.sort(li, (a, b) -> {
            return Long.compare(a.x, b.x);
        });

        long brr[] = new long[m];
        for(int i=0; i<m; i++) {
            brr[i] = nextLong();
        }
        long mini = 31;
        for(int i=0; i<m;i++) {
            for(int j=n-1; j>=0; j--) {
                if(brr[i]>=mini || li.get(j).x<(1<<brr[i])) break;
                if((li.get(j).x&((1<<brr[i]))-1) == 0) {
                    li.get(j).x += (1<<(brr[i]-1));
                }
            }
            mini = Math.min(mini, brr[i]);
            // out.println(li);
        }

        long[] arr = new long[n];
        for(var e: li) {
            arr[e.y] = e.x;
        }

        for(int i=0; i<n; i++) {
            out.print(arr[i] + " ");
        }
        out.println();
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