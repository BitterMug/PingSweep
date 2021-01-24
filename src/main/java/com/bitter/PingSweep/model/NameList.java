package com.bitter.PingSweep.model;


import javax.persistence.*;

@Entity (name = "namelist")
@Table
public class NameList {
    @Id
    @SequenceGenerator(
            name = "namelist_sequence",
            sequenceName = "namelist_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "namelist_sequence"
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
            name = "name",
            nullable = false,
            columnDefinition = "VARCHAR(64)"
    )
    private String name;


    public NameList(String address, String name) {
        this.address = address;
        this.name = name;
    }
    public NameList(){}

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

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return (this.getId() + " " +
                this.getAddress() + " " +
                this.getName() + " " + "\n");
    }
}
