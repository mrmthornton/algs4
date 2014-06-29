
public class testPerc {
	private static final int N = 10;
	public static void main(String[] args) {
		Percolation perc = new Percolation(N);
		
		for (int i=1; i<=N;i++) {
			for (int j=1; j<=N; j++) {
				System.out.print( perc.isOpen(i, j) + "  " );
			}
			System.out.println();
		}
		for (int i=1; i<=N;i++) {
			for (int j=1; j<=N; j++) {
				perc.open(i, j);
				System.out.print( perc.isOpen(i, j) + "  " );
			}
			System.out.println();
		}

	}

}
