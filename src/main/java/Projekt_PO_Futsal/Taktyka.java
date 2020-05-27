package Projekt_PO_Futsal;

import java.util.Scanner;

/**
 * Klasa Taktyka odpowiada za ustawienie taktyki ustawienia i taktyki poruszania wybranej przez uzytkownika.
 */
public class Taktyka {

	/** taktykaPoruszania to zmienna odpowiadajaca do przechowywanie taktyki poruszania zawodnikow, ktore wybral uzytkownik. */
	private int taktykaPoruszania=0;
	
	// Pozwala na wybór taktyki, odpowiedzialnej za automatyczne ustawienie
	/**
	 * taktykaUstawienia pozwala na wybor taktyki odpowiedzialnej za automatyczne ustawienie zawodnikow na boisku w zale¿nosci od ich statystyk.
	 *
	 * @param scan pobiera informacje z klawiatury.
	 * @return zwraca taktyke wybrana przez uzytkownika: ofensywna - 1, podania - 2 lub defensywna - 3.
	 */
	// zawodników na boisku w zale¿noœci od ich statystyk
	public int taktykaUstawienia(Scanner scan) {
		int taktyka;
		System.out.println("Jaka taktyka dla druzyny? 1-ofensywna, 2-podania, 3-defensywna");
		do
			taktyka = scan.nextInt();
		while (taktyka < 0 || taktyka > 3);
		return taktyka;
	}

	// Pozwala na wybór taktyki, któr¹ bêd¹ zawodnicy wykorzystywaæ poruszaj¹c siê
	/**
	 * taktykaPoruszania pozwala na wybor taktyki odpowiedzialnej za zachowania zawodnikow na boisku (jest to uwzglêdniane w LogiceZachowañ)
	 *
	 * @param scan pobiera informacje z klawiatury.
	 */
	// po boisku - np. ofensywna: duzo osob idzie atakowaæ;
	public void taktykaPoruszania(Scanner scan) {
		System.out.println("Jaka taktyka dla druzyny? 1-ofensywna, 2-zroznicowana, 3-defensywna");
		do
			taktykaPoruszania = scan.nextInt();
		while (taktykaPoruszania < 0 || taktykaPoruszania > 3);
	}
	
	/**
	 * Pobiera wartosc taktykaPoruszania do jej dalszego wykorzystania w LogiceZachowan	 *
	 * @return zwraca taktykePoruszania.
	 */
	public int getTaktykaPoruszania()
	{
		return taktykaPoruszania;
	}
	
}