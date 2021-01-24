package com.bitter.PingSweep.model;

import com.bitter.PingSweep.pingSweepCode.PingService;

import javax.persistence.*;

@Entity(name = "PingReturn")
@Table
public class PingReturn {

    @Id
    @SequenceGenerator(
            name = "ping_sequence",
            sequenceName = "ping_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ping_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "address",
            nullable = false,
            columnDefinition = "VARCHAR(20)"
    )
    private String address;

    @Column(
            name = "date",
            nullable = false,
            columnDefinition = "VARCHAR(20)"
    )
    private String date;

    @Column(
            name = "time",
            nullable = false,
            columnDefinition = "VARCHAR(20)"
    )
    private String time;

    public PingReturn(String address, String date, String time) {
        this.address = address;
        this.date = date;
        this.time = time;
    }
    public PingReturn(){
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String toString() {
        return (this.getAddress() + " " + this.getDate() + " " + this.getTime()  + "\n");
    }

}
