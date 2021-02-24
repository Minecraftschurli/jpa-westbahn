package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reservierung {

	@EqualsAndHashCode.Include
	private Long ID;

	private Date datum;

	private int praemienMeilenBonus = 15;

	private int preis = 150;

	private StatusInfo status;

	private Zug zug;

	private Strecke strecke;

	//0..1
	private Benutzer benutzer;

	private Zahlung zahlung;

}
