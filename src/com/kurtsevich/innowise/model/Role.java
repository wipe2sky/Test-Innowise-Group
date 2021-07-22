package com.kurtsevich.innowise.model;

import java.util.ArrayList;
import java.util.List;

public class Role extends BaseEntity {

    private List<ERole> name;
    public Role() {
        this.name = new ArrayList<>();
    }

    public List<ERole> getName() {
        return name;
    }

    public void setName(List<ERole> name) {
        this.name = name;
    }
}
