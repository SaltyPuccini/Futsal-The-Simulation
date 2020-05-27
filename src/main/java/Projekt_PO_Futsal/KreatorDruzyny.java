package Projekt_PO_Futsal;

import java.util.Scanner;

/**
 * Klasa KreatorDruzyny tworzy druzyny tj. zawodnikow oraz bramkarzy.
 */
public class KreatorDruzyny {

	/** napad przechowuje informacje o ilosci napastnikow w dru¿ynie. */
	private int napad;

	/** pomoc przechowuje informacje o ilosci pomocnikow. */
	private int pomoc;

	/** obrona przechowuje informacje o ilosci obroncow. */
	private int obrona;

	/** jakLiczne przechowuje informacje o liczebnosci druzyny. */
	private int jakLiczne;

	/**
	 * identyfikatorDruzyny przechowuje wartosc true - druzyna z lewej strony
	 * boiska, false - druzyna z prawej strony boiska.
	 */
	private boolean identyfikatorDruzyny;

	/**
	 * Konstruktor klasy KreatorDruzyny.
	 *
	 * @param napad                ustawia napad
	 * @param pomoc                ustawia pomoc
	 * @param obrona               ustawia obrona
	 * @param jakLiczne            ustawia jakliczne
	 * @param identyfikatorDruzyny ustawia identyfikatorDruzyny
	 */
	KreatorDruzyny(int napad, int pomoc, int obrona, int jakLiczne, boolean identyfikatorDruzyny) {
		this.napad = napad;
		this.pomoc = pomoc;
		this.obrona = obrona;
		this.jakLiczne = jakLiczne;
		this.identyfikatorDruzyny = identyfikatorDruzyny;
	}

	/**
	 * UstawJakLiczne pobiera informacje o liczebnosci druzyny "z palca".
	 *
	 * @param scan pobiera informacje z klawiatury.
	 */
	public void ustawJakLiczne(Scanner scan) {
		System.out.println("Podaj liczebnoœæ dru¿yny " + identyfikatorDruzyny + "(miêdzy 3 a 6 zawodników)");
		do
			this.jakLiczne = scan.nextInt();
		while (jakLiczne < 3 || jakLiczne > 6);
	}

	/**
	 * ustawBramkarzy pozwala na wpisanie statystyk bramkarza.
	 *
	 * @param bramkarz to obiekt klasy Bramkarz, ktoremu beda ustawiane statystyki.
	 * @param scan     pobiera informacje z klawiatury.
	 * @param druzyna  okresla do ktorej druzyny nalezy bramkarz, ktoremu wpisywane
	 *                 sa statystyki.
	 */
	public void ustawBramkarzy(Bramkarz bramkarz, Scanner scan, KreatorDruzyny druzyna) {
		System.out.println("Bramkarz druzyny" + getIdentyfikatorDruzyny());
		bramkarz.wpiszPodanie(scan);
		bramkarz.wpiszUmkiBramkarskie(scan);
		bramkarz.setDruzyna(druzyna.getIdentyfikatorDruzyny());
	}

	/**
	 * jakiePozycje pozwala na wpisanie ilu zawodnikow ma znajdowaæ siê na
	 * okreœlonych pozychja(napastnikow, pomocnikow, obroncow) w danej druzynie.
	 * Zabezpiecza rowniez przed podaniem nieprawidlowych danych.
	 *
	 * @param scan pobiera informacje z klawiatury.
	 */
	public void jakiePozycje(Scanner scan) {
		do {
			System.out.println("Ilu spoœród zawodników ma byæ napastnikiem");
			do
				napad = scan.nextInt();
			while (napad > jakLiczne || napad < 0);
			System.out.println("pomocnikiem");
			do
				pomoc = scan.nextInt();
			while (pomoc > jakLiczne - napad || pomoc < 0);
			System.out.println("obroñc¹");
			do
				obrona = scan.nextInt();
			while (obrona > jakLiczne - napad - pomoc || obrona < 0);
		} while (napad + pomoc + obrona != jakLiczne);
	}

	/**
	 * wygenerujZawdonikow tworzy zawodnikow w tablicy z statystykami podanymmi
	 * wczesniej przez uzytkownika.
	 *
	 * @param team    to tablica, w ktorej generowani sa zawodnicy.
	 * @param scan    pobiera informacje z klawiatury.
	 * @param druzyna przechowuje informacje o liczebnoœci dru¿yny
	 */
	public void wygenerujZawodnikow(Zawodnik[] team, Scanner scan, KreatorDruzyny druzyna) {
		int ile = druzyna.getJakLiczne();
		for (int i = 0; i < ile; i++) {
			team[i] = new Zawodnik(1, 1, 1, 1, false, 'x', false);
			System.out.println("Zawodnik nr." + (i + 1) + " dru¿yny: " + druzyna.getIdentyfikatorDruzyny());
			team[i].wpiszStrzal(scan);
			team[i].wpiszPodanie(scan);
			team[i].wpiszObrona(scan);
			team[i].setDruzyna(druzyna.getIdentyfikatorDruzyny());
		}
	}

	/**
	 * Pobiera informacje o ilosci napastnikow do uzycia w innych klasach.
	 *
	 * @return zwraca ilosc napastniow.
	 */
	public int getNapad() {
		return napad;
	}

	/**
	 * Pobiera informacje o ilosci pomocnikow do uzycia w innych klasach.
	 *
	 * @return zwraca ilosc pomocnikow.
	 */
	public int getPomoc() {
		return pomoc;
	}

	/**
	 * Pobiera informacje o ilosci obroncow do uzycia w innych klasach.
	 *
	 * @return zwraca ilosc obroncow.F
	 */
	public int getObrona() {
		return obrona;
	}

	/**
	 * Pobiera informacje o liczebnosci druzyn w innych klasach.
	 *
	 * @return zwraca liczebnosc druzyny.
	 */
	public int getJakLiczne() {
		return jakLiczne;
	}

	/**
	 * Pobiera identyfikator druzyny (true/false).
	 *
	 * @return zwraca identyfiaktor druzyny (true/false).
	 */
	public boolean getIdentyfikatorDruzyny() {
		return identyfikatorDruzyny;
	}

	/**
	 * Ustawia identyfiaktor druzyny.
	 *
	 * @param identyfikatorDruzyny the new identyfikator druzyny
	 */
	public void setIdentyfikatorDruzyny(boolean identyfikatorDruzyny) {
		this.identyfikatorDruzyny = identyfikatorDruzyny;
	}

}