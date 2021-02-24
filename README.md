# Webframeworks - Komponentenbasierte Implementierung "Westbahn mit ORM"

## Aufgabenstellung
Die detaillierte [Aufgabenstellung](TASK.md) beschreibt die notwendigen Schritte zur Realisierung.

## Implementierung
### Relationships
#### 1 zu 1
[see OneToOne Annotation javadoc](https://docs.oracle.com/javaee/7/api/javax/persistence/OneToOne.html)
```java
@OneToOne
private Bla bla;
```
#### 1 zu N
[see OneToMany Annotation javadoc](https://docs.oracle.com/javaee/7/api/javax/persistence/OneToMany.html)
```java
@OneToMany
private Collection<Bla> blas;
```
#### N zu 1
[see ManyToOne Annotation javadoc](https://docs.oracle.com/javaee/7/api/javax/persistence/ManyToOne.html)
```java
@ManyToOne
private Bla bla;
```
#### M zu N
[see ManyToMany Annotation javadoc](https://docs.oracle.com/javaee/7/api/javax/persistence/ManyToMany.html)
```java
@ManyToMany
private Collection<Bla> blas;
```

### UniqueConstraint auf 2 ForeignKey Columns
[see UniqueConstraint Annotation javadoc](https://docs.oracle.com/javaee/7/api/javax/persistence/UniqueConstraint.html)
```java
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"start_id", "ende_id"}))
```

### Markieren eines Attributes das nicht persistiert werden soll
Im Java-Sourcecode
```java
@Transient private Zahlung zahlung;
```
oder
```java
private transient Zahlung zahlung;
```
Im mapping xml
```xml
<transient name="zahlung"/>
```

### Inheritance
[see Inheritance Annotation javadoc](https://docs.oracle.com/javaee/7/api/javax/persistence/Inheritance.html)
[and InheritanceType javadoc](https://docs.oracle.com/javaee/7/api/javax/persistence/InheritanceType.html)
```java
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
```

## Quellen
