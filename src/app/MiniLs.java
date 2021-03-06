package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Date;
import java.util.Set;

/**
 * Linux mini-utils ================
 * 
 * "mini-ls" ---------
 * 
 * Usage
 * 
 * ./mini-ls [-r] [FILE...]
 * 
 * `mini-ls` lists information about the paths given in FILE. The information
 * required are: Owner, Permission, Modified Time.
 * 
 * - FILE can be zero or more arguments. If zero args are given, `mini-ls` will
 * list information about the current directory. - If given, the `-r` option
 * will make `mini-ls` run recursively on any directory it comes across.
 * 
 */
public class MiniLs {

	public void getFileList(boolean recursive, String... path) throws Exception {
		try {
			if (path.length == 0) {
				File current = new File(System.getProperty("user.dir"));
				System.out.println(current);
				list(recursive, current);
			} else {
				for (String one : path) {
					System.out.println(one);
					File each = new File(one);
					list(recursive, each);
				}
			}
		} catch (IOException ex) {
			System.out.println("Exception Occured::" + ex.getMessage());
			ex.printStackTrace();
		}
	}

	private void list(boolean recursive, File folder) throws IOException {
		if (Files.notExists(folder.toPath()))
			throw new FileNotFoundException();
		if (folder.isFile()) {
			printInfo(folder);
		} else {
			for (File f : folder.listFiles()) {
				if (f.isDirectory() && recursive) {
					System.out.println(f.getAbsolutePath());
					list(recursive, f);
				}
				if (f.isFile()) {
					printInfo(f);
				}
			}
		}
	}
	
	private void printInfo(File f) throws IOException {
		Date d = new Date(f.lastModified());
		String owner = Files.getOwner(f.toPath()).getName();
		String env = System.getProperty("os.name");
		if (!env.toLowerCase().contains("windows")) {
			String rwxFormPermissions = "---------";
			Set<PosixFilePermission> perm = Files.getPosixFilePermissions(Paths.get(f.getAbsolutePath()));
			rwxFormPermissions = PosixFilePermissions.toString(perm);
			System.out.println(rwxFormPermissions + "    " + owner + "   " + d + "   " + f.getName());
		} else {
			String perm = "";
			if (Files.isReadable(f.toPath()))
				perm += "r";
			if (Files.isWritable(f.toPath()))
				perm += "w";
			if (Files.isExecutable(f.toPath()))
				perm += "x";
			System.out.println(perm + "   " + owner + "   " + d + "   " + f.getName());
		}
	}
}
