import {Component, Input, OnInit} from '@angular/core';
import {MPVacatureMatch} from "../../../entities/vacature/mPVacatureMatch";
import {selectToTextConvert} from "../../../forms-util/select-to-text";
import {
  beheersingGedragscompetentieValues,
  codeGedragscompetentieValues
} from "../../../forms-util/formcontrol-value-enums";
import {Vacature} from "../../../entities/vacature/vacature";
import {MPArbeidsmarktkwalificatie} from "../../../entities/werkzoekende/mPArbeidsmarktkwalificatie";

@Component({
  selector: 'app-gedragscompetentie-match',
  templateUrl: './gedragscompetentie-match.component.html',
  styleUrls: ['./gedragscompetentie-match.component.css']
})
export class GedragscompetentieMatchComponent implements OnInit {

  @Input()
  data?: MPVacatureMatch | Vacature | MPArbeidsmarktkwalificatie;

  gedragscompetenties?: {
    codeBeheersingGedragscompetentie?: string | number;
    codeGedragscompetentie?: string | number;
    omschrijvingGedragscompetentie?: string
  }[] = [];

  constructor() {
  }

  ngOnInit(): void {
    this.gedragscompetenties = this.data?.gedragscompetentie?.map(({
                                                                     codeGedragscompetentie,
                                                                     omschrijvingGedragscompetentie,
                                                                     codeBeheersingGedragscompetentie
                                                                   }) => {
      const mappedcodeGedrag = selectToTextConvert(codeGedragscompetentie, codeGedragscompetentieValues);
      const mappedBeheersing = selectToTextConvert(codeBeheersingGedragscompetentie, beheersingGedragscompetentieValues);
      return {
        codeGedragscompetentie: mappedcodeGedrag,
        omschrijvingGedragscompetentie: omschrijvingGedragscompetentie,
        codeBeheersingGedragscompetentie: mappedBeheersing
      }
    })


  }


}
