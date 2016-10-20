package com.lingjoin.deepClassifier;

import java.util.Map;
import java.util.Map.Entry;

public class DeepClassifier {

	public static boolean state=false;
	
	public static boolean init(String arg,int nFeatureCount){
		state=CDeepClassifier.Instance.DC_Init(arg, 1, nFeatureCount, "");
		if(state)
			return true;
		else{
			System.out.println(CDeepClassifier.Instance.DC_GetLastErrorMsg());
			return false;
		}
	}
	
	public static Long newInstance(int nFeatureCount){
		return CDeepClassifier.Instance.DC_NewInstance(nFeatureCount);
	}
	
	public static int deleteInstance(Long handle){
		return CDeepClassifier.Instance.DC_DeleteInstance(handle);
	}
	
	public static boolean addTrain(String className,String text,Long handle){
		return CDeepClassifier.Instance.DC_AddTrain(className,text,handle);
	}
	
	public static boolean addTrainFile(String className,String fileName,Long handle){
		return CDeepClassifier.Instance.DC_AddTrain(className,fileName,handle);
	}

	public static void addTrainSet(Map<String,String> set,Long handle){
		for(Entry<String,String> entry:set.entrySet()){
			CDeepClassifier.Instance.DC_AddTrain(entry.getValue(), entry.getKey(), handle);
		}
	}
	
	public static boolean train(Long handle){
		return CDeepClassifier.Instance.DC_Train(handle);
	}
	
	public static boolean loadTrainResult(Long handle){
		return CDeepClassifier.Instance.DC_LoadTrainResult(handle);
	}
	
	public static String classify(String sText,Long handle){
		return CDeepClassifier.Instance.DC_Classify(sText, handle);
	}
	
	public static String classifyFile(String sFilename,Long handle){
		return CDeepClassifier.Instance.DC_ClassifyFile(sFilename, handle);
	}
	
	public static void exit(){
		CDeepClassifier.Instance.DC_Exit();
	}
	
	public static String DC_GetLastErrorMsg(){
		return CDeepClassifier.Instance.DC_GetLastErrorMsg();
	}
}
