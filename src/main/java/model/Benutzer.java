package model;

import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NamedQueries({
    @NamedQuery(name = "Benutzer.getReservierungenByEmail", query = """
    SELECT DISTINCT r FROM Benutzer b
    INNER JOIN b.reservierungen r
    WHERE b.eMail = :email
    """),
    @NamedQuery(name = "Benutzer.getAllWithMonatskarte", query = """
    SELECT DISTINCT b FROM Benutzer b
    INNER JOIN b.tickets t
    WHERE TYPE(t) = Zeitkarte
    AND t.typ = model.ZeitkartenTyp.MONATSKARTE
    """)
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Benutzer {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Nullable
    private Long ID;

    @NonNull
    private String vorName;

    @NonNull
    private String nachName;

    @Email(message = "eMail muss eine valide Email-adresse sein!")
    @NonNull
    @Column(unique = true)
    private String eMail;

    @NonNull
    @ToString.Exclude
    private String passwort;

    @NonNull
    private String smsNummer;

    private Long verbuchtePraemienMeilen;

    private Long gesamtPraemienMeilen;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tickets")
    private Set<Ticket> tickets;

    @ToString.Exclude
    @OneToMany(mappedBy = "benutzer")
    private Set<Reservierung> reservierungen;

    public Benutzer(@NonNull String vorName,
                    @NonNull String nachName,
                    @NonNull String eMail,
                    @NonNull String passwort,
                    @NonNull String smsNummer) {
        this.vorName = vorName;
        this.nachName = nachName;
        this.eMail = eMail;
        this.passwort = passwort;
        this.smsNummer = smsNummer;
        this.tickets = new HashSet<>();
        this.reservierungen = new HashSet<>();
    }
}
