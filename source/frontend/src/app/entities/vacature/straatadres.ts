import {StraatadresImpl} from './straatadresImpl';

export interface Straatadres {
  straatadres?: StraatadresImpl;
}

export function isStraatadres(object: any): object is Straatadres {
  return 'straatadres' in object;
}
