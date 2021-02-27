package model;

import lombok.*;
import validators.NotEqual;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder(setterPrefix = "with")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NotEqual(fields = {"start", "ende"}, message = "Start und End Bahnhof dürfen nicht der selbe sein!")
public class Zug {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
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
