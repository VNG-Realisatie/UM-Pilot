package com.cap.pocvng.mapper;


import com.cap.pocvng.dto.WerkzoekendeProfielMatchesRequest;
import com.cap.pocvng.dto.WerkzoekendeProfielMatchesRequestGemeente;
import org.mapstruct.Mapper;

/**
 * Interface from which Mapstruct generates a mapper between classes.
 */
@Mapper(
        componentModel = "spring"
)
public interface SimpleMapper {

    WerkzoekendeProfielMatchesRequest requestGemeenteToWerkzoekendeProfielMatchesRequest(WerkzoekendeProfielMatchesRequestGemeente werkzoekendeProfielMatchesRequestGemeente);
}
