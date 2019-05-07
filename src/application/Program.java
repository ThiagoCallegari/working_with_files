package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Product> list = new ArrayList<>();
		
		System.out.print("Enter file path: ");
		String path = sc.nextLine();
		File file = new File(path);
		String folder = file.getParent() + "\\out";
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			boolean succes = new File(folder).mkdir();
			
			String line = br.readLine();
			while (line != null) {
			String[] fields = line.split(", ");
			String name = fields[0];
			double price = Double.parseDouble(fields[1]);
			int quantity = Integer.parseInt(fields[2]);
			
			list.add(new Product(name, price, quantity));
			line = br.readLine();
			}
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(folder + "\\sumary.csv"))) {
				for (Product prod : list) {
					bw.write(prod.toString());
					bw.newLine();
				}
			}
			catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		finally {
			if (sc != null) {
				sc.close();
			}
		}
	}
}