import java.util.Comparator;

public class myComparator {
    private Comparator<T> COMPNAME = new CompName();
    
    // INNER CLASSES

    private final class  CompName implements Comparator<T> {

        public int compare(final T n1, final T n2) {
            int priority1 = n1.priorityValues();
            int priority2 = n2.priorityValues();

            if (priority1 < priority2)
                return -1;
            else if (priority1 == priority2)
                return 0;
            else
                return 1;
        }

        @Override
        public boolean equals(final Object a) {
            String msg = "Not Implemented. Do NOT use this method.";
            throw new java.lang.UnsupportedOperationException(msg);            
        }
    };

}
