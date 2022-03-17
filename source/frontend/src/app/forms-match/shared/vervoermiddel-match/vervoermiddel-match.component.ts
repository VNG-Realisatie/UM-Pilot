import {Component, Input, OnInit} from '@angular/core';
import {MPVacatureMatch} from "../../../entities/vacature/mPVacatureMatch";
import {selectToTextConvert} from "../../../forms-util/select-to-text";
import {indicatieThreeValues} from "../../../forms-util/formcontrol-value-enums";
import {Vacature} from "../../../entities/vacature/vacature";
import {MPWerkzoekendeMatchBemiddelaar} from "../../../entities/werkzoekende/mPWerkzoekendeMatchBemiddelaar";

@Component({
  selector: 'app-vervoermiddel-match',
  templateUrl: './vervoermiddel-match.component.html',
  styleUrls: ['./vervoermiddel-match.component.css']
})
export class VervoermiddelMatchComponent implements OnInit {

  @Input()
  data?: MPVacatureMatch | Vacature | MPWerkzoekendeMatchBemiddelaar;

  vervoermiddelen?: {
    indicatieBeschikbaarVoorUitvoeringWerk?: string | number;
    indicatieBeschikbaarVoorWoonWerkverkeer?: string | number;
  }[];

  constructor() {
  }

  ngOnInit(): void {
    this.vervoermiddelen = this.data?.vervoermiddel?.map((
      {indicatieBeschikbaarVoorUitvoeringWerk, indicatieBeschikbaarVoorWoonWerkverkeer}) => {
      const indicatieUitvoeringMapped = selectToTextConvert(indicatieBeschikbaarVoorUitvoeringWerk, indicatieThreeValues)
      const indicatieWoonWerkMapped = selectToTextConvert(indicatieBeschikbaarVoorWoonWerkverkeer, indicatieThreeValues)
      return {
        indicatieBeschikbaarVoorUitvoeringWerk: indicatieUitvoeringMapped,
        indicatieBeschikbaarVoorWoonWerkverkeer: indicatieWoonWerkMapped
      }
    });
  }
}
