import {Component, Input, OnInit} from '@angular/core';
import {selectToTextConvert} from "../../../forms-util/select-to-text";
import {codeVervoersmiddelValues, indicatieThreeValues} from "../../../forms-util/formcontrol-value-enums";
import {Vacature} from "../../../entities/vacature/vacature";
import {Werkzoekende} from "../../../entities/werkzoekende/werkzoekende";

@Component({
  selector: 'app-vervoermiddel-match-detailed',
  templateUrl: './vervoermiddel-match-detailed.component.html',
  styleUrls: ['./vervoermiddel-match-detailed.component.css']
})
export class VervoermiddelMatchDetailedComponent implements OnInit {

  @Input()
  data?: Vacature | Werkzoekende;

  vervoermiddelen?: {
    indicatieBeschikbaarVoorWoonWerkverkeer?: string | number;
    codeVervoermiddel?: string | number;
    indicatieBeschikbaarVoorUitvoeringWerk?: string | number
  }[]

  constructor() {
  }

  ngOnInit(): void {

    this.vervoermiddelen = this.data?.vervoermiddel?.map((
      {indicatieBeschikbaarVoorUitvoeringWerk, indicatieBeschikbaarVoorWoonWerkverkeer, codeVervoermiddel}) => {
      const indicatieUitvoeringMapped = selectToTextConvert(indicatieBeschikbaarVoorUitvoeringWerk, indicatieThreeValues)
      const indicatieWoonWerkMapped = selectToTextConvert(indicatieBeschikbaarVoorWoonWerkverkeer, indicatieThreeValues)
      const vervoerMiddelMapped = selectToTextConvert(codeVervoermiddel, codeVervoersmiddelValues)
      return {
        indicatieBeschikbaarVoorUitvoeringWerk: indicatieUitvoeringMapped,
        indicatieBeschikbaarVoorWoonWerkverkeer: indicatieWoonWerkMapped,
        codeVervoermiddel: vervoerMiddelMapped
      }
    });
  }

}
