package model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Preisstaffelung implements Serializable {
    @Serial
    private static final long serialVersionUID = 284884797442L;

    private final float grossGepaeck = 1.02f;

    private final float fahrrad = 1.05f;

    private final int zeitkarteWoche = 8;

    private final int zeitkarteMonat = 25;

    private final int zeitkarteJahr = 250;

    private static Preisstaffelung instance;

    public static Preisstaffelung getInstance() {
        if (instance == null)
            instance = new Preisstaffelung();
        return instance;
    }

}
