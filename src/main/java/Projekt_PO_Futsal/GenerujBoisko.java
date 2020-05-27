package Projekt_PO_Futsal;

import java.util.Scanner;

/**
 * Klasa generujBoisko generuje boisko.
 */
public class GenerujBoisko {

	/**
	 * strefa przechowuje inforacje o "zageszczeniu" zawodnikow w strefach boiska.
	 */
	protected int[] strefa = new int[32];

	/**
	 * Ustawia strefe domyslna.
	 *
	 * @param strefa the new strefa.
	 */
	public void setStrefa(int[] strefa) {
		this.strefa = strefa;
	}

	/**
	 * zajmijStrefy odpowiada za przypisanie odpowiednim strefom informacji, kto siê
	 * w nich znajduje. Gdy znajduje siê zawodnik dru¿yny true - 1, gdy false - 2,
	 * gdy obu - 3.
	 *
	 * @param zawodnik przechowuje informacje o zawodniku, ktoremu przypisywana jest
	 *                 strefa - jego dru¿ynê.
	 */
	public void zajmijStrefe(Zawodnik zawodnik) {
		if (zawodnik.getDruzyna() == true) {
			if (strefa[zawodnik.getStrefa()] == 2)
				strefa[zawodnik.getStrefa()] = 3;
			else
				strefa[zawodnik.getStrefa()] = 1;

		}
		if (zawodnik.getDruzyna() == false)

			if (strefa[zawodnik.getStrefa()] == 1)
				strefa[zawodnik.getStrefa()] = 3;

			else
				strefa[zawodnik.getStrefa()] = 2;

	}

	/**
	 * formacjaNaBoisku w zaleznosci od obranej taktyki (defensywa- obrona od
	 * najwiêkszej do najmniejszesz, ofensywa analogicznie ze strza³em, posiadanie,
	 * analogicznie z podaniem) sortuje zawodnikow, dzieki czemu bedziemy mogli ich
	 * potem ustawic na boisku.
	 *
	 * @param druzyna przechowuje informajce jak liczna jest dru¿yna
	 * @param taktyka prechowuje taktyke w zaleznosci od ktorej bedziemy sortowac
	 *                zawodnikow.
	 * @param team    tablica zawdonikow, ktora zostaje posortowana.
	 * @param scan    pobiera informacje z klawiatury.
	 */
	public void formacjaNaBoisku(KreatorDruzyny druzyna, Taktyka taktyka, Zawodnik[] team, Scanner scan) {
		int wybranaTaktyka = taktyka.taktykaUstawienia(scan);
		int ile = druzyna.getJakLiczne();
//------------------------------------------------------------------------------------------------------------		
//------------------------------------------------------------------------------------------------------------				
		if (wybranaTaktyka == 1)
			for (int i = 0; i < ile - 1; i++)
				for (int j = 0; j < ile - i - 1; j++)
					if (team[j].getStrzal() < team[j + 1].getStrzal()) {
						Zawodnik temp = new Zawodnik(0, 0, 0, 0, false, 'x', false);
						temp = team[j];
						team[j] = team[j + 1];
						team[j + 1] = temp;
					}
//------------------------------------------------------------------------------------------------------------		
//------------------------------------------------------------------------------------------------------------		
		if (wybranaTaktyka == 2)
			for (int i = 0; i < ile - 1; i++)
				for (int j = 0; j < ile - i - 1; j++)
					if (team[j].getPodanie() < team[j + 1].getPodanie()) {
						Zawodnik temp = new Zawodnik(0, 0, 0, 0, false, 'x', false);
						temp = team[j];
						team[j] = team[j + 1];
						team[j + 1] = temp;
					}
//------------------------------------------------------------------------------------------------------------		
//------------------------------------------------------------------------------------------------------------		        								
		if (wybranaTaktyka == 3)
			for (int i = 0; i < ile - 1; i++)
				for (int j = 0; j < ile - i - 1; j++)
					if (team[j].getObrona() < team[j + 1].getObrona()) {
						Zawodnik temp = new Zawodnik(0, 0, 0, 0, false, 'x', false);
						temp = team[j];
						team[j] = team[j + 1];
						team[j + 1] = temp;
					}
//------------------------------------------------------------------------------------------------------------		
//------------------------------------------------------------------------------------------------------------		
		for (int i = 0; i < ile - 1; i++)
			System.out.println("Adres zawodnika to: " + team[i]);
	}

	/**
	 * postawienieNaMurawie ustawia wczesniej posortowanych zawodnikow na boisku. Na
	 * podstawie ilosci napastnikow, pomocnikow i obroncow podanych w
	 * KreatorzeDruzyny ustawia zawodnikow posortowanych w tablicy za pomoca taktyki
	 * na pozycjach.
	 *
	 * @param druzyna  przechowuje informacje ktora druzyne aktualnie ustawia.
	 * @param zawodnik przechowuje informacje ktorego zawodnika aktualnie ustawia
	 */
	public void postawienieNaMurawie(KreatorDruzyny druzyna, Zawodnik[] zawodnik) {

		int napad = druzyna.getNapad();
		int pomoc = druzyna.getPomoc();
		int obrona = druzyna.getObrona();

		if (druzyna.getIdentyfikatorDruzyny()) {
			int obrotpetli = 0;
			int j = 0;
			int k = 0;
			for (int i = 0; i < napad; i++) {
				if (i % 2 == 0) {
					zawodnik[i].setStrefa(23 - j);
					j++;
				} else {
					zawodnik[i].setStrefa(24 + k);
					k++;
				}
				zawodnik[i].setRola('n');
				obrotpetli = i + 1;
			}
			j = 0;
			k = 0;
			for (int i = obrotpetli; i < pomoc + napad; i++) {
				if (i % 2 == 0) {
					zawodnik[i].setStrefa(13 - j);
					j++;
				} else {
					zawodnik[i].setStrefa(14 + k);
					k++;
				}
				zawodnik[i].setRola('p');
				obrotpetli = i + 1;
			}
			j = 0;
			k = 0;
			for (int i = obrotpetli; i < napad + pomoc + obrona; i++) {
				if (i % 2 == 0) {
					zawodnik[i].setStrefa(7 - j);
					j++;
				} else {
					zawodnik[i].setStrefa(8 + k);
					k++;
				}
				zawodnik[i].setRola('o');
			}
		}
//------------------------------------------------------------------------------------------------------------------------------------------------		
		else {
			int obrotpetli = 0;
			int j = 0;
			int k = 0;
			for (int i = 0; i < napad; i++) {
				if (i % 2 == 0) {
					zawodnik[i].setStrefa(8 + j);
					j++;
				} else {
					zawodnik[i].setStrefa(7 - k);
					k--;
				}
				zawodnik[i].setRola('n');
				obrotpetli = i + 1;
			}
			j = 0;
			k = 0;
			for (int i = obrotpetli; i < pomoc + napad; i++) {
				if (i % 2 == 0) {
					zawodnik[i].setStrefa(17 - j);
					j--;
				} else {
					zawodnik[i].setStrefa(18 + k);
					k++;
				}
				zawodnik[i].setRola('p');
				obrotpetli = i + 1;
			}
			j = 0;
			k = 0;
			for (int i = obrotpetli; i < napad + pomoc + obrona; i++) {
				if (i % 2 == 0) {
					zawodnik[i].setStrefa(23 - j);
					j--;
				} else {
					zawodnik[i].setStrefa(24 + k);
					k++;
				}
				zawodnik[i].setRola('o');
			}
		}
	}

}