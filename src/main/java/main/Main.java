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
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

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
        Strecke s2 = Strecke.builder()
                .withStart(wien)
                .withEnde(linz)
                .build();
        list.add(s2);
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
                .withStrecke(s)
                .withBenutzer(gburkl)
                .build();
        list.add(mk);
        Einzelticket et = Einzelticket.builder()
                .withBenutzer(gburkl)
                .withStrecke(s2)
                .withTicketOption(TicketOption.FAHRRAD)
                .build();
        list.add(et);
        for (Object o : list)
            entitymanager.persist(o);
        entitymanager.flush();
        entitymanager.getTransaction().commit();
    }

    public static <T> void task02() throws ParseException {
        TypedQuery<Bahnhof> q = entitymanager.createNamedQuery("Bahnhof.getAll", Bahnhof.class);

        List<Bahnhof> l = q.getResultList();

        for (Bahnhof b : l) {
            if (b != null) {
                log.info("Bahnhof: " + b.getName());
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
        TypedQuery<Bahnhof> q = entitymanager.createQuery("SELECT b FROM Bahnhof b WHERE b.name = :name", Bahnhof.class);
        Bahnhof wien = q.setParameter("name", "WienHbf").getSingleResult();
        Bahnhof linz = q.setParameter("name", "Linz-Ost").getSingleResult();
        entitymanager.createNamedQuery("Ticket.getWithoutReservierungForStrecke", Ticket.class)
                .setParameter("start", wien)
                .setParameter("ende", linz)
                .getResultList()
                .forEach(log::info);
    }

    public static void task03(EntityManager em) throws ParseException {
        List<Object> list = new LinkedList<>();
        Bahnhof testBhf1 = new Bahnhof("WienHbf", 0, 0, 0, false);
        Bahnhof testBhf2 = new Bahnhof("Huetteldorf", 0, 0, 0, false);

        //region Strecke
        list.add(Strecke.builder()
                .withStart(testBhf1)
                .withEnde(testBhf2)
                .build());
        list.add(Strecke.builder()
                .withStart(testBhf1)
                .withEnde(testBhf1)
                .build());
        //endregion
        //region Zug
        list.add(Zug.builder()
                .withStart(testBhf1)
                .withEnde(testBhf2)
                .withStartZeit(new Date())
                .build());
        list.add(Zug.builder()
                .withStart(testBhf1)
                .withEnde(testBhf1)
                .withStartZeit(new Date())
                .build());
        //endregion
        //region Benutzer
        list.add(Benutzer.builder()
                .withEMail("valid@email.com")
                .withNachName("test")
                .withVorName("test")
                .withPasswort("test")
                .withSmsNummer("+430000000000")
                .build());
        list.add(Benutzer.builder()
                .withEMail("not-valid")
                .withNachName("test")
                .withVorName("test")
                .withPasswort("test")
                .withSmsNummer("+430000000000")
                .build());
        //endregion
        //region Sonderangebot
        list.add(Sonderangebot.builder()
                .withStartZeit(dateForm.parse("26.03.2021"))
                .build());
        list.add(Sonderangebot.builder()
                .withStartZeit(dateForm.parse("01.01.2021"))
                .build());
        //endregion
        //region Bahnhof
        list.add(new Bahnhof("aaa", 0, 0, 0, false));
        list.add(new Bahnhof("a", 0, 0, 0, false));
        list.add(new Bahnhof("a".repeat(151), 0, 0, 0, false));
        list.add(new Bahnhof("1aaa", 0, 0, 0, false));
        list.add(testBhf1);
        //endregion

        list.forEach(Main::validate);
    }

    public static void validate(Object obj) {
        ValidatorFactory factory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(obj);
        for (ConstraintViolation<Object> violation : violations) {
            log.error(violation.getMessage());
        }
    }
}
