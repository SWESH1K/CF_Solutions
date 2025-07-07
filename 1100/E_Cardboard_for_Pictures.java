import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class E_Cardboard_for_Pictures {
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

    static long findSquareSum(long[] arr) {
        long sum = 0;
        for(long num: arr) {
            sum += (num)*(num);
        }

        return sum;
    }

    static void solve() throws IOException {
        // MainCode goes here
        int n = nextInt();
        long c = nextLong();

        long[] arr = new long[n];
        long sum = 0;
        for(int i=0; i<n; i++) {
            arr[i] = nextLong();
            sum += arr[i];
        }

        long squareSum = findSquareSum(arr);

        // long ans = (-sum + (long)Math.sqrt((sum*sum) - (n*(squareSum-c))))/n;

        // double discriminant = ((double)sum) * sum - ((double)n) * (squareSum - c);
        // long ans = (long)((long)(-sum + Math.sqrt(discriminant)) / n);

        BigInteger bigSum = BigInteger.valueOf(sum);
        BigInteger bigN = BigInteger.valueOf(n);
        BigInteger bigSquareSum = BigInteger.valueOf(squareSum);
        BigInteger bigC = BigInteger.valueOf(c);

        // Compute: sum^2
        BigInteger sumSq = bigSum.multiply(bigSum);

        // Compute: n*(squareSum - c)
        BigInteger rhs = bigN.multiply(bigSquareSum.subtract(bigC));

        // Discriminant
        BigInteger discriminant = sumSq.subtract(rhs);

        // sqrt with BigInteger: use double
        double sqrtDiscriminant = Math.sqrt(discriminant.doubleValue());

        // Final answer
        long ans = (-sum + (long)sqrtDiscriminant) / n;


        out.println(ans/2);
        
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