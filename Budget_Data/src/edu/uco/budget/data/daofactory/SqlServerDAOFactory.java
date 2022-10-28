package edu.uco.budget.data.daofactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.w3c.dom.ls.LSOutput;

import edu.uco.budget.crosscutting.exception.data.CrosscuttingCustomException;
import edu.uco.budget.crosscutting.exception.data.DataCustomException;
import edu.uco.budget.crosscutting.messages.Messages;
import edu.uco.budget.crosscutting.messages.Messages.SqlConnectionHelper;
import edu.uco.budget.data.dao.BudgetDAO;
import edu.uco.budget.data.dao.PersonDAO;
import edu.uco.budget.data.dao.YearDAO;
import edu.uco.budget.data.dao.relational.sqlserver.BudgetSqlServerDAO;
import edu.uco.budget.data.dao.relational.sqlserver.PersonSqlServerDAO;
import edu.uco.budget.data.dao.relational.sqlserver.YearSqlServerDAO;

class SqlServerDAOFactory extends DAOFactory{

    private Connection connection;

    SqlServerDAOFactory(){
        openConnection();
    }

    @Override
    protected void openConnection() {
        final String url = "jdbc:sqlserver://rg-wf.database.windows.net:1433;" 
                + "database=db-budget;" 
                + "user=userDmlBudget;" 
                + "password=us3rDmlBudg3t;"
                + "encrypt=true;"
                + "trustServerCertificate=false;"
                + "hostNameInCertificate=*.database.windows.net;"
                + "loginTimeout=30;";
                try {
                    connection = DriverManager.getConnection(url);
                } catch (SQLException e) {
                    throw new RuntimeException("Error al conectarse a la base de datos.");
                }
    }
    
    @Override
    public void closeConection() {
        try {
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void confirmTransaction() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BudgetDAO getBudgetDAO() {
        return new BudgetSqlServerDAO(connection);
    }

    @Override
    public PersonDAO getPersonDAO() {
        return new PersonSqlServerDAO(connection);
    }

    @Override
    public YearDAO getYearDAO() {
        return new YearSqlServerDAO(connection);
    }

    @Override
    public void initTransaction() {
        try {
            edu.uco.budget.crosscutting.helper.SqlConnectionHelper.initTrasaction(connection);
        }catch(CrosscuttingCustomException exception) {
            throw DataCustomException.CreateTechnicalException(null, exception);
        }
        
    }


}
