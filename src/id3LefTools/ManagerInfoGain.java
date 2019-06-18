/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id3LefTools;

import java.util.Enumeration;
import weka.core.Attribute;
import weka.core.Instances;

/**
 *
 * @author SARA
 */
public class ManagerInfoGain {
    
    private static double calc_entropy(Instances dataSet){
		double entropy=0;
		Enumeration en=dataSet.classAttribute().enumerateValues();
		while(en.hasMoreElements()){
			String v=(String)en.nextElement();
			double occ=ManagerDataSet.occurrence(dataSet, v,dataSet.classAttribute()) ;
			if(occ!=0){			
				entropy-= (occ/dataSet.numInstances())*(Math.log(occ/dataSet.numInstances()));				
			}
		}
		return entropy;
	}
    
	public static double calc_infoGain(Instances dataSet, Attribute A){
                double gain = 0;
                double infoGain = calc_entropy(dataSet);
		double infoGainA = calc_entropy(dataSet);
		Enumeration en=A.enumerateValues();
		while(en.hasMoreElements()){
			String v=(String)en.nextElement();
			Instances v_dataSet=new Instances(dataSet,dataSet.numInstances());
			for(int i=0;i<dataSet.numInstances();i++){
				if(dataSet.instance(i).stringValue(A)==v)
					v_dataSet.add(dataSet.instance(i));
				if(dataSet.instance(i).isMissing(A)&&v==ManagerDataSet.majorityElement(dataSet, A))
					v_dataSet.add(dataSet.instance(i));
			}
			v_dataSet.compactify();
			infoGainA-=ManagerDataSet.occurrence(dataSet, v, A)/dataSet.numInstances()*calc_entropy(v_dataSet);
                        gain = infoGainA-infoGain; 
		}
		return gain;
	}
    
}
