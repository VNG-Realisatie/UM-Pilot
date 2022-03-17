import {AdresBuitenland} from './adresBuitenland';
import {AdresNederland} from './adresNederland';
import {ModelDate} from '../shared/modelDate';


export interface AdresHouding {
  codeFunctieAdres?: string;
  datumAanvangAdres?: ModelDate;
  datumEindeAdres?: ModelDate;
  adres?: AdresNederland | AdresBuitenland;
}

export function isAdresNederland(object: any): object is AdresNederland {
  return 'adresNederland' in object;
}
