import {Injectable} from '@angular/core';
import {MPWerkzoekendeMatchBemiddelaar} from "../entities/werkzoekende/mPWerkzoekendeMatchBemiddelaar";

@Injectable({
  providedIn: 'root'
})
export class WerkzoekendePassService {

  private werkzoekende?: MPWerkzoekendeMatchBemiddelaar

  constructor() {
  }

  setWerkzoekende(werkzoekende: MPWerkzoekendeMatchBemiddelaar): void {
    this.werkzoekende = werkzoekende;
  }

  getWerkzoekende() {
    return this.werkzoekende;
  }
}
