package Projekt_PO_Futsal;

import java.util.*;

/**
 * Klasa LogikaZachowan odpowiada za zachowania pilkarzy na boisku. W skrocie: W
 * zale�no�ci od nadanej roli, zawodnik wykona rozne akcje. Mo�liwe s�
 * nast�puj�ce sytuacje: Pi�ka jest u zawodnika, Pi�ka jest u kogo� z dru�yny
 * zawodnika, Pi�ka jest w dru�ynie przeciwnej.
 * 
 * W finalnej wersji projektu, planowane s� cztery zestawy zachowa�: priorytet,
 * atak, obrona, klepka. Ka�da przewiduje wykonanie odpowiedniej czynno�ci w
 * stosunku do stanu posiadania pi�ki Na to, �e pi�karz wykona czynno��
 * priorytetow�, jest zawsze 35% szansy. Na wykonanie czynno�ci zawartej w
 * wybranym przez u�ytkowanika zachowaniu, rownie� 35% szansy. Na wykonanie
 * zachowania z ktorej� z pozosta�ych dwoch "taktyk", po 15%. W ten sposob
 * otrzymujemy kontrolowan� losowo�� symulacji
 * 
 */
public class LogikaZachowan {

	/**
	 * priorytety
	 *
	 * @param zawodnik          Zawodnik wykonuj�cy czynno��
	 * @param druzyna           Druzyna, do ktorej nalezy zawodnik
	 * @param przeciwnyBramkarz Bramkarz dru�yny przeciwnej
	 * @param boisko            Boisko, na ktorym rozegra si� mecz
	 * @param pilka             Pilka meczowa
	 * @param r                 liczba losowa
	 */

	public void logikaStrzelania(Zawodnik zawodnik, Bramkarz przeciwnyBramkarz, Random r) {
		if (zawodnik.strzelaj(r)) {
			System.out.println("Strzela, pi�ka szybuj� w �wiat�o bramki i...");
			if (przeciwnyBramkarz.robinsonada(r)) {
				System.out.println("Gol");
				System.out.println("Druzyna " + zawodnik.getDruzyna() + " ma " + przeciwnyBramkarz.getGolePrzeciwnika()
						+ " goli.");
			} else
				System.out.println("Nie ma gola, bramkarz obroni�");

		} else
			System.out.println("Strza�, ale bardzo niecelny");
	}

	public void oddajPilke(Zawodnik zawodnik, Pilka pilka, Bramkarz przeciwnyBramkarz, GenerujBoisko boisko) {
		if (zawodnik.getDruzyna())
			pilka.setDoKogoNaleze(false);
		if (!zawodnik.getDruzyna())
			pilka.setDoKogoNaleze(true);

		zawodnik.czyMamPilke(pilka);
		pilka.zmianaPozycji(przeciwnyBramkarz.podajDoPrzodu(boisko));
		System.out.println("Bramkarz podaje pi�k� do strefy " + pilka.getWKtorejStrefieJestem());
	}

	public void priorytetNapastnikaMajacegoPilke(Zawodnik zawodnik, Bramkarz przeciwnyBramkarz, GenerujBoisko boisko,
			Pilka pilka, Random r) {
		System.out.println(
				"Napastnik dru�yny " + zawodnik.getDruzyna() + " przy pi�ce w strefie " + zawodnik.getStrefa());
		logikaStrzelania(zawodnik, przeciwnyBramkarz, r);
		oddajPilke(zawodnik, pilka, przeciwnyBramkarz, boisko);
	}

	public void priorytetPomocnikaMajacegoPilke(Zawodnik zawodnik, Bramkarz przeciwnyBramkarz, GenerujBoisko boisko,
			Pilka pilka, Random r) {
		System.out
				.println("Pomocnik dru�yny " + zawodnik.getDruzyna() + " przy pi�ce w strefie " + zawodnik.getStrefa());
		int podanie = zawodnik.podajDoPrzodu(zawodnik, boisko);

		if (podanie == 0) {
			logikaStrzelania(zawodnik, przeciwnyBramkarz, r);
			oddajPilke(zawodnik, pilka, przeciwnyBramkarz, boisko);
		} else {
			pilka.zmianaPozycji(podanie);
			System.out.println("Podaje pi�k� do sektora: " + pilka.getWKtorejStrefieJestem());
		}
		zawodnik.czyMamPilke(pilka);
	}

	public void priorytetObroncyMajacegoPilke(Zawodnik zawodnik, Bramkarz przeciwnyBramkarz, GenerujBoisko boisko,
			Pilka pilka, Random r) {
		System.out
				.println("Obro�ca dru�yny " + zawodnik.getDruzyna() + " przy pi�ce w strefie " + zawodnik.getStrefa());
		int podanko = zawodnik.podajDoPrzodu(zawodnik, boisko);

		if (podanko != 0)
			pilka.zmianaPozycji(podanko);
		if (podanko == 0)
			pilka.zmianaPozycji(zawodnik.podajDoTyl(zawodnik, boisko));

		System.out.println("Podanie do sektora " + pilka.getWKtorejStrefieJestem());
		zawodnik.czyMamPilke(pilka);
	}

	public void priorytetNapastnikaDruzyna(Zawodnik zawodnik, Random r) {

		if (zawodnik.getDruzyna() == true)
			zawodnik.setStrefa(r.nextInt(3) + 27);
		else
			zawodnik.setStrefa(r.nextInt(3) + 2);

	}

	public void priorytetPomocnikaDruzyna(Zawodnik zawodnik, GenerujBoisko boisko, Random r) {
		int sprawdzanaStrefa = zawodnik.getStrefa();
		if (boisko.strefa[zawodnik.getStrefa()] == 3) {
			while (boisko.strefa[sprawdzanaStrefa] != 0 && sprawdzanaStrefa < 31 && sprawdzanaStrefa > 0) {
				if (zawodnik.getDruzyna() == true)
					sprawdzanaStrefa = sprawdzanaStrefa + 1;
				else
					sprawdzanaStrefa = sprawdzanaStrefa - 1;
			}
			if (sprawdzanaStrefa < 31 || sprawdzanaStrefa > 0)
				zawodnik.setStrefa(sprawdzanaStrefa);
		}

	}

	public void priorytetObroncyDruzyna(Zawodnik zawodnik, Random r) {
		if (zawodnik.getDruzyna() == true)
			zawodnik.setStrefa(r.nextInt(5) + 6);
		else
			zawodnik.setStrefa(r.nextInt(5) + 21);

	}

	public void priorytetNapastnikaPrzeciwnik(Zawodnik zawodnik,GenerujBoisko boisko, Pilka pilka, Random r) {
		
	}
	
	public void priorytetPomocnikaPrzeciwnik(Zawodnik zawodnik,GenerujBoisko boisko, Pilka pilka, Random r) {
		
	}
	
	public void priorytetObroncyPrzeciwnik(Zawodnik zawodnik,GenerujBoisko boisko, Pilka pilka, Random r) {
		
	}
	
	public void priorytety(Zawodnik zawodnik, Bramkarz przeciwnyBramkarz, GenerujBoisko boisko, Pilka pilka, Random r) {

		zawodnik.czyMamPilke(pilka);
		if (zawodnik.getCzyMamPilke()) {

			switch (zawodnik.getRola()) {
			case 'n':
				priorytetNapastnikaMajacegoPilke(zawodnik, przeciwnyBramkarz, boisko, pilka, r);
				break;

			case 'p':
				priorytetPomocnikaMajacegoPilke(zawodnik, przeciwnyBramkarz, boisko, pilka, r);
				break;

			case 'o':
				priorytetObroncyMajacegoPilke(zawodnik, przeciwnyBramkarz, boisko, pilka, r);
				break;
			}
		} else if (pilka.getDoKogoNaleze() == zawodnik.getDruzyna()) {
			switch (zawodnik.getRola()) {
			case 'n':
				priorytetNapastnikaDruzyna(zawodnik, r);
				break;

			case 'p':
				priorytetPomocnikaDruzyna(zawodnik, boisko, r);
				break;

			case 'o':
				priorytetObroncyDruzyna(zawodnik, r);
				break;
			}
		}else
			switch (zawodnik.getRola()) {
			case 'n':
				priorytetNapastnikaPrzeciwnik(zawodnik, boisko, pilka, r);
				break;

			case 'p':
				priorytetPomocnikaPrzeciwnik(zawodnik, boisko, pilka, r);
				break;

			case 'o':
				priorytetObroncyPrzeciwnik(zawodnik, boisko, pilka, r);
				break;
			}
	}

	/**
	 * Atak.
	 *
	 * @param zawodnik the zawodnik
	 * @param druzyna  the druzyna
	 */
	public void atak(Zawodnik zawodnik, KreatorDruzyny druzyna) {

	}

	/**
	 * Obrona.
	 *
	 * @param zawodnik the zawodnik
	 * @param druzyna  the druzyna
	 */
	public void obrona(Zawodnik zawodnik, KreatorDruzyny druzyna) {

	}

	/**
	 * Klepka.
	 *
	 * @param zawodnik the zawodnik
	 * @param druzyna  the druzyna
	 */
	public void klepka(Zawodnik zawodnik, KreatorDruzyny druzyna) {

	}
}
