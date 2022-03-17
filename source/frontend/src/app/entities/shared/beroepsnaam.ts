import {BeroepsnaamGecodeerd} from './beroepsnaamGecodeerd';
import {BeroepsnaamOngecodeerd} from './beroepsnaamOngecodeerd';

export type Beroepsnaam = BeroepsnaamGecodeerd | BeroepsnaamOngecodeerd;

export function determineBeroepsnaamType(object: any): object is BeroepsnaamGecodeerd {
  return 'codeBeroepsnaam' in object;
}
