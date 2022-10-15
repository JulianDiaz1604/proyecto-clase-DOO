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

        public static final String TECHNICAL_CONNECTION_IS_NULL = "Connection is null";
        public static final String TECHNICAL_CONNECTION_IS_CLOSED = "Connection is closed";

    }

}
