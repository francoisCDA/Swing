package org.example.view.component.listener;

import org.example.model.Employee;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.Date;

public class DateListener implements PropertyChangeListener {

    private Employee employee;

    public DateListener(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("date")) {
            Date date = (Date) evt.getNewValue();
            if (date != null) {
                LocalDate localDate =  LocalDate.of(date.getYear() +1900, date.getMonth()+1, date.getDate());
                employee.setStartDateNoEvent(localDate);
            }

        }


    }
}
