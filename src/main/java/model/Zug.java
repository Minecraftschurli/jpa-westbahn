package model;

import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder(setterPrefix = "with")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Zug {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	@EqualsAndHashCode.Include
	@Nullable
	private Long ID;

	@NonNull
	private Date startZeit;

	@Builder.Default
	private int sitzPlaetze = 500;

	@Builder.Default
	private int fahrradStellplaetze = 50;

	@Builder.Default
	private int rollStuhlPlaetze = 10;

	@NonNull
	@ManyToOne
	private Bahnhof start;

	@NonNull
	@ManyToOne
	private Bahnhof ende;

}
