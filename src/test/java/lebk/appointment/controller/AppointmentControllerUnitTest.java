package lebk.appointment.controller;

import lebk.appointment.controller.AppointmentController;
import lebk.appointment.repository.AppointmentRepository;
import lebk.appointment.service.AppointmentService;
import lebk.appointment.model.Appointment;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AppointmentController.class) // Startet NUR den Controlle
public class AppointmentControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentRepository appointmentRepository; // Repo wird gemockt

    @MockBean
    private AppointmentService appointmentService; // <-- Service wird hier gemockt!

    @Autowired
    private ObjectMapper objectMapper;

    private Appointment appointment;

    @BeforeEach
    public void setUp() {
        appointment = new Appointment();
        appointment.setId(1L);
        appointment.setTitle("Unit Test Appointment");
        appointment.setDescription("Unit Test Description");
        appointment.setDateTime(LocalDateTime.of(2025, 3, 22, 10, 0));
    }

    @Test
    public void shouldReturnAllAppointments() throws Exception {
        List<Appointment> appointments = List.of(appointment); // appointment aus setUp()

        Mockito.when(appointmentService.getAllAppointments()).thenReturn(appointments);

        mockMvc.perform(get("/appointments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1)); // <-- Hier wird erwartet, dass die Liste 1 Element hat
    }

    @Test
    public void shouldCreateAppointment() throws Exception {
        Appointment requestAppointment = new Appointment();
        requestAppointment.setTitle("Unit Test Appointment");
        requestAppointment.setDescription("Unit Test Description");
        requestAppointment.setDateTime(LocalDateTime.of(2025, 3, 22, 10, 0));

        Mockito.when(appointmentService.createAppointment(any(Appointment.class))).thenReturn(appointment);

        mockMvc.perform(post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestAppointment)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Unit Test Appointment"));
    }
}
