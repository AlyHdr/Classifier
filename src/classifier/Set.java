/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classifier;

import java.util.ArrayList;


public class Set {

    private String clas;
    private ArrayList<Double> attributes;
    public Set(String clas,ArrayList<Double> attributes) {
        this.clas=clas;
        this.attributes=attributes;
    }
    public Set(ArrayList<Double> attributes)
    {
        this.attributes=attributes;
    }
    public ArrayList<Double> getAttributes()
    {
        return this.attributes;
    }
    public String getClas()
    {
        return this.clas;
    }
    
}
