package contest;
import java.io.*;
import java.util.*;

import Pair;

public class E_MEX_Count {
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

        int[] freq = new int[n+1];

        for(int i=0; i<n; i++) {
            freq[nextInt()]++;
        }
        int suffix[] = new int[n+2];
        for(int i=n; i>=0; i--) {
            suffix[i] = suffix[i+1] + freq[i];
        }
        // out.println(Arrays.toString(suffix));
        int[] ans = new int[n+2];
        // Arrays.fill(ans, 1);

        int prefix=0;
        for(int i=0; i<=n; i++) {
            // out.println("At i=" + i + ", freq[i]=" + freq[i] + ", prefix=" + prefix + ", suffix=" + suffix[i+1]);
            int mini = freq[i];
            int maxi = Math.max(0, freq[i] + prefix - i + suffix[i+1]);
            // out.println("Maxi=" + maxi + " Mini=" + mini);
            ans[mini] += 1;
            ans[maxi+1] -= 1;
            prefix += freq[i];
            if(freq[i]==0) break;
        }
        // out.println(Arrays.toString(ans));
        out.print(ans[0] + " ");
        for(int i=1; i<=n; i++) {
            ans[i] += ans[i-1];
            out.print(ans[i] + " ");
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