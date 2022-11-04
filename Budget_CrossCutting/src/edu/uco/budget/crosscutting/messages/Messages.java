package edu.uco.budget.crosscutting.messages;

public class Messages {
    
    public static class DAOFactory{

        private DAOFactory() {
            super();
        }

        public static final String TECHNICAL_MONGODB_NOT_IMPLEMENTED = "DAOFactory for MongoDB is not implemented yet";
        public static final String TECHNICAL_CASSANDRA_NOT_IMPLEMENTED = "DAOFactory for Cassandra is not implemented yet";
        public static final String TECHNICAL_MARIADB_NOT_IMPLEMENTED = "DAOFactory for MariaDB is not implemented yet";
        public static final String TECHNICAL_ORACLE_NOT_IMPLEMENTED = "DAOFactory for Oracle is not implemented yet";
        public static final String TECHNICAL_POSTGRESQL_NOT_IMPLEMENTED = "DAOFactory for PostgreSQL is not implemented yet";
        public static final String TECHNICAL_MYSQL_NOT_IMPLEMENTED = "DAOFactory for MySQL is not implemented yet";
        public static final String TECHNICAL_UNEXPECTED_DAOFACTORY = "Unexpected DAOFactory";

    }

    public static class SqlConnectionHelper {

        private SqlConnectionHelper(){
            super();
        }
        public static final String TECHNICAL_CONNECTION_ALREADY_IS_OPEN = "Connection already is open";
        public static final String TECHNICAL_CONNECTION_IS_NULL = "Connection is null";
        public static final String TECHNICAL_CONNECTION_IS_CLOSED = "Connection is closed";
        public static final String TECHNICAL_CONNECTION_ALREADY_IS_CLOSED = "Connection already is closed";
        public static final String TECHNICAL_PROBLEM_CLOSING_CONNECTION = "There was a problem trying to close connection";
        public static final String TECHNICAL_CONNECTION_IS_CLOSED_FOR_INIT_TRANSACTION = "Connection is closed to init the current transaction";
        public static final String TECHNICAL_CONNECTION_IS_CLOSED_FOR_ROLLBACK_TRANSACTION = "Connection is closed to rollback the current transaction";      
        public static final String TECHNICAL_CONNECTION_IS_CLOSED_FOR_COMMIT_TRANSACTION = "Connection is closed to commit the current transaction";      
        public static final String TECHNICAL_PROBLEM_TRY_INIT_TRANSACTION = "There was a problem trying to init transaction";
        public static final String TECHNICAL_PROBLEM_TRY_COMMIT_TRANSACTION = "There was a problem trying to close connection";
        public static final String TECHNICAL_PROBLEM_TRY_ROLLBACK_TRANSACTION = "There was a problem trying to rollback trasaction";
        public static final String TECHNICAL_PROBLEM_INIT_TRANSACTION = "There was a problem trying to init trasaction with the current connection in SQLServerDAOFactory";
        public static final String TECHNICAL_PROBLEM_CLOSE_CONNECTION = "There was a problem trying to close connection with the current connection in SQLServerDAOFactory";
        public static final String TECHNICAL_PROBLEM_CONFIRM_TRANSACTION = "There was a problem trying to confirm trasaction with the current connection in SQLServerDAOFactory";

    }

    public static class UUIDHelper {
        private UUIDHelper(){
            super();
        }   

        public static final String TECHNICAL_UUID_FROM_STRING_INVALID = "The UUID to convert doesn't have a correct format";
        public static final String TECHNICAL_UUID_FROM_STRING_UNEXPECTED_ERROR = "There was an unexpected error";
    }
    public static class BudgetSqlServerDAO{
        private BudgetSqlServerDAO(){
            super();
        }
        public static final String TECHNICAL_PROBLEM_CREATE_BUDGET = "There was a problem trying to create the Budget";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_BUDGET = "There was an unexpected problem trying to create the Budget";
        public static final String TECHNICAL_PROBLEM_FIND_BUDGET = "There was a problem to trying to find budget";
        public static final String TECHNICAL_PROBLEM_SET_PARAMETERS_BUDGET = "There was a problem trying to set parameters";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETERS = "There was a unexpected problem to trying set parameters";
        public static final String TECHNICAL_PROBLEM_EXECUTE_QUERY_BUDGET = "There was a problem trying to excute query in the Budget";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECUTE_QUERY_BUDGET = "There was a unexpected problem trying to excute query in the Budget";
        public static final String TECHNICAL_PROBLEM_UPDATE_BUDGET = "There was a problem to trying to update the Budget";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_BUDGET = "There was a problem to trying to update the Budget";
        public static final String TECHNICAL_PROBLEM_DELETE_BUDGET = "There was a problem to trying to delete the Budget";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_DELETE_BUDGET = "There was a unexpected problem to trying to delete the Budget";
        public static final String TECHNICAL_PROBLEM_FILL_RESULTS_BUDGET= "There was a problem to trying to fill results in the Budget";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS_BUDGET = "There was a unexpected problem to trying to fill results in the Budget";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILL_BUDGETDTO = "There was a unexpected problem to trying to fill BudgetDTO in the Budget";
        public static final String TECHNICAL_PROBLEM_FILL_BUDGETDTO = "There was a problem to trying to fill BudgetDTO in the Budget";
        public static final String TECHNICAL_PROBLEM_FILL_YEARDTO = "There was a problem to trying to fill YearDTO in the Budget";
        public static final String TECHNICAL_PROBLEM_FILL_PERSONDTO = "There was a problem to trying to fill PersonDTO in the Budget";

    }
    
    public static class YearSqlServerDAO{
        private YearSqlServerDAO() {
        }
        
    }
    public static class PersonSqlServerDAO{
        private PersonSqlServerDAO() {
        }
        
    }
}

