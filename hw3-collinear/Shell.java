import java.util.Comparator;

public class Shell
{
    public static void sort(Comparable[] a)
    {
        int N = a.length;

        int h = 1;
        while (h < N/3) h= 3*h + 1;

        while (h >=1)
        {  // h-sort the array
            for (int i=h; i<N; i++)
            {
                for (int j=i; j>=h && less(a[j], a[j-h]); j-=h )
                    exch(a, j, j-h);
            }

            h = h/3;
        }
    }

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // is v < w ?
    private static boolean less(Comparator c, Object v, Object w) {
        return (c.compare(v, w) < 0);
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // exchange a[i] and a[j]  (for indirect sort)
    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        String[] strarr = {"the", "fat", "cat", "runs", "over", "the", "dog","while","The","mailman","drives","around"};
        Shell.sort(strarr);
        for(String s : strarr)
            System.out.print(s + " ");
    }

}
