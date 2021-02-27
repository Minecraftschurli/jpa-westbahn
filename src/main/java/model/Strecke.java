package model;

import lombok.*;
import validators.NotEqual;

import javax.annotation.Nullable;
import javax.persistence.*;

@Data
@Entity
@Builder(setterPrefix = "with")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"start_id", "ende_id"}))
@NotEqual(fields = {"start", "ende"}, message = "Start und End Bahnhof dürfen nicht der selbe sein!")
public class Strecke {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
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
