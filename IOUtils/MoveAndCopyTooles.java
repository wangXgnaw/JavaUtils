package io.tooles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MoveAndCopyTooles {
	//Privatization constructor
	private MoveAndCopyTooles() {
	}

	// Copy files to folders
	public static void copyFile2Dir(File srcFile, File destDir) throws IOException {
		if (!srcFile.isFile()) {
			return;
		}
		destDir.mkdirs();
		File f = new File(destDir, srcFile.getName());

		if (f.exists()) {
			copyAndRenameFile(f);

		} else {
			FileInputStream fis = new FileInputStream(srcFile);
			FileOutputStream fos = new FileOutputStream(f);

			byte[] b = new byte[1024];
			int len;

			while ((len = fis.read(b)) != -1) {
				fos.write(b, 0, len);
				fos.flush();
			}

			fos.close();
			fis.close();
		}
	}

	// Copy to the current folder, and rename, plus a copy
	private static void copyAndRenameFile(File srcFile) throws IOException {
		File f = srcFile;
		do {
			String name = f.getName();
			String[] split = name.split("[.]");
			String strName = "";
			for (int i = 0; i < split.length - 1; i++) {
				strName += split[i];
			}
			strName = strName + "-copy." + split[split.length - 1];

			f = new File(srcFile.getParentFile(), strName);
			// System.out.println(f.getName());
		} while (f.exists());

		FileInputStream fis = new FileInputStream(srcFile);
		FileOutputStream fos = new FileOutputStream(f);
		byte[] b = new byte[1024];
		int len;
		while ((len = fis.read(b)) != -1) {
			fos.write(b, 0, len);
		}
		fos.close();
		fis.close();

	}

	// Move files to folders
	public static void moveFile2Dir(File srcFile, File destDir) throws IOException {
		if (!srcFile.isFile()) {
			return;
		}
		destDir.mkdirs();
		File f = new File(destDir, srcFile.getName());

		if (f.exists()) {
			copyAndRenameFile(f);

		} else {
			FileInputStream fis = new FileInputStream(srcFile);
			FileOutputStream fos = new FileOutputStream(f);

			byte[] b = new byte[1024];
			int len;

			while ((len = fis.read(b)) != -1) {
				fos.write(b, 0, len);
				fos.flush();
			}

			fos.close();
			fis.close();
		}
		srcFile.delete();
	}

	//Copy folders to folders
	public static void copyDir2Dir(File srcDir,File destDir) throws IOException{
		if (!srcDir.isDirectory()) {
			return;
		}
		destDir.mkdirs();
		File[] listFiles = srcDir.listFiles();
		for (File file : listFiles) {
			if (file.isDirectory()) {
				File f=new File(destDir,file.getName());
				copyDir2Dir(file, f);
			}else {
				//File f=new File(destDir,file.getName());
				copyFile2Dir(file, destDir);
			}
		}
	}
	
	//Delete files or folders
	public static void delDirOrFile(File src){
		if (!src.exists()) {
			System.out.println("The source file does not exist");
			return;
		}
		if (src.isFile()) {
			src.delete();
			return;
		}
		
		File[] listFiles = src.listFiles();
		for (File file : listFiles) {
			if (file.isDirectory()) {
				delDirOrFile(file);
				file.delete();
			}else {
				file.delete();
			}
		}
		src.delete();
	}
	
	
}
