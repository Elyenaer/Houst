package support;

public class Valid {
	
	public static boolean validateCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(cpf.charAt(i));
            soma += digit * (10 - i);
        }
        int remainder = soma % 11;
        int firstVerifier = (remainder < 2) ? 0 : (11 - remainder);

        if (Character.getNumericValue(cpf.charAt(9)) != firstVerifier) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            int digit = Character.getNumericValue(cpf.charAt(i));
            soma += digit * (11 - i);
        }
        remainder = soma % 11;
        int secondVerifier = (remainder < 2) ? 0 : (11 - remainder);

        if (Character.getNumericValue(cpf.charAt(10)) != secondVerifier) {
            return false;
        }

        return true;
    }

}
