package com.example.automatosrgcpf.automatosrgcpf;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


@WebMvcTest(ValidationController.class)
@AutoConfigureMockMvc
public class ValidationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @ValueSource(strings = { "123", "abcdefghijk", "00000000000", "123.456.789-0", "", " ", "08.3073.010-92", "12.345.678-X" })
    public void testCpfInvalidFormat(String cpf) throws Exception {
        mockMvc.perform(post("/validateCpf")
        .param("cpf", cpf)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Formato do CPF inválido"));
    }

    @ParameterizedTest
    @ValueSource(strings = { "12345678901", "111.111.111-10", "111222333-44", "987.654.321-01" })
    public void testCpfValidFormat(String cpf) throws Exception {
        mockMvc.perform(post("/validateCpf")
        .param("cpf", cpf)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Digito do CPF inválido"));
    }

    @ParameterizedTest
    @ValueSource(strings = { "987.654.321-02", "111222333-44", "111.111.111-10", "12345678901", "00000000001" })
    public void testCpfInvalidDigit(String cpf) throws Exception {
        mockMvc.perform(post("/validateCpf")
        .param("cpf", cpf)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Digito do CPF inválido"));
    }

    @ParameterizedTest
    @ValueSource(strings = { "76067567083", "153127520-61", "297.874.810-90" })
    public void testCpfValidDigit(String cpf) throws Exception {
        mockMvc.perform(post("/validateCpf")
        .param("cpf", cpf)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").value("Digito do CPF válido"));
    }

    @ParameterizedTest
    @ValueSource(strings = { "123", "abcdefghijk", "00000000000", "123.456.789-0", "", " ", "08.3073.010-92" })
    public void testRgInvalidFormat(String rg) throws Exception {
        mockMvc.perform(post("/validateRg")
        .param("rg", rg)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Formato do RG inválido"));
    }

    @ParameterizedTest
    @ValueSource(strings = { "320059591", "20937656-4", "19.077.533-5" })
    public void testRgValidFormat(String rg) throws Exception {
        mockMvc.perform(post("/validateRg")
        .param("rg", rg)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Digito do RG inválido"));
    }

    @ParameterizedTest
    @ValueSource(strings = { "415173013", "23044054-6", "24.905.322-3", "000000001" })
    public void testRgInvalidDigit(String rg) throws Exception {
        mockMvc.perform(post("/validateRg")
        .param("rg", rg)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Digito do RG inválido"));
    }

    @ParameterizedTest
    @ValueSource(strings = { "13.778.332-2", "254145048", "27019766-7", "24.681.357-X" })
    public void testRgValidDigit(String rg) throws Exception {
        mockMvc.perform(post("/validateRg")
        .param("rg", rg)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").value("Digito do RG válido"));
    }
}
