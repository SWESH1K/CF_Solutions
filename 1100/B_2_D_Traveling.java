import java.io.*;
import java.util.*;

public class B_2_D_Traveling {
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
        int k = nextInt();
        int a = nextInt()-1;
        int b = nextInt()-1;

        List<Pair<Long, Long>> cords = new ArrayList<>();

        for(int i=0; i<n; i++) {
            cords.add(new Pair<>(nextLong(), nextLong()));
        }

        Pair<Long, Long> start = cords.get(a);
        Pair<Long, Long> end = cords.get(b);

        long startMini = Long.MAX_VALUE, endMini = Long.MAX_VALUE;

        for(int i=0; i<k; i++) {
            long curr = (long)Math.abs(start.x - cords.get(i).x) +  Math.abs(start.y - cords.get(i).y);
            startMini = Math.min(startMini, curr);
        }
        for(int i=0; i<k; i++) {
            long curr = (long)Math.abs(end.x - cords.get(i).x) +  Math.abs(end.y - cords.get(i).y);
            endMini = Math.min(endMini, curr);
        }
        
        long direct = (long)Math.abs(end.x - start.x) +  Math.abs(end.y - start.y); 
        long ans = (k!=0)? Math.min(startMini+endMini, direct):direct;
        out.println(ans);

        // out.println(start + ", " + end);
        
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