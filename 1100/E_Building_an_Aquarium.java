import java.io.*;
import java.util.*;

public class E_Building_an_Aquarium {
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

    static int BinarySearch(int[] arr, int target) {
        int ans = -1;
        int low = 0, high = arr.length-1;
        while(low<=high) {
            int mid = (low+high)/2;

            if(arr[mid]<target) {
                low = mid+1;
            }
            else {
                ans = mid;
                high = mid-1;
            }
        }
        // if(arr[ans]==target) ans++;
        return ans;
    }

    static long findTotal(int[] arr, long mid) {
        long total = 0;
        for(int num: arr) {
            total += Math.max(0, mid-num);
        }
        return total;
    }

    static void solve() throws IOException {
        // MainCode goes here
        int n = nextInt();
        int x = nextInt();

        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = nextInt();
        }

        Arrays.sort(arr);
        long total = 0, height = 0;
        long low = arr[0], high = 2*(long)(1e9);

        while(low<=high) {
            long mid = (low+high)/2;
            // out.println("Searched for " + i + ", found " + find);
            total = findTotal(arr, mid);
            if(total <= x) {
                height = mid;
                low = mid+1;
            }
            else {  
                high = mid-1;
            }

        }

        out.println(height);
        
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