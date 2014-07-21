
public final class myClass {

    private int varA;
    private double varB;
    private boolean x;
    
    public boolean equals(Object y)
    {
        if (y == this) return true;  // are the references pointing to the same instance?
        
        if (y== null) return false; // is y non null?
        
        if (y.getClass() != this.getClass()) return false;  // is y the same of the same class?
        
        myClass that = (myClass) y;  // cast to actual type.
        
        if (this.varA != that.varA) return false;  //  check all significant fields are the same.
        if (this.varB != that.varB) return false;
        if (this.x != that.x) return false;
        
        return true;         
    }
}
