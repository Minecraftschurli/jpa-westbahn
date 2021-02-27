package model;

import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Past;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Builder(setterPrefix = "with")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sonderangebot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    @Nullable
    private Long ID;

    @Builder.Default
    private int kontingent = 999;

    @NonNull
    @FutureOrPresent(message = "Die startZeit des Sonderangebots darf nicht in der Vergangenheit liegen!")
    private Date startZeit;

    @Builder.Default
    private int dauer = 12;

    @Builder.Default
    private float preisNachlass = 0.5f;

    @Singular
    @OneToMany(mappedBy = "sonderangebot")
    private Collection<Ticket> tickets;

}
