import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.StringTokenizer;

/*

- n > 1
- a[0] * a[1] * ... * a[k-1] = n
- each a[i] > 1
- a[i+1] is divisible by a[i] (must be in increasing order)
- k is max possible
- duplicates are possible

prime factorization is the key

 */

public class AuthorSol {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			long n = fs.nextLong();
			ArrayList<Pair> arr = new ArrayList<>();
			for (long i = 2; i * i <= n; i++) {
				int counter = 0;
				while (n % i == 0) {
					counter++;
					n /= i;
				}
				if (counter > 0) {
					arr.add(new Pair(counter, i));
				}
			}
			if (n > 1) {
				//this means n is a prime number
				arr.add(new Pair(1, n));
			}
			Collections.sort(arr);
			//get the prime factor with the highest frequency
			ArrayList<Long> ans = new ArrayList<>(arr.get(0).first);
			for (int i = 0; i < arr.get(0).first; i++) {
				ans.add(arr.get(0).second);
			}
			for (int i = 1; i < arr.size(); i++) {
				for (int j = 0; j < arr.get(i).first; j++) {
					ans.set(ans.size()-1, ans.get(ans.size()-1) * arr.get(i).second);
				}
			}
			System.out.println(ans.size());
			for (long x : ans) {
				System.out.print(x + " ");
			}
			System.out.println();
		}
		out.close();
	}
	
	static class Pair implements Comparable<Pair> {
		int first;
		long second;
		
		Pair(int first, long second) {
			this.first = first;
			this.second = second;
		}

		@Override
		public int compareTo(Main.Pair o) {
			return Integer.compare(o.first, this.first);
		}
	}
		
	static final Random rnd = new Random();
	
	static void shuffleSort(int[] a) { //change this
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int newInd = rnd.nextInt(n);
			int temp = a[newInd]; //change this
			a[newInd] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
