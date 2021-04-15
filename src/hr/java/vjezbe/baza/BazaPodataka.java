package hr.java.vjezbe.baza;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.entitet.Automobil;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.iznimke.BazaPodatakaException;

/**
 * Rad s bazom podataka u Javi
 * 
 * @author Patricija Kuže
 *
 */
public class BazaPodataka {
	private static final String DATABASE_FILE = "bazaPodataka.properties";
	private static final Logger logger = LoggerFactory.getLogger(BazaPodataka.class);

	public static List<Automobil> listaAutomobila = new ArrayList<>();
	public static List<Usluga> listaUsluga = new ArrayList<>();
	public static List<Stan> listaStanova = new ArrayList<>();
	public static List<PrivatniKorisnik> listaPrivatnihKorisnika = new ArrayList<>();
	public static List<PoslovniKorisnik> listaPoslovnihKorisnika = new ArrayList<>();

	// spajanje na bazu

	/**
	 * Spajanje na bazu podataka
	 * @return veza
	 * @throws SQLException iznimka vezana za greske u bazi
	 * @throws FileNotFoundException iznimka ako datoteka nije pronadena
	 * @throws IOException iznimka za unos/ispis
	 */
	private static Connection spajanjeNaBazuPodataka() throws SQLException, FileNotFoundException, IOException {
		Properties svojstva = new Properties();
		svojstva.load(new FileReader(DATABASE_FILE));
		String urlBazePodataka = svojstva.getProperty("bazaPodatakaUrl");
		String korisnickoIme = svojstva.getProperty("korisnickoIme");
		String lozinka = svojstva.getProperty("lozinka");
		Connection veza = DriverManager.getConnection(urlBazePodataka, korisnickoIme, lozinka);

		return veza;
	}

	/**
	 * Zatvaranje veze prema bazi podataka
	 * 
	 * @param veza
	 * @throws SQLException iznimka za greske vezane za bazu
	 */
	private static void zatvaranjeVezeNaBazuPodataka(Connection veza) throws SQLException {
		veza.close();
	}

	/**
	 * Sprema automobil
	 * 
	 * @param automobil
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void spremiAutomobil(Automobil automobil) throws SQLException, IOException {
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		try {
			PreparedStatement insertAutomobil = veza
					.prepareStatement("INSERT INTO ARTIKL(NASLOV, OPIS, SNAGA, CIJENA, IDSTANJE, IDTIPARTIKLA) VALUES (?, ?, ?, ?, ?, ?);");
			insertAutomobil.setString(1, automobil.getNaslov());
			insertAutomobil.setString(2, automobil.getOpis());
			insertAutomobil.setDouble(3, automobil.getSnagaKs().doubleValue());
			insertAutomobil.setDouble(4, automobil.getCijena().doubleValue());
			for (int i = 0; i < Stanje.values().length; i++) {
				if (Stanje.values()[i].name().equals(automobil.getStanje().name())) {
					int broj = i+1;
					insertAutomobil.setInt(5, broj);
				}
			}
			insertAutomobil.setInt(6,  Automobil.ID_TIP_ARTIKLA);
			insertAutomobil.executeUpdate();

			veza.commit();
			veza.setAutoCommit(true);
		} catch (Throwable ex) {
			veza.rollback();
			throw ex;
		}
		zatvaranjeVezeNaBazuPodataka(veza);
	}

	/**
	 * Sprema uslugu
	 * @param usluga
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void spremiUslugu(Usluga usluga) throws SQLException, IOException {
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		try {
			PreparedStatement insertUsluga = veza
					.prepareStatement("INSERT INTO ARTIKL(NASLOV, OPIS, CIJENA, IDSTANJE, IDTIPARTIKLA) VALUES (?, ?, ?, ?, ?);");
			insertUsluga.setString(1, usluga.getNaslov());
			insertUsluga.setString(2, usluga.getOpis());
			insertUsluga.setDouble(3, usluga.getCijena().doubleValue());
			for (int i = 0; i < Stanje.values().length; i++) {
				if (Stanje.values()[i].name().equals(usluga.getStanje().name())) {
					int broj = i+1;
					insertUsluga.setInt(4, broj);
				}
			}
			insertUsluga.setInt(5,  Usluga.ID_TIP_ARTIKLA);
			insertUsluga.executeUpdate();

			veza.commit();
			veza.setAutoCommit(true);
		} catch (Throwable ex) {
			veza.rollback();
			throw ex;
		}
		zatvaranjeVezeNaBazuPodataka(veza);
	}

	/**
	 * Sprema stan
	 */
	public static void spremiStan(Stan stan) throws SQLException, IOException {
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		try {
			PreparedStatement insertStan = veza.prepareStatement(
					"INSERT INTO ARTIKL(NASLOV, OPIS, KVADRATURA,  CIJENA, IDSTANJE, IDTIPARTIKLA) VALUES (?, ?, ?, ?, ?, ?);");
			insertStan.setString(1, stan.getNaslov());
			insertStan.setString(2, stan.getOpis());
			insertStan.setDouble(4, stan.getCijena().doubleValue());
			insertStan.setDouble(3, stan.getKvadratura().doubleValue());
			for (int i = 0; i < Stanje.values().length; i++) {
				if (Stanje.values()[i].name().equals(stan.getStanje().name())) {
					int broj = i+1;
					insertStan.setInt(5, broj);
				}
			}
			insertStan.setInt(6,  Stan.ID_TIP_ARTIKLA);
			insertStan.executeUpdate();

			veza.commit();
			veza.setAutoCommit(true);
		} catch (Throwable ex) {
			veza.rollback();
			throw ex;
		}
		zatvaranjeVezeNaBazuPodataka(veza);
	}

	/**
	 * Sprema privatnog korisnika
	 * @param privatniKorisnik
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void spremiPrivatnogKorisnika(PrivatniKorisnik privatniKorisnik) throws SQLException, IOException {
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		try {
			PreparedStatement insertPrivatniKorisnik = veza
					.prepareStatement("INSERT INTO KORISNIK(IME, PREZIME, EMAIL,  TELEFON, IDTIPKORISNIKA) VALUES (?, ?, ?, ?, ?);");
			insertPrivatniKorisnik.setString(1, privatniKorisnik.getIme());
			insertPrivatniKorisnik.setString(2, privatniKorisnik.getPrezime());
			insertPrivatniKorisnik.setString(3, privatniKorisnik.getEmail());
			insertPrivatniKorisnik.setString(4, privatniKorisnik.getTelefon());
			insertPrivatniKorisnik.setInt(5, privatniKorisnik.ID_TIPA);
			insertPrivatniKorisnik.executeUpdate();

			veza.commit();
			veza.setAutoCommit(true);
		} catch (Throwable ex) {
			veza.rollback();
			throw ex;
		}
		zatvaranjeVezeNaBazuPodataka(veza);
	}

	/**
	 * Sprema poslovnog korisnika
	 * @param poslovniKorisnik
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void spremiPoslovnogKorisnika(PoslovniKorisnik poslovniKorisnik) throws SQLException, IOException {
		Connection veza = spajanjeNaBazuPodataka();
		veza.setAutoCommit(false);
		try {
			PreparedStatement insertPoslovniKorisnik = veza
					.prepareStatement("INSERT INTO KORISNIK(NAZIV, WEB, EMAIL,  TELEFON, IDTIPKORISNIKA) VALUES (?, ?, ?, ?, ?);");
			insertPoslovniKorisnik.setString(1, poslovniKorisnik.getNaziv());
			insertPoslovniKorisnik.setString(2, poslovniKorisnik.getWeb());
			insertPoslovniKorisnik.setString(3, poslovniKorisnik.getEmail());
			insertPoslovniKorisnik.setString(4, poslovniKorisnik.getTelefon());
			insertPoslovniKorisnik.setInt(5, poslovniKorisnik.ID_TIPA);
			insertPoslovniKorisnik.executeUpdate();

			veza.commit();
			veza.setAutoCommit(true);
		} catch (Throwable ex) {
			veza.rollback();
			throw ex;
		}
		zatvaranjeVezeNaBazuPodataka(veza);
	}
	
	/**
	 * Dohvati stanove iz baze
	 * @return
	 * @throws BazaPodatakaException
	 */
	public static List<Stan> dohvatiStanove() throws BazaPodatakaException {
		List<Stan> listaStanova = new ArrayList<>();
		try (Connection connection = spajanjeNaBazuPodataka()) {
			StringBuilder sqlUpit = new StringBuilder(
					"SELECT distinct artikl.id, naslov, opis, cijena, kvadratura, stanje.naziv "
							+ "FROM artikl inner join stanje on stanje.id = artikl.idStanje "
							+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Stan'");
			
			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String naslov = resultSet.getString("naslov");
				String opis = resultSet.getString("opis");
				BigDecimal cijena = resultSet.getBigDecimal("cijena");
				BigDecimal kvadratura = resultSet.getBigDecimal("kvadratura");
				String stanje = resultSet.getString("naziv");
				Stan newStan = new Stan(naslov, opis, cijena, Stanje.valueOf(stanje.toUpperCase()), kvadratura, id);
				listaStanova.add(newStan);
			}
		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return listaStanova;
	}
	
	/**
	 * Dohvati automobile iz baze
	 * @return
	 * @throws BazaPodatakaException
	 */
	public static List<Automobil> dohvatiAutomobile() throws BazaPodatakaException {
		List<Automobil> listaAutomobila = new ArrayList<>();
		try (Connection connection = spajanjeNaBazuPodataka()) {
			StringBuilder sqlUpit = new StringBuilder(
					"SELECT distinct artikl.id, naslov, opis, cijena, snaga, stanje.naziv "
							+ "FROM artikl inner join stanje on stanje.id = artikl.idStanje "
							+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Automobil'");
			
			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String naslov = resultSet.getString("naslov");
				String opis = resultSet.getString("opis");
				BigDecimal cijena = resultSet.getBigDecimal("cijena");
				BigDecimal snaga = resultSet.getBigDecimal("snaga");
				String stanje = resultSet.getString("naziv");
				Automobil newAutomobil = new Automobil(naslov, opis, cijena, Stanje.valueOf(stanje.toUpperCase()), snaga, id);
				listaAutomobila.add(newAutomobil);
			}
		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return listaAutomobila;
	}
	
	/**
	 * Dohvati usluge iz baze
	 * @return
	 * @throws BazaPodatakaException
	 */
	public static List<Usluga> dohvatiUsluge() throws BazaPodatakaException {
		List<Usluga> listaUsluga = new ArrayList<>();
		try (Connection connection = spajanjeNaBazuPodataka()) {
			StringBuilder sqlUpit = new StringBuilder(
					"SELECT distinct artikl.id, naslov, opis, cijena, stanje.naziv "
							+ "FROM artikl inner join stanje on stanje.id = artikl.idStanje "
							+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Usluga'");
			
			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String naslov = resultSet.getString("naslov");
				String opis = resultSet.getString("opis");
				BigDecimal cijena = resultSet.getBigDecimal("cijena");
				String stanje = resultSet.getString("naziv");
				Usluga newUsluga = new Usluga(naslov, opis, cijena, Stanje.valueOf(stanje.toUpperCase()), id);
				listaUsluga.add(newUsluga);
			}
		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return listaUsluga;
	}
	
	/**
	 * Dohvati provatne korisnike iz baze
	 * @return
	 * @throws BazaPodatakaException
	 */
	public static List<PrivatniKorisnik> dohvatiPrivatneKorisnike() throws BazaPodatakaException {
		List<PrivatniKorisnik> listaPrivatnihKorisnika = new ArrayList<>();
		try (Connection connection = spajanjeNaBazuPodataka()) {
			StringBuilder sqlUpit = new StringBuilder(
					"SELECT distinct korisnik.id, ime, prezime, email, telefon FROM korisnik "
							+ "inner join tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika WHERE tipKorisnika.naziv = 'PrivatniKorisnik'");
			
			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String ime = resultSet.getString("ime");
				String prezime = resultSet.getString("prezime");
				String email = resultSet.getString("email");
				String telefon = resultSet.getString("telefon");
				PrivatniKorisnik newPrivatniKorisnik = new PrivatniKorisnik(ime, prezime, email, telefon, id);
				listaPrivatnihKorisnika.add(newPrivatniKorisnik);
			}
		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return listaPrivatnihKorisnika;
	}
	
	/**
	 * Dohvati poslovne korisnike iz baze
	 * @return
	 * @throws BazaPodatakaException
	 */
	public static List<PoslovniKorisnik> dohvatiPoslovneKorisnike() throws BazaPodatakaException {
		List<PoslovniKorisnik> listaPoslovnihKorisnika = new ArrayList<>();
		try (Connection connection = spajanjeNaBazuPodataka()) {
			StringBuilder sqlUpit = new StringBuilder(
					"SELECT distinct korisnik.id, korisnik.naziv, web, email, telefon FROM korisnik "
							+ "inner join tipKorisnika on tipKorisnika.id = korisnik.idTipKorisnika WHERE tipKorisnika.naziv = 'PoslovniKorisnik'");
			
			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String naziv = resultSet.getString("naziv");
				String web = resultSet.getString("web");
				String email = resultSet.getString("email");
				String telefon = resultSet.getString("telefon");
				PoslovniKorisnik newPoslovniKorisnik = new PoslovniKorisnik(naziv, web, email, telefon, id);
				listaPoslovnihKorisnika.add(newPoslovniKorisnik);
			}
		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return listaPoslovnihKorisnika;
	}
}
