package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"start_id", "ende_id"}))
public class Strecke {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	@EqualsAndHashCode.Include
	private Long ID;

	@ManyToOne
	private Bahnhof start;

	@ManyToOne
	private Bahnhof ende;

}
