package com.meng.mbrowser.history;

import android.content.*;
import android.widget.*;
import com.meng.mbrowser.tools.*;
import java.io.*;
import org.apache.http.util.*;

public class historyTool{
	Context context;
	public static final String historyFilePath="/data/data/com.meng.mbrowser/history.xml";
	String historyText="";
	xmlParser xmlparser;
	public historyTool(Context c) throws IOException{
		context=c;
		historyText=readTextFile();
		xmlparser=new xmlParser(historyTool.historyFilePath);
	}
	public void addHistory(String s){
		try{
			saveTextFile(historyText=insertText(readTextFile(),s));
			showToast(historyText);
		}catch(Exception e){
			showToast(e.toString());
		}
	}
	public String[] getHistory(){
		try{
			xmlparser.getXmlLength();
		return xmlparser.parseXml();
		}catch(Exception e){
			return new String[]{"读取出错",e.toString()};
		}
	}
	/*public void deleteHistory(int i){
		int line=0;
		int flag=historyText.indexOf("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<historys>\n");
		
		int index=historyText.indexOf("<history",flag);
		int end=historyText.indexOf("/>",flag)+2;
		if(i==line){
			historyText.
		}
		
	}*/
	public void cleanHistory(){
		historyText="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<historys>\n</historys>";
		saveTextFile(historyText);
	}

	private String readTextFile() throws IOException{
        File f = new File(historyFilePath);
		if(!f.exists()){
			f.createNewFile();
			saveTextFile("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<historys>\n</historys>");
		}
        String res = "";
		FileInputStream fin = new FileInputStream(historyFilePath);
		int length = fin.available();
		byte[] buffer = new byte[length];
		fin.read(buffer);
		res=EncodingUtils.getString(buffer,"UTF-8");
		fin.close();

        return res;
    }

    private String insertText(String fileText,String textToInsert)throws Exception{
        int index = fileText.indexOf("<historys>")+10;
        StringBuilder sb = new StringBuilder(fileText);
        sb.insert(index,"\n <history value=\""+textToInsert+"\" />");
        return sb.toString();
    }

    private boolean saveTextFile(String textToSave){
        try{
            FileOutputStream fout = new FileOutputStream(historyFilePath);
            byte[] bytes = textToSave.getBytes();
            fout.write(bytes);
            fout.close();
            return true;
        }catch(Exception e){
            return false;
        }
    }
	private void showToast(Object o){
		Toast.makeText(context,o.toString(),Toast.LENGTH_SHORT).show();
	}



}
