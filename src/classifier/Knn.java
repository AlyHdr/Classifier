/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classifier;

/**
 *
 * @author Ali Haidar
 */
public class Knn implements Comparable{
    String className;
    double value;
    public Knn(String className,double value)
    {
        this.className=className;
        this.value=value;
    }

    @Override
    public int compareTo(Object t) {
        int comp=(int)this.value-(int)((Knn)t).value;
        return comp;
    }
    @Override
    public boolean equals(Object o)
    {
        if(((Knn)o).className.equals(this.className))
            return true;
        return false;
    }
}
