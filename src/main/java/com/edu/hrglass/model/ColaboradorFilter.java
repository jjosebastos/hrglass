package com.edu.hrglass.model;

import java.time.LocalDate;

public record ColaboradorFilter (
    String ra,
    LocalDate startDate,
    LocalDate endDate){
    
}
