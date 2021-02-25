package model;

import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.*;

@Data
@Entity
@Builder(setterPrefix = "with")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"start_id", "ende_id"}))
public class Strecke {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	@EqualsAndHashCode.Include
	@Nullable
	private Long ID;

	@NonNull
	@ManyToOne
	private Bahnhof start;

	@NonNull
	@ManyToOne
	private Bahnhof ende;

}
