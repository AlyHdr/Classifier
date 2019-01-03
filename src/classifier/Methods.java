/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import javax.swing.JFrame;

public class Methods {
    public Methods()
    {
    }
    public String distance(Set set,ArrayList<Set> trainings)
    {
        return KNN(set, trainings, 1);
    }
    public String Normalization(Set s,ArrayList<Set> trainings)
    {
        ArrayList<Double> selectedNorms =new ArrayList<>();
        ArrayList<Double> minimums=new ArrayList<>();
        ArrayList<Double> maxmimums=new ArrayList<>();
        for(int i=0;i<trainings.get(0).getAttributes().size();i++)
        {
            minimums.add(minNorm(trainings, i));
            maxmimums.add(maxNorm(trainings, i));
        }
        //value-min/max-min
        for(int i=0;i<s.getAttributes().size();i++)
        {
            double value=(s.getAttributes().get(i)-minimums.get(i))/(maxmimums.get(i)-minimums.get(i));
            selectedNorms.add(value);
        }
        double min=100;
        String minClass="";
        for(Set tr:trainings)
        {
            double value=0;
            for(int i=0;i<tr.getAttributes().size();i++)
            {
                double x=(tr.getAttributes().get(i)-minimums.get(i))/(maxmimums.get(i)-minimums.get(i));
                value+=Math.abs(x-selectedNorms.get(i));
            }
            if(value<min)
            {
                minClass=tr.getClas();
                min=value;
            }
        }
        minClass=minClass.trim();
        return minClass;
    }
    public String MSS(Set s,ArrayList<Set> trainings)
    {
        ArrayList<Double> selectedMSS=new ArrayList<>();
        double median=0;
        double ASD=0;
        ArrayList<Double> medians=new ArrayList<>();
        ArrayList<Double> ASDs=new ArrayList<>();
        for(int i=0;i<s.getAttributes().size();i++)
        {
            median=getMedian(trainings, i);
            ASD=getAbsStandardDeviation(trainings, i);
            medians.add(median);
            ASDs.add(ASD);
            selectedMSS.add((s.getAttributes().get(i)-median)/ASD);
        }
        double min=1000;
        String minClass="";
        for(Set tr:trainings)
        {
            double value=0;
            for(int i=0;i<tr.getAttributes().size();i++)
            {
                median=medians.get(i);
                ASD=ASDs.get(i);
                double x=(tr.getAttributes().get(i)-median)/ASD;
                value+=Math.abs(selectedMSS.get(i)-x);
            }
            if(value<min)
            {
                min=value;
                minClass=tr.getClas();
            }
        }
        minClass=minClass.trim();
        return minClass;
    }
    public String KNN(Set s,ArrayList<Set> trainings,int k)
    {
        ArrayList<Knn> Knn_values=new ArrayList<>();
        for(Set tr:trainings)
        {
            double value=0;
            for(int i=0;i<tr.getAttributes().size();i++)
                value+=Math.abs(tr.getAttributes().get(i)-s.getAttributes().get(i));
            Knn_values.add(new Knn(tr.getClas(),value));
        }
        Collections.sort(Knn_values);
        String maxClass="";
        if(k==1)
            maxClass=Knn_values.get(0).className;
        else {
            for (int i = 0; i < k; i++) {
                String selected = Knn_values.get(i).className;
                selected = selected.trim();
                int count = 0;
                int max = 0;
                for (Knn kn : Knn_values) {
                    if (selected.equals(kn.className.trim())) {
                        count++;
                    }
                }
                if (count > max) {
                    maxClass = selected;
                    max = count;
                }
            }
        }
        maxClass=maxClass.trim();
        return maxClass;
    }
    public double minNorm(ArrayList<Set> trainings,int index)
    {
        double min=trainings.get(0).getAttributes().get(index);
        for(Set s:trainings)
        {
            if(s.getAttributes().get(index)<min)
                min=s.getAttributes().get(index);
        }
        return min;
    }
    public double maxNorm(ArrayList<Set> trainings,int index)
    {
        double max=trainings.get(0).getAttributes().get(index);
        for(Set s:trainings)
        {
            if(s.getAttributes().get(index)>max)
                max=s.getAttributes().get(index);
        }
        return max;
    }
    public double getMedian(ArrayList<Set> trainings,int index)
    {
        ArrayList<Double> values=new ArrayList<>();
        for(Set tr:trainings)
        {
            values.add(tr.getAttributes().get(index));
        }
        Collections.sort(values);
        double median=0;
        int i=values.size()/2;
        if(values.size()%2==0)
            median=(values.get(i)+values.get(i-1))/2;
        else
            median=values.get(i);
        return median;
    }
    public double getAbsStandardDeviation(ArrayList<Set> trainings,int index)
    {
        double ASD=0;
        double median=getMedian(trainings, index);
        for(Set tr:trainings)
            ASD+=(Math.abs(tr.getAttributes().get(index)-median));
        ASD=ASD/trainings.size();
        return ASD;
    }
    //standard deviation
    //average 
    //mathmatical op
    //max between all groups
    public String Bayes(Set s,ArrayList<Set> trainings)
    {
        ArrayList<String> groupsClasses=new ArrayList<>();
        for(Set tr:trainings)
        {
            if(!groupsClasses.contains(tr.getClas()))
                groupsClasses.add(tr.getClas());
        }
        double max=0;
        String maxClass="";
        for(String clas:groupsClasses)
        {
            double v=makeGroups(s, trainings, clas);
            if(v>max)
            {
                max=v;
                maxClass=clas;
            }
        }
        maxClass=maxClass.trim();
        return maxClass;
    }
    public double makeGroups(Set s,ArrayList<Set> trainings,String className)
    {
        className.trim();
        ArrayList<Set> group=new ArrayList<>();
        for(Set tr:trainings)
        {
            String trClass=tr.getClas().trim();
            if(trClass.equals(className))
            {
                group.add(new Set(trClass,tr.getAttributes()));
            }
        }
        double result=bayesValue(s,group);
        return result;
    }
    public double getAverage(ArrayList<Set> group,int index)
    {
        double count=0;
        for(Set st:group)
        {
            count+=st.getAttributes().get(index);
        }
        return count/group.size();
    }
    public double SD(ArrayList<Set> group,double average,int index)
    {
        double values=0;
        for(Set st:group)
        {
            values+=Math.pow(st.getAttributes().get(index)-average,2);
        }
        return Math.sqrt(values/group.size());
    }
    public double bayesValue(Set s,ArrayList<Set> group)
    {
        double res=1;
        for (int i = 0; i < group.get(0).getAttributes().size(); i++) {
            double average = getAverage(group, i);
            double sd = SD(group, average, i);
            double exp = Math.exp(-Math.pow(s.getAttributes().get(i) - average, 2) / (2 * sd * sd));
            double base = Math.sqrt(2 * Math.PI) * sd;
            res =res*(exp / base);
        }
        return res;
    }
}
