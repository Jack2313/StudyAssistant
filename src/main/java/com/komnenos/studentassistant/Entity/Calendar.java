package com.komnenos.studentassistant.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Calendar {
    @Id
    @GeneratedValue
    private int calendarId;
}
