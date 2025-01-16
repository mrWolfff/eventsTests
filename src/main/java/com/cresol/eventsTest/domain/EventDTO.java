package com.cresol.eventsTest.domain;

import java.util.Date;

public record EventDTO(Long id, String nome, Date initialDate, Date finalDate, Boolean active, Institution institution) {

}
