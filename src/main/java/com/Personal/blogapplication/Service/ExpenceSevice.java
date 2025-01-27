package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.ExpenseDTO;
import com.Personal.blogapplication.Entity.Expense;
import com.Personal.blogapplication.Entity.UserForExpence;
import com.Personal.blogapplication.Repo.ExpenseRepository;
import com.Personal.blogapplication.Repo.UserForExpenceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenceSevice {
    private final ExpenseRepository expenseRepository;
    private final UserForExpenceRepository userForExpenceRepository;

    @Transactional
    public ExpenseDTO addExpence(ExpenseDTO expenseDTO, String email) {
        log.info("Received email: {}", email);

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        Optional<UserForExpence> userForExpence = userForExpenceRepository.findByEmail(email);
        if (!userForExpence.isPresent()) {
            throw new IllegalArgumentException("Email not found");
        }

//      List<Expense> byEmail = expenseRepository.findByEmail(email);
//      if(byEmail == null || byEmail.isEmpty()){
//          throw new IllegalArgumentException("Email cannot be null or empty");
//
//      }

        Expense expense = new Expense();
        expense.setEmail(expenseDTO.getEmail());
        expense.setId(expenseDTO.getId());
        expense.setDate(expenseDTO.getDate());
        expense.setAmount(expenseDTO.getAmount());
        expense.setCategory(expenseDTO.getCategory());
        expense.setDescription(expenseDTO.getDescription());

        Expense savedExpence = expenseRepository.save(expense);

        return new ExpenseDTO(savedExpence.getId(), savedExpence.getEmail(), savedExpence.getDescription(), savedExpence.getAmount(), savedExpence.getDate(), savedExpence.getCategory());
    }

    public List<ExpenseDTO> updateExpences(ExpenseDTO expenseDTO, String email) {
        log.info("Updating expenses for email: {}", email);

        // Retrieve all expenses associated with the email
        List<Expense> expenses = expenseRepository.findByEmail(email);

        if (expenses == null || expenses.isEmpty()) {
            throw new IllegalArgumentException("No expenses found for the provided email");
        }

        // Update each expense in the list
        for (Expense expense : expenses) {
            expense.setCategory(expenseDTO.getCategory());
            expense.setDate(expenseDTO.getDate());
            expense.setAmount(expenseDTO.getAmount());
            expense.setDescription(expenseDTO.getDescription());
        }

        // Save all updated expenses back to the database
        List<Expense> updatedExpenses = expenseRepository.saveAll(expenses);

        // Convert the updated expenses to DTOs and return
        return updatedExpenses.stream()
                .map(exp -> new ExpenseDTO(
                        exp.getId(),
                        exp.getEmail(),
                        exp.getDescription(),
                        exp.getAmount(),
                        exp.getCategory(),
                        exp.getDate()
                ))
                .toList();
    }


    public List<ExpenseDTO> getAllExpeneces() {
        log.info("Fetching all expenses sorted by publication date.");
        return expenseRepository.findAll(Sort.by(Sort.Order.desc("date")))  // Sorting by date in descending order
                .stream()
                .map(expense -> new ExpenseDTO(
                        expense.getId(),
                        expense.getEmail(),
                        expense.getDescription(),
                        expense.getAmount(),
                        expense.getCategory(),
                        expense.getDate()
                ))
                .collect(Collectors.toList());
    }
    public List<Expense> getAllExpensesByEmail(String email) {
        return expenseRepository.findByEmail(email); // Directly return the list
    }
    public List<Expense> getAllExpencesByCat(String category){
        return expenseRepository.findByCategory(category);
    }

}
