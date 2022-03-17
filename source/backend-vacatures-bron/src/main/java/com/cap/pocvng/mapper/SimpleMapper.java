package com.cap.pocvng.mapper;

import com.cap.pocvng.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Interface from which Mapstruct generates a mapper from Vacature to MPVacatureMatch.
 */
@Mapper(
        componentModel = "spring"
)
public interface SimpleMapper {

    MPVacatureMatch vacatureToMPVacatureMatch(Vacature vacature);

    default MPOpleidingsnaam map(Opleidingsnaam value) {
        if (value instanceof OpleidingsnaamGecodeerd) {
            return (OpleidingsnaamGecodeerd) value;
        } else {
            return opleidingsnaamOngecodeerdToMPOpleidingsnaamOngecodeerd((OpleidingsnaamOngecodeerd) value);
        }
    }

    MPOpleidingsnaamOngecodeerd opleidingsnaamOngecodeerdToMPOpleidingsnaamOngecodeerd(OpleidingsnaamOngecodeerd value);


    default MPAdresBuitenland adresToMPAdresBuitenland(Adres value){
        if(value instanceof AdresNederland){
            return adresNederlandToMPAdresBuitenland((AdresNederland) value);
        }
        else{
            return adresBuitenlandToMPAdresBuitenland((AdresBuitenland) value);
        }
    }

    MPAdresBuitenland adresBuitenlandToMPAdresBuitenland(AdresBuitenland value);


    default MPAdresBuitenland adresNederlandToMPAdresBuitenland(AdresNederland value){
        MPAdresBuitenland result = new MPAdresBuitenland();
        MPAdresBuitenlandImpl impl = new MPAdresBuitenlandImpl();
        impl.setLandencodeIso("NL");
        result.setAdresBuitenland(impl);
        return result;
    }
}
