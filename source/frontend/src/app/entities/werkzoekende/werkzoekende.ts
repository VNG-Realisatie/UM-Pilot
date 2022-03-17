import {Arbeidsmarktkwalificatie} from './arbeidsmarktkwalificatie';
import {Beroepsnaam} from '../shared/beroepsnaam';
import {ContactpersoonAfdeling} from './contactpersoonAfdeling';
import {Contractvorm} from '../shared/contractvorm';
import {EisAanWerk} from './eisAanWerk';
import {Emailadres} from '../shared/emailadres';
import {Flexibiliteit} from '../shared/flexibiliteit';
import {IDWerkzoekende} from './iDWerkzoekende';
import {Mobiliteit} from './mobiliteit';
import {SectorBeroepsEnBedrijfsleven} from '../shared/sectorBeroepsEnBedrijfsleven';
import {Telefoonnummer} from '../shared/telefoonnummer';
import {Vervoermiddel} from '../shared/vervoermiddel';
import {Voorkeursland} from './voorkeursland';
import {Webadres} from '../shared/webadres';
import {Werktijden} from './werktijden';

export interface Werkzoekende {
  idWerkzoekende?: IDWerkzoekende;
  indicatieLdrRegistratie?: number;
  indicatieBeschikbaarheidContactgegevens?: number;
  persoonlijkePresentatie?: string;
  voorkeursland?: Array<Voorkeursland>;
  eisAanWerk?: EisAanWerk;
  vervoermiddel?: Array<Vervoermiddel>;
  mobiliteit?: Mobiliteit;
  flexibiliteit?: Flexibiliteit;
  werktijden?: Werktijden;
  contractvorm?: Array<Contractvorm>;
  arbeidsmarktkwalificatie?: Arbeidsmarktkwalificatie;
  emailadres?: Array<Emailadres>;
  webadres?: Array<Webadres>;
  telefoonnummer?: Array<Telefoonnummer>;
  contactpersoon?: Array<ContactpersoonAfdeling>;
  sector?: Array<SectorBeroepsEnBedrijfsleven>;
  bemiddelingsberoep?: Array<Beroepsnaam>;
}
