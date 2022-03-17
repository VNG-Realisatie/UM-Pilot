package com.cap.pocvng.mapper;


import com.cap.pocvng.dto.VacatureMatchesRequest;
import com.cap.pocvng.dto.VacatureMatchesRequestGemeente;
import org.mapstruct.Mapper;

/**
 * Interface from which Mapstruct generates a mapper between classes.
 */
@Mapper(
        componentModel = "spring"
)
public interface SimpleMapper {

    VacatureMatchesRequest vacatureMatchesRequestGemeenteToVacatureMatchesRequest(VacatureMatchesRequestGemeente vacatureMatchesRequestGemeente);
}
