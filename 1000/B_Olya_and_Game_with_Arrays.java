import java.io.*;
import java.util.*;

public class B_Olya_and_Game_with_Arrays {
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
        while(n-->0) {
            int mini = Integer.MAX_VALUE, mini2 = Integer.MIN_VALUE;
            int k = nextInt();
            while(k-->0) {
                int x = nextInt();
                if(x<mini) {
                    mini2 = mini;
                    mini = x;
                }
                else if(x<mini2) {
                    mini2 = x;
                }
            }
            li.add(new Pair<>(mini, mini2));
        }
        
        int minMini = Integer.MAX_VALUE, minMini2 = Integer.MAX_VALUE;

        
        for(int i=0; i<li.size(); i++) {
            minMini = Math.min(minMini, li.get(i).x);
            minMini2 = Math.min(minMini2, li.get(i).y);
        }
        long sum = 0;

        for(int i=0; i<li.size(); i++) {
            if(li.get(i).x == minMini) {
                sum += li.get(i).x;
                // out.println("Added " + li.get(i).x);
                minMini = Integer.MAX_VALUE;
            }
            if(li.get(i).y != minMini2) {
                sum += li.get(i).y;
                // out.println("Added " + li.get(i).y);
            }
            else {
                minMini2 = Integer.MAX_VALUE;
            }
        }
        // out.println(li);
        // out.println(minMini + " " + minMini2);
        out.println(sum);

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

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}