package com.grj.javatest.io.filestream;

import java.io.*;

public class FileStreamTest {

	public static void main(String args[]) {
		try {
			byte bWrite[] = { 49, 50, 51, 52, 53 };
			OutputStream os = new FileOutputStream("src/main/java/com/grj/javatest/io/filestream/test.txt");
			for (int x = 0; x < bWrite.length; x++) {
				os.write(bWrite[x]); // writes the bytes
			}
			os.close();

			InputStream is = new FileInputStream("src/main/java/com/grj/javatest/io/filestream/test.txt");
			int size = is.available();

			for (int i = 0; i < size; i++) {
				System.out.print((char) is.read() + "  ");
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.print("Exception");
		}
	}
}
