import {MPVacatureMatch} from "./mPVacatureMatch";

export interface AanvraagVacature {
  vraagId: string,
  vacatures: MPVacatureMatch[],
  creatieDatum: Date
}
