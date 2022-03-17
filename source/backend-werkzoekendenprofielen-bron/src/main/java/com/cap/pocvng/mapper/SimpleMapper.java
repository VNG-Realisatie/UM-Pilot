package com.cap.pocvng.mapper;

import com.cap.pocvng.entity.MPWerkzoekendeMatch;
import com.cap.pocvng.entity.Werkzoekende;
import org.mapstruct.Mapper;

/**
 * Interface from which Mapstruct generates a mapper from Werkzoekende to MPWerkzoekendeMatch.
 */
@Mapper(
        componentModel = "spring"
)
public interface SimpleMapper {

    MPWerkzoekendeMatch werkzoekendeToMPWerkzoekendeMatch(Werkzoekende werkzoekende);
}
