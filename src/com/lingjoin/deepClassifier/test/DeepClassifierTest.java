package com.lingjoin.deepClassifier.test;

import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.lingjoin.deepClassifier.DeepClassifier;

public class DeepClassifierTest {

    List<String> files=new ArrayList<String>();
	
	public void getAllfiles(File filePath){
		File[] fsFiles=filePath.listFiles();
		for(File f:fsFiles){
			if(f.isFile()&&!f.getName().equals(".DS_Store")) files.add(f.getPath());
			if(f.isDirectory()) this.getAllfiles(f);
		}
	}
	
	public String getContent(File file)throws Exception{
		RandomAccessFile f=new RandomAccessFile(file, "r");
		byte[] b=new byte[(int) file.length()];
		f.read(b);
		f.close();
		String c=new String(b,"GBK").replaceAll("\\s", "");
		return c;
	}
	public static void main(String[] args) throws Exception{
		DeepClassifier.init("lib", 800);
		//训练
		Long handle=DeepClassifier.newInstance(800);
		String fPath="data_train";
		DeepClassifierTest test=new DeepClassifierTest();
		test.getAllfiles(new File(fPath));
		for(String f:test.files){
			System.out.println(f);
			String con=test.getContent(new File(f));
			String[] cls=new File(f).getParent().split("/");
			String cl=cls[cls.length-1];
			String name=new File(f).getParent();
			System.out.println(name.substring(name.lastIndexOf(File.separator)+1));
			DeepClassifier.addTrain(cl, con, handle);
			//fw.write(new File(f).getName()+"\t"+NLPIR.getNewWords(con, 10, true)+"\t"+con+"\n");
		}
		DeepClassifier.train(handle);
		
		//分类
		DeepClassifier.loadTrainResult(handle);
		String path="data_train";
		DeepClassifierTest classifier=new DeepClassifierTest();
		classifier.getAllfiles(new File(path));
		FileWriter fw=new FileWriter(new File("classes.txt"));
		for(String f:classifier.files){
			System.out.println(f);
			String con=classifier.getContent(new File(f));
			fw.write(new File(f).getName()+"\t"+DeepClassifier.classify(con, handle)+"\t"+con+"\n");
		}
		fw.flush();
		fw.close();
	}
}
