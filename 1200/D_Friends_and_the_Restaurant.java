import java.io.*;
import java.util.*;

public class D_Friends_and_the_Restaurant {
    // Fast I/O
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

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

        int[] arr = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = nextInt();
        }

        for(int i=0; i<n; i++) {
            arr[i] = nextInt() - arr[i];
        }
        Debugger.log( "arr",arr);

        TreeMap<Integer, Integer> posMap = new TreeMap<>();
        TreeMap<Integer, Integer> negMap = new TreeMap<>();
        int ans = 0;
        for(int i=0; i<n; i++) {
            if(arr[i]>=0) {
                posMap.put(arr[i], posMap.getOrDefault(arr[i], 0)+1);
            }
            else {
                negMap.put(arr[i], negMap.getOrDefault(arr[i], 0)+1);
            }
        }

        while (!negMap.isEmpty() && !posMap.isEmpty()) { 
            int mini = negMap.firstKey();
            int maxi = posMap.lastKey();
            if(-mini > maxi) {
                negMap.remove(mini);
                continue;
            }

            int miniVal = negMap.get(mini);
            if(miniVal == 1) {
                negMap.remove(mini);
            }
            else {
                negMap.put(mini, miniVal-1);
            }
            
            int maxiVal = posMap.get(maxi);
            if(maxiVal == 1) {
                posMap.remove(maxi);
            }
            else {
                posMap.put(maxi, maxiVal-1);
            }


            ans++;
        }

        int rem = 0;
        for(int num: posMap.values()) {
            rem += num;
        }
        ans += rem/2;

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

    final class Debugger {
        public static final boolean DEBUG = System.getProperty("ONLINE_JUDGE") != null;

        public static void log(Object... vars) {
            if (DEBUG) return;

            StringBuilder sb = new StringBuilder();
            sb.append("[Debugger] ");

            for (int i = 0; i < vars.length; i++) {
                Object var = vars[i];
                sb.append(prettyPrint(var));
                if (i != vars.length - 1) sb.append(" = ");
            }

            System.err.println(sb);
        }

        private static String prettyPrint(Object var) {
            if (var == null) return "null";

            Class<?> clazz = var.getClass();

            if (clazz.isArray()) {
                return arrayToString(var);
            } else if (var instanceof Collection) {
                return collectionToString((Collection<?>) var);
            } else if (var instanceof Map) {
                return mapToString((Map<?, ?>) var);
            } else {
                return String.valueOf(var);
            }
        }

        private static String arrayToString(Object arr) {
            if (arr instanceof int[]) return Arrays.toString((int[]) arr);
            if (arr instanceof long[]) return Arrays.toString((long[]) arr);
            if (arr instanceof double[]) return Arrays.toString((double[]) arr);
            if (arr instanceof char[]) return Arrays.toString((char[]) arr);
            if (arr instanceof boolean[]) return Arrays.toString((boolean[]) arr);
            if (arr instanceof Object[]) return Arrays.toString((Object[]) arr);
            return arr.toString();
        }

        private static String collectionToString(Collection<?> col) {
            return col.toString();
        }

        private static String mapToString(Map<?, ?> map) {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            Iterator<? extends Map.Entry<?, ?>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<?, ?> e = it.next();
                sb.append(e.getKey()).append("=").append(e.getValue());
                if (it.hasNext()) sb.append(", ");
            }
            sb.append("}");
            return sb.toString();
        }
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