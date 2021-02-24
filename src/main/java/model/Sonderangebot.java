package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sonderangebot {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	@EqualsAndHashCode.Include
	private Long ID;

	private int kontingent = 999;

	private Date startZeit;

	private int dauer = 12;

	private float preisNachlass = 0.5f;

	@OneToMany
	private Collection<Ticket> tickets;

}
