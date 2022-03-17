import {Component, Input, OnInit} from '@angular/core';
import {Vacature} from "../../../entities/vacature/vacature";
import {OpleidingsnaamGecodeerd} from "../../../entities/shared/opleidingsnaamGecodeerd";
import {selectToTextConvert} from "../../../forms-util/select-to-text";
import {codeNiveauOpleidingValues, indicatieDiplomaValues} from "../../../forms-util/formcontrol-value-enums";
import {determineOpleidingsnaamType, Opleidingsnaam} from "../../../entities/shared/opleidingsnaam";
import {OpleidingsnaamOngecodeerd} from "../../../entities/shared/opleidingsnaamOngecodeerd";

@Component({
  selector: 'app-opleiding-detailed',
  templateUrl: './opleiding-detailed.component.html',
  styleUrls: ['./opleiding-detailed.component.css']
})
export class OpleidingDetailedComponent implements OnInit {

  @Input()
  data?: Vacature;

  opleidingen?: {
    codeNiveauOpleiding?: string | number;
    indicatieDiploma?: string | number;
    opleidingsnaam?: OpleidingsnaamGecodeerd | OpleidingsnaamOngecodeerd;
    type: boolean
  }[];


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
