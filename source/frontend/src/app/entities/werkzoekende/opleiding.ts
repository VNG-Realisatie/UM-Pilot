import {MPOpleiding} from './mPOpleiding';
import {ModelDate} from '../shared/modelDate';


export interface Opleiding extends MPOpleiding {
  datumAanvangVolgenOpleiding?: ModelDate;
  datumEindeVolgenOpleiding?: ModelDate;
  codeStatusOpleiding?: number;
  naamOpleidingsinstituut?: string;
}
