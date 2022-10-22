package edu.uco.budget.crosscutting.helper;

import java.sql.Connection;
import java.sql.SQLException;

public final class SqlConnectionHelper {
    
    private SqlConnectionHelper(){
        super();
    } 

    public static final boolean connectionIsNull(final Connection connection) {
        return ObjectHelper.isNull(connection);
    }

    public static final boolean connectionIsOpen(final Connection connection) {
        try {
            return !ObjectHelper.isNull(connection) && !connection.isClosed();
        } catch (final SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public static final void closeConnection(final Connection connection){
        try {
            if(!connectionIsOpen(connection)){
                throw new RuntimeException();
            }
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static final void openConnection(final Connection connection){
        try {
            if(!connectionIsOpen(connection)){
                throw new RuntimeException();
            }
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static final void initTransaction(final Connection connection){
        try {
            if(!connectionIsOpen(connection)){
                throw new RuntimeException();
            }
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
