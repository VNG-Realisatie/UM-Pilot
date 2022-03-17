import {Component, Input, OnInit} from '@angular/core';
import {MPVacatureMatch} from "../../../entities/vacature/mPVacatureMatch";
import {selectToTextConvert} from "../../../forms-util/select-to-text";
import {codeNiveauOpleidingValues, indicatieDiplomaValues} from "../../../forms-util/formcontrol-value-enums";
import {determineOpleidingsnaamType, Opleidingsnaam} from "../../../entities/shared/opleidingsnaam";
import {OpleidingsnaamGecodeerd} from "../../../entities/shared/opleidingsnaamGecodeerd";
import {Vacature} from "../../../entities/vacature/vacature";
import {MPOpleidingsnaamOngecodeerd} from "../../../entities/vacature/mPOpleidingsnaamOngecodeerd";

@Component({
  selector: 'app-opleiding-match',
  templateUrl: './opleiding-match.component.html',
  styleUrls: ['./opleiding-match.component.css']
})
export class OpleidingMatchComponent implements OnInit {

  @Input()
  data?: MPVacatureMatch | Vacature;

  opleidingen?: {
    codeNiveauOpleiding: string | undefined | number;
    indicatieDiploma: string | undefined | number;
    opleidingsnaam: OpleidingsnaamGecodeerd | MPOpleidingsnaamOngecodeerd | undefined;
    type: boolean
  }[] | undefined;


  constructor() {
  }

  ngOnInit(): void {
    this.opleidingen = this.data?.opleiding?.map(({codeNiveauOpleiding, indicatieDiploma, opleidingsnaam}) => {

      return {
        codeNiveauOpleiding: selectToTextConvert(codeNiveauOpleiding, codeNiveauOpleidingValues),
        indicatieDiploma: selectToTextConvert(indicatieDiploma, indicatieDiplomaValues),
        opleidingsnaam: opleidingsnaam,
        type: this.checkOpleidingsnaam(opleidingsnaam)
      };
    });
  }


  checkOpleidingsnaam(opleidingsnaam: Opleidingsnaam | undefined): boolean {
    return determineOpleidingsnaamType(opleidingsnaam)
  }


}
