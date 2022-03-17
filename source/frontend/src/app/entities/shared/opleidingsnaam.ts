import {OpleidingsnaamGecodeerd} from './opleidingsnaamGecodeerd';
import {OpleidingsnaamOngecodeerd} from './opleidingsnaamOngecodeerd';

export type Opleidingsnaam = OpleidingsnaamGecodeerd | OpleidingsnaamOngecodeerd;

export function determineOpleidingsnaamType(object: any): object is OpleidingsnaamGecodeerd {
  return 'codeOpleidingsnaam' in object;
}
