package controller;

import datastorage.CaregiverDAO;
import datastorage.DAOFactory;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Caregiver;
import model.Patient;
import model.Treatment;
import utils.DateConverter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class NewTreatmentController {
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private Label lblSurname;
    @FXML
    private Label lblFirstname;
    @FXML
    private TextField txtBegin;
    @FXML
    private TextField txtEnd;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextArea taRemarks;
    @FXML
    private DatePicker datepicker;

    private ObservableList<String> tableviewContent = FXCollections.observableArrayList();
    private ArrayList<Caregiver> careGiverList;
    private AllTreatmentController controller;
    private Patient patient;

    private Stage stage;

    public void initialize(AllTreatmentController controller,Stage stage, Patient patient) {
        combobox.setItems(tableviewContent);
        combobox.getSelectionModel().select(0);

        this.controller= controller;
        this.patient = patient;
        this.stage = stage;
        showPatientData();
    }

    private void showPatientData(){
        CaregiverDAO cDao = DAOFactory.getDAOFactory().createCareGiverDAO();

        this.lblFirstname.setText(patient.getFirstName());
        this.lblSurname.setText(patient.getSurname());

        try {
            careGiverList = (ArrayList<Caregiver>) cDao.readAll();
            for(Caregiver caregiver : careGiverList) {
                this.tableviewContent.add(caregiver.getFirstname());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void handleAdd(){
        LocalDate date = this.datepicker.getValue();
        //String s_begin = txtBegin.getText();
        LocalTime begin = DateConverter.convertStringToLocalTime(txtBegin.getText());
        LocalTime end = DateConverter.convertStringToLocalTime(txtEnd.getText());
        String description = txtDescription.getText();
        String remarks = taRemarks.getText();
        Caregiver caregiver = getCareGiver();
        Treatment treatment = new Treatment(patient.getPid(),caregiver.getCid(), date,
                begin, end, description, remarks, caregiver);
        createTreatment(treatment);
        controller.readAllAndShowInTableView();
        stage.close();
    }
    private Caregiver getCareGiver(){
        for(Caregiver caregiver1 : careGiverList){
            if(caregiver1.getFirstname().equals( combobox.getSelectionModel().getSelectedItem())){
                return caregiver1;
            }
        }
        return null;

    }
    private void createTreatment(Treatment treatment) {
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.create(treatment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCancel(){
        stage.close();
    }
}