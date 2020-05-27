package Projekt_PO_Futsal;

import java.util.*;
import java.util.Scanner;

/**
 * Klasa Bramkarz odpowiada za tworzenie obiektow bramkarzy. W maine stworzone
 * zostan� dwa takie obiekty, po jednym bramkarzu na dru�yn�. Klasa ta
 * przechowuje w sobie informacje o ilo�ci wpuszczonych goli, statystykach
 * podania i bronienia bramkarza. Posiada te� metody, okre�lajace system podania
 * oraz obrony strza�ow.
 */
public class Bramkarz extends Podanie {

	/**
	 * gole przeciwnika przechowuj� informacje o wpuszczonych przez bramkarza
	 * golach.
	 */
	private int golePrzeciwnika;

	/**
	 * podanie, umki bramkarskie przechowuj� statystyki bramkarza podane przez
	 * uzytkownika
	 */
	private int podanie;
	private final int umkiBramkarskie;

	/**
	 * druzyna przechowuje informacje o tym, do ktorej druzyny nalezy bramkarz (true
	 * - lewa, b�dz false - prawa)
	 */
	private boolean druzyna;

	/**
	 * konstruktor klasy Bramkarz.
	 *
	 * @param podanie         ustawia podanie
	 * @param umkiBramkarskie ustaiwa umiejetnosci bramkarskie
	 * @param druzyna         ustawia druzyn�
	 * @param golePrzeciwnika ustawia gole wpuszczone
	 */

	Bramkarz(int podanie, int umkiBramkarskie, boolean druzyna, int golePrzeciwnika) {
		this.golePrzeciwnika = golePrzeciwnika;
		this.podanie = podanie;
		this.umkiBramkarskie = umkiBramkarskie;
		this.druzyna = druzyna;
	}

	/**
	 * wpiszPodanie Umo�liwia u�ytkownikowi wpisanie z klawiatury statystyki
	 * podania, co wykonuje si� w KreatorzeDruzyny;
	 *
	 * @param scan potrzebny do wpisania informacji z klawiatury.
	 */
	public void wpiszPodanie(Scanner scan) {
		System.out.println("Podaj statystyke podania zawodnika, maksymalnie 99");
		do
			this.podanie = scan.nextInt();
		while (podanie > 99 || podanie < 0);
	}

	/**
	 * wpiszUmkiBramkarskie Umo�liwia u�ytkownikowi wpisanie z klawiatury statystyki
	 * umiejetnosci bramkarskich, co wykonuje si� w KreatorzeDruzyny;
	 *
	 * @param scan potrzebny do wpisania informacji z klawiatury.
	 */
	public void wpiszUmkiBramkarskie(Scanner scan) {
		System.out.println("Podaj statystyke umiejetnosci bramkarskich, maksymalnie 99");
		do
			this.podanie = scan.nextInt();
		while (podanie > 99 || podanie < 0);
	}

	/**
	 * Podaj do przodu.
	 *
	 * @param boisko klasy GenerujBoisko, na ktorym odbywa si� mecz
	 * @return strefa, do ktorej bramkarz poda� pi�k�. W LogiceZachowa� t�
	 *         informacja przekazywana jest obiektowi klasy Pilka, przechowujacemu
	 *         informacje o strefie pobytu pilki. Aby znalezc najbli�sz� (indeksem
	 *         rosn�cym) stref�, nast�puj� sprawdzenie, czy strefa ewentualnego
	 *         podania jest zaj�ta przez zawodnika tej samej dru�yny, ewentualnie
	 *         zawodnikow obu dru�yn.
	 */
	int podajDoPrzodu(GenerujBoisko boisko) {

		int gdzieJestWspolzawodnik;
		if (druzyna == true) {
			gdzieJestWspolzawodnik = 0;
			for (int i = 1; gdzieJestWspolzawodnik + i < 31; i++) {
				if (boisko.strefa[gdzieJestWspolzawodnik + i] == 1 || boisko.strefa[gdzieJestWspolzawodnik + i] == 3)
					return gdzieJestWspolzawodnik + i;
			}
		} else {
			gdzieJestWspolzawodnik = 31;
			for (int i = gdzieJestWspolzawodnik - 1; i > 0; i--) {
				if (boisko.strefa[i] == 2 || boisko.strefa[i] == 3)
					return i;
			}
		}
		return 0;
	}

	/**
	 * wykop wykonuje wykopanie pilki do stref za polowe bramkarz.
	 *
	 * @param r losuje liczbe.
	 * @return strefa, w zale�no�ci od dru�yny bramkarza, do ktorej bramkarz wykopa�
	 *         pi�k�. W LogiceZachowa� ta informacja przekazywana jest obiektowi
	 *         klasy Pilka, ktory aktualizuje informacje o strefie pobytu pilki.
	 */
	int wykop(Random r) {
		int doKtorejStrefy;

		if (this.druzyna) {
			doKtorejStrefy = r.nextInt(10) + 16;
			return doKtorejStrefy;
		} else {
			doKtorejStrefy = r.nextInt(10) + 6;
			return doKtorejStrefy;
		}
	}

	/**
	 * robinsonada odpowiada za sprawdzenie czy bramkarz obroni� strza�. Wywo�ywana
	 * jest w LogicaZachowa� w momencie gdy zostaje oddany celny strzal.
	 *
	 * @param r losuje liczbe.
	 * @return true, je�li bramkarz nie obroni� strza�u
	 * @return false, je�li bramkarz obroni� strza�
	 */
	boolean robinsonada(Random r) {
		int czyObroni = 200 + (umkiBramkarskie * 8);

		int losowankoCzyObroni = r.nextInt(1000) + 1;

		if (losowankoCzyObroni > czyObroni) {
			golePrzeciwnika++;
			return true;
		}
		
		return false;

	}

	/**
	 * Ustawia druzyne bramkarzowi.
	 *
	 * @param druzyna the new druzyna.
	 */
	public void setDruzyna(boolean druzyna) {
		this.druzyna = druzyna;
	}

	/**
	 * Pobiera informacje do ktorej druzyny nalezy bramkarz.
	 *
	 * @return true - bramakrz nalezy do druzyny po lewej.
	 * @return false = bramakrz nalezy do druzyny po prawej.
	 */
	public boolean getDruzyna() {
		return druzyna;
	}

	/**
	 * Pobiera informacje o golach strzelonych bramkarzowi przez druzyne przeciwna.
	 *
	 * @return zwraca gole strzelone bramkarzowi.
	 */
	public int getGolePrzeciwnika() {
		return this.golePrzeciwnika;
	}

	/**
	 * Pobiera informacje o statystyce umiejetnosci bramkarskich bramakarza
	 * wpisanych przez uzytkownika.
	 *
	 * @return zwraca wartosc statystyki umiejetnosci bramkarskie.
	 */
	public int getUmkiBramkarskie() {
		return umkiBramkarskie;
	}

	/**
	 * Pobiera informacje o statystyce podan bramakarza wpisanych przez uzytkownika.
	 *
	 * @return zwraca wartosc statystyki podanie.
	 */
	public int getPodanie() {
		return podanie;
	}

}
