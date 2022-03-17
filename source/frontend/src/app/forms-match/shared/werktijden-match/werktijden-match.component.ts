import {Component, Input, OnInit} from '@angular/core';
import {MPVacatureMatch} from "../../../entities/vacature/mPVacatureMatch";
import {selectToTextConvert} from "../../../forms-util/select-to-text";
import {indicatieThreeValues} from "../../../forms-util/formcontrol-value-enums";
import {Vacature} from "../../../entities/vacature/vacature";
import {MPWerkzoekendeMatchBemiddelaar} from "../../../entities/werkzoekende/mPWerkzoekendeMatchBemiddelaar";
import {Werkzoekende} from "../../../entities/werkzoekende/werkzoekende";

@Component({
  selector: 'app-werktijden-match',
  templateUrl: './werktijden-match.component.html',
  styleUrls: ['./werktijden-match.component.css']
})
export class WerktijdenMatchComponent implements OnInit {

  @Input()
  data?: MPVacatureMatch | Vacature | MPWerkzoekendeMatchBemiddelaar | Werkzoekende;

  aantalWerkurenPerWeekMinimaal?: number;
  aantalWerkurenPerWeekMaximaal?: number;
  indicatieKantoortijden?: string | number;

  constructor() {
  }

  ngOnInit(): void {

    this.aantalWerkurenPerWeekMinimaal = this.data?.werktijden?.aantalWerkurenPerWeekMinimaal;
    this.aantalWerkurenPerWeekMaximaal = this.data?.werktijden?.aantalWerkurenPerWeekMaximaal;
    this.indicatieKantoortijden = selectToTextConvert(this.data?.werktijden?.indicatieKantoortijden, indicatieThreeValues);


  }

}
