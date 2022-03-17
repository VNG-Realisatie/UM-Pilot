import {AdresHouding} from './adresHouding';
import {ContactpersoonAfdeling} from './contactpersoonAfdeling';
import {SectorBeroepsEnBedrijfsleven} from '../shared/sectorBeroepsEnBedrijfsleven';
import {Webadres} from '../shared/webadres';

export interface Werkgever {
  handelsnaamOrganisatie?: string;
  webadres?: Array<Webadres>;
  adresHouding?: Array<AdresHouding>;
  contactpersoon?: Array<ContactpersoonAfdeling>;
  sector?: Array<SectorBeroepsEnBedrijfsleven>;
}
