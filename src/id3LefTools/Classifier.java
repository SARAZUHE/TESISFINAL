/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id3LefTools;

import java.util.Iterator;
import id3LefTree.DecisionTree;
import weka.core.*;

/**
 *
 * @author SARA
 */
public class Classifier {
    
    public static double classifyTestingSet(Instances testSet,DecisionTree tree){
		double error=0;
		for(int i=0;i<testSet.numInstances();i++){
			
			if(classifyInstance(testSet,testSet.instance(i),tree)==false){
				error++;
			}
		}
		return error/testSet.numInstances();
	}
	private static boolean classifyInstance(Instances testSet,Instance instance,DecisionTree tree ){
	
		
		Attribute A=testSet.attribute(tree.getAttribute());
		while(A!=null){
			String value=instance.stringValue(A);
			if(instance.isMissing(A))
				value=ManagerDataSet.majorityElement(testSet, A);		
			tree=chooseChild(tree,value);
			A=testSet.attribute(tree.getAttribute());
			
		}
		if(tree.getResult()==instance.stringValue(instance.classAttribute()))
			return true;
		else
			return false;
	}
	private static DecisionTree chooseChild(DecisionTree tree,String value){
		Iterator<DecisionTree>itr=tree.getChildrens().iterator();
		while(itr.hasNext()){
			DecisionTree node=itr.next();
			if(value==node.getEdge())
				return node;
		}
	
		return null;
	}
    
}
