package edu.uco.budget.service.usecase.budget;

import java.util.List;

import edu.uco.budget.data.dao.relational.sqlserver.BudgetSqlServerDAO;
import edu.uco.budget.domain.BudgetDTO;

public interface FindBudgetUseCase {
    
    List<BudgetDTO> findBudget(BudgetDTO budget);

}
