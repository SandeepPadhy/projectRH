package app;

import java.io.File;
import java.io.IOException;

/**
Linux mini-utils
================

"mini-df"
---------

Usage:

    ./mini-df [-h] [PATH...]

`mini-df` outputs the file system disk space usage of each entry in
PATH. The information required is: Total Space, Free Space, Used
Space. The result should be in Bytes.

- PATH can be zero or more arguments. IF zero args are given,
  `mini-df` will list the disk space usage of the current directory.
- If given `-h` will output the result in human-readable format.

*/

public class MiniDf {
	
	public void getDiskInfo(boolean humanReadable , String ... path ) throws Exception {
		try {
			if(path.length == 0) {
				File current = new File(System.getProperty("user.dir"));
				System.out.println(current);
				System.out.println("Total Space Available: "+ humanReadble(humanReadable, getTotalSpace(current)));
				System.out.println("Used Space Available: "+ humanReadble(humanReadable, getUsedSpace(current)));
				System.out.println();
			}else {
				
				for(String one : path) {
					File each = new File(one);
					if(each.isDirectory()) {
						System.out.println(each.getAbsolutePath());
						System.out.println("Total Space: "+ humanReadble(humanReadable, getTotalSpace(each)));
						System.out.println("Used Space: "+ humanReadble(humanReadable, getUsedSpace(each)));
						System.out.println();
					}else {
						System.out.println(each+" is not a Directory !!");
					}
				}
				
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}
				
	}
	
	private long getUsedSpace(File dir) throws IOException {
	      long length = 0;
	      File[] files = dir.listFiles();
	      if (files != null) {
	          for (File file : files) {
	              if (file.isFile())
	                  length += file.length();
	              else
	                  length += getUsedSpace(file);
	          }
	      }
	      return length;
	}

	private long getTotalSpace(File file) {
		return file.getTotalSpace();
	}
	
	private String humanReadble(boolean humanReadable, Long size) {
		double newSize = size;
		if(humanReadable)
			if(newSize/1024/1024 > 999)
				return String.format("%.2f",newSize/1024/1024/1024)+"TB";
			else if(newSize/1024 > 999)
				return String.format("%.2f",newSize/1024/1024)+"GB";
			else if(newSize > 999)
				return String.format("%.2f",newSize/1024)+"KB";
			else
				return String.valueOf(newSize)+"B";
		else
			return String.valueOf(newSize);
	}
}
