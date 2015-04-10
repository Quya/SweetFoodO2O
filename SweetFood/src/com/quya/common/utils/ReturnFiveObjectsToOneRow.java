package com.quya.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.quya.model.ObjectLine;

public class ReturnFiveObjectsToOneRow {
	public static List<ObjectLine> findObjects(List<?>objList){
		
		List<ObjectLine>lineList=new ArrayList<>();
		int seatsNum=objList.size();
		for(int i=0,p=0;i<=seatsNum/5;i++){
			ObjectLine objectLine=new ObjectLine();
			for(int j=1;p<seatsNum;j++,p++){
				if(j%6!=0){
					objectLine.getObjects().add(objList.get(p));
				}else{
					break;
				}
			}
			lineList.add(objectLine);
		}
		return lineList;		
	}
}
