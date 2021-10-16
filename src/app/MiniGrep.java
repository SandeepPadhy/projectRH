package app;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

/**
 * Linux mini-utils ================
 * 
 * "mini-grep" -----------
 * 
 * Usage:
 * 
 * ./mini-grep [-q] -e PATTERN [FILE...]
 * 
 * `mini-grep` goes through every argument in FILE and prints the whole line in
 * which PATTERN is found. By default `mini-grep` also outputs the line number
 * of each printed line.
 * 
 * - PATTERN has to be a valid regex - FILE can be zero or more arguments. If
 * zero args are given, `mini-grep` will parse entries from the standard input.
 * - If given, the `-q` options only outputs lines but omits the matching line
 * numbers.
 * 
 **/
public class MiniGrep {

	public void findPattern(String pattern, String... path) throws Exception {
		findPattern(true, pattern, path);
	}

	public void findPattern(boolean setnumber, String pattern, String... path) throws Exception {
		try {
			if (path.length == 0) {
				Scanner stdin = null;
				File tempFile = null;
				try {
					stdin = new Scanner(new BufferedInputStream(System.in));
					tempFile = File.createTempFile("mingrep", ".txt", new File(System.getProperty("user.dir")));
					try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
						String newline = "";
						while (!(newline = stdin.nextLine()).equals("")) {
							bw.write(newline);
							bw.write(System.lineSeparator());
						}
					}
					traverse(setnumber, pattern, tempFile , false);
				} catch (IOException ex) {
					System.out.println("Exception Occured::" + ex.getMessage());
					ex.printStackTrace();
				} finally {
					stdin.close();
					tempFile.deleteOnExit();
				}
			} else {
				for (String one : path) {
					File each = new File(one);
					traverse(setnumber, pattern, each, true);
				}
			}
		} catch (IOException ex) {
			System.out.println("Exception Occured::" + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void traverse(boolean setnumber, String pattern, File folder , boolean printfilename ) throws IOException {
		if (Files.notExists(folder.toPath()))
			throw new FileNotFoundException();
		if (folder.isFile()) {
			parseFile(setnumber, pattern, folder, printfilename);
		} else {
			for (File f : folder.listFiles()) {
				if (f.isDirectory()) {
					traverse(setnumber, pattern, f , printfilename);
				}
				if (f.isFile()) {
					parseFile(setnumber, pattern, f , printfilename);
				}

			}
		}

	}

	public void parseFile(boolean setnumber, String pattern, File f , boolean printfilename) throws IOException {
		int lineCount = 0;
		BufferedReader reader = null;
		if(f.canRead()) {
			try {
				reader = new BufferedReader(new FileReader(f));
				String currentLine = reader.readLine();
				while (currentLine != null) {
					lineCount++;
					if (currentLine.contains(pattern)) {
						if (printfilename) {
							System.out.println();
							System.out.println(f.getAbsolutePath());
							printfilename = false;
						}
						if (setnumber)
							System.out.print(lineCount + "  ");
						System.out.println(currentLine);
					}
					currentLine = reader.readLine();
				}
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else {
			System.out.println(f.getAbsolutePath()+" cant be read !!");
		}
	}

}
