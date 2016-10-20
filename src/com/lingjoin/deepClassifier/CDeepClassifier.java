package com.lingjoin.deepClassifier;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface CDeepClassifier extends Library {

	CDeepClassifier Instance=(CDeepClassifier) Native.loadLibrary("DeepClassifier", CDeepClassifier.class);
	
	public boolean DC_Init(String sDataPath,int encode,int nFeatureCount,String sLicenceCode);
	
	public Long DC_NewInstance(int nFeatureCount);
	
	public int DC_DeleteInstance(Long handle);
	
	public boolean DC_AddTrain(String sClassName,String sText,Long handle);
	
	public boolean DC_AddTrainFile(String sClassName,String sFilename,Long handle);
	
	public boolean DC_Train(Long handle);
	
	public boolean DC_LoadTrainResult(Long handle);
	
	public String DC_Classify(String sText,Long handle);
	
	public String  DC_ClassifyFile(String sFilename,Long handle);
	
	public String DC_GetLastErrorMsg();
	
	public void DC_Exit();
}
