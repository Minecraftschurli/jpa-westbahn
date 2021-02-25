package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

import javax.persistence.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.*;

import model.*;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public class Main {

	private static final Logger log = Logger.getLogger(Main.class);

        // TODO Check PersistenceUnit Definition
	private static EntityManagerFactory sessionFactory;
	@PersistenceContext
	private static EntityManager entitymanager;
	
	static SimpleDateFormat dateForm = new SimpleDateFormat("dd.MM.yyyy");
	static SimpleDateFormat timeForm = new SimpleDateFormat("dd.MM.yyyy mm:hh");

	private Main() {
		super();
	}

	public static void main(String[] args) {
		
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.INFO);
		
		try {
			log.info("Starting \"Mapping Perstistent Classes and Associations\" (task1)");
			sessionFactory = Persistence.createEntityManagerFactory("westbahn");
			entitymanager = sessionFactory.createEntityManager();
			fillDB(entitymanager);
			task01();
			log.info("Starting \"Working with JPA-QL and the Hibernate Criteria API\" (task2)");
			task02();
			task02a();
			task02b();
			task02c();
			task03(entitymanager);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (entitymanager.getTransaction().isActive())
				entitymanager.getTransaction().rollback();
			entitymanager.close();
			sessionFactory.close();
		}
	}

	public static void fillDB(EntityManager em) throws ParseException {
		em.getTransaction().begin();
		List<Bahnhof> list = new ArrayList<Bahnhof>();
		list.add(new Bahnhof("WienHbf", 0, 0, 0, true));
		list.add(new Bahnhof("SalzburgHbf", 20, 60, 120, true));
		list.add(new Bahnhof("Amstetten", 40, 124, 169, false));
		list.add(new Bahnhof("Linz-Ost", 140, 320, 250, false));
		list.add(new Bahnhof("Huetteldorf", 3, 5, 19, false));
		list.add(new Bahnhof("Wels-Zentrum", 102, 400, 250, true));
		for (Bahnhof b : list)
			em.persist(b);
		em.flush();
		em.getTransaction().commit();
	}

	public static void task01() throws ParseException, InterruptedException {
		entitymanager.getTransaction().begin();
		TypedQuery<Bahnhof> q = entitymanager.createQuery("SELECT b FROM Bahnhof b WHERE b.name = :name", Bahnhof.class);
		Bahnhof wien = q.setParameter("name", "WienHbf").getSingleResult();
		Bahnhof wels = q.setParameter("name", "Wels-Zentrum").getSingleResult();
		Bahnhof linz = q.setParameter("name", "Linz-Ost").getSingleResult();
		List<Object> list = new LinkedList<>();
		Benutzer gburkl = Benutzer.builder()
				.withVorName("Georg")
				.withNachName("Burkl")
				.withEMail("gburkl@student.tgm.ac.at")
				.withPasswort("bla1234")
				.withSmsNummer("+4369966996699")
				.build();
		list.add(gburkl);
		Strecke s = Strecke.builder()
				.withStart(wien)
				.withEnde(wels)
				.build();
		list.add(s);
		Zug z = Zug.builder()
				.withStart(wien)
				.withEnde(linz)
				.withFahrradStellplaetze(10)
				.withRollStuhlPlaetze(4)
				.withSitzPlaetze(120)
				.withStartZeit(new Date())
				.build();
		list.add(z);
		Reservierung r = Reservierung.builder()
				.withBenutzer(gburkl)
				.withDatum(new Date())
				.withStrecke(s)
				.withStatus(StatusInfo.ONTIME)
				.withZug(z)
				.build();
		list.add(r);
		Zeitkarte mk = Zeitkarte.builder()
				.withGueltigAb(dateForm.parse("24.02.2021"))
				.withGueltigBis(dateForm.parse("24.03.2021"))
				.withTyp(ZeitkartenTyp.MONATSKARTE)
				.withBenutzer(gburkl)
				.withStrecke(s)
				.build();
		list.add(mk);
		for (Object o : list)
			entitymanager.persist(o);
		entitymanager.flush();
		entitymanager.getTransaction().commit();
	}

	public static <T> void task02() throws ParseException {
		Query q = entitymanager.createNamedQuery("Bahnhof.getAll");

		List<?> l = q.getResultList();

		for (Object b : l) {
			Bahnhof bhf = null;
			if (b instanceof Bahnhof) {
				bhf = (Bahnhof) b;
				log.info("Bahnhof: " + bhf.getName());
			}
		}
	}

	public static void task02a() throws ParseException {
		entitymanager.createNamedQuery("Benutzer.getReservierungenByEmail", Reservierung.class)
				.setParameter("email", "gburkl@student.tgm.ac.at")
				.getResultList()
				.forEach(log::info);
	}

	public static void task02b() throws ParseException {
		entitymanager.createNamedQuery("Benutzer.getAllWithMonatskarte", Benutzer.class)
				.getResultList()
				.forEach(log::info);
	}

	public static void task02c() throws ParseException {
	}

	public static void task03(EntityManager em) {
	}

	public static void validate(Object obj) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(obj);
		for (ConstraintViolation<Object> violation : violations) {
			log.error(violation.getMessage());
			System.out.println(violation.getMessage());
		}
	}
}
