import {Postcode} from '../shared/postcode';

export interface Mobiliteit {
  bemiddelingspostcode?: Postcode;
  maximaleReisafstand?: number;
  maximaleReistijd?: number;
}
