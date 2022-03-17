import {ModelDate} from './modelDate';

export interface Flexibiliteit {
  codeRegiostraal?: number;
  indicatieOnregelmatigWerkOfPloegendienst?: number;
  datumAanvangBeschikbaarVoorWerk?: ModelDate;
  datumEindeBeschikbaarVoorWerk?: ModelDate;
}
