import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur implements Serializable {
    private String nom;
    private String email;
    private List<Reservation> historiqueReservations;

    // Constructeur
    public Utilisateur(String nom, String email) {
        this.nom = nom;
        this.email = email;
        this.historiqueReservations = new ArrayList<>();
    }

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Reservation> getHistoriqueReservations() {
        return historiqueReservations;
    }

    public void ajouterReservation(Reservation reservation) {
        historiqueReservations.add(reservation);
    }

    @Override
    public String toString() {
        return "Utilisateur [nom=" + nom + ", email=" + email + "]";
    }
}
