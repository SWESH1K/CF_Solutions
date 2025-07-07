import java.io.*;
import java.util.*;

public class B_Sort_the_Subarray {
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
        int n = nextInt();
        int[] a = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n; i++) a[i] = nextInt();
        for (int i = 0; i < n; i++) b[i] = nextInt();

        // Find the length of longest already sorted segment in a[]
        int maxSorted = 0, curr = 0;
        for (int i = 0; i < n; i++) {
            curr++;
            if (i == n - 1 || a[i] > a[i + 1]) {
                maxSorted = Math.max(maxSorted, curr);
                curr = 0;
            }
        }

        // Find the longest sorted segment in b[] that differs from a[]
        int start = -1, end = -1, tempStart = -1;
        boolean inSegment = false, changed = false;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            boolean last = (i == n - 1);
            boolean sorted = last || b[i] <= b[i + 1];

            if (!inSegment && sorted) {
                inSegment = true;
                tempStart = i;
                changed = a[i] != b[i];
            } else if (inSegment) {
                if (a[i] != b[i]) changed = true;
                if (!sorted || last) {
                    int tempEnd = last && sorted ? i : i;
                    int len = tempEnd - tempStart + 1;

                    if (changed && len >= maxLen) {
                        start = tempStart;
                        end = tempEnd;
                        maxLen = len;
                    }
                    inSegment = false;
                }
            }
        }

        if (start == -1) {
            out.println(maxSorted);
        } else {
            out.println((start + 1) + " " + (end + 1));
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