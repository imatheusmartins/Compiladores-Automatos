package com.example.automatosrgcpf.automatosrgcpf;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.automatosrgcpf.automatosrgcpf.Utils;

@SpringBootTest(classes = Utils.class)
public class UtilsTests {
    
    @ParameterizedTest
    @ValueSource(strings = {"12345678901", "987.654.321-00", "111.222.333-44"})
    public void testCpfValidFormat(String cpf) {
        assertTrue(Utils.validateCpfFormat(cpf), "CPF deveria ser válido: " + cpf);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "abcdefghijk", "0000000000", "123.456.789-0", "", " "})
    public void testCpfInvalidFormat(String cpf) {
        assertFalse(Utils.validateCpfFormat(cpf), "CPF deveria ser inválido: " + cpf);
    }

    @ParameterizedTest
    @ValueSource(strings = {"083073010-92", "493.918.250-95", "17875509076"}) 
    public void testCpfValidDigit(String cpf) {
        assertTrue(Utils.validateCpfDigit(cpf), "CPF deveria ter dígito correto: " + cpf);
    }

    @ParameterizedTest
    @ValueSource(strings = {"083073019-92", "493.918.250-15", "1787550907"}) 
    public void testCpfInvalidDigit(String cpf) {
        assertFalse(Utils.validateCpfDigit(cpf), "CPF deveria ter dígito incorreto: " + cpf);
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345678-9", "12.345.678-9", "987654321", "11.111.111-0"})
    public void testRgValidFormat(String rg) {
        assertTrue(Utils.validateRgFormat(rg), "RG deveria ser válido: " + rg);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "abcdefg", "0000000000", "12.345.678-", "", " "})
    public void testRgInvalidFormat(String rg) {
        assertFalse(Utils.validateRgFormat(rg), "RG deveria ser inválido: " + rg);
    }

    @ParameterizedTest
    @ValueSource(strings = {"294410016", "403244158", "283399788", "22.650.018-4", "21.954.778-6", "17.375.700-5", "11.379.050-8"})
    public void testRgValidDigit(String rg) {
        assertTrue(Utils.validateRgDigit(rg), "RG deveria ter dígito correto: " + rg);
    }

    @ParameterizedTest
    @ValueSource(strings = {"219547136", "226500394", "173757895", "113791518", "22.650.039-4", "21.954.713-6", "17.375.789-5", "11.379.151-8"}) 
    public void testRgInvalidDigit(String rg) {
        assertFalse(Utils.validateRgDigit(rg), "RG deveria ter dígito incorreto: " + rg);
    }
}
