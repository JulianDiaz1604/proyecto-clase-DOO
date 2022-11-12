package edu.uco.budget.data.dao.relational.sqlserver;

import static edu.uco.budget.crosscutting.helper.UUIDHelper.getUUIDAsString;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uco.budget.crosscutting.exception.data.DataCustomException;
import edu.uco.budget.crosscutting.helper.ObjectHelper;
import edu.uco.budget.crosscutting.helper.UUIDHelper;
import edu.uco.budget.crosscutting.messages.Messages;
import edu.uco.budget.data.dao.YearDAO;
import edu.uco.budget.data.dao.relational.DAORelational;
import edu.uco.budget.domain.BudgetDTO;
import edu.uco.budget.domain.PersonDTO;
import edu.uco.budget.domain.YearDTO;
import edu.uco.budget.crosscutting.helper.StringHelper;
public class YearSqlServerDAO extends DAORelational implements YearDAO{

    public YearSqlServerDAO(final Connection connection) {
        super(connection);
    }
    @Override
    public final void create(final YearDTO year) {
        final var sql = "INSERT INTO YEAR(id, numberYear) VALUES (?, ?)";

        try (final var preparedStatement = getConnection().prepareStatement(sql)) {
            
            preparedStatement.setString(1, year.getIdAsString());
            preparedStatement.setString(2, year.getYearNumber().toString()); //no entiendo este error

            preparedStatement.executeUpdate();

            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.YearSqlServerDAO.TECHNICAL_PROBLEM_CREATE_YEAR, exception); 
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.YearSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_YEAR, exception); 
        }
    }

    @Override
    public final List<YearDTO> find(final YearDTO year) {

        var parameters = new ArrayList<Object>();
        final var sqlBuilder = new StringBuilder();

        createSelectFrom(sqlBuilder);
        createWhere(sqlBuilder, year, parameters);
        createOrderBy(sqlBuilder);

        return prepareAndExecuteQuery(sqlBuilder, parameters);
    }

    private final List<YearDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters) {

        try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())) {
            
            setParametersValues(preparedStatement, parameters);

            return executeQuery(preparedStatement);

        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.YearSqlServerDAO.TECHNICAL_PROBLEM_PREPAREANDEXECUTEQUERY_YEAR, exception);
        }

    }


    private void setParametersValues(PreparedStatement preparedStatement, List<Object> parameters) {
        try {
            for(int index = 0; index < parameters.size(); index++){
                preparedStatement.setObject(index + 1, parameters.get(index));
            }
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.YearSqlServerDAO.TECHNICAL_PROBLEM_SET_PARAMETERS_YEAR, exception); 
        } catch(final Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.YearSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETERS_YEAR, exception); 
        }
    }


    private final void createSelectFrom(final StringBuilder sqlBuilder){
        sqlBuilder.append("SELECT Year.Id AS IdYear");
        sqlBuilder.append("       Year.year AS NumberYear");
        sqlBuilder.append("FROM Year");//revisar si esto está realmente bien
    }

    private final void createWhere(final StringBuilder sqlBuilder, final YearDTO year, final List<Object> parameters){



        if(!ObjectHelper.isNull(year)){

            if (!UUIDHelper.isDefaultUUID(year.getId())){
                sqlBuilder.append("WHERE id = ? ");
                parameters.add(year.getIdAsString());
         
        }
            else {
                sqlBuilder.append("WHERE yearNumber = ? ");
                parameters.add(year.getYearNumber());//?????????????????????????????????? tostring?
            }
        }

    }

    private final void createOrderBy(final StringBuilder sqlBuilder){

        sqlBuilder.append("ORDER BY Year.id"); 

    }

    private final List<YearDTO> executeQuery(PreparedStatement preparedStatement){

        try (final var resultSet = preparedStatement.executeQuery()) {
            return fillResults(resultSet);
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.YearSqlServerDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY_YEAR, exception); 
        } catch(final DataCustomException exception) {
            throw exception;
        } catch(final Exception exception){
            throw DataCustomException.CreateTechnicalException(Messages.YearSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECUTE_QUERY_YEAR, exception); 
        }
    }

    @Override
    public final void update(final YearDTO year) {
        final var sql = "UPDATE YEAR SET id = ?, numberYear = ?";

        try (final var preparedStatement = getConnection().prepareStatement(sql)) {
            
            preparedStatement.setString(1, year.getIdAsString());
            preparedStatement.setString(2, year.getYearNumber()); //ERROR DELSTRING

            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.YearSqlServerDAO.TECHNICAL_PROBLEM_UPDATE_YEAR, exception); 
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.YearSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_YEAR, exception); 
        }
    }

    @Override
    public final void delete(final UUID id) {
        final var sql = "DELETE FROM YEAR WHERE id = ?";

        try (final var preparedStatement = getConnection().prepareStatement(sql)) {
            
            preparedStatement.setString(1, getUUIDAsString(id));

            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.YearSqlServerDAO.TECHNICAL_PROBLEM_DELETE_YEAR, exception); 
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.YearSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_DELETE_YEAR, exception); 
        }
    }

    private final List<YearDTO> fillResults(final ResultSet resultSet){

        try{

            var results = new ArrayList<YearDTO>();

            while(resultSet.next()){

                results.add(fillYearDTO(resultSet));

            }

            return results;

        } catch (final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.YearSqlServerDAO.TECHNICAL_PROBLEM_FILL_RESULTS_YEAR, exception); 
        } catch (final Exception exception){
            throw DataCustomException.CreateTechnicalException(Messages.YearSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS_YEAR, exception); 
        }

    }


    private final YearDTO fillYearDTO(final ResultSet resultSet){

        try {

            return YearDTO.create(resultSet.getString("IdYear"), resultSet.getShort("NumberYear"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.YearSqlServerDAO.TECHNICAL_PROBLEM_FILL_YEARDTO_YEAR, exception); 
        }

    }

   
}
