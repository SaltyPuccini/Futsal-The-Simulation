package Projekt_PO_Futsal;

/**
 * Klasa odpowiedzialna za przechodwanie polozenia pilki na murawie i
 * sprawdzanie ktora druzyna jest w posiadaniu.
 */
public class Pilka {

	/**
	 * wKtorejStrefieJestem trzyma informacje w ktore strefie znajduje sie pilka.
	 */
	private int wKtorejStrefieJestem;

	/** doKogoNaleze trzyma informacje do ktorej druzyny nalezy pilka. */
	private boolean doKogoNaleze;

	/**
	 * Konstruktor klasy Pilka.
	 *
	 * @param wKtorejStrefieJestem ustawia wKtorejStrefieJestem.
	 * @param doKogoNaleze         ustawia doKogoNaleze.
	 */
	Pilka(int wKtorejStrefieJestem, boolean doKogoNaleze) {
		this.wKtorejStrefieJestem = wKtorejStrefieJestem;
		this.doKogoNaleze = doKogoNaleze;
	}

	/**
	 * zmianaPozycji odpowiada za zmiane strefy pilki na nowa strefe.
	 *
	 * @param nowaStrefa przyjmuje w LogiceZachowan numer sektora, do ktorego ma
	 *                   udac siê pilka i przekazuje go odpowiedzialnemu za to polu.
	 */
	public void zmianaPozycji(int nowaStrefa) {
		this.wKtorejStrefieJestem = nowaStrefa;
	}

	/**
	 * Ustawia doKogoNaleze.
	 *
	 * @param doKogoNaleze the new doKogoNaleze.
	 */
	public void setDoKogoNaleze(boolean doKogoNaleze) {
		this.doKogoNaleze = doKogoNaleze;
	}

	/**
	 * Pobiera wartosc wKtorejStefieJestem.
	 *
	 * @return zwraca wartosc wwKtorejStefieJestem do dzialania w innych klasach.
	 */
	public int getWKtorejStrefieJestem() {
		return this.wKtorejStrefieJestem;
	}

	/**
	 * Pobiera wartosc doKogoNaleze, aby moc to wykorzystac w innych klasach.
	 *
	 * @return zwraca wartosc doKogoNaleze do dzialania w innych klasach.
	 */
	public boolean getDoKogoNaleze() {
		return this.doKogoNaleze;
	}
}
