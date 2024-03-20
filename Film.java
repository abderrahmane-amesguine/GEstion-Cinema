import java.io.Serializable;

public class Film implements Serializable {
    private Salle salle;
    private String titre;
    private String dateProjection;
    private String genre;
    private int duree;
    private int nombreSieges;

    // Constructeur
    public Film(String titre, String dateProjection, String genre, int duree, int nombreSieges, Salle salle) {
        this.titre = titre;
        this.dateProjection = dateProjection;
        this.genre = genre;
        this.duree = duree;
        this.nombreSieges = nombreSieges;
        this.salle = salle;
    }

    // Getters et setters
    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDateProjection() {
        return dateProjection;
    }

    public void setDateProjection(String dateProjection) {
        this.dateProjection = dateProjection;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getNombreSieges() {
        return nombreSieges;
    }

    public void setNombreSieges(int nombreSieges) {
        this.nombreSieges = nombreSieges;
    }

    @Override
    public String toString() {
        return "Film [titre=" + titre + ", dateProjection=" + dateProjection + ", genre=" + genre + ", duree=" + duree
                + " minutes, nombreSieges=" + nombreSieges + "]";
    }
}
