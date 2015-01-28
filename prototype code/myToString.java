
public class myToString {
    
    // string representation of the board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int val = (int)tiles[i][j];
                s.append(String.format("%2d ", val));
            }
            s.append("\n");
        }
        return s.toString();
    }
}
