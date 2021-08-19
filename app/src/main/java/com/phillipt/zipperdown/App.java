package com.phillipt.zipperdown;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import com.phillipt.zipperdown.KeyStore;
import java.util.Scanner;

public class App {
	public static void main(String[] args) throws IOException {
		unzip_new();
		staticKey();
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

	private static void staticKey() {
		KeyStore ks = KeyStore.getInstance();
		// Unsafe call - statically embedded password
		ks.load("StaticPassword");
		// Safe call - password determined at runtime
		Scanner scanner = new Scanner(System.in);
		ks.load(scanner.nextLine());
		scanner.close();
	}
}
