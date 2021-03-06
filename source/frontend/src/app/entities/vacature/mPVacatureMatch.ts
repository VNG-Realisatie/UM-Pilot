import {Beroepsnaam} from '../shared/beroepsnaam';
import {Contractvorm} from '../shared/contractvorm';
import {Cursus} from './cursus';
import {Flexibiliteit} from '../shared/flexibiliteit';
import {Gedragscompetentie} from '../shared/gedragscompetentie';
import {MPArbeidsVoorwaarden} from './mPArbeidsVoorwaarden';
import {MPOpleiding} from './mPOpleiding';
import {MPSollicitatiewijze} from './mPSollicitatiewijze';
import {MPVervoermiddel} from '../shared/mPVervoermiddel';
import {MPWerkgever} from './mPWerkgever';
import {ModelDate} from '../shared/modelDate';
import {Rijbewijs} from '../shared/rijbewijs';
import {SectorBeroepsEnBedrijfsleven} from '../shared/sectorBeroepsEnBedrijfsleven';
import {Taalbeheersing} from '../shared/taalbeheersing';
import {Vakvaardigheid} from '../shared/vakvaardigheid';
import {Werkervaring} from './werkervaring';
import {Werktijden} from './werktijden';

export interface MPVacatureMatch {
  vumID: string;
  fetchedDetail : boolean;
  codeWerkEnDenkniveauMinimaal?: string;
  indicatieLdrRegistratie?: number;
  sluitingsDatumVacature?: ModelDate;
  sollicitatiewijze?: Array<MPSollicitatiewijze>;
  werkgever?: MPWerkgever;
  sector?: SectorBeroepsEnBedrijfsleven;
  arbeidsVoorwaarden?: MPArbeidsVoorwaarden;
  contractvorm?: Array<Contractvorm>;
  beroep?: Beroepsnaam;
  werkervaring?: Array<Werkervaring>;
  rijbewijs?: Array<Rijbewijs>;
  vervoermiddel?: Array<MPVervoermiddel>;
  flexibiliteit?: Flexibiliteit;
  werktijden?: Werktijden;
  cursus?: Array<Cursus>;
  opleiding?: Array<MPOpleiding>;
  gedragscompetentie?: Array<Gedragscompetentie>;
  vakvaardigheid?: Array<Vakvaardigheid>;
  taalbeheersing?: Array<Taalbeheersing>;
}
