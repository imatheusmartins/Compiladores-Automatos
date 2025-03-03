package com.example.automatosrgcpf.automatosrgcpf;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ValidationController {

    @PostMapping("/validateCpf")
    public ResponseEntity<Map<String, String>> validateCpf(@RequestParam("cpf") String cpf) {
        try {
            Map<String, String> response = new HashMap<>();

            String message = validateCpfFormat(cpf);

            if ("Formato do CPF inválido".equals(message)) {
                response.put("message", message);
                return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON) // Define JSON como resposta
                .body(response);
            }

            message = validateCpfDigit(cpf);
            if ("Digito do CPF inválido".equals(message)) {
                response.put("message", message);
                return ResponseEntity.badRequest()
                        .contentType(MediaType.APPLICATION_JSON) // Define JSON como resposta
                        .body(response);
            }

            response.put("message", message);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON) // Define JSON como resposta
                    .body(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Ocorreu um erro durante a verificação do CPF");
            return ResponseEntity.badRequest().body(response);
        }
    }

    public String validateCpfFormat(String cpf) {
        boolean formatResult = Utils.validateCpfFormat(cpf);

        if (!formatResult)
            return "Formato do CPF inválido";
        else
            return "Formato do CPF válido";
    }

    public String validateCpfDigit(String cpf) {
        boolean formatResult = Utils.validateCpfDigit(cpf);

        if (!formatResult)
            return "Digito do CPF inválido";
        else
            return "Digito do CPF válido";
    }

    @PostMapping("/validateRg")
    public ResponseEntity<Map<String, String>> validateRg(@RequestParam("rg") String rg, Model model) {
        Map<String, String> response = new HashMap<>();

        String message = validateRgFormat(rg);

        if (message == "Formato do RG inválido") {
            response.put("message", message);
            return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON) // Define JSON como resposta
                .body(response);
        }

        message = validateRgDigit(rg);
        if (message == "Digito do RG inválido") {
            response.put("message", message);
            return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON) // Define JSON como resposta
                .body(response);
        }

        response.put("message", message);
        return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON) // Define JSON como resposta
                    .body(response);
    }

    public String validateRgFormat(String rg) {
        boolean formatResult = Utils.validateRgFormat(rg);

        if (!formatResult)
            return "Formato do RG inválido";
        else
            return "Formato do RG válido";
    }

    public String validateRgDigit(String rg) {
        boolean formatResult = Utils.validateRgDigit(rg);

        if (!formatResult)
            return "Digito do RG inválido";
        else
            return "Digito do RG válido";
    }
}
