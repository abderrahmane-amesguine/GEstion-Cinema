import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        Salle s1 = new Salle(1, 50, "2D");
        Salle s2 = new Salle(2, 100, "2D");
        Salle s3 = new Salle(3, 75, "2D");
        Salle s4 = new Salle(4, 80, "3D");
        Salle s5 = new Salle(5, 50, "3D");
        Salle s6 = new Salle(6, 50, "3D");
        Salle s7 = new Salle(7, 120, "IMAX");

        cinema.ajouterSalle(s1);
        cinema.ajouterSalle(s2);
        cinema.ajouterSalle(s3);
        cinema.ajouterSalle(s4);
        cinema.ajouterSalle(s5);
        cinema.ajouterSalle(s6);
        cinema.ajouterSalle(s7);

        Film f1 = new Film("Avengers 1", "2/11/2023","Action", 120, 50,s1);
        Film f2 = new Film("Avengers 2", "22/1/2023","Action", 120, 50,s2);
        Film f3 = new Film("SpiderMan", "10/3/2023","Action", 90, 60,s3);
        Film f4 = new Film("Cast Away", "23/10/2022","Survival", 110, 45,s4);
        Film f5 = new Film("Openhiemer", "12/3/2023","Historique", 180, 75,s5);

        cinema.ajouterFilm(f1);
        cinema.ajouterFilm(f2);
        cinema.ajouterFilm(f3);
        cinema.ajouterFilm(f4);
        cinema.ajouterFilm(f5);

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {

                System.out.println("\n**********************************************");
                System.out.println("***********Menu :*****************************");
                System.out.println("**  1. Réserver un billet                   **");
                System.out.println("**  2. Afficher la liste des films          **");
                System.out.println("**  3. Afficher la liste des salles         **");
                System.out.println("**  4. Afficher la liste des réservations   **");
                System.out.println("**  5. Quitter                              **");
                System.out.println("**********************************************");
                System.out.println("**********************************************");

                System.out.print("\nChoisissez une option : ");
                int choix = scanner.nextInt();

                switch (choix) {
                    case 1:
                        System.out.print("\n\n*************************************************\n"+
                                             "**    Entrez votre nom : ");
                        String nom = scanner.next();

                        System.out.print("**    Entrez votre email : ");
                        String email = scanner.next();

                        System.out.print("**    Selectionnez un film par son numero : ");

                        int numeroFilm = scanner.nextInt();

                        System.out.print("**    Nombre de sieges : ");
                        int nombreSieges = scanner.nextInt();

                        System.out.println("*************************************************");

                        Utilisateur user = new Utilisateur(nom, email);
                        try{
                            Film filmChoisi = cinema.getFilmByNumero(numeroFilm);                        
                            if (filmChoisi != null) {
                                Thread reservationThread = new Thread(() -> {
                                    synchronized (cinema) {
                                        Reservation reservation = new Reservation(filmChoisi.getTitre(), nombreSieges, user);
                                        reservation.setSalle(filmChoisi.getSalle());
                                        user.ajouterReservation(reservation);
                                        cinema.effectuerReservation(reservation);
                                        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("cinema.ser"))) {
                                            outputStream.writeObject(cinema);

                                            System.out.println("\nObjets serialises avec succes.");
                                        } catch (IOException x) {
                                            x.printStackTrace();
                                        }
                                        try {
                                            Thread.sleep(500);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        System.out.println("Reservation effectuee avec succes.");
                                    }
                                });
                                reservationThread.start();
                            } else {
                                System.out.println("Film non trouve.");
                            }
                        } catch (Exception e) {
                            System.out.println("Film non trouve."+e.getMessage());
                        }
                        
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;

                    case 2:
                    System.out.println("\n*********liste des Filmes :**************\n");
                        cinema.afficherFilms();
                        System.out.println("\n");
                        break;

                    case 3:
                    System.out.println("\n*********liste des Salles :**************\n");
                        cinema.afficherSalles();
                        System.out.println("\n");
                        break;

                    case 4:
                    System.out.println("\n*********liste des Reservations :**********\n");
                        //cinema.afficherReservations();
                        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("cinema.ser"))) {
                            while (true) {
                                try {
                                    Cinema cin = (Cinema) inputStream.readObject();
                                    cin.afficherReservations();
                                } catch (EOFException eof) {
                                    // Fin de fichier
                                    break;
                                }
                            }
                        } catch (IOException | ClassNotFoundException x) {
                            x.printStackTrace();
                        }
                        System.out.println("\n");
                        break;

                    case 5:
                        System.out.println("\nMerci d'avoir utilise notre service. Au revoir !\n");
                        break;

                    default:
                        System.out.println("\nOption non valide. Veuillez choisir une option valide.\n");
                }
                
                if (choix==5) {
                    break;
                }
            }
        }
    }
}
