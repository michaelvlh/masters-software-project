package com.team12.foodforall;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Random;

/**
 * @author: Heng Gao
 * @date: 02/05/2022 17:34
 **/
public class test {
	public static void main(String[] args) {
		// test1();
		// test2();
		// test3();
		// testRandom();
		// float price = 2.55f;
		// // price = (float) (price * 100)/100 ;
		// System.out.println((float) Math.round(price * 13 *100) / 100);

		//  double d = 114.145;
		// System.out.println(String.format("%.2f", d));

		double price = (double) Math.round(new Random().nextFloat() * 2500) / 100 ;
		System.out.println(price);
	}

	private static void testRandom() {
		final Random random = new Random();
		for (int i = 0; i < 100; i++) {
			System.out.println(random.nextInt(5));
		}
	}


	public static void test1() {
		try {
			File resource = new ClassPathResource("/static/Foodforall.jpeg").getFile();
			String text = new String(Files.readAllBytes(resource.toPath()));

			System.out.println(resource.getAbsolutePath());
			// String content = new String(Files.readAllBytes(file.toPath()));
			// System.out.println(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void test2() {
		Path path = Paths.get(".");
		System.out.println(path.toAbsolutePath());

		String s1 = "a";

		System.out.println(String.format("a word: {0}", s1));
		System.out.println("a word: " + s1);
	}

	public static void test3() {
		try {

			File resource = new ClassPathResource("/static/Foodforall.jpeg").getFile();
			byte[] img = Files.readAllBytes(resource.toPath());
			String imgBase64 = Base64.getEncoder().encodeToString(img);
			// project.setImg(imgBase64); // img in String
			System.out.println(imgBase64);
		}catch (IOException e){
			e.printStackTrace();
		}

	}
}
