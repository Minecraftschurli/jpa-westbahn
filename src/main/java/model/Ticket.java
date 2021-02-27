package model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.annotation.Nullable;
import javax.persistence.*;

@Data
@Entity
@SuperBuilder(setterPrefix = "with")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQueries({
    @NamedQuery(name = "Ticket.getWithoutReservierungForStrecke", query = """
    SELECT DISTINCT t FROM Benutzer b
    INNER JOIN b.tickets t
    WHERE t.strecke.ende = :ende
    AND t.strecke.start = :start
    AND t.strecke NOT IN (SELECT r.strecke FROM b.reservierungen r)
    """)
})
public abstract class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Nullable
    @EqualsAndHashCode.Include
    protected Long ID;

    //TODO move to Einzelticket
    @NonNull
    @ManyToOne(optional = false)
    protected Strecke strecke;

    //TODO fix assoziation
	@NonNull
	@ManyToOne
	protected Benutzer benutzer;

    //TODO fix assoziation
	@Nullable
	@ManyToOne
	protected Sonderangebot sonderangebot;

    @Transient
    protected Zahlung zahlung;

}
