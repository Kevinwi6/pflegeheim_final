package datastorage;

public class DAOFactory {

    private static DAOFactory instance;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public TreatmentDAO createTreatmentDAO() {
        return new TreatmentDAO(ConnectionBuilder.getConnection());
    }
    public CaregiverDAO createCareGiverDAO() {
        return new CaregiverDAO(ConnectionBuilder.getConnection());
    }
    public PatientDAO createPatientDAO() {
        return new PatientDAO(ConnectionBuilder.getConnection());
    }
    public LoginDAO createLoginDAO(){return  new LoginDAO(ConnectionBuilder.getConnection());}
    public TArchiveDAO createTArchiveDAO(){return new TArchiveDAO(ConnectionBuilder.getConnection());}
    public PArchiveDAO createPArchiveDAO(){return new PArchiveDAO(ConnectionBuilder.getConnection());}
    public CArchiveDAO createCArchiveDAO(){return new CArchiveDAO(ConnectionBuilder.getConnection());}
}