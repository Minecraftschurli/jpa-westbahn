package model;

import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import java.util.*;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sonderangebot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    @Nullable
    private Long ID;

    private int kontingent = 999;

    @NonNull
    @FutureOrPresent(message = "Die startZeit des Sonderangebots darf nicht in der Vergangenheit liegen!")
    private Date startZeit;

    private int dauer = 12;

    private float preisNachlass = 0.5f;

    @OneToMany
    private Set<Ticket> tickets;

    public Sonderangebot(@NonNull Date startZeit){
        this.startZeit = startZeit;
        this.tickets = new HashSet<>();
    }
}
