# Fragestellungen
## Was ist ORM und wie kommt dabei JPA zum Einsatz?
__ORM__ steht für ObjectRelationalMapping und bezeichnet das direkte Verknüpfen eines oder mehrerer Relationaler 
Datenbank Tabellen mit Objekten einer objektorientierten Programmiersprache.    
__JPA__ steht für JavaPersistenceApi und ist eine standard API der Java EE (EnterpriseEdition). Sie ermöglicht die 
Datenpersistenz mittels __ORM__.

## Welche Vorteile bietet die komponentenbasierte Implementierung in einer Webapplikation?

## Was ist die PersistenceUnit und wo wird diese definiert?
Eine PersistenceUnit ist eine logische gruppierung von benutzerdefinierten Persistenz-klassen (z.B.: `@Entity`Klassen, 
`@Embeddable`Klassen oder gemappte superklassen) mit den dazugehörigen einstellungen. Sie wird in der `persistence.xml` 
definiert.

## Wozu dient die application.properties und wo muss diese abgelegt werden?
Die `application.properties` oder `application.yml` Datei enthält die Konfigurationsinformationen für ein Framework 
(z.B.: Spring)

## Welche Annotationen kommen häufig bei Entitätstypen zum Einsatz? Welche Eckpunkte müssen beachtet werden?
`@Entity`, `@Id`, `@GeneratedValue` und `@Column`. Bzw bei verwendung von Lombok auch noch `@Data` und 
`@NoArgsConstructor`

## Wie ruft man in JavaSE den EntityManager auf?

## Welche Methoden benötigt man für CRUD und worauf muss man dabei achten?
 - Create
 - Read
 - Update
 - Delete

# Quellen
- https://www.objectdb.com/java/jpa/entity/persistence-unit#:~:text=A%20JPA%20Persistence%20Unit%20is,ObjectDB%2C%20but%20required%20by%20JPA.