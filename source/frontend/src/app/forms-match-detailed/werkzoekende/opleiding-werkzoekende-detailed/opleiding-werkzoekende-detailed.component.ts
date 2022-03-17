import {Component, Input, OnInit} from '@angular/core';
import {selectToTextConvert} from "../../../forms-util/select-to-text";
import {
  codeNiveauOpleidingValues,
  codeStatusOpleiding,
  indicatieDiplomaValues
} from "../../../forms-util/formcontrol-value-enums";
import {determineOpleidingsnaamType, Opleidingsnaam} from "../../../entities/shared/opleidingsnaam";
import {Arbeidsmarktkwalificatie} from "../../../entities/werkzoekende/arbeidsmarktkwalificatie";

@Component({
  selector: 'app-opleiding-werkzoekende-detailed',
  templateUrl: './opleiding-werkzoekende-detailed.component.html',
  styleUrls: ['./opleiding-werkzoekende-detailed.component.css']
})
export class OpleidingWerkzoekendeDetailedComponent implements OnInit {

  @Input()
  data?: Arbeidsmarktkwalificatie

  opleidingen?: {
    codeNiveauOpleiding?: string | number;
    indicatieDiploma?: string | number;
    datumDiploma?: string;
    opleidingsnaam?: Opleidingsnaam;
    type?: boolean;
    datumAanvangVolgenOpleiding?: string;
    datumEindeVolgenOpleiding?: string;
    codeStatusOpleiding?: string | number;
    naamOpleidingsinstituut?: string;
  }[] | undefined


  constructor() {
  }

  ngOnInit(): void {
    this.opleidingen = this.data?.opleiding?.map(x => {
      return {
        codeNiveauOpleiding: selectToTextConvert(x.codeNiveauOpleiding, codeNiveauOpleidingValues),
        indicatieDiploma: selectToTextConvert(x.indicatieDiploma, indicatieDiplomaValues),
        datumDiploma: x.datumDiploma,
        opleidingsnaam: x.opleidingsnaam,
        type: this.checkOpleidingsnaam(x.opleidingsnaam),
        datumAanvangVolgenOpleiding: x.datumAanvangVolgenOpleiding,
        datumEindeVolgenOpleiding: x.datumEindeVolgenOpleiding,
        codeStatusOpleiding: selectToTextConvert(x.codeStatusOpleiding, codeStatusOpleiding),
        naamOpleidingsinstituut: x.naamOpleidingsinstituut
      }
    })
  }

  checkOpleidingsnaam(opleidingsnaam: Opleidingsnaam | undefined): boolean {
    return determineOpleidingsnaamType(opleidingsnaam)
  }

}
