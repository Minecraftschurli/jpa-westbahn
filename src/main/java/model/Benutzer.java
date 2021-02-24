package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Benutzer {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Long ID;

	private String vorName;

	private String nachName;

	@Column(unique = true)
	private String eMail;

	private String passwort;

	private String smsNummer;

	private Long verbuchtePraemienMeilen;

	private Long gesamtPraemienMeilen;

	@OneToMany
	private Collection<Ticket> tickets;

	@OneToMany(mappedBy = "benutzer")
	private Collection<Reservierung> reservierungen;

}
