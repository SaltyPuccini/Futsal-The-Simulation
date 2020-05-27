package Projekt_PO_Futsal;

/**
 * Klasa Podanie definiuje 2 rozne typy podan, ktore wykorzystwywane sa w klasach dziedzicz¹cych.
 */
public abstract class Podanie {
	
	
	/**
	 * podajDoTyl odpowiada za podanie do tylu.
	 *
	 * @param zawodnik to obiekt klasy Zawodnik, ktory wykonuje akcje - jest aktualnie w posiadaniu pi³ki
	 * @param boisko to obiekt klasy GenerujBoisko, ktory sprawdza w ktorej strefie jest zawodnik, zeby wiedziec do ktorej strefy podac.
	 * @return strefa, w ktor¹ zostanie przeniesiona pilka
	 */
	int podajDoTyl(Zawodnik zawodnik, GenerujBoisko boisko) {
		boolean ktoraDruzyna = zawodnik.getDruzyna();
		
		int gdzie = zawodnik.getStrefa();
		
		if (ktoraDruzyna == true)
			for (int i = gdzie - 1; i > 0; i--) {
				if (boisko.strefa[i] == 1 || boisko.strefa[i] == 3)
					return i;
			}
		else
			for (int i = gdzie; i < 31; i++) {
				if (boisko.strefa[i] == 2 || boisko.strefa[i] == 3)
					return i;
			}
		return 0;
	}

	/**
	 * podajDoPrzodu odpowiada za podanie do przodu.
	 *
	 * @param zawodnik to obiekt klasy Zawodnik, ktory wykonuje akcje - jest aktualnie w posiadaniu pi³ki
	 * @param boisko to obiekt klasy GenerujBoisko, ktory sprawdza w ktorej strefie jest zawodnik, zeby wiedziec do ktorej strefy podac.
	 * @return strefa, w ktor¹ zostanie przeniesiona pilka
	 */
	public int podajDoPrzodu(Zawodnik zawodnik, GenerujBoisko boisko) {

		boolean ktoraDruzyna = zawodnik.getDruzyna();

		int gdzie = zawodnik.getStrefa();

		if (ktoraDruzyna == true)
			for (int i = 1; gdzie + i < 31; i++) {
				if (boisko.strefa[gdzie + i] == 1 || boisko.strefa[gdzie + i] == 3)
					return gdzie + i;
			}
		else
			for (int i = gdzie - 1; i > 0; i--) {
				if (boisko.strefa[i] == 2 || boisko.strefa[i] == 3)
					return i;
			}
		return 0;

	}
}
