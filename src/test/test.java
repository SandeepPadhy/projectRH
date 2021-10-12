package test;

import app.MiniDf;
import app.MiniLs;

public class test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		MiniDf m = new MiniDf();
		//human readable or not
		// file or directory
		// one or more path
//		m.getDiskInfo(true,"D:\\AA_LOGS\\Sample");
		
		MiniLs ls = new MiniLs();
		ls.getFileList(true, "D:\\newWork");
	}

}
