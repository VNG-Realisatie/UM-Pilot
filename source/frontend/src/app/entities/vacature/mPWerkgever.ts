import {MPAdresHouding} from './mPAdresHouding';
import {SectorBeroepsEnBedrijfsleven} from '../shared/sectorBeroepsEnBedrijfsleven';

export interface MPWerkgever {
  adresHouding?: Array<MPAdresHouding>;
  sector?: Array<SectorBeroepsEnBedrijfsleven>;
}
