package model;

import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Builder(setterPrefix = "with")
@NamedQueries({
		@NamedQuery(name = "Benutzer.getReservierungenByEmail", query = "SELECT DISTINCT r FROM Benutzer b INNER JOIN b.reservierungen r WHERE b.eMail = :email"),
		@NamedQuery(name = "Benutzer.getAllWithMonatskarte", query = "SELECT DISTINCT b FROM Benutzer b INNER JOIN b.tickets t WHERE TYPE(t) = Zeitkarte AND t.typ = model.ZeitkartenTyp.MONATSKARTE")
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Benutzer {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	@Nullable
	private Long ID;

	@NonNull
	private String vorName;

	@NonNull
	private String nachName;

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

	@ToString.Exclude
	@Singular("ticket")
	@OneToMany(mappedBy = "benutzer")
	private Collection<Ticket> tickets;

	@ToString.Exclude
	@Singular("reservierung")
	@OneToMany(mappedBy = "benutzer")
	private Collection<Reservierung> reservierungen;

}
