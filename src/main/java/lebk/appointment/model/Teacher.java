package lebk.appointment.model;
import jakarta.persistence.*;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String Nachname;
    private String Vorname;
    private String Beschreibung;
    OneToMany Appointment;

    public String getNachname(){
        return Nachname;
    }

    public String getVorname(){
        return Vorname;
    }

    public String Beschreibung(){
        return Beschreibung;
    }

    public void setNachname(String Nachname){
        this.Nachname = Nachname;
    }

    public void setVorname(String Vorname){
        this.Vorname = Vorname;
    }

    public void setBeschreibung(String Beschreibung){
        this.Beschreibung = Beschreibung;
    }
}
