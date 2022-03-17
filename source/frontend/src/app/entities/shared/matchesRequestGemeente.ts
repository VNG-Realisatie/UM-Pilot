import {Postcode} from "./postcode";
import {MPVacature} from "../vacature/mPVacature";
import {MPWerkzoekende} from "../werkzoekende/mPWerkzoekende";

export interface MatchesRequestGemeente {
  postcode?: Postcode,
  straal?: number,
  aanvraagKenmerk?: string,
  vraagObject: MPVacature | MPWerkzoekende
}
