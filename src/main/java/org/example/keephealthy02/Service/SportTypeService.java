package org.example.keephealthy02.Service;

import org.example.keephealthy02.Entity.SportType;

import java.util.List;

public interface SportTypeService {
    List<SportType> getAllSportType();

    SportType getOne(String sportId);
}
