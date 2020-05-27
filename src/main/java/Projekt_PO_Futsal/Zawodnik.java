package Projekt_PO_Futsal;

import java.util.*;
import java.util.Scanner;

/**
 * Klasa Zawodnik wykonuje rozne akcje, ktore leza w jego kompetencjach.
 */
public class Zawodnik extends Podanie {

	/** strzal przechowuje informacje o statystyce strzalu zawodnika. */
	private int strzal;

	/** podanie przechowuje informacje o statystyce podan zawodnika. */
	private int podanie;

	/** obrona przechowuje informacje o statystyce obrony zawodnika. */
	private int obrona;

	/**
	 * strefa przechowuje informacje o tym w ktorej strefie zawodnik sie znajduje.
	 */
	private int strefa;

	/**
	 * czyMamPilke sprawdza czy zawowdnik ma pilke: true jesli ma, false przeciwnie.
	 */
	private boolean czyMamPilke;

	/** rola przetrzymuje informacje o roli zawodnika */
	private char rola;

	/**
	 * druzyna przetrzymuje true - zawodnik nalezy do druzyny po lewej stronie
	 * boiska, lub false - zawodnik nalezy do druzyny po prawej stronie boiska.
	 */
	private boolean druzyna;

	/**
	 * Konstruktor klasy Zawdonik
	 *
	 * @param strzal      ustawia strzal
	 * @param podanie     ustawia podanie
	 * @param obrona      ustawia obrone
	 * @param strefa      ustawia strefe
	 * @param czyMamPilke ustawia czyMamPilke
	 * @param rola        ustawia role
	 * @param druzyna     ustawia druzyne
	 */
	Zawodnik(int strzal, int podanie, int obrona, int strefa, boolean czyMamPilke, char rola, boolean druzyna) {
		this.strefa = strefa;
		this.strzal = strzal;
		this.podanie = podanie;
		this.obrona = obrona;
		this.czyMamPilke = czyMamPilke;
		this.rola = rola;
		this.druzyna = druzyna;
	}

	/**
	 * wpiszStrzal pozwala uzytkownikowi na wpisanie statystyki strzalu jednegmu z
	 * zawodnikow.
	 *
	 * @param scan pobiera informacje z klawiatury.
	 */
	public void wpiszStrzal(Scanner scan) {
		System.out.println("Podaj statystyke strza³u zawodnika");
		do
			this.strzal = scan.nextInt();
		while (strzal > 99 || strzal < 0);
	}

	/**
	 * wpiszPodanie pozwala uzytkownikowi na wpisanie statystyki podania jednemu z
	 * zawodnikow.
	 *
	 * @param scan pobiera informacje z klawiatury.
	 */
	public void wpiszPodanie(Scanner scan) {
		System.out.println("Podaj statystyke podania zawodnika");
		do
			this.podanie = scan.nextInt();
		while (podanie > 99 || podanie < 0);
	}

	/**
	 * wpiszObrona pozwala uzytkownikowi na wpisanie statystyki obrony jednemu z
	 * zawodnikow.
	 *
	 * @param scan pobiera informacje z klawiatury.
	 */
	public void wpiszObrona(Scanner scan) {
		System.out.println("Podaj statystyke obrony zawodnika");
		do
			this.obrona = scan.nextInt();
		while (obrona > 99 || obrona < 0);
	}

	/**
	 * Ustawia strefe zawodnikowi.
	 *
	 * @param strefa the new strefa
	 */
	public void setStrefa(int strefa) {
		this.strefa = strefa;
	}

	/**
	 * Ustawia strzal zawodnikowi.
	 *
	 * @param strzal the new strzal
	 */
	public void setStrzal(int strzal) {
		this.strzal = strzal;
	}

	/**
	 * Ustawia obrone zawodnikowi.
	 *
	 * @param obrona the new obrona
	 */
	public void setObrona(int obrona) {
		this.obrona = obrona;
	}

	/**
	 * Ustawia role zawodnikowi.
	 *
	 * @param rola the new rola
	 */
	public void setRola(char rola) {
		this.rola = rola;
	}

	/**
	 * Ustawia druzyne zawodnikowi.
	 *
	 * @param druzyna the new druzyna
	 */
	public void setDruzyna(boolean druzyna) {
		this.druzyna = druzyna;
	}

	/**
	 * Pobiera druzyne do wykorzystania jej w innych klasach.
	 *
	 * @return zwraca druzyne zawodnika.a
	 */
	public boolean getDruzyna() {
		return druzyna;
	}

	/**
	 * czyMamPilke sprawdza czy zawodnik ma pilke w taki sposob, ze najpierw
	 * sprawdza czy strefa, w ktorej jest pilka jest stref¹, w ktorej jest zawodnik;
	 * jesli tak to sprawdza w ktorej druzynie jest aktualnie i do kogo nalezy,
	 * jesli obie wartosci sa albo true albo false to znaczy, ze zawodnik ma pilke
	 *
	 * @param pilka obiekt klasy Pilka, ktory pozwala sprawdzac w ktorej strefie
	 *              jest.
	 */
	public void czyMamPilke(Pilka pilka) {
		int sprawdzacz = 0, sprawdzacz2 = 0;
		if (pilka.getWKtorejStrefieJestem() == this.strefa) {
			if (getDruzyna()) {
				sprawdzacz = 1;
			}
			if (!getDruzyna()) {
				sprawdzacz = 0;
			}
			if (pilka.getDoKogoNaleze()) {
				sprawdzacz2 = 1;
			}
			if (!pilka.getDoKogoNaleze()) {
				sprawdzacz2 = 0;
			}
            czyMamPilke = sprawdzacz == sprawdzacz2;
		}

		else
			czyMamPilke = false;
	}

	/**
	 * Pobiera strzal zawodnika.
	 *
	 * @return zwraca strzal.
	 */
	public int getStrzal() {
		return strzal;
	}

	/**
	 * Pobiera podanie zawodnika.
	 *
	 * @return zwraca podanie.
	 */
	public int getPodanie() {
		return podanie;
	}

	/**
	 * Pobiera obrona zawodnika.
	 *
	 * @return zwraca obrone.
	 */
	public int getObrona() {
		return obrona;
	}

	/**
	 * Pobiera strefe zawodnika.
	 *
	 * @return zwraca strefe.
	 */
	public int getStrefa() {
		return strefa;
	}

	/**
	 * Pobiera wartosc czyMamPilke (true/false) zawodnika.
	 *
	 * @return zwraca czyMamPilke (true/false).
	 */
	public boolean getCzyMamPilke() {
		return czyMamPilke;
	}

	/**
	 * Pobiera role zawodnika.
	 *
	 * @return zwraca role.
	 */
	public char getRola() {
		return rola;
	}

	/**
	 * strzelaj odpowiada za to, czy zawodnik bedac aktualnie przy pilce oddaje
	 * strzal czy nie. Dzia³a to w ten sposob, ze w zaleznosci od statystyki strza³u
	 * jaka dalismy wczesniej zawodnikowi oraz od strefy, w ktorej on sie znajduje
	 * wykonuja sie odpowiednie oblicznia zapisujace sie do zmiennej 'szansa'. Na
	 * koniec jezeli szasna jest odpowiednie duza (wykonuje sie losowanie, ktore
	 * okresla czy strzal sie udaje czy tez nie) strzal jest celny, w przeciwnym
	 * razie jest on niecelny.
	 *
	 * @param r do losowania.
	 * @return true oznacza strzal celny
	 * @return false oznacza strzal niecelny
	 */
	boolean strzelaj(Random r) {
		int szansa = 0;
		if (getDruzyna()) {
			if (strefa <= 10)
				szansa = (strzal);
			if (strefa > 10 && strefa <= 20)
				szansa = (strzal) * (15);
			if ((strefa > 20 && strefa <= 26) || strefa == 30)
				szansa = (strzal) * (70);
			if (strefa > 26 && strefa <= 29)
				szansa = (strzal) * (90);
		} else {
			if (strefa >= 25)
				szansa = (strzal);
			if (strefa < 25 && szansa >= 11)
				szansa = (strzal) * (15);
			if ((strefa < 11 && strefa >= 5) || strefa == 1)
				szansa = (strzal) * (70);
			if (strefa < 5 && strefa >= 2)
				szansa = (strzal) * (90);
		}

        return szansa >= r.nextInt(10000);
	}

	/**
	 * przejmij bedzie odpowiadala za przejecie pilki innemu zawodnikowi.
	 *
	 * @param przejmij
	 */
	void przejmij(int przejmij) {

	}

}