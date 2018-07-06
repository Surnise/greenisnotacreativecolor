package dao;

public class DAOFactory {
    private final UserDAO USER_DAO;
    private final DrugDAO DRUG_DAO;
    private final OrderDAO ORDER_DAO;
    private static final DAOFactory INSTANCE = new DAOFactory();

    DAOFactory(){
        USER_DAO = new UserDAO();
        DRUG_DAO = new DrugDAO();
        ORDER_DAO = new OrderDAO();
    }

    public static DAOFactory getInstance(){
        return INSTANCE;
    }

    public UserDAO getUserDAO(){
        return USER_DAO;
    }

    public DrugDAO getDrugDAO() {
        return DRUG_DAO;
    }

    public OrderDAO getOrderDAO() {
        return ORDER_DAO;
    }
}
