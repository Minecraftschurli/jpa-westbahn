package model;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.*;
import org.hibernate.annotations.Type;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NamedQueries ({@NamedQuery(name="Bahnhof.getAll",query="SELECT b from Bahnhof b")})
public class Bahnhof {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@EqualsAndHashCode.Include
	@Column(name="id")
	private Long ID;

	@NonNull
	@Size(min=2,max=150,message="Bahnhofname muss mindestens 2 und maximal 150 Zeichen lang sein!")
	private String name;

	private int absPreisEntfernung;

	private int absKmEntfernung;

	private int absZeitEntfernung;

	@Type(type="yes_no")
	private boolean kopfBahnhof;

	public Bahnhof(@NonNull String name, int absPreisEntfernung, int absKmEntfernung, int absZeitEntfernung, boolean kopfBahnhof) {
		this.name = name;
		this.absPreisEntfernung = absPreisEntfernung;
		this.absKmEntfernung = absKmEntfernung;
		this.absZeitEntfernung = absZeitEntfernung;
		this.kopfBahnhof = kopfBahnhof;
	}
}
