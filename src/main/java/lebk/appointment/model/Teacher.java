package lebk.appointment.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;
    private String vorname;
    private String nachname;
    private  String beschreibung;


    public Long getTId() {return tid; }

    public void setTId(Long tid) { this.tid= tid; }

    public String getNachname() {return nachname; }

    public void setNachname(String nachname) { this.nachname=nachname; }

    public String getVorname() {return vorname;}

    public void setVorname(String vorname) { this.vorname=vorname; }

    public String getBeschreibung() {return beschreibung;}

    public void setBeschreibung(String de) { this.beschreibung=beschreibung; }

    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private List<Appointment> appointments;
}