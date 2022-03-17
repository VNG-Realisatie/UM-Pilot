import {Beroepsnaam} from '../shared/beroepsnaam';
import {Contractvorm} from '../shared/contractvorm';
import {Flexibiliteit} from '../shared/flexibiliteit';
import {Mobiliteit} from './mobiliteit';
import {SectorBeroepsEnBedrijfsleven} from '../shared/sectorBeroepsEnBedrijfsleven';
import {Voorkeursland} from './voorkeursland';
import {Werktijden} from './werktijden';
import {MPVervoermiddel} from "../shared/mPVervoermiddel";
import {MPArbeidsmarktkwalificatie} from "./mPArbeidsmarktkwalificatie";

export interface MPWerkzoekendeMatchBemiddelaar {
  vumID?: string;
  fetchedDetail? : boolean;
  indicatieLdrRegistratie?: number;
  indicatieBeschikbaarheidContactgegevens?: number;
  voorkeursland?: Array<Voorkeursland>;
  vervoermiddel?: Array<MPVervoermiddel>;
  mobiliteit?: Mobiliteit;
  flexibiliteit?: Flexibiliteit;
  werktijden?: Werktijden;
  contractvorm?: Array<Contractvorm>;
  arbeidsmarktkwalificatie?: MPArbeidsmarktkwalificatie;
  sector?: Array<SectorBeroepsEnBedrijfsleven>;
  bemiddelingsberoep?: Array<Beroepsnaam>;

}



