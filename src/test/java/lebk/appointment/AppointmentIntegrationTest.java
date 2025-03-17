package lebk.appointment;

import com.fasterxml.jackson.databind.ObjectMapper;
import lebk.appointment.model.Appointment;
import lebk.appointment.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest // Startet die gesamte Spring-Anwendung inkl. DB
@AutoConfigureMockMvc // Initialisiert MockMvc für HTTP-Anfragen
public class AppointmentIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    public void cleanDatabase() {
        appointmentRepository.deleteAll();
    }

    @Test
    public void shouldCreateAndRetrieveAppointment() throws Exception {
        // Arrange: Erstelle einen neuen Termin
        Appointment appointment = new Appointment();
        appointment.setTitle("Test Meeting");
        appointment.setDescription("Integration Test Meeting");
        appointment.setDateTime(LocalDateTime.of(2025, 3, 20, 14, 0));

        // Act: POST /appointments
        mockMvc.perform(post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appointment)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("Test Meeting"));

        // Assert: Überprüfen, ob der Termin tatsächlich in der DB ist
        mockMvc.perform(get("/appointments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Test Meeting"));
    }
}