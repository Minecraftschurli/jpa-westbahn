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
class ...
```

### Markieren eines Attributes das nicht persistiert werden soll
Im Java-Sourcecode
```java
@Transient 
private Zahlung zahlung;
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
als Referenz [Inheritance Annotation](https://docs.oracle.com/javaee/7/api/javax/persistence/Inheritance.html) und
[InheritanceType Enum](https://docs.oracle.com/javaee/7/api/javax/persistence/InheritanceType.html) javadoc
```java
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) 
class ...
```

### Enum field
als Referenz [Enumerated Annotation](https://docs.oracle.com/javaee/7/api/javax/persistence/Enumerated.html) und
[EnumType Enum](https://docs.oracle.com/javaee/7/api/javax/persistence/EnumType.html) javadoc oder 
[dieses Guide auf baeldung](https://www.baeldung.com/jpa-persisting-enums-in-jpa)
```java
enum Bla {
    FOO, 
    BAR
}

@Entity
class Xyz {
    ...
    @Enumerated(EnumType.ORDINAL) 
    private Bla bla;
    ...
}
```

## Quellen
- https://docs.oracle.com/javaee/7/api/javax/persistence/OneToOne.html
- https://docs.oracle.com/javaee/7/api/javax/persistence/OneToMany.html
- https://docs.oracle.com/javaee/7/api/javax/persistence/ManyToOne.html
- https://docs.oracle.com/javaee/7/api/javax/persistence/ManyToMany.html
- https://docs.oracle.com/javaee/7/api/javax/persistence/UniqueConstraint.html
- https://docs.oracle.com/javaee/7/api/javax/persistence/Inheritance.html
- https://docs.oracle.com/javaee/7/api/javax/persistence/InheritanceType.html
- https://docs.oracle.com/javaee/7/api/javax/persistence/Enumerated.html
- https://docs.oracle.com/javaee/7/api/javax/persistence/EnumType.html
- https://www.baeldung.com/jpa-persisting-enums-in-jpa
