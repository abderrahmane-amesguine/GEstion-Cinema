import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cinema implements Serializable{
    private List<Film> films;
    private List<Salle> salles;
    private List<Reservation> reservations;

    public Cinema() {
        films = new ArrayList<>();
        salles = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    public List<Film> getFilms(){
        return films;
    }

    public Film getFilmByName(String titre){
        for (Film f : films) {
            if(f.getTitre()==titre) return f;
        }
        return null;
    }

    public void ajouterFilm(Film film) {
        films.add(film);
    }

    public void ajouterSalle(Salle salle) {
        salles.add(salle);
    }

    public void effectuerReservation(Reservation reservation) {
        //++++++++++++++++++++++++++++++++
        if(reservation.getSalle().getCapacite()>=reservation.getNbrPlacesReseve())
            reservations.add(reservation);
        else
            System.out.println("\nLes resrvations Sur "+reservation.getFilm().getTitre()+" sont finis! \n");
    }

    public void afficherFilms() {
        for (Film film : films) {
            System.out.println(film);
        }
    }

    public void afficherSalles() {
        for (Salle salle : salles) {
            System.out.println(salle);
        }
    }

    public void afficherReservations() {
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public List<Salle> getSalle() {
        return salles;
    }

    public Film getFilmByNumero(int numeroFilm) {
        return films.get(numeroFilm-1);
    }

}

