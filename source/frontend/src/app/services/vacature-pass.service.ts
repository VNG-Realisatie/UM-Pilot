import {Injectable} from '@angular/core';
import {MPVacatureMatch} from "../entities/vacature/mPVacatureMatch";

@Injectable({
  providedIn: 'root'
})
export class VacaturePassService {

  private vacature?: MPVacatureMatch

  constructor() {
  }

  setVacature(vacature: MPVacatureMatch): void {
    this.vacature = vacature;
  }

  getVacature() {
    return this.vacature;
  }
}
