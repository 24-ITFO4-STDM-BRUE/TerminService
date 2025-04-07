package lebk.appointment.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nachname;
    private String vorname;
    private String beschreibung;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNachname() { return nachname; }

    public void setNachname(String nachname) { this.nachname = nachname; }

    public String getVorname() { return vorname; }

    public void setVorname(String vorname) { this.vorname = vorname; }

    public String getBeschreibung() { return beschreibung; }

    public void setBeschreibung(String beschreibung) { this.beschreibung = beschreibung; }
}