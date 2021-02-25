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
public abstract class Ticket {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	@Nullable
	@EqualsAndHashCode.Include
	protected Long ID;

	@NonNull //TODO move to Einzelticket
	@ManyToOne(optional = false)
	protected Strecke strecke;

	@NonNull
	@ManyToOne
	protected Benutzer benutzer;

	@Nullable
	@ManyToOne
	protected Sonderangebot sonderangebot;

	@Transient
	protected Zahlung zahlung;

}
