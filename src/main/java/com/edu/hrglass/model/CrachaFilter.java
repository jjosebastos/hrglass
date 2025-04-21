package com.edu.hrglass.model;

import java.time.LocalDate;

public record CrachaFilter(
    String statusCracha,
    String cracha,
    LocalDate startDate, 
    LocalDate endDate) {
}
