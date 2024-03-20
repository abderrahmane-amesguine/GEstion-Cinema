import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class InterfaceUtilisateurCinema {

    private JFrame frame;
    private JComboBox<String> filmsComboBox;
    private JTextField nameComboBox;
    private JTextField emailComboBox;
    private JTextField nombreSiegesField;
    private JButton reserverButton;

    public InterfaceUtilisateurCinema(Cinema cinema) {
        frame = new JFrame("Cinéma - Réservation de places");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JLabel labelFilm = new JLabel("Sélectionnez un film :");
        filmsComboBox = new JComboBox<>();
        for (Film film : cinema.getFilms()) {
            filmsComboBox.addItem(film.getTitre());
        }

        JLabel labelSieges = new JLabel("Nombre de sièges :");
        nombreSiegesField = new JTextField(10);

        JLabel labelname = new JLabel("Entrer votre nom :");
        nameComboBox = new JTextField(10);
        
        JLabel labelemail = new JLabel("Entrer votre email :");
        emailComboBox = new JTextField(10);

        reserverButton = new JButton("Réserver");

        reserverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = (String) nameComboBox.getText();
                String email = (String) emailComboBox.getText();
                String filmSelectionne = (String) filmsComboBox.getSelectedItem();
                int nombreSieges = Integer.parseInt(nombreSiegesField.getText());

                Utilisateur user = new Utilisateur(nom, email);
                try{
                    Thread reservationThread = new Thread(()->{
                        synchronized(cinema){
                            Reservation reservation = new Reservation(filmSelectionne, nombreSieges, user);
                            reservation.setSalle(cinema.getFilmByName(filmSelectionne).getSalle());
                            user.ajouterReservation(reservation);
                            cinema.effectuerReservation(reservation);
                            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("reservation.ser"))) {
                                outputStream.writeObject(reservation);
                            
                                System.out.println("Objets serialises avec succes.");
                            } catch (IOException x) {
                                x.printStackTrace();
                            }
                            String buf = "";
                            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("reservation.ser"))) {
                                while (true) {
                                    try {
                                        Reservation res = (Reservation) inputStream.readObject();
                                        buf = buf + res + "\n";
                                    } catch (EOFException eof) {
                                        break;
                                    }
                                }
                            } catch (IOException | ClassNotFoundException x) {
                                x.printStackTrace();
                            }
                            JOptionPane.showMessageDialog(frame, "Réservation effectuée avec succès.\n"+
                            "reservations : "+buf);
                        }
                    });
                    reservationThread.start();
                }catch(Exception c) {
                    System.out.println("Film non trouve."+c.getMessage());
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(labelFilm);
        panel.add(filmsComboBox);
        panel.add(labelname);
        panel.add(nameComboBox);
        panel.add(labelemail);
        panel.add(emailComboBox);
        panel.add(labelSieges);
        panel.add(nombreSiegesField);
        panel.add(reserverButton);

        frame.add(panel);
        frame.setVisible(true);
    }

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

        SwingUtilities.invokeLater(() -> new InterfaceUtilisateurCinema(cinema));

    }
}
