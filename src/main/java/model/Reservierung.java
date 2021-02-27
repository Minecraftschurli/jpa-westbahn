package model;

import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
@Builder(setterPrefix = "with")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservierung {

    @Nullable
    @EqualsAndHashCode.Include
    private Long ID;

    @NonNull
    private Date datum;

    @Builder.Default
    private int praemienMeilenBonus = 15;

    @Builder.Default
    private int preis = 150;

    @NonNull
    @Enumerated(EnumType.ORDINAL)
    private StatusInfo status;

    @NonNull
    private Zug zug;

    @NonNull
    private Strecke strecke;

    @NonNull
    private Benutzer benutzer;

    private Zahlung zahlung;

}
