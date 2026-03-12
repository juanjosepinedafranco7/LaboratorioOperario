package logica;

public class Procesos {
    
    public double calcularAumento(double sueldo, int antiguedad) {
        double sueldoFinal = sueldo;double sueldoFinal = sueldo;

        // Regla 1: Sueldo < 500 y Antigüedad >= 10 años -> +20%
        if (sueldo < 500 && antiguedad >= 10) {
            sueldoFinal = sueldo * 1.20;
        } 
        // Regla 2: Sueldo < 500 y Antigüedad < 10 años -> +5%
        else if (sueldo < 500 && antiguedad < 10) {
            sueldoFinal = sueldo * 1.05;
        }
        // Regla 3: Sueldo >= 500 -> Sin cambios
        else {
            sueldoFinal = sueldo;
        }
        
        return sueldoFinal;
    }
}
