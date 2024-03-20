import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Reservation implements Serializable {
    private static int reservationId = 0;
    private static int nbrPlacesReserve = 1;
    private int npr;
    private int id;
    private Film film;
    private Salle salle;
    private List<Integer> placesReserves;
    private Utilisateur user;

    // Constructeur
    public Reservation(int reservationId, Film film, Salle salle, List<Integer> placesReserves, Utilisateur user) {
        reservationId ++;
        nbrPlacesReserve += placesReserves.size();
        this.npr = nbrPlacesReserve-1;
        this.id = reservationId;
        this.film = film;
        this.salle = salle;
        this.placesReserves = placesReserves;
        this.user = user;
        this.user.ajouterReservation(this);
    }
    public Reservation(String titre, int nbrPlaces, Utilisateur user) {
        reservationId ++;
        this.id = reservationId;
        this.film = new Film(titre, "", "", 0, 0,null);
        this.placesReserves = new ArrayList<>();
        for (int i = 0; i < nbrPlaces; i++) {
            this.placesReserves.add(nbrPlacesReserve+i);
        }
        nbrPlacesReserve += placesReserves.size();
        this.npr = nbrPlacesReserve-1;
        this.user = user;
        this.user.ajouterReservation(this);
    }

    public int getNbrPlacesReseve(){
        return npr;
    }
    // Getters et setters
    public int getReservationId() {
        return reservationId;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public List<Integer> getplacesReserves() {
        return placesReserves;
    }

    public void setplacesReserves(List<Integer> placesReserves) {
        this.placesReserves = placesReserves;
    }

    public String getNomUtilisateur() {
        return user.getNom();
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.user.setNom(nomUtilisateur);
    }

    @Override
    public String toString() {
        return "Reservation [reservationId=" + id + ", film=" + film.getTitre() + ", salle=" + salle.getNumeroSalle() + ", placesReserves=" + placesReserves + ", nomUtilisateur=" + user.getNom() + "]";
    }
}