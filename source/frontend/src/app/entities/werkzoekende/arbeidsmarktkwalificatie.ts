import {Cursus} from './cursus';
import {Gedragscompetentie} from '../shared/gedragscompetentie';
import {Interesse} from './interesse';
import {Opleiding} from './opleiding';
import {Rijbewijs} from '../shared/rijbewijs';
import {Taalbeheersing} from '../shared/taalbeheersing';
import {Vakvaardigheid} from '../shared/vakvaardigheid';
import {Werkervaring} from './werkervaring';

export interface Arbeidsmarktkwalificatie {
  codeWerkEnDenkniveauWerkzoekende?: number;
  vakvaardigheid?: Array<Vakvaardigheid>;
  taalbeheersing?: Array<Taalbeheersing>;
  gedragscompetentie?: Array<Gedragscompetentie>;
  interesse?: Array<Interesse>;
  rijbewijs?: Array<Rijbewijs>;
  cursus?: Array<Cursus>;
  opleiding?: Array<Opleiding>;
  werkervaring?: Array<Werkervaring>;
}
