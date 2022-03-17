import {AntwoordnummerAdres} from './antwoordnummerAdres';
import {Postbusadres} from './postbusadres';
import {Postcode} from '../shared/postcode';
import {Straatadres} from './straatadres';


export interface AdresNederlandImpl {
  codeGemeente?: string;
  district?: string;
  gemeentedeel?: string;
  gemeentenaam?: string;
  identificatiecodeNummeraanduiding?: string;
  identificatiecodeVerblijfplaats?: string;
  locatieomschrijving?: string;
  postcode?: Postcode;
  woonplaatsnaam?: string;
  adresDetails?: Straatadres | Postbusadres | AntwoordnummerAdres;
}
