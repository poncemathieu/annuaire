package com.annuaire.phonebook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PhoneBook {
	
	public static Scanner sc = null;
	public static String PHONE_BOOK_FILE_PATH = "/Users/mac/Documents/cours/JAVA/live-yt/phoneBook.txt";

	public static void main(String[] args) {
		
		sc = new Scanner(System.in);
		
		String userLastName     = getUserInput("Entrez un nom de famille : ");
		String userFirstName   = getUserInput("Entrez un prénom : ");
		String userPhoneNumber = getUserInput("Entrez un numéro de téléphone : ");
	
		Contact newContact = new Contact(userLastName, userFirstName, userPhoneNumber);
		 
		System.out.println(newContact.toString());
		
		File phoneBookFile = getOrCreatePhoneBookFile(PHONE_BOOK_FILE_PATH);
		appendContactPhoneBook(phoneBookFile, newContact);
		
		
		
		sc.close();
	}
	
	public static String getUserInput(String userRequest) {
		
		System.out.println(userRequest); 
		return sc.nextLine();
		
	}
	
	public static File getOrCreatePhoneBookFile(String phoneBookFilePath) {
		

		File phoneBookFile = new File(phoneBookFilePath);
		
		if(phoneBookFile.exists()) {
			return phoneBookFile;
		} 
			System.out.println("Le fichier n'existe pas (" + phoneBookFilePath + ")");
			
			try {
				phoneBookFile.createNewFile();
				System.out.println("Le fichier à été créé (" + phoneBookFilePath + ")");
				return phoneBookFile;
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
		return null;
	}

	public static void appendContactPhoneBook(File phoneBookFile, Contact newContact) {
		
		try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(phoneBookFile, true))){ 			
			
			fileWriter.append(newContact.toString());
			fileWriter.append(System.lineSeparator());
			
			System.out.println("Contact ajouté");
			
			
		} catch(IOException e) {
			
			e.printStackTrace();
			
		}
	}
}
