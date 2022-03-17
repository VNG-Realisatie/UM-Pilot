import {PostbusadresBuitenland} from './postbusadresBuitenland';
import {StraatadresBuitenland} from './straatadresBuitenland';


export interface AdresBuitenlandImpl {
  landencodeIso?: string;
  landsnaam?: string;
  locatieomschrijvingBuitenland?: string;
  postcodeBuitenland?: string;
  regionaamBuitenland?: string;
  woonplaatsnaamBuitenland?: string;
  adresDetailsBuitenland?: StraatadresBuitenland | PostbusadresBuitenland;
}
