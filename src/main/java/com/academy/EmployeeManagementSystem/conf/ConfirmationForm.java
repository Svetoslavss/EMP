package com.academy.EmployeeManagementSystem.conf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationForm {

    private String confirmation;

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }
}
