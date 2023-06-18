package controller;

import datastorage.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Caregiver;
import model.PArchive;
import model.Patient;
import utils.DateConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AllCareGiverController {

    public TableView<Caregiver> tableView;
    public TableColumn<Caregiver, Integer> colID;
    public TableColumn<Caregiver, String> colSurname;
    public TableColumn<Caregiver, String> colFirstName;
    public TableColumn<Caregiver, String> colTelephone;
    public TextField txfSurname;
    public TextField txfFirstname;
    public TextField txfTelephone;

    private ObservableList<Caregiver> tableviewContent = FXCollections.observableArrayList();
    private CaregiverDAO dao;

    public void initialize() {
        readAllAndShowInTableView();

        this.colID.setCellValueFactory(new PropertyValueFactory<Caregiver, Integer>("cid"));

        this.colFirstName.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("firstname"));
        this.colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colSurname.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("surename"));
        this.colSurname.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colTelephone.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("phoneNumber"));
        this.colTelephone.setCellFactory(TextFieldTableCell.forTableColumn());


        this.tableView.setItems(this.tableviewContent);
    }

    @FXML
    public void handleOnEditFirstname(TableColumn.CellEditEvent<Caregiver, String> event){
        event.getRowValue().setFirstname(event.getNewValue());
        doUpdate(event);
    }

    @FXML
    public void handleOnEditSurname(TableColumn.CellEditEvent<Caregiver, String> event){
        event.getRowValue().setSurename(event.getNewValue());
        doUpdate(event);
    }

    @FXML
    public void handleOnEditDateOfBirth(TableColumn.CellEditEvent<Caregiver, String> event){
        event.getRowValue().setPhoneNumber(event.getNewValue());
        doUpdate(event);
    }


    private void doUpdate(TableColumn.CellEditEvent<Caregiver, String> t) {
        try {
            dao.update(t.getRowValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void readAllAndShowInTableView() {
        this.tableviewContent.clear();
        this.dao = DAOFactory.getDAOFactory().createCareGiverDAO();
        List<Caregiver> allCaregiver;
        try {
            allCaregiver = dao.readAll();
            for (Caregiver p : allCaregiver) {
                this.tableviewContent.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleDeleteRow() {
        CaregiverDAO cDao = DAOFactory.getDAOFactory().createCareGiverDAO();
        Caregiver selectedItem = this.tableView.getSelectionModel().getSelectedItem();

        try {
            cDao.deleteById(selectedItem.getCid());
            this.tableView.getItems().remove(selectedItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles a add-click-event. Creates a patient and calls the create method in the {@link PatientDAO}
     */
    @FXML
    public void handleAdd() {
        String surname = this.txfSurname.getText();
        String firstname = this.txfFirstname.getText();
        String telephone = this.txfTelephone.getText();

        try {
            Caregiver p = new Caregiver(firstname, surname, telephone);
            dao.create(p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        readAllAndShowInTableView();
        clearTextfields();
    }

    private void clearTextfields() {
        this.txfFirstname.clear();
        this.txfSurname.clear();
        this.txfTelephone.clear();
    }
}
