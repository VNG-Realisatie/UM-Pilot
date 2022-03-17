import {ModelDate} from '../shared/modelDate';
import {Opleidingsnaam} from '../shared/opleidingsnaam';

export interface MPOpleiding {
  codeNiveauOpleiding?: number;
  indicatieDiploma?: number;
  datumDiploma?: ModelDate;
  opleidingsnaam?: Opleidingsnaam;
}
