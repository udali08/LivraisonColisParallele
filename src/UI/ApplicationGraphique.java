package UI;

import Gestion.Colis;

import Gestion.GestionnaireDeColis;
import Gestion.LivraisonThread;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationGraphique {
    private JFrame frame;
    private JTable colisTable;
    private ColisTableModel tableModel;
    private GestionnaireDeColis gestionnaire;
    private JComboBox<String> filterComboBox;

    public ApplicationGraphique() {
        gestionnaire = new GestionnaireDeColis();
        tableModel = new ColisTableModel(gestionnaire);

        frame = new JFrame("Gestion des Livraisons de Colis");
        frame.setLayout(new BorderLayout());

        // Création de la table avec un renderer pour les couleurs
        colisTable = new JTable(tableModel);
        colisTable.setDefaultRenderer(Object.class, new ColisTableCellRenderer());
        frame.add(new JScrollPane(colisTable), BorderLayout.CENTER);

        // Ajout des boutons d'interaction dans un panneau supérieur
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAdd = new JButton("Ajouter Colis");
        btnAdd.addActionListener(e -> ajouterColis());
        buttonPanel.add(btnAdd);

        // Filtre de statut
        filterComboBox = new JComboBox<>(new String[]{"Tous", "En attente", "En transit", "Livré"});
        filterComboBox.addActionListener(e -> filtrerColis());
        buttonPanel.add(new JLabel("Filtrer par statut :"));
        buttonPanel.add(filterComboBox);

        topPanel.add(buttonPanel, BorderLayout.WEST);
        frame.add(topPanel, BorderLayout.NORTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
    }

    public void show() {
        frame.setVisible(true);
    }

    private void ajouterColis() {
        String id = JOptionPane.showInputDialog(frame, "ID du colis");
        if (id != null && !id.isEmpty()) {
            if (gestionnaire.getColisList().stream().anyMatch(c -> c.getId().equals(id))) {
                JOptionPane.showMessageDialog(frame, "Le colis avec cet ID existe déjà !");
            } else {
                Colis colis = new Colis(id);
                try {
                    gestionnaire.enregistrerColis(colis);
                    LivraisonThread thread = new LivraisonThread(gestionnaire, colis);
                    thread.start();
                    tableModel.notifyDataChanged();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void filtrerColis() {
        String filter = (String) filterComboBox.getSelectedItem();
        List<Colis> filteredList = gestionnaire.getColisList();
        if (!"Tous".equals(filter)) {
            filteredList = filteredList.stream()
                    .filter(c -> c.getStatut().equalsIgnoreCase(filter))
                    .collect(Collectors.toList());
        }
        tableModel.setColisList(filteredList);
    }

    // Renderer pour appliquer des couleurs aux lignes de la table en fonction du statut
    private static class ColisTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            String statut = (String) table.getValueAt(row, 1);
            if ("En attente".equalsIgnoreCase(statut)) {
                component.setBackground(Color.LIGHT_GRAY);
            } else if ("En transit".equalsIgnoreCase(statut)) {
                component.setBackground(Color.YELLOW);
            } else if ("Livré".equalsIgnoreCase(statut)) {
                component.setBackground(Color.GREEN);
            } else {
                component.setBackground(Color.WHITE);
            }
            return component;
        }
    }
}
