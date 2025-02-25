package com.example.automatosrgcpf.automatosrgcpf;

public class Utils {
    
    public static String validarCpf(String cpf) {
        int estado = 0;
        for (char c : cpf.toCharArray()) {
            switch (estado) {
                case 0:
                case 1:
                case 2:
                    if (Character.isDigit(c)) {
                        estado++;
                    } else {
                        return "Erro de sintaxe: esperado um dígito.";
                    }
                    break;
                case 3:
                    if (c == '.') {
                        estado++;
                    } else if (Character.isDigit(c)) {
                        estado = 6;
                    } else {
                        return "Erro de sintaxe: esperado '.' ou dígito.";
                    }
                    break;
                case 4:
                case 5:
                case 6:
                    if (Character.isDigit(c)) {
                        estado++;
                    } else {
                        return "Erro de sintaxe: esperado um dígito.";
                    }
                    break;
                case 7:
                    if (c == '.') {
                        estado++;
                    } else {
                        return "Erro de sintaxe: esperado '.'";
                    }
                    break;
                case 8:
                case 9:
                case 10:
                    if (Character.isDigit(c)) {
                        estado++;
                    } else {
                        return "Erro de sintaxe: esperado um dígito.";
                    }
                    break;
                case 11:
                    if (c == '-') {
                        estado++;
                    } else {
                        return "Erro de sintaxe: esperado '-'";
                    }
                    break;
                case 12:
                case 13:
                    if (Character.isDigit(c)) {
                        estado++;
                    } else {
                        return "Erro de sintaxe: esperado um dígito.";
                    }
                    break;
                default:
                    return "Erro de sintaxe: formato inválido.";
            }
        }
        return (estado == 11 || estado == 13) ? "CPF válido" : "Erro de sintaxe: formato incompleto.";
    }
}
