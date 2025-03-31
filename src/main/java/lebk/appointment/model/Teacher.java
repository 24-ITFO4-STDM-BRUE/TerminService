package lebk.appointment.model;
import jakarta.persistence.*;

    @Entity
    public class Teacher {

        private String nachname;
        private String vorname;
        private String beschreibung;

        @OneToMany public Appointment appointment;

        public Appointment getAppointment() {return appointment;}

        public void setNachname(String nachname) {this.nachname = nachname;}

        public String getNachname() {return nachname;}

        public void setVorname(String vorname) {this.vorname = vorname;}

        public String getVorname() {return vorname;}

        public void setBeschreibung(String beschreibung) {this.beschreibung = beschreibung;}

        public String getBeschreibung() {return beschreibung;}
    }
