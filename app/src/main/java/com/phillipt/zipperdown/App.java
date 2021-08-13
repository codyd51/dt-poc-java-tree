package com.phillipt.zipperdown;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class App {
	public static void main(String[] args) throws IOException {
		unzip();
	}

	private static void unzip() throws IOException {
		/*
		ZipInputStream zis = new ZipInputStream(new FileInputStream("./poc.zip"));
		ZipEntry ent = zis.getNextEntry();
		while (ent != null) {
			String name = ent.getName();
			System.out.println("Name: " + name);
			FileOutputStream fos = new FileOutputStream(name);
			byte[] bytes = new byte[128];
			int cnt = zis.read(bytes);
			while (cnt != -1) {
				fos.write(bytes, 0, cnt);
				cnt = zis.read(bytes);
			}
			ent = zis.getNextEntry();
		}
		zis.closeEntry();
		zis.close();
		*/
	}

	private static void unzip_new() throws IOException {
		ZipInputStream zis = new ZipInputStream(new FileInputStream("./poc.zip"));
		ZipEntry ent = zis.getNextEntry();
		while (ent != null) {
			String name = ent.getName();
			System.out.println("Name: " + name);
			FileOutputStream fos = new FileOutputStream(name);
			byte[] bytes = new byte[128];
			int cnt = zis.read(bytes);
			while (cnt != -1) {
				fos.write(bytes, 0, cnt);
				cnt = zis.read(bytes);
			}
			ent = zis.getNextEntry();
		}
		zis.closeEntry();
		zis.close();
	}
}

/*
	ZipEntry is used
	FileOutputStream is used with the output of ZipEntry.getName()
	ZipEntry.getCanonicalName() is not used
*/
