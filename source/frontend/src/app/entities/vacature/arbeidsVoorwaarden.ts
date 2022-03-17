import {ModelDate} from '../shared/modelDate';


export interface ArbeidsVoorwaarden {
  datumAanvangWerkzaamheden?: ModelDate;
  datumEindeWerkzaamheden?: ModelDate;
  omschrijvingArbeidsvoorwaarden?: string;
  salarisIndicatie?: string;
}
