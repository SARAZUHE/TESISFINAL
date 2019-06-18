/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id3lefever;

/**
 *
 * @author SARA
 * //CODIGO IMPLEMENTACION FORMULAS DE TESIS aja si chido 
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import id3LefTree.DecisionTree;
import weka.core.Instances;

import id3LefTools.Classifier;
import id3LefTools.ManagerDataSet;
import id3LefTools.Id3Lef;
import id3LefTools.ManagerDisplay;
public class ID3Lefever {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try {
			
			//FIXME INSERT HERE THE NEW DATA SET
			BufferedReader b = new BufferedReader(new FileReader("C:\\Users\\SARA\\Documents\\NetBeansProjects\\ID3Lefever\\src\\id3LefData\\7_HCVL_1.arff"));
			Instances dataSet;//\\data
			dataSet = new Instances(b);			
			dataSet.setClassIndex(dataSet.numAttributes()-1);
			
			dataSet=ManagerDataSet.discretize(dataSet);
			Instances[]split=ManagerDataSet.split(dataSet);		
			
			DecisionTree id3_tree=Id3Lef.decisionTreeLearner(split[0],null,null);	
			
			ManagerDisplay.displayTree(id3_tree);
			
			double error_trainingSet=Classifier.classifyTestingSet(split[0], id3_tree);
                        double error_testingSet=Classifier.classifyTestingSet(split[1], id3_tree);
			System.out.println("Training Set error: "+Math.round( error_trainingSet* Math.pow( 10, 2 ) )/Math.pow( 10, 2 )+"%");
			System.out.println("Testing Set error: "+Math.round( error_testingSet*100* Math.pow( 10, 2 ) )/Math.pow( 10, 2 )+"%");
			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
        
    }   
}
