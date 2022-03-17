import {Component, Input, OnInit} from '@angular/core';
import {MPArbeidsmarktkwalificatie} from "../../../entities/werkzoekende/mPArbeidsmarktkwalificatie";
import {selectToTextConvert} from "../../../forms-util/select-to-text";
import {codeNiveauOpleidingValues, indicatieDiplomaValues} from "../../../forms-util/formcontrol-value-enums";
import {OpleidingsnaamOngecodeerd} from "../../../entities/shared/opleidingsnaamOngecodeerd";
import {OpleidingsnaamGecodeerd} from "../../../entities/shared/opleidingsnaamGecodeerd";
import {determineOpleidingsnaamType, Opleidingsnaam} from "../../../entities/shared/opleidingsnaam";

@Component({
  selector: 'app-opleiding-werkzoekende-match',
  templateUrl: './opleiding-werkzoekende-match.component.html',
  styleUrls: ['./opleiding-werkzoekende-match.component.css']
})
export class OpleidingWerkzoekendeMatchComponent implements OnInit {

  @Input()
  data?: MPArbeidsmarktkwalificatie

  opleidingen?: {
    codeNiveauOpleiding: string | undefined | number;
    indicatieDiploma: string | undefined | number;
    opleidingsnaam: OpleidingsnaamGecodeerd | OpleidingsnaamOngecodeerd | undefined;
    datumDiploma: string | undefined
    type: boolean
  }[] | undefined;


  constructor() {
  }

  ngOnInit(): void {
    this.opleidingen = this.data?.opleiding?.map(x => {
      return {
        codeNiveauOpleiding: selectToTextConvert(x.codeNiveauOpleiding, codeNiveauOpleidingValues),
        indicatieDiploma: selectToTextConvert(x.indicatieDiploma, indicatieDiplomaValues),
        datumDiploma: x.datumDiploma,
        opleidingsnaam: x.opleidingsnaam,
        type: this.checkOpleidingsnaam(x.opleidingsnaam)
      }
    })
  }

  checkOpleidingsnaam(opleidingsnaam: Opleidingsnaam | undefined): boolean {
    return determineOpleidingsnaamType(opleidingsnaam)
  }


}
