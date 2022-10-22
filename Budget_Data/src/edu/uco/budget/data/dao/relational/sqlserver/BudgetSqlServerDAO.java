package edu.uco.budget.data.dao.relational.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uco.budget.crosscutting.helper.ObjectHelper;
import edu.uco.budget.crosscutting.helper.UUIDHelper;
import edu.uco.budget.data.dao.BudgetDAO;
import edu.uco.budget.data.dao.relational.DAORelational;
import edu.uco.budget.domain.BudgetDTO;
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

        } catch (SQLException e) {
            // TODO: handle exception
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public final List<BudgetDTO> find(final BudgetDTO budget) {

        var results = new ArrayList<>()
        var setWhere = true;
        var parameters = new ArrayList<Object>();
        final var sqlBuilder = new StringBuilder();

        sqlBuilder.append("SELECT Bu.Id AS IdBudget");
        sqlBuilder.append("       Bu.idYear AS IdYear");
        sqlBuilder.append("       Ye.year AS NumberYear");
        sqlBuilder.append("       Bu.idPerson AS IdPerson");
        sqlBuilder.append("       Pe.idCard AS IdCard");
        sqlBuilder.append("       Pe.firstName AS FirstNamePerson");
        sqlBuilder.append("       Pe.secondName AS SecondNamePerson");
        sqlBuilder.append("       Pe.firstSurname AS FirstSurnamePerson");
        sqlBuilder.append("       Pe.secondSurname AS SecondSurnamePerson");

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

        try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())) {
            
            for(int index = 0; index < parameters.size(); index++){
                preparedStatement.setObject(index + 1, parameters.get(index));
            }

            try (final var resultSet = preparedStatement.executeQuery()) {
                
            } catch (Exception e) {
                // TODO: handle exception
            }

        } catch (Exception e) {
            // TODO: handle exception
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

        } catch (SQLException e) {
            // TODO: handle exception
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public final void delete(final UUID id) {
        final var sql = "DELETE FROM BUDGET WHERE id = ?";

        try (final var preparedStatement = getConnection().prepareStatement(sql)) {
            
            preparedStatement.setString(1, getUUIDAsString(id));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // TODO: handle exception
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
