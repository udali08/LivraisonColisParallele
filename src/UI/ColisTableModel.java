package UI;

import Gestion.Colis;

import Gestion.GestionnaireDeColis;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ColisTableModel extends AbstractTableModel {
    private final GestionnaireDeColis gestionnaire;
    private List<Colis> displayedColisList;
    private static final String[] COLUMN_NAMES = {"ID", "Statut"};

    public ColisTableModel(GestionnaireDeColis gestionnaire) {
        this.gestionnaire = gestionnaire;
        this.displayedColisList = new ArrayList<>(gestionnaire.getColisList());
    }

    @Override
    public int getRowCount() {
        return displayedColisList.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Colis colis = displayedColisList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return colis.getId();
            case 1:
                return colis.getStatut();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }

    /**
     * Met à jour la liste des colis affichés (par exemple, après un filtrage).
     *
     * @param newColisList Liste de colis filtrée ou complète.
     */
    public void setColisList(List<Colis> newColisList) {
        this.displayedColisList = newColisList;
        fireTableDataChanged();
    }

    /**
     * Notifie que les données ont changé sans modifier la liste filtrée.
     * Utilisé lorsque des colis sont ajoutés ou modifiés.
     */
    public void notifyDataChanged() {
        this.displayedColisList = new ArrayList<>(gestionnaire.getColisList());
        fireTableDataChanged();
    }
}
