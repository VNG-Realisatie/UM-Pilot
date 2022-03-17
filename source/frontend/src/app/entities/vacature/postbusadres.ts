import {PostbusadresImpl} from './postbusadresImpl';

export interface Postbusadres {
  postbusadres?: PostbusadresImpl;
}

export function isPostbusadres(object: any): object is Postbusadres {
  return 'postbusadres' in object;
}
