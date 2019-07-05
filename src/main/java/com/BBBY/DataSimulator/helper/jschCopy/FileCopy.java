package com.BBBY.DataSimulator.helper.jschCopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy {

	public static void copyFiles(String fileLocationSource, String fileLocationDestination, int numberOfFilesToCopy) {
		File inputLocation = new File(fileLocationSource);
		if (inputLocation.isDirectory()) {
			System.out.println("Listing the files...");
			File[] attachmentFiles = inputLocation.listFiles();
			System.out.println("Total files in the folder: " + attachmentFiles.length);
			for (File aFile : attachmentFiles) {
				if (!aFile.isDirectory()) {
					String fileName = aFile.getName();
					String sourceFileName = aFile.getAbsolutePath();
					String destinationFileName = fileLocationDestination + fileName;
					copyFile(sourceFileName, destinationFileName);
				}
				if (numberOfFilesToCopy >= 0) {
					if (--numberOfFilesToCopy == 0) {
						break;
					}
				}
			}
		}
		System.out.println("Completed...");
	}

	/**
	 *
	 * @param sourceFileName
	 * @param destionFileName
	 *
	 *            Copies a single file from source location to a destination
	 *            location.
	 */
	public static void copyFile(String sourceFileName, String destionFileName) {
		try {
			System.out.println("Reading..." + sourceFileName);
			File sourceFile = new File(sourceFileName);
			File destinationFile = new File(destionFileName);
			InputStream in = new FileInputStream(sourceFile);
			OutputStream out = new FileOutputStream(destinationFile);

			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.close();
			System.out.println("Copied: " + sourceFileName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
