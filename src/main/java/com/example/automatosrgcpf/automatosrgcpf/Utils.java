package com.example.automatosrgcpf.automatosrgcpf;

public class Utils {

    enum cpfStates {
        Q0, Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10, Q11, Q12, Q13, Q14, Q15, Q16, Q17, Q18, Q19, Q20, Q21, ERRO
    };

    enum rgStates {
        Q0, Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10, Q11, Q12, Q13, Q14, Q15, Q16, Q17, Q18, ERRO
    };

    public static boolean validateCpfFormat(String cpf) {
        final String cpfVerify = cpf.replace(".", "").replace("-", "").trim();

        if (cpfVerify.length() != 11 || cpfVerify.chars().allMatch(c -> c == cpfVerify.charAt(0))) {
            return false;
        }

        cpfStates cpfState = cpfStates.Q0;
        for (char c : cpf.trim().toLowerCase().toCharArray()) {
            switch (cpfState) {
                case Q0:
                    cpfState = (Character.isDigit(c)) ? cpfStates.Q1 : cpfStates.ERRO;
                    break;
                case Q1:
                    cpfState = (Character.isDigit(c)) ? cpfStates.Q2 : cpfStates.ERRO;
                    break;
                case Q2:
                    cpfState = (Character.isDigit(c)) ? cpfStates.Q3 : cpfStates.ERRO;
                    break;
                case Q3: {
                    if (Character.isDigit(c))
                        cpfState = cpfStates.Q15;
                    else if (c == '.')
                        cpfState = cpfStates.Q4;
                    else
                        cpfState = cpfStates.ERRO;
                }
                    break;
                case Q4:
                    cpfState = (Character.isDigit(c)) ? cpfStates.Q5 : cpfStates.ERRO;
                    break;
                case Q5:
                    cpfState = (Character.isDigit(c)) ? cpfStates.Q6 : cpfStates.ERRO;
                    break;
                case Q6:
                    cpfState = (Character.isDigit(c)) ? cpfStates.Q7 : cpfStates.ERRO;
                    break;
                case Q7:
                    cpfState = (c == '.') ? cpfStates.Q8 : cpfStates.ERRO;
                    break;
                case Q8:
                    cpfState = (Character.isDigit(c)) ? cpfStates.Q9 : cpfStates.ERRO;
                    break;
                case Q9:
                    cpfState = (Character.isDigit(c)) ? cpfStates.Q10 : cpfStates.ERRO;
                    break;
                case Q10:
                    cpfState = (Character.isDigit(c)) ? cpfStates.Q11 : cpfStates.ERRO;
                    break;
                case Q11:
                    cpfState = (c == '-') ? cpfStates.Q12 : cpfStates.ERRO;
                    break;
                case Q12:
                    cpfState = (Character.isDigit(c)) ? cpfStates.Q13 : cpfStates.ERRO;
                    break;
                case Q13:
                    cpfState = (Character.isDigit(c)) ? cpfStates.Q14 : cpfStates.ERRO;
                    break;
                case Q14:
                    break;
                case Q15:
                    cpfState = (Character.isDigit(c)) ? cpfStates.Q16 : cpfStates.ERRO;
                    break;
                case Q16:
                    cpfState = (Character.isDigit(c)) ? cpfStates.Q17 : cpfStates.ERRO;
                    break;
                case Q17:
                    cpfState = (Character.isDigit(c)) ? cpfStates.Q18 : cpfStates.ERRO;
                    break;
                case Q18:
                    cpfState = (Character.isDigit(c)) ? cpfStates.Q19 : cpfStates.ERRO;
                    break;
                case Q19:
                    cpfState = (Character.isDigit(c)) ? cpfStates.Q20 : cpfStates.ERRO;
                    break;
                case Q20: {
                    if (Character.isDigit(c))
                        cpfState = cpfStates.Q13;
                    else if (c == '-')
                        cpfState = cpfStates.Q12;
                    else
                        cpfState = cpfStates.ERRO;
                }
                    break;
                default:
                    cpfState = cpfStates.ERRO;
                    break;
            }

            if (cpfState == cpfStates.ERRO)
                break;
        }

        return cpfState == cpfStates.Q14;
    }

    public static boolean validateRgFormat(String rg) {
        final String rgVerify = rg.replace(".", "").replace("-", "").trim();
        rgStates rgState = rgStates.Q0;

        if (rgVerify.chars().allMatch(c -> c == rgVerify.charAt(0))) {
            return false;
        }
        for (char c : rg.trim().toLowerCase().toCharArray()) {
            switch (rgState) {
                case Q0:
                    rgState = (Character.isDigit(c)) ? rgStates.Q1 : rgStates.ERRO;
                    break;
                case Q1:
                    rgState = (Character.isDigit(c)) ? rgStates.Q2 : rgStates.ERRO;
                    break;
                case Q2: {
                    if (Character.isDigit(c))
                        rgState = rgStates.Q3;
                    else if (c == '.')
                        rgState = rgStates.Q9;
                    else
                        rgState = rgStates.ERRO;
                }
                    break;
                case Q3:
                    rgState = (Character.isDigit(c)) ? rgStates.Q4 : rgStates.ERRO;
                    break;
                case Q4:
                    rgState = (Character.isDigit(c)) ? rgStates.Q5 : rgStates.ERRO;
                    break;
                case Q5:
                    rgState = (Character.isDigit(c)) ? rgStates.Q6 : rgStates.ERRO;
                    break;
                case Q6:
                    rgState = (Character.isDigit(c)) ? rgStates.Q7 : rgStates.ERRO;
                    break;
                case Q7:
                    rgState = (Character.isDigit(c)) ? rgStates.Q8 : rgStates.ERRO;
                    break;
                case Q8:{
                   if(Character.isDigit(c) || c == 'x')
                   rgState = rgStates.Q18;
                   else if(c == '-')
                   rgState = rgStates.Q17;
                   else
                   rgState = rgStates.ERRO;
                }
                    break;
                case Q9:
                    rgState = (Character.isDigit(c)) ? rgStates.Q10 : rgStates.ERRO;
                    break;
                case Q10:
                    rgState = (Character.isDigit(c)) ? rgStates.Q11 : rgStates.ERRO;
                    break;
                case Q11:
                    rgState = (Character.isDigit(c)) ? rgStates.Q12 : rgStates.ERRO;
                    break;
                case Q12:
                    rgState = (c == '.') ? rgStates.Q13 : rgStates.ERRO;
                    break;
                case Q13:
                    rgState = (Character.isDigit(c)) ? rgStates.Q14 : rgStates.ERRO;
                    break;
                case Q14:
                    rgState = (Character.isDigit(c)) ? rgStates.Q15 : rgStates.ERRO;
                    break;
                case Q15:
                    rgState = (Character.isDigit(c)) ? rgStates.Q16 : rgStates.ERRO;
                    break;
                case Q16:
                    rgState = (c == '-') ? rgStates.Q17 : rgStates.ERRO;
                    break;
                case Q17:
                    rgState = (Character.isDigit(c) || c == 'x') ? rgStates.Q18 : rgStates.ERRO;
                    break;
                case Q18:
                    break;
                default:
                rgState = rgStates.ERRO;
                    break;
            }

            if (rgState == rgStates.ERRO)
                break;
        }

        return rgState == rgStates.Q18;
    }

    public static boolean validateCpfDigit(String cpf){
        cpf = cpf.replace(".", "").replace("-", "").trim();
        int div = 11;
        int multi = 10;
        int sum = 0;
        int firstDigit = 0;
        int secondDigit = 0;

        for (char number : cpf.toCharArray()) {
            sum += (number - '0') * multi;
            multi--;

            if(multi < 2)
                break;
        }

        firstDigit = Math.abs((sum % div) - div);
        if(firstDigit > 9)
            firstDigit = 0;

        if((cpf.charAt(cpf.length() - 2) - '0') != firstDigit)
            return false;

        multi = 10;
        sum = 0;

        for (char number : cpf.substring(1).toCharArray()) {
            sum += (number - '0') * multi;
            multi--;

            if(multi < 2)
                break;
        }
        
        secondDigit = Math.abs((sum % div) - div);
        if(secondDigit > 9)
            secondDigit = 0;

        var test = cpf.charAt(cpf.length() - 1) - '0';
        return test == secondDigit;
    }

    public static boolean validateRgDigit(String rg){
        rg = rg.replace(".", "").replace("-", "").trim();
        int div = 11;
        int multi = 2;
        int sum = 0;
        int digit = 0;

        for (char number : rg.toCharArray()) {
            sum += (number - '0') * multi;
            multi++;

            if(multi > 9)
                break;
        }

        digit = Math.abs((sum % div) - div);
        if(digit > 10)
            digit = 0;

        if(digit == 10 && rg.toLowerCase().charAt(rg.length() - 1) == 'x'){
            return true;
        }


        return digit == (rg.charAt(rg.length() - 1) - '0');
    }

    
}
