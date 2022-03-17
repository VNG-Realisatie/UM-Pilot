import {PostbusadresBuitenlandImpl} from './postbusadresBuitenlandImpl';

export interface PostbusadresBuitenland {
  postbusadresbuitenland?: PostbusadresBuitenlandImpl;
}

export function isPostbusadresBuitenland(object: any): object is PostbusadresBuitenland {
  return 'postbusadresbuitenland' in object;
}
