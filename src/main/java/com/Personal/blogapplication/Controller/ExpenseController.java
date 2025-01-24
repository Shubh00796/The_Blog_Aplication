package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.ExpenseDTO;
import com.Personal.blogapplication.Entity.Expense;
import com.Personal.blogapplication.Service.ExpenceSevice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
@Slf4j
public class ExpenseController {

    private final ExpenceSevice expenseService;

    // Add a new expense
    @PostMapping("/{email}")
    public ResponseEntity<ExpenseDTO> addExpense(@RequestBody ExpenseDTO expenseDTO, @PathVariable String email) {
        try {
            ExpenseDTO createdExpense = expenseService.addExpence(expenseDTO, email);
            return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            log.error("Error adding expense: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Update an existing expense
    @PutMapping("/update/{email}")
    public ResponseEntity<List<ExpenseDTO>> updateExpenses(
            @RequestBody ExpenseDTO expenseDTO,
            @PathVariable String email) {
        try {
            log.info("Request received to update expenses for email: {}", email);

            List<ExpenseDTO> updatedExpenses = expenseService.updateExpences(expenseDTO, email);

            return new ResponseEntity<>(updatedExpenses, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            log.error("Error updating expenses: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error occurred: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Get all expenses
    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
        List<ExpenseDTO> expenses = expenseService.getAllExpeneces();
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    // Get all expenses by email
    @GetMapping("/email/{email}")
    public ResponseEntity<List<Expense>> getExpensesByEmail(@PathVariable String email) {
        List<Expense> expenses = expenseService.getAllExpensesByEmail(email);
        if (expenses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    // Get all expenses by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Expense>> getExpensesByCategory(@PathVariable String category) {
        List<Expense> expenses = expenseService.getAllExpencesByCat(category);
        if (expenses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }
}
