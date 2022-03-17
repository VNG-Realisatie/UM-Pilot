import {MPCursus} from './mPCursus';
import {ModelDate} from '../shared/modelDate';


export interface Cursus extends MPCursus {
  datumAanvangVolgenCursus?: ModelDate;
  datumEindeVolgenCursus?: ModelDate;
  naamOpleidingsinstituut?: string;
}
