import java.io.*;
import java.util.*;

public class B_Divan_and_a_New_Project {
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

        List<Pair<Integer, Integer>> li = new ArrayList<>();

        List<Integer> values = new ArrayList<>();

        for(int i=0; i<n; i++) {
            li.add(new Pair<>(nextInt(), i));
        }

        Collections.sort(li);

        int k = (int)Math.ceil((float)n/2);
        for(int i=0; i<n; i++) {
            values.add(k);
            k--;
            if(k==0) k--;
        }

        Collections.sort(values, (a, b) -> {
            return -Integer.compare(Math.abs(a), Math.abs(b));
        });

        int ans[] = new int[n];
        int i = 0;
        // out.println(li);
        Iterator<Pair<Integer, Integer>> ite = li.iterator();
        long total = 0;
        while(ite.hasNext()) {
            var cur = ite.next();
            ans[cur.y] = values.get(i);
            i++;
            total += 2L*Math.abs(ans[cur.y])*cur.x;
        }
        out.println(total);
        out.print(0 + " ");
        for(int v:ans) {
            out.print(v + " ");
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

final class Pair<T1, T2> implements Comparable<Pair<T1, T2>> {
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
    public int compareTo(Pair<T1, T2> other) {
        return Integer.compare((int)this.x, (int)other.x);
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}