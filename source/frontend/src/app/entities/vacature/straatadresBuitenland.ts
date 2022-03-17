import {StraatadresBuitenlandImpl} from './straatadresBuitenlandImpl';

export interface StraatadresBuitenland {
  straatadresbuitenland?: StraatadresBuitenlandImpl;
}


export function isStraatadresBuitenland(object: any): object is StraatadresBuitenland {
  return 'straatadresbuitenland' in object;
}
