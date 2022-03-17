import {AntwoordnummerAdresImpl} from "./antwoordnummerAdresImpl";

export interface AntwoordnummerAdres {
  antwoordnummeradres?: AntwoordnummerAdresImpl;
}

export function isAntwoordnummeradres(object: any): object is AntwoordnummerAdres {
  return 'antwoordnummeradres' in object;
}
