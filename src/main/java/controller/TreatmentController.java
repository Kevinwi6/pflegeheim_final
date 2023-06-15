package controller;

import datastorage.CaregiverDAO;
import datastorage.DAOFactory;
import datastorage.PatientDAO;
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
import java.util.ArrayList;

public class TreatmentController {
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Label lblPatientName;
    @FXML
    private Label lblCarelevel;
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
    @FXML
    private Button btnChange;
    @FXML
    private Button btnCancel;

    private ObservableList<String> tableViewContent = FXCollections.observableArrayList();
    private ArrayList<Caregiver> careGiverList;
    private AllTreatmentController controller;
    private Stage stage;
    private Patient patient;
    private Treatment treatment;

    public void initializeController(AllTreatmentController controller, Stage stage, Treatment treatment) {
        PatientDAO pDao = DAOFactory.getDAOFactory().createPatientDAO();
        comboBox.setItems(tableViewContent);
        comboBox.getSelectionModel().select(0);

        this.stage = stage;
        this.controller = controller;

        try {
            this.patient = pDao.read((int) treatment.getPid());
            this.treatment = treatment;
            showData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showData() {
        CaregiverDAO cDao = DAOFactory.getDAOFactory().createCareGiverDAO();

        this.lblPatientName.setText(patient.getSurname() + ", " + patient.getFirstName());
        this.lblCarelevel.setText(patient.getCareLevel());
        LocalDate date = DateConverter.convertStringToLocalDate(treatment.getDate());
        this.datepicker.setValue(date);
        this.txtBegin.setText(this.treatment.getBegin());
        this.txtEnd.setText(this.treatment.getEnd());
        this.txtDescription.setText(this.treatment.getDescription());
        this.taRemarks.setText(this.treatment.getRemarks());
        try {
            this.careGiverList = (ArrayList<Caregiver>) cDao.readAll();
            for (Caregiver caregiver : careGiverList) {
                this.tableViewContent.add(caregiver.getFirstname());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void handleChange() {
        this.treatment.setDate(this.datepicker.getValue().toString());
        this.treatment.setBegin(txtBegin.getText());
        this.treatment.setEnd(txtEnd.getText());
        this.treatment.setDescription(txtDescription.getText());
        this.treatment.setRemarks(taRemarks.getText());
        doUpdate();
        controller.readAllAndShowInTableView();
        stage.close();
    }

    private void doUpdate() {
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.update(treatment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCancel() {
        stage.close();
    }
}