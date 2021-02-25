package model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity
@SuperBuilder(setterPrefix = "with")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Einzelticket extends Ticket {

	@NonNull
	@Enumerated(EnumType.ORDINAL)
	private TicketOption ticketOption;

}
