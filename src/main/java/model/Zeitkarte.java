package model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
@Entity
@SuperBuilder(setterPrefix = "with")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Zeitkarte extends Ticket {

    @NonNull
    private Date gueltigAb;

    @NonNull
    private Date gueltigBis;

    @NonNull
    @Enumerated(EnumType.ORDINAL)
    private ZeitkartenTyp typ;

}
