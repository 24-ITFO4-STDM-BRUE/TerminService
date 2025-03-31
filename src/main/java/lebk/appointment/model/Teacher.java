package lebk.appointment.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Nachname;
    private String Vorname;
    private String Beschreibung;

    @OneToMany(mappedBy = "Teacher",cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter und Setter für Nachname
    public String getNachname() {
        return Nachname;
    }

    public void setNachname(String nachname) {
        this.Nachname = nachname;
    }

    // Getter und Setter für Vorname
    public String getVorname() {
        return Vorname;
    }

    public void setVorname(String vorname) {
        this.Vorname = vorname;
    }

    // Getter und Setter für Beschreibung
    public String getBeschreibung() {
        return Beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.Beschreibung = beschreibung;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

}
