package com.bitter.PingSweep.model;

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
            name = "datetime",
            nullable = false,
            columnDefinition = "VARCHAR(64)"
    )
    private String dateTime;

    public PingReturn(String address, String dateTime) {
        this.address = address;
        this.dateTime = dateTime;
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

    public String getDateTime() {
        return dateTime;
    }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }


}
