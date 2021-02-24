package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Zug {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	@EqualsAndHashCode.Include
	private Long ID;

	private Date startZeit;

	private int sitzPlaetze = 500;

	private int fahrradStellplaetze = 50;

	private int rollStuhlPlaetze = 10;

	@ManyToOne
	private Bahnhof start;

	@ManyToOne
	private Bahnhof ende;

}
