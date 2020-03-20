package com.komnenos.studentassistant.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ticket {

    @Id
    @GeneratedValue
    private int ticketId;

    private String ticketCode;

    private int userId;

    private boolean isUsed;

    public boolean isAble() {
        return isAble;
    }

    public void setAble(boolean able) {
        isAble = able;
    }

    private boolean isAble;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int value;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

}
