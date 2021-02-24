package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.util.Date;

@Data
@Entity
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Zeitkarte extends Ticket {

	private Date gueltigAb;

	private Date gueltigBis;

	private ZeitkartenTyp typ;

}
