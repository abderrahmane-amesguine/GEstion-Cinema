import java.io.Serializable;

public class Salle implements Serializable {
    private int numeroSalle;
    private int capacite;
    private String type;
    
    // Constructeur
    public Salle(int numeroSalle, int capacite, String type) {
        this.numeroSalle = numeroSalle;
        this.capacite = capacite;
        this.type = type;
    }

    // Getters et setters
    public int getNumeroSalle() {
        return numeroSalle;
    }

    public void setNumeroSalle(int numeroSalle) {
        this.numeroSalle = numeroSalle;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Salle [numeroSalle=" + numeroSalle + ", capacite=" + capacite + ", type=" + type + "]";
    }
}
