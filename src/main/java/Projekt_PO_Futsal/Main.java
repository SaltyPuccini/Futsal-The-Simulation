package Projekt_PO_Futsal;


import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class Main {

	public static void main(String[] args) throws IOException {

		Scanner scan = new Scanner(System.in);									// Scaner do wpisywania danych z klawiatury
		Pilka pilka = new Pilka(7, true); 										// pi�ka meczowa
		GenerujBoisko boisko = new GenerujBoisko(); 							// boisko meczowe
		LogikaZachowan grajcie = new LogikaZachowan(); 							// zachowanie pi�karzy
																				//
		System.out.println("XD");																		//
																				// Wywo�anie wszystkich potrzebnych rzeczy do stworzenia Dru�yny true
																				//
		KreatorDruzyny bayernMonachium = new KreatorDruzyny(0, 0, 0, 0, true); 	// wywo�anie dru�yny i konstruktor - wa�ne ustawia stron� dru�yny - true
																				// 
		Taktyka hansiFlick = new Taktyka(); 									// tworzenie taktyki
		Bramkarz neuer = new Bramkarz(1, 1, true, 0); 							// tworzenie bramkarza
		bayernMonachium.ustawJakLiczne(scan); 									// u�ytkowanik okre�la ile pi�karzy ma dru�yna true
		bayernMonachium.jakiePozycje(scan); 									// u�ytkownik okre�la ilu napastnik�w, ilu pomocnik�w i ilu obro�c�w ma dru�yna true
																				// 
		int jakliczne = bayernMonachium.getJakLiczne(); 						//
		Zawodnik[] bawarczycy = new Zawodnik[jakliczne]; 						// stworzenie tablicy zawodnik�w
		System.out.println("Statystyka musi zawiera� si� w przedziale (0,99>"); //
		bayernMonachium.wygenerujZawodnikow(bawarczycy, scan, bayernMonachium); // zape�nienie tej tablicy zawodnikami, ustawienie im statystyk z klawiatury i przypisanie ich do dru�yny
																				// 
																				// 
		boisko.formacjaNaBoisku(bayernMonachium, hansiFlick, bawarczycy, scan); // posortowanie tablicy pi�karzy po statystykach okre�lonych w taktyce
																				// 
		boisko.postawienieNaMurawie(bayernMonachium, bawarczycy); 				// przypisanie pozycji i stref zawodnikom
		bayernMonachium.ustawBramkarzy(neuer, scan, bayernMonachium); 			// u�ytkownik wpisuje statystyki bramkarza
																				//
																				//
																				// Wywo�anie wszystkich potrzebnych rzeczy do stworzenia Dru�yny false
																				//
		KreatorDruzyny barcelona = new KreatorDruzyny(0, 0, 0, 0, false); 		// wywo�anie dru�yny ikonstruktor - wa�ne ustawia stron� dru�yny - false
																				// 
		Taktyka ernestoValverde = new Taktyka(); 								// tworzenie taktyki	
		Bramkarz mats = new Bramkarz(1, 1, false, 0); 							// tworzenie bramkarza
		barcelona.ustawJakLiczne(scan); 										// u�ytkowanik okre�la ile pi�karzy ma dru�yna false
		barcelona.jakiePozycje(scan); 											// u�ytkownik okre�la ilu napastnik�w, ilu pomocnik�w i ilu obro�c�w ma dru�yna false
																				// 
		int jakliczne2 = barcelona.getJakLiczne(); 								//
		Zawodnik[] katalonczycy = new Zawodnik[jakliczne2]; 					// stworzenie tablicy zawodnik�w
		System.out.println("Statystyka musi zawiera� si� w przedziale (0,99>"); //
		barcelona.wygenerujZawodnikow(katalonczycy, scan, barcelona); 			// zape�nienie tej tablicy zawodnikami, ustawienie im statystyk z klawiatury i przypisanie ich do dru�yny
																				// 
																				// 
		boisko.formacjaNaBoisku(barcelona, ernestoValverde, katalonczycy, scan);// posortowanie tablicy pi�karzy po statystykach okre�lonych w taktyce
																				// 
																				// 
		boisko.postawienieNaMurawie(barcelona, katalonczycy); 					// przypisanie pozycji i stref zawodnikom
		barcelona.ustawBramkarzy(mats, scan, barcelona); 						// u�ytkownik wpisuje statystyki bramkarza
																				//
		for (int i = 0; i < bayernMonachium.getJakLiczne(); i++) 				//
			boisko.zajmijStrefe(bawarczycy[i]); 								// Zaj�cie stref w tablicy, przechowuj�cej informacje o
																				// tym, kt�re strefy s� zaj�te
		for (int i = 0; i < barcelona.getJakLiczne(); i++)					    //
			boisko.zajmijStrefe(katalonczycy[i]); 								// Zaj�cie stref w tablicy, przechowuj�cej informacje o
																				// tym, kt�re strefy s� zaj�te
		System.out.println("Do ilu bramek gramy?");								//
		int doIlu=scan.nextInt();
		Random r = new Random();
		
		while(mats.getGolePrzeciwnika()<doIlu&&neuer.getGolePrzeciwnika()<doIlu) {
			for (int i = 0; i < bayernMonachium.getJakLiczne(); i++)
				grajcie.priorytety(bawarczycy[i], mats, boisko, pilka, r);
			for (int i = 0; i < barcelona.getJakLiczne(); i++)
				grajcie.priorytety(katalonczycy[i], neuer, boisko, pilka, r);
		}
		scan.close();
	}

}