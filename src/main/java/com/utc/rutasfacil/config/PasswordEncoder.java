package com.utc.rutasfacil.config;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Codificador de contraseñas usando SHA-256 con salt.
 * Para producción, considerar usar Spring Security BCryptPasswordEncoder
 */
public class PasswordEncoder {

    private static final int SALT_LENGTH = 16;
    private static final String ALGORITHM = "SHA-256";

    /**
     * Encripta una contraseña con salt
     */
    public static String encode(String password) {
        try {
            // Generar salt aleatorio
            byte[] salt = new byte[SALT_LENGTH];
            SecureRandom random = new SecureRandom();
            random.nextBytes(salt);

            // Crear instancia de MessageDigest
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(salt);

            // Hash la contraseña
            byte[] hashedPassword = md.digest(password.getBytes());

            // Combinar salt + hash
            byte[] saltAndHash = new byte[salt.length + hashedPassword.length];
            System.arraycopy(salt, 0, saltAndHash, 0, salt.length);
            System.arraycopy(hashedPassword, 0, saltAndHash, salt.length, hashedPassword.length);

            // Codificar en Base64
            return Base64.getEncoder().encodeToString(saltAndHash);
        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar contraseña", e);
        }
    }

    /**
     * Verifica una contraseña contra su hash
     */
    public static boolean matches(String password, String encodedPassword) {
        try {
            // Decodificar el hash guardado
            byte[] saltAndHash = Base64.getDecoder().decode(encodedPassword);

            // Extraer salt
            byte[] salt = new byte[SALT_LENGTH];
            System.arraycopy(saltAndHash, 0, salt, 0, SALT_LENGTH);

            // Hash la contraseña proporcionada con el mismo salt
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());

            // Comparar
            byte[] storedHash = new byte[saltAndHash.length - SALT_LENGTH];
            System.arraycopy(saltAndHash, SALT_LENGTH, storedHash, 0, storedHash.length);

            return MessageDigest.isEqual(hashedPassword, storedHash);
        } catch (Exception e) {
            throw new RuntimeException("Error al verificar contraseña", e);
        }
    }
}
