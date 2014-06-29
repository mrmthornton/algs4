
public class testStats {
	public static void main(String[] args) {

		for (int i=95; i<=100;i++) {
			for (int j=45; j<=50; j++) {
				
				PercolationStats stats = new PercolationStats(i, j);
				
				System.out.println("std dev = " + stats.stddev());
			    double mu =  stats.mean();
				System.out.println("CI lo  = " + stats.confidenceLo());
				System.out.println("mean = " + mu);
				System.out.println("CI hi  = " + stats.confidenceHi());
				System.out.println( i + " " + j);
			}
		}
	}
}
