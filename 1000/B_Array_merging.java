import java.io.*;
import java.util.*;

public class B_Array_merging {
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
        int curr=0, curr_count=0;
        Map<Integer, Pair<Integer, Integer>> map = new HashMap<>();

        for(int i=0; i<n; i++) {
            if(i==0) {
                curr = nextInt();
                curr_count = 1;
                continue;
            }
            int x = nextInt();
            if(x==curr) {
                curr_count++;
            }
            else {
                var currentEntry = map.getOrDefault(curr, new Pair<>(0, 0));
                currentEntry.x = Math.max(currentEntry.x, curr_count);
                map.put(curr, currentEntry);
                curr = x;
                curr_count=1;
            }
        }
        var currentEntry = map.getOrDefault(curr, new Pair<>(0, 0));
        currentEntry.x = Math.max(currentEntry.x, curr_count);
        map.put(curr, currentEntry);
        for(int i=0; i<n; i++) {
            if(i==0) {
                curr = nextInt();
                curr_count = 1;
                continue;
            }
            int x = nextInt();
            if(x==curr) {
                curr_count++;
            }
            else {
                currentEntry = map.getOrDefault(curr, new Pair<>(0, 0));
                currentEntry.y = Math.max(currentEntry.y, curr_count);
                map.put(curr, currentEntry);
                curr = x;
                curr_count=1;
            }
        }
        currentEntry = map.getOrDefault(curr, new Pair<>(0, 0));
        currentEntry.y = Math.max(currentEntry.y, curr_count);
        map.put(curr, currentEntry);
        // out.println(map);

        List<Map.Entry<Integer, Pair<Integer, Integer>>> li = new ArrayList<>(map.entrySet());
        Collections.sort(li, (a, b) -> {
            Pair<Integer, Integer> aValue = a.getValue();
            Pair<Integer, Integer> bValue = b.getValue();
            long sumA = (long)aValue.x + aValue.y;
            long sumB = (long)bValue.x + bValue.y;

            return -Long.compare(sumA, sumB);
        });

        long ans=(long)li.get(0).getValue().x + li.get(0).getValue().y;
        out.println(ans);
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
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}