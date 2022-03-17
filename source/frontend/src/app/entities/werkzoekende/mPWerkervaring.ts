import {Beroepsnaam} from '../shared/beroepsnaam';
import {ModelDate} from '../shared/modelDate';


export interface MPWerkervaring {
  datumAanvangWerkzaamheden?: ModelDate;
  datumEindeWerkzaamheden?: ModelDate;
  naamOrganisatie?: string;
  beroep?: Beroepsnaam;
}
