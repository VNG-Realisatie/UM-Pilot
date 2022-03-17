import {Gedragscompetentie} from '../shared/gedragscompetentie';
import {MPCursus} from './mPCursus';
import {MPOpleiding} from './mPOpleiding';
import {MPWerkervaring} from './mPWerkervaring';
import {Rijbewijs} from '../shared/rijbewijs';
import {Taalbeheersing} from '../shared/taalbeheersing';
import {Vakvaardigheid} from '../shared/vakvaardigheid';

export interface MPArbeidsmarktkwalificatie {
  codeWerkEnDenkniveauWerkzoekende?: number;
  vakvaardigheid?: Array<Vakvaardigheid>;
  taalbeheersing?: Array<Taalbeheersing>;
  gedragscompetentie?: Array<Gedragscompetentie>;
  rijbewijs?: Array<Rijbewijs>;
  cursus?: Array<MPCursus>;
  opleiding?: Array<MPOpleiding>;
  werkervaring?: Array<MPWerkervaring>;
}
