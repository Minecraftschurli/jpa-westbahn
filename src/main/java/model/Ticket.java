package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Ticket {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	@EqualsAndHashCode.Include
	protected Long ID;

	@ManyToOne
	protected Strecke strecke;

	@Transient
	protected Zahlung zahlung;

}
