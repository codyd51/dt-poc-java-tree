package com.phillipt.zipperdown;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.Scanner;

public class App {
	public static void main(String[] args) throws IOException {
		unzip_new();
		staticKey();

		// 02-0108 - Google Play Warning: Embedded AWS Credentials"
		System.out.println("Our AWS credential is " + "AKIAIOSFODNN7EXAMPLE");

		// 02-0123 - Google Play Security Warning: Hardcoded Google Oauth Refresh Token
		String oauthRefreshToken = "1/DGXUA3FmzHZnjEJDvX8ynqJCALJpo6ZT3IVNK6aBYHU";
		String refreshToken2 = "1/EeE4LhI8EVRj3F98wmlwFDFnIycu16EY2Gv8dDujT24";
		System.out.printf("OAuth refresh token: %s token2 %s%n", oauthRefreshToken, refreshToken2);

		// 00-0080 - Hardcoded Kony Password
		System.out.println("Kony@1234DXir4mzKhY7SuAVoTrjti7Sm is very sensitive");

		// 02-0085 - Sensitive Data Exposed via Ordered Broadcast
		/*
		String signaturePermission = "com.app.custom_permission.broadcastpermission";
		Intent intent = new Intent("Action name");
		intent.putExtra("PARAM", "Sensitive Info from Sender");
		sendOrderedBroadcast(intent, signaturePermission, null, null, 0, null, null);
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
			fos.close();
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
