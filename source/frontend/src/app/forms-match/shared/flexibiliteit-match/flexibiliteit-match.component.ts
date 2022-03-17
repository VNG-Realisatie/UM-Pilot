import {Component, Input, OnInit} from '@angular/core';
import {MPVacatureMatch} from "../../../entities/vacature/mPVacatureMatch";
import {selectToTextConvert} from "../../../forms-util/select-to-text";
import {codeRegioStraalValues, indicatieThreeValues} from "../../../forms-util/formcontrol-value-enums";
import {dateFormatterString} from "../../../util/dateformatter";
import {Vacature} from "../../../entities/vacature/vacature";
import {MPWerkzoekendeMatchBemiddelaar} from "../../../entities/werkzoekende/mPWerkzoekendeMatchBemiddelaar";
import {Werkzoekende} from "../../../entities/werkzoekende/werkzoekende";

@Component({
  selector: 'app-flexibiliteit-match',
  templateUrl: './flexibiliteit-match.component.html',
  styleUrls: ['./flexibiliteit-match.component.css']
})
export class FlexibiliteitMatchComponent implements OnInit {

  @Input()
  data?: MPVacatureMatch | Vacature | MPWerkzoekendeMatchBemiddelaar | Werkzoekende;

  codeRegiostraal?: string | number
  indicatieOnregelmatigWerkOfPloegendienst?: string | number
  datumAanvangBeschikbaarVoorWerk?: string
  datumEindeBeschikbaarVoorWerk?: string


  constructor() {
  }

  ngOnInit(): void {
    this.codeRegiostraal = selectToTextConvert(this.data?.flexibiliteit?.codeRegiostraal, codeRegioStraalValues);
    this.indicatieOnregelmatigWerkOfPloegendienst = selectToTextConvert(this.data?.flexibiliteit?.indicatieOnregelmatigWerkOfPloegendienst, indicatieThreeValues);
    this.datumAanvangBeschikbaarVoorWerk = dateFormatterString(this.data?.flexibiliteit?.datumAanvangBeschikbaarVoorWerk);
    this.datumEindeBeschikbaarVoorWerk = dateFormatterString(this.data?.flexibiliteit?.datumEindeBeschikbaarVoorWerk);
  }

}
