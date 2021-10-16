package test;

import app.MiniDf;
import app.MiniGrep;
import app.MiniLs;

public class TestSuite {

	/*
	 * Have Covered mostly Positive test cases
	 */
	public static void main(String[] args) throws Exception {
		
		boolean enableMiniDfTestCase = true;
		boolean enableMiniLsTestCase = true;
		boolean enableMiniGrepTestCase = true;
		String currDir = System.getProperty("user.dir");
		String linesepartor = System.getProperty("line.separator");
		
		if(enableMiniDfTestCase) {
			MiniDf df = new MiniDf();
			String dfFile1 = currDir+"/src/testdata/folder2/pattern1.txt";
			String dfFolder1 = currDir+"/src/testdata/folder1";
			String dfFolder2 = currDir+"/src/testdata/folder2";	
			
			System.out.println(linesepartor+">>MiniDf-Test1-human readable Off");
			df.getDiskInfo(false, dfFolder1);
			// human readable On - (not testing GB due to project size)
			System.out.println(linesepartor+">>MiniDf-Test2-human readable On");
			df.getDiskInfo(true, dfFolder1);
			
			System.out.println(linesepartor+">>MiniDf-Test3-zero args -> current directory");
			df.getDiskInfo(false);
			
			System.out.println(linesepartor+">>MiniDf-Test4-more directories");
			df.getDiskInfo(true, dfFolder1 , dfFolder2);
			
			System.out.println(linesepartor+">>MiniDf-Test5-file");
			df.getDiskInfo(true, dfFile1);
		}

		
		if(enableMiniLsTestCase) {
			MiniLs ls = new MiniLs();
			String lsFile1 = currDir+"/src/testdata/folder2/pattern1.txt";
			String lsFile2 = currDir+"/src/testdata/folder2/pattern2.txt";
			String lsFolder1 = currDir+"/src/testdata";
			String lsFolder2 = currDir+"/src/testdata/folder2";
			
			System.out.println(linesepartor+">>MiniLs-Test1-no file -> current directory");
			ls.getFileList(false);

			System.out.println(linesepartor+">>MiniLs-Test2-one file");
			ls.getFileList(false,lsFile1);

			System.out.println(linesepartor+">>MiniLs-Test3-more files");
			ls.getFileList(false,lsFile2);

			System.out.println(linesepartor+">>MiniLs-Test4-directory");
			ls.getFileList(false,lsFolder2);

			System.out.println(linesepartor+">>MiniLs-Test5-recursive directory");
			ls.getFileList(true,lsFolder1);			
		}
		
		
		if(enableMiniGrepTestCase) {
			MiniGrep grep = new MiniGrep();
			String grepFile1 = currDir+"/src/testdata/folder2/pattern1.txt";
			String grepFile2 = currDir+"/src/testdata/folder2/pattern2.txt";
			String grepFolder1 = currDir+"/src/testdata/folder2";
			
			System.out.println(linesepartor+">>MiniGrep-Test1-default line number");
			grep.findPattern("SANDEEP", grepFile1);
			
			System.out.println(linesepartor+">>MiniGrep-Test2-without line number");
			grep.findPattern(false,"SANDEEP", grepFile1);
			
			System.out.println(linesepartor+">>MiniGrep-Test3-more than one file");
			grep.findPattern("SANDEEP", grepFile1, grepFile2);
			
			System.out.println(linesepartor+">>MiniGrep-Test4-directory");
			grep.findPattern("SANDEEP", grepFolder1);
			 
			/* 
			 * To test standard input
			 * Insert multiple lines of String one after another without blank lines.
			 * After inserting data from standard input, Please press enter(2x) to insert a blank line.
			 * Inserting blank line indicates input end
			 */
			System.out.println(linesepartor+">>MiniGrep-Test5-no file -> standard input");
			grep.findPattern("SANDEEP");
		}
		
	}

}
