package edu.uco.budget.data.dao.relational.sqlserver;

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
import edu.uco.budget.data.dao.BudgetDAO;
import edu.uco.budget.data.dao.relational.DAORelational;
import edu.uco.budget.domain.BudgetDTO;
import edu.uco.budget.domain.PersonDTO;
import edu.uco.budget.domain.YearDTO;

import static edu.uco.budget.crosscutting.helper.UUIDHelper.getUUIDAsString;

public class BudgetSqlServerDAO extends DAORelational implements BudgetDAO{

    public BudgetSqlServerDAO(final Connection connection) {
        super(connection);
    }


    @Override
    public final void create(final BudgetDTO budget) {
        final var sql = "INSERT INTO BUDGET(id, idYear, idPerson) VALUES (?, ?, ?)";

        try (final var preparedStatement = getConnection().prepareStatement(sql)) {
            
            preparedStatement.setString(1, budget.getIdAsString());
            preparedStatement.setString(2, budget.getYear().getIdAsString());
            preparedStatement.setString(3, budget.getPerson().getIdAsString());

            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO crear excepcion
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO crear excepcion
        }
    }

    @Override
    public final List<BudgetDTO> find(final BudgetDTO budget) {

        var parameters = new ArrayList<Object>();
        final var sqlBuilder = new StringBuilder();

        createSelectFrom(sqlBuilder);
        createWhere(sqlBuilder, budget, parameters);
        createOrderBy(sqlBuilder);

        return prepareAndExecuteQuery(sqlBuilder, parameters);
    }

    private final List<BudgetDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters) {

        try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())) {
            
            setParametersValues(preparedStatement, parameters);

            return executeQuery(preparedStatement);

        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(null, exception);
        }

    }


    private void setParametersValues(PreparedStatement preparedStatement, List<Object> parameters) {
        try {
            for(int index = 0; index < parameters.size(); index++){
                preparedStatement.setObject(index + 1, parameters.get(index));
            }
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO crear excepcion
        } catch(final Exception exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO crear excepcion
        }
    }


    private final void createSelectFrom(final StringBuilder sqlBuilder){
        sqlBuilder.append("SELECT Bu.Id AS IdBudget");
        sqlBuilder.append("       Bu.idYear AS IdYear");
        sqlBuilder.append("       Ye.year AS NumberYear");
        sqlBuilder.append("       Bu.idPerson AS IdPerson");
        sqlBuilder.append("       Pe.idCard AS IdCard");
        sqlBuilder.append("       Pe.firstName AS FirstNamePerson");
        sqlBuilder.append("       Pe.secondName AS SecondNamePerson");
        sqlBuilder.append("       Pe.firstSurname AS FirstSurnamePerson");
        sqlBuilder.append("       Pe.secondSurname AS SecondSurnamePerson");
        sqlBuilder.append("FROM   Budget Bu ");
        sqlBuilder.append("INNER JOIN Year Ye ");
        sqlBuilder.append("ON     Bu.idYear = Ye.id ");
        sqlBuilder.append("INNER JOIN Person Pe ");
        sqlBuilder.append("ON     Bu.idPerson = Pe.id ");
    }

    private final void createWhere(final StringBuilder sqlBuilder, final BudgetDTO budget, final List<Object> parameters){

        var setWhere = true;

        if(!ObjectHelper.isNull(budget)){

            if (!UUIDHelper.isDefaultUUID(budget.getId())){
                sqlBuilder.append("WHERE id = ? ");
                setWhere = false;
                parameters.add(budget.getIdAsString());
            }

            if (!UUIDHelper.isDefaultUUID(budget.getYear().getId())){
                sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("idYear = ? ");
                setWhere = false;
                parameters.add(budget.getYear().getIdAsString());
            }

            if (!UUIDHelper.isDefaultUUID(budget.getPerson().getId())){
                sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("idPerson = ? ");
                parameters.add(budget.getPerson().getIdAsString());
            }

        }

    }

    private final void createOrderBy(final StringBuilder sqlBuilder){

        sqlBuilder.append("ORDER BY "); 
        sqlBuilder.append("ON     Bu.idYear = Ye.id "); //TODO: Completar query

    }

    private final List<BudgetDTO> executeQuery(PreparedStatement preparedStatement){

        try (final var resultSet = preparedStatement.executeQuery()) {
            return fillResults(resultSet);
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO crear excepcion
        } catch(final DataCustomException exception) {
            throw exception;
        } catch(final Exception exception){
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO crear excepcion
        }
    }

    @Override
    public final void update(final BudgetDTO budget) {
        final var sql = "UPDATE BUDGET SET id = ?, idyear = ?, idperson = ?";

        try (final var preparedStatement = getConnection().prepareStatement(sql)) {
            
            preparedStatement.setString(1, budget.getIdAsString());
            preparedStatement.setString(2, budget.getYear().getIdAsString());
            preparedStatement.setString(3, budget.getPerson().getIdAsString());

            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        }
    }

    @Override
    public final void delete(final UUID id) {
        final var sql = "DELETE FROM BUDGET WHERE id = ?";

        try (final var preparedStatement = getConnection().prepareStatement(sql)) {
            
            preparedStatement.setString(1, getUUIDAsString(id));

            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        }
    }

    private final List<BudgetDTO> fillResults(final ResultSet resultSet){

        try{

            var results = new ArrayList<BudgetDTO>();

            while(resultSet.next()){

                results.add(fillBudgetDTO(resultSet));

            }

            return results;

        } catch (final SQLException exception){
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        } catch (final Exception exception){
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        }

    }

    private final BudgetDTO fillBudgetDTO(final ResultSet resultSet){

        try {

        return BudgetDTO.create(resultSet.getString("idBudget"), fillYearDTO(resultSet), fillPersonDTO(resultSet));

        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        } catch(final DataCustomException exception) {
            throw exception;
        } catch(final Exception exception){
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        }

    }

    private final YearDTO fillYearDTO(final ResultSet resultSet){

        try {

            return YearDTO.create(resultSet.getString("IdYear"), resultSet.getShort("NumberYear"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
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
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        }

    }

}
