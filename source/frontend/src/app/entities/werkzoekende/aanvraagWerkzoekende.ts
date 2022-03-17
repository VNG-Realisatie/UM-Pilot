import {MPWerkzoekendeMatchBron} from "./mPWerkzoekendeMatchBron";

export interface AanvraagWerkzoekende {
  vraagId: string,
  werkzoekenden: MPWerkzoekendeMatchBron[],
  creatieDatum: Date
}
