
public class TestDeque2 {

    public static void main(String[] args) {
        Deque<String>dek = new Deque<String>();

        

        
        
         for (int i=0; i<5;i++) {
             dek.addFirst("F");
             dek.addLast("L");
             if(i%5==0) { System.out.print("."); }
         }
         
         for ( String s : dek) {
             if (dek.iterator().hasNext()) {
                 System.out.print(s + " ");
             }
         }
    }
}
