package edu.uco.budget.data.dao.relational.sqlserver;

import static edu.uco.budget.crosscutting.helper.UUIDHelper.getUUIDAsString;

import java.security.MessageDigestSpi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uco.budget.crosscutting.exception.data.DataCustomException;
import edu.uco.budget.crosscutting.helper.ObjectHelper;
import edu.uco.budget.crosscutting.helper.UUIDHelper;
import edu.uco.budget.crosscutting.messages.Messages;
import edu.uco.budget.data.dao.PersonDAO;
import edu.uco.budget.data.dao.relational.DAORelational;
import edu.uco.budget.domain.BudgetDTO;
import edu.uco.budget.domain.PersonDTO;
import edu.uco.budget.domain.YearDTO;

public class PersonSqlServerDAO extends DAORelational implements PersonDAO{

    public PersonSqlServerDAO(final Connection connection) {
        super(connection);
    }

/* 
        sqlBuilder.append("       Pe.idCard AS IdCard");
        sqlBuilder.append("       Pe.firstName AS FirstNamePerson");
        sqlBuilder.append("       Pe.secondName AS SecondNamePerson");
        sqlBuilder.append("       Pe.firstSurname AS FirstSurnamePerson");
        sqlBuilder.append("       Pe.secondSurname AS SecondSurnamePerson");*/

    @Override
    public final void create(final PersonDTO person) {
        final var sql = "INSERT INTO BUDGET(id, idCard, firstName, secondName, firstSurname, secondSurname) VALUES (?, ?, ?, ?, ?, ?)";

        try (final var preparedStatement = getConnection().prepareStatement(sql)) {
            
            preparedStatement.setString(1, person.getIdAsString());
            preparedStatement.setString(2, person.getIdCard());//STRING!!!!!
            preparedStatement.setString(3, person.getFirstName());
            preparedStatement.setString(4, person.getSecundName());
            preparedStatement.setString(5, person.getFirstSurname());
            preparedStatement.setString(6, person.getSecondSurname());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.PersonSqlServerDAO.TECHNICAL_PROBLEM_CREATE_PERSON, exception); 
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.PersonSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_PERSON, exception); 
        }
    }

    @Override
    public final List<PersonDTO> find(final PersonDTO person) {

        var parameters = new ArrayList<Object>();
        final var sqlBuilder = new StringBuilder();

        createSelectFrom(sqlBuilder);
        createWhere(sqlBuilder, person, parameters);
        createOrderBy(sqlBuilder);

        return prepareAndExecuteQuery(sqlBuilder, parameters);
    }

    private final List<PersonDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters) {

        try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())) {
            
            setParametersValues(preparedStatement, parameters);

            return executeQuery(preparedStatement);

        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.PersonSqlServerDAO.TECHNICAL_PROBLEM_PREPAREANDEXECUTEQUERY_PERSON, exception);
        }

    }


    private void setParametersValues(PreparedStatement preparedStatement, List<Object> parameters) {
        try {
            for(int index = 0; index < parameters.size(); index++){
                preparedStatement.setObject(index + 1, parameters.get(index));
            }
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.PersonSqlServerDAO.TECHNICAL_PROBLEM_SET_PARAMETERS_PERSON, exception); 
        } catch(final Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.PersonSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETERS_PERSON, exception); //TODO crear excepcion
        }
    }


    private final void createSelectFrom(final StringBuilder sqlBuilder){
        sqlBuilder.append("SELECT pe.Id AS IdPerson");
        sqlBuilder.append("       Pe.v AS IdCard");
        sqlBuilder.append("       Pe.firstName AS FirstNamePerson");
        sqlBuilder.append("       Pe.secondName AS SecondNamePerson");
        sqlBuilder.append("       Pe.firstSurname AS FirstSurnamePerson");
        sqlBuilder.append("       Pe.secondSurname AS SecondSurnamePerson");
        sqlBuilder.append("FROM Person pe"); // revisar si está bien
}
    private final void createWhere(final StringBuilder sqlBuilder, final PersonDTO person, final List<Object> parameters){

        var setWhere = true;

        if(!ObjectHelper.isNull(person)){

            if (!UUIDHelper.isDefaultUUID(person.getId())){
                sqlBuilder.append("WHERE id = ? ");
                setWhere = false;
                parameters.add(person.getIdAsString());
            }
            if (!ObjectHelper.isNull(person.getIdCard()) ){
                sqlBuilder.append("WHERE idCard = ? ");
                setWhere = false;
                parameters.add(person.getIdCard());
            }
            if (!ObjectHelper.isNull(person.getFirstName())){
                sqlBuilder.append("WHERE firstName = ? ");
                setWhere = false;
                parameters.add(person.getFirstName());
            }
            if (!ObjectHelper.isNull(person.getSecundName())){
                sqlBuilder.append("WHERE secondName = ? ");
                setWhere = false;
                parameters.add(person.getSecundName());
            }
            if (!ObjectHelper.isNull(person.getFirstSurname())){
                sqlBuilder.append("WHERE firstSurname = ? ");
                setWhere = false;
                parameters.add(person.getFirstSurname());
            }
            if (!ObjectHelper.isNull(person.getSecondSurname())){
                sqlBuilder.append("WHERE secondSurname = ? ");
                setWhere = false;
                parameters.add(person.getSecondSurname());
            }
            

        }

    }

    private final void createOrderBy(final StringBuilder sqlBuilder){

        sqlBuilder.append("ORDER BY pe.id"); 


    }

    private final List<PersonDTO> executeQuery(PreparedStatement preparedStatement){

        try (final var resultSet = preparedStatement.executeQuery()) {
            return fillResults(resultSet);
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.PersonSqlServerDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY_PERSON, exception); 
        } catch(final DataCustomException exception) {
            throw exception;
        } catch(final Exception exception){
            throw DataCustomException.CreateTechnicalException(Messages.PersonSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECUTE_QUERY_PERSON, exception); 
        }
    }

    @Override
    public final void update(final PersonDTO person) {
        final var sql = "UPDATE PERSON SET id = ?, idCard = ?, firstName = ? , secondName = ? , firstSurname = ? , secondSurname = ? ";

        try (final var preparedStatement = getConnection().prepareStatement(sql)) {
            
            preparedStatement.setString(1, person.getIdAsString());
            preparedStatement.setString(2, person.getIdCard());//STRING!!!!!
            preparedStatement.setString(3, person.getFirstName());
            preparedStatement.setString(4, person.getSecundName());
            preparedStatement.setString(5, person.getFirstSurname());
            preparedStatement.setString(6, person.getSecondSurname());

            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.PersonSqlServerDAO.TECHNICAL_PROBLEM_UPDATE_PERSON, exception); 
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.PersonSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_PERSON, exception); 
        }
    }

    @Override
    public final void delete(final UUID id) {
        final var sql = "DELETE FROM PERSON WHERE id = ?";

        try (final var preparedStatement = getConnection().prepareStatement(sql)) {
            
            preparedStatement.setString(1, getUUIDAsString(id));

            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.PersonSqlServerDAO.TECHNICAL_PROBLEM_DELETE_PERSON, exception); 
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.PersonSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_DELETE_PERSON, exception); 
        }
    }

    private final List<PersonDTO> fillResults(final ResultSet resultSet){

        try{

            var results = new ArrayList<PersonDTO>();

            while(resultSet.next()){

                results.add(fillPersonDTO(resultSet));

            }

            return results;

        } catch (final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.PersonSqlServerDAO.TECHNICAL_PROBLEM_FILL_RESULTS_PERSON, exception); //TODO
        } catch (final Exception exception){
            throw DataCustomException.CreateTechnicalException(Messages.PersonSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS_PERSON, exception); //TODO
        }

    }



    private final PersonDTO fillPersonDTO(final ResultSet resultSet){

        try {

            return PersonDTO.create(resultSet.getString("IdPerson"), 
                                    resultSet.getString("IdCard"), 
                                    resultSet.getString("FirstNamePerson"),
                                    resultSet.getString("SecondNamePerson"),
                                    resultSet.getString("FirstSurnamePerson"),
                                    resultSet.getString("SecondSurnamePerson"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.PersonSqlServerDAO.TECHNICAL_PROBLEM_FILL_PERSONDTO_PERSON, exception); //TODO
        }

    }

}


