import Service.ArchiveService;
import datastorage.ConnectionBuilder;
import datastorage.DAOFactory;
import datastorage.PArchiveDAO;
import model.PArchive;
import model.Patient;
import org.junit.jupiter.api.Test;
import utils.DateConverter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.List;

public class ExpiredDataTest {
    @Test
    void expiredPatientDataGotDeleted() {
        ArchiveService archiveService = new ArchiveService();
        PArchiveDAO paDAO = DAOFactory.getDAOFactory().createPArchiveDAO();

        Patient testPatient = new Patient("Max", "Mustermann", DateConverter.convertStringToLocalDate("2034-01-01"),
                "1", "1");

        try {
            paDAO.create(archiveService.convertPatientIntoArchive(testPatient));
            archiveService.checkDateForDelete();
            List<PArchive> pArchiveList = paDAO.readAll();
            assertFalse(pArchiveList.contains(testPatient));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addPatientDataToArchive() {
        ArchiveService archiveService = new ArchiveService();
        PArchiveDAO paDAO = DAOFactory.getDAOFactory().createPArchiveDAO();

        Patient testPatient = new Patient("Max", "Mustermann", DateConverter.convertStringToLocalDate("2024-01-01"),
                "1", "1");

        try {
            paDAO.create(archiveService.convertPatientIntoArchive(testPatient));
            List<PArchive> pArchiveList = paDAO.readAll();
            assertTrue(pArchiveList.contains(testPatient));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
